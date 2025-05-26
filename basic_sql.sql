-- Creating the database
CREATE DATABASE StudentRecords;

-- Create the table Students
CREATE TABLE Students(
    studentId INT AUTO_INCREMENT PRIMARY KEY,
    firstName VARCHAR(50) NOT NULL,
    lastName VARCHAR(50) NOT NULL,
    age INT,
    email VARCHAR(100) NOT NULL
);


-- Create the table Courses
CREATE TABLE Courses (
    courseId INT AUTO_INCREMENT PRIMARY KEY,
    courseName VARCHAR(50) NOT NULL,
    courseDescription VARCHAR(50) NOT NULL,
    studentId INT,
    FOREIGN KEY (studentId) REFERENCES Students(studentId)
);

-- Insert one register to table Students
INSERT INTO Students (firstName, lastName, age, email)
VALUES ('Maria', 'Garcia', 20, 'maria.garcia@example.com');


-- Insert row values at once
INSERT INTO Students (firstName, lastName, age, email)
VALUES 
    ('Luis', 'Hernandez', 19, 'luish@example.com'),
    ('Selene', 'Lopez', 21,'selene@io.com'),
    ('Denzel', 'Vazquez', 18, 'dvazq@test.com'),
    ('Sofia', 'Escamilla', 20, 'sofesc@io.com');


-- Insert row value to Courses, students: 1 and 5
INSERT INTO Courses (courseName, courseDescription, studentId)
VALUES ('Data Structures', 'Introduction to Data Structures', 1);

INSERT INTO Courses (courseName, courseDescription, studentId)
VALUES ('Data Structures', 'Introduction to Data Structures', 5);

-- Course for student 2
INSERT INTO Courses (courseName, courseDescription, studentId)
VALUES ('Calculus II', 'Programming Fundamentals', 2);

-- Course for student 3
INSERT INTO Courses (courseName, courseDescription, studentId)
VALUES ('Literature', 'Shakespeare Studies', 3);

-- Two courses for student 4
INSERT INTO Courses (courseName, courseDescription, studentId)
VALUES ('Physics', 'Classical Mechanics', 4),
       ('Chemistry', 'Organic Chemistry', 4);

-- Course for studentId 5
INSERT INTO Courses (courseName, courseDescription, studentId)
VALUES ('History', 'World History', 5);

-- Check course assignments
SELECT
    c.courseId,
    c.courseName,
    s.studentId,
    CONCAT(s.firstName, ' ',s.lastName) AS studentName
FROM Courses c
JOIN Students s on c.studentId = s.studentId; 


/*
Reflection questions
Question 1: Why did you choose specific data types for the columns in 
the Students table? Explain how these data types help in storing student information efficiently.

Having these data types allow the system to constrain the values that enter the database, acting
like a filter. Also having just the essential information from a student allows not having
redundant or non-relevant data.

Question 2: What are some benefits of using databases over simple file storage systems (like spreadsheets)?

The speed of the lecture and writing new registers for new users. If reading plain texts files or
spreadsheets, when the information increases, the speed does too. Spreadsheets were not created
to hold a large amount of data, you can use it for analytics but its purpose won't go further than that.
*/