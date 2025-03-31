package doudix.ch.ctrlrest1.controllers;

import org.springframework.web.bind.annotation.*;

import doudix.ch.ctrlrest1.models.Message;
import doudix.ch.ctrlrest1.models.Post;
import doudix.ch.ctrlrest1.models.User;
import doudix.ch.ctrlrest1.services.Rest1Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

@RestController
@RequestMapping("/api/rest1")
public class Rest1Controller {

    @Autowired
    private Rest1Service service;

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestParam String username, @RequestParam String password) {
        User user = service.login(username, password);
        return (user != null) ? ResponseEntity.ok("Login successful") : ResponseEntity.status(401).body("Invalid credentials");
    }

    @PostMapping("/logout")
    public ResponseEntity<String> logout() {
        service.logout();
        return ResponseEntity.ok("Logged out");
    }

    @PostMapping("/addPost")
    public ResponseEntity<Post> addPost(@RequestParam String content) {
        return ResponseEntity.ok(service.addPost(content));
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

    @DeleteMapping("/deleteUsers/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable Long id) {
        service.deleteUser(id);
        return ResponseEntity.ok("User deleted");
    }
}
