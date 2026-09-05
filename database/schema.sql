CREATE DATABASE IF NOT EXISTS student_management;
USE student_management;
CREATE TABLE IF NOT EXISTS students (id INT PRIMARY KEY AUTO_INCREMENT,name VARCHAR(100) NOT NULL,email VARCHAR(100) NOT NULL UNIQUE,phone VARCHAR(15),course VARCHAR(100) NOT NULL,year INT NOT NULL,created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP);
CREATE TABLE IF NOT EXISTS subjects (id INT PRIMARY KEY AUTO_INCREMENT,name VARCHAR(100) NOT NULL,course VARCHAR(100) NOT NULL);
CREATE TABLE IF NOT EXISTS attendance (id INT PRIMARY KEY AUTO_INCREMENT,student_id INT NOT NULL,subject_id INT NOT NULL,total_classes INT NOT NULL DEFAULT 0,attended_classes INT NOT NULL DEFAULT 0,FOREIGN KEY(student_id) REFERENCES students(id) ON DELETE CASCADE,FOREIGN KEY(subject_id) REFERENCES subjects(id) ON DELETE CASCADE,UNIQUE(student_id,subject_id));
INSERT IGNORE INTO subjects(name,course) VALUES ('Java','CSE'),('SQL','CSE'),('Web Development','CSE'),('React','CSE'),('Data Structures','CSE'),('Database Management','IT'),('JavaScript','IT');
INSERT IGNORE INTO students(name,email,phone,course,year) VALUES ('Ravi Kumar','ravi@example.com','9876543210','CSE',3),('Priya Sharma','priya@example.com','9876501234','IT',2),('Arun Reddy','arun@example.com','9123456780','CSE',4);
