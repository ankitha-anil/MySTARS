package controller;

import java.time.LocalTime;
import java.util.ArrayList;

import entity.*;

public class TimeTableMgr {

    // Two overloaded functions
    // First one does normal checking
    // Second one additional parameter that skips the index

    public boolean checkClash(ArrayList<Index> studentIndex, Index index) {
        for (Lesson lesson : index.getLessons()
        ) {
            for (Index indexRegistered : studentIndex
            ) {
                for (Lesson registeredLesson : indexRegistered.getLessons()
                ) {
                    if (lesson.getDay() != registeredLesson.getDay())
                        continue;

                    if (lesson.getLessonType().equals(registeredLesson.getLessonType()) && lesson.getLessonType().equals("lab")) {
                        if (lesson.getLabWeek().equals("odd") && registeredLesson.getLabWeek().equals("even"))
                            continue;
                        else if (lesson.getLabWeek().equals("even") && registeredLesson.getLabWeek().equals("odd"))
                            continue;
                    }

                    LocalTime time1, time2, time3, time4;
                    time1 = lesson.getStartTime();
                    time2 = lesson.getEndTime();
                    time3 = registeredLesson.getStartTime();
                    time4 = registeredLesson.getEndTime();

                    if ((time1.isBefore(time3) && time2.isAfter(time3)) || time1.compareTo(time3) == 0)
                        return true;
                    else if ((time1.isAfter(time3) && time1.isBefore(time4)))
                        return true;
                }

            }

        }
        return false;
    }


    // Check clash between an index and a List of Indexes
    // This method can be used to check clash with registered courses as well as courses on waitList
    public boolean checkClash(ArrayList<Index> studentIndex, Index index, Index skipIndex) {
        for (Lesson lesson : index.getLessons()
        ) {
            for (Index indexRegistered : studentIndex
            ) {
                if (studentIndex.equals(skipIndex))
                    continue;
                for (Lesson registeredLesson : indexRegistered.getLessons()
                ) {
                    if (lesson.getDay() != registeredLesson.getDay())
                        continue;

                    if (lesson.getLessonType().equals(registeredLesson.getLessonType()) && lesson.getLessonType().equals("lab")) {
                        if (lesson.getLabWeek().equals("odd") && registeredLesson.getLabWeek().equals("even"))
                            continue;
                        else if (lesson.getLabWeek().equals("even") && registeredLesson.getLabWeek().equals("odd"))
                            continue;
                    }

                    LocalTime time1, time2, time3, time4;
                    time1 = lesson.getStartTime();
                    time2 = lesson.getEndTime();
                    time3 = registeredLesson.getStartTime();
                    time4 = registeredLesson.getEndTime();

                    if ((time1.isBefore(time3) && time2.isAfter(time3)) || time1.compareTo(time3) == 0)
                        return true;
                    else if ((time1.isAfter(time3) && time1.isBefore(time4)))
                        return true;
                }

            }

        }
        return false;
    }


    // Check if student has already enrolled in this course or the student has put it on waitList
    public boolean checkExistingCourse(ArrayList<Index> studentIndex, Index index) {
        for (Index registeredIndex : studentIndex
        ) {
            if (index.getCourseCode().equals(registeredIndex.getCourseCode()))
                return true;
        }
        return false;
    }

    // This function has not been used anywhere
    public boolean checkVacancy(Index index) {
        return index.getVacancy() == 0;
    }
}

