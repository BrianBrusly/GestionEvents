package factory;

import service.EmailNotificationService;
import service.NotificationService;
import service.SMSNotificationService;

public class NotificationFactory {
    public static NotificationService createNotificationService(String type) {
        return switch (type.toLowerCase()) {
            case "email" -> new EmailNotificationService();
            case "sms" -> new SMSNotificationService();
            default -> throw new IllegalArgumentException("Type de notification non supporté");
        };
    }
}