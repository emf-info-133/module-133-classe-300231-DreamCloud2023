package doudix.ch.ctrlrest2.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import doudix.ch.ctrlrest2.models.Post;
import doudix.ch.ctrlrest2.models.PostRepository;
import doudix.ch.ctrlrest2.models.User;
import doudix.ch.ctrlrest2.models.UserRepository;

@Service
public class Rest2Service {
    @Autowired
    private PostRepository postRepository;

    @Autowired
    private UserRepository userRepository;

    // Méthode pour supprimer un post par son nom
    public boolean deletePostByName(String name) {
        Optional<Post> post = postRepository.findByTitle(name);
        if (post.isPresent()) {
            postRepository.delete(post.get());
            return true;
        }
        return false;
    }

    // Méthode pour supprimer un utilisateur par son nom
    public boolean deleteUserByName(String name) {
        Optional<User> user = userRepository.findByUsername(name);
        if (user.isPresent()) {
            userRepository.delete(user.get());
            return true;
        }
        return false;
    }
}

