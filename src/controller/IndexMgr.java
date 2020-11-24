package controller;

import entity.Course;
import entity.Index;
import entity.Student;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Scanner;

import static boundary.MyStarsInterface.*;

/**
 * Class that manages the list of indices for a particular course
 *
 * @author Anon
 */
public class IndexMgr extends ObjectEntityController {

    /**
     * list of indices of a particular course
     */
    private ArrayList<Object> indexes;

    /**
     * constructor of IndexMgr
     *
     * @param parentCourse course object whose indices have to be stored in the arraylist
     */
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

    /**
     * constructor of IndexMgr
     */
    public IndexMgr() {
        super();
        indexes = new ArrayList<>();
        System.out.println(RED + "Indexes must be present inside a course" + RESET);
        System.out.println();
    }

    /**
     * Checks if an index with a particular index number exists in the list of indices
     *
     * @param indexNumber index number that needs to be verified
     * @return boolean value to indicate if the required index exists or not
     */
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


    /**
     * Adds an index to the index list of a course
     * Checks if a non-negative integer has been entered as the index number
     * Also checks if the entered index number does not already exist
     *
     * @param indexNumber   index number of the index to be added
     * @param vacancy       number of vacancies of the index to be added
     * @param courseCode    course code of the course to which new index needs to be added
     * @param academicUnits academic units of the course to which the index belongs to
     */
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


    /**
     * Adds a lesson to a given index
     * The type of class, weeks and timings of each lesson are entered by the user
     * If no lessons are added for the index, the index is removed
     *
     * @param index object of type index to which new lesson needs to be added
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
                int day = 1;
                try {
                    day = sc.nextInt();
                    if (day < 1 || day > 7) {
                        System.out.println(RED + "Day can only take values from 1 to 7" + RESET);
                        continue;
                    }
                } catch (NumberFormatException e) {
                    System.out.println(RED + "Day can only take values from 1 to 7" + RESET);
                }
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
                    endTime = LocalTime.parse(dateTimeLine, dateTimeFormatter);
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

    /**
     * Prints the number of vacancies left for a given index
     * Checks if the index exists before printing
     *
     * @param indexNumber index number of the required index
     */
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
                System.out.println("+----------------------------------------+");
                System.out.println("|    Index number    |       Vacancy     |");
                System.out.println("+----------------------------------------+");
                System.out.printf("| %10d %9c %8d/%d %7c \n", indexNum, '|', ((Index) existingIndex).getVacancy(), (((Index) existingIndex).getStudentsRegistered().size() + ((Index) existingIndex).getVacancy()), '|');
                System.out.println("+----------------------------------------+");

            } else {
                System.out.println(RED + "System Error" + RESET);
            }
        } else {
            System.out.println(RED + "Index doesn't exists in the database" + RESET);
        }
        System.out.println();
    }

    /**
     * Prints the list of registered students for a given index
     * Checks if the index exists before printing
     *
     * @param indexNumber index number of the required index
     */
    public void printStudentListByIndex(String indexNumber) {
        Object existingIndex = getObjectFromList(indexNumber);
        int count = 0;
        if (existingIndex != null) {
            if (existingIndex instanceof Index) {
                for (Student student : ((Index) existingIndex).getStudentsRegistered()
                ) {
                    student.print();
                    count += 1;
                }
            } else {
                System.out.println(RED + "System Error" + RESET);
            }
        } else {
            System.out.println(RED + "Index doesn't exists in the database" + RESET);
        }
        if (count == 0) {
            System.out.println(RED + "No students have registered for this index" + RESET);
        }
    }

    /**
     * Prints the details for each index in the list of indices
     */
    public void printObjects() {
        for (Object index : indexes
        ) {
            if (index instanceof Index)
                ((Index) index).print();
            else System.out.println(RED + "Unexpected object type" + RESET);
        }
    }

    /**
     * Gets an index of a particular index number from the list of indices
     *
     * @param indexNumber index number of the required index
     * @return required index object
     */
    public Object getObjectFromList(String indexNumber) {
        int indexNum = getIntegerValueOfIndex(indexNumber);
        if (indexNum < 0) {
            return null;
        }
        Object index = new Index(indexNum);
        Object existingIndex = systemMgr.findObject(indexes, index);
        return existingIndex;
    }

    /**
     * Extracts the index number as an integer from the corresponding string entered by the user
     *
     * @param indexNumber index number which is of type string
     * @return index number which is of type int
     */
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
