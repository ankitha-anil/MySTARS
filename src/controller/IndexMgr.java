package controller;

import entity.Course;
import entity.Index;
import entity.Student;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Scanner;

public class IndexMgr extends ObjectEntityController {

    public static final String RESET = "\u001B[0m";
    public static final String RED = "\033[1;31m";

    private ArrayList<Object> indexes;

    public IndexMgr(Course parentCourse) {
        super();
        if (parentCourse == null) {
            System.out.println(RED + "Invalid course" + RESET);
            System.out.println();
            indexes = new ArrayList<>();
        } else {
            indexes = parentCourse.getIndexNumberList();
        }
    }

    // Just in case Constructor
    IndexMgr() {
        super();
        indexes = new ArrayList<>();
        System.out.println(RED + "Indexes must be present inside a course" + RESET);
        System.out.println();
    }

    public boolean checkObjectExists(String indexNumber) {
        int indexNum = getIntegerValueOfIndex(indexNumber);
        if (indexNum < 0) {
            return false;
        }
        Index index = (Index) getObjectFromList(indexNumber);
        if (index != null)
            return true;
        else return false;
    }


    public void addIndex(String indexNumber, int vacancy, String courseCode, int academicUnits) {
        int indexNum = getIntegerValueOfIndex(indexNumber);
        if (indexNum < 0) {
            System.out.println(RED + "Index number is invalid" + RESET);
            System.out.println();
            return;
        }
        if (checkObjectExists(indexNumber)) {
            System.out.println(RED + "The index number already exists" + RESET);
        } else {
            Object index = new Index(indexNum, vacancy, academicUnits, courseCode);
            indexes.add(index);
            addLesson((Index) index);
        }
    }


    // Defined in UpdateManager
    /*
    public void updateIndexNumber(String indexNumber, String newIndexNumber) {
        int indexNum = getIntegerValueOfIndex(indexNumber);
        if (indexNum < 0) {
            System.out.println("Old Index number is invalid");
            return;
        }
        int newIndexNum = getIntegerValueOfIndex(newIndexNumber);
        if (newIndexNum < 0) {
            System.out.println("New Index number is invalid");
            return;
        }
        Object existingIndex = getObjectFromList(indexNumber);
        if (existingIndex != null) {
            ((Index) existingIndex).setIndexNumber(newIndexNum);
        } else {
            System.out.println("Index exists in the database");
        }
    }

     */


    // Defined in updateManager
    /*
    public void updateIndexVacancy(String indexNumber, int newVacancy) {
        int indexNum = getIntegerValueOfIndex(indexNumber);
        if (indexNum < 0) {
            System.out.println("Index number is invalid");
            return;
        }
        Object existingIndex = getObjectFromList(indexNumber);
        if (existingIndex != null) {
            if (((Index) existingIndex).getStudentsRegistered().size() < newVacancy) {
                ((Index) existingIndex).setVacancy(newVacancy);
                for (Student student : ((Index) existingIndex).getStudentsRegistered()
                ) {
                    for (Index index : student.getIndexRegistered()
                    ) {
                        if (index.equals(existingIndex) && index.getCourseCode().equals(((Index) existingIndex).getCourseCode()))
                            index = (Index) existingIndex;
                    }
                    for (Index index : student.getIndexOnWaitList()
                    ) {
                        if (index.equals(existingIndex) && index.getCourseCode().equals(((Index) existingIndex).getCourseCode()))
                            index = (Index) existingIndex;
                    }

                }
            } else
                System.out.println("Too many students have registered for this index... cannot decrease the vacancy");
        } else {
            System.out.println("Index doesn't exists in the database");
        }
    }

     */


