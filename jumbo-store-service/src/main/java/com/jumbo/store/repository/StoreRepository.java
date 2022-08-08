package com.jumbo.store.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jumbo.store.entity.Store;
import com.jumbo.store.enums.Status;

@Repository
public interface StoreRepository extends JpaRepository<Store, Long>{
	
	Optional<Store> findByStatusAndUuid(Status status,String uuid);
	
	Optional<Store> findByStatusAndId(Status status,Long storeId);


}
