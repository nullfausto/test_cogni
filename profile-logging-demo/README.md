For executing the application use mvn and select the preferred profile.
Profile can also be changed in the application.properties file, by editing code line 3

Example: spring.profiles.active=dev

Dev logging level: DEBUG and TRACE
Prod logging level: WARN and INFO
Test logging level: INFO and DEBUG

Ports for testing the api, base_url = http://localhost:8080/api/users for DEV
http://localhost:8081/api/users for TEST
http://localhost:8082/api/users for PROD

Test endpoint for visualizing logs in console: http://localhost:PORT/api/users/test
