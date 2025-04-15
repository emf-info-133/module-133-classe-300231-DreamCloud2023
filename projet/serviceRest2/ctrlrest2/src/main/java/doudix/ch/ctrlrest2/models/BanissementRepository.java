package doudix.ch.ctrlrest2.models;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Interface : BanissementRepository
 * Interface qui permet d'interagir avec la base de données pour les objets Banissement.
 * Elle hérite de JpaRepository, ce qui fournit automatiquement :
 *  - des méthodes comme save(), findAll(), deleteById(), findById()...
 * 
 * Exemple d’utilisation automatique :
 *  banissementRepository.findAll(); → retourne tous les utilisateurs bannis
 *  banissementRepository.save(obj); → enregistre un nouvel utilisateur banni
 */
public interface BanissementRepository extends JpaRepository<Banissement, Long> {
}
