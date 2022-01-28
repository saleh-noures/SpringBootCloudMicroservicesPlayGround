# Spring Boot, Cloud, and Microservices Sample Project
## A-How to run:
###    1-Install RabbitMQ, then start RabbitMQ:
        -Open RabbitMQ Command Prompt
        -Execute "set HOMEDRIVE=C:\Users\nouressaleh\rabbitmqConf"
        -Execute "rabbitmq-plugins.bat enable rabbitmq_management"
        -Execute "rabbitmq-server"
        -To verify the RabbitMQ is working, open http://localhost:15672/ . use username:guest Password:guest.
###    2-Start the servers in this order:
        -Configuration Server
        -Discovery Server
        -Microservices
        -API Gateway
## B-Jason Web Token (JWT)
        -JWT is used as users authentication mechanism
        -After Creating a user, the user will need to login to get a Json Web Token (JWT) to be authenticated for the subsequent requests.
        -The AuthenticationFilter in UserClient will create the Token using the "token.secret" property after a successfull login, add it to the header with Key "token", and then send it back to the user
        -The user will use the token in the subsequent requests by adding it to the header under Key "Authorization" and Value "Bearer  token_text"
         then the API GateWay will use the same "token.secret" to ensure the JWT is valid before forwarding it to the Microservices
 ## D-Password Encryption
        - Passwords are encrypted in the database
        - Using Spring BCryptPasswordEncoder
 ## E- Feign Http Clint 
         - Feign is used for the internal communications between the Microservices FeignErrorDecoder
         - Feign ErrorDecoder is used to handle the errors 
 ## F- Resilience4j. Two modules are used in this project
         - Circuit Breaker 
         - Retry
         
 ## C-URLS
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
        
         
         
