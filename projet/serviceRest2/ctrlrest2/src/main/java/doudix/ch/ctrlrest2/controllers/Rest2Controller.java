package doudix.ch.ctrlrest2.controllers;

import org.springframework.web.bind.annotation.*;

import doudix.ch.ctrlrest2.models.Post;
import doudix.ch.ctrlrest2.models.User;
import doudix.ch.ctrlrest2.services.Rest2Service;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@RestController
@RequestMapping("/api/rest2")
public class Rest2Controller {

    Rest2Service service = new Rest2Service();

    // Supprimer un Post par son nom
    @DeleteMapping("/deletePostByName/{name}")
    public ResponseEntity<String> deletePost(@PathVariable String name) {
        boolean isDeleted = service.deletePostByName(name);
        if (isDeleted) {
            return ResponseEntity.ok("Post deleted");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Post not found");
        }
    }

    // Supprimer un User par son nom
    @DeleteMapping("/deleteUserByName/{name}")
    public ResponseEntity<String> deleteUser(@PathVariable String name) {
        boolean isDeleted = service.deleteUserByName(name);
        if (isDeleted) {
            return ResponseEntity.ok("User deleted");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
        }
    }
}
