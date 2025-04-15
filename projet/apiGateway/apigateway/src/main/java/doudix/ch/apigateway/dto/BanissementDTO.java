package doudix.ch.apigateway.dto;

import java.time.LocalDateTime;

/**
 * Cette classe est un DTO (Data Transfer Object) utilisé pour transférer
 * les informations liées à un bannissement d'utilisateur entre le frontend,
 * l'API Gateway, et le microservice REST2.
 */
public class BanissementDTO {

    // Identifiant unique du bannissement (généré automatiquement)
    private Long id;

    // Nom d'utilisateur qui a été banni
    private String username;

    // Raison du bannissement (remarque saisie par l'admin)
    private String remarque;

    // Date à laquelle le bannissement a été effectué
    private LocalDateTime dateBannissement;

    // Constructeur par défaut (utilisé par les frameworks comme Spring ou Jackson)
    public BanissementDTO() {}

    // Constructeur simplifié (utilisé quand on crée un nouveau bannissement)
    public BanissementDTO(String username, String remarque) {
        this.username = username;
        this.remarque = remarque;
    }

    // ----- Getters et Setters -----

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getRemarque() {
        return remarque;
    }

    public void setRemarque(String remarque) {
        this.remarque = remarque;
    }

    public LocalDateTime getDateBannissement() {
        return dateBannissement;
    }

    public void setDateBannissement(LocalDateTime dateBannissement) {
        this.dateBannissement = dateBannissement;
    }
}
