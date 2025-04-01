package doudix.ch.ctrlrest1.controllers;

import org.springframework.web.bind.annotation.*;
import doudix.ch.ctrlrest1.models.Message;
import doudix.ch.ctrlrest1.models.Post;
import doudix.ch.ctrlrest1.models.User;
import doudix.ch.ctrlrest1.services.Rest1Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import java.math.BigInteger;
import java.util.Map;

@RestController
@RequestMapping("/api/rest1")
public class Rest1Controller {

    @Autowired
    private Rest1Service service;

    // Connexion
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody Map<String, String> credentials) {
        String username = credentials.get("username");
        String password = credentials.get("password");

        User user = service.login(username, password);
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
    public ResponseEntity<String> addUser(@RequestBody Map<String, String> userDetails) {
        User user = new User();
        user.setUsername(userDetails.get("username"));
        user.setPassword(userDetails.get("password"));
        try {
            service.addUser(user);
            return ResponseEntity.ok("User added successfully");
        } catch (RuntimeException e) {
            return ResponseEntity.status(400).body(e.getMessage());
        }
    }

    // Ajouter un post
    @PostMapping("/addPost")
    public ResponseEntity<Post> addPost(@RequestBody Map<String, Object> postDetails) {
        BigInteger creatorIdBig = BigInteger.valueOf(Long.parseLong(postDetails.get("creator_id").toString()));
        String title = postDetails.get("title").toString();
        String description = postDetails.get("description").toString();
        String imgUrl = postDetails.get("imgUrl").toString();
        String categorie = postDetails.get("categorie").toString();
        String couleur = postDetails.get("couleur").toString();

        Post post = service.addPost(creatorIdBig, title, description, imgUrl, categorie, couleur);
        return ResponseEntity.ok(post);
    }

    // Ajouter un message
    @PostMapping("/addMsg")
    public ResponseEntity<Message> addMessage(@RequestBody Map<String, Object> messageDetails) {
        String text = messageDetails.get("text").toString();
        Long creatorId = Long.parseLong(messageDetails.get("creatorId").toString());
        Long postId = Long.parseLong(messageDetails.get("postId").toString());

        Message message = service.addMessage(text, creatorId, postId);
        return ResponseEntity.ok(message);
    }
}
