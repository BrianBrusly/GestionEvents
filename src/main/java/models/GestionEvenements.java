package models;

import java.util.HashMap;
import java.util.Map;

public class GestionEvenements {
    private static GestionEvenements instance;
    private Map<String, Evenement> evenements;

    //Pattern Singleton : constructeur de "GestionÉvènements" declarer comm "privé(private)"
    private GestionEvenements() {
        this.evenements = new HashMap<>();
    }

    //Le Pattern Singleton se repercute ici du coup a la possibilite de ne creer qu'une seule instance de "GestionÉvènements"
    public static GestionEvenements getInstance() {
        if (instance == null) {
            instance = new GestionEvenements();
        }
        return instance;
    }

    public void ajouterEvenement(Evenement evenement) {
        evenements.put(evenement.getId(), evenement);
    }

    public void supprimerEvenement(String id) {
        evenements.remove(id);
    }

    public Evenement rechercherEvenement(String id) {
        return evenements.get(id);
    }
}
