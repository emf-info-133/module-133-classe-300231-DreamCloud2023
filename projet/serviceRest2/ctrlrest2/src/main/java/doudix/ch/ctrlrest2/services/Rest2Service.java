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

    /**
     * Méthode : banUser
     * Sert à enregistrer un utilisateur banni dans la base de données.
     * Vérifie que le nom d'utilisateur est fourni (non vide).
     * Si aucune remarque n'est donnée, une remarque par défaut est ajoutée.
     * Retourne l'objet Banissement enregistré (avec l'ID auto-généré).
     */
    public Banissement banUser(Banissement banissement) {
        System.out.println("Check1-Rest2");

        // Vérifie que le nom d'utilisateur n'est pas vide
        if (banissement.getUsername() == null || banissement.getUsername().isBlank()) {
            throw new IllegalArgumentException("Le nom d'utilisateur ne peut pas être vide.");
        }

        System.out.println("Check2-Rest2");

        // Si aucune remarque n'est fournie, on en ajoute une par défaut
        if (banissement.getRemarque() == null || banissement.getRemarque().isBlank()) {
            banissement.setRemarque("Aucune remarque fournie.");
        }

        System.out.println("Check3-Rest2");

        // Tente d'enregistrer l'utilisateur banni dans la base
        try {
            return banissementRepository.save(banissement);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new IllegalArgumentException(e.getMessage());
        }
    }

    /**
     * Méthode : getAllBans
     * Récupère tous les utilisateurs bannis enregistrés dans la base.
     * Retourne une liste d’objets Banissement (entités).
     */
    public List<Banissement> getAllBans() {
        return banissementRepository.findAll();
    }
}
