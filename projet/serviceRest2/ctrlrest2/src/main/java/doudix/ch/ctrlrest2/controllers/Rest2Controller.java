package doudix.ch.ctrlrest2.controllers;

import org.springframework.web.bind.annotation.*;
import doudix.ch.ctrlrest2.services.Rest2Service;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@RequestMapping("/api/rest2")
public class Rest2Controller {

    private static final Logger logger = LoggerFactory.getLogger(Rest2Controller.class);

    private final Rest2Service service;

    @Autowired
    public Rest2Controller(Rest2Service service) {
        this.service = service;
    }

    @DeleteMapping("/deletePosts")
    public ResponseEntity<String> deletePosts(@RequestBody List<Long> postIds) {
        try {
            // Appeler la méthode du service pour supprimer les messages et posts
            service.deleteMessagesAndPosts(postIds);
            return ResponseEntity.ok("Posts and associated messages deleted successfully.");
        } catch (Exception e) {
            // En cas d'erreur, renvoyer une réponse 500 avec un message d'erreur
            return ResponseEntity.status(500).body("An error occurred while deleting posts and messages.");
        }
    }

    // Supprimer un User par son nom
    @DeleteMapping("/deleteUserByName/{name}")
    public ResponseEntity<String> deleteUser(@PathVariable String name) {
        logger.info("Attempting to delete user with username: {}", name);
        boolean isDeleted = service.deleteUserByName(name);
        if (isDeleted) {
            logger.info("User with username '{}' deleted successfully.", name);
            return ResponseEntity.ok("User deleted");
        } else {
            logger.warn("User with username '{}' not found.", name);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
        }
    }
}
