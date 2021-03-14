# doctor-labelling-service
This microservices responsible to manager Labelling Cases

## Back-end Technology stack used

- Java 11
- Spring Boot Web 2.4.3
- Spring Boot Starter Data JPA
- Spring Boot devtools
- Spring Boot Starter Test
- Spring Boot Starter Validation
- Spring Cloud Starter Openfeign
- Spring Boot Starter Security
- IO JSON WEB TOKEN v0.9.1
- SPringFox Swagger 2 v2.9.2
- SpringFox Swagger UI v2.9.2
- Mysql
- Docker
- Maven

## Runnning Spring Boot Applicaiton DoctorLabel Labelling Service Back-End Microservices "traditional way"

```bash
git clone https://github.com/igormelao/doctor-labelling-service.git
cd doctorlabel-labelling-service
# You need a instancy of Mysql running on port 3306 with
 - database: db_dev_doctor_labelling
# Generate JARs with Maven
 - mvn clean package
# Starting Spring Boot Projet 
 - with JAR generated, go to the folder /target and just run  command
  - java -jar doctorlabel-labelling-service-0.0.1-SNAPSHOT.jar
  
PS: The Spring Boot will run the file inside of project data.sql and will populate some data.  

You need to authenticate to use this application.

Make a POST request with credentials e-mail and password to 
http://localhost:8081/auth/

It will return a token and type Bearer. Keep it to send with other requests to access the services
```
``` Accessing the API Document
 When Back-nd Microservices be runing, you can see all resources for the API through documentation generated automatically by Swagger Project on
 
 # Doctor-Labelling-Services
  http://localhost:8081/swagger-ui.html#
```
