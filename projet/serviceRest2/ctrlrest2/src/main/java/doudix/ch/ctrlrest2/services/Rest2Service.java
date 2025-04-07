package doudix.ch.ctrlrest2.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import doudix.ch.ctrlrest2.models.Banissement;
import doudix.ch.ctrlrest2.models.BanissementRepository;

@Service
public class Rest2Service {

    @Autowired
    private BanissementRepository banissementRepository;

    // Méthode pour bannir un utilisateur avec l'objet Banissement directement
    public Banissement banUser(Banissement banissement) {
        // Vérification si l'objet Banissement est valide
        if (banissement.getUsername() == null || banissement.getUsername().isBlank()) {
            throw new IllegalArgumentException("Le nom d'utilisateur ne peut pas être vide.");
        }
        if (banissement.getRemarque() == null || banissement.getRemarque().isBlank()) {
            banissement.setRemarque("Aucune remarque fournie.");
        }

        // Sauvegarde dans la base de données et renvoi de l'entité
        return banissementRepository.save(banissement);
    }
    
    // Méthode pour récupérer la liste des utilisateurs bannis
    public List<Banissement> getAllBans() {
        return banissementRepository.findAll();
    }
}
