package entity;

import java.io.Serializable;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

import static boundary.MyStarsInterface.*;


/**
 * Entity Class Index that stores details of an index number
 *
 * @author Anon
 */
public class Index implements Serializable {

    /**
     * Index number of the index
     * Vacancy of the index
     * Academics Units of the index
     * Course Code which the index belongs to
     * List of Lessons in the Index
     * Waiting List of the index
     * List of students registered for the index
     */
    private int indexNumber;
    private int vacancy;
    private int academicUnits;
    private String courseCode;
    private ArrayList<Lesson> lessons;
    private Queue<Student> waitingList;
    private ArrayList<Student> studentsRegistered;


    /**
     * Constructor of Index
     *
     * @param indexNumber   Index number of the index
     * @param vacancy       Vacancy of the index
     * @param academicUnits Academic Units of the index
     * @param courseCode    Course Code of the index
     */
    public Index(int indexNumber, int vacancy, int academicUnits, String courseCode) {
        this.indexNumber = indexNumber;
        this.vacancy = vacancy;
        this.academicUnits = academicUnits;
        this.courseCode = courseCode;
        lessons = new ArrayList<>();
        waitingList = new LinkedList<>();
        studentsRegistered = new ArrayList<>();
    }

    /**
     * Dummy Constructor of the index that initialises the unique attribute
     *
     * @param indexNumber index number of the index
     */
    public Index(int indexNumber) {
        this.indexNumber = indexNumber;
    }

    /**
     * Accessor of academicUnits
     *
     * @return academic units of the course
     */

    public int getAcademicUnits() {
        return academicUnits;
    }

    /**
     * Mutator of academicUnits
     *
     * @param academicUnits academic units to be set
     */

    public void setAcademicUnits(int academicUnits) {
        this.academicUnits = academicUnits;
    }

