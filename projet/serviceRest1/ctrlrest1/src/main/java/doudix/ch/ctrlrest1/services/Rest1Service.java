package doudix.ch.ctrlrest1.services;

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
        return userRepository.findAll()
                .stream()
                .filter(u -> u.getUsername().equals(username) && u.getPassword().equals(password))
                .findFirst()
                .orElse(null);
    }

    public void logout() {
        System.out.println("User logged out");
    }

    public Post addPost(String content) {
        Post post = new Post();
        post.setContent(content);
        return postRepository.save(post);
    }

    public Message addMessage(String content) {
        Message msg = new Message();
        msg.setContent(content);
        return messageRepository.save(msg);
    }

    public void deletePost(Long id) {
        postRepository.deleteById(id);
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
}

