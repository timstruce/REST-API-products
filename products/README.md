# Simple REST API

## Summary
The Product REST API is a simple web application that manages a list of products, providing CRUD (Create, Read, Update, Delete) operations for products. 
Additionally, the USD price for each product is automatically calculated by calling the HNB API to fetch the latest exchange rate.


## Techology
- Language: Java 17
- Framework: Spring Boot
- Build tool: Maven
- Data persistence: Hibernate
- Database: H2 (update)

## Endpoints (and logic)
Endpoints are defined in openapi.yaml

## Documentation
You can build and run app with IDE (run as Java app) or you can do it in terminal.
First of all, clone repository from Git using:

```sh
$ git clone {url}
```

### Build and run app
The Spring Boot application is located in the products folder.
```sh
$ cd ./products
```

Clear the target directory (if needed), build the project described by Maven POM file and install the resulting artifact (JAR) into your local Maven repository.
```sh
$ mvn clean install
```

Run the Spring Boot Application 
```sh
$ mvn spring-boot:run
```


You can send requests to the REST service using Postman.
There is also Angular frontend application which can be used for sending requests to the REST service.



