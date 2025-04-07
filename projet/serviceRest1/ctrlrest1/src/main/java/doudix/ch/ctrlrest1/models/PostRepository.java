package doudix.ch.ctrlrest1.models;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
        // Méthode pour supprimer des posts par leur ID
        void deleteByPostIdIn(List<Long> postIds);
    
}