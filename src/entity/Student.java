package entity;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

/**
 * @author Anon
 */
public class Student extends User implements Serializable {
    public static final String RESET = "\u001B[0m";
    public static final String RED = "\033[1;31m";
    public static final String YELLOW = "\033[1;33m"; // YELLOW

    /**
     * Start Time for student access into the system
     * End Time for student access into the system
     * Starting Day for student access into the system
     * Ending Day for student access into the system
     */
    private static LocalTime startTime = LocalTime.of(0, 0);
    private static LocalTime endTime = LocalTime.of(23, 59);
    private static LocalDate startDate = LocalDate.of(2020, 1, 1);
    private static LocalDate endDate = LocalDate.of(2020, 12, 31);

    /**
     * Matriculation Number of the student
     * School of the student
     * Maximum allowable academic units for the student
     * Year of Study of the student
     * Indexes that the student has registered for
     * Indexes that the student is on the waiting list for
     * Number of AUs that the student has registered for
     */
    private String matriculationNumber;
    private String school;
    private int maxAU = 21;
    private int studyYear;
    private ArrayList<Index> indexRegistered;
    private ArrayList<Index> indexOnWaitList;
    private int registeredAU = 0;

    /**
     * Constructor for Student Class
     *
     * @param name                name of the student
     * @param networkName         network name of the student
     * @param matriculationNumber matriculation number of the student
     * @param emailID             email id of the student
     * @param gender              gender of the student
     * @param nationality         nationality of the student
     * @param school              school of the student
     * @param studyYear           year of study of the student
     */
    public Student(String name, String networkName, String matriculationNumber, String emailID, String gender, String nationality,
                   String school, int studyYear) {
        super(name, networkName, gender, nationality, emailID);
        this.school = school;
        this.studyYear = studyYear;
        this.indexRegistered = new ArrayList<>();
        this.indexOnWaitList = new ArrayList<>();
        this.matriculationNumber = matriculationNumber;
    }

    /**
     * Dummy Constructor that initialises the unique attribute of the student class
     * Used to check if two students have the same network name
     *
     * @param networkName
     */
    public Student(String networkName) {
        super(networkName);
    }

    /**
     * Dummy Constructor that initialises the networkName and matriculationNumber of the student
     *
     * @param networkName
     * @param matriculationNumber
     */
    public Student(String networkName, String matriculationNumber) {
        super(networkName);
        this.matriculationNumber = matriculationNumber;
    }

    /**
     * Accessor for matriculationNumber
     *
     * @return returns the matriculation number of the student
     */

    public String getMatriculationNumber() {
        return matriculationNumber;
    }

    /**
     * Mutator for matriculationNumber
     *
     * @param matriculationNumber matriculation number to be set
     */

    public void setMatriculationNumber(String matriculationNumber) {
        this.matriculationNumber = matriculationNumber;
    }

    /**
     * Accessor for registeredAU
     *
     * @return returns the number of registered AUs
     */
    public int getRegisteredAU() {
        return registeredAU;
    }

    /**
     * Mutator for registeredAU
     *
     * @param registeredAU registeredAU to be set
     */
    public void setRegisteredAU(int registeredAU) {
        this.registeredAU = registeredAU;
    }

    /**
     * Accessor for school
     *
     * @return returns the school of the student
     */
    public String getSchool() {
        return school;
    }

    /**
     * Mutator for school
     *
     * @param school school of the student to be set
     */
    public void setSchool(String school) {
        this.school = school;
    }

    /**
     * Accessor for maxAU
     *
     * @return returns the maximum allowable AUs that the student can register for
     */
    public int getMaxAU() {
        return maxAU;
    }

    /**
     * Mutator for maxAU
     *
     * @param maxAU maximum AUs to be set
     */
    public void setMaxAU(int maxAU) {
        this.maxAU = maxAU;
    }

    /**
     * Accessor for studyYear
     *
     * @return returns the study year of the student
     */
    public int getStudyYear() {
        return studyYear;
    }

    /**
     * Mutator for studyYear
     *
     * @param studyYear study year of the student to be set
     */
    public void setStudyYear(int studyYear) {
        this.studyYear = studyYear;
    }

    /**
     * Accessor for indexRegistered
     *
     * @return returns the list of indexes that the student has been registered to
     */
    public ArrayList<Index> getIndexRegistered() {
        return indexRegistered;
    }

    /**
     * Accessor of indexRegistered as a list of objects
     * A function that returns the list of indexes as objects
     *
     * @return returns the list of indexes that the student has been registered to as objects
     */
    public ArrayList<Object> getIndexRegisteredAsObjects() {
        ArrayList<Object> objects = new ArrayList<>();
        for (Index index : indexRegistered
        ) {
            objects.add(index);
        }
        return objects;
    }

