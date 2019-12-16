Java, Spring Boot, MySQL, JPA, Hibernate Rest API, JUnit, and Integration Test Case

**Requirements**
Java - 1.8.x
Maven - 3.x.x
Mysql - 5.x.x

**Steps to Setup**
1. Clone the application

git clone https://github.com/anand449/assignment.git
2. Create Mysql database

create database test_db
3. Change mysql username and password as per your installation

open src/main/resources/application.properties

change spring.datasource.username and spring.datasource.password as per your mysql installation

4. Build and run the app using maven

mvn package
java -jar target/assignment-1.jar

Alternatively, you can run the app without packaging it using -

mvn spring-boot:run
The app will start running at http://localhost:8080

**Explore Rest APIs**

The app defines following Realm APIs.

GET /service/user/realm/{id}

POST /service/user/realm

You can test them using postman or any other rest client.