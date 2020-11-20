package controller;

import entity.User;

public interface Notifier {
    void sendMessage(User recipient, String subject, String message);
}