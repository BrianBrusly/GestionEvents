package models;

import observer.ParticipantObserver;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonSubTypes;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,  // On utilise le nom de la classe coe Id
        property = "type"         // Variable json spécifiant le type d'une instance de participants
)
@JsonSubTypes({
        @JsonSubTypes.Type(value = Participant.class, name = "participant"),
        @JsonSubTypes.Type(value = Organisateur.class, name = "organisateur"),
        @JsonSubTypes.Type(value = Intervenant.class, name = "intervenant")
})
public class Participant implements ParticipantObserver {
    private String id;
    private String nom;
    private String email;

    @JsonIgnore
    private List<String> notifications = new ArrayList<>();

    // Constructeurs
    public Participant() {}

    public Participant(String id, String nom, String email) {
        this.id = id;
        this.nom = nom;
        this.email = email;
    }

    // Méthode d'observation
    @Override
    public void recevoir(String message) {
        notifications.add(message);
        System.out.println("[" + nom + "] Notification : " + message);
    }

    // Méthode utilitaire
    public void afficherNotifications() {
        System.out.println("Notifications de " + nom + ":");
        notifications.forEach((msg) -> System.out.println("• " + msg));
    }

    // Getters/Setters
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getNom() {
        return nom;
    }
    public void setNom(String nom) {
        this.nom = nom;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public List<String> getNotifications() {
        return notifications;
    }
    public void setNotifications(List<String> notifications) {
        this.notifications = notifications;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        return Objects.equals(id, ((Participant) o).id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}