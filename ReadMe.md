# Setup
    /src/main/resources/certs/private.pem
    /src/main/resources/certs/public.pem

    /src/main/resources/application.properties

# Properties
    spring.application.name=auth-service
    server.port=8100
    
    rsa.private-key=classpath:certs/private.pem
    rsa.public-key=classpath:certs/public.pem
    
    spring.data.mongodb.uri=mongodb://localhost:27017/auth-service

