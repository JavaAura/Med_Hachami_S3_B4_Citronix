# Citronix Project

## Project Overview
**Citronix** is a farm management application designed for managing lemon farms. The application allows farmers to track and manage various aspects of their farm, such as farm creation, field management, tree tracking, harvests, and sales. It helps optimize tree productivity based on their age and improves overall farm operation management.

### Main Features:
- **Farm Management**: Create, update, and view farm details such as name, location, area, and creation date.
- **Field Management**: Link fields to farms, ensuring that the total field area is within the farm's limits.
- **Tree Management**: Track tree planting dates, calculate tree age, and determine productivity based on tree age (young, mature, or old trees).
- **Harvest Management**: Track harvests by season, with a record of the harvest date and total quantity.
- **Sales Management**: Record sales transactions, including the date, price per unit, customer information, and associated harvests.

## Technology Stack

### Backend:
- **Spring Boot**: A framework for building the REST API and handling business logic.
- **Spring Data JPA**: For object-relational mapping and database interactions.
- **Spring Validation**: For input validation using annotations.
- **JUnit**: For unit testing the backend services.
- **Mockito**: For mocking objects during unit testing.
- **Lombok**: For reducing boilerplate code in entities (getters, setters, etc.).
- **MapStruct**: For mapping between entities, DTOs (Data Transfer Objects), and View Models.

### Database:
- **PostgreSQL**: A relational database for storing farm, field, tree, harvest, and sales data.
- **H2**: An in-memory database used for unit tests.

### Tools:
- **Postman**: For API testing and documentation.
- **Swagger**: For generating API documentation and testing endpoints directly from the app.
- **Git**: For version control and collaboration.
- **Maven**: For managing dependencies and building the project.

## Installation

### Prerequisites:
- **Java 11 or higher**: The project is developed using Java.
- **Maven**: A build automation tool for managing the project dependencies.
- **PostgreSQL**: For setting up the database.

### Steps to Run Locally:

1. **Clone the repository**:
   ```bash
   git clone https://github.com/JavaAura/Med_Hachami_S3_B4_Citronix.git
   cd Med_Hachami_S3_B4_Citronix
