package doudix.ch.ctrlrest1.services;

import java.math.BigInteger;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import doudix.ch.ctrlrest1.dto.PostDTO;
import doudix.ch.ctrlrest1.dto.UserDTO;
import doudix.ch.ctrlrest1.models.Message;
import doudix.ch.ctrlrest1.models.MessageRepository;
import doudix.ch.ctrlrest1.models.Post;
import doudix.ch.ctrlrest1.models.PostRepository;
import doudix.ch.ctrlrest1.models.User;
import doudix.ch.ctrlrest1.models.UserRepository;
import jakarta.transaction.Transactional;

@Service
public class Rest1Service {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PostRepository postRepository;
    @Autowired
    private MessageRepository messageRepository;

    public User login(String username, String password) {
        User user = userRepository.findByUsername(username);
        // Verification
        System.out.println("Utilisateur trouvé : " + user);
        System.out.println("Mot de passe en base : " + user.getPassword());
        System.out.println("Mot de passe reçu : " + password);

        if (user != null && user.getPassword().equals(password)) {
            return user;
        }
        return null;
    }

    public void logout() {
        System.out.println("User logged out");
    }

    public User addUser(User user) {
        if (userRepository.findByUsername(user.getUsername()) != null) {
            throw new RuntimeException("User already exists");
        }
        return userRepository.save(user);
    }

    public Post addPost(BigInteger creatorId, String title, String description, String imgUrl, String categorie,
            String couleur) {
        Post post = new Post();
        post.setCreatorId(creatorId);
        post.setTitle(title);
        post.setDescription(description);
        post.setImageUrl(imgUrl);
        post.setCategory(categorie);
        post.setCouleur(couleur);

        Post savedPost = postRepository.save(post);
        System.out.println("Post enregistré: " + savedPost);

        return savedPost;
    }

    public User getUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public List<PostDTO> getAllPosts() {
    List<Post> posts = postRepository.findAll();
    List<PostDTO> dtos = posts.stream().map(p -> {
        // Récupérer l'utilisateur en utilisant le creatorId
        User user = userRepository.findById(p.getCreatorId().longValue()).orElse(null);
        String creatorUsername = (user != null) ? user.getUsername() : "Unknown"; // Vérifier si l'utilisateur existe

        // Créer un PostDTO avec le nom de l'utilisateur
        return new PostDTO(
            p.getPostId(),
            p.getCreatorId(),
            creatorUsername,  // Ajouter le nom de l'utilisateur ici
            p.getImageUrl(),
            p.getTitle(),
            p.getDescription(),
            p.getCategory(),
            p.getCouleur()
        );
    }).toList();

    return dtos;
}


    public Message addMessage(String text, Long creatorId, Long postId) {
        Message msg = new Message();
        msg.setText(text);
        msg.setCreatorId(creatorId);

        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new RuntimeException("Post non trouvé avec l'ID : " + postId));

        msg.setPost(post);

        return messageRepository.save(msg);
    }

    public List<Message> getMessagesByPost(Long postId) {
        return messageRepository.findByPostPostId(postId);
    }

    // Méthode pour supprimer les messages et les posts
    @Transactional
    public void deleteMessagesAndPosts(List<Long> postIds) {
        // Supprimer d'abord les messages associés aux posts
        List<Message> messages = messageRepository.findByPostPostIdIn(postIds);
        for (Message message : messages) {
            messageRepository.delete(message); // Suppression explicite des messages
        }

        // Ensuite, supprimer les posts
        postRepository.deleteByPostIdIn(postIds);
    }

    // Méthode pour supprimer un utilisateur par son nom
    public boolean deleteUserByName(String name) {
        User user = userRepository.findByUsername(name); // Recherche de l'utilisateur par son nom

        if (user != null) { // Vérifie si l'utilisateur existe
            userRepository.delete(user); // Supprime l'utilisateur
            return true;
        }
        return false; // Si l'utilisateur n'est pas trouvé
    }

    public String getUsernameById(Long id) {
        User user = userRepository.findById(id).orElse(null);
        return user != null ? user.getUsername() : null;
    }

    // Méthode pour récupérer tous les utilisateurs
    public List<UserDTO> getAllUsers() {
        List<User> users = userRepository.findAll();  // Récupère tous les utilisateurs depuis la base de données
        List<UserDTO> userDTOs = users.stream().map(user -> {
           
            return new UserDTO(user.getId(), user.getUsername(), user.getPassword(), user.isAdmin());
        }).collect(Collectors.toList());
        return userDTOs;
    }
}


