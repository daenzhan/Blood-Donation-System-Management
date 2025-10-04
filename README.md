# Blood Donation Management System (Microservices)

## 📌Description
This project implements a **blood donation management system** using a **microservices architecture**.  
The system includes several services:
- **Eureka Server** – service registry and discovery.
- **API Gateway** – single entry point, routing requests to the proper services.
- **User Service** – manages users (donors).
- **Medical Center Service** – manages medical centers.
- **Blood Request Service** – manages medical centers.

## 🛠 Tech Stack
- **Java 17+**
- **Spring Boot**
- **Spring Cloud (Eureka, Gateway)**
- **Spring Data JPA**
- **PostgreSQL**
- **Postman** (for API testing)

  
## Running
1. Start **eureka-server** (`localhost:8761`)  
2. Start **user-service** (`localhost:8081`)  
3. Start **med-center-service** (`localhost:8082`)
4. Start **bloodrequest-service** (`localhost:8084`)
5. Start **api-gateway-service** (`localhost:8080`)

## API Endpoints (via API Gateway - http://localhost:8080/...)

### User Service `/users`
- `POST /users` – create user  
- `GET /users` – list users  
- `GET /users/{id}` – get user  
- `PUT /users/{id}` – update user  
- `DELETE /users/{id}` – delete user  

### MedCenter Service `/medcenters`
- `POST /medcenters` – create med center  
- `GET /medcenters` – list med centers  
- `GET /medcenters/{id}` – get med center  
- `PUT /medcenters/{id}` – update med center  
- `DELETE /medcenters/{id}` – delete med center  


### Bloodrequest Service `/requests`
- `POST /requests/save` – save requests
- `GET /requests/new` – show form for creating the request
- `GET /requests` – list requests 
- `GET /requests/{id}` – get requests by id
- `POS /requests/{id}/edit` – update requests
- `DELETE /requests/{id}` – delete requests


