package exceptions;

public class EvenementNonTrouveException extends Exception {
    public EvenementNonTrouveException() {
        super("L'événement n'a pas été trouvé");
    }

    public EvenementNonTrouveException(String message) {
        super(message);
    }
}