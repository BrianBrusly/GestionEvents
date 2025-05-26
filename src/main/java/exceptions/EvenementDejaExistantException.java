package exceptions;

public class EvenementDejaExistantException extends Exception {
    public EvenementDejaExistantException() {
        super("Un événement avec cet identifiant existe déjà");
    }

    public EvenementDejaExistantException(String message) {
        super(message);
    }
}