package doudix.ch.apigateway.dto;

/**
 * DTO (Data Transfer Object) utilisé pour échanger les données liées à un message
 * entre le frontend, le Gateway et le microservice REST1.
 *
 * Il permet de structurer les informations d'un message posté dans une discussion.
 */
public class MessageDTO {

    // Identifiant unique du message
    private Long id;

    // Contenu textuel du message
    private String text;

    // ID de l'utilisateur qui a écrit le message
    private Long creatorId;

    // Nom d'utilisateur (en clair) du créateur du message (utile pour l'affichage frontend)
    private String creatorUsername;

    // ID du post auquel ce message est lié
    private Long postId;

    // ----------- Constructeurs -----------

    // Constructeur vide nécessaire pour les frameworks (ex. : lors de la désérialisation JSON)
    public MessageDTO() {}

    // Constructeur complet avec tous les champs
    public MessageDTO(Long id, String text, Long creatorId, String creatorUsername, Long postId) {
        this.id = id;
        this.text = text;
        this.creatorId = creatorId;
        this.creatorUsername = creatorUsername;
        this.postId = postId;
    }

    // ----------- Getters et Setters -----------

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
