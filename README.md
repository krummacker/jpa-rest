# JPA REST

A small Spring Boot application demonstrating how to model relational business data with Jakarta Persistence (JPA) and expose repositories as REST resources with Spring Data REST.

## Domain model

- **Customers** have a trusted, new, or avoid rating.
- **Employees** have names and phone numbers and can belong to a department.
- **Departments** contain employees.
- **Delivery notes** have a number, shop number, shipping date, and line items.

The entities and relationships are intended as an example of JPA mapping and repository-backed REST APIs.

## REST API

Spring Data REST exposes the repositories as REST resources. Delivery notes are available at `/deliverynotes`; the other repository paths are derived from their entity names. The admin endpoint below creates sample customers, employees, departments, and delivery notes:

```http
PUT /admin/setup/{count}
```

`count` controls how many batches of sample data are created.

## Technology

- Java 25
- Spring Boot 4
- Spring Data REST and Spring Data JPA
- H2 for embedded development, with MySQL Connector/J available for MySQL deployments
- Maven

The configured Hibernate schema mode creates the database schema on application startup. Datasource settings are in the top-level `application.properties` file. The MySQL connection examples there are commented out; with H2 on the classpath, Spring Boot can use an embedded database by default.

## Run

Run the tests:

```bash
mvn test
```

Start the application:

```bash
mvn spring-boot:run
```

Package the application:

```bash
mvn package
```
