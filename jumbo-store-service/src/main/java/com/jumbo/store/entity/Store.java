package com.jumbo.store.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Index;
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
@SequenceGenerator(name = "SEQ", sequenceName = "store_id_seq", allocationSize = 1)
@Table(name = "store", indexes = { @Index(name = "storeidx", columnList = "status,uuid", unique = true), })
public class Store extends BaseEntity {

	@Column(name = "uuid")
	private String uuid;

	@Column(name = "sap_store_id")
	private String sapStoreId;

	@Column(name = "show_warning_message")
	private Boolean showWarningMessage;

	@Column(name = "complex_number")
	private String complexNumber;

	@Column(name = "today_open")
	private String todayOpen;

	@Column(name = "today_close")
	private String todayClose;

	@Column(name = "collection_point")
	private Boolean collectionPoint;

}
