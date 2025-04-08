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
        System.out.println("Check1-Rest2");
        // Vérification si l'objet Banissement est valide
        if (banissement.getUsername() == null || banissement.getUsername().isBlank()) {
            throw new IllegalArgumentException("Le nom d'utilisateur ne peut pas être vide.");
        }
        System.out.println("Check2-Rest2");
        if (banissement.getRemarque() == null || banissement.getRemarque().isBlank()) {
            banissement.setRemarque("Aucune remarque fournie.");
        }
        System.out.println("Check3-Rest2");
        try {
            return banissementRepository.save(banissement);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new IllegalArgumentException(e.getMessage());
        }
        
    }
    
    // Méthode pour récupérer la liste des utilisateurs bannis
    public List<Banissement> getAllBans() {
        return banissementRepository.findAll();
    }
}
