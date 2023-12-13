package fi.sade23.pizzeria.monitoringservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/monitoring/v1")
public class MonitoringController {

    private final MonitoringService monitoringService;
    private final RestTemplate restTemplate;

    @Autowired
    public MonitoringController(MonitoringService monitoringService, RestTemplate restTemplate) {
        this.monitoringService = monitoringService;
        this.restTemplate = restTemplate;
    }

    @PostMapping("/monitordelivery")
    public String monitordelivery() {
        StringBuilder response = new StringBuilder();
        response.append("Monitoring service is monitoring the delivering orders\n");
        response.append(restTemplate.getForObject("http://localhost:8081/pizzastore/v1/orders/assign/1", String.class));
        response.append("\nThis order has been delivered successfully");
        return response.toString();
    }
}
