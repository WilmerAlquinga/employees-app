# App for Employees and Departments management

## Features

- Create, Read, Update, Delete employees
- Create, Read, Update, Delete departments
- Assign employees to departments
- View employees by department
- Search employees by filters

## Tools and Technologies

### Backend

- Java 21
- Spring Boot 3.5
- H2 Database

### Frontend

- NodeJS 23
- Angular 21
- TypeScript

## How to run

### Docker

Run the following command to build and run the Docker containers:

```bash
  docker-compose up --build
```

- Open your web browser and navigate to `http://localhost:4200` to access the frontend application.
- The backend API will be available at `http://localhost:8080`.
- You can access the API documentation at `http://localhost:8080/swagger-ui/index.html`.

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
3. The backend server will start on `http://localhost:8080`.
4. You can access the API documentation at `http://localhost:8080/swagger-ui/index.html`.

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
