package models;

public class Intervenant extends Participant {
    private String specialite;
    private String biographie;

    // Constructeurs
    public Intervenant() {
        super();
    }

    public Intervenant(String id, String nom, String email, String specialite, String biographie) {
        super(id, nom, email);  // Initialise les champs de Participant
        this.specialite = specialite;
        this.biographie = biographie;
    }

    // Méthodes spécifiques
    public String presenter() {
        return String.format("%s - Spécialité : %s \n Bio : %s",
                getNom(), specialite, biographie);
    }

    // Getters/Setters
    public String getSpecialite() {
        return specialite;
    }

    public void setSpecialite(String specialite) {
        this.specialite = specialite;
    }

    public String getBiographie() {
        return biographie;
    }

    public void setBiographie(String biographie) {
        this.biographie = biographie;
    }
}