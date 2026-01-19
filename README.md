# Athlete Performance Tracker (Spring Data JPA)

## Overview
Athlete Performance Tracker is a backend-focused Spring Boot application that demonstrates the usage of **Spring Data JPA** to manage and query athlete statistics in a sports management system.

The project focuses on **data access concepts** and does **not expose any REST APIs**.

---

## Objectives
- Track athlete performance details
- Practice Spring Data JPA features:
  - Entity Mapping
  - Derived Query Methods
  - JPQL Queries
  - Native SQL Queries
  - Pagination and Sorting
  - Modifying (Bulk Update) Queries

---

## Technology Stack
- Java 17 (or 11)
- Spring Boot
- Spring Data JPA
- H2 In-Memory Database
- Maven

---

## Domain Model
### Athlete
- `id` (Long, Primary Key)
- `firstName`
- `lastName`
- `sport` (Enum)
- `age`
- `careerPoints`
- `isActive`
- `lastMatchDate`

---

## Implemented Features

### Repository Query Methods
- Find athletes by sport
- Find athletes by last name (case-insensitive)
- Find active athletes below a certain age
- Find athletes within a career points range

### JPQL Query
- Retrieve full names of athletes by sport

### Native Query
- Fetch top 3 highest-scoring athletes per sport

### Modifying Query
- Bulk retire athletes who have not played since a given date

### Pagination & Sorting
- Retrieve top 10 athletes sorted dynamically by:
  - Career points (descending)
  - Age (ascending)

---

## How the Application Works
- **CommandLineRunner** is used to:
  - Preload at least 10 diverse athlete records
  - Demonstrate service and repository methods
- Outputs are printed directly to the console
- No REST controllers are used

---

## How to Run
```bash
mvn clean install
mvn spring-boot:run

