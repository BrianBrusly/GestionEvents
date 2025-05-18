package models;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

public abstract class Evenement {
    protected final String id;
    protected final String nom;
    protected final LocalDateTime date;
    protected final String lieu;
    protected final int capaciteMax;
    protected final Set<Participant> participants;

    public Evenement(String id, String nom, LocalDateTime date, String lieu, int capaciteMax) {
        this.id = id;
        this.nom = nom;
        this.date = date;
        this.lieu = lieu;
        this.capaciteMax = capaciteMax;
        this.participants = new HashSet<>();
    }

    public abstract void ajouterParticipant(Participant participant);
    public abstract void annuler();

    public void retirerParticipant(Participant participant) {
        if (participants.contains(participant)) {
            participants.remove(participant);
            System.out.println(participant.getNom() + " a été retiré de l'événement.");
        } else {
            System.out.println("Participant non trouvé dans l'événement.");
        }
    }

    public void afficherDetails() {
        System.out.println("Événement : " + nom);
        System.out.println("Lieu : " + lieu);
        System.out.println("Date : " + date);
        System.out.println("Participants inscrits : " + participants.size() + "/" + capaciteMax);
    }

    public String getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public String getLieu() {
        return lieu;
    }

    public int getCapaciteMax() {
        return capaciteMax;
    }

    public Set<Participant> getParticipants() {
        return new HashSet<>(participants);
    }
}
