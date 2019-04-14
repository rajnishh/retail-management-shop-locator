package com.example.retailmanagement.service;

import java.util.List;

import org.springframework.data.geo.Distance;
import org.springframework.data.geo.Point;

import com.example.retailmanagement.model.RetailManagementEntity;
import com.example.retailmanagement.payload.ShopMarkersPayload;

/**
 * RetailManagement Service class.
 * @author raj
 *
 */
public interface RetailManagementService {

	/**
	 * findBySubjectAndLocationNear method to find stores.
	 */
	List<RetailManagementEntity>  findByNameAndLocationNear(String sid, Point p, Distance d);

	/**
	 *  Service method to add new Shop.
	 * @param req
	 */
	RetailManagementEntity addNewShop(ShopMarkersPayload req);
		
	/**
	 * listAll Shop Details.
	 * @return
	 */
	List<ShopMarkersPayload> listAll();

}
