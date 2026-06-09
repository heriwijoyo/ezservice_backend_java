# EzClouds Election Management System

A high-concurrency, enterprise-grade election management and real-time data aggregation platform. Designed from the ground up to handle voter canvassing, demographic reporting, and large-scale data synchronization in a distributed environment.

## 🏗️ Architectural Overview

This project is structured as a **Multi-Module Maven application**, utilizing a strict layered architecture (Clean Architecture/Hexagonal) to enforce separation of concerns and ensure long-term maintainability.

### System Flow
```mermaid
graph TD
    %% Define styles
    classDef client fill:#e1f5fe,stroke:#01579b,stroke-width:2px,color:#000;
    classDef gateway fill:#fff3e0,stroke:#e65100,stroke-width:2px,color:#000;
    classDef biz fill:#e8f5e9,stroke:#1b5e20,stroke-width:2px,color:#000;
    classDef dal fill:#fce4ec,stroke:#880e4f,stroke-width:2px,color:#000;
    classDef db fill:#ede7f6,stroke:#4a148c,stroke-width:2px,color:#000;
    classDef ext fill:#eceff1,stroke:#37474f,stroke-width:2px,color:#000;

    %% Nodes
    Client((Clients)):::client
    Web[Web Portal]:::client
    Bifrost{Bifrost Layer<br>Gateway / API / WebSockets}:::gateway
    Facade[Facade Contracts<br>Interfaces]:::biz
    CoreBiz[Core Business Engines<br>Async Processors]:::biz
    DAL[Data Access Layer<br>Repositories]:::dal
    MySQL[(MySQL Database)]:::db
    Watzap[External Service<br>WhatsApp API]:::ext
    Logger[Centralized Logs<br>DigestLog]:::ext

    %% Connections
    Client -.->|HTTP Request| Bifrost
    Web -.->|HTTP Request| Bifrost
    Bifrost -->|Maps to DO| Facade
    Facade -->|Executes| CoreBiz
    CoreBiz -->|Converts DO to Entity| DAL
    DAL -->|JPA PESSIMISTIC_WRITE| MySQL

    %% Side effects
    CoreBiz -.->|Reactive WebClient| Watzap
    Bifrost -.->|Trace ID| Logger
    CoreBiz -.->|Audit / Metrics| Logger
```

### Module Breakdown
- `bifrost` **(Gateway Layer):** Acts as the primary entry point for HTTP and WebSocket traffic. It isolates the web-framework logic from the core business domain.
- `core` **&** `app` **(Domain Layer):** Contains the business engines, including complex data aggregation, async ThreadPool processing, and integration logic.
- `dal` **(Data Access Layer):** An abstraction layer that strictly decouples JPA/Hibernate entities from the business services using Data Objects (DO).
- `common` **(Infrastructure/Cross-cutting):** Centralized utilities, shared models, thread-local context holders, and custom operational logging.

## ⚙️ Key Technical Decisions
- **Concurrency & Consistency over Throughput:** Implemented `Pessimistic Locking` (via JPA `@Lock(LockModeType.PESSIMISTIC_WRITE)`) on report accumulation processes to prevent "Lost Update" anomalies during high-frequency vote canvassing.
- **Structured Operational Logging:** Developed a custom `DigestLog` utility to capture structured metrics (TraceID, TimeCost, SuccessFlag) for every request. This enables deep observability and request-tracing across modules without the need to parse raw text.
- **Strategy Pattern for Async Processing:** Utilized a `Map<ProcessName, BizAsyncProcessor>` dynamically injected by Spring to route asynchronous tasks, avoiding monolithic switch-case logic and ensuring clean extensibility.
- **Memory & Connection Pool Optimization:** Set `spring.jpa.open-in-view=false` to enforce efficient session management and strictly isolate database transactions from view rendering.

## 🛠️ Technology Stack
- **Core Framework:** Java 8+, Spring Boot 2.x
- **Database:** MySQL (Optimized with Hibernate/JPA)
- **Integration:** Spring WebFlux (Reactive `WebClient`) for 3rd-party communications
- **Build System:** Maven (Multi-Module)
- **Architecture:** Domain-Driven Design (DDD) principles, Clean Architecture

## 🚀 Deployment & Operations
The system is designed to be operationally resilient in resource-constrained environments. It utilizes a custom, script-based orchestrator (`run.sh`, `start.sh`, `stop.sh`) to manage the JVM lifecycle, handle environment variable injection, manage PID tracking, and execute automated artifact backups during deployments.

## 🗺️ Roadmap & Future Improvements
While the core system is fully functional and designed to serve production traffic, future iterations will focus on scaling the operational infrastructure:
1. **Integration Testing:** Implementing containerized testing suites using Testcontainers to validate database transactions and repository layer locking mechanisms.
2. **Resilience Engineering:** Integrating **Circuit Breaker** patterns (via Resilience4j) into the external communication services to gracefully handle API timeouts.
3. **CI/CD Pipeline:** Migrating the manual shell-script deployment process to GitHub Actions for automated delivery.

Created and maintained independently by Heri Wijoyo
