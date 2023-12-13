// PizzaController.java
package fi.sade23.pizzeria.pizzastore;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "pizzastore/v1/orders")
public class PizzaController {

    @Autowired
    private PizzaService pizzaService;

    @Autowired
    private FleetPizzaService fleetPizzaService;


    @PostMapping("/place")
    public String placeOrder(@RequestParam String pizzaType, @RequestParam String deliveryAddress) {
        PizzaOrder pizzaOrder = new PizzaOrder(pizzaType, deliveryAddress);
        return pizzaService.placeOrder(pizzaOrder);
    }

    @GetMapping(path = "/{orderId}")
    public PizzaOrder getOrder(@PathVariable int orderId) {
        return pizzaService.getOrder(orderId);
    }

    @GetMapping
    public List<PizzaOrder> getAllOrders() {
        return pizzaService.getAllOrders();
    }

    @PutMapping("/{orderId}")
    public String updateOrder(@PathVariable int orderId, @RequestBody PizzaOrder updatedOrder) {
        return pizzaService.updateOrder(orderId, updatedOrder);
    }

    @DeleteMapping("/{orderId}")
    public String deleteOrder(@PathVariable int orderId) {
        boolean deleted = pizzaService.deleteOrder(orderId);

        if (deleted) {
            return "Order deleted successfully!";
        } else {
            return "Order not found or could not be deleted.";
        }
    }

 
    @GetMapping("/assign/{Order_id}")
public String assigndrone(@PathVariable("Order_id") int orderId) {
    String response = "Orders : ";
    PizzaOrder order = pizzaService.getOrder(orderId);

    if (order != null) {
        response += order.toString(); 
        response += "\n";
        response += fleetPizzaService.connectfreedrone();
    } else {
        response = "Order not found.";
    }
    return response;
}

}


