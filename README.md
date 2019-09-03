# Kenzan-rs

Kenzan-rs is a Test Application  that expose CRUD method for employees through
a Java Rest API

## Dependencies

Kenzan-rs uses a MySQL database hosted in an AWS server and is already configured on
```bash
/src/main/java/com/kenzan/config/ApplicationCotextConfig.java
```

To run with local DB, run the following script to create a local instance of the database and configure on previous ApplicationCotextConfig.java.

```sql
CREATE DATABASE employeedb;

CREATE TABLE IF NOT EXISTS EMPLOYEE (
    EMPLOYEE_ID INT AUTO_INCREMENT PRIMARY KEY,
    FIRST_NAME VARCHAR(100) NOT NULL,
    MIDDLE_INITIAL CHAR(2) NOT NULL,
    LAST_NAME VARCHAR(100) NOT NULL,
    BIRTHDATE DATE NOT NULL,
    START_DATE DATE NOT NULL,
    STATUS VARCHAR(10) NOT NULL
);
```

## Usage

More information about API functions at:
[Kenzan-rs at Postman](https://www.getpostman.com/collections/82f6d1495ccac6f4bb75)

* Upload Employees File
* Get Employee by ID
* Create new employees
* Update existing employees
* Delete employees
* Get all employees


## Description
Kenzan-rs  uses the following features:

* Springboot framework
* Java 8
* MySql
* Basic Auth Security


## Project Status
 This API is developed as a POC therefore  multiple changes should be done to make it production ready.  A list of the  items to improve are:

- Credential for Basic security Encrypted
- Credentials for DB externalized and encrypted
- Improve validation of batch upload fields
