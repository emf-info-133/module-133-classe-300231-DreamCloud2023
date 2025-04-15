package doudix.ch.ctrlrest1.dto;

/**
 * DTO (Data Transfer Object) représentant un message échangé entre frontend et backend.
 * Il transporte les données nécessaires sans exposer directement l'entité JPA.
 */
public class MessageDTO {

    // Identifiant unique du message
    private Long id;

    // Contenu textuel du message
    private String text;

    // ID de l'utilisateur qui a écrit le message
    private Long creatorId;

    // Nom d'utilisateur de l'auteur du message (ajouté pour affichage dans le front)
    private String creatorUsername;

    // ID du post auquel ce message est rattaché
    private Long postId;

    // Constructeur vide requis pour la désérialisation JSON
    public MessageDTO() {}

    // Constructeur principal
    public MessageDTO(Long id, String text, Long creatorId, String creatorUsername, Long postId) {
        this.id = id;
        this.text = text;
        this.creatorId = creatorId;
        this.creatorUsername = creatorUsername;
        this.postId = postId;
    }

    // --- Getters et Setters ---

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getText() { return text; }
    public void setText(String text) { this.text = text; }

    public Long getCreatorId() { return creatorId; }
    public void setCreatorId(Long creatorId) { this.creatorId = creatorId; }

    public String getCreatorUsername() { return creatorUsername; }
    public void setCreatorUsername(String creatorUsername) { this.creatorUsername = creatorUsername; }

    public Long getPostId() { return postId; }
    public void setPostId(Long postId) { this.postId = postId; }
}
