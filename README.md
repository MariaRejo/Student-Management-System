# Student Management System

A full-stack web application to manage student records. It provides Create, Read, Update, and Delete (CRUD) operations, with search by name and filter by department. The backend is a Spring Boot REST API, and the frontend is built using HTML, CSS, and JavaScript, served by Spring Boot.

## Tech Stack

- **Frontend:** HTML, CSS, JavaScript (No Framework)
- **Backend:** Java 17, Spring Boot
- **Database:** MySQL
- **ORM:** Spring Data JPA / Hibernate
- **Build Tool:** Maven

## Features

- Add, view, update, and delete student records
- Search students by name and filter by department
- Frontend communicates with REST APIs using JavaScript Fetch API
- Layered architecture: Controller, Service, Repository
- Automatic database and table updates using Hibernate

## API Endpoints

| Method | URL | Description |
|---|---|---|
| GET | `/api/students` | List all students |
| GET | `/api/students/{id}` | Get a student by ID |
| POST | `/api/students` | Create a student |
| PUT | `/api/students/{id}` | Update a student |
| DELETE | `/api/students/{id}` | Delete a student |
| GET | `/api/students/department/{dept}` | List students by department |
| GET | `/api/students/search?name=raj` | Search students by name |

## Sample Request Body

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

### Prerequisites

1. Install Java 17 or above.
2. Install Maven.
3. Install MySQL Server.

### Setup Database

1. Start MySQL Server.
2. Create a database named `studentdb` (if it does not already exist).

```sql
CREATE DATABASE studentdb;
```

### Configure Database Credentials

Set the following environment variables:

- `DB_USERNAME` – MySQL username (defaults to `root`)
- `DB_PASSWORD` – MySQL password

The application uses the following database configuration:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/studentdb?createDatabaseIfNotExist=true
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
spring.jpa.hibernate.ddl-auto=update
```

### Run the Application

1. Clone the repository:

```bash
git clone https://github.com/MariaRejo/Student-Management-System.git
```

2. Navigate to the project directory.
3. Run the application:

```bash
mvn spring-boot:run
```

You can also run `StudentManagementApplication.java` from an IDE such as IntelliJ IDEA or Eclipse.

4. Open the application in your browser:

`http://localhost:8080`

## Database Management

- MySQL is used for persistent data storage.
- Spring Data JPA and Hibernate handle database operations.
- Hibernate automatically updates the database schema using `ddl-auto=update`.

## Author

**Maria Rejo J**

- LinkedIn: https://www.linkedin.com/in/it-maria-rejo-j-635597264
- GitHub: https://github.com/MariaRejo
