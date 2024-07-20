# receipt-processor
receipt-processor-challenge


**Technical Details:**

Project Name: receipt-processor
Project Version: 0.0.1-SNAPSHOT
Framework: Spring Boot
Database: H2 (in-memory database)
Testing: JUnit 5
Description: https://github.com/fetch-rewards/receipt-processor-challenge


**Dependencies and Technologies:**

**Spring Boot:** Java Framework to build the RESTful APIs.

**Spring Boot Starter Parent:** To configure basics of Spring Boot Application.

**Spring Boot Starter Data JPA:** To add ORM Mapping for the Database.

**Spring Boot Starter Web:** Adds necessary dependencies for building web applications.

**Spring Boot DevTools:** It offers development-time features like automatic application restart and live reload.

**H2 Database:** In-memory Database used for testing the persistence of objects.

**Project Lombok:** Reduces boilerplate code for adding getters, setters, constructors.

**Spring Boot Starter Test:** Adds testing dependencies and utilities for writing unit and integration tests.

**JUnit 5 Spring:** Adds testing framework the APIs in Distributed Environment.


**Steps for Running and Building the Service**
 
# Prerequisites:
Install Docker Desktop on your machine.

# Steps:
1. Go to commandline and clone the project repository - git clone https://github.com/varshinikaithapuram/receipt-processor.git
2. Navigate to the project directory - cd <project_directory>  ( Ex: cd receipt-processor)
3. Build the jar to run it in your docker- **./mvnw.cmd clean package** 
    In windows use - **mvnw.cmd clean package** (./ is not necessary)
4. Create the Docker image - **docker-compose up --build**
This command will start the application container along with any necessary dependencies defined in the docker-compose.yml file.
   *This project runs in 8080, so make sure no other service runs in 8080 port.*

5. Open your Postman or any similar tool and hit the below APIs as mentioned in the Receipt-Processor Challenge Document:  

POST -  http://localhost:8080/receipts/process

GET - http://localhost:8080/receipts/{id}/points


6. To stop your running docker- **docker-compose down**
