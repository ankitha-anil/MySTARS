package entity;

import java.io.Serializable;
import java.time.LocalTime;
import java.util.ArrayList;

public class Lesson implements Serializable {

    private String venue;
    private int day;
    private String lessonType;
    private LocalTime startTime;
    private LocalTime endTime;
    private String labWeek = "both";

    public Lesson(int day, String venue, String lessonType, LocalTime startTime, LocalTime endTime) {
        this.day = day;
        this.venue = venue;
        this.lessonType = lessonType.toLowerCase();
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public Lesson(int day, String venue, String lessonType, String labWeek, LocalTime startTime, LocalTime endTime) {
        this.day = day;
        this.venue = venue;
        this.lessonType = lessonType.toLowerCase();
        this.labWeek = labWeek.toLowerCase();
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public String getLabWeek() {
        return labWeek;
    }

    public void setLabWeek(String labWeek) {
        this.labWeek = labWeek;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public String getVenue() {
        return venue;
    }

    public void setVenue(String venue) {
        this.venue = venue;
    }

    public String getLessonType() {
        return lessonType;
    }

    public void setLessonType(String lessonType) {
        this.lessonType = lessonType;
    }

    public LocalTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalTime endTime) {
        this.endTime = endTime;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
    }

    public void print() {
        String lesson = lessonType.toUpperCase().charAt(0) + lessonType.toLowerCase().substring(1);
        System.out.println(lesson);
        System.out.println("Venue: " + venue);
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
        System.out.println("Weeks: " + weeks);
        System.out.println(startTime + " to " + endTime);
        System.out.println();
    }

    public static void printObjects(ArrayList<Lesson> lessons) {
        for (Lesson lesson : lessons
        ) {
            lesson.print();
        }
    }

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
