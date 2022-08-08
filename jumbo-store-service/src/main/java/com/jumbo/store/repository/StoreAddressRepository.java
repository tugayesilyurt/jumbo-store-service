package com.jumbo.store.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jumbo.store.entity.StoreAddress;
import com.jumbo.store.enums.Status;

@Repository
public interface StoreAddressRepository extends JpaRepository<StoreAddress, Long>{

	Optional<StoreAddress> findByStatusAndStoreId(Status status,Long storeId);

}
