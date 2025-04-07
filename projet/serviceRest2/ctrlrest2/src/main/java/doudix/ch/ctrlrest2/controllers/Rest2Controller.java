package doudix.ch.ctrlrest2.controllers;

import org.springframework.web.bind.annotation.*;
import doudix.ch.ctrlrest2.services.Rest2Service;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;

import doudix.ch.ctrlrest2.dto.BanissementDTO;
import doudix.ch.ctrlrest2.models.Banissement;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/rest2")
public class Rest2Controller {

    private final Rest2Service service;

    @Autowired
    public Rest2Controller(Rest2Service service) {
        this.service = service;
    }

    // Bannir un utilisateur
    // Bannir un utilisateur
    @PostMapping("/banUser")
    public ResponseEntity<BanissementDTO> banUser(@RequestBody Banissement banissement) {
        try {
            // On appelle le service pour bannir l'utilisateur
            Banissement ban = service.banUser(banissement);

            // Création du DTO de réponse à partir de l'objet Banissement
            BanissementDTO response = new BanissementDTO(
                    ban.getId(),
                    ban.getUsername(),
                    ban.getRemarque(),
                    ban.getDateBannissement());

            return ResponseEntity.ok(response);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    // Récupérer la liste des bannissements
    @GetMapping("/getBans")
    public ResponseEntity<List<BanissementDTO>> getAllBans() {
        List<Banissement> bans = service.getAllBans();
        List<BanissementDTO> dtos = bans.stream()
                .map(b -> new BanissementDTO(b.getId(), b.getUsername(), b.getRemarque(), b.getDateBannissement()))
                .toList();

        return ResponseEntity.ok(dtos);
    }
}
