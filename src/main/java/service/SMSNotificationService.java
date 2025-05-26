package service;

import java.util.concurrent.CompletableFuture;

public class SMSNotificationService implements NotificationService {
    @Override
    public void envoyerNotification(String destinataire, String message) {
        System.out.println("SMS envoyé à " + destinataire + " : " + message);
    }

    @Override
    public CompletableFuture<Boolean> envoyerNotificationAsync(String destinataire, String message) {
        return CompletableFuture.supplyAsync(() -> {
            envoyerNotification(destinataire, message);
            return true;
        });
    }
}