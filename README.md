# 🍔 Food Ordering Microservices System

A simple microservices-based application built using **Spring Boot** to simulate a food ordering workflow.  
The system consists of two independent services that communicate with each other.

---

## 🧩 Architecture Overview

The system is divided into two main microservices:

### 🔹 1. Payment Service
- Handles payment processing.
- Runs independently.

### 🔹 2. Order Service
- Handles order creation.
- Communicates with Payment Service to complete the payment process.

---

## ⚙️ Technologies Used

- Java 17
- Spring Boot
- Maven
- REST APIs

---

## 🚀 Services Details

### 📌 Payment Service
- **Port:** 8080
- **Endpoint:**
  ```
  POST /api/payments/process
  ```
- **Description:**
  Processes a payment request and returns a confirmation message.

- **Example Request:**
  ```bash
  curl -X POST http://localhost:8080/api/payments/process \
  -H "Content-Type: text/plain" \
  -d "order123"
  ```

---

### 📌 Order Service
- **Port:** 8081
- **Endpoint:**
  ```
  POST /api/orders/create
  ```
- **Description:**
  Creates an order and calls the Payment Service to process the payment.

- **Example Request:**
  ```bash
  curl -X POST http://localhost:8081/api/orders/create \
  -H "Content-Type: text/plain" \
  -d "order123"
  ```

---

## ▶️ How to Run the Project

### 1️⃣ Run Payment Service

```bash
cd payment-service/payment-service
mvnw.cmd clean package
java -jar target/payment-service-0.0.1-SNAPSHOT.jar
```

---

### 2️⃣ Run Order Service

```bash
cd order-service/order-service
mvnw.cmd clean package
java -jar target/order-service-0.0.1-SNAPSHOT.jar --server.port=8081
```

---

## 🧪 Testing the System

After running both services:

```bash
curl -X POST http://localhost:8081/api/orders/create \
-H "Content-Type: text/plain" \
-d "order123"
```

---

## ✅ Expected Output

```
Order created successfully. Payment completed successfully for order: order123
```

---

## 🔄 Service Communication Flow

1. Client sends request to Order Service  
2. Order Service processes the request  
3. Order Service calls Payment Service  
4. Payment Service returns response  
5. Order Service returns final result to client  

---

## 📌 Notes

- Both services must be running simultaneously.
- Payment Service should start before Order Service.
- Ensure ports (8080, 8081) are available.

---

## 👨‍💻 Author

**Amir Al-Aqad**  
Microservices Project – Advanced Software Engineering
