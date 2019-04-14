package com.example.retailmanagement.repository;

import java.util.List;

import org.springframework.data.geo.Distance;
import org.springframework.data.geo.Point;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.example.retailmanagement.model.RetailManagementEntity;

@Repository
public interface RetailManagementRepository extends MongoRepository<RetailManagementEntity, String> {

  List<RetailManagementEntity> findByShopNameAndLocationNear(String sid, Point p, Distance d);
  

}