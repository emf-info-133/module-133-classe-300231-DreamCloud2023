package doudix.ch.apigateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Classe principale qui démarre l'application API Gateway
 * 
 * Cette classe lance un microservice Spring Boot, qui agit comme
 * point d'entrée entre le frontend et les autres services REST.
 * Elle utilise l’annotation @SpringBootApplication qui combine :
 * - @Configuration : permet de définir des beans dans le contexte Spring
 * - @EnableAutoConfiguration : active la configuration automatique de Spring Boot
 * - @ComponentScan : scanne le package et sous-packages pour détecter les composants (controllers, services, etc.)
 */
@SpringBootApplication
public class ApigatewayApplication {

    // Méthode main → point de départ de l'application
    public static void main(String[] args) {
        SpringApplication.run(ApigatewayApplication.class, args);
    }
}
