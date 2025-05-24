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

1. **Clone the repository**
   ```bash
   git clone [repository-url]
   cd gulimall-master
   ```

2. **Database Setup**
   - Create databases: `gulimall_pms`, `gulimall_oms`, `gulimall_ums`, `gulimall_sms`, `gulimall_wms`
   - Import SQL files from `/sql文件/` directory
   - Update database connection settings in each service's `application.yml`

3. **Infrastructure Services**
   ```bash
   # Start Nacos (default port 8848)
   cd nacos/bin
   ./startup.sh -m standalone
   
   # Start Redis (default port 6379)
   redis-server
   
   # Start RabbitMQ (default port 5672)
   rabbitmq-server
   
   # Start Elasticsearch (default port 9200)
   ./elasticsearch
   ```

4. **Configuration**
   - Configure Nacos server addresses in `bootstrap.properties` files
   - Update IP addresses in configuration files (currently set to 192.168.56.10)
   - Configure Redis, MySQL, and RabbitMQ connection parameters

### Running the Application

1. **Start Core Services (in order)**
   ```bash
   # 1. Start Gateway Service
   cd gulimall-gateway
   mvn spring-boot:run
   
   # 2. Start Product Service  
   cd gulimall-product
   mvn spring-boot:run
   
   # 3. Start Member Service
   cd gulimall-member
   mvn spring-boot:run
   
   # 4. Start Other Services
   cd gulimall-order && mvn spring-boot:run &
   cd gulimall-coupon && mvn spring-boot:run &
   cd gulimall-ware && mvn spring-boot:run &
   ```

2. **Access the Application**
   - **Frontend**: http://localhost:10000
   - **Admin Panel**: http://localhost:8080
   - **API Gateway**: http://localhost:88
   - **Nacos Console**: http://localhost:8848/nacos

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
