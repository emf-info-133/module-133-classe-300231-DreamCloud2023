package doudix.ch.ctrlrest2.controllers;

import org.springframework.web.bind.annotation.*;
import doudix.ch.ctrlrest2.services.Rest2Service;
import org.springframework.http.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;

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
    public ResponseEntity<String> deletePosts(@RequestBody Map<String, List<Long>> postIdsMap) {
        List<Long> postIds = postIdsMap.get("postIds");
        try {
            service.deleteMessagesAndPosts(postIds);
            return ResponseEntity.ok("Posts and associated messages deleted successfully.");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("An error occurred while deleting posts and messages.");
        }
    }

    // Supprimer un utilisateur par nom
    @DeleteMapping("/deleteUserByName")
    public ResponseEntity<String> deleteUser(@RequestBody Map<String, String> userDetails) {
        String username = userDetails.get("username");
        boolean isDeleted = service.deleteUserByName(username);
        if (isDeleted) {
            return ResponseEntity.ok("User deleted");
        } else {
            return ResponseEntity.status(404).body("User not found");
        }
    }
}
