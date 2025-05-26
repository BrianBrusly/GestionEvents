package models;

import exceptions.CapaciteMaxAtteinteException;
import exceptions.ParticipantDejaInscritException;
import observer.EvenementObservable;
import observer.ParticipantObserver;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

// Import des annotations Jackson pour la sérialisation JSON
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME,
              property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = Conference.class, name = "conference"),
        @JsonSubTypes.Type(value = Concert.class, name = "concert")
})
public abstract class Evenement implements EvenementObservable {
    private String id;
    private String nom;
    private LocalDateTime date;
    private String lieu;
    private int capaciteMax;
    private boolean annule;

    @JsonIgnore
    private List<ParticipantObserver> participants = new ArrayList<>();

    public Evenement() {}

    public Evenement(String id, String nom, LocalDateTime date, String lieu, int capaciteMax) {
        this.id = id;
        this.nom = nom;
        this.date = date;
        this.lieu = lieu;
        this.capaciteMax = capaciteMax;
    }

    // Méthodes "observateur" (Pattern Observer)
    @Override
    public void ajouterObservateur(ParticipantObserver observateur) {
        participants.add(observateur);
    }

    @Override
    public void supprimerObservateur(ParticipantObserver observateur) {
        participants.remove(observateur);
    }

    @Override
    public void notifierObservateurs(String message) {
        participants.forEach(p -> p.recevoir(message));
    }

    // Logique métier
    public void ajouterParticipant(Participant participant)
            throws ParticipantDejaInscritException, CapaciteMaxAtteinteException {
        if (annule) throw new IllegalStateException("Événement annulé");
        if (participants.contains(participant)) {
            throw new ParticipantDejaInscritException(participant.getNom());
        }
        if (participants.size() >= capaciteMax) {
            throw new CapaciteMaxAtteinteException();
        }
        ajouterObservateur(participant);
        participant.recevoir("Inscription confirmée à: " + nom);
    }

    public void retirerParticipant(Participant participant) {
        if (annule) throw new IllegalStateException("Événement annulé");
        if (!participants.contains(participant)) {
            throw new IllegalArgumentException("Participant non inscrit");
        }
        supprimerObservateur(participant);
        participant.recevoir("Désinscription confirmée de: " + nom);
        notifierObservateurs(participant.getNom() + " a quitté l'événement");
    }

    public void annuler() {
        annule = true;
        notifierObservateurs("Événement annulé: " + nom);
    }


    // Getters/Setters
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public String getNom() { return nom; }
    public void setNom(String nom) { this.nom = nom; }
    public LocalDateTime getDate() { return date; }
    public void setDate(LocalDateTime date) { this.date = date; }
    public String getLieu() { return lieu; }
    public void setLieu(String lieu) { this.lieu = lieu; }
    public int getCapaciteMax() { return capaciteMax; }
    public void setCapaciteMax(int capaciteMax) { this.capaciteMax = capaciteMax; }
    public boolean isAnnule() { return annule; }
    public void setAnnule(boolean annule) { this.annule = annule; }
    public List<ParticipantObserver> getParticipants() { return participants; }
    public void setParticipants(List<ParticipantObserver> participants) { this.participants = participants; }

    /**
     * Affiche les détails communs à tous les événements
     */
    public void afficherDetails() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        System.out.println("=== Détails de l'événement ===");
        System.out.println("ID: " + id);
        System.out.println("Nom: " + nom);
        System.out.println("Date: " + date.format(formatter));
        System.out.println("Lieu: " + lieu);
        System.out.println("Capacité max: " + capaciteMax + " personnes");
        System.out.println("Statut: " + (annule ? "ANNULÉ" : "ACTIF"));
        System.out.println("Participants inscrits: " + participants.size());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        return Objects.equals(id, ((Evenement) o).id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}