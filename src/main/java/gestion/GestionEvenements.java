package gestion;

import exceptions.CapaciteMaxAtteinteException;
import exceptions.EvenementDejaExistantException;
import exceptions.EvenementNonTrouveException;
import exceptions.ParticipantDejaInscritException;
import models.Evenement;
import models.Participant;
import persistence.GestionPersistance;

import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

//Singleton gérant la logique métier des événements (Pattern Singleton)

public class GestionEvenements {
    private static GestionEvenements instance;
    private final Map<String, Evenement> evenements = new HashMap<>();
    private final GestionPersistance gestionPersistance = new GestionPersistance();

    private GestionEvenements() {}

    public static synchronized GestionEvenements getInstance() {
        if (instance == null) {
            instance = new GestionEvenements();
        }
        return instance;
    }

    //Ajouter un nouvel événement

    public void ajouterEvenement(Evenement evenement) throws EvenementDejaExistantException {
        if (evenements.containsKey(evenement.getId())) {
            throw new EvenementDejaExistantException(evenement.getId());
        }
        evenements.put(evenement.getId(), evenement);
    }

    //Supprimer un événement

    public void supprimerEvenement(String id) throws EvenementNonTrouveException {
        if (!evenements.containsKey(id)) {
            throw new EvenementNonTrouveException(id);
        }
        evenements.remove(id);
    }

    //Récupérer un événement par son ID

    public Evenement getEvenement(String id) throws EvenementNonTrouveException {
        Evenement e = evenements.get(id);
        if (e == null) throw new EvenementNonTrouveException(id);
        return e;
    }

    //Inscrire un participant à un événement

    public void inscrireParticipant(String eventId, Participant participant)
            throws EvenementNonTrouveException, ParticipantDejaInscritException, CapaciteMaxAtteinteException, CapaciteMaxAtteinteException, ParticipantDejaInscritException {
        getEvenement(eventId).ajouterParticipant(participant);
    }

    //Désinscrire un participant d'un événement

    public void desinscrireParticipant(String eventId, Participant participant)
            throws EvenementNonTrouveException {
        getEvenement(eventId).retirerParticipant(participant);
    }

    // Méthodes de recherche
    public List<Evenement> rechercherParLieu(String lieu) {
        return filtrer(e -> e.getLieu().toLowerCase().contains(lieu.toLowerCase()));
    }

    public List<Evenement> rechercherParNom(String nom) {
        return filtrer(e -> e.getNom().toLowerCase().contains(nom.toLowerCase()));
    }

    public List<Evenement> getEvenementsNonAnnules() {
        return filtrer(e -> !e.isAnnule());
    }

    private List<Evenement> filtrer(Predicate<Evenement> condition) {
        return evenements.values().stream()
                .filter(condition)
                .collect(Collectors.toList());
    }

    // Sauvegarde l'état courant

    public void sauvegarder() {
        gestionPersistance.sauvegarderEvenements(new ArrayList<>(evenements.values()));
    }

    // Charge l'état précédent

    public void charger() {
        List<Evenement> loaded = gestionPersistance.chargerEvenements();
        evenements.clear();
        loaded.forEach(e -> evenements.put(e.getId(), e));
    }

}