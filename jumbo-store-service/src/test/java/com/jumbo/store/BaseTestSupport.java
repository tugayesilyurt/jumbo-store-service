package com.jumbo.store;

import java.util.ArrayList;
import java.util.List;

import com.jumbo.store.dto.StoreDto;
import com.jumbo.store.entity.Store;
import com.jumbo.store.entity.StoreAddress;
import com.jumbo.store.entity.StoreLocation;
import com.jumbo.store.enums.LocationType;
import com.jumbo.store.enums.Status;
import com.jumbo.store.response.NearestStoreResponse;
import com.jumbo.store.response.StoreResponse;

public class BaseTestSupport {

	protected final String latitude = "52.090736";
	protected final String longitude = "5.121420";
	protected final int limit = 5;
	protected final Long storeId = 500l;
	protected final static String updatedStoreMessage = "Store successfully updated!";
	protected final static String createdStoreMessage = "Store successfully created!";
	protected final static String deletedStoreMessage = "Store successfully deleted!";
	protected final static String storeNotFound = "Store Not Found!";
	protected final static String storeAlreadyExist = "Store Already Exist!";

	protected NearestStoreResponse getNearestStoreResponse() {

		List<StoreResponse> nearestStores = new ArrayList<StoreResponse>();

		nearestStores.add(StoreResponse.builder().latitude("52.095381").longitude("5.129767").storeId(490l)
				.uuid("sWwKYx4XRAsAAAFIJoIYwKxK").addressName("Jumbo Utrecht Biltstraat").build());
		nearestStores.add(StoreResponse.builder().latitude("52.100556").longitude("5.115163").storeId(493l)
				.uuid("ARcKYx4XJ5cAAAFIckMYwKxK").addressName("Jumbo Utrecht Merelstraat").build());
		nearestStores.add(StoreResponse.builder().latitude("52.082126").longitude("5.134643").storeId(492l)
				.uuid("rzIKYx4Xl5EAAAFIiTMYwKxK").addressName("Jumbo Utrecht Ina Boudier Bakkerlaan").build());
		nearestStores.add(StoreResponse.builder().latitude("52.115515").longitude("5.127659").storeId(495l)
				.uuid("7hQKYx4X6QcAAAFI33UYwKxK").addressName("Jumbo Utrecht Overkapel").build());
		nearestStores.add(StoreResponse.builder().latitude("52.126700").longitude("5.093240").storeId(491l)
				.uuid("AoYKYx4XRswAAAFIHzMYwKxK").addressName("Jumbo Utrecht Franciscusdreef").build());

		return NearestStoreResponse.builder().nearestStores(nearestStores).build();
	}

	protected StoreDto getStoreDto() {

		return StoreDto.builder().city("Zoetermeer").uuid("vNYKYx4XH7YAAAFZar8nMJqL").longitude("4.477336")
				.latitude("52.043506").sapStoreID("3177").locationType(LocationType.SupermarktPuP).build();

	}

	protected Store getStore() {
		
		Store store = Store.builder().uuid("vNYKYx4XH7YAAAFZar8nMJqL").complexNumber("32224").sapStoreId("3177").build();
		store.setId(storeId);

		return store;

	}
	
	protected Store getStorePassive() {
		
		Store store = Store.builder().uuid("vNYKYx4XH7YAAAFZar8nMJqL").complexNumber("32224").sapStoreId("3177").build();
		store.setId(storeId);
		store.setStatus(Status.PASSIVE);
		return store;

	}

	protected StoreAddress getStoreAddress() {

		return StoreAddress.builder().city("Zoetermeer").addressName("Jumbo Zoetermeer Quirinegang").store(getStore()).build();

	}

	protected StoreLocation getStoreLocation() {

		return StoreLocation.builder().locationType(LocationType.SupermarktPuP).latitude("52.043506").longitude("4.477336").store(getStore()).build();

	}

	protected List<StoreLocation> getStoreLocationList() {

		List<StoreLocation> storeLocation = new ArrayList<StoreLocation>();
		Store store = Store.builder().uuid("vNYKYx4XH7YAAAFZar8nMJqL").build();

		storeLocation.add(StoreLocation.builder().latitude("52.095381").longitude("5.129767").store(store).build());
		storeLocation.add(StoreLocation.builder().latitude("52.100556").longitude("5.115163").store(store).build());
		storeLocation.add(StoreLocation.builder().latitude("52.082126").longitude("5.134643").store(store).build());
		storeLocation.add(StoreLocation.builder().latitude("52.115515").longitude("5.127659").store(store).build());
		storeLocation.add(StoreLocation.builder().latitude("52.126700").longitude("5.093240").store(store).build());

		return storeLocation;

	}
	
	protected StoreResponse getStoreResponse() {

		return StoreResponse.builder().uuid("vNYKYx4XH7YAAAFZar8nMJqL").addressName("Jumbo Zoetermeer Quirinegang").longitude("4.477336")
				.latitude("52.043506").build();

	}

}
