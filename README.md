# Spring Boot, Cloud, and Microservices Sample Project
## A-How to run:
 ###    1-Install RabbitMQ, then start RabbitMQ:
        -Open RabbitMQ Command Prompt
        -Run "set HOMEDRIVE=C:\Users\nouressaleh\rabbitmqConf"
        -Run "rabbitmq-plugins.bat enable rabbitmq_management"
        -Run "rabbitmq-server"
        -To verify the RabbitMQ is working, open http://localhost:15672/ . use username:guest Password:guest.
 ###    2-Start the servers in this order:
        -Configuration Server
        -Discovery Server (URL:http://localhost:8010, User:user1/user1)
        -Microservices
        -API Gateway
## B-Microservices
        There are 3 dummy Microservices in this project
        -users-ws
        -albums-ws
        -account-ws

## C-Jason Web Token (JWT)
        -JWT is used as users authentication mechanism
        -After Creating a user, the user will need to login to get a Json Web Token (JWT) to be authenticated for the subsequent requests.
        -The AuthenticationFilter in UserClient will create the Token using the "token.secret" property after a successfull login, add it to the header with Key "token", and then send it back to the user
        -The user will use the token in the subsequent requests by adding it to the header under Key "Authorization" and Value "Bearer  token_text"
         then the API GateWay will use the same "token.secret" to ensure the JWT is valid before forwarding it to the Microservices
 ## D-Password Encryption
        - Passwords are encrypted in the database
        - Using Spring BCryptPasswordEncoder
 ## E- Feign Http Clint 
         - Feign is used for the internal communications between the Microservices
         - Feign ErrorDecoder is used to handle the errors 
 ## F- Resilience4j. 
         Two modules are used in this project
         - Circuit Breaker 
         - Retry
         
 ## G-URLS
        -To create a user --> POST http://localhost:8082/users-ws/users/ and the payload is
            {
            "firstName":"Saleh",
            "lastName":"Noures",
            "password":"1122334455",
            "email":"test@test.com"
            }
        -To login and receive a JWT token --> POST http://localhost:8082/users-ws/users/login and the payload is
            {
            "email":"test@test.com",
            "password":"1122334455"
            }
        -To call "/status/check" --> GET http://localhost:8082/users-ws/users/status/check and in the header add
          Key: Authorization
          Value: Bearer JWT_Token
        -To push configraton changes to the Microservices (Verified to be working on GitHub config repo only) --> http://localhost:8012/actuator/busrefresh
        -To see the configrations (properties) applied to users-WS Microservice -->  http://localhost:8012/users-ws/default
        -To health check users-WS Microservice using actuator --> http://localhost:8082/users-ws/actuator/health  
        -To get a user and his albums (Using Feign Http Client) --> GET http://localhost:8082/users-ws/users/{userID} and in the header add
          Key: Authorization
          Value: Bearer JWT_Token
        -To check the Circuit Breaker events log through the Actuator-- > Get http://localhost:8082/users-ws/actuator/circuitbreakerevents
 ## Sleuth and Zipkin
         -Spring Cloud Sleuth is used to generate and attach the trace id, span id to the logs so that these can then be used by tools like Zipkin and ELK for storage and analysis.        
         -Zipkin is a distributed tracing system. It helps gather timing data needed to troubleshoot latency problems in service architectures
         - To download and install Zipkin --> https://zipkin.io/pages/quickstart.html 
         - To access Zipkin --> http://localhost:9411/zipkin/
 
 ## H-Spring Boot Run Dashboard (Services)
    To manage all the Servers in one palce using the Run Dashboard
    1- Open one of the servers in Intellij - Maybe the Discovery Server.
    2- View --> Tool Windows --> Sevices --> Add Sevice --> Run Configuration Type --> Spring Boot
    3- View --> Tool Windows --> Add Maven Projects --> Select the POM.xml for the all other servers

![Spring Boot Run  Dashboard](https://user-images.githubusercontent.com/25222121/151729513-f9a898fd-0ac6-40d6-9510-182f8d3fa120.jpg)        


         
