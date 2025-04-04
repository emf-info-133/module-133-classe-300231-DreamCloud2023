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

    // Supprimer des posts
    @DeleteMapping("/deletePosts")
    public ResponseEntity<String> deletePosts(@RequestBody List<PostDTO> postDtos) {
        try {
            // Extraction des ids des posts à supprimer depuis le DTO
            List<Long> postIds = postDtos.stream()
                                         .map(PostDTO::getId)
                                         .toList();
            service.deleteMessagesAndPosts(postIds);
            return ResponseEntity.ok("Posts and associated messages deleted successfully.");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("An error occurred while deleting posts and messages.");
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
