-- Project: Employee Management System - Advanced Database Operations

-- Create Database
CREATE DATABASE EmployeeManagement

-- Creating the tables with their respective constraints

CREATE TABLE Departments(
    departmentId INT AUTO_INCREMENT PRIMARY KEY,
    departmentName VARCHAR(100) NOT NULL
);

CREATE TABLE Employees(
    employeeId INT AUTO_INCREMENT PRIMARY KEY,
    firstName VARCHAR(50) NOT NULL,
    lastName VARCHAR(50) NOT NULL,
    age INT,
    email VARCHAR(100) UNIQUE,
    departmentId INT,
    FOREIGN KEY (departmentId) REFERENCES Departments(departmentId)
);


CREATE TABLE Projects(
    projectId INT AUTO_INCREMENT PRIMARY KEY,
    projectName VARCHAR(100),
    projectBudget DECIMAL,
    managerId INT,
    FOREIGN KEY (managerId) REFERENCES Employees(employeeId)
);

-- Inserting data
INSERT INTO Departments(departmentName) VALUES
('Human Resources'),
('Engineering'),
('Marketing');

-- SELECT * FROM Departments;

INSERT INTO Employees(firstName, lastName, age, email, departmentId) VALUES
('Gabriela','Escandon', 30, 'gabesc@test.com', 1),
('Luis','Hernandez', 23, 'luishdz@io.com', 2),
('Selene','Lopez', 24, 'sellop@tst.com', 2),
('Denzel','Vazquez', 21, 'dvazq@io.com', 2),
('Frida','Valle', 24, 'fvalle@aux.com',3);

INSERT INTO Projects(projectName, projectBudget, managerId) VALUES
('Website Redesign', 5000.00, 2),
('Young Talent Recruitment', 5000.00, 1),
('Emerging LATAM Markets', 10000.00, 5);

-- Retrieve Employee Information: display the list of all employees along with their department names

SELECT d.departmentName,
    CONCAT(e.firstName, ' ',e.lastName) as employeeName
FROM Employees e
INNER JOIN Departments d on e.departmentId = d.departmentId;

-- Project and Manager Information
-- Show all projects along with the names of the managers assigned to them.

SELECT p.projectName, p.managerId,
    CONCAT(e.firstName, ' ',e.lastName) as managerName
FROM Projects p
LEFT JOIN Employees e ON p.managerId = e.employeeId;

-- Filtering data using WHERE
-- List of employees over the age of 22 in the Engineering department

SELECT 
    CONCAT(e.firstName, ' ', e.lastName) AS employeeName,
    e.age
FROM Employees e 
WHERE e.age > 22 AND e.departmentId = 2;

-- Creating views to simplify data access
-- Employee Details View

CREATE VIEW EmployeeDetails AS
SELECT Employees.employeeId, Employees.firstName, Employees.lastName, Departments.departmentName
FROM Employees
INNER JOIN Departments ON Employees.departmentId = Departments.departmentId;

-- Active Projects View
CREATE VIEW ActiveProjects AS
SELECT Projects.projectName, Projects.projectBudget, e.employeeId,
    CONCAT(e.firstName, ' ', e.lastName) as fullName
FROM Projects
LEFT JOIN Employees e ON Projects.managerId = e.employeeId
WHERE Projects.projectBudget > 6000;


/*

Summary:

To create the database tables, it was important to start with the primary table 
which has no dependencies on other tables—the Departments table. Once this was created, 
the next step was to create the Employees table. For the Employees table, it was necessary 
to include a column for the department, as each employee must be assigned to one. 
This attribute was marked as a foreign key to link the employee to their corresponding department.

Finally, for the Projects table, there had to be an employee in charge (a manager). 
To establish this relationship, the Projects table was connected to the Employees table 
via a foreign key, using the employee's identifier, which was named managerId.

When managing queries, it was important to first identify where the required information was 
stored. Once this was determined, the next step was to specify which records to display—whether 
all records from the left table, the right table, or only matching records. 
This helped refine the query results for more specific data retrieval.

Creating views was useful for saving time when executing frequently used queries, 
such as displaying employee information or checking which projects were active.


*/