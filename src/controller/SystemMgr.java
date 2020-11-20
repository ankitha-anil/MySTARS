package controller;

import entity.Student;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

public class SystemMgr {

    public Object findObject(ArrayList<Object> objects, Object object) {
        if (objects == null)
            return null;
        for (Object objectItem : objects
        ) {
            if (objectItem.equals(object)) {
                return objectItem;
            }
        }
        return null;
    }

    public boolean checkTimeSanity(LocalTime startTime, LocalTime endTime) {
        return startTime.isBefore(endTime);
    }

    public boolean checkDateSanity(LocalDate startDate, LocalDate endDate) {
        return startDate.isBefore(endDate);
    }

    public boolean isAccessible() {
        LocalTime currentTime = LocalTime.now();
        LocalDate currentDate = LocalDate.now();
        if (Student.getStartDate().isBefore(currentDate) && Student.getEndDate().isAfter(currentDate))
            if (Student.getStartTime().isBefore(currentTime) && Student.getEndTime().isAfter(currentTime))
                return true;
        return false;
    }

}
