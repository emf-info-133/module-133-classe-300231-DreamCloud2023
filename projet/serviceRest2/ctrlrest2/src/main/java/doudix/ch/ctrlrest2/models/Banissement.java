package doudix.ch.ctrlrest2.models;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import doudix.ch.ctrlrest2.dto.*;

/**
 * Classe entité : Banissement
 * Représente un utilisateur banni dans la base de données.
 * Annotée avec @Entity : c’est une table dans la base (nommée "Banissements").
 * Contient les infos : id, nom d'utilisateur, remarque, date du bannissement.
 */
@Entity
@Table(name = "Banissements") // Nom de la table dans la base de données
public class Banissement {

    // Identifiant unique (clé primaire), généré automatiquement
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Nom d'utilisateur banni (champ obligatoire)
    @Column(nullable = false)
    private String username;

    // Remarque associée au bannissement (texte long autorisé)
    @Column(nullable = false, columnDefinition = "TEXT")
    private String remarque;

    // Date du bannissement (générée automatiquement à la création)
    private LocalDateTime dateBannissement;

    // Constructeur vide requis par JPA
    public Banissement() {}

    // Constructeur avec paramètres (initialise aussi la date automatiquement)
    public Banissement(String username, String remarque) {
        this.username = username;
        this.remarque = remarque;
        this.dateBannissement = LocalDateTime.now();
    }

    // Getters et Setters : permettent d’accéder/modifier les données

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

    /**
     * Convertit cette entité Banissement en DTO (Data Transfer Object)
     * Sert à transmettre les données vers le frontend ou un autre service.
     */
    public BanissementDTO toDTO() {
        return new BanissementDTO(this.id, this.username, this.remarque, this.dateBannissement);
    }

    /**
     * Méthode statique pour créer une entité Banissement à partir d’un DTO
     * Sert à reconstruire un objet Banissement quand on reçoit un DTO.
     */
    public static Banissement fromDTO(BanissementDTO dto) {
        Banissement banissement = new Banissement(dto.getUsername(), dto.getRemarque());
        banissement.setId(dto.getId());
        banissement.setDateBannissement(dto.getDateBannissement());
        return banissement;
    }
}
