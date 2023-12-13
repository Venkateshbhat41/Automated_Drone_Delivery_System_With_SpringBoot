package fi.sade23.pizzeria.dronepizza;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path="drone/v1/assignments")
public class DroneController {

    @Autowired
    private DroneService droneService;

    @GetMapping
    public String test () {
        return droneService.droneTest();
    }
    
}
