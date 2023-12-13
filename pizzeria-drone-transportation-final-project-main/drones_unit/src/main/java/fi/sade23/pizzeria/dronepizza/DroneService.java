// DroneService.java
package fi.sade23.pizzeria.dronepizza;


import org.springframework.stereotype.Component;

@Component
public class DroneService {
    public String droneTest(){
        String response = "The drone is responding";
        return response;
    }
    
}
