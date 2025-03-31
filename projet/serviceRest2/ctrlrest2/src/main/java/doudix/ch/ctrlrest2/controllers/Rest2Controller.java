package doudix.ch.ctrlrest2.controllers;

import org.springframework.web.bind.annotation.*;

import doudix.ch.ctrlrest2.models.Post;
import doudix.ch.ctrlrest2.models.User;
import doudix.ch.ctrlrest2.services.Rest2Service;

import org.springframework.http.ResponseEntity;

@RestController
@RequestMapping("/api/rest2")
public class Rest2Controller {

    Rest2Service service = new Rest2Service();

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
