# Spring app boot-up

mvnw spring-boot:run

# H2 DB Console

http://localhost:8080/h2-console

# JDBC URL

jdbc:h2:mem:testdb

# Rest API

## TODO: document API using Swagger

### Users API

GET: http://localhost:8080/users

GET: http://localhost:8080/users/{id}

### Tasks API

GET: http://localhost:8080/tasks

GET: http://localhost:8080/tasks/{id}

GET: http://localhost:8080/tasks-by-query?acquiredBy={value}
- condition: acquiredId equals value

GET: http://localhost:8080/tasks-by-query?userNote={value} (like)
- condition: userNote contains value

POST: http://localhost:8080/tasks
- payload:
- {
  "userNote" : "value",
  "taskData" : "value",
  "acquiredBy" : id,
  "createdBy" : id
  }

PUT: http://localhost:8080/tasks/{id}
- payload:
- {
  "userNote" : "value",
  "taskData" : "value",
  "acquiredBy" : id
  }


DELETE: http://localhost:8080/tasks/{id}