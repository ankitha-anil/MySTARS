package controller;

import entity.Course;
import entity.Index;
import entity.Student;

import static boundary.MyStarsInterface.*;

/**
 * This class contains methods that update associations and compositions
 * When the Course code or Index number details are updated, the List of indexes present in the student object should also be updated
 *
 * @author Anon
 */
public class UpdateManager {
    /**
     * Student Records Controller. Reference name is the Parent Class
     */
    private ObjectEntityController studentRecordsMgr;
    /**
     * Course Records Controller. Reference name is the Parent Class
     */
    private ObjectEntityController courseMgr;
    /**
     * Index Records Controller. Reference name is the Parent Class
     */
    private ObjectEntityController indexMgr;


    /**
     * Constructor for UpdateManager Class
     */
    public UpdateManager() {
        studentRecordsMgr = new StudentRecordsMgr();
        courseMgr = new CourseMgr();
    }

    /**
     * Method that checks if the student exists in the database and updates the student name. Updates the names of students and reflects the changes in the waiting list and registered list of all indexes
     *
     * @param userName user name of the student to update
     * @param name     new name of the student to be set
     */
    public void updateStudentName(String userName, String name) {
        ((StudentRecordsMgr) studentRecordsMgr).loadStudentObjectList();
        Object existingStudent = studentRecordsMgr.getObjectFromList(userName);
        if (existingStudent != null) {
            ((Student) existingStudent).setName(name);
            for (Index index : ((Student) existingStudent).getIndexRegistered()
            ) {
                Course course = (Course) courseMgr.getObjectFromList(index.getCourseCode());
                indexMgr = new IndexMgr(course);
                Index existingIndex = (Index) indexMgr.getObjectFromList(String.valueOf(index.getIndexNumber()));
                for (Student student : existingIndex.getStudentsRegistered()
                ) {
                    if (student.equals(existingStudent))
                        student.setName(name);
                }
            }

            for (Index index : ((Student) existingStudent).getIndexOnWaitList()
            ) {
                Course course = (Course) courseMgr.getObjectFromList(index.getCourseCode());
                indexMgr = new IndexMgr(course);
                Index existingIndex = (Index) indexMgr.getObjectFromList(String.valueOf(index.getIndexNumber()));
                for (Student student : existingIndex.getWaitingList()
                ) {
                    if (student.equals(existingStudent))
                        student.setName(name);
                }
            }
            System.out.println(GREEN + "Successfully updated details" + RESET);
            ((CourseMgr) courseMgr).saveCourseObjectList();
            ((StudentRecordsMgr) studentRecordsMgr).saveStudentObjectList();
            System.out.println("+------------------------------------------------------------------------------------------+"); //HERE
            ((Student) existingStudent).print();
        } else {
            System.out.println(RED + "Student doesn't exist in the database... Cannot update student details" + RESET);
        }
        System.out.println();
    }

    /**
     * Method that checks if the student exists in the database and updates the nationality of the student. Updates the nationality of student and reflects the changes in the waiting list and registered list of all indexes
     *
     * @param userName    user name of the student to update
     * @param nationality new nationality of the student to be set
     */
    public void updateStudentNationality(String userName, String nationality) {
        ((StudentRecordsMgr) studentRecordsMgr).loadStudentObjectList();
        Object existingStudent = studentRecordsMgr.getObjectFromList(userName);
        if (existingStudent != null) {
            ((Student) existingStudent).setNationality(nationality);
            for (Index index : ((Student) existingStudent).getIndexRegistered()
            ) {
                Course course = (Course) courseMgr.getObjectFromList(index.getCourseCode());
                indexMgr = new IndexMgr(course);
                Index existingIndex = (Index) indexMgr.getObjectFromList(String.valueOf(index.getIndexNumber()));
                for (Student student : existingIndex.getStudentsRegistered()
                ) {
                    if (student.equals(existingStudent))
                        student.setNationality(nationality);
                }
            }

            for (Index index : ((Student) existingStudent).getIndexOnWaitList()
            ) {
                Course course = (Course) courseMgr.getObjectFromList(index.getCourseCode());
                indexMgr = new IndexMgr(course);
                Index existingIndex = (Index) indexMgr.getObjectFromList(String.valueOf(index.getIndexNumber()));
                for (Student student : existingIndex.getWaitingList()
                ) {
                    if (student.equals(existingStudent))
                        student.setNationality(nationality);
                }
            }
            System.out.println(GREEN + "Successfully updated details" + RESET);
            ((CourseMgr) courseMgr).saveCourseObjectList();
            ((StudentRecordsMgr) studentRecordsMgr).saveStudentObjectList();
            ((Student) existingStudent).print();
        } else {
            System.out.println(RED + "Student doesn't exist in the database... Cannot update student details" + RESET);
        }
        System.out.println();
    }

