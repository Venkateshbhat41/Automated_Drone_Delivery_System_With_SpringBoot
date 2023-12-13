# Pizzastore System Documentation
The Pizzastore System is a distributed application consisting of multiple components: Drone Inventory, Pizzastore Orders, Fleet Delivery, Drone Unit, and Monitoring. Each component serves a specific purpose in the overall system.

 ```
inventory_drones 👉 server.port=8080
drones_unit      👉 server.port=8484
fleet            👉 server.port=8312
pizzastore       👉 server.port=8081
monitoring       👉 server.port=8082
 ```

## Components

### 1. Drone Inventory

#### Description

The Drone Inventory component manages drones used for pizza delivery.


#### Instructions

1. **Start Drone Inventory:** 
    ```bash
    ./mvnw spring-boot:run
    ```
2. **Add Drones:** 
    ```bash
    curl -X POST -H "Content-Type: application/json" -d '{"id": "6", "name": "Drone6", "type": "Type6", "capacity": 600}' http://localhost:8080/control/drones

    ```
3. **Get Drones:** 
    ```bash
    curl http://localhost:8080/control/drones
    curl http://localhost:8080/control/drones/5
    ```
4. **Delete Drone:** 
    ```bash
    curl -X DELETE http://localhost:8080/control/drones/6
    ```
5. **Update Drone:** 
    ```bash
    curl -X PUT -H "Content-Type: application/json" -d '{"id": "1", "name": "UpdatedDrone1", "type": "UpdatedType1", "capacity": 150}' http://localhost:8080/control/drones
    ```

### 2. Pizzastore Orders

#### Description

The Pizzastore Orders component handles customer orders for pizzas.

#### Instructions

1. **Start Pizzastore Orders:** 
    ```
    ./mvnw spring-boot:run`
    ```
2. **Add Orders:** 
    ```bash
    curl -X POST -d "pizzaType=Margarita&deliveryAddress=Koskipuisto 1c" http://localhost:8081/pizzastore/v1/orders/place
    ```
3. **Get Orders:** 
    ```bash
    curl http://localhost:8081/pizzastore/v1/orders
    curl http://localhost:8081/pizzastore/v1/orders/{orderId}
    ```
4. **Update Order:** 
    ```bash
    curl -X PUT -d '{"pizzaType":"Vegetarian","deliveryAddress":"456 Oak Street"}' -H 'Content-Type: application/json' http://localhost:8081/pizzastore/v1/orders/{orderId}
    ```
5. **Delete Order:** 
    ```bash
    curl -X DELETE http://localhost:8081/pizzastore/v1/orders/{orderId}
    ```
6. **Assign Order to Drones:** 
      ```bash 
      http://localhost:8081/pizzastore/v1/orders/assign/4

### 3. Fleet Delivery

#### Description

The Fleet Delivery component coordinates the delivery process using available drones.

#### Instructions

1. **Start Fleet Delivery:** 
    ```
    ./mvnw spring-boot:run
    ```
2. **Test Fleet Delivery:** 
    ```
    curl localhost:8312/fleet/v1/flights/test
    ```
3. **Result:** 

    This is availabe drone id: 
    Avaliable drones in inventory! 
    [{"id":"1","name":"Drone1","type":"Type1","capacity":120},{"id":"2","name":"Drone2","type":"Type2","capacity":220},{"id":"3","name":"Drone3","type":"Type3","capacity":320},{"id":"4","name":"Drone4","type":"Type4","capacity":420},{"id":"5","name":"Drone5","type":"Type5","capacity":520}]     


### 4. Drone Unit

#### Description

The Drone Unit component represents an individual drone and responds to assignment requests.

#### Instructions

1. **Start Drone Unit:** 
    ```
    ./mvnw spring-boot:run
    ```
2. **Test Drone Unit:** 
    ```
    curl localhost:8484/drone/v1/assignments
    ```
3. **Result:** 

  
    The drone is responding 

### 5. Monitoring

#### Description

The Monitoring component provides monitoring capabilities for the entire system.

#### Instructions

1. **Start Monitoring:** 
    ```
    ./mvnw spring-boot:run
    ```
2. **Test Monitoring:** 
    ```
    curl http://localhost:8082/monitoring/v1/monitordelivery
    ```
3. **Result:** 

    Monitor service is monitoring the delivering orders

    Orders : PizzaOrder{orderId=1, pizzaType='mini pizza', orderTime=2023-12-12 11:46:25, deliveryAddress='street 3a'}

    Pizza order package is done!  

    Now the free Drone device is ready for the delivery!

    Assigned Drone ID 2 to Pizza Order ID 5

    The drone is responding

    This order has been delivered successfully

### 6. activemq

 ```
cd bin
./activemq start
sudo lsof -i -P -n | grep LISTEN

Example: http://127.0.0.1:8161/admin/queues.jsp
 ```

### 7. Pizzastore Websites

#### Description

Access the Pizzastore websites for ordering and management.

#### URLs

- **Order Page:** http://localhost:8081/order.html
- **Drone Page:** http://localhost:8080/inventory.html

