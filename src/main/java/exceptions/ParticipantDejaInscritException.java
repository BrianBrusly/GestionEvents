package exceptions;

public class ParticipantDejaInscritException extends Exception {
    public ParticipantDejaInscritException() {
        super("Le participant est déjà inscrit à cet événement");
    }

    public ParticipantDejaInscritException(String message) {
        super(message);
    }
}