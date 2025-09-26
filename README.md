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
