package Entity;

import java.io.Serializable;
import java.time.LocalTime;

public class Session implements Serializable {

    private String venue;
    private int day;
    private String lessonType;
    private LocalTime startTime;
    private LocalTime endTime;
    private String labWeek = "both";

    public Session(int day, String venue, String lessonType, LocalTime startTime, LocalTime endTime) {
        this.day = day;
        this.venue = venue;
        this.lessonType = lessonType.toLowerCase();
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public Session(int day, String venue, String lessonType, String labWeek, LocalTime startTime, LocalTime endTime) {
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
        System.out.print(lesson + " ");
        if (lessonType.equals("lab"))
            System.out.print("Weeks: " + labWeek);
        System.out.println("\nVenue: " + venue);
        System.out.println(startTime + " to " + endTime);
    }

    public boolean checkCLash(Session session) {
        if (session.getDay() != this.getDay())
            return false;

        if (session.getLessonType().equals(this.getLessonType()) && session.getLessonType().equals("lab")) {
            if (session.getLabWeek().equals("odd") && this.getLabWeek().equals("even"))
                return false;
            else if (session.getLabWeek().equals("even") && this.getLabWeek().equals("odd"))
                return false;
        }

        LocalTime time1, time2, time3, time4;
        time1 = session.getStartTime();
        time2 = session.getEndTime();
        time3 = this.getStartTime();
        time4 = this.getEndTime();

        if ((time1.isBefore(time3) && time2.isAfter(time3)) || time1.compareTo(time3) == 0)
            return true;
        else return time1.isAfter(time3) && time1.isBefore(time4);

    }
}
