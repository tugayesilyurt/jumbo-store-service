package com.jumbo.store.response;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class NearestStoreResponse {
	
	private List<StoreResponse> nearestStores;

}
