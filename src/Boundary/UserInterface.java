package Boundary;

import Controller.AdminController;
import Controller.FileMgr;
import Controller.LoginMgr;
import Controller.StudentController;
import Controller.SystemMgr;
import Controller.UserController;
import Entity.Admin;
import Entity.Student;
import Entity.User;
// import javax.swing.*; // for *** for password

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;


public class UserInterface {

    public static String userName,passWord;
    public static int user;
    public static Scanner sc = new Scanner(System.in);
    private static ArrayList<Object> User;
    private static FileMgr fileMgr;
    private static SystemMgr systemMgr;
    public static String fileName;

    public static void main(String[] args) throws IOException {
        while(true) {
           int user = login();
           if(user==1)
               fileName="admin.dat";
           else if(user==2)
               fileName="student.dat";
           else
                {System.out.println("Wrong input");
                continue;
            }
           if (!(LoginMgr.loginCheck(userName, passWord)))
               System.out.println("The username or password you entered is incorrect, PLease try again");
           else  
           {
               System.out.println("Login is successful!");
               fileMgr = new FileMgr();
               systemMgr = new SystemMgr();

               User = fileMgr.loadObjects(fileName);
               switch(user)
               {
                   case 1: AdminController adminCtrl = new AdminController(); break;

                   case 2: StudentController studentCtrl = new StudentController();break;
               }

               
           }
           
       }

    }


    public static int login(){
        System.out.println("        Welcome to MyStars Planner        \n");
        System.out.println("Select your domain\n");
        System.out.println("1: Admin ");
        System.out.println("2: Student ");
        int user = sc.nextInt();

        System.out.println("Enter NetworkID: ");
        userName = sc.next();

        System.out.println("Enter password: ");
        passWord = sc.next();

        return user; 

    }
    // must call admin controller and student controller functions. someone please do
    public static void openMenu(AdminController adminCtrl) {
        {
            System.out.println("Select your task: \n");
            System.out.println("1. Edit Access Period");
            System.out.println("2. Add a new student");
            System.out.println("3. Remove an existing student");
            System.out.println("4. Update a student");
            System.out.println("5. Add a new course");
            System.out.println("6. Remove an existing course");
            System.out.println("7. Update a course");
            System.out.println("8. Check available slots for an index number");
            System.out.println("9. Print student list by index number");
            System.out.println("10. Print student list by course");
        }

        Scanner sc = new Scanner(System.in);
        int sel = sc.nextInt();
        switch (sel) {
            case 1:
                System.out.println(
                        "Enter access period Start Date yyyy-mm-dd \n end date yyyy-mm-dd\n starting time\n ending time\n ");
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                String startDateStr = sc.next();
                LocalDate startDate = LocalDate.parse(startDateStr, formatter);
                String endDateStr = sc.next();
                LocalDate endDate = LocalDate.parse(endDateStr, formatter);
                DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");
                String startTimeStr = sc.next();
                LocalTime startTime = LocalTime.parse(startTimeStr, timeFormatter);
                String endTimeStr = sc.next();
                LocalTime endTime = LocalTime.parse(endTimeStr, timeFormatter);
                adminCtrl.editAccessPeriod(startTime, endTime, startDate, endDate);
                break;

            case 2:
                System.out.println("Enter the following details of the student");
                System.out
                        .println("Name \n Network name \n matric number \n password \n email \n gender \n nationality");
                System.out.println("school \n Year of Study \n");
                String name = sc.next();
                String networkName = sc.next();
                String matric = sc.next();
                String password = sc.next();
                String email = sc.next();
                String gender = sc.next();
                String nationality = sc.next();
                String school = sc.next();
                String studyYear = sc.next();
                // must call admin ctrl add student
                try {
                    LoginMgr.createUser(networkName, password);
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                    break;
            
            case 3: System.out.println("Enter network name of student to be removed");
                    String netName = sc.next(); break;

            case 4: System.out.println("Enter network name of student to be updated");
                    String existingNetName = sc.next(); break;

            case 5: System.out.println("Enter the details of new course: ");
                    System.out.println("Enter the course code");
                    int newCourseCode = sc.nextInt();
                    System.out.println("Enter the Course name");
                    String newCourseName = sc.next();
                    System.out.println("Enter the academic units");
                    int AU = sc.nextInt();
                    System.out.println("Enter school name");
                    String newschool = sc.next();break;
            
            case 6: System.out.println("Enter the course code of the course to be deleted ");
                    String CourseCode = sc.next();break; 
            
            case 7: System.out.println("Enter the course code of the course to be updated ");
                    String existCourseCode = sc.next();break; 
            
            case 8: System.out.println("Enter the index to check availablity ");
                    int checkIndex = sc.nextInt();break; 

            case 9: System.out.println("Enter the index to print Student List ");
                    int printStdIndex = sc.nextInt();break; 
            
            case 10: System.out.println("Enter the course code to print Student List ");
                     String printStdCourse = sc.next();break; 
            
            default: System.out.println("Wrong input");
                    



        }



    }

}


