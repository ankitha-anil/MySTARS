package boundary;

import controller.CourseMgr;
import actor.Actor;
import controller.UpdateManager;

import java.util.Scanner;

public class IndexUpdateInterface {
    public static void main(String[] args, Actor actor, String courseCode) {
        CourseMgr courseMgr = new CourseMgr();
        UpdateManager updateManager = new UpdateManager();
        int choice = 0;
        Scanner sc = new Scanner(System.in);
        do {
            String indexNumber;
            System.out.println("Enter the index number to update... Press 0 to go back");
            indexNumber = sc.next();
            if (indexNumber.equals("0"))
                break;
            if (!courseMgr.checkIndex(courseCode, indexNumber)) {
                System.out.println("This index number doesn't exist");
                break;
            } else {
                String newIndexNumber;
                int vacancy;
                System.out.println("Choose the attribute to update");
                System.out.println("1: Index number");
                System.out.println("2: Vacancy");
                try {
                    choice = sc.nextInt();
                } catch (Exception e) {
                    System.out.println(e);
                    sc.nextLine();
                    choice = -1;
                }

                boolean goBack = false;
                switch (choice) {
                    case 0:
                        break;
                    case 1:
                        System.out.println("Enter the new index number");
                        newIndexNumber = sc.next();
                        updateManager.updateIndexNumber(courseCode, indexNumber, newIndexNumber);
                        goBack = true;
                        break;
                    case 2:
                        System.out.println("Enter the new vacancy");
                        vacancy = sc.nextInt();
                        updateManager.updateIndexVacancy(courseCode, indexNumber, vacancy);
                        goBack = true;
                        break;
                    default:
                        System.out.println("Invalid choice");
                }
                if (goBack)
                    break;
            }
        } while (choice != 0);
    }
}
