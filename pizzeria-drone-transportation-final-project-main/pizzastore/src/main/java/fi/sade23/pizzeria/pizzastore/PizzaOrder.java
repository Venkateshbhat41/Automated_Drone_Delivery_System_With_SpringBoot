// PizzaOrder.java
package fi.sade23.pizzeria.pizzastore;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class PizzaOrder implements Serializable {

    private static int orderIdCounter = 0;

    private int orderId;
    private String pizzaType;
    private LocalDateTime orderTime;
    private String deliveryAddress;

    public PizzaOrder(String pizzaType, String deliveryAddress) {
        this.orderId = getNextOrderId();
        this.pizzaType = pizzaType;
        this.orderTime = LocalDateTime.now();
        this.deliveryAddress = deliveryAddress;
    }

    private synchronized int getNextOrderId() {
        return ++orderIdCounter;
    }

    public int getOrderId() {
        return orderId;
    }

    public String getPizzaType() {
        return pizzaType;
    }

    public void setPizzaType(String pizzaType) {
        this.pizzaType = pizzaType;
    }

    public LocalDateTime getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(LocalDateTime orderTime) {
        this.orderTime = orderTime;
    }

    public String getDeliveryAddress() {
        return deliveryAddress;
    }

    public void setDeliveryAddress(String deliveryAddress) {
        this.deliveryAddress = deliveryAddress;
    }
    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formattedOrderTime = orderTime.format(formatter);

        return "PizzaOrder{" +
                "orderId=" + orderId +
                ", pizzaType='" + pizzaType + '\'' +
                ", orderTime=" + formattedOrderTime +
                ", deliveryAddress='" + deliveryAddress + '\'' +
                '}';
    }
}
