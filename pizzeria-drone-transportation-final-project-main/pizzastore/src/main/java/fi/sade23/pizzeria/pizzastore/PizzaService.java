// PizzaService.java
package fi.sade23.pizzeria.pizzastore;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PizzaService {

    private final List<PizzaOrder> pizzaOrders = new ArrayList<>();
    private final FleetPizzaService fleetPizzaService;

    @Autowired
    public PizzaService(FleetPizzaService fleetPizzaService) {
        this.fleetPizzaService = fleetPizzaService;
    }
    @Autowired
    private JmsTemplate jmsTemplate;

    public String placeOrder(PizzaOrder pizzaOrder) {
        pizzaOrders.add(pizzaOrder);
        String coordinationResult = fleetPizzaService.coordinatePizzaDelivery(String.valueOf(pizzaOrder.getOrderId()));
    
        // Send order to the JMS queue
        jmsTemplate.convertAndSend("pizza-order-queue", pizzaOrder);
    
        return "Order placed successfully! " + coordinationResult;
    }
    

    public PizzaOrder getOrder(int orderId) {
        return pizzaOrders.stream()
                .filter(order -> order.getOrderId() == orderId)
                .findFirst()
                .orElse(null);
    }

    public List<PizzaOrder> getAllOrders() {
        return pizzaOrders;
    }

    public String updateOrder(int orderId, PizzaOrder updatedOrder) {
        PizzaOrder existingOrder = pizzaOrders.stream()
                .filter(order -> order.getOrderId() == orderId)
                .findFirst()
                .orElse(null);

        if (existingOrder != null) {
            existingOrder.setPizzaType(updatedOrder.getPizzaType());
            existingOrder.setDeliveryAddress(updatedOrder.getDeliveryAddress());
            return "Order updated successfully!";
        } else {
            return "Order not found or could not be updated.";
        }
    }

    public boolean deleteOrder(int orderId) {
        PizzaOrder orderToRemove = pizzaOrders.stream()
                .filter(order -> order.getOrderId() == orderId)
                .findFirst()
                .orElse(null);

        if (orderToRemove != null) {
            pizzaOrders.remove(orderToRemove);
            return true;
        } else {
            return false;
        }
    }
}
