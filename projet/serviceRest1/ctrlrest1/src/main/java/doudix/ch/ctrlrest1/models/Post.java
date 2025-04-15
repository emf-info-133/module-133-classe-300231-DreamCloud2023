package doudix.ch.ctrlrest1.models;

import java.math.BigInteger;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.*;

/**
 * Représente un Post dans l'application.
 * Chaque post contient un auteur (creatorId), un titre, une description, une image, une catégorie, une couleur,
 * et peut contenir plusieurs messages/commentaires.
 */
@Entity
public class Post {

    // Clé primaire (identifiant unique du post)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long postId;

    // ID de l'utilisateur qui a créé ce post
    private BigInteger creatorId;

    // URL de l’image du post (peut être une image externe ou base64), stockée en LONGTEXT
    @Column(name = "imageUrl", columnDefinition = "LONGTEXT")
    private String imageUrl;

    private String title;          // Titre du post
    private String description;    // Description du post

    @Column(name = "category")
    private String category;       // Catégorie du post (ex : "news", "humour", etc.)

    @Column(name = "couleur")
    private String couleur;        // Couleur utilisée pour styliser le post (ex: "green", "blue", etc.)

    // Liste des messages liés à ce post (relation 1-to-many)
    @OneToMany(mappedBy = "post") // "mappedBy" indique que l’attribut "post" dans Message est le côté propriétaire
    @JsonManagedReference          // Permet d’éviter une boucle infinie lors de la sérialisation JSON avec les messages
    private List<Message> messages;

    // -------------------- Getters & Setters --------------------

    // On précise le nom dans le JSON pour correspondre à l’attribut utilisé côté frontend (postId)
    @JsonProperty("postId")
    public Long getPostId() {
        return postId;
    }

    public void setPostId(Long postId) {
        this.postId = postId;
    }

    public BigInteger getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(BigInteger creatorId) {
        this.creatorId = creatorId;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getCouleur() {
        return couleur;
    }

    public void setCouleur(String couleur) {
        this.couleur = couleur;
    }

    public List<Message> getMessages() {
        return messages;
    }

    public void setMessages(List<Message> messages) {
        this.messages = messages;
    }
}
