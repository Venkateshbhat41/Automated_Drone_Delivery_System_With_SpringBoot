package fi.sade23.pizzeria.droneinventory.inventory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import fi.sade23.pizzeria.droneinventory.Drone;


import java.util.List;

@RestController
public class InventoryController {

    @Autowired
    private InventoryService inventoryService;

    @GetMapping("/info")
    public String infoService() {
        return "Drone Inventory ready";
    }

    @GetMapping("/control/drones/{id}")
    public Drone getDrone(@PathVariable("id") String id) {
        return inventoryService.getDrone(id);
    }

    @DeleteMapping("/control/drones/{id}")
    public String deleteDrone(@PathVariable("id") String id) {
        inventoryService.removeDrone(id);
        return "Deleted the drone: " + id;
    }

    @GetMapping("/control/drones")
    public List<Drone> getDrones() {
        return inventoryService.getAllDrones();
    }

    @PostMapping("/control/drones")
    public String addDrone(@RequestBody Drone drone) {
        inventoryService.addDrone(drone.getId(), drone);
        return "Added new drone to our fleet: " + drone.toString();
    }

    @PutMapping("/control/drones")
    public String updateDrone(@RequestBody Drone drone) {
        inventoryService.updateDrone(drone.getId(), drone);
        return "Updated the drone: " + drone.toString();
    }
}
