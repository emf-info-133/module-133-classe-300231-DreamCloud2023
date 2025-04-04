package doudix.ch.ctrlrest1.controllers;

import org.springframework.web.bind.annotation.*;
import doudix.ch.ctrlrest1.models.Message;
import doudix.ch.ctrlrest1.models.Post;
import doudix.ch.ctrlrest1.models.User;
import doudix.ch.ctrlrest1.dto.PostDTO;
import doudix.ch.ctrlrest1.dto.UserDTO;
import doudix.ch.ctrlrest1.dto.MessageDTO;
import doudix.ch.ctrlrest1.services.Rest1Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

@RestController
@RequestMapping("/api/rest1")
public class Rest1Controller {

    @Autowired
    private Rest1Service service;

    // Connexion
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody UserDTO userDTO) {
        User user = service.login(userDTO.getUsername(), userDTO.getPassword());
        return (user != null) ? ResponseEntity.ok("Login successful")
                : ResponseEntity.status(401).body("Invalid credentials");
    }

    // Déconnexion
    @PostMapping("/logout")
    public ResponseEntity<String> logout() {
        service.logout();
        return ResponseEntity.ok("Logged out");
    }

    // Ajouter un utilisateur
    @PostMapping("/addUser")
    public ResponseEntity<String> addUser(@RequestBody UserDTO userDTO) {
        User user = new User();
        user.setUsername(userDTO.getUsername());
        user.setPassword(userDTO.getPassword());
        try {
            service.addUser(user);
            return ResponseEntity.ok("User added successfully");
        } catch (RuntimeException e) {
            return ResponseEntity.status(400).body(e.getMessage());
        }
    }

    // Ajouter un post
    @PostMapping("/addPost")
    public ResponseEntity<Post> addPost(@RequestBody PostDTO postDTO) {
        Post post = service.addPost(
            postDTO.getCreatorId(),
            postDTO.getTitle(),
            postDTO.getDescription(),
            postDTO.getImageUrl(),  // Correspondance correcte avec le DTO
            postDTO.getCategorie(),
            postDTO.getCouleur()
        );
        return ResponseEntity.ok(post);
    }

    // Ajouter un message
    @PostMapping("/addMsg")
    public ResponseEntity<Message> addMessage(@RequestBody MessageDTO messageDTO) {
        Message message = service.addMessage(
            messageDTO.getText(),
            messageDTO.getCreatorId(),
            messageDTO.getPostId()
        );
        return ResponseEntity.ok(message);
    }
}
