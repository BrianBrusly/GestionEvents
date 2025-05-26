package models;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Conference extends Evenement {
    private String theme;
    private List<Intervenant> intervenants = new ArrayList<>();

    // Constructeurs
    public Conference() {
    }

    public Conference(String id, String nom, LocalDateTime date, String lieu, int capaciteMax, String theme) {
        super(id, nom, date, lieu, capaciteMax);
        this.theme = theme;
    }

    // Méthodes spécifiques
    public void ajouterIntervenant(Intervenant intervenant) {
        intervenants.add(intervenant);
        notifierObservateurs("Nouvel intervenant: " + intervenant.getNom());
    }

    public void supprimerIntervenant(Intervenant intervenant) {
        intervenants.remove(intervenant);
        notifierObservateurs("Intervenant retiré: " + intervenant.getNom());
    }

    // Getters/Setters
    public String getTheme() {
        return theme;
    }

    public void setTheme(String theme) {
        this.theme = theme;
    }

    public List<Intervenant> getIntervenants() {
        return intervenants;
    }

    public void setIntervenants(List<Intervenant> intervenants) {
        this.intervenants = intervenants;
    }

    public void afficherDetails() {
        super.afficherDetails(); // Affiche les détails de base
        System.out.println("Type: Conférence");
        System.out.println("Thème: " + theme);
        System.out.println("Intervenants (" + intervenants.size() + "):");
        intervenants.forEach(i ->
                System.out.println(" - " + i.getNom() + " (" + i.getSpecialite() + ")")
        );

    }
}
