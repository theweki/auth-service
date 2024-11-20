# Setup

    /src/main/resources/certs/private.pem
    /src/main/resources/certs/public.pem

# generate pem files

    -> create rsa key pair
    openssl genrsa -out keypair.pem 2048
    
    -> extract public key
    openssl rsa -in keypair.pem -pubout -out public.pem
    
    -> create private key in PKCS#8 format
    openssl pkcs8 -topk8 -inform PEM -outform PEM -nocrypt -in keypair.pem -out private.pem

# .env

    SERVER_PORT=8100
    SPRING_DATA_MONGODB_URI=mongodb://localhost:27017/auth-service

### Uncomment @Component in MongoSeeder Java File for Populating Mongo Data
