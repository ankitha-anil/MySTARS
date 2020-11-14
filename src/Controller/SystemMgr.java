package Controller;

import Entity.Course;
import Entity.Student;
import com.sun.source.tree.BreakTree;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

public class SystemMgr {

    public Object findObject(ArrayList<Object> objects, Object object) {
        Object refObject = null;
        for (Object objectItem : objects
        ) {
            if (objectItem.equals(object)) {
                refObject = objectItem;
                return refObject;
            }

        }
        return refObject;
    }

    public boolean checkTimeSanity(LocalTime startTime, LocalTime endTime) {
        if (startTime.compareTo(endTime) >= 0)
            return true;
        return false;
    }

    public boolean checkDateSanity(LocalDate startDate, LocalDate endDate) {
        return startDate.isBefore(endDate);
    }

    public boolean isAccessible() {
        LocalTime currentTime = LocalTime.now();
        LocalDate currentDate = LocalDate.now();
        if (Student.getStartDate().isBefore(currentDate) && Student.getEndDate().isAfter(currentDate))
            if (Student.getStartTime().isAfter(currentTime) && Student.getEndTime().isBefore(currentTime))
                return true;
        return false;
    }

}
