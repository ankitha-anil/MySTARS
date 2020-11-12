package Controller;

import java.time.LocalTime;
import java.util.ArrayList;
import Entity.*;


/*

Must change some methods depending on the Boundary Class

 */


public class timetableMgr {


    // Add an index into student's indexRegistered
    public void addCourse(Student student, Index index) {
        // Code the functions

    }


    // Check clash between an index and a List of Indexes
    // This method can be used to check clash with registered courses as well as courses on waitList
    private boolean checkClash(ArrayList<Index> studentIndex, Index index) {
        for (Session session : index.getLesson()
        ) {
            for (Index indexRegistered : studentIndex
            ) {
                for (Session registeredSession : indexRegistered.getLesson()
                ) {
                    if (session.getDay() != registeredSession.getDay())
                        continue;

                    if (session.getLessonType() == registeredSession.getLessonType() && session.getLessonType() == "Lab") {
                        if (session.getLabWeek() == "odd" && registeredSession.getLabWeek() == "even")
                            continue;
                        else if (session.getLabWeek() == "even" && registeredSession.getLabWeek() == "odd")
                            continue;
                    }

                    LocalTime time1, time2, time3, time4;
                    time1 = session.getStartTime();
                    time2 = session.getEndTime();
                    time3 = registeredSession.getStartTime();
                    time4 = registeredSession.getEndTime();

                    if ((time1.compareTo(time3) == -1 && time2.compareTo(time3) == 1) || time1.compareTo(time3) == 0)
                        return true;
                    else if ((time1.compareTo(time3) == 1 && time1.compareTo(time4) == -1))
                        return true;
                }

            }

        }
        return false;
    }


    // Check if student has already enrolled in this course or the student has put it on waitList
    private boolean checkExistingCourse(ArrayList<Index> studentIndex, Index index) {
        for (Index registeredIndex : studentIndex
        ) {
            if (index.getCourseCode() == registeredIndex.getCourseCode())
                return true;
        }
        return false;
    }

    public boolean checkVacancy(Index index) {
        if (index.getVacancy() == 0) {
            return true;
        }
        return false;
    }
}

