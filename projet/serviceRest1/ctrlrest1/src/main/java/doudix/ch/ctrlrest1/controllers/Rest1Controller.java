package doudix.ch.ctrlrest1.controllers;

import org.springframework.web.bind.annotation.*;
import doudix.ch.ctrlrest1.models.Message;
import doudix.ch.ctrlrest1.models.Post;
import doudix.ch.ctrlrest1.models.User;
import doudix.ch.ctrlrest1.dto.PostDTO;
import doudix.ch.ctrlrest1.dto.UserDTO;
import doudix.ch.ctrlrest1.dto.MessageDTO;
import doudix.ch.ctrlrest1.services.Rest1Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

@RestController
@RequestMapping("/api/rest1")
public class Rest1Controller {

    @Autowired
    private Rest1Service service;

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody UserDTO userDTO) {
        try {
            User user = service.login(userDTO.getUsername(), userDTO.getPassword());
            return (user != null) ? ResponseEntity.ok("Login successful")
                    : ResponseEntity.status(401).body("Invalid credentials");
        } catch (Exception e) {
            e.printStackTrace(); // log dans les logs du conteneur
            return ResponseEntity.status(500).body("Erreur interne: " + e.getMessage());
        }
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
                postDTO.getImageUrl(),
                postDTO.getCategory(),
                postDTO.getCouleur());
        return ResponseEntity.ok(post);
    }

    @GetMapping("/getUser/{username}")
    public ResponseEntity<UserDTO> getUserByUsername(@PathVariable String username) {
        User user = service.getUserByUsername(username);
        if (user != null) {
            return ResponseEntity.ok(new UserDTO(user.getId(), user.getUsername(), user.getPassword()));
        } else {
            return ResponseEntity.status(404).body(null);
        }
    }

    @GetMapping("/getPosts")
public ResponseEntity<List<PostDTO>> getAllPosts() {
    List<Post> posts = service.getAllPosts();
    List<PostDTO> dtos = posts.stream().map(p -> new PostDTO(
        p.getPostId(),   // ← devrait marcher
        p.getCreatorId(),
        p.getImageUrl(),
        p.getTitle(),
        p.getDescription(),
        p.getCategory(),
        p.getCouleur()
    )).toList();

    return ResponseEntity.ok(dtos);
}


    // Ajouter un message
    @PostMapping("/addMsg")
    public ResponseEntity<Message> addMessage(@RequestBody MessageDTO messageDTO) {
        Message message = service.addMessage(
                messageDTO.getText(),
                messageDTO.getCreatorId(),
                messageDTO.getPostId());
        return ResponseEntity.ok(message);
    }

    @GetMapping("/getMessages/{postId}")
    public ResponseEntity<List<MessageDTO>> getMessagesByPost(@PathVariable Long postId) {
        List<Message> messages = service.getMessagesByPost(postId);
        List<MessageDTO> dtos = messages.stream()
                .map(m -> new MessageDTO(m.getId(), m.getText(), m.getCreatorId(), m.getPost().getPostId())).toList();
        return ResponseEntity.ok(dtos);
    }

    // Méthode pour supprimer un post selon son ID dans le service cible
    @DeleteMapping("/deletePost")
    public ResponseEntity<String> deletePost(@RequestParam Long postId) {
        try {
            // Appeler la méthode pour supprimer le post selon son ID
            service.deleteMessagesAndPosts(List.of(postId)); // Supprimer le post et les messages associés
            return ResponseEntity.ok("Post and associated messages deleted successfully.");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("An error occurred while deleting the post and messages.");
        }
    }

    // Supprimer un utilisateur par nom
    @DeleteMapping("/deleteUserByName")
    public ResponseEntity<String> deleteUser(@RequestBody UserDTO userDto) {
        String username = userDto.getUsername(); // Utilisation du DTO pour récupérer le nom d'utilisateur
        boolean isDeleted = service.deleteUserByName(username);
        if (isDeleted) {
            return ResponseEntity.ok("User deleted");
        } else {
            return ResponseEntity.status(404).body("User not found");
        }
    }

    @GetMapping("/getUserById/{id}")
    public ResponseEntity<String> getUserById(@PathVariable Long id) {
      
        String username = service.getUsernameById(id);
       
        if (username != null) {
            return ResponseEntity.ok(username);
        } else {
            return ResponseEntity.status(404).body("User not found");
        }
    }
}
