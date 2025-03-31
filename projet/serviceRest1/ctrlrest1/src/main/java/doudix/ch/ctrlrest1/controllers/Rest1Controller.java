package doudix.ch.ctrlrest1.controllers;

import org.springframework.web.bind.annotation.*;

import doudix.ch.ctrlrest1.models.Message;
import doudix.ch.ctrlrest1.models.Post;
import doudix.ch.ctrlrest1.models.User;
import doudix.ch.ctrlrest1.services.Rest1Service;

import java.math.BigInteger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

@RestController
@RequestMapping("/api/rest1")
public class Rest1Controller {

    @Autowired
    private Rest1Service service;

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody User ImportedUser) {
        User user = service.login(ImportedUser.getUsername(), ImportedUser.getPassword());
        return (user != null) ? ResponseEntity.ok("Login successful")
                : ResponseEntity.status(401).body("Invalid credentials");
    }

    @PostMapping("/logout")
    public ResponseEntity<String> logout() {
        service.logout();
        return ResponseEntity.ok("Logged out");
    }

    @PostMapping("/addUser")
    public ResponseEntity<String> addUser(@RequestBody User user) {
        try {
            service.addUser(user);
            return ResponseEntity.ok("User added successfully");
        } catch (RuntimeException e) {
            return ResponseEntity.status(400).body(e.getMessage());
        }
    }

    @PostMapping("/addPost")
    public ResponseEntity<Post> addPost(
            @RequestParam Long creator_id,
            @RequestParam String title,
            @RequestParam String description,
            @RequestParam String imgUrl) {
        BigInteger creatorIdBig = BigInteger.valueOf(creator_id);
        return ResponseEntity.ok(service.addPost(creatorIdBig, title, description, imgUrl));
    }

    @PostMapping("/addMsg")
    public ResponseEntity<Message> addMessage(@RequestParam String content) {
        return ResponseEntity.ok(service.addMessage(content));
    }

    @DeleteMapping("/deletePost/{id}")
    public ResponseEntity<String> deletePost(@PathVariable Long id) {
        service.deletePost(id);
        return ResponseEntity.ok("Post deleted");
    }

}
