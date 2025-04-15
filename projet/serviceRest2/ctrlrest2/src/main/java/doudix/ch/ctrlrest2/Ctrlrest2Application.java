package doudix.ch.ctrlrest2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Point d'entrée principal de l'application Spring Boot.
 * Cette classe lance le microservice REST2 (gestion des bannissements).
 */
@SpringBootApplication
public class Ctrlrest2Application {

    /**
     * Méthode main appelée au démarrage.
     * Elle lance l'application avec la configuration Spring Boot.
     */
    public static void main(String[] args) {
        SpringApplication.run(Ctrlrest2Application.class, args);
    }

}
