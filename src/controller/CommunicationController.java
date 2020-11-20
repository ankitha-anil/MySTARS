package controller;

import entity.User;

public class CommunicationController {
    ObjectEntityController objectEntityController;

    public void communicateToStudent(String receiver, String subject, String body, Notifier notifier) {
        objectEntityController = new StudentRecordsMgr();
        User student = (User) objectEntityController.getObjectFromList(receiver);
        if (student == null) {
            System.out.println("No such student exists");
            return;
        }
        notifier.sendMessage(student, subject, body);
    }

    public void communicateToAdmin(String receiver, String subject, String body, Notifier notifier) {
        ObjectEntityController objectEntityController = new AdminMgr();
        User admin = (User) objectEntityController.getObjectFromList(receiver);
        if (admin == null) {
            System.out.println("No such admin exists");
            return;
        }
        notifier.sendMessage(admin, subject, body);
    }
}
