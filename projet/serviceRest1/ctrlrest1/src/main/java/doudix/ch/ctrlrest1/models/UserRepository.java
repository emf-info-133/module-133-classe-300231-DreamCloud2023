package doudix.ch.ctrlrest1.models;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Interface permettant d'accéder aux données des utilisateurs (User).
 * Elle hérite de JpaRepository, ce qui fournit toutes les méthodes CRUD de base.
 * Exemple : save(), findById(), findAll(), deleteById()...
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    /**
     * Recherche un utilisateur dans la base de données par son nom d'utilisateur.
     * Cette méthode est automatiquement implémentée par Spring grâce à son nom.
     *
     * @param username Le nom d'utilisateur recherché.
     * @return L'objet User correspondant, ou null si non trouvé.
     */
    User findByUsername(String username);
}
