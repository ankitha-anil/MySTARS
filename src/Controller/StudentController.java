package Controller;

import Entity.Course;
import Entity.Index;
import Entity.Student;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Scanner;

public class StudentController {

    private FileMgr fileMgr;
    private SystemMgr systemMgr;
    private TimeTableMgr timeTableMgr;
    private ArrayList<Object> courses;
    private ArrayList<Object> students;
    private ArrayList<Object> admins;
    private static final String studentFile = "student.dat";
    private static final String courseFile = "course.dat";
    private static final String adminFile = "admin.dat";

    public StudentController() {
        fileMgr = new FileMgr();
        systemMgr = new SystemMgr();
        students = fileMgr.loadObjects(studentFile);
        courses = fileMgr.loadObjects(courseFile);
        admins= fileMgr.loadObjects(adminFile);
    }

    public void addCourse(Student student, int indexId, String courseId)
    {

        Course c= new Course(courseId);
        Course courseToAdd=(Course)systemMgr.findObject(courses,c);
        Index i= new Index(indexId);
        Index  indexToAdd=(Index)systemMgr.findObject(courseToAdd.getIndexNumberList(),i);
        if(courseToAdd == null)
        {
            System.out.println("Course code does not exist.Please enter the correct one");
            return;
        }

        if(indexToAdd == null)
        {
            System.out.println("Index id does not exist. Please enter the correct one");
            return;
        }

        if(timeTableMgr.checkExistingCourse(student.getIndexonWaitList(),indexToAdd)) //check if already on waiting list for course
        {
            System.out.println("Already on waitlist for this course");
            return;
        }

        if(timeTableMgr.checkExistingCourse(student.getIndexRegistered(),indexToAdd)) //check if already registered for course
        {
            System.out.println("Already registered for this course");
            return;
        }

        if(indexToAdd.isFilled()) // no vacancies, need to add to waiting list if possible
        {
            System.out.println("No vacancies for this course");
            //need to make timetable manager function public

                if(timeTableMgr.checkClash(student.getIndexonWaitList(),indexToAdd) || timeTableMgr.checkClash(student.getIndexRegistered(),indexToAdd) ) //check if there is clash with registered indices or waiting list
                {
                    System.out.println("Clash with registered indices or indices on waitlist");
                    return;

                }
                else
                { // if no clash , update 2 way association of student and index wrt waiting list
                    indexToAdd.addToWaitingList(student);
                    //notification for waitlist


                }
        }

        else{

            else
            {
                if(timeTableMgr.checkClash(student.getIndexonWaitList(),indexToAdd) || timeTableMgr.checkClash(student.getIndexRegistered(),indexToAdd) ) //check if there is clash with registered indices or waiting list
                {
                    System.out.println("Clash with registered indices or indices on waitlist");
                    return;

                }
                else
                { // if no clash , update 2 way assoication of student and index wrt registered list
                    if((student.getMaxAU()-student.getRegisteredAU())< courseToAdd.getAcademicUnits())
                    {
                        System.out.println("Max au limit exceeded. Cannot register for this course");
                        return;
                    }
                    student.addIndexRegistered(index);
                    //add code for notification
                }
            }


        }

        fileMgr.saveObjects(students); //same change made in admin controller?
        fileMgr.saveObjects(courses);
    }

    public void dropCourse(Student student, int indexToDrop, String courseId)
    {
        for(Index index:student.getIndexRegistered())
        {
            if(index.getIndexNumber()==indexToDrop  && index.getCourseCode()==courseId)
            {
                student.removeIndex(index);// need to add code to removeIndex to reduce AUs
                System.out.println("Index"+index.getIndexNumber()+ "removed from registered list");
                //notification

                //give vacancy to first student in waitlist
                Student nowRegistered=index.getWaitingList().poll();
                if(nowRegistered)
                {
                    nowRegistered.addIndexRegistered(index);
                    //notification to newly registered student
                }

                fileMgr.saveObjects(students);
                fileMgr.saveObjects(courses);

                return;
            }
        }

        for(Index index:student.getIndexonWaitList())
        {
            if(index.getIndexNumber()==indexToDrop  && index.getCourseCode()==courseId)
            {
                student.removeIndexFromWaitList(index);
                System.out.println("Index"+index.getIndexNumber()+ "removed from waiting list");
                //notification

                fileMgr.saveObjects(students);
                fileMgr.saveObjects(courses);

                return;
            }
        }
        System.out.println("Index not found in waiting list and registered index list");
    }

    public void printCourses(Student student)
    {
        for(Index registeredIndex:student.getIndexRegistered())
        {
            Course c= new Course(registeredIndex.getCourseCode());
            Course courseReg=(Course)systemMgr.findObject(courses,c);
            System.out.println(registeredIndex.getIndexNumber()+" "+registeredIndex.getCourseCode()+" "+courseReg.getAcademicUnits()+" Registered");
        }

        for(Index waitingListIndex:student.getIndexonWaitingList())
        {
            Course c= new Course(waitingListIndex.getCourseCode());
            Course courseReg=(Course)systemMgr.findObject(courses,c);
            System.out.println(waitingListIndex.getIndexNumber()+" "+waitingListIndex.getCourseCode()+" "+courseReg.getAcademicUnits()+" Waiting List");
        }
    }

