package doudix.ch.apigateway.models;

import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

public class ApigatewayApplication {
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

}
