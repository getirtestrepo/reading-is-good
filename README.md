# reading-is-good project

The project aim is to provide basic operations for online book store, such as order, stock, shipment.
used libraries, jdk11, spring-boot 2.3.10, mongodb

image is hosted on docker hub
https://hub.docker.com/repository/docker/getirdockerrepo/latest

## Usage
be sure docker and docker-compose installed on your local computer. 
copy docker-compose.yaml file on your docker host machine.

```bash
docker-compose up

```

swagger url
http://localhost:8080/swagger-ui.html#/

after all containers are up, the project will be exposed at
http://localhost:8080/

most of endpoints are secured, must be authenticated. stock endpoints are not secured due to add stock.

before starting, the user must be created over user/ enpoints.postman collections can be used.
mobileNumber must be uniq.
http://localhost:8080/user/register

```
{
    "firstName" : "sihab",
    "lastName": "demir",
    "password" : "1234",
    "age": 45,
    "address": "Istanbul",
    "mobileNumber" : 111223123433
}
```

after user creation, user must login and get the jwt token.
http://localhost:8080/user/login

```
{
    "password" : "1234",
    "mobileNumber" : 111223123433
}
```

get customer detail endpoint is secure, jwt token must be used in Authorization header
http://localhost:8080/customer/detail

stock endpoints are not secured, can be used for adding stock, stockCode is uniq, if exist, it stockCount will be increased, if not, new stock will be added.
http://localhost:8080/stock/add

```
{
    "password" : "1234",
    "mobileNumber" : 111223123433
}
```

order placement enpoint, jwt is mandatory, orderCode is uniq, it will check the stock, if stock is available, execute order placement and update stock.
will return orderId, it is used to query orderDetail.
http://localhost:8080/order/buy

```
{
    "orderCode": "ABC100",
    "orderCount": 10
}
```

getOrderDetail endpoint avalibale for user who made order.
http://localhost:8080/order/get?orderId=6099c97fe40aff2811868fafd

user must be login if token expires.
user can buy moe than 1 product, the price will be calculated.

customer can see his own orders, needs jwt token
http://localhost:8080/order/get/customer








