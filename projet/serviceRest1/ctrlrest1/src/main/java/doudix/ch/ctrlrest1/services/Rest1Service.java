package doudix.ch.ctrlrest1.services;

import java.math.BigInteger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import doudix.ch.ctrlrest1.models.Message;
import doudix.ch.ctrlrest1.models.MessageRepository;
import doudix.ch.ctrlrest1.models.Post;
import doudix.ch.ctrlrest1.models.PostRepository;
import doudix.ch.ctrlrest1.models.User;
import doudix.ch.ctrlrest1.models.UserRepository;

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

    public Message addMessage(String text, Long creatorId, Long postId) {
        Message msg = new Message();
        msg.setText(text);
        msg.setCreatorId(creatorId);
        
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new RuntimeException("Post non trouvé avec l'ID : " + postId));
        
        msg.setPost(post);
        
        return messageRepository.save(msg);
    }

}
