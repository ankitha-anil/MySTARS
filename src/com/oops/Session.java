package com.oops;

import java.time.LocalTime;
import java.util.Date;

public class Session {

    private String venue;
    private int day;
    private String lessonType;
    private LocalTime startTime;
    private LocalTime endTime;
    private String labWeek = "both";

    public String getLabWeek() {
        return labWeek;
    }

    public int getDay() {
        return day;
    }

    public Session(int day, String venue, String lessonType, LocalTime startTime, LocalTime endTime) {
        this.day = day;
        this.venue = venue;
        this.lessonType = lessonType;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public Session(int day, String venue, String lessonType, String labWeek, LocalTime startTime, LocalTime endTime) {
        this.day = day;
        this.venue = venue;
        this.lessonType = lessonType;
        this.labWeek = labWeek;
        this.startTime = startTime;
        this.endTime = endTime;
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
        System.out.println(this.getVenue());
        System.out.println(this.getLessonType());
        System.out.println(this.getStartTime());
        System.out.println(this.getEndTime());
    }
}
