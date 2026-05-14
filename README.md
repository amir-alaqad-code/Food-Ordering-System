# Online Food Ordering System

**Advanced Software Engineering Running Project**  
**Course:** Advanced Software Engineering  
**Course Code:** SDEV 4304  
**Student Name:** AMIR N. H. ALAQAD  
**Student ID:** 120221924  
**Instructor / Supervisor:** Dr. Abdelkareem Alashqar  

---

## 1. Project Overview

The **Online Food Ordering System** is a microservices-based application developed for the Advanced Software Engineering running project.

The system represents a simple food ordering workflow where a customer places an order, the payment is processed, the restaurant accepts the order, a delivery driver is assigned, and the customer receives a notification.

The project is designed using **Microservices Architecture**, where each service has a clear business responsibility and can be built, tested, and run independently.

In the current implementation, the Order Service coordinates the main order workflow by calling the Payment Service, Restaurant Service, Delivery Service, and Notification Service using REST APIs.

---

## 2. Main Workflow

The main workflow of the system is:

```text
Customer places order
        ↓
Order Service creates the order
        ↓
Payment Service confirms the payment
        ↓
Restaurant Service accepts the order
        ↓
Delivery Service assigns a driver
        ↓
Notification Service sends confirmation to the customer
```

This workflow is suitable for microservices because it includes multiple independent business steps. Each step can be handled by a separate service.

---

## 3. Microservices

The project contains five microservices:

| Microservice | Responsibility | Port |
|---|---|---|
| Order Service | Creates orders and coordinates the main workflow with other services | 8080 |
| Payment Service | Processes payment confirmation | 8081 |
| Restaurant Service | Accepts restaurant orders | 8082 |
| Delivery Service | Assigns a driver for the order | 8083 |
| Notification Service | Sends notification messages to the customer | 8084 |

---

## 4. Project Structure

The project is organized as a **GitHub Monorepo**.  
Each microservice is placed in a separate folder.

```text
Food-Ordering-System/
│
├── order-service/
│   ├── src/
│   ├── pom.xml
│   ├── Dockerfile
│   └── mvnw.cmd
│
├── payment-service/
│   ├── src/
│   ├── pom.xml
│   └── mvnw.cmd
│
├── restaurant-service/
│   ├── src/
│   ├── pom.xml
│   └── mvnw.cmd
│
├── delivery-service/
│   ├── src/
│   ├── pom.xml
│   └── mvnw.cmd
│
├── notification-service/
│   ├── src/
│   ├── pom.xml
│   └── mvnw.cmd
│
├── .github/
│   └── workflows/
│       └── order-service-ci.yml
│
└── README.md
```

---

## 5. Technologies Used

| Technology | Purpose |
|---|---|
| Java 17 | Main programming language |
| Spring Boot | Building REST-based microservices |
| Maven | Build and dependency management |
| REST API | Communication between services |
| Docker Desktop | Containerizing Order Service |
| GitHub | Source code hosting |
| GitHub Actions | CI/CD pipeline for Order Service |
| Postman | API testing |

---

## 6. Services and Endpoints

### 6.1 Order Service

**Responsibility:**  
The Order Service creates customer orders and coordinates the main workflow by calling the Payment Service, Restaurant Service, Delivery Service, and Notification Service.

**Port:**  
```text
8080
```

**Endpoint:**  
```text
POST /api/orders/create
```

**Example URL:**  
```text
http://localhost:8080/api/orders/create
```

**Example Body:**  
```text
Order for customer 1 from restaurant 1 with total amount 50
```

**Expected Response:**  
```text
Order created successfully.
Payment completed successfully for order: Order for customer 1 from restaurant 1 with total amount 50
Restaurant accepted the order: Order for customer 1 from restaurant 1 with total amount 50
Driver assigned for the order: Order for customer 1 from restaurant 1 with total amount 50
Notification sent to customer: Your order has been confirmed: Order for customer 1 from restaurant 1 with total amount 50
```

---

### 6.2 Payment Service

**Responsibility:**  
The Payment Service processes payment requests and returns payment confirmation.

**Port:**  
```text
8081
```

**Endpoint:**  
```text
POST /api/payments/process
```

**Example URL:**  
```text
http://localhost:8081/api/payments/process
```

**Example Body:**  
```text
Order for customer 1 from restaurant 1 with total amount 50
```

**Expected Response:**  
```text
Payment completed successfully for order: Order for customer 1 from restaurant 1 with total amount 50
```

---

### 6.3 Restaurant Service

**Responsibility:**  
The Restaurant Service handles restaurant approval for the order.

**Port:**  
```text
8082
```

**Endpoint:**  
```text
POST /api/restaurants/accept
```

**Example URL:**  
```text
http://localhost:8082/api/restaurants/accept
```

**Example Body:**  
```text
Order 5001 from customer 1
```

**Expected Response:**  
```text
Restaurant accepted the order: Order 5001 from customer 1
```

---

### 6.4 Delivery Service

**Responsibility:**  
The Delivery Service assigns a driver for the order.

**Port:**  
```text
8083
```

**Endpoint:**  
```text
POST /api/deliveries/assign
```

**Example URL:**  
```text
http://localhost:8083/api/deliveries/assign
```

**Example Body:**  
```text
Order 5001 from restaurant 1
```

**Expected Response:**  
```text
Driver assigned for the order: Order 5001 from restaurant 1
```

---

### 6.5 Notification Service

**Responsibility:**  
The Notification Service sends notification messages to the customer.

**Port:**  
```text
8084
```

**Endpoint:**  
```text
POST /api/notifications/send
```

**Example URL:**  
```text
http://localhost:8084/api/notifications/send
```

**Example Body:**  
```text
Your order has been confirmed
```

