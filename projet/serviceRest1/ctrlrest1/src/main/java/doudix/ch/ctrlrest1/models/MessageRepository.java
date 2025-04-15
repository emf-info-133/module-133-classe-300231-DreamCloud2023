package doudix.ch.ctrlrest1.models;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Interface permettant d'interagir avec la base de données pour les entités Message.
 * Elle hérite de JpaRepository pour fournir automatiquement les opérations CRUD.
 */
@Repository
public interface MessageRepository extends JpaRepository<Message, Long> {
    
    /**
     * Récupère tous les messages associés à une liste d'identifiants de posts.
     * Utilisée par exemple quand on veut supprimer tous les messages de plusieurs posts à la fois.
     *
     * @param postIds Liste des IDs des posts
     * @return Liste des messages correspondant à ces posts
     */
    List<Message> findByPostPostIdIn(List<Long> postIds);

    /**
     * Récupère tous les messages associés à un seul post.
     * Utilisée pour l'affichage des commentaires dans une discussion.
     *
     * @param postId ID du post concerné
     * @return Liste des messages liés à ce post
     */
    List<Message> findByPostPostId(Long postId);
}
