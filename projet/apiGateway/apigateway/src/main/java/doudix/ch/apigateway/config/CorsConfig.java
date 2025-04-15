package doudix.ch.apigateway.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.*;

/**
 * Classe de configuration CORS (Cross-Origin Resource Sharing).
 * Permet de définir les règles d'accès entre le frontend (ex. HTML/JS) et le backend (API Gateway).
 */
@Configuration // Indique à Spring que cette classe contient une configuration
public class CorsConfig {

    /**
     * Bean qui configure les règles CORS pour l'application.
     * Cela permet à ton frontend (qui tourne sur http://localhost:5500) d'accéder aux endpoints du backend.
     */
    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                // Déclare que tous les endpoints sous /api/gateway/** acceptent les requêtes CORS
                registry.addMapping("/api/gateway/**")
                        .allowedOrigins("http://localhost:5500") // Autorise uniquement les requêtes venant de ce frontend
                        .allowedMethods("*")                     // Autorise toutes les méthodes HTTP (GET, POST, DELETE, etc.)
                        .allowCredentials(true);                 // Autorise l'envoi de cookies/session
            }
        };
    }
}
