package service;

import java.util.concurrent.CompletableFuture;

public interface NotificationService {
    void envoyerNotification(String destinataire, String message);
    CompletableFuture<Boolean> envoyerNotificationAsync(String destinataire, String message);
}