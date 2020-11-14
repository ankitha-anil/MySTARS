package Entity;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class Student extends User implements Serializable {

    // Registration Period
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

    public Student(String name, String networkName, String matriculationNumber, String password, String emailID, String gender, String nationality,
                   String school, int studyYear) {
        super(name, networkName, gender, nationality);
        this.school = school;
        this.studyYear = studyYear;
        this.indexRegistered = new ArrayList<>();
        this.indexOnWaitList = new ArrayList<>();
        this.matriculationNumber = matriculationNumber;
    }

    // Dummy Constructor
    public Student(String networkName, String matriculationNumber) {
        super(networkName);
        this.matriculationNumber = matriculationNumber;
    }

    public String getMatriculationNumber() {
        return matriculationNumber;
    }

    public void setMatriculationNumber(String matriculationNumber) {
        this.matriculationNumber = matriculationNumber;
    }

    public int getRegisteredAU() {
        return registeredAU;
    }

    public void setRegisteredAU(int registeredAU) {
        this.registeredAU = registeredAU;
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public int getMaxAU() {
        return maxAU;
    }

    public void setMaxAU(int maxAU) {
        this.maxAU = maxAU;
    }

    public int getStudyYear() {
        return studyYear;
    }

    public void setStudyYear(int studyYear) {
        this.studyYear = studyYear;
    }

    public ArrayList<Index> getIndexRegistered() {
        return indexRegistered;
    }

    public void setIndexRegistered(ArrayList<Index> indexRegistered) {
        this.indexRegistered = indexRegistered;
    }

    public ArrayList<Index> getIndexOnWaitList() {
        return indexOnWaitList;
    }

    public void setIndexOnWaitList(ArrayList<Index> indexOnWaitList) {
        this.indexOnWaitList = indexOnWaitList;
    }

    public void addIndexRegistered(Index index) {
        this.indexRegistered.add(index);
        this.increaseRegisteredAU(index.getAcademicUnits());
        index.addStudent(this);
    }

    public void removeIndex(Index index) {
        this.indexRegistered.remove(index);
        index.removeStudent(this);
    }

    public void addIndexOnWaitList(Index index) {
        this.indexOnWaitList.add(index);
    }

    public void removeIndexFromWaitList(Index index) {
        this.indexOnWaitList.remove(index);
        index.removeFromWaitingList(this);
    }

    public void increaseRegisteredAU(int academicUnits) {
        this.registeredAU += academicUnits;
    }

    public boolean equals(Object object) {
        return (matriculationNumber.equals(((Student) object).getMatriculationNumber()));
    }

    public void print() {
        System.out.println(getName() + ", " + getGender() + ", " + getNationality());
    }

    public static void setAccessPeriod(LocalTime regStartTime, LocalTime regEndTime, LocalDate regStartDate, LocalDate regEndDate) {
        startTime = regStartTime;
        endTime = regEndTime;
        startDate = regStartDate;
        endDate = regEndDate;
    }

    public static LocalTime getStartTime() {
        return startTime;
    }

    public static LocalTime getEndTime() {
        return endTime;
    }

    public static LocalDate getStartDate() {
        return startDate;
    }

    public static LocalDate getEndDate() {
        return endDate;
    }
}
