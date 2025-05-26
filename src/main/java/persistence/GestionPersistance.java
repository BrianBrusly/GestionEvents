package persistence;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import models.Evenement;
import models.Participant;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class GestionPersistance {
    private static final String DOSSIER_RESSOURCES = "src/main/resources/";
    private static final String FICHIER_EVENEMENTS = "evenements.json";
    private static final String FICHIER_PARTICIPANTS = "participants.json";

    private final ObjectMapper objectMapper;
    private final File fichierEvenements;
    private final File fichierParticipants;

    public GestionPersistance() {
        this.objectMapper = new ObjectMapper();
        this.objectMapper.registerModule(new JavaTimeModule()); // Pour gérer LocalDateTime

        this.fichierEvenements = new File(DOSSIER_RESSOURCES + FICHIER_EVENEMENTS);
        this.fichierParticipants = new File(DOSSIER_RESSOURCES + FICHIER_PARTICIPANTS);

        initialiserFichiers();
    }

    // Méthodes publiques

    public void sauvegarderEvenements(List<Evenement> evenements) {
        sauvegarder(evenements, fichierEvenements);
    }

    public List<Evenement> chargerEvenements() {
        return charger(Evenement.class, fichierEvenements);
    }

    public void sauvegarderParticipants(List<Participant> participants) {
        sauvegarder(participants, fichierParticipants);
    }

    public List<Participant> chargerParticipants() {
        return charger(Participant.class, fichierParticipants);
    }

    // Méthodes génériques privées

    private <T> void sauvegarder(List<T> data, File fichier) {
        try {
            // Crée le dossier parent si nécessaire
            fichier.getParentFile().mkdirs();

            objectMapper.writeValue(fichier, data);
            System.out.println("Sauvegarde réussie dans " + fichier.getPath());
        } catch (IOException e) {
            System.err.println("Erreur de sauvegarde: " + e.getMessage());
        }
    }

    private <T> List<T> charger(Class<T> clazz, File fichier) {
        if (!fichier.exists()) {
            System.out.println("Fichier non trouvé. Retour d'une liste vide.");
            return new ArrayList<>();
        }

        try {
            CollectionType type = objectMapper.getTypeFactory()
                    .constructCollectionType(List.class, clazz);
            return objectMapper.readValue(fichier, type);
        } catch (IOException e) {
            System.err.println("Erreur de chargement: " + e.getMessage());
            return new ArrayList<>();
        }
    }

    private void initialiserFichiers() {
        try {
            if (!fichierEvenements.exists()) {
                fichierEvenements.getParentFile().mkdirs();
                objectMapper.writeValue(fichierEvenements, Collections.emptyList());
            }

            if (!fichierParticipants.exists()) {
                fichierParticipants.getParentFile().mkdirs();
                objectMapper.writeValue(fichierParticipants, Collections.emptyList());
            }
        } catch (IOException e) {
            System.err.println("Erreur d'initialisation: " + e.getMessage());
        }
    }
}