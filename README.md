# Student Management System

A full-stack web application to manage student records. It provides Create, Read, Update and Delete (CRUD) operations, with search by name and filter by department. The backend is a Spring Boot REST API, and the front end is plain HTML, CSS and JavaScript served by Spring Boot.

## Tech Stack

- **Frontend:** HTML, CSS, JavaScript (no framework)
- **Backend:** Java 17, Spring Boot
- **Database:** H2 (in-memory) with Spring Data JPA / Hibernate
- **Build Tool:** Maven

## Features

- Add, view, update and delete students
- Search students by name and filter by department
- Front end calls the REST APIs using the JavaScript fetch() API
- Layered architecture: Controller, Service, Repository

## API Endpoints

| Method | URL | Description |
|--------|-----|-------------|
| GET | /api/students | List all students |
| GET | /api/students/{id} | Get a student by ID |
| POST | /api/students | Create a student |
| PUT | /api/students/{id} | Update a student |
| DELETE | /api/students/{id} | Delete a student |
| GET | /api/students/department/{dept} | List students by department |
| GET | /api/students/search?name=raj | Search students by name |

Sample request body for POST and PUT:

```json
{
  "name": "Rajesh Kumar",
  "email": "rajesh@example.com",
  "department": "CSE",
  "age": 20,
  "marks": 85.5
}
```

## How to Run

1. Install Java 17 or above and Maven.
2. Clone the repository:
   ```
   git clone https://github.com/MariaRejo/Student-Management-System.git
   ```
3. Start the application:
   ```
   mvn spring-boot:run
   ```
   You can also run StudentManagementApplication.java from an IDE such as IntelliJ IDEA or Eclipse.
4. Open http://localhost:8080 in your browser to use the application.

## H2 Database Console

1. Open http://localhost:8080/h2-console while the app is running.
2. JDBC URL: jdbc:h2:mem:studentdb
3. Username: sa, Password: (leave empty)
4. Click Connect to view the STUDENTS table.

Note: H2 is an in-memory database, so data resets when the application stops.

## Author

Maria Rejo J
LinkedIn: https://www.linkedin.com/in/it-maria-rejo-j-635597264
GitHub: https://github.com/MariaRejo
