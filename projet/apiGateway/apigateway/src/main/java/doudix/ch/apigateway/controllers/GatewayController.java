package doudix.ch.apigateway.controllers;

import doudix.ch.apigateway.dto.*;

import java.util.List;
import java.util.Map;

import jakarta.servlet.http.HttpSession;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.core.ParameterizedTypeReference;

@RestController
@RequestMapping("/api/gateway")
public class GatewayController {

    private final RestTemplate restTemplate = new RestTemplate();
    private final String REST1_BASE_URL = "http://restctrl1:8080/api/rest1";
    private final String REST2_BASE_URL = "http://restctrl2:8080/api/rest2";

    // Connexion
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody UserDTO userDTO, HttpSession session) {
        String url = REST1_BASE_URL + "/login";
        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, new HttpEntity<>(userDTO), String.class);

        if (response.getStatusCode().is2xxSuccessful()) {
            session.setAttribute("username", userDTO.getUsername());
        }

        return response;
    }

    // Déconnexion
    @PostMapping("/logout")
    public ResponseEntity<String> logout(HttpSession session) {
        String username = (String) session.getAttribute("username");

        if (username != null) {
            session.invalidate();
            String url = REST1_BASE_URL + "/logout";
            return restTemplate.exchange(url, HttpMethod.POST, null, String.class);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("User not logged in.");
        }
    }

    // Exemple : ajouter un post uniquement si connecté
    @PostMapping("/addPost")
    public ResponseEntity<String> addPost(@RequestBody PostDTO postDTO, HttpSession session) {
        String username = (String) session.getAttribute("username");

        if (username == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("You must be logged in to add a post.");
        }

        String url = REST1_BASE_URL + "/addPost";
        return restTemplate.exchange(url, HttpMethod.POST, new HttpEntity<>(postDTO), String.class);
    }

    // Le reste de ton code reste identique sauf si tu veux aussi protéger d'autres endpoints

    @PostMapping("/addUser")
    public ResponseEntity<String> addUser(@RequestBody UserDTO userDTO) {
        String url = REST1_BASE_URL + "/addUser";
        return restTemplate.exchange(url, HttpMethod.POST, new HttpEntity<>(userDTO), String.class);
    }

    @GetMapping("/getUser/{username}")
    public ResponseEntity<UserDTO> getUserByUsername(@PathVariable String username) {
        try {
            String url = REST1_BASE_URL + "/getUser/" + username;
            return restTemplate.exchange(url, HttpMethod.GET, null, UserDTO.class);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @GetMapping("/getPosts")
    public ResponseEntity<List<PostDTO>> getAllPosts() {
        String url = REST1_BASE_URL + "/getPosts";
        return restTemplate.exchange(url, HttpMethod.GET, null, new ParameterizedTypeReference<List<PostDTO>>() {});
    }

    @PostMapping("/addMsg")
    public ResponseEntity<String> addMessage(@RequestBody MessageDTO messageDTO) {
        String url = REST1_BASE_URL + "/addMsg";
        return restTemplate.exchange(url, HttpMethod.POST, new HttpEntity<>(messageDTO), String.class);
    }

    @GetMapping("/getMessages/{postId}")
    public ResponseEntity<List<MessageDTO>> getMessagesByPost(@PathVariable Long postId) {
        String url = REST1_BASE_URL + "/getMessages/" + postId;

        try {
            ResponseEntity<List<MessageDTO>> response = restTemplate.exchange(
                    url,
                    HttpMethod.GET,
                    null,
                    new ParameterizedTypeReference<List<MessageDTO>>() {});
            return response;
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @DeleteMapping("/deletePost")
    public ResponseEntity<String> deletePost(@RequestBody Map<String, Long> requestBody) {
        Long postId = requestBody.get("postId");

        if (postId == null) {
            return ResponseEntity.badRequest().body("postId is required.");
        }

        String url = REST1_BASE_URL + "/deletePost?postId=" + postId;

        try {
            return restTemplate.exchange(url, HttpMethod.DELETE, null, String.class);
        } catch (Exception e) {
            return ResponseEntity.status(500).body("An error occurred while calling the target service.");
        }
    }

    @DeleteMapping("/deleteUser")
    public ResponseEntity<String> deleteUser(@RequestBody UserDTO userDto) {
        String url = REST1_BASE_URL + "/deleteUserByName";

        try {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            HttpEntity<UserDTO> entity = new HttpEntity<>(userDto, headers);
            ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.DELETE, entity, String.class);

            return response.getStatusCode().is2xxSuccessful()
                    ? ResponseEntity.ok("User deleted via gateway")
                    : ResponseEntity.status(response.getStatusCode()).body("Failed to delete user via gateway");

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500)
                    .body("An error occurred while calling the target service: " + e.getMessage());
        }
    }

    @PostMapping("/banUser")
    public ResponseEntity<BanissementDTO> banUser(@RequestBody BanissementDTO banDto) {
        try {
            String url = REST2_BASE_URL + "/banUser";
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            HttpEntity<BanissementDTO> requestEntity = new HttpEntity<>(banDto, headers);

            return restTemplate.exchange(url, HttpMethod.POST, requestEntity, BanissementDTO.class);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @GetMapping("/getBans")
    public ResponseEntity<List<BanissementDTO>> getAllBans() {
        String url = REST2_BASE_URL + "/getBans";

        try {
            ResponseEntity<List<BanissementDTO>> response = restTemplate.exchange(
                    url,
                    HttpMethod.GET,
                    null,
                    new ParameterizedTypeReference<List<BanissementDTO>>() {});
            return ResponseEntity.ok(response.getBody());

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body(null);
        }
    }

    @GetMapping("/getUserById/{userId}")
    public ResponseEntity<String> getUserById(@PathVariable Long userId) {
        String url = REST1_BASE_URL + "/getUserById/" + userId;

        try {
            return restTemplate.exchange(url, HttpMethod.GET, null, String.class);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erreur interne");
        }
    }

    @GetMapping("/getAllUsers")
    public ResponseEntity<List<UserDTO>> getAllUsers() {
        String url = REST1_BASE_URL + "/getAllUsers";

        try {
            ResponseEntity<List<UserDTO>> response = restTemplate.exchange(
                    url,
                    HttpMethod.GET,
                    null,
                    new ParameterizedTypeReference<List<UserDTO>>() {});
            return ResponseEntity.ok(response.getBody());
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
}
