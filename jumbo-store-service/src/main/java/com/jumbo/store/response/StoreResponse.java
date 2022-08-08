package com.jumbo.store.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class StoreResponse {
	
	private Long storeId;
	private String uuid;
	private String addressName;
	private String latitude;
	private String longitude;

}
