# Jumbo Store Service

Technologies
------------
- `Spring Boot`
- `MySQL` 
- `Docker`
- `Docker-Compose`
- `Lombok`
- `Swagger`
- `JUnit`
- `CommandLineRunner`

## Run the System
We can easily run the whole with only a single command:

* `docker-compose up -d`

## Stop the System
Stopping all the running containers is also simple with a single command:

* `docker-compose down`

### Swagger

To view the generated Swagger UI documentation go to: [http://localhost:8080/swagger-ui/index.html#/](http://localhost:8080/swagger-ui/index.html#/)

- **Read Json File src/main/resources/json**

![Json Read](https://github.com/tugayesilyurt/jumbo-store-service/blob/main/assets/ReadJson.PNG)


### EndPoints ###

| Service      			| EndPoint                      | Method | Description                                      |
| --------------------- | ----------------------------- | :-----:| ------------------------------------------------ |
| jumbo-store-service   | /v1/store/nearest  			| GET    | Get nearest jumbo stores             	        |
| jumbo-store-service   | /v1/store    					| POST   | Save new Jumbo Store             	            |
| jumbo-store-service   | /v1/store/{id}   				| GET    | Get Jumbo Store             	                    |
| jumbo-store-service   | /v1/store/{id}   				| PUT    | Update Jumbo Store             	                |
| jumbo-store-service   | /v1/store/{id}      		    | DELETE | Delete Jumbo Store 	           	                |



Things to improve
------------
- `Spring AOP for logging`
- `Resilience4j - retry pattern - fallback method`
- `Jwt - authentication and authorization`
