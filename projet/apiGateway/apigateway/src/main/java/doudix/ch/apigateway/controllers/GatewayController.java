package doudix.ch.apigateway.controllers;

import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import java.util.Map;

@RestController
@RequestMapping("/api/gateway")
public class GatewayController {

    private final RestTemplate restTemplate = new RestTemplate();
    private final String REST1_BASE_URL = "http://localhost:8080/api/rest1";
    private final String REST2_BASE_URL = "http://localhost:8080/api/rest2";

    // Connexion
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody Map<String, String> credentials) {
        String url = REST1_BASE_URL + "/login";
        return restTemplate.exchange(url, HttpMethod.POST, new HttpEntity<>(credentials), String.class);
    }

    // Déconnexion
    @PostMapping("/logout")
    public ResponseEntity<String> logout() {
        String url = REST1_BASE_URL + "/logout";
        return restTemplate.exchange(url, HttpMethod.POST, null, String.class);
    }

    // Ajouter un post
    @PostMapping("/addPost")
    public ResponseEntity<String> addPost(@RequestBody Map<String, Object> postDetails) {
        String url = REST1_BASE_URL + "/addPost";
        return restTemplate.exchange(url, HttpMethod.POST, new HttpEntity<>(postDetails), String.class);
    }

    // Ajouter un message
    @PostMapping("/addMsg")
    public ResponseEntity<String> addMessage(@RequestBody Map<String, Object> messageDetails) {
        String url = REST1_BASE_URL + "/addMsg";
        return restTemplate.exchange(url, HttpMethod.POST, new HttpEntity<>(messageDetails), String.class);
    }

    // Supprimer un post
    @DeleteMapping("/deletePost")
    public ResponseEntity<String> deletePost(@RequestBody Map<String, Object> postIds) {
        String url = REST2_BASE_URL + "/deletePosts";
        restTemplate.exchange(url, HttpMethod.DELETE, new HttpEntity<>(postIds), String.class);
        return ResponseEntity.ok("Post deleted via gateway");
    }

    // Supprimer un utilisateur
    @DeleteMapping("/deleteUser")
    public ResponseEntity<String> deleteUser(@RequestBody Map<String, String> userDetails) {
        String url = REST2_BASE_URL + "/deleteUserByName";
        restTemplate.exchange(url, HttpMethod.DELETE, new HttpEntity<>(userDetails), String.class);
        return ResponseEntity.ok("User deleted via gateway");
    }
}
