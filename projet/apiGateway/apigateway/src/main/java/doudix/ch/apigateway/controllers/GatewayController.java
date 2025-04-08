package doudix.ch.apigateway.controllers;

import doudix.ch.apigateway.dto.*;

import java.util.List;
import java.util.Map;

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
    public ResponseEntity<String> login(@RequestBody UserDTO userDTO) {
        String url = REST1_BASE_URL + "/login";
        return restTemplate.exchange(url, HttpMethod.POST, new HttpEntity<>(userDTO), String.class);
    }

    // Déconnexion
    @PostMapping("/logout")
    public ResponseEntity<String> logout() {
        String url = REST1_BASE_URL + "/logout";
        return restTemplate.exchange(url, HttpMethod.POST, null, String.class);
    }

    // Ajouter un utilisateur
    @PostMapping("/addUser")
    public ResponseEntity<String> addUser(@RequestBody UserDTO userDTO) {
        String url = REST1_BASE_URL + "/addUser";
        return restTemplate.exchange(url, HttpMethod.POST, new HttpEntity<>(userDTO), String.class);
    }

    // Ajouter un post
    @PostMapping("/addPost")
    public ResponseEntity<String> addPost(@RequestBody PostDTO postDTO) {
        String url = REST1_BASE_URL + "/addPost";
        System.out.println("Sout ApiGateway - Check_1 : " + postDTO);
        System.out.println("CreatorId: " + postDTO.getCreatorId());
        System.out.println("Title: " + postDTO.getTitle());
        System.out.println("Description: " + postDTO.getDescription());
        System.out.println("ImageUrl: " + postDTO.getImageUrl());
        System.out.println("Category: " + postDTO.getCategory());
        System.out.println("Couleur: " + postDTO.getCouleur());
        return restTemplate.exchange(url, HttpMethod.POST, new HttpEntity<>(postDTO), String.class);
    }

    // Ajouter un message
    @PostMapping("/addMsg")
    public ResponseEntity<String> addMessage(@RequestBody MessageDTO messageDTO) {
        String url = REST1_BASE_URL + "/addMsg";
        return restTemplate.exchange(url, HttpMethod.POST, new HttpEntity<>(messageDTO), String.class);
    }

    // Supprimer un post
    @DeleteMapping("/deletePost")
    public ResponseEntity<String> deletePost(@RequestBody Map<String, Long> requestBody) {
        Long postId = requestBody.get("postId");

        if (postId == null) {
            return ResponseEntity.badRequest().body("postId is required.");
        }

        String url = REST1_BASE_URL + "/deletePost?postId=" + postId;

        try {
            ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.DELETE, null, String.class);
            return ResponseEntity.status(response.getStatusCode()).body(response.getBody());
        } catch (Exception e) {
            return ResponseEntity.status(500).body("An error occurred while calling the target service.");
        }
    }

    // Supprimer un utilisateur
    @DeleteMapping("/deleteUser")
    public ResponseEntity<String> deleteUser(@RequestBody UserDTO userDto) {
        String url = REST1_BASE_URL + "/deleteUserByName";

        try {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            HttpEntity<UserDTO> entity = new HttpEntity<>(userDto, headers);

            ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.DELETE, entity, String.class);

            if (response.getStatusCode().is2xxSuccessful()) {
                return ResponseEntity.ok("User deleted via gateway");
            } else {
                return ResponseEntity.status(response.getStatusCode()).body("Failed to delete user via gateway");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500)
                    .body("An error occurred while calling the target service: " + e.getMessage());
        }
    }

    @PostMapping("/banUser")
    public ResponseEntity<BanissementDTO> banUser(@RequestBody BanissementDTO banDto) {
        try {
            // Préparer l'URL du service REST2
            String url = REST2_BASE_URL + "/banUser"; // Assurez-vous que l'URL correspond bien à votre service REST2
    
            // Créer un objet Banissement à partir du DTO reçu
            BanissementDTO banissement = new BanissementDTO(banDto.getUsername(), banDto.getRemarque());
    
            // Créer l'entité Http pour envoyer l'objet Banissement
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            HttpEntity<BanissementDTO> requestEntity = new HttpEntity<>(banissement, headers);
    
            // Faire la requête HTTP POST vers le service REST2
            ResponseEntity<BanissementDTO> response = restTemplate.exchange(
                    url, 
                    HttpMethod.POST, 
                    requestEntity, 
                    BanissementDTO.class);
    
            // Retourner la réponse obtenue
            return response;
        } catch (Exception e) {
            e.printStackTrace(); // Pour afficher l'exception dans les logs
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
    

    // Récupérer tous les bannissements
    @GetMapping("/getBans")
    public ResponseEntity<List<BanissementDTO>> getAllBans() {
        String url = REST2_BASE_URL + "/getBans";

        try {
            ResponseEntity<List<BanissementDTO>> response = restTemplate.exchange(
                    url,
                    HttpMethod.GET,
                    null,
                    new ParameterizedTypeReference<List<BanissementDTO>>() {
                    });

            return ResponseEntity.ok(response.getBody());

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body(null);
        }
    }
}
