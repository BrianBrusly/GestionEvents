package models;

public class Intervenant {
    private final String id;
    private final String nom;
    private final String specialite;

    public Intervenant(String id, String nom, String specialite) {
        this.id = id;
        this.nom = nom;
        this.specialite = specialite;
    }

    public String getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public String getSpecialite() {
        return specialite;
    }

    @Override
    public String toString() {
        return "Intervenant{id='" + id + "', nom='" + nom + "', specialite='" + specialite + "'}";
    }
}
