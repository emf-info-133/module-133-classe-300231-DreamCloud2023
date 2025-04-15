package doudix.ch.ctrlrest1.dto;

import java.math.BigInteger;

/**
 * DTO (Data Transfer Object) représentant un post (publication).
 * Il permet de transférer les données d'un post entre le backend et le frontend
 * sans exposer directement l'entité `Post` de la base de données.
 */
public class PostDTO {

    // Identifiant unique du post
    private Long id;

    // Identifiant de l'utilisateur qui a créé le post
    private BigInteger creatorId;

    // Nom de l'utilisateur créateur (ajouté pour affichage côté client)
    private String creatorUsername;

    // URL de l'image associée au post
    private String imageUrl;

    // Titre du post
    private String title;

    // Description du post
    private String description;

    // Catégorie du post (ex. : Actu, Humour, Info)
    private String category;

    // Couleur associée au post (pour affichage visuel)
    private String couleur;

    // Constructeur vide requis par Spring/JSON
    public PostDTO() {}

    // Constructeur complet
    public PostDTO(Long id, BigInteger creatorId, String creatorUsername, String imageUrl,
                   String title, String description, String category, String couleur) {
        this.id = id;
        this.creatorId = creatorId;
        this.creatorUsername = creatorUsername;
        this.imageUrl = imageUrl;
        this.title = title;
        this.description = description;
        this.category = category;
        this.couleur = couleur;
    }

    // --- Getters et Setters ---

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    // Méthode alternative pour compatibilité avec d'autres noms (postId)
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
