package com.jumbo.store.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.jumbo.store.entity.StoreLocation;
import com.jumbo.store.enums.Status;

@Repository
public interface StoreLocationRepository extends JpaRepository<StoreLocation, Long>{

	@Query(value = "SELECT *, SQRT(\r\n"
			+ "    POW(69.1 * (latitude - ?1), 2) +\r\n"
			+ "    POW(69.1 * (?2 - longitude) * COS(latitude / 57.3), 2)) AS distance\r\n"
			+ "FROM jumbo.store_location where status = 1 ORDER BY distance asc LIMIT 0, ?3", nativeQuery=true)
	List<StoreLocation> findNearestStores(String latitude,String longitude,int limit);
	
	Optional<StoreLocation> findByStatusAndStoreId(Status status,Long storeId);


}
