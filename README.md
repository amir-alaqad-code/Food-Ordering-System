# Food Ordering Microservices System

This project is a simple microservices-based system built using Spring Boot.

## Services

### 1. Payment Service
- Runs on port 8080
- Endpoint:
  POST /api/payments/process
- Example:
  curl -X POST http://localhost:8080/api/payments/process -H "Content-Type: text/plain" -d "order123"

---

### 2. Order Service
- Runs on port 8081
- Endpoint:
  POST /api/orders/create
- Calls Payment Service internally

---

## How to Run
cd order-service/order-service
mvnw.cmd clean package
java -jar target/order-service-0.0.1-SNAPSHOT.jar --server.port=8081

--- 

## Testing 
curl -X POST http://localhost:8081/api/orders/create -H "Content-Type: text/plain" -d "order123"


