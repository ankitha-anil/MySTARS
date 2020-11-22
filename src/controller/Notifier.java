package controller;

import entity.User;

/**
 * @author Anon
 * Interface that can send notifications
 */
public interface Notifier {
    /**
     * Sends a notification to a user
     * @param recipient recipient of the notification
     * @param subject subject of the notification
     * @param message message in the notification
     */
    void sendMessage(User recipient, String subject, String message);
}