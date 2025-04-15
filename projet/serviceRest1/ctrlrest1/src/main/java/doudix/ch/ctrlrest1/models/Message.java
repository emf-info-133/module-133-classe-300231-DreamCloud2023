package doudix.ch.ctrlrest1.models;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import com.fasterxml.jackson.annotation.JsonBackReference;

/**
 * Entité JPA représentant un message posté dans une discussion.
 * Chaque message est lié à un post.
 */
@Entity
public class Message {

    // Identifiant unique du message (clé primaire)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Contenu du message (champ obligatoire, de type texte long)
    @Column(nullable = false, columnDefinition = "TEXT")
    private String text;

    // ID de l'utilisateur qui a créé le message (pas de relation directe avec l'entité User)
    @Column(nullable = false, name = "creator_id")
    private Long creatorId;

    // Lien vers le post auquel le message est associé (Many-to-One = plusieurs messages peuvent appartenir à un post)
    @ManyToOne
    @JoinColumn(name = "post_id", referencedColumnName = "id", nullable = false)
    @JsonBackReference // Empêche la boucle infinie lors de la sérialisation JSON (post -> messages -> post -> ...)
    private Post post;

    // Date de création automatique (non modifiable après insertion)
    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt = LocalDateTime.now();

    // --- Getters et Setters ---

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Long getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(Long creatorId) {
        this.creatorId = creatorId;
    }

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
}
