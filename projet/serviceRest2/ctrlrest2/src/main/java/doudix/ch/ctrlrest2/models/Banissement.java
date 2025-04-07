package doudix.ch.ctrlrest2.models;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "Banissements")
public class Banissement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Le nom d'utilisateur banni
    @Column(nullable = false)
    private String username;

    // Motif ou remarque du bannissement
    @Column(nullable = false, columnDefinition = "TEXT")
    private String remarque;

    // Date de bannissement (auto-générée à la création)
    @Column(nullable = false, updatable = false)
    private LocalDateTime dateBannissement = LocalDateTime.now();

    // Constructeurs
    public Banissement() {}

    public Banissement(String username, String remarque) {
        this.username = username;
        this.remarque = remarque;
    }

    // Getters & Setters
    public Long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getRemarque() {
        return remarque;
    }

    public void setRemarque(String remarque) {
        this.remarque = remarque;
    }

    public LocalDateTime getDateBannissement() {
        return dateBannissement;
    }
}
