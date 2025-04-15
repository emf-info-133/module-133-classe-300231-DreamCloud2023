package doudix.ch.ctrlrest1.models;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Interface permettant de gérer l’accès à la table des Posts via JPA.
 * Elle hérite de JpaRepository ce qui fournit déjà toutes les méthodes de base (findAll, save, delete, etc.)
 */
@Repository
public interface PostRepository extends JpaRepository<Post, Long> {

    /**
     * Supprime tous les posts dont l'ID figure dans la liste fournie.
     * Cette méthode est automatiquement interprétée par Spring Data JPA grâce à son nom.
     *
     * @param postIds Liste des identifiants de posts à supprimer
     */
    void deleteByPostIdIn(List<Long> postIds);
}
