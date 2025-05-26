package models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.ArrayList;
import java.util.List;

public class Organisateur extends Participant {
    @JsonIgnore  // Exclut cette liste du JSON
    private List<Evenement> evenementsOrganises = new ArrayList<>();

    // Constructeurs
    public Organisateur() {}

    public Organisateur(String id, String nom, String email) {
        super(id, nom, email);
    }

    // Méthodes métier
    public void organiserEvenement(Evenement evenement) {
        evenementsOrganises.add(evenement);
        System.out.println(getNom() + " a organisé : " + evenement.getNom());
    }

    public void annulerEvenement(Evenement evenement) {
        if (evenementsOrganises.remove(evenement)) {
            evenement.annuler();
            System.out.println(getNom() + " a annulé : " + evenement.getNom());
        } else {
            System.out.println(getNom() + " ne gère pas cet événement");
        }
    }

    public void afficherEvenementsOrganises() {
        System.out.println("Événements de " + getNom() + ":");
        evenementsOrganises.forEach(e ->
                System.out.println("- " + e.getNom() + " (" + (e.isAnnule() ? "Annulé" : "Actif") + ")")
        );
    }

    // Getters/Setters
    public List<Evenement> getEvenementsOrganises() {
        return new ArrayList<>(evenementsOrganises);  // Copie défensive
    }

    public void setEvenementsOrganises(List<Evenement> evenements) {
        this.evenementsOrganises = new ArrayList<>(evenements);  // Copie défensive
    }
}