package com.jumbo.store.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.jumbo.store.BaseTestSupport;
import com.jumbo.store.dto.StoreDto;
import com.jumbo.store.exception.BusinessException;
import com.jumbo.store.service.StoreService;

@ExtendWith(MockitoExtension.class)
public class StoreControllerTest extends BaseTestSupport {

	@InjectMocks
	private StoreController storeController;

	@Mock
	private StoreService storeService;

	@Test
	void testGetNearestJumboStore_shouldListNearestJumboStore() {

		ResponseEntity<?> responseEntity = storeController.getNearestJumboStores(latitude, longitude,
				limit);
		
		assertEquals(HttpStatus.valueOf(200), responseEntity.getStatusCode());
		verify(storeService, times(1)).findNearestStores(latitude, longitude, limit);

	}
	
	@Test
	void testSaveJumboStore_shouldSaveJumboStore() throws BusinessException {
		
		StoreDto storeDto = getStoreDto();		
		ResponseEntity<?> responseEntity = storeController.saveJumboStore(storeDto);
		 
		assertEquals(HttpStatus.valueOf(201), responseEntity.getStatusCode());
		verify(storeService, times(1)).saveJumboStore(storeDto);

	}
	
    @Test
    void testGetJumboStore_shouldReturnJumboStore() throws BusinessException {
    	
        ResponseEntity<?> responseEntity = storeController.getJumboStore(500l);
        
        assertEquals(HttpStatus.valueOf(200), responseEntity.getStatusCode());
        verify(storeService, times(1)).findJumboStore(500l);

    }
    
    @Test
    void testDeleteJumboStore_shouldDeleteJumboStore() throws BusinessException {
    	
        ResponseEntity<?> responseEntity = storeController.deleteJumboStore(500l);
        
        assertEquals(HttpStatus.valueOf(200), responseEntity.getStatusCode());
        verify(storeService, times(1)).deleteJumboStore(500l);

    }
    
    @Test
    void testUpdateJumboStore_shouldUpdateJumboStore() throws BusinessException {
    	
    	StoreDto storeDto = getStoreDto();		
        ResponseEntity<?> responseEntity = storeController.updateJumboStore(500l,storeDto);
        
        assertEquals(HttpStatus.valueOf(200), responseEntity.getStatusCode());
        verify(storeService, times(1)).updateJumboStore(500l,storeDto);

    }

}
