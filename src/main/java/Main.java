import exceptions.*;
import models.*;
import gestion.GestionEvenements;
import persistence.GestionPersistance;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static final GestionEvenements gestion = GestionEvenements.getInstance();
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        chargerDonnees();
        afficherMenuPrincipal();
        scanner.close();
    }

    private static void chargerDonnees() {
        gestion.charger();
        System.out.println("Donnees chargees");
    }

    private static void afficherMenuPrincipal() {
        while (true) {
            System.out.println("\nMENU PRINCIPAL");
            System.out.println("1. Gerer les evenements");
            System.out.println("2. Gerer les participants");
            System.out.println("3. Rechercher");
            System.out.println("4. Sauvegarder et quitter");
            System.out.print("Votre choix : ");

            String choix = scanner.nextLine();
            switch (choix) {
                case "1" -> menuEvenements();
                case "2" -> menuParticipants();
                case "3" -> menuRecherche();
                case "4" -> { sauvegarder(); return; }
                default -> System.out.println("Choix invalide");
            }
        }
    }

    private static void menuEvenements() {
        System.out.println("\nGESTION EVENEMENTS");
        System.out.println("1. Creer un concert");
        System.out.println("2. Creer une conference");
        System.out.println("3. Lister les evenements");
        System.out.println("4. Annuler un evenement");
        System.out.print("Votre choix : ");

        String choix = scanner.nextLine();
        switch (choix) {
            case "1" -> creerConcert();
            case "2" -> creerConference();
            case "3" -> listerEvenements();
            case "4" -> annulerEvenement();
            default -> System.out.println("Choix invalide");
        }
    }

    private static void creerConcert() {
        try {
            System.out.print("Nom du concert : ");
            String nom = scanner.nextLine();

            Concert concert = new Concert(
                    "C" + System.currentTimeMillis(),
                    nom,
                    LocalDateTime.now().plusDays(7),
                    "Salle Pleyel",
                    500,
                    "Artiste Principal",
                    "Rock"
            );

            gestion.ajouterEvenement(concert);
            System.out.println("Concert cree avec succes");
        } catch (EvenementDejaExistantException e) {
            System.out.println("Erreur : " + e.getMessage());
        }
    }

    private static void creerConference() {
        try {
            System.out.print("Theme de la conference : ");
            String theme = scanner.nextLine();

            Conference conference = new Conference(
                    "Conf" + System.currentTimeMillis(),
                    "Conference sur " + theme,
                    LocalDateTime.now().plusDays(14),
                    "Centre de congres",
                    200,
                    theme
            );

            gestion.ajouterEvenement(conference);
            System.out.println("Conference creee avec succes");
        } catch (EvenementDejaExistantException e) {
            System.out.println("Erreur : " + e.getMessage());
        }
    }

    private static void listerEvenements() {
        System.out.println("\nLISTE DES EVENEMENTS");
        gestion.getEvenementsNonAnnules().forEach(e -> {
            System.out.println("- " + e.getNom() + " (" + e.getId() + ")");
        });
    }

    private static void annulerEvenement() {
        listerEvenements();
        System.out.print("ID de l'evenement a annuler : ");
        String id = scanner.nextLine();

        try {
            gestion.getEvenement(id).annuler();
            System.out.println("Evenement annule avec succes");
        } catch (EvenementNonTrouveException e) {
            System.out.println("Erreur : " + e.getMessage());
        }
    }

    private static void menuParticipants() {
        System.out.println("\nGESTION PARTICIPANTS");
        System.out.println("1. Inscrire un participant");
        System.out.println("2. Desinscrire un participant");
        System.out.print("Votre choix : ");

        String choix = scanner.nextLine();
        switch (choix) {
            case "1" -> inscrireParticipant();
            case "2" -> desinscrireParticipant();
            default -> System.out.println("Choix invalide");
        }
    }

    private static void inscrireParticipant() {
        listerEvenements();
        System.out.print("ID de l'evenement : ");
        String eventId = scanner.nextLine();

        System.out.print("Nom du participant : ");
        String nom = scanner.nextLine();

        try {
            Participant p = new Participant("P" + System.currentTimeMillis(), nom, nom.toLowerCase() + "@mail.com");
            gestion.inscrireParticipant(eventId, p);
            System.out.println("Participant inscrit avec succes");
        } catch (Exception e) {
            System.out.println("Erreur : " + e.getMessage());
        }
    }

    private static void desinscrireParticipant() {
        listerEvenements();
        System.out.print("ID de l'évènement : ");
        String eventId = scanner.nextLine();

        System.out.print("ID du participant : ");
        String participantId = scanner.nextLine();

        try {
            Participant p = new Participant(participantId, "", "");
            gestion.desinscrireParticipant(eventId, p);
            System.out.println("Participant désinscrit avec succès");
        } catch (Exception e) {
            System.out.println("Erreur : " + e.getMessage());
        }
    }

    private static void menuRecherche() {
        System.out.println("\nRECHERCHE");
        System.out.println("1. Par lieu");
        System.out.println("2. Par nom");
        System.out.print("Votre choix : ");

        List<Evenement> resultats = switch (scanner.nextLine()) {
            case "1" -> {
                System.out.print("Lieu recherche : ");
                yield gestion.rechercherParLieu(scanner.nextLine());
            }
            case "2" -> {
                System.out.print("Nom recherche : ");
                yield gestion.rechercherParNom(scanner.nextLine());
            }
            default -> { System.out.println("Choix invalide"); yield List.of(); }
        };

        if (resultats.isEmpty()) {
            System.out.println("Aucun résultat trouve");
        } else {
            resultats.forEach(e -> System.out.println("- " + e.getNom()));
        }
    }

    private static void sauvegarder() {
        gestion.sauvegarder();
        System.out.println("Donnees sauvegardées");
    }
}