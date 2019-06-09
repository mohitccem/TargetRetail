# TargetRetail
App to get and update product price

## Tech Stack
Java JDK 1.8.0  
Spring 2.1.3 Release  
Maven 3.6.1  
MySQL 5.7  
Tomcat 9.0.16  

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

## Configure MySQL, 

Update below database configuration -> TargetRetail/src/main/resources/application.properties  
    
```spring.datasource.url=jdbc:mysql://localhost:3306/retail
spring.datasource.username=mkyong
spring.datasource.password=password```




