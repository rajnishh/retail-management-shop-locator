package com.example.retailmanagement.payload;

import java.io.Serializable;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * ShopMarkersPayload payload class.
 * 
 * @author raj
 *
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ShopMarkersPayload implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2621798827351506962L;
	
	/**
	 * latitude
	 */
	private String lat;
	/**
	 * longitude
	 */
	private String lng;
	
	/**
	 * store Name.
	 */
	private String shopName;
	
	/**
	 * icon.
	 */
	private String icon;
	
	/**
	 * owner Name.
	 */
	private String ownerName;
	
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
	 * category the shop category.
	 */
	private String category;
	
	
	/**
	 * createdAt field to audit.
	 */
	private Date createdAt = new Date();
}