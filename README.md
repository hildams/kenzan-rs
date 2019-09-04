# Kenzan-rs

Kenzan-rs is a Test Application  that expose CRUD method for employees through
a Java Rest API

## Clone and Run

Recomended IDE for run and test are STS(Springboot suite tool) or Eclipse with Springboot pluig-in

1.- Clone project from Git
```bash
 File -> Import -> Projects from Git -> Clone URL
```

2.- Convert to Maven project using existing pom.xml
```bash
Right click on Java Project -> Configure -> Convert to maven project
```

3.- Run as Sprigboot app
```bash
Right click on Java Project -> Run as -> Springboot app
```
## Dependencies

Kenzan-rs uses a MySQL database hosted in an AWS server and is already configured in file
```bash
/src/main/java/com/kenzan/config/ApplicationCotextConfig.java
```

To configure with local DB:

- [Download and install MySQL Server] (https://dev.mysql.com/downloads/installer)

- [Download and install MySQL Workbench](https://dev.mysql.com/downloads/workbench/5.2.html)

- Run the following script to create a local instance of the database.

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
- Set credentials of your local Database on previous ApplicationCotextConfig.java File.

## Usage

More information about API functions at:
[Kenzan-rs at Postman](https://www.getpostman.com/collections/82f6d1495ccac6f4bb75)

![](https://img.shields.io/badge/Create%20Employee-POST-blue)
```batch
curl --location --request POST "http://localhost:63001/employee/saveEmployee" \
  --header "Content-Type: application/json" \
  --data "{
    \"birthdate\": \”yourDate\”,
    \"firstName\": \”Name\”,
    \"lastName\": \”LastName\”,
    \"middleInitial\": \”Middle Initial\”,
    \"startDate\": \"Date\”
}"
```

![](https://img.shields.io/badge/Update%20Employee-POST-blue)
```batch
curl --location --request POST "http://localhost:63001/employee/updateEmployee" \
  --header "Content-Type: application/json" \
  --data "{
    \"employeeId\": 1,
    \"birthdate\": \”yourDate\”,
    \"firstName\": \”Name\”,
    \"lastName\": \”LastName\”,
    \"middleInitial\": \”Middle Initial\”,
    \"startDate\": \"Date\”
}"
```

![](https://img.shields.io/badge/Get%20Employee-POST-blue)
```batch
curl --location --request POST "http://localhost:63001/employee/employeeById?employeeId=2" \
  --header "Content-Type: application/json" \
  --data ""
```
![](https://img.shields.io/badge/All%20Employees-GET-green)
```batch
curl --location --request GET "http://localhost:63001/employee/listEmployees"
```
![](https://img.shields.io/static/v1?label=Upload%20File&message=GET&color=green)
```batch
curl --location --request GET "http://localhost:63001/employee/uploadEmployees"
```
![](https://img.shields.io/badge/Delete%20AEmployee-DELETE-yellow)
```batch
curl --location --request DELETE "http://localhost:63001/employee/deleteEmployee?employeeId=3" \
  --header "Content-Type: application/json"
```


## Description
Kenzan-rs  uses the following features:

* Springboot framework
* Java 8
* MySql
* Basic Auth Security


## Project Status
 This API is developed as a POC therefore multiple changes should be done to make it production ready. A list of the items to improve are:
 
- Unit test
- Credential for Basic security Encrypted
- Credentials for DB externalized and encrypted
- Improve validation of batch upload fields
- Integrate Swagger as API documentation
