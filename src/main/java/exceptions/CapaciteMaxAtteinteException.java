package exceptions;

public class CapaciteMaxAtteinteException extends Exception {
    public CapaciteMaxAtteinteException() {
        super("La capacité maximale de l'événement est atteinte");
    }

    public CapaciteMaxAtteinteException(String message) {
        super(message);
    }
}