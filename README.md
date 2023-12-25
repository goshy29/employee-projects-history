# employee-projects-history
## Description 

This is an application that identifies the pair of employees who have worked together on 
common projects for the longest period of time and the time for each of those projects.
The system provides the ability to read and write data from a csv file into a database. 
Also display a list of all employees and projects, add new ones and edit existing ones. 
In the last section of the menu, you will find the pair of employees who have worked 
together the longest on a given project.

## Instructions

1. Configure the application.properties file with your database name, username and password.
2. Create tables employees, projects and history in your database (PostgreSQL recommended):
 
    CREATE TABLE employees (
        id INT PRIMARY KEY NOT NULL,
        name VARCHAR(255) NOT NULL
    ); 
    
    CREATE TABLE projects (
        id INT PRIMARY KEY NOT NULL,
        name VARCHAR(255) NOT NULL
    ); 
    
    CREATE TABLE history (
        employeeId INT NOT NULL,
        projectId INT NOT NULL,
        startDate DATE NOT NULL,
        endDate DATE NULL,
        PRIMARY KEY (employeeId, projectId)
    );  

3. In MainController.java file find @GetMapping("/load") and @GetMapping("/couples") annotations
  and replace the path at csvFile with your actuall path to file on your computer.
4. After starting the program for the first time, to load the csv file press Load CSV from the
  menu bar. After this step, the program is ready to work.


