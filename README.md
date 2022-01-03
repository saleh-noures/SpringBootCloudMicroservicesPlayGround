# Spring Boot Cloud Microservices Course PlayGround
## A-How to run the application
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
## B-JWT
        -After Creating a user, the user will need to login to get a Json Web Token (JWT) to be authenticated for the subsequent requests.
        -The AuthenticationFilter in UserClient will create the Token using the "token.secret" property after a successfull login, add it to the header with Key "token", and then send it back to the user
        -The user will use the token in the subsequent request by adding it to the header under Key "Authorization" and Value "Bearer  token_text"
         then the API GateWay will use the same "token.secret" to ensure the JWT is valid before forwarding it to the Microservices

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