    public void checkVacanciesAvailable(Student student, int indexId, String courseId)
    {

        Course c= new Course(courseId);
        Course  courseToCheck=(Course)systemMgr.findObject(courses,c);
        if(courseToCheck == null)
        {
            System.out.println("Course code does not exist");
            return;
        }
        Index i= new Index(indexId);
        Index  indexToCheck=(Index)systemMgr.findObject(courseToCheck.getIndexNumber(),i);
        if(indexToCheck == null)
        {
            System.out.println("Index id does not exist");
            return;
        }
        System.out.println("Index "+indexToCheck.getIndexNumber()+" has "+indexToCheck.getVacancy()+" vacancies");
    }

    public void changeIndex(Student student, int currentIndex, int newIndex, String courseId)
    {
        //for changing index of registered course
        Course c= new Course(courseId);
        Course  courseToCheck=(Course)systemMgr.findObject(courses,c);
        if(courseToCheck == null)
        {
            System.out.println("Course code does not exist");
            return;
        }

        Index i1= new Index(currentIndex);
        origIndex=(Index)systemMgr.findObject(courseToCheck.getIndexNumberList(),i1);  // finding if currentIndex exists
        if(origIndex == null)
        {
            System.out.println("Current index does not exist");
            return;
        }

        Index i2= new Index(newIndex);
        changeToIndex=(Index)systemMgr.findObject(courseToCheck.getIndexNumberList(),i2);  // finding new index object
        if(changeToIndex == null)
        {
            System.out.println("New index does not exist");
            return;
        }

        for(Index index:student.getIndexRegistered())
        {
            if(index.getIndexNumber()==currentIndex && index.getCourseCode()==courseId) // to check if student is registered to the original index
            {

                 if(!changeToIndex.isFilled())
                 {
                     // need to skip iteration of checking with currentIndex
                     if(timeTableMgr.checkClash(student.getIndexonWaitList(),indexToChange,origIndex) || timeTableMgr.checkClash(student.getIndexRegistered(),indexToChange,origIndex))
                     {
                         System.out.println("New index clashes with waiting index or registered indices of student");
                         return;
                     }
                     else
                     {
                         student.removeIndex(index);
                         student.addIndex(changeToIndex);
                         fileMgr.saveObjects(students);
                         fileMgr.saveObjects(courses);

                         //notification
                         return;
                     }
                 }
                 else
                 {
                     System.out.println("No vacancies in new index");
                 }
                return;
            }
        }



        System.out.println("Not registered for current index");
    }


    public void swapIndex(Student student1,String networkName, string password, int index1, int index2, String courseId)
    {
        Student s= new Student(networkName);
        Student student2=(Student)systemMgr.findObject(students,s);
        if(student2== null)
        {
            System.out.println("Invalid username");
            return;
        }

        // code to check if student's password and entered password match

        LoginMgr loginMgr=new LoginMgr();
        if(!loginCheck(networkName,password))
        {
            System.out.println("Username and password of second student does not match");
            return;
        }

        Index i1= new Index(index1);
        Index firstIndex= (Index)systemMgr.findObject(student1.getIndexRegistered(),i1);
        if(firstIndex == null)
        {
            System.out.println("Student "+student1.getNetworkName()+" is not registered for index "+ index1);
            return;
        }

        Index i2= new Index(index1);
        Index secondIndex=(Index)systemMgr.findObject(student2.getIndexRegistered(),i2);
        if(secondIndex == null)
        {
            System.out.println("Student "+student2.getNetworkName()+" is not registered for index "+ index2);
            return;
        }

        // need to skip clash checking with currently registered index
        if(!timeTableMgr.checkClash(student1.getRegisteredIndex(), index2,index1) && !timeTableMgr.checkClash(student1.getIndexOnWaitList(), index2,index1) && !timeTableMgr.checkClash(student2.getRegisteredIndex(), index1,index2) && !timeTableMgr.checkClash(student2.getIndexOnWaitList(), index1,index2))
        {
            student1.removeIndex(firstIndex);
            student2.removeIndex(secondIndex);
            student1.addIndexRegistered(secondIndex);
            student2.addIndexRegistered(firstIndex);
            fileMgr.saveObjects(students);
            fileMgr.saveObjects(courses);
        }

    }

    public void emailAdmin( Student student)
    {
        for (int i=0;i<admins.size();i++)
        {
            System.out.println(i+". "+admins.get(i+1));
        }

        System.out.println("Please enter the number corresponding to the admin you would like to send email to");
        Scanner sc= new Scanner(System.in);
        int choice= sc.nextInt();

        while( choice<1 || choice>admins.size) {
            System.out.println("Invalid input.Please enter the number corresponding to the admin you would like to send email to");
            choice= sc.nextInt();
        }

        System.out.println("Please enter the new AU limit you wish to apply for");
        int newLimit= sc.nextInt();
        while( newLimit< student.getMaxAU()) {
            System.out.println("Invalid input.Please enter valid limit");
            newLimit= sc.nextInt();
        }


        // code for notification- emailMgr.sendEmail(student.getEmail(),choice,"Request to increase AUs"," I would like to increase my AU limit to "+newLimit);

    }



}