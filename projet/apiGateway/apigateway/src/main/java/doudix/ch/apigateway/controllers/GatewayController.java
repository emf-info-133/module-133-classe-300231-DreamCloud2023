package doudix.ch.apigateway.controllers;

import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/api/gateway")
public class GatewayController {

    private final RestTemplate restTemplate = new RestTemplate();

    private final String REST1_BASE_URL = "http://localhost:8081/api/rest1";
    private final String REST2_BASE_URL = "http://localhost:8082/api/rest2";

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestParam String username, @RequestParam String password) {
        String url = REST1_BASE_URL + "/login?username=" + username + "&password=" + password;
        return restTemplate.postForEntity(url, null, String.class);
    }

    @PostMapping("/logout")
    public ResponseEntity<String> logout() {
        String url = REST1_BASE_URL + "/logout";
        return restTemplate.postForEntity(url, null, String.class);
    }

    @PostMapping("/addPost")
    public ResponseEntity<String> addPost(@RequestParam String content) {
        String url = REST1_BASE_URL + "/addPost?content=" + content;
        return restTemplate.postForEntity(url, null, String.class);
    }

    @DeleteMapping("/deletePost/{id}")
    public ResponseEntity<String> deletePost(@PathVariable Long id) {
        String url = REST2_BASE_URL + "/deletePost/" + id;
        restTemplate.delete(url);
        return ResponseEntity.ok("Post deleted via gateway");
    }

    @DeleteMapping("/deleteUser/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable Long id) {
        String url = REST2_BASE_URL + "/deleteUsers/" + id;
        restTemplate.delete(url);
        return ResponseEntity.ok("User deleted via gateway");
    }
}