**Expected Response:**  
```text
Notification sent to customer: Your order has been confirmed
```

---

## 7. Service Communication

The project uses **REST API communication** between microservices.

In the current implementation, the **Order Service** acts as the main workflow coordinator. It receives the order request and then calls the **Payment Service**, **Restaurant Service**, **Delivery Service**, and **Notification Service** in sequence using `RestTemplate`.

The implemented workflow is:

```text
Order Service
        ↓
Payment Service
        ↓
Restaurant Service
        ↓
Delivery Service
        ↓
Notification Service
```

Because the Order Service is running inside Docker and the other services are running locally on the host machine, the Order Service uses `host.docker.internal` to access them.

```text
http://host.docker.internal:8081/api/payments/process
http://host.docker.internal:8082/api/restaurants/accept
http://host.docker.internal:8083/api/deliveries/assign
http://host.docker.internal:8084/api/notifications/send
```

This allows the Docker container to access the services running on the laptop.

---

## 8. Docker Deployment

The **Order Service** is containerized using Docker Desktop.

The Dockerfile is located inside:

```text
order-service/Dockerfile
```

### Dockerfile

```dockerfile
FROM eclipse-temurin:17-jdk

WORKDIR /app

COPY target/order-service-0.0.1-SNAPSHOT.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]
```

### Build Order Service JAR

```bash
cd order-service
mvnw.cmd package
```

### Build Docker Image

```bash
docker build -t order-service .
```

### Run Docker Container

```bash
docker run -p 8080:8080 --name order-service-container order-service
```

### Stop Docker Container

```bash
docker stop order-service-container
```

### Remove Docker Container

```bash
docker rm order-service-container
```

---

## 9. CI/CD Using GitHub Actions

CI/CD is applied to the **Order Service** using GitHub Actions.

The workflow file is located at:

```text
.github/workflows/order-service-ci.yml
```

The pipeline performs the following steps:

1. Checkout the repository.
2. Set up Java 17.
3. Build the Order Service using Maven.
4. Generate the JAR artifact.
5. Upload the generated artifact.

This helps ensure that the Order Service can be built automatically after pushing changes to GitHub.

---

## 10. Running the Project Locally

Before running the services, make sure the following tools are installed:

- Java 17
- Maven Wrapper included in each service
- Docker Desktop
- Postman or any API testing tool

---

### 10.1 Run Payment Service

```bash
cd payment-service
java -jar target\payment-service-0.0.1-SNAPSHOT.jar
```

The service runs on:

```text
http://localhost:8081
```

---

### 10.2 Run Restaurant Service

```bash
cd restaurant-service
java -jar target\restaurant-service-0.0.1-SNAPSHOT.jar
```

The service runs on:

```text
http://localhost:8082
```

---

### 10.3 Run Delivery Service

```bash
cd delivery-service
java -jar target\delivery-service-0.0.1-SNAPSHOT.jar
```

The service runs on:

```text
http://localhost:8083
```

---

### 10.4 Run Notification Service

```bash
cd notification-service
java -jar target\notification-service-0.0.1-SNAPSHOT.jar
```

The service runs on:

```text
http://localhost:8084
```

---

### 10.5 Run Order Service Using Docker

```bash
cd order-service
docker build -t order-service .
docker run -p 8080:8080 --name order-service-container order-service
```

The service runs on:

```text
http://localhost:8080
```

---

## 11. Build Commands

Each microservice can be built independently.

### Order Service

```bash
cd order-service
mvnw.cmd package
```

### Payment Service

```bash
cd payment-service
mvnw.cmd package
```

### Restaurant Service

```bash
cd restaurant-service
mvnw.cmd package
```

### Delivery Service

```bash
cd delivery-service
mvnw.cmd package
```

### Notification Service

```bash
cd notification-service
mvnw.cmd package
```

---

## 12. Testing Example Using Postman

### Test Full Order Workflow

Before testing, make sure:

1. Docker Desktop is running.
2. Payment Service is running on port `8081`.
3. Restaurant Service is running on port `8082`.
4. Delivery Service is running on port `8083`.
5. Notification Service is running on port `8084`.
6. Order Service container is running on port `8080`.

Send this request using Postman:

```text
POST http://localhost:8080/api/orders/create
```

Body type:

```text
raw → Text
```

Body:

```text
Order for customer 1 from restaurant 1 with total amount 50
```

Expected response:

```text
Order created successfully.
Payment completed successfully for order: Order for customer 1 from restaurant 1 with total amount 50
Restaurant accepted the order: Order for customer 1 from restaurant 1 with total amount 50
Driver assigned for the order: Order for customer 1 from restaurant 1 with total amount 50
Notification sent to customer: Your order has been confirmed: Order for customer 1 from restaurant 1 with total amount 50
```

---

## 13. Microservices Concepts Applied

This project applies several concepts from the Advanced Software Engineering course.

### Independent Services

Each microservice has its own folder, source code, configuration file, port, and build process.

### Low Coupling

Services communicate through REST APIs instead of directly accessing each other’s internal code.

### High Cohesion

Each service focuses on one clear business responsibility.

### Independent Build

Each microservice can be built independently using Maven.

### Docker Deployment

The Order Service is deployed as a Docker container using Docker Desktop.

### CI/CD

GitHub Actions is used to automate the build process for the Order Service.

---

## 14. Notes

This project is prepared as the final running project for the **Advanced Software Engineering** course.

It demonstrates:

- Microservices architecture
- Spring Boot implementation
- REST communication between services
- Independent service structure
- Docker containerization
- GitHub Monorepo
- CI/CD using GitHub Actions

The implementation is simple, but it clearly represents the main concepts required in the running project.
