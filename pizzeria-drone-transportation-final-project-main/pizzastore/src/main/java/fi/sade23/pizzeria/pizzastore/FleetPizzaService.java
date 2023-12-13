// FleetPizzaService.java
package fi.sade23.pizzeria.pizzastore;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.support.converter.MappingJackson2MessageConverter;
import org.springframework.jms.support.converter.MessageConverter;
import org.springframework.jms.support.converter.MessageType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import jakarta.jms.ConnectionFactory;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@Service
public class FleetPizzaService {

    private final RestTemplate restTemplate;
    private final JmsTemplate jmsTemplate;

    private static final Logger logger = LoggerFactory.getLogger(FleetPizzaService.class);

    @Autowired
    public FleetPizzaService(ConnectionFactory connectionFactory, RestTemplate restTemplate) {
        this.jmsTemplate = new JmsTemplate(connectionFactory);
        this.jmsTemplate.setMessageConverter(messageConverter());
        this.restTemplate = restTemplate; 
    }

    @JmsListener(destination = "pizzaOrderQueue") 
    public void receiveMessage(String pizzaOrderDetails) {
        // Process the received message
        logger.info("Received message: {}", pizzaOrderDetails);
    }

    public String coordinatePizzaDelivery(String pizzaOrderId) {
        String pizzaOrderDetails = "Details for pizza order " + pizzaOrderId;

        // Send message to the pizzaOrderQueue
        jmsTemplate.convertAndSend("pizzaOrderQueue", pizzaOrderDetails);

        return "Pizza delivery coordinated successfully!";
    }

    public String startDelivery(PizzaOrder pizzaOrder) {
        // logic to start delivery for the given pizza order
        // For example, send a message to a delivery queue
        jmsTemplate.convertAndSend("deliveryQueue", pizzaOrder);
        return "Delivery started for order ID: " + pizzaOrder.getOrderId();
    }

    // Configure JmsListenerContainerFactory
    public DefaultJmsListenerContainerFactory myFactory(ConnectionFactory connectionFactory,
                                                        MessageConverter messageConverter) {
        DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();
        factory.setConnectionFactory(connectionFactory);
        factory.setMessageConverter(messageConverter);
        return factory;
    }

    // Configure MessageConverter
    public MessageConverter messageConverter() {
        MappingJackson2MessageConverter converter = new MappingJackson2MessageConverter();
        converter.setTargetType(MessageType.TEXT);
        converter.setTypeIdPropertyName("_type");
        return converter;
    }


    public String connectfreedrone() {
    String response = "";
    response += "Pizza order package is done!  Now the free Drone device is ready for the delivery!\n";

    // Fetch the list of available drones from the external service
    List<Map<String, Object>> drones = Arrays.asList(restTemplate.getForObject("http://localhost:8080/control/drones", Map[].class));

    // Fetch the list of pending pizza orders from service
    List<Map<String, Object>> pendingOrders = Arrays.asList(restTemplate.getForObject("http://localhost:8081/pizzastore/v1/orders", Map[].class));

    // Randomly assign a drone to a pending pizza order
    if (pendingOrders != null && drones != null && !pendingOrders.isEmpty() && !drones.isEmpty()) {
        Random random = new Random();
        Map<String, Object> randomOrder = pendingOrders.get(random.nextInt(pendingOrders.size()));
        Map<String, Object> randomDrone = drones.get(random.nextInt(drones.size()));

        // update the pizza order with the assigned drone ID
        // and send  message to a queue or service indicating the assignment.
        response += "Assigned Drone ID " + randomDrone.get("id") + " to Pizza Order ID " + randomOrder.get("orderId") + "\n";
    } else {
        response += "No pending orders or available drones for assignment.\n";
    }

    // Include drone assignment details in the response
    response += restTemplate.getForObject("http://localhost:8484/drone/v1/assignments", String.class);

    return response;
}

}
