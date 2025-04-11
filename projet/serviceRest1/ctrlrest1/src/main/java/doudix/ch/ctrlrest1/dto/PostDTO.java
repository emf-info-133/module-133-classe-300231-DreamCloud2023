package doudix.ch.ctrlrest1.dto;

import java.math.BigInteger;

public class PostDTO {
    private Long id; // ← ici
    private BigInteger creatorId;
    private String imageUrl;
    private String title;
    private String description;
    private String category;
    private String couleur;

    public PostDTO() {}

    public PostDTO(Long id, BigInteger creatorId, String imageUrl, String title, String description, String category, String couleur) {
        this.id = id;
        this.creatorId = creatorId;
        this.imageUrl = imageUrl;
        this.title = title;
        this.description = description;
        this.category = category;
        this.couleur = couleur;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    // Getters & setters
    public Long getPostId() { return id; }
    public void setPostId(Long postId) { this.id = postId; }

    public BigInteger getCreatorId() { return creatorId; }
    public void setCreatorId(BigInteger creatorId) { this.creatorId = creatorId; }

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
