package doudix.ch.ctrlrest2.models;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {

    // Méthode pour supprimer des posts par leur ID
    void deleteByIdIn(List<Long> postIds);
}
