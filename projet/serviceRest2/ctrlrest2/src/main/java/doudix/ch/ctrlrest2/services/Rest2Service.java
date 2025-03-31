package doudix.ch.ctrlrest2.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import doudix.ch.ctrlrest2.models.Post;
import doudix.ch.ctrlrest2.models.PostRepository;
import doudix.ch.ctrlrest2.models.User;
import doudix.ch.ctrlrest2.models.UserRepository;

@Service
public class Rest2Service {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PostRepository postRepository;
    
    public User login(String username, String password) {
        return userRepository.findAll()
                .stream()
                .filter(u -> u.getUsername().equals(username) && u.getPassword().equals(password))
                .findFirst()
                .orElse(null);
    }

    public void logout() {
        System.out.println("User logged out");
    }

    public void deletePost(Long id) {
        postRepository.deleteById(id);
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
}

