package models;

@FunctionalInterface
public interface NotificationService {
    void envoyerNotification(String message);
}
