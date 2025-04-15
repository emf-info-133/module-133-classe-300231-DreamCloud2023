package doudix.ch.apigateway.dto;

import java.math.BigInteger;

/**
 * PostDTO (Data Transfer Object)
 * 
 * Ce DTO permet de transporter les données d'un post entre le frontend, le gateway
 * et le microservice REST1. Il sert à afficher ou créer un post de manière simple,
 * sans exposer l'entité réelle.
 */
public class PostDTO {

    // Identifiant unique du post
    private Long id;

    // ID de l'utilisateur ayant créé le post (type BigInteger pour correspondre à la base)
    private BigInteger creatorId;

    // Nom de l'utilisateur (texte) ayant créé le post (utilisé côté frontend pour affichage)
    private String creatorUsername;

    // URL de l'image liée au post
    private String imageUrl;

    // Titre du post
    private String title;

    // Description du post
    private String description;

    // Catégorie associée au post (ex: "jeux", "cinéma"...)
    private String category;

    // Couleur du post (utilisée pour le style visuel)
    private String couleur;

    // ------------------ Constructeurs ------------------

    // Constructeur vide (obligatoire pour frameworks de sérialisation/désérialisation JSON)
    public PostDTO() {}

    // Constructeur complet
    public PostDTO(Long id, BigInteger creatorId, String creatorUsername,
                   String imageUrl, String title, String description,
                   String category, String couleur) {
        this.id = id;
        this.creatorId = creatorId;
        this.creatorUsername = creatorUsername;
        this.imageUrl = imageUrl;
        this.title = title;
        this.description = description;
        this.category = category;
        this.couleur = couleur;
    }

    // ------------------ Getters et Setters ------------------

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    // Alias pour le champ id, utilisé spécifiquement comme identifiant "postId"
    public Long getPostId() { return id; }
    public void setPostId(Long postId) { this.id = postId; }

    public BigInteger getCreatorId() { return creatorId; }
    public void setCreatorId(BigInteger creatorId) { this.creatorId = creatorId; }

    public String getCreatorUsername() { return creatorUsername; }
    public void setCreatorUsername(String creatorUsername) { this.creatorUsername = creatorUsername; }

    public String getImageUrl() { return imageUrl; }
    public void setImageUrl(String imageUrl) { this.imageUrl = imageUrl; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }

    public String getCouleur() { return couleur; }
    public void setCouleur(String couleur) { this.couleur = couleur; }
}
