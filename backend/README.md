# EMPLOYEES SERVICE

This is a simple Spring Boot microservice to manage employees and his departments of a company.

## Tools and Technologies
- Java 21
- Spring Boot 3.5.0
- Spring Data JPA
- H2 Database
- Lombok
- Spring Boot DevTools
- Spring Boot Test
- Spring Boot Starter Web

## Features
- Create, Read, Update, Delete (CRUD) operations for employees and departments.
- Unit and integration tests using Spring Boot Test.
- In-memory H2 database for quick setup and testing.
- Docker support for containerization.

## How to Run

### Using Docker

1. Ensure you have Docker installed and running.
2. Build the Docker image:
   ```bash
   docker build -t employees-service .
   ```
   
3. Run the Docker container:
   ```bash
    docker run -p 8080:8080 employees-service
    ```
   
4. Access the application at `http://localhost:8080/api/v1`.

5. Access the H2 console at `http://localhost:8080/api/v1/h2-console` with the following credentials:
   - **JDBC URL**: `jdbc:h2:mem:employees_db;DB_CLOSE_DELAY=-1;DB_CLOSE_ON_EXIT=FALSE`
   - **Username**: `sa`
   - **Password**: (leave blank)
