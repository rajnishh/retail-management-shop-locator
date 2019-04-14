package com.example.retailmanagement.model;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.geo.GeoJsonPoint;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * RetailManagement entityS class to store the store's location.
 * @author raj
 *
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonIgnoreProperties(value = {"createdAt"}, allowGetters = true)
@Document(collection = "retail_management")
public class RetailManagementEntity {

	/**
	 * Store id.
	 */
	@Id
	private String id;
	
	/**
	 * store Name.
	 */
	private String shopName;
	
	/**
	 * owner Name.
	 */
	private String ownerName;
	
	/**
	 * Store location.
	 */
	private GeoJsonPoint location;
	
	/**
	 * category the shop category.
	 */
	private String category;
	
	/**
	 * street Name.
	 */
	private String street;
	
	/**
	 * city Name.
	 */
	private String city;
	
	/**
	 * state Name.
	 */
	private String state;
	
	/**
	 * postCode.
	 */
	private String postCode;
	
	/**
	 * country.
	 */
	private String country;
	
	/**
	 * icon.
	 */
	private String icon;
	
	/**
	 * createdAt field to audit.
	 */
	private Date createdAt = new Date();
	
	
}