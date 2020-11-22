package controller;

import java.time.LocalTime;
import java.util.ArrayList;

import entity.*;

/**
 * @author Anon
 * Class that manages the timetable of a student
 */

public class TimeTableMgr {

    // Two overloaded functions
    // First one does normal checking
    // Second one additional parameter that skips the index

    /**
     * Checks if timings of the lessons of the existing indices in a timetable clash with the timings of the lessons of another index
     * If the two lessons being compared are labs, then the function checks if the weeks for them are alternate ones. No clash in this case
     *
     * @param studentIndex array of indices in the student's timetable
     * @param index        additional index for which clash is being checked
     * @return returns a boolean value that determines whether the timings clash or not
     */
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

    /**
     * Checks if timings of the lessons of the existing indices in a timetable clash with the timings of the lessons of another index
     * If the two lessons being compared are labs, then the function checks if the weeks for them are alternate ones. No clash in this case
     * This overloaded function is required while changing the index or swapping it with another student as the old index should not be considered while checking for clash
     *
     * @param studentIndex array of indices in the student's timetable
     * @param index        additional index for which clash is being checked
     * @param skipIndex    index present in the timetable that should be considered while checking for clash. It is the old index of the student in the event of changing or swapping
     * @return returns a boolean value that determines whether the timings clash or not
     */
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

    /**
     * Checks if student has already enrolled in this course or the student has put it on waitList by checking if an index exists in a list of indexes
     *
     * @param studentIndex list of indices that the student has been registered for
     * @param index        index that needs to be checked
     * @return returns a boolean value that determines if an index is present in a list of indexes
     */
    public boolean checkExistingCourse(ArrayList<Index> studentIndex, Index index) {
        for (Index registeredIndex : studentIndex
        ) {
            if (index.getCourseCode().equals(registeredIndex.getCourseCode()))
                return true;
        }
        return false;
    }

    // This function has not been used anywhere

    /**
     * Checks if index has vacancies
     *
     * @param index index object whose vacancies need to be known
     * @return boolean value indicating if the index has vacancies or not
     */
    public boolean checkVacancy(Index index) {
        return index.getVacancy() == 0;
    }
}

