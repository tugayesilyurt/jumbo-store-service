package com.jumbo.store.runner;

import java.io.IOException;
import java.io.InputStream;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jumbo.store.dto.StoreJsonDto;
import com.jumbo.store.entity.Store;
import com.jumbo.store.entity.StoreAddress;
import com.jumbo.store.entity.StoreLocation;
import com.jumbo.store.enums.Status;
import com.jumbo.store.repository.StoreAddressRepository;
import com.jumbo.store.repository.StoreLocationRepository;
import com.jumbo.store.repository.StoreRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Component
@RequiredArgsConstructor
@Slf4j
public class JsonRunner implements CommandLineRunner {

	private final StoreRepository storeRepository;
	private final StoreAddressRepository storeAddressRepository;
	private final StoreLocationRepository storeLocationRepository;

	@Override
	public void run(String... args) throws Exception {

		ObjectMapper mapper = new ObjectMapper();
		StopWatch stopWatch = new StopWatch();
		TypeReference<StoreJsonDto> typeReference = new TypeReference<StoreJsonDto>() {
		};
		try (InputStream inputStream = TypeReference.class.getResourceAsStream("/json/stores.json")) {
			StoreJsonDto storesDto = mapper.readValue(inputStream, typeReference);
			stopWatch.start();
			storesDto.getStores().stream().forEach(data -> {

				final String uuid = data.getUuid();
				if (storeRepository.findByStatusAndUuid(Status.ACTIVE, uuid).isEmpty()) {

					Store store = Store.builder().uuid(uuid).sapStoreId(data.getSapStoreID())
							.showWarningMessage(data.getShowWarningMessage()).complexNumber(data.getComplexNumber())
							.todayClose(data.getTodayClose()).todayOpen(data.getTodayOpen())
							.collectionPoint(data.getCollectionPoint()).build();

					StoreAddress storeAddress = StoreAddress.builder().city(data.getCity())
							.postalCode(data.getPostalCode()).street(data.getStreet()).street2(data.getStreet2())
							.street3(data.getStreet3()).addressName(data.getAddressName()).store(store).build();

					StoreLocation storeLocation = StoreLocation.builder().latitude(data.getLatitude())
							.longitude(data.getLongitude()).locationType(data.getLocationType()).store(store).build();

					storeRepository.save(store);
					storeAddressRepository.save(storeAddress);
					storeLocationRepository.save(storeLocation);
				}

			});
			stopWatch.stop();
			log.info("Read Json : " + stopWatch.getTotalTimeSeconds());

		} catch (IOException e) {
			log.error("Unable to read json: " + e.getMessage());
		}

	}

}
