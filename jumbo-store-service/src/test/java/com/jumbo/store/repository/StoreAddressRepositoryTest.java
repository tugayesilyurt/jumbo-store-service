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
import com.jumbo.store.entity.StoreAddress;
import com.jumbo.store.enums.Status;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class StoreAddressRepositoryTest extends BaseTestSupport {

	@Autowired
	StoreAddressRepository storeAddressRepository;
	
	@Autowired
	StoreRepository storeRepository;
	
	@Test
	void findByStatusAndStoreId_success() {
		
		Store store = storeRepository.save(getStore());
		StoreAddress storeAddress = StoreAddress.builder().city("Zoetermeer").addressName("Jumbo Zoetermeer Quirinegang").store(store).build();
		storeAddressRepository.save(storeAddress);
		Optional<StoreAddress> storeAddressResponse = storeAddressRepository.findByStatusAndStoreId(Status.ACTIVE, store.getId());
		assertTrue(storeAddressResponse.isPresent());
		storeAddressRepository.deleteAll();
		storeRepository.deleteAll();

	}


}
