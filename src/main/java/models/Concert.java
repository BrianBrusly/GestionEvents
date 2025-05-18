package models;

import java.time.LocalDateTime;

public class Concert extends Evenement {
    private String artiste;
    private String genreMusical;

    public Concert(String id, String nom, LocalDateTime date, String lieu, int capaciteMax, String artiste, String genreMusical) {
        super(id, nom, date, lieu, capaciteMax);
        this.artiste = artiste;
        this.genreMusical = genreMusical;
    }

    @Override
    public void ajouterParticipant(Participant participant) {
        if (participants.size() < capaciteMax) {
            participants.add(participant);
            System.out.println(participant.getNom() + " inscrit au concert !");
        } else {
            System.out.println("Concert complet !");
        }
    }

    @Override
    public void annuler() {
        System.out.println("Le concert est annulé.");
    }
}
