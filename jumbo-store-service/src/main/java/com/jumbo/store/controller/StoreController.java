package com.jumbo.store.controller;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jumbo.store.dto.StoreDto;
import com.jumbo.store.exception.BusinessException;
import com.jumbo.store.response.CRUDStoreResponse;
import com.jumbo.store.response.NearestStoreResponse;
import com.jumbo.store.response.StoreResponse;
import com.jumbo.store.service.StoreService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/v1/store")
@RequiredArgsConstructor()
@Tag(name = "Jumbo Store Operations")
public class StoreController {

	private final StoreService storeService;

	@GetMapping(value = "/nearest")
	@Operation(summary = "Get nearest jumbo stores", description = "Get nearest jumbo stores")
	@ApiResponse(responseCode = "200", description = "Get nearest jumbo stores")
	public ResponseEntity<?> getNearestJumboStores(@RequestParam(name = "latitude") String latitude,
			@RequestParam(name = "longitude") String longitude, @RequestParam(name = "limit") int limit) {

		NearestStoreResponse response = storeService.findNearestStores(latitude, longitude, limit);

		return new ResponseEntity<NearestStoreResponse>(response, HttpStatus.OK);
	}

	@PostMapping
	@Operation(summary = "Save new store", description = "Save new store")
	@ApiResponse(responseCode = "201", description = "Save new store")
	public ResponseEntity<?> saveJumboStore(@Valid @RequestBody StoreDto storeRequest) throws BusinessException {
		
		CRUDStoreResponse response = storeService.saveJumboStore(storeRequest);

		return new ResponseEntity<CRUDStoreResponse>(response, HttpStatus.CREATED);

	}
	
	@GetMapping("/{storeId}")
	@Operation(summary = "Get jumbo store", description = "Get jumbo store")
	@ApiResponse(responseCode = "200", description = "Get jumbo Store")
	public ResponseEntity<?> getJumboStore(@PathVariable Long storeId) throws BusinessException {

		StoreResponse response = storeService.findJumboStore(storeId);

		return new ResponseEntity<StoreResponse>(response, HttpStatus.OK);
	}
	
	@PutMapping("/{storeId}")
	@Operation(summary = "Update jumbo store", description = "Update jumbo store")
	@ApiResponse(responseCode = "200", description = "Update jumbo Store")
	public ResponseEntity<?> updateJumboStore(@PathVariable Long storeId,@Valid @RequestBody StoreDto storeRequest) throws BusinessException {

		CRUDStoreResponse response = storeService.updateJumboStore(storeId,storeRequest);

		return new ResponseEntity<CRUDStoreResponse>(response, HttpStatus.OK);
	}
	
	@DeleteMapping("/{storeId}")
	@Operation(summary = "Delete jumbo store", description = "Delete jumbo store")
	@ApiResponse(responseCode = "200", description = "Delete jumbo Store")
	public ResponseEntity<?> deleteJumboStore(@PathVariable Long storeId) throws BusinessException {

		CRUDStoreResponse response = storeService.deleteJumboStore(storeId);

		return new ResponseEntity<CRUDStoreResponse>(response, HttpStatus.OK);
	}

}
