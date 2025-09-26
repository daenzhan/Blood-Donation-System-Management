# Blood Donation Management System (Microservices)

## 📌Description
This project implements a **blood donation management system** using a **microservices architecture**.  
The system includes several services:
- **Eureka Server** – service registry and discovery.
- **API Gateway** – single entry point, routing requests to the proper services.
- **User Service** – manages users (donors).
- **Medical Center Service** – manages medical centers.
- **Request Service** – manages requests from medical centers (centers can see all requests, but only edit or delete their own).

## 🛠 Tech Stack
- **Java 17+**
- **Spring Boot**
- **Spring Cloud (Eureka, Gateway)**
- **Spring Data JPA**
- **PostgreSQL**
- **Maven**
- **Postman** (for API testing)
- 
## Running
1. Start **Eureka Server** (`localhost:8761`)  
2. Start **API Gateway** (`localhost:8080`)  
3. Start **User Service** (`localhost:8081`)  
4. Start **MedCenter Service** (`localhost:8082`)  
5. Start **Request Service** (`localhost:8083`)

## API Endpoints (via API Gateway - http://localhost:8082/...)

### User Service `/users`
- `POST /users` – create user  
- `GET /users` – list users  
- `GET /users/{id}` – get user  
- `PUT /users/{id}` – update user  
- `DELETE /users/{id}` – delete user  

### MedCenter Service `/medcenters`
- `POST /med-centers` – create med center  
- `GET /med-centers` – list med centers  
- `GET /med-centers/{id}` – get med center  
- `PUT /med-centers/{id}` – update med center  
- `DELETE /med-centers/{id}` – delete med center  

### Request Service `/requests`
- `POST /requests` – create request  
- `GET /requests` – list requests  
- `GET /requests/{id}` – get request  
- `PUT /requests/{id}` – update request (owner only)  
- `DELETE /requests/{id}` – delete request (owner only)  

