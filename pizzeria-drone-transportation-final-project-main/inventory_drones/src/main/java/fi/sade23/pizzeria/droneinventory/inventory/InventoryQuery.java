package fi.sade23.pizzeria.droneinventory.inventory;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class InventoryQuery {

    @JmsListener(destination = "droneInventoryQueue")
public void processInventoryMessage(String message) {
    // logic to process the received message
    System.out.println("Received message from drone-inventory-queue: " + message);

    }
}
