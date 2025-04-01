package doudix.ch.ctrlrest2.models;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface MessageRepository extends JpaRepository<Message, Long> {

    // Méthode pour récupérer les messages par les IDs de post
    List<Message> findByPostIdIn(List<Long> postIds);
}
