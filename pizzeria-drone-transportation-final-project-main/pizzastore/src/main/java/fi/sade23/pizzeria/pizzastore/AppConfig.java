// AppConfig.java
package fi.sade23.pizzeria.pizzastore;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import jakarta.jms.ConnectionFactory;

@Configuration
public class AppConfig {

    @Bean
    public FleetPizzaService fleetPizzaService(ConnectionFactory connectionFactory, RestTemplate restTemplate) {
        return new FleetPizzaService(connectionFactory, restTemplate);
    }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
