package persistance;

import models.Conference;
import models.Evenement;
import models.Participant;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import persistence.GestionPersistance;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class GestionPersistanceTest {
    private GestionPersistance gestionPersistance;
    private List<Evenement> evenementsTest;
    private List<Participant> participantsTest;

    /**
     * Préparation des données de test
     */
    @BeforeEach
    void setUp() {
        // Initialisation de la gestion de persistance
        gestionPersistance = new GestionPersistance();

        // Création des données de test
        evenementsTest = new ArrayList<>();
        evenementsTest.add(new Conference(
                "1",
                "Test Conférence",
                LocalDateTime.now(),
                "Yaoundé",
                50,
                "Test"
        ));

        participantsTest = new ArrayList<>();
        participantsTest.add(new Participant("P1", "Jean Test", "jean@test.com"));
    }

    /**
     * Test du cycle sauvegarde/chargement des événements
     */
    @Test
    void testSauvegardeEtChargementEvenements() {
        // Test de la sauvegarde
        gestionPersistance.sauvegarderEvenements(evenementsTest);

        // Test du chargement
        List<Evenement> evenementsCharges = gestionPersistance.chargerEvenements();

        assertNotNull(evenementsCharges);
        assertFalse(evenementsCharges.isEmpty());
        assertEquals(evenementsTest.get(0).getId(), evenementsCharges.get(0).getId());
    }

    /**
     * Test du cycle sauvegarde/chargement des participants
     */
    @Test
    void testSauvegardeEtChargementParticipants() {
        // Test de la sauvegarde
        gestionPersistance.sauvegarderParticipants(participantsTest);

        // Test du chargement
        List<Participant> participantsCharges = gestionPersistance.chargerParticipants();

        assertNotNull(participantsCharges);
        assertFalse(participantsCharges.isEmpty());
        assertEquals(participantsTest.get(0).getId(), participantsCharges.get(0).getId());
    }

    /**
     * Test du chargement avec fichiers vides
     */
    @Test
    void testChargementFichiersVides() {
        // Test du chargement initial (fichiers vides)
        List<Evenement> evenements = gestionPersistance.chargerEvenements();
        List<Participant> participants = gestionPersistance.chargerParticipants();

        assertTrue(evenements.isEmpty());
        assertTrue(participants.isEmpty());
    }
}