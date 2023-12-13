package fi.sade23.pizzeria.droneinventory.inventory;

import org.springframework.stereotype.Service;
import fi.sade23.pizzeria.droneinventory.Drone;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class InventoryService {

    private Map<String, Drone> droneHashMap = new HashMap<>();

    public InventoryService() {
        droneHashMap.put("1", new Drone("1", "Drone1", "Type1", 120));
        droneHashMap.put("2", new Drone("2", "Drone2", "Type2", 220));
        droneHashMap.put("3", new Drone("3", "Drone3", "Type3", 320));
        droneHashMap.put("4", new Drone("4", "Drone4", "Type4", 420));
        droneHashMap.put("5", new Drone("5", "Drone5", "Type5", 520));
    }

    public Drone getDrone(String id) {
        return droneHashMap.get(id);
    }

    public List<Drone> getAllDrones() {
        return new ArrayList<>(droneHashMap.values());
    }

    public void addDrone(String id, Drone drone) {
        droneHashMap.put(id, drone);
    }

    public void updateDrone(String id, Drone drone) {
        droneHashMap.computeIfPresent(id, (key, existingDrone) -> drone);
    }

    public void removeDrone(String id) {
        droneHashMap.remove(id);
    }
}