    /**
     * Mutator for indexRegistered
     *
     * @param indexRegistered registered index list to be set
     */
    public void setIndexRegistered(ArrayList<Index> indexRegistered) {
        this.indexRegistered = indexRegistered;
    }

    /**
     * Accessor for indexOnWaitList
     *
     * @return returns the indexes on waiting list
     */
    public ArrayList<Index> getIndexOnWaitList() {
        return indexOnWaitList;
    }

    /**
     * Accessor for indexOnWaitList as a list of objects
     *
     * @return returns the indexes that are on waiting list as a list of objects
     */
    public ArrayList<Object> getIndexOnWaitingListAsObjects() {
        ArrayList<Object> objects = new ArrayList<>();
        for (Index index : indexOnWaitList
        ) {
            objects.add(index);
        }
        return objects;
    }

    /**
     * Mutator for indexOnWaitList
     *
     * @param indexOnWaitList list of indexes on waiting list to be set
     */
    public void setIndexOnWaitList(ArrayList<Index> indexOnWaitList) {
        this.indexOnWaitList = indexOnWaitList;
    }

    /**
     * Function to add an index to the registered list
     *
     * @param index the index to add to the registered list
     */
    public void addIndexRegistered(Index index) {
        System.out.println(YELLOW+"Registering " + networkName + " to  " + index.getIndexNumber()+RESET);
        this.indexRegistered.add(index);
        this.updateRegisteredAU(index.getAcademicUnits());
        index.addStudent(this);
    }

    /**
     * Function to remove an index from the registered list
     *
     * @param index index to be removed from the registered list
     */
    public void removeIndex(Index index) {
        boolean success = this.indexRegistered.remove(index);
        if (success) {
            System.out.println(YELLOW+"De-registering " + this.networkName + " from" + index.getIndexNumber()+RESET);
            this.updateRegisteredAU(-1 * index.getAcademicUnits());
            index.removeStudent(this);
        }
    }

    /**
     * Function to add an index to waiting list
     *
     * @param index index to add in the waiting list
     */
    public void addIndexOnWaitList(Index index) {
        this.indexOnWaitList.add(index);
    }

    /**
     * Function to remove an index from the waiting list
     *
     * @param index index to be removed from the waiting list
     */
    public void removeIndexFromWaitList(Index index) {
        this.indexOnWaitList.remove(index);
    }

    /**
     * Function that updates the student's registered AUs
     * Used when a student adds and drops a course
     *
     * @param academicUnits the change in academic units. Can take positive and negative values depending on whether the student add or drops a course
     */
    public void updateRegisteredAU(int academicUnits) {
        this.registeredAU += academicUnits;
    }

    /**
     * Overridden function to compare if two students have the same networkName
     *
     * @param object the object to compare with
     * @return returns a boolean value that determines whether two students have the same network name
     */
    public boolean equals(Object object) {
        return (networkName.equals(((Student) object).getNetworkName()));
    }

    /**
     * Function to print the student details in a user friendly manner
     */
    public void print() {
        System.out.println("+--------------------------------------------------+");
        System.out.println(" Name: " +getName() );
        System.out.println(" Username :"+getNetworkName());
        System.out.println(" Sex: "+ getGender());
        System.out.println(" Nationality: " + getNationality());
        System.out.println("+--------------------------------------------------+");
    }

    /**
     * Function that sets the access period into the system
     *
     * @param regStartTime starting time for the student's access
     * @param regEndTime   ending time for the student's access
     * @param regStartDate starting day for the student's access
     * @param regEndDate   ending day for the student's access
     */
    public static void setAccessPeriod(LocalTime regStartTime, LocalTime regEndTime, LocalDate regStartDate, LocalDate regEndDate) {
        startTime = regStartTime;
        endTime = regEndTime;
        startDate = regStartDate;
        endDate = regEndDate;
    }

    /**
     * Accessor for startTime
     *
     * @return returns the start time of access
     */
    public static LocalTime getStartTime() {
        return startTime;
    }

    /**
     * Accessor for endTime
     *
     * @return returns the end time of access
     */
    public static LocalTime getEndTime() {
        return endTime;
    }

    /**
     * Accessor for startDate
     *
     * @return returns the starting date of access
     */
    public static LocalDate getStartDate() {
        return startDate;
    }

    /**
     * Accessor for endTime
     *
     * @return returns the ending date of access
     */
    public static LocalDate getEndDate() {
        return endDate;
    }
}
