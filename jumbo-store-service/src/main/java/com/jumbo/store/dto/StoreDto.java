package com.jumbo.store.dto;

import javax.validation.constraints.NotBlank;

import com.jumbo.store.enums.LocationType;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class StoreDto {
	
	@NotBlank(message = "city can not be null or empty")
	private String city;
	
	private String postalCode;
	
	@NotBlank(message = "street can not be null or empty")
	private String street;
	
	private String street2;
	
	private String street3;
	
	@NotBlank(message = "addressName can not be null or empty")
	private String addressName;
	
	private String uuid;
	
	@NotBlank(message = "longitude can not be null or empty")
	private String longitude;
	
	@NotBlank(message = "latitude can not be null or empty")	
	private String latitude;
	
	private String complexNumber;	
	private Boolean showWarningMessage;	
	private String todayOpen;
	private LocationType locationType;	
	private Boolean collectionPoint;
	private String sapStoreID;
	private String todayClose;

}
