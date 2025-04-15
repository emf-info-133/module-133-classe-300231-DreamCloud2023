package doudix.ch.apigateway.dto;

/**
 * UserDTO (Data Transfer Object)
 *
 * Ce DTO est utilisé pour transférer les informations des utilisateurs
 * entre le frontend, l'API Gateway et le microservice REST1.
 * Il ne contient que les données nécessaires à l'identification et aux droits.
 */
public class UserDTO {

    // Identifiant unique de l'utilisateur (clé primaire)
    private Long id;

    // Nom d'utilisateur (login ou pseudonyme)
    private String username;

    // Mot de passe (en texte ici, mais normalement devrait être crypté)
    private String password;

    // Indique si l'utilisateur a les droits d'administration
    private boolean isAdmin;

    // ------------------ Constructeurs ------------------

    // Constructeur vide requis pour la sérialisation JSON
    public UserDTO() {}

    // Constructeur complet
    public UserDTO(Long id, String username, String password, boolean isAdmin) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.isAdmin = isAdmin;
    }

    // ------------------ Getters et Setters ------------------

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public boolean isAdmin() { return isAdmin; }
    public void setAdmin(boolean isAdmin) { this.isAdmin = isAdmin; }
}
