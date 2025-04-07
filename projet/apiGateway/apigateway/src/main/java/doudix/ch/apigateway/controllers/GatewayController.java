package doudix.ch.apigateway.controllers;

import doudix.ch.apigateway.dto.*;

import java.util.Map;

import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

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

        // Affichage du contenu du PostDTO avant l'envoi
        System.out.println("Sout ApiGateway - Check_1 : " + postDTO);

        // Ajouter un autre print pour chaque attribut si tu veux plus de détails
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

    @DeleteMapping("/deletePost")
    public ResponseEntity<String> deletePost(@RequestBody Map<String, Long> requestBody) {
        Long postId = requestBody.get("postId");

        if (postId == null) {
            return ResponseEntity.badRequest().body("postId is required.");
        }

        // Construction de l'URL pour appeler le service cible avec l'ID du post
        String url = REST2_BASE_URL + "/deletePost?postId=" + postId;

        try {
            // Envoi de la requête DELETE à l'API cible et capture de la réponse
            ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.DELETE, null, String.class);

            // Retour de la réponse de l'API cible
            return ResponseEntity.status(response.getStatusCode()).body(response.getBody());
        } catch (Exception e) {
            // Gestion des erreurs en cas de problème avec la requête
            return ResponseEntity.status(500).body("An error occurred while calling the target service.");
        }
    }

    @DeleteMapping("/deleteUser")
    public ResponseEntity<String> deleteUser(@RequestBody UserDTO userDto) {
        // Construction de l'URL pour appeler le service cible
        String url = REST2_BASE_URL + "/deleteUserByName";
    
        try {
            // Création de l'entité HTTP avec le corps de la requête contenant le UserDTO
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON); // Indiquer que le corps de la requête est en JSON
            HttpEntity<UserDTO> entity = new HttpEntity<>(userDto, headers);
    
            // Envoi de la requête DELETE avec l'objet UserDTO dans le corps
            ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.DELETE, entity, String.class);
    
            // Vérification de la réponse
            if (response.getStatusCode().is2xxSuccessful()) {
                return ResponseEntity.ok("User deleted via gateway");
            } else {
                return ResponseEntity.status(response.getStatusCode()).body("Failed to delete user via gateway");
            }
        } catch (Exception e) {
            // Gestion des erreurs si la requête échoue
            e.printStackTrace(); // Affiche la trace de l'exception pour faciliter le débogage
            return ResponseEntity.status(500)
                    .body("An error occurred while calling the target service: " + e.getMessage());
        }
    }
    

}
