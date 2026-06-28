# 🎓 Student Management System

A console-based Student Management System built using Java, JDBC, and MySQL. The project demonstrates CRUD (Create, Read, Update, Delete) operations with a relational database.

## Features

- Add Student
- View Students
- Search Student by ID
- Update Student Details
- Delete Student
- Menu-driven Console Application

## Tech Stack

- Java
- JDBC
- MySQL
- VS Code

## Project Structure

StudentManagementSystem/
│
├── src/
├── lib/
├── database/
└── README.md

## Database Setup

Run the SQL script:

database/student.sql

## Configure Database

Open `DBConnection.java` and update:

```java
private static final String USER = "root";
private static final String PASSWORD = "YOUR_MYSQL_PASSWORD";
```

## How to Run

1. Clone the repository.
2. Import into VS Code.
3. Add the MySQL JDBC Connector JAR.
4. Create the database using `student.sql`.
5. Update the database password.
6. Run `Main.java`.

## Learning Outcomes

- Core Java
- Object-Oriented Programming
- JDBC
- SQL CRUD Operations
- MySQL Database Connectivity
- Exception Handling

## Author

LakshmanaRao Gedela
