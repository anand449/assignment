# Summary

The application has been implemented as a Java 8 + web-app using Spring Boot, RESTful APIs, MVC, MySQL Database with JPA APIs and has deployable WAR file to Tomcat Server.

It also contains Unit and Integration tests written using JUnit.

## Requirements
The following are the required software for this application:

- Java version 1.8.x
- Maven version 3.x.x
- MySQL version 5.x.x

## Deployment Steps

1. Clone the application
`git clone https://github.com/anand449/assignment.git`

2. Create Mysql database
`create database test_db`

3. Change mysql username and password as per your installation:
    open `src/main/resources/application.properties` and change `spring.datasource.username` and `spring.datasource.password` as per your mysql installation

4. Build and run the app using maven
`mvn package`
`java -jar target/assignment-1.jar`

5. Alternatively, you can run the app without packaging it using:
`mvn spring-boot:run`

6. The app will start running at http://localhost:8080

## RESTful APIs

The app defines following Realm APIs.

- GET /service/user/realm/{id}
- POST /service/user/realm

You can test them using Postman or any other REST Client.