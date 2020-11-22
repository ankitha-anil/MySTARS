package controller;

import entity.User;
import static boundary.MyStarsInterface.*;

public class CommunicationController {


    ObjectEntityController objectEntityController;

    public void communicateToStudent(String receiver, String subject, String body, Notifier notifier) {
        objectEntityController = new StudentRecordsMgr();
        User student = (User) objectEntityController.getObjectFromList(receiver);
        if (student == null) {
            System.out.println(RED + "No such student exists" + RESET);
            return;
        }
        notifier.sendMessage(student, subject, body);
    }

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