    /**
     * Method that checks if the student exists in the database and updates the maximum allowable aacademic units.
     * Updates the student details in the waiting list and registered list of all indexes
     *
     * @param userName user name of the student to update
     * @param maxAU    maximum allowable academic credits to be set
     */
    public void updateStudentMaxAU(String userName, int maxAU) {
        ((StudentRecordsMgr) studentRecordsMgr).loadStudentObjectList();
        Object existingStudent = studentRecordsMgr.getObjectFromList(userName);
        if (existingStudent != null) {
            ((Student) existingStudent).setMaxAU(maxAU);
            for (Index index : ((Student) existingStudent).getIndexRegistered()
            ) {
                Course course = (Course) courseMgr.getObjectFromList(index.getCourseCode());
                indexMgr = new IndexMgr(course);
                Index existingIndex = (Index) indexMgr.getObjectFromList(String.valueOf(index.getIndexNumber()));
                for (Student student : existingIndex.getStudentsRegistered()
                ) {
                    if (student.equals(existingStudent))
                        student.setMaxAU(maxAU);
                }
            }

            for (Index index : ((Student) existingStudent).getIndexOnWaitList()
            ) {
                Course course = (Course) courseMgr.getObjectFromList(index.getCourseCode());
                indexMgr = new IndexMgr(course);
                Index existingIndex = (Index) indexMgr.getObjectFromList(String.valueOf(index.getIndexNumber()));
                for (Student student : existingIndex.getWaitingList()
                ) {
                    if (student.equals(existingStudent))
                        student.setMaxAU(maxAU);
                }
            }
            System.out.println(GREEN + "Successfully updated details" + RESET);
            ((CourseMgr) courseMgr).saveCourseObjectList();
            ((StudentRecordsMgr) studentRecordsMgr).saveStudentObjectList();
            ((Student) existingStudent).print();
        } else {
            System.out.println(RED + "Student doesn't exist in the database... Cannot update student details" + RESET);
        }
        System.out.println();
    }


    /**
     * Method that checks if a student exists in the database and updates the school of the student
     * Updates the student details in the waiting list and registered list of all indexes
     *
     * @param userName user name of the student to be updated
     * @param school   school of the student to be set
     */
    public void updateStudentSchool(String userName, String school) {
        ((StudentRecordsMgr) studentRecordsMgr).loadStudentObjectList();
        ((CourseMgr) courseMgr).loadCourseObjectList();
        Object existingStudent = studentRecordsMgr.getObjectFromList(userName);
        if (existingStudent != null) {
            ((Student) existingStudent).setSchool(school);
            for (Index index : ((Student) existingStudent).getIndexRegistered()
            ) {
                Course course = (Course) courseMgr.getObjectFromList(index.getCourseCode());
                indexMgr = new IndexMgr(course);
                Index existingIndex = (Index) indexMgr.getObjectFromList(String.valueOf(index.getIndexNumber()));
                for (Student student : existingIndex.getStudentsRegistered()
                ) {
                    if (student.equals(existingStudent))
                        student.setSchool(school);
                }
            }

            for (Index index : ((Student) existingStudent).getIndexOnWaitList()
            ) {
                Course course = (Course) courseMgr.getObjectFromList(index.getCourseCode());
                indexMgr = new IndexMgr(course);
                Index existingIndex = (Index) indexMgr.getObjectFromList(String.valueOf(index.getIndexNumber()));
                for (Student student : existingIndex.getWaitingList()
                ) {
                    if (student.equals(existingStudent))
                        student.setSchool(school);
                }
            }
            System.out.println(GREEN + "Successfully updated details" + RESET);
            ((CourseMgr) courseMgr).saveCourseObjectList();
            ((StudentRecordsMgr) studentRecordsMgr).saveStudentObjectList();
            ((Student) existingStudent).print();
        } else {
            System.out.println(RED + "Student doesn't exist in the database... Cannot update student details" + RESET);
        }
        System.out.println();
    }

