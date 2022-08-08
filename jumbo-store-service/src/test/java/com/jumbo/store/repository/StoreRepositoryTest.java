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
import com.jumbo.store.enums.Status;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class StoreRepositoryTest extends BaseTestSupport {

	@Autowired
	StoreRepository storeRepository;

	@Test
	void findByStatusAndId_success() {
		Store store = storeRepository.save(getStore());
		Optional<Store> storeResponse = storeRepository.findByStatusAndId(Status.ACTIVE, store.getId());
		assertTrue(storeResponse.isPresent());
		storeRepository.deleteAll();
	}

	@Test
	void findByStatusAndUuid_success() {
		Store store = storeRepository.save(getStore());
		Optional<Store> storeResponse = storeRepository.findByStatusAndUuid(Status.ACTIVE, store.getUuid());
		assertTrue(storeResponse.isPresent());
		storeRepository.deleteAll();

	}

}
