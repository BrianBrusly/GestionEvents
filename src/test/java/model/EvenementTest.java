package model;

import models.Concert;
import models.Conference;
import models.Evenement;
import models.Participant;
import exceptions.CapaciteMaxAtteinteException;
import exceptions.ParticipantDejaInscritException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class EvenementTest {
    // Déclaration des objets qui seront utilisés dans les tests
    private Evenement conference;
    private Participant participant1;
    private Participant participant2;

    /**
     * Initialisation avant chaque test
     * Crée une nouvelle conférence et deux participants
     */
    @BeforeEach
    void setUp() {
        // Création d'une conférence avec une capacité de 2 personnes
        conference = new Conference(
                "1",
                "NAVY-Assembly",
                LocalDateTime.now().plusDays(1),
                "Yaounde",
                2,
                "Assembly Meeting"
        );

        // Création de deux participants pour les tests
        participant1 = new Participant("P1", "Brian Brusly", "brianbrusly@gmail.com");
        participant2 = new Participant("P2", "Nathan Heat", "nathanheat@gmail.com");
    }

    /**
     * Test de base des propriétés d'un événement
     */
    @Test
    void testProprietesEvenement() {
        assertEquals("NAVY-Assembly", conference.getNom());
        assertEquals("Yaounde", conference.getLieu());
        assertEquals(2, conference.getCapaciteMax());
        assertFalse(conference.isAnnule());
    }

    /**
     * Test de l'ajout de participants
     */
    @Test
    void testAjoutParticipants() throws Exception {
        // Ajout du premier participant
        conference.ajouterParticipant(participant1);
        assertTrue(conference.getParticipants().contains(participant1));

        // Vérifie qu'on ne peut pas ajouter le même participant deux fois
        assertThrows(ParticipantDejaInscritException.class, () -> {
            conference.ajouterParticipant(participant1);
        });

        // Ajout du deuxième participant
        conference.ajouterParticipant(participant2);

        // Vérifie qu'on ne peut pas dépasser la capacité
        Participant participant3 = new Participant("P3", "MASO", "MASO@gmail.com");
        assertThrows(CapaciteMaxAtteinteException.class, () -> {
            conference.ajouterParticipant(participant3);
        });
    }

    /**
     * Test du retrait de participants
     */
    @Test
    void testRetraitParticipants() throws Exception {
        // Ajout puis retrait d'un participant
        conference.ajouterParticipant(participant1);
        conference.retirerParticipant(participant1);
        assertFalse(conference.getParticipants().contains(participant1));

        // Vérifie qu'on ne peut pas retirer un participant non inscrit
        assertThrows(IllegalArgumentException.class, () -> {
            conference.retirerParticipant(participant2);
        });
    }

    /**
     * Test de l'annulation d'un événement
     */
    @Test
    void testAnnulation() throws Exception {
        conference.ajouterParticipant(participant1);
        conference.annuler();

        assertTrue(conference.isAnnule());

        // Vérifie qu'on ne peut plus ajouter de participants
        assertThrows(IllegalStateException.class, () -> {
            conference.ajouterParticipant(participant2);
        });
    }

    /**
     * Test de l'égalité entre événements
     */
    @Test
    void testEgalite() {
        // Deux événements avec le même ID sont égaux
        Evenement conference2 = new Conference("1", "NAVY-Corp", LocalDateTime.now(), "Douala", 10, "Désarmement Nucléaire");
        assertEquals(conference, conference2);

        // Deux événements avec des IDs différents sont différents
        Evenement concert = new Concert("2", "Concert", LocalDateTime.now(), "Olembe", 10, "Brian Brusly", "Rumba");
        assertNotEquals(conference, concert);
    }
}