package doudix.ch.ctrlrest1.models;

import jakarta.persistence.*;
import java.util.List;

/**
 * Représente un utilisateur dans l'application.
 * Cette entité est liée à la table "Users" dans la base de données.
 */
@Entity
@Table(name = "Users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;  // Identifiant unique généré automatiquement

    private String username;  // Nom d'utilisateur
    private String password;  // Mot de passe de l'utilisateur

    @Column(name = "is_admin")
    private boolean isAdmin;  // Statut administrateur (true ou false)

    @OneToMany(mappedBy = "creatorId", cascade = CascadeType.ALL)
    private List<Message> messages;  // Liste des messages écrits par l'utilisateur

    // Constructeur sans paramètre requis par JPA
    public User() {}

    // Constructeur personnalisé pour initialiser les champs
    public User(String username, String password, boolean isAdmin) {
        this.username = username;
        this.password = password;
        this.isAdmin = isAdmin;
    }

    // -------------------- Getters --------------------

    public Long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public List<Message> getMessages() {
        return messages;
    }

    // -------------------- Setters --------------------

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setIsAdmin(boolean isAdmin) {
        this.isAdmin = isAdmin;
    }

    public void setMessages(List<Message> messages) {
        this.messages = messages;
    }

    // Méthode toString utile pour afficher les infos d'un utilisateur dans la console
    @Override
    public String toString() {
        return "User{id=" + id + ", username='" + username + "', password='" + password + "'}";
    }
}
