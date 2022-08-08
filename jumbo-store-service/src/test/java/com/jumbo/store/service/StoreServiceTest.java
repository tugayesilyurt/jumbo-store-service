package com.jumbo.store.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.jumbo.store.BaseTestSupport;
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

@ExtendWith(MockitoExtension.class)
public class StoreServiceTest extends BaseTestSupport {

	@InjectMocks
	private StoreService storeService;

	@Mock
	private StoreRepository storeRepository;

	@Mock
	private StoreLocationRepository storeLocationRepository;

	@Mock
	private StoreAddressRepository storeAddressRepository;

	@Test
	void testGetNearestJumboStore_shouldListNearestJumboStore() {

		List<StoreLocation> storeLocation = getStoreLocationList();
		NearestStoreResponse nearestStoreResponse = getNearestStoreResponse();

		Mockito.when(storeLocationRepository.findNearestStores(latitude, longitude, limit)).thenReturn(storeLocation);

		NearestStoreResponse response = storeService.findNearestStores(latitude, longitude, limit);

		Assert.assertEquals(nearestStoreResponse.getNearestStores().size(), response.getNearestStores().size());
		Mockito.verify(storeLocationRepository).findNearestStores(latitude, longitude, limit);

	}

	@Test
	void testSaveJumboStore_shouldSaveJumboStore() throws BusinessException {

		StoreDto storeDto = getStoreDto();
		Store store = getStore();
		StoreAddress storeAddress = getStoreAddress();
		StoreLocation storeLocation = getStoreLocation();
		CRUDStoreResponse crudResponse = CRUDStoreResponse.builder().message(createdStoreMessage).build();

		when(storeRepository.findByStatusAndUuid(any(Status.class), any(String.class))).thenReturn(Optional.empty());

		when(storeRepository.save(any(Store.class))).thenReturn(store);
		when(storeAddressRepository.save(any(StoreAddress.class))).thenReturn(storeAddress);
		when(storeLocationRepository.save(any(StoreLocation.class))).thenReturn(storeLocation);

		CRUDStoreResponse response = storeService.saveJumboStore(storeDto);
		Assert.assertEquals(response, crudResponse);
		Mockito.verify(storeRepository).save(any(Store.class));

	}

	@Test
	void testGetJumboStore_shouldReturnJumboStore() throws BusinessException {

		Store store = getStore();
		StoreAddress storeAddress = getStoreAddress();
		StoreLocation storeLocation = getStoreLocation();
		StoreResponse storeResponse = getStoreResponse();

		when(storeRepository.findByStatusAndId(any(Status.class), any(Long.class))).thenReturn(Optional.of(store));
		when(storeAddressRepository.findByStatusAndStoreId(any(Status.class), any(Long.class))).thenReturn(Optional.of(storeAddress));
		when(storeLocationRepository.findByStatusAndStoreId(any(Status.class), any(Long.class))).thenReturn(Optional.of(storeLocation));
		
		StoreResponse response = storeService.findJumboStore(storeId);

		assertEquals(response.getUuid(), storeResponse.getUuid());
		verify(storeRepository, times(1)).findByStatusAndId(any(Status.class), any(Long.class));

	}
	
	@Test
	void testGetJumboStore_whenStoreNotExist_shouldThrowBusinessException() {
				
		Mockito.when(storeRepository.findByStatusAndId(any(Status.class), any(Long.class)))
				.thenReturn(Optional.empty());

		BusinessException exception = assertThrows(BusinessException.class, () -> storeService.findJumboStore(storeId));
		Assert.assertEquals(storeNotFound, exception.getMessage());

		Mockito.verifyNoInteractions(storeAddressRepository);
	}
	
	@Test
	void testDeleteJumboStore_shouldStatusPassiveJumboStore() throws BusinessException {

		Store store = getStore();
		Store storePassive = getStorePassive();
		CRUDStoreResponse crudResponse = CRUDStoreResponse.builder().message(deletedStoreMessage).build();
		
		when(storeRepository.findByStatusAndId(any(Status.class), any(Long.class))).thenReturn(Optional.of(store));
		when(storeRepository.save(any(Store.class))).thenReturn(storePassive);
		
		CRUDStoreResponse response = storeService.deleteJumboStore(storeId);
		
		Assert.assertEquals(response, crudResponse);
		Mockito.verify(storeRepository).save(any(Store.class));

	}
	
	@Test
	void testDeleteJumboStore_whenStoreNotExist_shouldThrowBusinessException() {
				
		Mockito.when(storeRepository.findByStatusAndId(any(Status.class), any(Long.class)))
				.thenReturn(Optional.empty());

		BusinessException exception = assertThrows(BusinessException.class, () -> storeService.deleteJumboStore(storeId));
		Assert.assertEquals(storeNotFound, exception.getMessage());

		Mockito.verifyNoInteractions(storeAddressRepository);
	}
	
	@Test
	void testUpdateJumboStore_whenStoreExist_shouldUpdateJumboStore() throws BusinessException {

		StoreDto storeDto = getStoreDto();
		Store store = getStore();
		CRUDStoreResponse crudResponse = CRUDStoreResponse.builder().message(updatedStoreMessage).build();
		
		when(storeRepository.findByStatusAndId(any(Status.class), any(Long.class))).thenReturn(Optional.of(store));
		CRUDStoreResponse response = storeService.updateJumboStore(storeId,storeDto);

		Assert.assertEquals(response, crudResponse);
		verify(storeRepository, times(1)).findByStatusAndId(any(Status.class), any(Long.class));
	}
	
	@Test
	void testUpdateJumboStore_whenStoreNotExist_shouldThrowBusinessException() {
			
		StoreDto storeDto = getStoreDto();
		Mockito.when(storeRepository.findByStatusAndId(any(Status.class), any(Long.class)))
				.thenReturn(Optional.empty());

		BusinessException exception = assertThrows(BusinessException.class, () -> storeService.updateJumboStore(storeId,storeDto));
		Assert.assertEquals(storeNotFound, exception.getMessage());

		Mockito.verifyNoInteractions(storeAddressRepository);
	}

}
