interface Notifier {
    void sendNotification(String recipientEmail, String Message);
    void sendNotification(String recipientEmail, String senderEmail, String Message);
}