    public void addLesson(Index index) {
        Scanner sc = new Scanner(System.in);
        int choice;
        while (true) {
            System.out.println("+------------------+");
            System.out.println("|    Add session?  |");
            System.out.println("|------------------|");
            System.out.println("| 1. Yes           |");
            System.out.println("| 2. No            |");
            System.out.println("+------------------+");
            try {
                choice = sc.nextInt();
            } catch (NumberFormatException e) {
                System.out.println(RED + "Invalid choice" + RESET);
                continue;
            }

            if (choice == 2) {
                break;
            } else if (choice < 1 || choice > 2) {
                continue;
            } else {
                String labWeek = null;
                System.out.print("Enter the type of class (Lecture, Tutorial, Lab) : ");
                String type = sc.next();
                type = type.toLowerCase();
                if (!type.equals("lecture") && !type.equals("tutorial") && !type.equals("lab")) {
                    System.out.println(RED + "Invalid class" + RESET);
                    continue;
                }
                if (type.toLowerCase().equals("lab")) {
                    System.out.print("Enter the Lab week ( Even, Odd, Both ) : ");
                    labWeek = sc.next();
                    labWeek = labWeek.toLowerCase();
                    if (!labWeek.equals("both") && !labWeek.equals("even") && !labWeek.equals("odd")) {
                        System.out.println(RED + "Invalid lab week" + RESET);
                        continue;
                    }
                }
                System.out.print("Enter the day (1 to 7) for Monday to Sunday : ");
                int day = sc.nextInt();
                if (choice < 1 || choice > 7) {
                    System.out.println(RED + "Invalid day" + RESET);
                    continue;
                }
                LocalTime starTime = null;
                LocalTime endTime = null;
                try {
                    DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("HH:mm");
                    System.out.print("Enter the starting time in HH:mm  : ");
                    String dateTimeLine = sc.next();
                    starTime = LocalTime.parse(dateTimeLine, dateTimeFormatter);
                    System.out.print("Enter the ending time in HH:mm  : ");
                    dateTimeLine = sc.next();
                    dateTimeFormatter = DateTimeFormatter.ofPattern("HH:mm");
                    endTime = LocalTime.parse(dateTimeLine, dateTimeFormatter);
                    System.out.print("Enter the ending time (Separate the hour and minute by space) : ");
                } catch (DateTimeParseException e) {
                    System.out.print(RED + "Date and time are not in the specified format" + RESET);
                    continue;
                }
                System.out.print("Enter the venue : ");
                String venue = sc.next();

                if (labWeek == null) {
                    index.addLesson(day, venue, type, starTime, endTime);
                } else {
                    index.addLesson(day, venue, type, starTime, endTime, labWeek);
                }
            }
        }
        if (index.getLessons().size() == 0) {
            indexes.remove(index);
            System.out.println(RED + "Index was removed as there were no lessons" + RESET);
            System.out.println();
        }
    }

    public void checkAvailabilityIndex(String indexNumber) {
        int indexNum = getIntegerValueOfIndex(indexNumber);
        if (indexNum < 0) {
            System.out.println(RED + "Index number is invalid" + RESET);
            return;
        }
        Object index = new Index(indexNum);
        Object existingIndex = systemMgr.findObject(indexes, index);
        if (existingIndex != null) {
            if (existingIndex instanceof Index) {
                System.out.println("+--------------------------------------+");
                System.out.println("| Index number: |   " + indexNumber + "              |");
                System.out.println("|--------------------------------------|");
                System.out.println("| Vacancy:      |    " + ((Index) existingIndex).getVacancy() + "/" + (((Index) existingIndex).getStudentsRegistered().size() + ((Index) existingIndex).getVacancy()) + "                |");
                System.out.println("+--------------------------------------+");

            } else {
                System.out.println(RED + "System Error" + RESET);
            }
        } else {
            System.out.println(RED + "Index doesn't exists in the database" + RESET);
        }
        System.out.println();
    }

    public void printStudentListByIndex(String indexNumber) {
        Object existingIndex = getObjectFromList(indexNumber);
        if (existingIndex != null) {
            if (existingIndex instanceof Index) {
                for (Student student : ((Index) existingIndex).getStudentsRegistered()
                ) {
                    student.print();
                }
            } else {
                System.out.println(RED + "System Error" + RESET);
            }
        } else {
            System.out.println(RED + "Index doesn't exists in the database" + RESET);
        }
    }

    public void printObjects() {
        for (Object index : indexes
        ) {
            if (index instanceof Index)
                ((Index) index).print();
            else System.out.println(RED + "Unexpected object type" + RESET);
        }
    }

    public Object getObjectFromList(String indexNumber) {
        int indexNum = getIntegerValueOfIndex(indexNumber);
        if (indexNum < 0) {
            return null;
        }
        Object index = new Index(indexNum);
        Object existingIndex = systemMgr.findObject(indexes, index);
        return existingIndex;
    }

    public int getIntegerValueOfIndex(String indexNumber) {
        try {
            int indexNum = Integer.parseInt(indexNumber);
            if (indexNum < 1) {
                System.out.println(RED + "Index number must be a positive number" + RESET);
                System.out.println();
                return -1;
            }
            return indexNum;
        } catch (NumberFormatException e) {
            System.out.println(RED + "Index number must be an integer " + RESET);
            System.out.println();
            return -1;
        }
    }

}
