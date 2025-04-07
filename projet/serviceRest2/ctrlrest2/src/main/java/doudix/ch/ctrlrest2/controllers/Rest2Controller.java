package doudix.ch.ctrlrest2.controllers;

import org.springframework.web.bind.annotation.*;
import doudix.ch.ctrlrest2.services.Rest2Service;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;

import doudix.ch.ctrlrest2.dto.UserDTO;
import doudix.ch.ctrlrest2.models.Banissement;
import doudix.ch.ctrlrest2.dto.BanissementDTO;
import doudix.ch.ctrlrest2.dto.PostDTO;

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
    @PostMapping("/banUser")
    public ResponseEntity<BanissementDTO> banUser(@RequestBody BanissementDTO dto) {
        try {
            Banissement ban = service.banUser(dto.getUsername(), dto.getRemarque());

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
