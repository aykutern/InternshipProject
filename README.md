# InternshipProject — Backend API for Etiya Internship Portal

**A robust, enterprise-ready Spring Boot backend developed as an internship capstone project at Etiya to manage internship lifecycles, mentor-intern pairings, supervisor departments, task tracking, and training schedules.**

[![Java](https://img.shields.io/badge/Java-21-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white)](https://openjdk.org/)
[![Spring Boot](https://img.shields.io/badge/Spring_Boot-3.x-6DB33F?style=for-the-badge&logo=springboot&logoColor=white)](https://spring.io/projects/spring-boot)
[![PostgreSQL](https://img.shields.io/badge/PostgreSQL-16-4169E1?style=for-the-badge&logo=postgresql&logoColor=white)](https://www.postgresql.org/)
[![Security](https://img.shields.io/badge/OAuth2-MSAL_Integration-4F4F4F?style=for-the-badge&logo=microsoft&logoColor=white)](https://learn.microsoft.com/en-us/entra/identity-platform/)

---

## Table of Contents
- [Overview](#overview)
- [Key Features](#key-features)
- [Architecture](#architecture)
- [Database ER Model](#database-er-model)
- [API Endpoints](#api-endpoints)
- [Getting Started](#getting-started)
- [Configuration](#configuration)
- [Engineering Highlights](#engineering-highlights)

---

## Overview

The **InternshipProject Backend** serves as the central API service for the Etiya Internship Portal. Developed specifically to streamline and coordinate Etiya's structured internship training programs, it automates the management and interaction between HR department supervisors, technical mentors, and incoming interns.

The backend handles complex business logic including university collaborations, department hierarchies, daily/weekly task assignments, programmatic feedback, and Microsoft Entra ID (MSAL) authentication validation matching Etiya's active directory environments.

---

## Key Features

### Microsoft Entra ID (MSAL) Security
- Token validation pipeline matching enterprise identity platforms.
- Role-based API access control verifying claims (`MEMBER`, `MENTOR`, `ADMIN`).

### Multi-Role Resource Mapping
- **Interns**: Track academic details, university affiliations, office assignments, and active mentorship relationships.
- **Mentors**: Technical experts at Etiya overseeing day-to-day intern workflows.
- **Supervisors**: Management layer tracking department statistics, resource allocation, and overall program execution.

### Assignment & Report Workflow
- Task creation, status updates, and milestone tracking.
- Automated daily/weekly internship log submissions and reviews.

### Corporate Structure Modeling
- Office location maps and department hierarchy tracking.
- Prepopulated department metadata and FAQs.

---

## Architecture

```
Controller Layer (REST)  ---> Validation & Exception Handlers
       │
Service Layer (Abstract) ---> Core Business Logic & Transaction boundaries
       │
Repository Layer (JPA)   ---> Spring Data JPA & Hibernate Mappings
       │
Database (PostgreSQL)    ---> Persistent Relational Engine
```

---

## Database ER Model

```mermaid
erDiagram
    INTERN }o--|| UNIVERSITY : "enrolled at"
    INTERN }o--|| OFFICE : "assigned to"
    INTERN_MENTOR_RELATION }o--|| INTERN : "tracks"
    INTERN_MENTOR_RELATION }o--|| MENTOR : "assigns"
    MENTOR }o--|| SUPERVISOR : "reports to"
    SUPERVISOR }o--|| SUPERVISOR_DEPARTMENT : "manages"
    ASSIGNMENT }o--|| INTERN : "assigned to"
    ASSIGNMENT }o--|| MENTOR : "reviewed by"
    ANNOUNCEMENT }o--|| MENTOR : "published by"

    INTERN {
        int id PK
        string first_name
        string last_name
        string email UK
        date start_date
        date end_date
    }

    MENTOR {
        int id PK
        string name
        string email UK
        string title
    }

    SUPERVISOR {
        int id PK
        string name
        string email UK
    }
```

---

## API Endpoints

### Announcements
* `GET /api/announcements` - Retrieve all announcements.
* `POST /api/announcements` - Publish a new announcement.

### Assignments (Tasks)
* `GET /api/assignments` - List assignments with filter keys.
* `POST /api/assignments` - Issue a new assignment to an intern.
* `PUT /api/assignments/{id}` - Update assignment details/status.

### Intern & Mentor Management
* `GET /api/interns` - Retrieve all interns.
* `POST /api/interns` - Register a new intern profile.
* `GET /api/mentors` - List technical mentors.
* `POST /api/relations` - Map an intern to a mentor.

---

## Getting Started

### Prerequisites
- **Java 21 JDK** or newer
- **Maven 3.9+**
- **PostgreSQL 16**

### Local Run
1. Configure database connection parameters in `src/main/resources/application.properties` (or set env variables).
2. Run database migrations and start the application:

```bash
mvn clean install
mvn spring-boot:run
```

---

## Configuration

Key environment keys mapped inside `application.properties`:

| Key | Description |
|-----|-------------|
| `SPRING_DATASOURCE_URL` | JDBC Connection URL to PostgreSQL |
| `SPRING_DATASOURCE_USERNAME` | Database username |
| `SPRING_DATASOURCE_PASSWORD` | Database password |
| `AZURE_ACTIVEDIRECTORY_CLIENT_ID` | OAuth2 Client ID for Entra Token validation |
| `AZURE_ACTIVEDIRECTORY_TENANT_ID` | Microsoft Tenant ID |

---

## Engineering Highlights

- **JPA Auditing**: Extends custom `BaseEntity` tracking record lifecycles.
- **DTO Modeling**: Strictly isolates business models from persistence layers using transactional mappings.
- **Entra ID Authentication Integration**: Offloads password management securely to MSAL provider.
