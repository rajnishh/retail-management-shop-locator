# Spring Boot 2.x and Angular 7 Retail-Management-Shop-Locator Project using Google Map

This is an Angular 7 Google Maps Shop locator project. 

## Clone this project

```
git clone git@github.com:rajnishh/retail-management-shop-locator.git .

```

## Backend Project

Backend Rest API has been written in Spring Boot 2.x and Java 8. We are using Spring Data MongoDB as database.

The technologies used:
  Spring Boot
  Spring Data MongoDB
  Lombok
  Gradle
  
## Build the backend Project

Setup the MongoDB 

If you have already installed MongoDB then define your database and create an index. if not then please install MongoDB and follow the below steps.

  use retail_management
  db.locations.createIndex( { location : "2dsphere" } )
  
Please verify the DB url (I have specified the DB URL in application.yaml file):
  
  ### MONGODB (MongoProperties)
spring:
  data.mongodb.uri: mongodb://localhost:27017/retail_management

## Build & Run the Application.

mvn spring-boot:run


## Front End Project
Front End Project is developed in Angular 7 using Google MAP API.

## Development server

Run `ng serve` for a dev server. Navigate to `http://localhost:4200/`. The app will automatically reload if you change any of the source files.

## Build and Deploy in Prod

Run `ng build` to build the project. The build artifacts will be stored in the `dist/` directory. Use the `-prod` flag for a production build.

## Features

* Add the new Retail Shop based on the category. i.e; General Store, Mall, Medical Store and Super Market.
* Locate the Shop using Name and address.
* Display shop information in list and in Map.

##ToDos

* Authentication Implementation (Registration and Login)
* API Security
* Custom Icons to display the shop based on categories
* UI inhancement
* Deployment on Heroku.

