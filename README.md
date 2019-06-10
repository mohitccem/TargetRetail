# TargetRetail
App to get and update product price

## Tech Stack
Java JDK 1.8.0  
Spring 2.1.3 Release  
Maven 3.6.1  
MySQL 5.7  
Tomcat 9.0.16  

## Pull Project & compile  
Get the source code from github.  

```cd TargetReatil```   
```mvn clean package```   

## Install MySQL and Update it

Download and install mysql from the below mentioned link :  
```https://dev.mysql.com/doc/refman/8.0/en/installing.html```

Login into mysql database  
```mysql -u [username] -p;```

Create a database and use it   
```CREATE DATABASE [IF NOT EXISTS] retail;```

```USE retail;```  

```CREATE TABLE product (id INT, price DOUBLE, currency_code VARCHAR(20), PRIMARY KEY(id));```  

```INSERT INTO product(id, price, currency_code) VALUES (13860416);```  

## Configure MySQL  

Update below database configuration -> TargetRetail/src/main/resources/application.properties    
    
spring.datasource.username=mkyong  
spring.datasource.password=password  
spring.datasource.url=jdbc:mysql://localhost:3306/retail  

## Starting the application

Run the jar inside target folder  

```java -jar TargetRetail/target/retail-1.0-FINAL.jar```

## Calling the REST-API's

1.  API to retrieve product name and price details using product id  

```GET http://localhost:8080/retail/products/13860416```  

### Response Body :-  
```json
{
  "id": 13860416,
  "name": "Progressive Power Yoga: Sedona Experie (DVD)",
  "productPrice": {
    "price": 72.8,
    "currencyCode": "USD"
  }
}
```  
2. API to update price of a product.  

```PUT http://localhost:8080/retail/products/13860416```   

### Request Body:-
```json
{
  "id": 13860416,
  "name": "Progressive Power Yoga: Sedona Experie (DVD)",
  "productPrice": {
    "price": 72.99,
    "currencyCode": "USD"
  }
}
```

### Response :-  
```json
{
  "id": 13860416,
  "name": "Progressive Power Yoga: Sedona Experie (DVD)",
  "productPrice": {
    "price": 72.8,
    "currencyCode": "USD"
  }
}
```
## Additional Scope

1. Application can be made more secure by adding Authentication/Authorization mechanisms across services.
2. Add thread pool to handle concurrrent requests.
3. Validation of inputs like product id, price etc.
4. Cache the product name from redsky.target.com to avoid latency.
5. Dockerize the app .
