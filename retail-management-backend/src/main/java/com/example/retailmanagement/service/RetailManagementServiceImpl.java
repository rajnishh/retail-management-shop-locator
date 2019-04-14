/**
 * 
 */
package com.example.retailmanagement.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.geo.Distance;
import org.springframework.data.geo.Point;
import org.springframework.data.mongodb.core.geo.GeoJsonPoint;
import org.springframework.stereotype.Service;

import com.example.retailmanagement.model.RetailManagementEntity;
import com.example.retailmanagement.payload.ShopMarkersPayload;
import com.example.retailmanagement.repository.RetailManagementRepository;

/**
 * Implementation class for RetailManagementService.
 * 
 * @author raj
 *
 */
@Service
public class RetailManagementServiceImpl implements RetailManagementService {

	private RetailManagementRepository repository;

	/**
	 * @param repository the repository to set
	 */
	@Autowired
	public void setRepository(RetailManagementRepository repository) {
		this.repository = repository;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.example.retailmanagement.service.RetailShopService#
	 * findByNameAndLocationNear(java.lang.String,
	 * com.mongodb.client.model.geojson.Point,
	 * org.springframework.data.geo.Distance)
	 */
	@Override
	public List<RetailManagementEntity> findByNameAndLocationNear(String sid, Point p, Distance d) {

		return repository.findByShopNameAndLocationNear(sid, p, d);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.example.retailmanagement.service.RetailManagementService#addNewShop(com.
	 * example.retailmanagement.payload.AddShopReq)
	 */
	@Override
	public RetailManagementEntity addNewShop(ShopMarkersPayload shopReq) {
		final GeoJsonPoint locationPoint = new GeoJsonPoint(Double.valueOf(shopReq.getLng()),
				Double.valueOf(shopReq.getLat()));
		return repository.save(RetailManagementEntity.builder().ownerName(shopReq.getOwnerName())
				.shopName(shopReq.getShopName()).category(shopReq.getCategory()).location(locationPoint)
				.street(shopReq.getStreet()).city(shopReq.getCity()).postCode(shopReq.getPostCode())
				.state(shopReq.getState()).country(shopReq.getCountry()).icon(shopReq.getIcon()).build());

	}

	@Override
	public List<ShopMarkersPayload> listAll() {
		return convertEntityListToResponseList(repository.findAll());
	}

	/**
	 * convertEntityListToResponseList method to convert entity list model to
	 * ShopMarkersPayload list.
	 * 
	 * @param uploads
	 * @return
	 */
	private List<ShopMarkersPayload> convertEntityListToResponseList(final List<RetailManagementEntity> entities) {
		return entities.stream()
				.map(entity -> ShopMarkersPayload.builder().category(entity.getCategory()).city(entity.getCity())
						.country(entity.getCountry()).ownerName(entity.getOwnerName()).shopName(entity.getShopName())
						.state(entity.getState()).street(entity.getStreet()).postCode(entity.getPostCode())
						.lng(String.valueOf(entity.getLocation().getX())).icon(entity.getIcon())
						.lat(String.valueOf(entity.getLocation().getY())).build())
				.collect(Collectors.toList());

	}

}
