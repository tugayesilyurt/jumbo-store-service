package com.jumbo.store.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jumbo.store.dto.StoreDto;
import com.jumbo.store.entity.Store;
import com.jumbo.store.entity.StoreAddress;
import com.jumbo.store.entity.StoreLocation;
import com.jumbo.store.enums.Status;
import com.jumbo.store.exception.BusinessException;
import com.jumbo.store.repository.StoreAddressRepository;
import com.jumbo.store.repository.StoreLocationRepository;
import com.jumbo.store.repository.StoreRepository;
import com.jumbo.store.response.CRUDStoreResponse;
import com.jumbo.store.response.NearestStoreResponse;
import com.jumbo.store.response.StoreResponse;
import com.jumbo.store.util.JumboUtil;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class StoreService {

	private final StoreLocationRepository storeLocationRepository;
	private final StoreAddressRepository storeAddressRepository;
	private final StoreRepository storeRepository;

	private final static String updatedStoreMessage = "Store successfully updated!";
	private final static String createdStoreMessage = "Store successfully created!";
	private final static String deletedStoreMessage = "Store successfully deleted!";
	private final static String storeNotFound = "Store Not Found!";
	
	@Transactional
	public NearestStoreResponse findNearestStores(String latitude, String longitude, int limit) {

		List<StoreLocation> storeLocation = storeLocationRepository.findNearestStores(latitude, longitude, limit);

		final NearestStoreResponse nearestStoreResponse = new NearestStoreResponse();
		final List<StoreResponse> storeResponseList = new ArrayList<StoreResponse>();

		storeLocation.stream().forEach(data -> {

			Store store = data.getStore();
			Optional<StoreAddress> storeAddress = storeAddressRepository.findByStatusAndStoreId(Status.ACTIVE,
					store.getId());

			StoreResponse storeResponse = new StoreResponse();
			storeResponse.setStoreId(store.getId());
			storeResponse.setUuid(data.getStore().getUuid());
			storeResponse.setLatitude(data.getLatitude());
			storeResponse.setLongitude(data.getLongitude());
			storeResponse
					.setAddressName(storeAddress.isPresent() ? storeAddress.get().getAddressName() : StringUtils.EMPTY);

			storeResponseList.add(storeResponse);

		});

		nearestStoreResponse.setNearestStores(storeResponseList);

		return nearestStoreResponse;
	}

	@Transactional
	public CRUDStoreResponse saveJumboStore(StoreDto data) throws BusinessException{

		String uuid = JumboUtil.createRandomUuid();
		while(storeRepository.findByStatusAndUuid(Status.ACTIVE, uuid).isPresent()) {
			uuid = JumboUtil.createRandomUuid();
		}
		
		Store store = Store.builder().uuid(uuid).sapStoreId(data.getSapStoreID())
				.showWarningMessage(data.getShowWarningMessage()).complexNumber(data.getComplexNumber())
				.todayClose(data.getTodayClose()).todayOpen(data.getTodayOpen())
				.collectionPoint(data.getCollectionPoint()).build();

		StoreAddress storeAddress = StoreAddress.builder().city(data.getCity()).postalCode(data.getPostalCode())
				.street(data.getStreet()).street2(data.getStreet2()).street3(data.getStreet3())
				.addressName(data.getAddressName()).store(store).build();

		StoreLocation storeLocation = StoreLocation.builder().latitude(data.getLatitude())
				.longitude(data.getLongitude()).locationType(data.getLocationType()).store(store).build();

		storeRepository.save(store);
		storeAddressRepository.save(storeAddress);
		storeLocationRepository.save(storeLocation);
			
		
		return CRUDStoreResponse.builder().message(createdStoreMessage).build();
	}

	@Transactional
	public StoreResponse findJumboStore(Long storeId) throws BusinessException{

		StoreResponse storeResponse = new StoreResponse();
		Store store = storeRepository.findByStatusAndId(Status.ACTIVE, storeId)
				.orElseThrow(() -> new BusinessException(storeNotFound));

		Optional<StoreAddress> storeAddress = storeAddressRepository.findByStatusAndStoreId(Status.ACTIVE, storeId);
		Optional<StoreLocation> storeLocation = storeLocationRepository.findByStatusAndStoreId(Status.ACTIVE, storeId);

		storeResponse.setStoreId(store.getId());
		storeResponse.setUuid((store.getUuid()));
		storeResponse.setLatitude(storeLocation.isPresent() ? storeLocation.get().getLatitude() : StringUtils.EMPTY);
		storeResponse.setLongitude(storeLocation.isPresent() ? storeLocation.get().getLongitude() : StringUtils.EMPTY);
		storeResponse
				.setAddressName(storeAddress.isPresent() ? storeAddress.get().getAddressName() : StringUtils.EMPTY);

		return storeResponse;

	}
	
	@Transactional
	public CRUDStoreResponse deleteJumboStore(Long storeId) throws BusinessException{
		
		Store store = storeRepository.findByStatusAndId(Status.ACTIVE, storeId)
				.orElseThrow(() -> new BusinessException(storeNotFound));
		store.setStatus(Status.PASSIVE);
		storeRepository.save(store);
		
		Optional<StoreAddress> storeAddress = storeAddressRepository.findByStatusAndStoreId(Status.ACTIVE, storeId);
		if(storeAddress.isPresent()) {
			storeAddress.get().setStatus(Status.PASSIVE);
			storeAddressRepository.save(storeAddress.get());
		}
				
		Optional<StoreLocation> storeLocation = storeLocationRepository.findByStatusAndStoreId(Status.ACTIVE, storeId);
		if(storeLocation.isPresent()) {
			storeLocation.get().setStatus(Status.PASSIVE);
			storeLocationRepository.save(storeLocation.get());
		}
		
		
		return CRUDStoreResponse.builder().message(deletedStoreMessage).build();
		
	}
	
	@Transactional
	public CRUDStoreResponse updateJumboStore(Long storeId,StoreDto data) throws BusinessException{

		Store store = storeRepository.findByStatusAndId(Status.ACTIVE, storeId)
				.orElseThrow(() -> new BusinessException(storeNotFound));
		
		store.setUuid(data.getUuid());
		store.setSapStoreId(data.getSapStoreID());
		store.setShowWarningMessage(data.getShowWarningMessage());
		store.setComplexNumber(data.getComplexNumber());
		store.setTodayClose(data.getTodayClose());
		store.setTodayOpen(data.getTodayOpen());
		store.setCollectionPoint(data.getCollectionPoint());
		storeRepository.save(store);
		
		Optional<StoreAddress> storeAddress = storeAddressRepository.findByStatusAndStoreId(Status.ACTIVE, storeId);
		if(storeAddress.isPresent()) {
			storeAddress.get().setCity(data.getCity());
			storeAddress.get().setPostalCode(data.getPostalCode());
			storeAddress.get().setStreet(data.getStreet());
			storeAddress.get().setStreet(data.getStreet2());
			storeAddress.get().setStreet(data.getStreet3());
			storeAddress.get().setAddressName(data.getAddressName());
			storeAddressRepository.save(storeAddress.get());
		}
		
		Optional<StoreLocation> storeLocation = storeLocationRepository.findByStatusAndStoreId(Status.ACTIVE, storeId);
		if(storeLocation.isPresent()) {
			storeLocation.get().setLatitude(data.getLatitude());
			storeLocation.get().setLongitude(data.getLongitude());
			storeLocation.get().setLocationType(data.getLocationType());
			storeLocationRepository.save(storeLocation.get());
		}

		storeRepository.save(store);
		
		return CRUDStoreResponse.builder().message(updatedStoreMessage).build();
	}
	

}
