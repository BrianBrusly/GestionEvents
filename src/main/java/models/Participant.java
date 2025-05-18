package models;

public class Participant {
    private String id;
    private String nom;
    private String email;

    public Participant(String id, String nom, String email) {
        this.id = id;
        this.nom = nom;
        this.email = email;
    }

    public String getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public String toString() {
        return "Participant{" +
                "id='" + id + '\'' +
                ", nom='" + nom + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
