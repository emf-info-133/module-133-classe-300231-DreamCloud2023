package doudix.ch.ctrlrest1.dto;

/**
 * DTO (Data Transfer Object) représentant un utilisateur.
 * Utilisé pour transférer les données d'un utilisateur entre le backend et le frontend
 * sans exposer directement l'entité `User`.
 */
public class UserDTO {

    // Identifiant unique de l'utilisateur
    private Long id;

    // Nom d'utilisateur (identifiant de connexion)
    private String username;

    // Mot de passe de l'utilisateur
    private String password;

    // Statut administrateur : true si l'utilisateur est un admin
    private boolean isAdmin;

    // --- Constructeurs ---

    // Constructeur vide (nécessaire pour la sérialisation JSON / Spring)
    public UserDTO() {}

    // Constructeur complet avec tous les champs
    public UserDTO(Long id, String username, String password, boolean isAdmin) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.isAdmin = isAdmin;
    }

    // --- Getters et Setters ---

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public boolean isAdmin() { return isAdmin; }
    public void setIsAdmin(boolean isAdmin) { this.isAdmin = isAdmin; }
}
