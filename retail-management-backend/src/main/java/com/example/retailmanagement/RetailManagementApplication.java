package com.example.retailmanagement;

import java.util.TimeZone;

import javax.annotation.PostConstruct;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class RetailManagementApplication {

	/**
	 * Main method.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		SpringApplication.run(RetailManagementApplication.class, args);
	}
	
	/**
	   * init method.
	   */
	  @PostConstruct
	  void init() {
	    TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
	  }

}
