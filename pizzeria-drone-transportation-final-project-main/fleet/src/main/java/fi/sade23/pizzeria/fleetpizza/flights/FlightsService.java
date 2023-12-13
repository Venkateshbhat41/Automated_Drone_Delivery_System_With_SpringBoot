package fi.sade23.pizzeria.fleetpizza.flights;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class FlightsService {
    @Autowired
    private RestTemplate restTemplate;

    public  String pingDroneUnit(){
        String response = "";

        // take rest connection with one of the drone from Dronevers
        //localhost:8484/drone/v1/assignments
        
        response += "This is availabe drone id: \n ";
        response += "Avaliable drones in inventory! \n ";
        response += restTemplate.getForObject("http://localhost:8080/control/drones",String.class);
        return response;
    }
}
