package doudix.ch.apigateway.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.*;

@Configuration
public class CorsConfig {

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")
                    .allowedOrigins("http://localhost:8080") // Liste explicite des origines autorisées
                    .allowedMethods("GET", "POST", "PUT", "DELETE")
                    .allowCredentials(true);  // Autorisation des credentials
            }
        };
    }
}
