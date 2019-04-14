/**
 * 
 */
package com.example.retailmanagement.controller;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.geo.Distance;
import org.springframework.data.geo.Metrics;
import org.springframework.data.geo.Point;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.example.retailmanagement.model.RetailManagementEntity;
import com.example.retailmanagement.payload.ApiResponse;
import com.example.retailmanagement.payload.ShopMarkersPayload;
import com.example.retailmanagement.service.RetailManagementService;

/**
 * Rest Controller class for handling search the stores and creating the stores.
 * 
 * @author raj
 *
 */
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class RetailManagementController {

	private RetailManagementService retailManagementService;

	/**
	 * @param retailShopService the retailShopService to set
	 */
	@Autowired
	public void setRetailShopService(RetailManagementService retailManagementService) {
		this.retailManagementService = retailManagementService;
	}

	@GetMapping(value = "/findShop", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<RetailManagementEntity>> findStoreLocations(@RequestParam("lat") String latitude,
			@RequestParam("long") String longitude, @RequestParam("d") double distance,
			@RequestParam(value = "s", required = false) String subjects) {

		List<RetailManagementEntity> entityRes = this.retailManagementService.findByNameAndLocationNear(subjects,
				new Point(Double.valueOf(longitude), Double.valueOf(latitude)),
				new Distance(distance, Metrics.KILOMETERS));
		return new ResponseEntity<List<RetailManagementEntity>>(entityRes, HttpStatus.OK);
	}

	/**
	 * Controller method to list all shop details.
	 * 
	 * @return
	 */
	@GetMapping(value = "/listAllShops", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> listAllStores() {

		return new ResponseEntity<List<ShopMarkersPayload>>(this.retailManagementService.listAll(), HttpStatus.OK);
	}

	/**
	 * Controller method to add new shop in system.
	 * 
	 * @param shopReq
	 * @return
	 */
	@PostMapping(value = "/addNewShop", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> addNewShop(@Valid @RequestBody ShopMarkersPayload shopReq) {

		RetailManagementEntity newShop = this.retailManagementService.addNewShop(shopReq);

		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{shopId}")
				.buildAndExpand(newShop.getId()).toUri();

		return ResponseEntity.created(location).body(new ApiResponse(true, "New Shop added Successfully"));
	}

}
