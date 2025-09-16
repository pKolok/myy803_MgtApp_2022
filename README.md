# Courses Management Application

A Spring Boot web application for managing courses, student registrations, and instructors. It also provides statistical analysis of course grades.

## Features

*   **Course Management:** Create, read, update, and delete courses.
*   **Student Registration:** Manage student registrations for courses, including grades.
*   **Instructor Management:** View instructor details.
*   **User Authentication:** Simple login and registration system for instructors.
*   **Grade Statistics:** Calculate and display various statistics for course grades, including:
    *   Mean
    *   Median
    *   Min/Max
    *   Standard Deviation
    *   Variance
    *   Skewness
    *   Kurtosis
    *   95th Percentile

## Technologies Used

*   **Backend:**
    *   Java 18
    *   Spring Boot 2.5.11
    *   Spring Web
    *   Spring Data JPA
    *   Thymeleaf
*   **Database:**
    *   MySQL
*   **Testing:**
    *   JUnit
    *   Mockito
    *   H2 (for testing)
*   **Build Tool:**
    *   Maven

## Prerequisites

Before you begin, ensure you have the following installed:

*   JDK 18 or later
*   Maven 3.2+
*   MySQL Server

## Getting Started

### 1. Clone the repository

```bash
git clone <repository-url>
cd <repository-directory>
```

### 2. Database Configuration

1.  Make sure you have a MySQL server running.
2.  Create a database named `myy803_mgtapp`.
    ```sql
    CREATE DATABASE myy803_mgtapp;
    ```
3.  The application is configured to connect to the database with the following credentials (you can change them in `src/main/resources/application.properties`):
    *   **Username:** `springuser`
    *   **Password:** `ThePassword`
    *   You may need to create this user in MySQL:
        ```sql
        CREATE USER 'springuser'@'localhost' IDENTIFIED BY 'ThePassword';
        GRANT ALL PRIVILEGES ON myy803_mgtapp.* TO 'springuser'@'localhost';
        FLUSH PRIVILEGES;
        ```

### 3. Build the project

```bash
mvn clean install
```

## Running the application

You can run the application using the Spring Boot Maven plugin:

```bash
mvn spring-boot:run
```

The application will start on `http://localhost:8080`.

## Running the tests

To run the tests, execute the following command:

```bash
mvn test
```
