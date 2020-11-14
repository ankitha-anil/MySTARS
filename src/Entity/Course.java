package Entity;

import java.io.Serializable;
import java.util.ArrayList;

public class Course implements Serializable {
    private String courseCode;
    private int academicUnits;
    private String schoolName;
    private ArrayList<Object> indexNumberList;

    public ArrayList<Object> getIndexNumberList() {
        return indexNumberList;
    }

    public void setIndexNumberList(ArrayList<Object> indexNumberList) {
        this.indexNumberList = indexNumberList;
    }

    public Course(String courseCode, int academicUnits, String schoolName) {
        this.courseCode = courseCode;
        this.academicUnits = academicUnits;
        this.schoolName = schoolName;

        this.indexNumberList = new ArrayList<>();
    }

    public Course(String courseCode) {
        this.courseCode = courseCode;
    }

    public int getAcademicUnits() {
        return academicUnits;
    }

    public void setAcademicUnits(int academicUnits) {
        this.academicUnits = academicUnits;
    }

    public String getSchoolName() {
        return schoolName;
    }

    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName;
    }

    public String getCourseCode() {
        return courseCode;
    }

    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode;
    }

    public void addIndexNumber(int indexNumber, int vacancy) {
        Index index = new Index(indexNumber, vacancy, this.academicUnits, this.courseCode);
        this.indexNumberList.add(index);
    }

    public boolean equals(Object object) {
        return (courseCode.equals(((Course) object).getCourseCode()));
    }


}
