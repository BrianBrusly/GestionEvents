package models;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Conference extends Evenement {
    private String theme;
    private List<Intervenant> intervenants;

    public Conference(String id, String nom, LocalDateTime date, String lieu, int capaciteMax, String theme) {
        super(id, nom, date, lieu, capaciteMax);
        this.theme = theme;
        this.intervenants = new ArrayList<>();
    }

    public void ajouterIntervenant(Intervenant intervenant) {
        intervenants.add(intervenant);
    }

    @Override
    public void ajouterParticipant(Participant participant) {
        if (participants.size() < capaciteMax) {
            participants.add(participant);
            System.out.println(participant.getNom() + " inscrit à la conférence !");
        } else {
            System.out.println("Capacité maximale atteinte !");
        }
    }

    @Override
    public void annuler() {
        System.out.println("La conférence est annulée.");
    }
}