    /**
     * Method to check if the course exists in the database and updates the course code of a course object
     * Updates the course code of the index objects which are part of the course and updates the details of indexes in the registered list and waiting list of the student objects
     *
     * @param courseCode
     * @param newCourseCode
     */
    public void updateCourseCode(String courseCode, String newCourseCode) {
        ((CourseMgr) courseMgr).loadCourseObjectList();
        ((StudentRecordsMgr) studentRecordsMgr).loadStudentObjectList();
        Object newExistingCourse = courseMgr.getObjectFromList(newCourseCode);
        if (newExistingCourse != null) {
            System.out.println(RED + "A course with this course code already exists" + RESET);
            return;
        }

        Object existingCourse = courseMgr.getObjectFromList(courseCode);

        if (existingCourse == null) {
            System.out.println(RED + "This course doesn't exist in the database" + RESET);
            return;
        }
        if (existingCourse instanceof Course) {

            for (Object index : ((Course) existingCourse).getIndexNumberList()
            ) {
                for (Student student : ((Index) index).getStudentsRegistered()
                ) {
                    Student existingStudent = (Student) studentRecordsMgr.getObjectFromList(student.getNetworkName());
                    for (Index indexReg : existingStudent.getIndexRegistered()
                    ) {
                        if (indexReg.equals(index))
                            indexReg.setCourseCode(newCourseCode);
                    }

                }
                for (Student student : ((Index) index).getWaitingList()
                ) {
                    Student existingStudent = (Student) studentRecordsMgr.getObjectFromList(student.getNetworkName());
                    for (Index indexWait : existingStudent.getIndexOnWaitList()
                    ) {
                        if (indexWait.equals(index))
                            indexWait.setCourseCode(newCourseCode);
                    }

                }
                ((Index) index).setCourseCode(courseCode);
            }
            ((Course) existingCourse).setCourseCode(newCourseCode);
            System.out.println(GREEN + "Successfully updated course" + RESET);
            ((Course) existingCourse).print();
            ((StudentRecordsMgr) studentRecordsMgr).saveStudentObjectList();
            ((CourseMgr) courseMgr).saveCourseObjectList();
        }

    }

    /**
     * Method that checks if the course exists in the database and if the index is part of this course and updates the index number of the index
     * Updates the index number of the index objects in the waiting list and registered list of the student object
     *
     * @param courseCode     course code to be updated
     * @param indexNumber    index number of the index to be updated
     * @param newIndexNumber new index number of the index to be set
     */

