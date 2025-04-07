package doudix.ch.ctrlrest2.dto;

import java.time.LocalDateTime;

public class BanissementDTO {

    private Long id;
    private String username;
    private String remarque;
    private LocalDateTime dateBannissement;

    public BanissementDTO() {}

    public BanissementDTO(Long id, String username, String remarque, LocalDateTime dateBannissement) {
        this.id = id;
        this.username = username;
        this.remarque = remarque;
        this.dateBannissement = dateBannissement;
    }

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
}
