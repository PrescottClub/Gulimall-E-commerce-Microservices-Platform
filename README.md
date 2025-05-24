# Gulimall E-commerce Microservices Platform

[![Java](https://img.shields.io/badge/Java-8+-orange.svg)](https://www.oracle.com/java/)
[![Spring Boot](https://img.shields.io/badge/Spring%20Boot-2.3.2-brightgreen.svg)](https://spring.io/projects/spring-boot)
[![Spring Cloud](https://img.shields.io/badge/Spring%20Cloud-Hoxton.SR8-blue.svg)](https://spring.io/projects/spring-cloud)
[![License](https://img.shields.io/badge/License-Apache%202.0-blue.svg)](LICENSE)

A comprehensive e-commerce microservices platform built with Spring Cloud ecosystem, featuring distributed architecture, high concurrency handling, and modern development practices.

**Author:** Terence Qin  
**Project Type:** Microservices E-commerce Platform  
**Architecture:** Distributed Microservices

## 🏗️ Project Architecture

### Microservices Overview
- **gulimall-product** - Product catalog management service
- **gulimall-order** - Order processing and management service  
- **gulimall-member** - User membership and authentication service
- **gulimall-cart** - Shopping cart service
- **gulimall-coupon** - Coupon and promotion service
- **gulimall-ware** - Warehouse and inventory management service
- **gulimall-search** - Search and indexing service (Elasticsearch)
- **gulimall-seckill** - Flash sale and high-concurrency service
- **gulimall-gateway** - API Gateway and routing service
- **gulimall-auth-server** - Authentication and authorization service
- **gulimall-third-party** - Third-party service integration
- **gulimall-common** - Shared utilities and common dependencies

### Supporting Services
- **renren-fast** - Management backend service
- **renren-generator** - Code generation tool
- **Admin Vue App** - Management frontend application

## 🛠️ Technology Stack

### Core Framework
- **Spring Boot 2.3.2** - Application framework
- **Spring Cloud Hoxton.SR8** - Microservices framework
- **Spring Cloud Alibaba 2.2.1** - Alibaba cloud native components

### Microservices Components
- **Nacos** - Service discovery and configuration management
- **Gateway** - API gateway and load balancing
- **OpenFeign** - Declarative REST client
- **Sentinel** - Circuit breaker and flow control
- **Seata** - Distributed transaction management

### Data & Storage
- **MySQL 8.0** - Primary database
- **MyBatis-Plus 3.4.0** - ORM framework
- **Redis** - Caching and session storage
- **Elasticsearch** - Search engine
- **RabbitMQ** - Message queue

### DevOps & Tools
- **Docker** - Containerization
- **Maven** - Dependency management
- **Git** - Version control

## 🚀 Quick Start

### Prerequisites
- **Java 8+** 
- **Maven 3.6+**
- **MySQL 8.0+**
- **Redis 5.0+**
- **Nacos 2.0+**
- **Elasticsearch 7.x**
- **RabbitMQ 3.8+**

### Environment Setup

#### **Step 1: Clone and Setup Repository**
```bash
git clone https://github.com/PrescottClub/gilimall.git
cd gulimall-master
```

#### **Step 2: Database Setup**
1. **Install MySQL 8.0+** and create databases:
   ```sql
   CREATE DATABASE gulimall_admin;
   CREATE DATABASE gulimall_pms;
   CREATE DATABASE gulimall_oms;
   CREATE DATABASE gulimall_ums;
   CREATE DATABASE gulimall_sms;
   CREATE DATABASE gulimall_wms;
   ```

2. **Import SQL files** from `/sql/` directory in order:
   ```bash
   mysql -u root -p gulimall_admin < sql/gulimall_admin.sql
   mysql -u root -p gulimall_pms < sql/gulimall_pms.sql
   mysql -u root -p gulimall_oms < sql/gulimall_oms.sql
   mysql -u root -p gulimall_ums < sql/gulimall_ums.sql
   mysql -u root -p gulimall_sms < sql/gulimall_sms.sql
   mysql -u root -p gulimall_wms < sql/gulimall_wms.sql
   ```

#### **Step 3: Infrastructure Services Setup**

1. **Install and Start Nacos 2.0+**
   ```bash
   # Download Nacos from https://nacos.io/
   cd nacos/bin
   # Windows
   startup.cmd -m standalone
   # Linux/Mac
   ./startup.sh -m standalone
   ```
   - Access: http://localhost:8848/nacos (admin/nacos)

2. **Install and Start Redis 5.0+**
   ```bash
   # Windows (using Redis for Windows)
   redis-server.exe
   # Linux/Mac
   redis-server
   ```
   - Default port: 6379

3. **Install and Start RabbitMQ 3.8+**
   ```bash
   # Enable management plugin
   rabbitmq-plugins enable rabbitmq_management
   # Start service
   rabbitmq-server
   ```
   - Management UI: http://localhost:15672 (guest/guest)

4. **Install and Start Elasticsearch 7.x**
   ```bash
   # Download from https://www.elastic.co/downloads/elasticsearch
   ./bin/elasticsearch
   ```
   - API endpoint: http://localhost:9200

#### **Step 4: Configuration Setup**

1. **Update application.yml files** in each microservice module:
   
   **Replace the IP address `192.168.56.10` with your actual IP or localhost:**
   ```yaml
   # Change this line in all application.yml files
   ipAddr: localhost  # or your actual IP address
   
   spring:
     datasource:
       username: root
       password: your_mysql_password  # Change this
       url: jdbc:mysql://localhost:3306/gulimall_xxx
   ```

2. **Update Nacos addresses** - ensure consistency:
   ```yaml
   spring:
     cloud:
       nacos:
         discovery:
           server-addr: localhost:8848  # Use consistent address
   ```

3. **Configure Redis and RabbitMQ**:
   ```yaml
   spring:
     redis:
       host: localhost
     rabbitmq:
       host: localhost
   ```

#### **Step 5: Version Compatibility Check**
- **Java**: JDK 8 or 11
- **Maven**: 3.6+
- **Spring Boot**: 2.3.2.RELEASE
- **Spring Cloud**: Hoxton.SR8
- **MySQL**: 8.0+
- **Redis**: 5.0+
- **Nacos**: 2.0+
- **RabbitMQ**: 3.8+
- **Elasticsearch**: 7.x

### Running the Application

#### **Step 6: Build and Start Services**

1. **Build the project**:
   ```bash
   mvn clean compile -DskipTests
   ```

2. **Start services in the following order**:

   **First - Infrastructure:**
   ```bash
   # Start Gateway Service (Port: 88)
   cd gulimall-gateway
   mvn spring-boot:run
   ```

   **Second - Core Services:**
   ```bash
   # Start Product Service (Port: 10002)
   cd gulimall-product  
   mvn spring-boot:run
   
   # Start Member Service (Port: 8000)
   cd gulimall-member
   mvn spring-boot:run
   
   # Start Coupon Service (Port: 7000)
   cd gulimall-coupon
   mvn spring-boot:run
   ```

   **Third - Business Services:**
   ```bash
   # Start Order Service (Port: 9001)
   cd gulimall-order
   mvn spring-boot:run
   
   # Start Warehouse Service (Port: 11000)
   cd gulimall-ware
   mvn spring-boot:run
   
   # Start Search Service (Port: 12000)
   cd gulimall-search
   mvn spring-boot:run
   ```

3. **Access the Application**:
   - **API Gateway**: http://localhost:88
   - **Admin Panel**: http://localhost:8080 (renren-fast)
   - **Nacos Console**: http://localhost:8848/nacos (admin/nacos)
   - **RabbitMQ Management**: http://localhost:15672 (guest/guest)
   - **Elasticsearch**: http://localhost:9200

#### **Step 7: Troubleshooting Common Issues**

1. **Port Conflicts**: Ensure ports 88, 7000, 8000, 9001, 10002, 11000, 12000 are available
2. **Database Connection**: Verify MySQL is running and credentials are correct
3. **Nacos Connection**: Check if Nacos is accessible at configured address
4. **Memory Issues**: Increase JVM heap size if needed: `-Xmx2g -Xms1g`
5. **Logs**: Check application logs in each service for detailed error messages

### Service Ports
- **gulimall-gateway**: 88
- **gulimall-product**: 10002  
- **gulimall-member**: 8000
- **gulimall-order**: 9001
- **gulimall-coupon**: 7000
- **gulimall-ware**: 11000
- **gulimall-search**: 12000
- **gulimall-seckill**: 25000
- **gulimall-auth-server**: 20000
- **gulimall-third-party**: 30000

## 📋 Features

### Core E-commerce Features
- **Product Management** - Catalog, categories, brands, SKUs
- **Order Processing** - Cart, checkout, payment integration
- **User Management** - Registration, authentication, profiles
- **Inventory Management** - Stock tracking, warehouse management
- **Promotion System** - Coupons, discounts, flash sales
- **Search & Discovery** - Elasticsearch-powered search

### Technical Features
- **Microservices Architecture** - Loosely coupled, independently deployable
- **Distributed Session** - Redis-based session management
- **Circuit Breaker** - Sentinel for fault tolerance
- **Message Queue** - Asynchronous processing with RabbitMQ
- **Distributed Caching** - Redis integration
- **API Gateway** - Centralized routing and security
- **Service Discovery** - Nacos-based service registration

## 🔧 Configuration

### Environment Variables
Create environment-specific configuration files:
- `application-dev.yml` - Development environment
- `application-test.yml` - Testing environment  
- `application-prod.yml` - Production environment

### Key Configuration Points
- Database connections in each service
- Nacos server addresses
- Redis configuration
- RabbitMQ settings
- Elasticsearch cluster configuration

## 📖 API Documentation

### REST Endpoints
- **Product Service**: `/api/product/**`
- **Order Service**: `/api/order/**`
- **Member Service**: `/api/member/**`
- **Cart Service**: `/api/cart/**`

### Service Communication
Services communicate via:
- **HTTP/REST** - External API calls
- **OpenFeign** - Internal service-to-service calls
- **RabbitMQ** - Asynchronous messaging

## 🧪 Testing

```bash
# Run unit tests
mvn test

# Run integration tests
mvn verify

# Run specific service tests
cd gulimall-product
mvn test
```

## 📦 Deployment

### Docker Deployment
```bash
# Build Docker images
mvn clean package -DskipTests
docker build -t gulimall-product:latest ./gulimall-product/

# Run with Docker Compose
docker-compose up -d
```

### Production Deployment
1. Package applications: `mvn clean package`
2. Deploy to application servers
3. Configure load balancers
4. Set up monitoring and logging

## 🔍 Monitoring & Logging

- **Service Health**: Spring Boot Actuator endpoints
- **Metrics**: Micrometer integration
- **Distributed Tracing**: Sleuth integration
- **Centralized Logging**: ELK Stack compatible

## 📝 Development Guide

### Code Structure
```
gulimall-{service}/
├── src/main/java/com/terenceqin/gulimall/{service}/
│   ├── controller/     # REST controllers
│   ├── service/        # Business logic
│   ├── dao/           # Data access layer
│   ├── entity/        # JPA entities
│   ├── vo/            # Value objects
│   ├── config/        # Configuration classes
│   └── feign/         # Feign clients
├── src/main/resources/
│   ├── mapper/        # MyBatis XML mappers
│   ├── application.yml # Service configuration
│   └── bootstrap.properties # Bootstrap config
└── pom.xml            # Maven dependencies
```

### Development Standards
- Follow RESTful API design principles
- Use proper HTTP status codes
- Implement comprehensive error handling
- Write unit tests for business logic
- Document public APIs

## 🤝 Contributing

1. Fork the repository
2. Create your feature branch (`git checkout -b feature/amazing-feature`)
3. Commit your changes (`git commit -m 'Add some amazing feature'`)
4. Push to the branch (`git push origin feature/amazing-feature`)
5. Open a Pull Request

## 📄 License

This project is licensed under the Apache License 2.0 - see the [LICENSE](LICENSE) file for details.

## 📞 Contact

**Terence Qin**  
Project Maintainer

---

## 🔗 Useful Links

- [Spring Cloud Documentation](https://spring.io/projects/spring-cloud)
- [Spring Cloud Alibaba](https://github.com/alibaba/spring-cloud-alibaba)
- [Nacos Documentation](https://nacos.io/en-us/docs/what-is-nacos.html)
- [Sentinel Documentation](https://sentinelguard.io/en-us/)

---

**⭐ If this project helps you, please give it a star!**
