package doudix.ch.ctrlrest1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Classe principale de l'application Spring Boot REST1.
 * Elle sert de point d'entrée pour démarrer tout le backend REST1.
 *
 * L'annotation @SpringBootApplication indique que :
 * - C'est une application Spring Boot.
 * - Elle active la configuration automatique (auto-configuration).
 * - Elle recherche automatiquement les composants dans le même package et sous-packages.
 */
@SpringBootApplication
public class Ctrlrest1Application {

    /**
     * Méthode main : point d’entrée de l’application.
     * Elle démarre le serveur Spring (Tomcat intégré), initialise le contexte,
     * et lance tous les composants (controllers, services, repositories, etc.).
     */
    public static void main(String[] args) {
        SpringApplication.run(Ctrlrest1Application.class, args);
    }

}
