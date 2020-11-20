package entity;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

public class Student extends User implements Serializable {


    /*

    Someone add javadoc for Student class... Please complete the missing descriptions

     */

    // Registration Period
    /***
     *
     */
    private static LocalTime startTime = LocalTime.of(0, 0);
    private static LocalTime endTime = LocalTime.of(23, 59);
    private static LocalDate startDate = LocalDate.of(2020, 1, 1);
    private static LocalDate endDate = LocalDate.of(2020, 12, 31);

    private String matriculationNumber;
    private String school;
    private int maxAU = 21;
    private int studyYear;
    private ArrayList<Index> indexRegistered;
    private ArrayList<Index> indexOnWaitList;
    private int registeredAU = 0;

    /**
     *
     * @param name
     * @param networkName
     * @param matriculationNumber
     * @param emailID
     * @param gender
     * @param nationality
     * @param school
     * @param studyYear
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
     *
     * @param networkName
     */
    public Student(String networkName) {
        super(networkName);
    }

    // Dummy Constructor

    /**
     *
     * @param networkName
     * @param matriculationNumber
     */
    public Student(String networkName, String matriculationNumber) {
        super(networkName);
        this.matriculationNumber = matriculationNumber;
    }

    /**
     *
     * @return
     */

    public String getMatriculationNumber() {
        return matriculationNumber;
    }

    /**
     *
     * @param matriculationNumber
     */

    public void setMatriculationNumber(String matriculationNumber) {
        this.matriculationNumber = matriculationNumber;
    }

    /**
     *
     * @return
     */
    public int getRegisteredAU() {
        return registeredAU;
    }

    /**
     *
     * @param registeredAU
     */
    public void setRegisteredAU(int registeredAU) {
        this.registeredAU = registeredAU;
    }

    /**
     *
     * @return
     */
    public String getSchool() {
        return school;
    }

    /**
     *
     * @param school
     */
    public void setSchool(String school) {
        this.school = school;
    }

    /**
     *
     * @return
     */
    public int getMaxAU() {
        return maxAU;
    }

    /**
     *
     * @param maxAU
     */
    public void setMaxAU(int maxAU) {
        this.maxAU = maxAU;
    }

    /**
     *
     * @return
     */
    public int getStudyYear() {
        return studyYear;
    }

    /**
     *
     * @param studyYear
     */
    public void setStudyYear(int studyYear) {
        this.studyYear = studyYear;
    }

    /**
     *
     * @return
     */
    public ArrayList<Index> getIndexRegistered() {
        return indexRegistered;
    }

    /**
     *
     * @return
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
     *
     * @param indexRegistered
     */
    public void setIndexRegistered(ArrayList<Index> indexRegistered) {
        this.indexRegistered = indexRegistered;
    }

    /**
     *
     * @return
     */
    public ArrayList<Index> getIndexOnWaitList() {
        return indexOnWaitList;
    }

    /**
     *
     * @return
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
     *
     * @param indexOnWaitList
     */
    public void setIndexOnWaitList(ArrayList<Index> indexOnWaitList) {
        this.indexOnWaitList = indexOnWaitList;
    }

    /**
     *
     * @param index
     */
    public void addIndexRegistered(Index index) {
        System.out.println("Registering " + networkName + " to  " + index.getIndexNumber());
        this.indexRegistered.add(index);
        this.updateRegisteredAU(index.getAcademicUnits());
        index.addStudent(this);
    }

    /**
     *
     * @param index
     */
    public void removeIndex(Index index) {
        boolean success = this.indexRegistered.remove(index);
        if (success) {
            System.out.println("De-registering " + this.networkName + " from" + index.getIndexNumber());
            this.updateRegisteredAU(-1 * index.getAcademicUnits());
            index.removeStudent(this);
        }
    }

    /**
     *
     * @param index
     */
    public void addIndexOnWaitList(Index index) {
        this.indexOnWaitList.add(index);
    }

    /**
     *
     * @param index
     */
    public void removeIndexFromWaitList(Index index) {
        this.indexOnWaitList.remove(index);
    }

    /**
     *
     * @param academicUnits
     */
    public void updateRegisteredAU(int academicUnits) {
        this.registeredAU += academicUnits;
    }

    /**
     *
     * @param object
     * @return
     */
    public boolean equals(Object object) {
        return (networkName.equals(((Student) object).getNetworkName()));
    }

    /**
     *
     */
    public void print() {
        System.out.println(getName() + ", " + getNetworkName() + ", Sex: " + getGender() + ", Nationality: " + getNationality());
    }

    /**
     *
     * @param regStartTime
     * @param regEndTime
     * @param regStartDate
     * @param regEndDate
     */
    public static void setAccessPeriod(LocalTime regStartTime, LocalTime regEndTime, LocalDate regStartDate, LocalDate regEndDate) {
        startTime = regStartTime;
        endTime = regEndTime;
        startDate = regStartDate;
        endDate = regEndDate;
    }

    /**
     *
     * @return
     */
    public static LocalTime getStartTime() {
        return startTime;
    }

    /**
     *
     * @return
     */
    public static LocalTime getEndTime() {
        return endTime;
    }

    /**
     *
     * @return
     */
    public static LocalDate getStartDate() {
        return startDate;
    }

    /**
     *
     * @return
     */
    public static LocalDate getEndDate() {
        return endDate;
    }
}
