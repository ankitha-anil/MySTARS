package Controller;

import Entity.Course;
import Entity.Index;
import Entity.Student;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Scanner;

public class StudentController {
    private final String studentFile = "student.dat"; // need to save files here or while logging out??
    private final String courseFile = "course.dat";
    private ArrayList<Object> courses;
    private ArrayList<Object> students;
    private FileMgr fileMgr;
    private SystemMgr systemMgr;
    private TimeTableMgr timeTableMgr;

    public StudentController() {
        fileMgr = new FileMgr();
        systemMgr = new SystemMgr();
        students = fileMgr.loadObjects(studentFile); // not stored in filemgr?
        courses = fileMgr.loadObjects(courseFile);
    }

    public addCourse(Student student, int indexId,Index i)
    {

        // need function to find index using string indexId. That's what the user will enter
        //need to change calling functions

        // doing this validation here or in user interface?? need to change function paramter to index instead of string??
        Course courseToAdd=systemMgr.findObject(courses,indexId);
        Index  indexToAdd=systemMgr.findObject(courses,indexId);
        if(!courseToAdd)
        {
            System.out.println("Course code does not exist.Please enter the correct one");
            return;
        }

        if(!indexToAdd)
        {
            System.out.println("Index id does not exist. Please enter the correct one");
            return;
        }


        if(indexToAdd.isFilled()) // no vacancies, need to add to waiting list if possible
        {
            System.out.println("No vacancies for this course");
            //need to make timetable manager function public
            if(timeTableMgr.checkExistingCourse(student.getIndexonWaitList(),indexToAdd)) //check if already on waiting list for course
            {
                System.out.println("Already on waitlist for this course");
            }
            else
            {
                if(timeTableMgr.checkClash(student.getIndexonWaitList(),indexToAdd) || timeTableMgr.checkClash(student.getIndexRegistered(),indexToAdd) ) //check if there is clash with registered indices or waiting list
                {
                    System.out.println("Clash with registered indices or indices on waitlist");

                }
                else
                { // if no clash , update 2 way assoication of student and index wrt waiting list
                    indexToAdd.addToWaitingList(student);
                    //notification for waitlist??
                }
            }
        }

        else{
            if(timeTableMgr.checkExistingCourse(student.getIndexRegistered(),indexToAdd)) //check if already registered for course
            {
                System.out.println("Already on waitlist for this course");
            }
            else
            {
                if(timeTableMgr.checkClash(student.getIndexonWaitList(),indexToAdd) || timeTableMgr.checkClash(student.getIndexRegistered(),indexToAdd) ) //check if there is clash with registered indices or waiting list
                {
                    System.out.println("Clash with registered indices or indices on waitlist");

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
    }

    public dropCourse(Student student, int indexToDrop)
    {
        for(Index index:student.getIndexRegistered())
        {
            if(index.getIndexNumber()==indexToDrop)
            {
                student.removeIndex(index);// need to add code to removeIndex to reduce AUs
                System.out.println("Index"+index.getIndexNumber()+ "removed from waitlist");
                //notification

                //give vacancy to first student in waitlist
                Student nowRegistered=index.getWaitingList().poll();
                if(nowRegistered)
                {
                    nowRegistered.addIndexRegistered(index);
                    //notification to newly regsitered student
                }

                return;
            }
        }

        for(Index index:student.getIndexonWaitList())
        {
            if(index.getIndexNumber()==indexToDrop)
            {
                student.removeIndexFromWaitList(index);
                System.out.println("Index"+index.getIndexNumber()+ "removed from waiting list");
                //notification
                return;
            }
        }
        System.out.println("Index not found in waiting list and registered index list");
    }

    public printCourses(Student student)
    {
        for(Index registeredIndex:student.getIndexRegistered())
        {
            //need function like this
            Course courseReg=systemMgr.findObject(courses,registeredIndex.getCourseCode());
            System.out.println(registeredIndex.getIndexNumber()+" "+registeredIndex.getCourseCode()+" "+courseReg.getAcademicUnits()+" Registered");
        }

        for(Index waitingListIndex:student.getIndexonWaitingList())
        {
            //need function like this
            Course courseReg=systemMgr.findObject(courses,waitingListIndex.getCourseCode());
            System.out.println(waitingListIndex.getIndexNumber()+" "+waitingListIndex.getCourseCode()+" "+courseReg.getAcademicUnits()+" Waiting List");
        }
    }

    public checkVacanciesAvailable(Student student, int indexId)
    {
        //need this type of function
        Index  indexToCheck=systemMgr.findObject(courses,indexId);
        System.out.println("Index "+indexToCheck.getIndexNumber()+" has "+indexToCheck.getVacancy()+" vacancies");
    }

    public changeIndex(Student student, int currentIndex, int newIndex)
    {
        //for changing index of registered course
        for(Index index:student.getIndexRegistered())
        {
            if(index.getIndexNumber()==currentIndex) // finding current index object
            {
                //need this type of function
                 changeToIndex=systemMgr.findObject(courses,indexId);  // finding new index object
                 if(!changeToIndex)
                 {
                     System.out.println("New index does not exist"); //doing validation here??
                 }

                 if(!changeToIndex.isFilled())
                 {
                     // need to skip iteration of checking with currentIndex
                     if(timeTableMgr.checkClash(student.getIndexonWaitList(),indexToChange) || timeTableMgr.checkClash(student.getIndexRegistered(),indexToChange))
                     {
                         System.out.println("New index clashes with waiting index or registered indices of student");
                         return;
                     }
                     else
                     {
                         student.removeIndex(index);
                         student.addIndex(changeToIndex);
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

        //for changing index of waiting list course also?


        System.out.println("Not registered for current index");
    }


    public swapIndex(Student student1,String username, string password, int index1, int index2)
    {
        //need a function like this
        Student student2=systemMgr.findObject(students,username);
        if(!student2)
        {
            System.out.println("Invalid username");
            return;
        }

        // add code to check if student's password and entered password match

        Index firstIndex=systemMgr.findObject(student1.getIndexRegistered(),index1);
        if(!firstIndex)
        {
            System.out.println("Student "+student1.getUsername()+" is not registered for index "+ index1);
            return;
        }

        Index secondIndex=systemMgr.findObject(student2.getIndexRegistered(),index2);
        if(!secondIndex)
        {
            System.out.println("Student "+student2.getUsername()+" is not registered for index "+ index2);
            return;
        }

        // need to skip clash checking with currently registered index
        if(!timeTableMgr.checkClash(student1.getRegisteredIndex(), index2) && !timeTableMgr.checkClash(student1.getIndexOnWaitList(), index2) && !timeTableMgr.checkClash(student2.getRegisteredIndex(), index1) && !timeTableMgr.checkClash(student2.getIndexOnWaitList(), index1))
        {
            student1.removeIndex(firstIndex);
            student2.removeIndex(secondIndex);
            student1.addIndexRegistered(secondIndex);
            student2.addIndexRegistered(firstIndex);
        }

    }





}