    public void updateIndexNumber(String courseCode, String indexNumber, String newIndexNumber) {
        ((CourseMgr) courseMgr).loadCourseObjectList();
        Course existingCourse = (Course) courseMgr.getObjectFromList(courseCode);
        if (existingCourse == null) {
            System.out.println(RED + "No such course exists" + RESET);
            return;
        }
        indexMgr = new IndexMgr(existingCourse);

        Index indexToUpdate = (Index) indexMgr.getObjectFromList(indexNumber);
        if (indexToUpdate == null) {
            System.out.println(RED + "No such index number exists" + RESET);
            return;
        }

        int newIndexNum = ((IndexMgr) indexMgr).getIntegerValueOfIndex(newIndexNumber);
        if (newIndexNum == -1) {
            return;
        }

        Object newExistingIndex = indexMgr.getObjectFromList(newIndexNumber);
        if (newExistingIndex != null) {
            System.out.println(RED + "An index with this index number already exists" + RESET);
            return;
        }

        for (Student student : indexToUpdate.getStudentsRegistered()
        ) {
            Student existingStudent = (Student) studentRecordsMgr.getObjectFromList(student.getNetworkName());
            for (Index indexReg : existingStudent.getIndexRegistered()
            ) {
                if (indexReg.equals(indexToUpdate))
                    indexReg.setIndexNumber(newIndexNum);
            }

        }
        for (Student student : ((Index) indexToUpdate).getWaitingList()
        ) {
            Student existingStudent = (Student) studentRecordsMgr.getObjectFromList(student.getNetworkName());
            for (Index indexWait : existingStudent.getIndexOnWaitList()
            ) {
                if (indexWait.equals(indexToUpdate))
                    indexWait.setIndexNumber(newIndexNum);
            }

        }
        indexToUpdate.setIndexNumber(newIndexNum);

        ((CourseMgr) courseMgr).saveCourseObjectList();
        ((StudentRecordsMgr) studentRecordsMgr).saveStudentObjectList();
    }


    /**
     * Method that checks if the course exists in the database and if the index is part of this course and updates the vacancy of the index
     * Updates the vacancy of the index objects in the waiting list and registered list of the student object
     * If the vacancy has increased and the waiting list is not empty then the students on waiting list are removed from the wait list and are registered for the course until the vacancy is 0 or the waiting list is empty
     *
     * @param courseCode  course code of the course to update
     * @param indexNumber index number of the index to update
     * @param vacancy     new vacancy of the index number to be set
     */

    public void updateIndexVacancy(String courseCode, String indexNumber, int vacancy) {
        ((CourseMgr) courseMgr).loadCourseObjectList();
        Course existingCourse = (Course) courseMgr.getObjectFromList(courseCode);
        if (existingCourse == null) {
            System.out.println(RED + "No such course exists" + RESET);
            return;
        }
        indexMgr = new IndexMgr(existingCourse);

        Index indexToUpdate = (Index) indexMgr.getObjectFromList(indexNumber);
        if (indexToUpdate == null) {
            System.out.println(RED + "No such index number exists" + RESET);
            return;
        }

        if (vacancy < indexToUpdate.getStudentsRegistered().size()) {
            System.out.println(RED + "Too many students have already registered" + RESET);
            return;
        }

        for (Student student : ((Index) indexToUpdate).getStudentsRegistered()
        ) {
            Student existingStudent = (Student) studentRecordsMgr.getObjectFromList(student.getNetworkName());
            for (Index indexReg : existingStudent.getIndexRegistered()
            ) {
                if (indexReg.equals(indexToUpdate))
                    indexReg.setVacancy(vacancy);
            }

        }
        for (Student student : ((Index) indexToUpdate).getWaitingList()
        ) {
            Student existingStudent = (Student) studentRecordsMgr.getObjectFromList(student.getNetworkName());
            for (Index indexWait : existingStudent.getIndexOnWaitList()
            ) {
                if (indexWait.equals(indexToUpdate))
                    indexWait.setVacancy(vacancy);
            }

        }

        indexToUpdate.setVacancy(vacancy);

        if (((Index) indexToUpdate).getWaitingList().size() > 0) {
            while (((Index) indexToUpdate).getWaitingList().size() > 0 && indexToUpdate.getVacancy() > 0) {
                Student student = indexToUpdate.getWaitingList().poll();
                Student existingStudent = (Student) studentRecordsMgr.getObjectFromList(student.getNetworkName());
                existingStudent.removeIndexFromWaitList(indexToUpdate);
                existingStudent.addIndexRegistered(indexToUpdate);
                indexToUpdate.removeFromWaitingList(existingStudent);
            }
        }

        ((CourseMgr) courseMgr).saveCourseObjectList();
        ((StudentRecordsMgr) studentRecordsMgr).saveStudentObjectList();
    }
}
