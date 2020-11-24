package controller;

import entity.User;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import static boundary.MyStarsInterface.*;

/**
 * Class that is responsible for sending emails to students and admins
 *
 * @author Anon
 */
public class EmailMgr implements Notifier {

    /**
     * string containing the email id the system will send emails from
     * string containing the password of the email id the system will send emails from
     */
    private static final String systemEmail = "starsplanner@gmail.com";
    private static final String password = "$tarsplanner21";

    /**
     * Sends an email to a user
     *
     * @param recipient recipient of the email
     * @param subject   subject of the email
     * @param body      body of the email
     */
    public void sendMessage(User recipient, String subject, String body) {

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(systemEmail, password);
                    }
                });

        try {

            String receiverEmail = recipient.getEmailID();
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(systemEmail)); // sender email
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(receiverEmail)); // receiver email
            message.setSubject(subject);
            message.setText(body);

            Transport.send(message);

            System.out.println(GREEN + "Notification Sent to " + receiverEmail + RESET);
            System.out.println();

        } catch (MessagingException e) {
            System.out.println(RED + "System error: Could not send email" + RESET);
        } catch (NoClassDefFoundError e) {
            System.out.println(RED + "System error: Could not send email" + RESET);
        }
    }
}