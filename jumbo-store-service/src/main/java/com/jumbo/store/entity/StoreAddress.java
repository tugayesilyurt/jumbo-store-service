package com.jumbo.store.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

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
@SequenceGenerator(name = "SEQ", sequenceName = "store_address_id_seq", allocationSize = 1)
@Table(name = "store_address")
public class StoreAddress extends BaseEntity{
	
	@Column(name = "city")
    private String city;

	@Column(name = "postal_code")
    private String postalCode;
	
	@Column(name = "street")
    private String street;
	
	@Column(name = "street2")
    private String street2;
	
	@Column(name = "street3")
    private String street3;
	
	@Column(name = "address_name")
    private String addressName;
	
	@OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "store_id")
    private Store store;

}
