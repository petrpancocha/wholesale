# Spring app boot-up

mvnw spring-boot:run

# H2 DB Console

http://localhost:8080/h2-console

# JDBC URL

jdbc:h2:mem:testdb

# Rest API - Swagger documentation

http://localhost:8080/swagger-ui.html

# TODO

- improve Swagger documentation
- implement validation of requests' payloads and query parameters
- add exception handling i.e. mapping of java exceptions to http response codes
- add more junit tests for Rest API layer (controllers)
- setup specific data resource and data (data.sql) for junits