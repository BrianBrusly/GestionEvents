package gestion;

import exceptions.EvenementDejaExistantException;
import exceptions.EvenementNonTrouveException;
import models.Conference;
import models.Evenement;
import models.Participant;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class GestionEvenementsTest {
    private GestionEvenements gestion;
    private Evenement evenement;
    private Participant participant;

    @BeforeEach
    void setUp() {
        gestion = GestionEvenements.getInstance();
        evenement = new Conference("1", "Conférence Tech", LocalDateTime.now(), "Yaounde", 100, "Technologies Émergentes");
        participant = new Participant("p1", "Brian Brusly", "brianbrusly@mail.com");
    }

    @Test
    void testAjouterEvenement() throws EvenementDejaExistantException {
        gestion.ajouterEvenement(evenement);
        assertDoesNotThrow(() -> gestion.getEvenement("1"));
    }

    @Test
    void testAjouterEvenementExistant() throws EvenementDejaExistantException {
        gestion.ajouterEvenement(evenement);
        assertThrows(EvenementDejaExistantException.class, () -> gestion.ajouterEvenement(evenement));
    }

    @Test
    void testInscriptionDesinscription() throws Exception {
        // Test d'utilisation
        gestion.inscrireParticipant("conference 1", participant);
        gestion.desinscrireParticipant("conference 1", participant);

        // Vérification avec assert
        assertDoesNotThrow(() -> gestion.desinscrireParticipant("conference 1", participant));
    }

    @Test
    void testRecherche() {
        List<Evenement> resultats = gestion.rechercherParLieu("Yaounde");
        assertEquals(1, resultats.size());
    }

    @Test
    void testSupprimerEvenement() throws EvenementDejaExistantException, EvenementNonTrouveException {
        gestion.ajouterEvenement(evenement); // Ajouter un événement
        gestion.supprimerEvenement(evenement.getId()); // Supprimer l'événement ajouté
        assertThrows(EvenementNonTrouveException.class, () -> gestion.getEvenement(evenement.getId())); // Vérification
    }

    @Test
    void testSupprimerEvenementNonTrouve() {
        assertThrows(EvenementNonTrouveException.class, () -> gestion.supprimerEvenement("nonexistent_id"));
    }
}