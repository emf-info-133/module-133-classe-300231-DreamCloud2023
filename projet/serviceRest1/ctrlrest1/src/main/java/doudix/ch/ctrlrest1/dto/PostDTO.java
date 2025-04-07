package doudix.ch.ctrlrest1.dto;

import java.math.BigInteger;
import java.util.List;

public class PostDTO {
    private Long id;
    private BigInteger creatorId;
    private String description;
    private String imageUrl;
    private String title;
    private List<MessageDTO> messages;
    private String category;
    private String couleur;

    // Constructeurs
    public PostDTO() {}

    public PostDTO(Long id, BigInteger creatorId, String description, String imageUrl, String title, List<MessageDTO> messages, String category, String couleur) {
        this.id = id;
        this.creatorId = creatorId;
        this.description = description;
        this.imageUrl = imageUrl;
        this.title = title;
        this.messages = messages;
        this.category = category;
        this.couleur = couleur;
    }

    // Getters et Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public BigInteger getCreatorId() { return creatorId; }
    public void setCreatorId(BigInteger creatorId) { this.creatorId = creatorId; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public String getImageUrl() { return imageUrl; }
    public void setImageUrl(String imageUrl) { this.imageUrl = imageUrl; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public List<MessageDTO> getMessages() { return messages; }
    public void setMessages(List<MessageDTO> messages) { this.messages = messages; }

    public String getCategorie() { return category; }
    public void setCategorie(String category) { this.category = category; }

    public String getCouleur() { return couleur; }
    public void setCouleur(String couleur) { this.couleur = couleur; }

}
