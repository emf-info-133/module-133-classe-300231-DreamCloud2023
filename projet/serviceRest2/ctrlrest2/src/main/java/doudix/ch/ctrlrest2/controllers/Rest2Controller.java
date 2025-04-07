package doudix.ch.ctrlrest2.controllers;

import org.springframework.web.bind.annotation.*;
import doudix.ch.ctrlrest2.services.Rest2Service;
import org.springframework.http.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;

import doudix.ch.ctrlrest2.dto.UserDTO;
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

    // Méthode pour supprimer un post selon son ID dans le service cible
    @DeleteMapping("/deletePost")
    public ResponseEntity<String> deletePost(@RequestParam Long postId) {
        try {
        // Appeler la méthode pour supprimer le post selon son ID
            service.deleteMessagesAndPosts(List.of(postId));  // Supprimer le post et les messages associés
            return ResponseEntity.ok("Post and associated messages deleted successfully.");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("An error occurred while deleting the post and messages.");
        }
    }


    // Supprimer un utilisateur par nom
    @DeleteMapping("/deleteUserByName")
    public ResponseEntity<String> deleteUser(@RequestBody UserDTO userDto) {
        String username = userDto.getUsername();  // Utilisation du DTO pour récupérer le nom d'utilisateur
        boolean isDeleted = service.deleteUserByName(username);
        if (isDeleted) {
            return ResponseEntity.ok("User deleted");
        } else {
            return ResponseEntity.status(404).body("User not found");
        }
    }
}
