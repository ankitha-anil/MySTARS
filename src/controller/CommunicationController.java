package controller;

import entity.User;

import static boundary.MyStarsInterface.*;

/**
 * Class that manages the notifications to be sent to student and admin
 *
 * @author Anon
 */
public class CommunicationController {

    /**
     * instance objectEntityController will be used for retieving a student/admin from their respective lists
     */

    ObjectEntityController objectEntityController;

    /**
     * Sends a notification to student
     *
     * @param receiver student who will receive the notification
     * @param subject  subject of the notification
     * @param body     content of the notification
     * @param notifier object that implements the notifier interface
     */
    public void communicateToStudent(String receiver, String subject, String body, Notifier notifier) {
        objectEntityController = new StudentRecordsMgr();
        User student = (User) objectEntityController.getObjectFromList(receiver);
        if (student == null) {
            System.out.println(RED + "No such student exists" + RESET);
            return;
        }
        notifier.sendMessage(student, subject, body);
    }

    /**
     * Sends a notification to an admin
     *
     * @param receiver admin who will receive the notification
     * @param subject  subject of the notifcation
     * @param body     body of the notification
     * @param notifier object that implements the notifier interface
     */
    public void communicateToAdmin(String receiver, String subject, String body, Notifier notifier) {
        ObjectEntityController objectEntityController = new AdminMgr();
        User admin = (User) objectEntityController.getObjectFromList(receiver);
        if (admin == null) {
            System.out.println(RED + "No such admin exists" + RESET);
            return;
        }
        notifier.sendMessage(admin, subject, body);
    }
}
