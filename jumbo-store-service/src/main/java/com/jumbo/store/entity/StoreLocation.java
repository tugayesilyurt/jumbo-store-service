package com.jumbo.store.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.jumbo.store.enums.LocationType;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@SequenceGenerator(name = "SEQ", sequenceName = "store_location_id_seq", allocationSize = 1)
@Table(name = "store_location"
)
public class StoreLocation extends BaseEntity{
	
	@Column(name = "location_type")
	@Enumerated(EnumType.STRING)
    private LocationType locationType;
	
	@Column(name = "longitude")
    private String longitude;
	
	@Column(name = "latitude")
    private String latitude;
	
	@OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "store_id")
    private Store store;


}
