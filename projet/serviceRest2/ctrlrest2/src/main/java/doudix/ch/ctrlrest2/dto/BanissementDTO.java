package doudix.ch.ctrlrest2.dto;

import java.time.LocalDateTime;

/**
 * Classe DTO (Data Transfer Object) pour Banissement
 * Sert à transporter des données entre le backend et le frontend (ou d'autres services)
 * Ne contient que les données utiles, sans logique métier
 */
public class BanissementDTO {

    // Identifiant du bannissement
    private Long id;

    // Nom de l'utilisateur banni
    private String username;

    // Remarque associée au bannissement
    private String remarque;

    // Date et heure du bannissement
    private LocalDateTime dateBannissement;

    // Constructeur vide (obligatoire pour la sérialisation / désérialisation JSON)
    public BanissementDTO() {}

    // Constructeur complet (pratique pour renvoyer les infos complètes)
    public BanissementDTO(Long id, String username, String remarque, LocalDateTime dateBannissement) {
        this.id = id;
        this.username = username;
        this.remarque = remarque;
        this.dateBannissement = dateBannissement;
    }

    //Getters et Setters (permettent de lire/modifier les champs)

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
