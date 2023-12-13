package fi.sade23.pizzeria.monitoringservice;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

@Service
public class MonitoringService {

    private Map<Integer, DroneFlight> droneFlights;

    public MonitoringService() {
        this.droneFlights = new HashMap<>();
    }

    public void updateDroneFlight(int orderId, DroneFlight droneFlight) {
        droneFlight.setLastUpdateTime(LocalDateTime.now());
        droneFlights.put(orderId, droneFlight);
    }

    public DroneFlight getDroneFlight(int orderId) {
        return droneFlights.get(orderId);
    }
}
