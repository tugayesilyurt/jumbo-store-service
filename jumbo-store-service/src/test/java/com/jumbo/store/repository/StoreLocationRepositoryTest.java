package com.jumbo.store.repository;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.jumbo.store.BaseTestSupport;
import com.jumbo.store.entity.Store;
import com.jumbo.store.entity.StoreLocation;
import com.jumbo.store.enums.LocationType;
import com.jumbo.store.enums.Status;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class StoreLocationRepositoryTest extends BaseTestSupport {

	@Autowired
	StoreLocationRepository storeLocationRepository;
	
	@Autowired
	StoreRepository storeRepository;
	
	@Test
	void findByStatusAndStoreId_success() {
		
		Store store = storeRepository.save(getStore());
		storeRepository.save(store);
		
		StoreLocation storeLocation = StoreLocation.builder().locationType(LocationType.SupermarktPuP).latitude("52.043506").longitude("4.477336").store(store).build();
		storeLocationRepository.save(storeLocation);
		
		Optional<StoreLocation> storeLocationResponsse = storeLocationRepository.findByStatusAndStoreId(Status.ACTIVE,store.getId());
		assertTrue(storeLocationResponsse.isPresent());
		storeLocationRepository.deleteAll();
		storeRepository.deleteAll();
		
	}


}
