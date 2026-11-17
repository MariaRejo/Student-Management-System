# Student Management System (Spring Boot)

Simple REST API project - Student data ah Create, Read, Update, Delete (CRUD) panna.

## Project Structure

```
student-management/
├── pom.xml                          -> Maven dependencies file
├── src/main/java/com/example/studentmanagement/
│   ├── StudentManagementApplication.java   -> Main class (idha run pannanum)
│   ├── model/Student.java                  -> Database table structure
│   ├── repository/StudentRepository.java   -> Database operations
│   ├── service/StudentService.java         -> Business logic
│   └── controller/StudentController.java   -> API endpoints
└── src/main/resources/
    ├── application.properties       -> DB & server config
    └── static/                      -> FRONTEND (plain HTML/CSS/JS, no framework)
        ├── index.html
        ├── style.css
        └── app.js
```

## Frontend Pathi

Frontend ku **React/Angular/Vue mathiri onnum use pannala** — plain **HTML + CSS + JavaScript** than.
Spring Boot automatic aa `src/main/resources/static` folder la iruka files ah
web page aa serve pannikum — so namba separate frontend server run pannanum nu venam!
`app.js` la `fetch()` vachi backend API ah call panni, data ah screen la kaatum
(Add / Edit / Delete / Search — ellame irukum).

## Run Panna Enna Venum

1. **Java 17** (or above) install pannirukanum
2. **Maven** install pannirukanum (IntelliJ / Eclipse la already built-in irukum)
3. Any IDE - **IntelliJ IDEA** (recommend) illa **Eclipse / STS**

## Project ah Eppadi Open Pandrathu

### IntelliJ IDEA (Easy method)
1. Zip file ah unzip pannunga
2. IntelliJ open pannunga -> `File` -> `Open` -> `student-management` folder ah select pannunga
3. `pom.xml` file ah kandippa Maven project aa detect pannikum
4. Konjam time eduthukum (dependencies download aagum - first time la internet venum)

### Eclipse / Spring Tool Suite (STS)
1. Zip ah unzip pannunga
2. Eclipse open pannunga -> `File` -> `Import` -> `Maven` -> `Existing Maven Projects`
3. `student-management` folder ah select pannunga -> `Finish`

## Run Pandrathu Eppadi

1. `StudentManagementApplication.java` file ah thura
2. Andha file la `main` method mela right-click pannunga
3. **"Run 'StudentManagementApplication'"** nu option click pannunga
4. Console la idhu maadhiri varum:
   ```
   Tomcat started on port 8080
   === Student Management System Started! ===
   ```
5. Adhukapram app `http://localhost:8080` la run aagikitu irukum
6. Browser la po: **`http://localhost:8080`** — ippo frontend UI page open aagum!
   (Idhu la irundhu than students ah Add/Edit/Delete/Search panna mudiyum, Postman kooda venam)

(Terminal vachi run panna venumna: zip unzip panna folder ku po, `mvn spring-boot:run` nu type pannunga)

## API Endpoints Test Pandrathu (Postman use pannunga)

| Method | URL | Enna Pannum |
|--------|-----|--------------|
| GET | `/api/students` | Ella students-um list |
| GET | `/api/students/1` | ID=1 oda student |
| POST | `/api/students` | Pudhu student create |
| PUT | `/api/students/1` | ID=1 oda student update |
| DELETE | `/api/students/1` | ID=1 oda student delete |
| GET | `/api/students/department/CSE` | CSE dept students |
| GET | `/api/students/search?name=raj` | Name la "raj" iruka students |

### POST Request - Sample JSON Body (Postman la Body -> raw -> JSON select pannunga)

```json
{
    "name": "Rajesh Kumar",
    "email": "rajesh@example.com",
    "department": "CSE",
    "age": 20,
    "marks": 85.5
}
```

## Database ah Browser la Paakalam (H2 Console)

1. App run pannirunga
2. Browser la po: `http://localhost:8080/h2-console`
3. JDBC URL: `jdbc:h2:mem:studentdb`
4. Username: `sa`, Password: (empty vidunga)
5. `Connect` click pannunga - "STUDENTS" table ah paakalam

## Resume la Eppadi Mention Pannalam

"Developed a RESTful Student Management System using Spring Boot, implementing CRUD
operations with a layered architecture (Controller-Service-Repository), Spring Data JPA
for database interaction, and input validation using Jakarta Bean Validation."

## Future la Add Pannalam (Optional - resume ku innum weight aagum)

- MySQL/PostgreSQL use panni real database connect pannalam
- Exception handling globally handle panna `@ControllerAdvice` add pannalam
- Swagger/OpenAPI documentation add pannalam
- Unit tests (JUnit + Mockito) add pannalam
- Spring Security add panni login/authentication add pannalam
