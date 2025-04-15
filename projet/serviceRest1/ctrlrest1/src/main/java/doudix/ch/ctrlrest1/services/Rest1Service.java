package doudix.ch.ctrlrest1.services;

import java.math.BigInteger;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

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

    /**
     * Méthode de connexion : vérifie si un utilisateur existe avec le bon mot de passe.
     * @param username Le nom d'utilisateur saisi.
     * @param password Le mot de passe saisi.
     * @return L'utilisateur si les identifiants sont corrects, sinon null.
     */
    public User login(String username, String password) {
        User user = userRepository.findByUsername(username);
        System.out.println("Utilisateur trouvé : " + user);
        System.out.println("Mot de passe en base : " + user.getPassword());
        System.out.println("Mot de passe reçu : " + password);

        if (user != null && user.getPassword().equals(password)) {
            return user;
        }
        return null;
    }

    /**
     * Méthode de déconnexion — ici purement symbolique (log console uniquement).
     */
    public void logout() {
        System.out.println("User logged out");
    }

    /**
     * Ajoute un nouvel utilisateur si le username n'existe pas encore.
     * @param user L'utilisateur à ajouter.
     * @return L'utilisateur sauvegardé.
     */
    public User addUser(User user) {
        if (userRepository.findByUsername(user.getUsername()) != null) {
            throw new RuntimeException("User already exists");
        }
        return userRepository.save(user);
    }

    /**
     * Ajoute un post dans la base de données.
     * @param creatorId ID de l'utilisateur créateur du post.
     * @param title Titre du post.
     * @param description Contenu du post.
     * @param imgUrl URL de l'image.
     * @param categorie Catégorie du post.
     * @param couleur Couleur liée à la catégorie.
     * @return Le post enregistré.
     */
    public Post addPost(BigInteger creatorId, String title, String description, String imgUrl, String categorie, String couleur) {
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

    /**
     * Recherche un utilisateur par son nom d'utilisateur.
     * @param username Nom de l'utilisateur.
     * @return L'objet User correspondant.
     */
    public User getUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    /**
     * Récupère tous les posts et les convertit en PostDTO (avec le nom du créateur).
     * @return Liste de PostDTO.
     */
    public List<PostDTO> getAllPosts() {
        List<Post> posts = postRepository.findAll();
        List<PostDTO> dtos = posts.stream().map(p -> {
            // Récupère le User associé au creatorId
            User user = userRepository.findById(p.getCreatorId().longValue()).orElse(null);
            String creatorUsername = (user != null) ? user.getUsername() : "Unknown";

            // Transforme en PostDTO
            return new PostDTO(
                p.getPostId(),
                p.getCreatorId(),
                creatorUsername,
                p.getImageUrl(),
                p.getTitle(),
                p.getDescription(),
                p.getCategory(),
                p.getCouleur()
            );
        }).toList();

        return dtos;
    }

    /**
     * Ajoute un message à un post donné.
     * @param text Contenu du message.
     * @param creatorId ID de l'auteur du message.
     * @param postId ID du post lié au message.
     * @return Message sauvegardé.
     */
    public Message addMessage(String text, Long creatorId, Long postId) {
        Message msg = new Message();
        msg.setText(text);
        msg.setCreatorId(creatorId);

        // Vérifie que le post existe
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new RuntimeException("Post non trouvé avec l'ID : " + postId));

        msg.setPost(post);

        return messageRepository.save(msg);
    }

    /**
     * Récupère tous les messages associés à un post donné.
     * @param postId L'identifiant du post.
     * @return Liste de messages.
     */
    public List<Message> getMessagesByPost(Long postId) {
        return messageRepository.findByPostPostId(postId);
    }

    /**
     * Supprime tous les messages et les posts associés à une liste d'identifiants.
     * @param postIds Liste d'IDs de posts à supprimer.
     */
    @Transactional
    public void deleteMessagesAndPosts(List<Long> postIds) {
        // Supprimer d'abord les messages liés
        List<Message> messages = messageRepository.findByPostPostIdIn(postIds);
        for (Message message : messages) {
            messageRepository.delete(message);
        }

        // Puis supprimer les posts
        postRepository.deleteByPostIdIn(postIds);
    }

    /**
     * Supprime un utilisateur selon son nom.
     * @param name Nom d'utilisateur.
     * @return true si l'utilisateur a été supprimé, false sinon.
     */
    public boolean deleteUserByName(String name) {
        User user = userRepository.findByUsername(name);

        if (user != null) {
            userRepository.delete(user);
            return true;
        }
        return false;
    }

    /**
     * Récupère le nom d'utilisateur à partir de son ID.
     * @param id ID de l'utilisateur.
     * @return Username correspondant ou null.
     */
    public String getUsernameById(Long id) {
        User user = userRepository.findById(id).orElse(null);
        return user != null ? user.getUsername() : null;
    }

    /**
     * Récupère tous les utilisateurs sous forme de DTO.
     * @return Liste de UserDTO.
     */
    public List<UserDTO> getAllUsers() {
        List<User> users = userRepository.findAll();
        return users.stream()
                .map(user -> new UserDTO(user.getId(), user.getUsername(), user.getPassword(), user.isAdmin()))
                .collect(Collectors.toList());
    }
}
