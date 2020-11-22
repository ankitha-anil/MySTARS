package entity;

import java.io.Serializable;
import java.time.LocalTime;
import java.util.ArrayList;

/**
 * Entity Class Lesson that stores details about a lesson's timings
 *
 * @author Anon
 */
public class Lesson implements Serializable {
    /**
     * venue is the venue where the lesson is being held
     * day is the Day of the Week when the lesson is being held
     * lessonType describes whether the lesson is a tutorial, lecture, seminar etc.
     * startTime starting time of lesson
     * endTime ending time of lesson
     * labWeek specifies whether labs are held in the even week or odd week or both
     */
    private String venue;
    private int day;
    private String lessonType;
    private LocalTime startTime;
    private LocalTime endTime;
    private String labWeek = "both";

    /**
     * This is the constructor for objects of class Lesson
     *
     * @param day        Day of the Week when the lesson is being held
     * @param venue      venue is the venue where the lesson is being held
     * @param lessonType lessonType describes whether the lesson is a tutorial, lecture, seminar etc.
     * @param startTime  startTime starting time of lesson
     * @param endTime    endTime ending time of lesson
     */
    public Lesson(int day, String venue, String lessonType, LocalTime startTime, LocalTime endTime) {
        this.day = day;
        this.venue = venue;
        this.lessonType = lessonType.toLowerCase();
        this.startTime = startTime;
        this.endTime = endTime;
    }

    /**
     * This is the constructor for objects of class Lesson
     *
     * @param day        Day of the Week when the lesson is being held
     * @param venue      venue is the venue where the lesson is being held
     * @param lessonType lessonType describes whether the lesson is a tutorial, lecture, seminar etc.
     * @param labWeek    labWeek specifies whether labs are held in the even week or odd week or both
     * @param startTime  startTime starting time of lesson
     * @param endTime    endTime ending time of lesson
     */
    public Lesson(int day, String venue, String lessonType, String labWeek, LocalTime startTime, LocalTime endTime) {
        this.day = day;
        this.venue = venue;
        this.lessonType = lessonType.toLowerCase();
        this.labWeek = labWeek.toLowerCase();
        this.startTime = startTime;
        this.endTime = endTime;
    }

    /**
     * This is a Accessor function for labWeek
     *
     * @return returns the weeks in which labs are held
     */
    public String getLabWeek() {
        return labWeek;
    }

    /**
     * This is a mutator function for labweek
     *
     * @param labWeek lab week to be set
     */
    public void setLabWeek(String labWeek) {
        this.labWeek = labWeek;
    }

    /**
     * This is the accessor function for day of the week in which the lesson is held
     *
     * @return returns the lab week
     */
    public int getDay() {
        return day;
    }

    /**
     * This is the mutator function for the day of the week in which the lesson is being held
     *
     * @param day day to be set
     */
    public void setDay(int day) {
        this.day = day;
    }

    /**
     * This is the accessor function for the venue the lesson is being held
     *
     * @return returns the venue of the lesson
     */
    public String getVenue() {
        return venue;
    }

    /**
     * This is the mutator function  for venue where the lesson is being held
     *
     * @param venue venue to be set
     */
    public void setVenue(String venue) {
        this.venue = venue;
    }

    /**
     * This is the accessor function for the type of the lesson
     *
     * @return returns the lesson type
     */
    public String getLessonType() {
        return lessonType;
    }

    /**
     * This is the mutator function for the type of lesson
     *
     * @param lessonType lesson type to be set
     */
    public void setLessonType(String lessonType) {
        this.lessonType = lessonType;
    }

    /**
     * This is the accessor function for the ending time of the lesson
     *
     * @return returns the ending time of the lesson
     */
    public LocalTime getEndTime() {
        return endTime;
    }

    /**
     * This is the mutator function for the ending time of the lesson
     *
     * @param endTime ending time to be set
     */
    public void setEndTime(LocalTime endTime) {
        this.endTime = endTime;
    }

    /**
     * This is the accessor function for the starting time of the lesson
     *
     * @return returns the starting time of the lesson
     */
    public LocalTime getStartTime() {
        return startTime;
    }

    /**
     * This is the mutator function for the starting time of the lesson
     *
     * @param startTime starting time of the lesson to be set
     */
    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
    }

    /**
     * This function prints the lesson type, venue, weeks it is held in, start time and end time
     */
    public void print() {
        String lesson = lessonType.toUpperCase().charAt(0) + lessonType.toLowerCase().substring(1);
        String weeks = "1-13";
        if (lessonType.equals("lecture"))
            weeks = "1-13";
        else if (lessonType.equals("tutorial"))
            weeks = "2-13";
        else if (lessonType.equals("lab")) {
            if (labWeek.equals("both"))
                weeks = "2-13";
            else if (labWeek.equals("odd"))
                weeks = "3-5-7-9-11-13";
            else if (labWeek.equals("even"))
                weeks = "2-4-6-8-10-12";
        }
        System.out.printf(" %4c %10s %7c %9s %3c %14s %2c %16s %2c\n",'|', lesson,'|', venue,'|', weeks,'|',startTime + " to " + endTime,'|');
        System.out.println();
    }

    /**
     * Checks if the timings of this lesson object is clashes with a given lesson
     *
     * @param lesson lesson object to compare with
     * @return returns a boolean value that determines if the lessons clash
     */
    public boolean checkCLash(Lesson lesson) {
        if (lesson.getDay() != this.getDay())
            return false;

        if (lesson.getLessonType().equals(this.getLessonType()) && lesson.getLessonType().equals("lab")) {
            if (lesson.getLabWeek().equals("odd") && this.getLabWeek().equals("even"))
                return false;
            else if (lesson.getLabWeek().equals("even") && this.getLabWeek().equals("odd"))
                return false;
        }

        LocalTime time1, time2, time3, time4;
        time1 = lesson.getStartTime();
        time2 = lesson.getEndTime();
        time3 = this.getStartTime();
        time4 = this.getEndTime();

        if ((time1.isBefore(time3) && time2.isAfter(time3)) || time1.compareTo(time3) == 0)
            return true;
        else return time1.isAfter(time3) && time1.isBefore(time4);

    }
}
