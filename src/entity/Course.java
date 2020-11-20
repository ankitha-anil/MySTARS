package entity;

import java.io.Serializable;
import java.util.ArrayList;

public class Course implements Serializable {
    private String courseCode;
    private String courseName;
    private int academicUnits;
    private String schoolName;
    private ArrayList<Object> indexNumberList;

    public Course(String courseCode, String courseName, int academicUnits, String schoolName) {
        this.courseCode = courseCode;
        this.courseName = courseName;
        this.academicUnits = academicUnits;
        this.schoolName = schoolName;
        this.indexNumberList = new ArrayList<>();
    }

    // Dummy course
    public Course(String courseCode) {
        this.courseCode = courseCode;
    }

    public String getCourseCode() {
        return courseCode;
    }

    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode;
        for (Object index :
                indexNumberList) {
            ((Index) index).setCourseCode(courseCode);
        }
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public int getAcademicUnits() {
        return academicUnits;
    }

    public void setAcademicUnits(int academicUnits) {
        this.academicUnits = academicUnits;
        for (Object index :
                indexNumberList) {
            ((Index) index).setAcademicUnits(academicUnits);
        }
    }

    public String getSchoolName() {
        return schoolName;
    }

    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName;
    }


    public void addIndexNumber(int indexNumber, int vacancy) {
        Index index = new Index(indexNumber, vacancy, this.academicUnits, this.courseCode);
        this.indexNumberList.add(index);
    }

    public void removeIndexNumber(Object index) {
        for (Student student : ((Index) index).getStudentsRegistered()
        ) {
            student.removeIndex((Index) index);

        }
        for (Student student : ((Index) index).getWaitingList()
        ) {
            student.removeIndexFromWaitList((Index) index);

        }
        this.indexNumberList.remove(index);
    }

    public ArrayList<Object> getIndexNumberList() {
        return indexNumberList;
    }

    public void setIndexNumberList(ArrayList<Object> indexNumberList) {
        this.indexNumberList = indexNumberList;
    }

    public Index createDummyIndex(int indexNumber) {
        Index index = new Index(indexNumber);
        return index;
    }


    public boolean equals(Object object) {
        return (courseCode.equals(((Course) object).getCourseCode()));
    }

    public void print() {
        System.out.print(courseCode);
        System.out.print("  " + courseName);
        System.out.print("  " + academicUnits + "AU");
        System.out.println("  " + "School: " + schoolName);
    }

}