    /**
     * Mutator of courseCode
     *
     * @param courseCode course code to be set
     */

    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode;
    }

    /**
     * Accessor of waitingList
     *
     * @return returns waiting list of the index
     */

    public Queue<Student> getWaitingList() {
        return waitingList;
    }

    /**
     * Mutator of waitingList
     *
     * @param waitingList waiting list to be set
     */

    public void setWaitingList(Queue<Student> waitingList) {
        this.waitingList = waitingList;
    }

    /**
     * Accessor of studentsRegistered
     *
     * @return returns the list of students
     */

    public ArrayList<Student> getStudentsRegistered() {
        return studentsRegistered;
    }

    /**
     * Mutators of studentsRegistered
     *
     * @param studentsRegistered list of registered students to be set
     */
    public void setStudentsRegistered(ArrayList<Student> studentsRegistered) {
        this.studentsRegistered = studentsRegistered;
    }

    /**
     * Accessor of vacancy
     *
     * @return returns the vacancy of the index
     */

    public int getVacancy() {
        return vacancy;
    }

    /**
     * Mutator of vacancy
     *
     * @param vacancy vacancy of the index to be set
     */

    public void setVacancy(int vacancy) {
        this.vacancy = vacancy - this.getStudentsRegistered().size();
    }

    /**
     * Accessor of indexNumber
     *
     * @return index number of the Index
     */

    public int getIndexNumber() {
        return indexNumber;
    }

    /**
     * Mutator of indexNumber
     *
     * @param indexNumber index number to be set
     */

    public void setIndexNumber(int indexNumber) {
        this.indexNumber = indexNumber;
    }

    /**
     * Accessor of courseCode
     *
     * @return course code of the index number
     */

    public String getCourseCode() {
        return courseCode;
    }

    /**
     * Accessor of lessons
     *
     * @return list of lessons
     */

    public ArrayList<Lesson> getLessons() {
        return lessons;
    }

    /**
     * Mutator of lessons
     *
     * @param lessons list of lessons to be set
     */

    public void setLessons(ArrayList<Lesson> lessons) {
        this.lessons = lessons;
    }

    /**
     * Adds a lecture or tutorial to the lessons list
     *
     * @param day        starting day of the lesson (1-7 for Monday-Sunday)
     * @param venue      venue of the lesson
     * @param lessonType type of the lesson
     * @param startTime  starting time of the lesson
     * @param endTime    ending time of the lesson
     */

    public void addLesson(int day, String venue, String lessonType, LocalTime startTime, LocalTime endTime) {
        if (startTime.compareTo(endTime) >= 0) {
            System.out.println(RED + "Start time must be earlier than end time" + RESET);
            System.out.println(RED + "System will not add this lesson" + RESET);
            return;
        }
        // Check for clash with existing sessions
        Lesson session = new Lesson(day, venue, lessonType, startTime, endTime);
        for (Lesson lesson : lessons
        ) {
            if (session.checkCLash(lesson)) {
                System.out.println(RED + "Lesson clashes with an existing leeson in this index number" + RESET);
                System.out.println(RED + "System will not add this lesson" + RESET);
                return;
            }
        }
        if (checkLessonValidity(session.getLessonType()))
            this.lessons.add(session);
        else {
            System.out.println(RED + "Illegal combination of classes" + RESET);
            System.out.println(RED + "Can only have (Lecture) or (Lecture, Tutorial) or (Lecture, Tutorial, Lab)" + RESET);
            System.out.println();
        }
    }

    /**
     * Adds a lab to the lesson list
     *
     * @param day        starting day of the lesson (1-7 for Monday-Sunday)
     * @param venue      venue of the lesson
     * @param lessonType type of the lesson
     * @param startTime  starting time of the lesson
     * @param endTime    ending time of the lesson
     * @param labWeek    lab week
     */

    public void addLesson(int day, String venue, String lessonType, LocalTime startTime,
                          LocalTime endTime, String labWeek) {
        if (startTime.isAfter(endTime)) {
            System.out.println(RED + "Start time must be earlier than end time" + RESET);
            System.out.println(RED + "System will not add this lesson" + RESET);
            return;
        }
        // Check for clash with existing sessions
        Lesson session = new Lesson(day, venue, lessonType, labWeek, startTime, endTime);
        for (Lesson lesson : lessons
        ) {
            if (session.checkCLash(lesson)) {
                System.out.println(RED + "Lesson clashes with an existing lesson in this index number" + RESET);
                System.out.println(RED + "System will not add this lesson" + RESET);
                return;
            }
        }
        if (checkLessonValidity(session.getLessonType()))
            this.lessons.add(session);
    }


    /**
     * Checks the validity of the lesson
     *
     * @param lessonType type of the lesson
     * @return return a boolean that determines the validity of the lesson
     */
    private boolean checkLessonValidity(String lessonType) {
        if (lessonType.equals("lecture"))
            return true;
        boolean isTutorial = false;
        boolean isLab = false;
        if (lessonType.equals("tutorial"))
            isTutorial = true;
        else if (lessonType.equals("lab"))
            isLab = true;
        boolean hasLecture = false;
        boolean hasTutorial = false;
        for (Lesson s : lessons) {
            String availableLesson = s.getLessonType();
            if (availableLesson.equals("lecture"))
                hasLecture = true;
            else if (availableLesson.equals("tutorial"))
                hasTutorial = true;
        }
        return (hasLecture && isTutorial) || (hasLecture && hasTutorial && isLab);
    }


    /**
     * Adds a student to the waiting list
     *
     * @param student student object to add to the waiting list
     */
    public void addToWaitingList(Student student) {
        waitingList.add(student);
        student.addIndexOnWaitList(this);
    }

    /**
     * Removes a student from the waiting list
     *
     * @param student student object to be removed from the waiting list
     */

    public void removeFromWaitingList(Student student) {
        waitingList.remove(student);
        student.removeIndexFromWaitList(this);
    }

    /**
     * Adds student to the registered list
     *
     * @param student student object to add into registered list
     */

    public void addStudent(Student student) {
        this.studentsRegistered.add(student);
        this.vacancy -= 1;
    }

    /**
     * Removes a student from the registered list
     *
     * @param student
     */

    public void removeStudent(Student student) {
        this.studentsRegistered.remove(student);
        this.vacancy += 1;
    }

    /**
     * Check if the vacancy is 0
     *
     * @return returns a boolean value that determines if the index is filled
     */


    public boolean isFilled() {
        return this.vacancy == 0;
    }

    /**
     * Prints the details of the index number
     */
    public void print() {
        System.out.printf("| %10s %12c %9s %7c %13s %10c %14s %14c\n",this.getIndexNumber(),'|', this.getVacancy(),'|', studentsRegistered.size(),'|', waitingList.size(),'|');
        System.out.println("+------------------------------------------------------------------------------------------------+");


        for (Lesson lesson : lessons) {
            lesson.print();
        }
    }

    /**
     * Checks if an object has the same index number
     *
     * @param object Object to compare with
     * @return boolean value that determines if the object has the same index number
     */
    public boolean equals(Object object) {
        return (indexNumber == ((Index) object).getIndexNumber());
    }

}
