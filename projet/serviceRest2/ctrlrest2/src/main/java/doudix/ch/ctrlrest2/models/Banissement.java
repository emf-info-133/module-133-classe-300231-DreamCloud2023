package doudix.ch.ctrlrest2.models;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import doudix.ch.ctrlrest2.dto.*;

@Entity
@Table(name = "Banissements")
public class Banissement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String remarque;

    @Column(nullable = false)
    private LocalDateTime dateBannissement;

    public Banissement() {}

    public Banissement(String username, String remarque) {
        this.username = username;
        this.remarque = remarque;
        this.dateBannissement = LocalDateTime.now();
    }

    // Getters & Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public void setDateBannissement(LocalDateTime dateBannissement) {
        this.dateBannissement = dateBannissement;
    }

    // Méthode de conversion en DTO
    public BanissementDTO toDTO() {
        return new BanissementDTO(this.id, this.username, this.remarque, this.dateBannissement);
    }

    // Méthode pour créer un Banissement à partir d'un DTO
    public static Banissement fromDTO(BanissementDTO dto) {
        Banissement banissement = new Banissement(dto.getUsername(), dto.getRemarque());
        banissement.setId(dto.getId());
        banissement.setDateBannissement(dto.getDateBannissement());
        return banissement;
    }
}
