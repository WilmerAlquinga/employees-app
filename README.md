# App for Employees and Departments management

## Features

- Create, Read, Delete employees
- Create, Read, Delete departments
- Assign employees to departments
- Search employees by filters

## Tools and Technologies

### Backend

- Java 21
- Spring Boot 3.5
- H2 Database

### Frontend

- NodeJS 22
- Angular 19
- TypeScript

## How to run

### Docker

Run the following command to build and run the Docker containers:

```bash
  docker-compose up -d --build
```

- Open your web browser and navigate to `http://localhost:4200` to access the frontend application.
- The backend API will be available at `http://localhost:8080/api/v1`.
- You can access to the H2 console at `http://localhost:8080/api/v1/h2-console` with the following credentials:
   - **JDBC URL**: `jdbc:h2:mem:employees_db;DB_CLOSE_DELAY=-1;DB_CLOSE_ON_EXIT=FALSE`
   - **Username**: `sa`
   - **Password**: (leave blank)

### Local Development

#### Backend

1. Navigate to the `backend` directory with the following command:
   ```bash
   cd backend
   ```
2. Run the following command to execute the application:
   ```bash
   ./gradlew bootRun
   ```
3. The backend server will start on `http://localhost:8080/api/v1`.
4. You can access the H2 console at `http://localhost:8080/api/v1/h2-console` with the following credentials:
   - **JDBC URL**: `jdbc:h2:mem:employees_db;DB_CLOSE_DELAY=-1;DB_CLOSE_ON_EXIT=FALSE`
   - **Username**: `sa`
   - **Password**: (leave blank)

#### Frontend

1. Navigate to the `frontend` directory with the following command:
   ```bash
   cd frontend
   ```
2. Run the following command to install dependencies:
   ```bash
   npm install
   ```
3. Run the following command to start the frontend application:
   ```bash
    ng serve
    ```
4. The frontend application will be available at `http://localhost:4200`.
