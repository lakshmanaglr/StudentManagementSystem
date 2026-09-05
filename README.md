# Student Management System

Full-stack Student Management System using Java 17, Spring Boot, JDBC, MySQL and React/Vite. Hibernate and JPA are NOT used.

## Included features
- Add Student
- View Students
- Search Student by name/email/course
- Update Student
- Delete Student
- Open Attendance for each student
- Add Attendance by subject
- View Attendance and automatic percentage
- Update Attendance
- Delete Attendance
- Subjects stored in MySQL

## Setup in VS Code
### MySQL
Run `database/schema.sql` in MySQL Workbench.

### Backend
Edit `backend/src/main/resources/application.properties` and replace `YOUR_MYSQL_PASSWORD` with your MySQL password.

In terminal 1:
```
cd backend
mvn spring-boot:run
```
Backend runs on http://localhost:8080

### Frontend
In terminal 2:
```
cd frontend
npm install
npm run dev
```
Open http://localhost:5173

## Architecture
React -> REST API -> Spring Boot Controller -> Service -> DAO -> JdbcTemplate/JDBC -> MySQL

## REST APIs
GET /api/students
GET /api/students/{id}
GET /api/students?search=java
POST /api/students
PUT /api/students/{id}
DELETE /api/students/{id}
GET /api/subjects
GET /api/attendance/student/{studentId}
POST /api/attendance
PUT /api/attendance/{id}
DELETE /api/attendance/{id}

### Important
The `frontend/package.json` is included. From the `frontend` folder run `npm install` once, then `npm run dev` whenever you start the frontend.
