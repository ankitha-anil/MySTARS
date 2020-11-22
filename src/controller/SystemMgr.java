package controller;

import entity.Student;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

/**
 * @author group
 */
public class SystemMgr {



    /**
     * This function checks if a given objects exists in a list of objects. Dynamic Binding is taking place because the entity classes have overriden the equals method of the Object class. Therefore, this function can be used for all entity classes
     *
     * @param objects Array List of objects
     * @param object  an object
     * @return objectItem if found else return null
     */
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

    /**
     * This function ensures that the start time is before end time
     *
     * @param startTime the starting time of the registration period
     * @param endTime   the ending time of the registeration period
     * @return boolean True or False
     */
    public boolean checkTimeSanity(LocalTime startTime, LocalTime endTime) {
        return startTime.isBefore(endTime);
    }

    /**
     * This function ensures that the starting date is before the ending date
     *
     * @param startDate starting date of the registeration period
     * @param endDate   ending date of the registeration period
     * @return boolean Trur or False
     */
    public boolean checkDateSanity(LocalDate startDate, LocalDate endDate) {
        return startDate.isBefore(endDate);
    }

    /**
     * This functions returns True if both the start time and start date are before end time and end date respectively else it returns false
     * @return boolean True or False
     */ 
    public boolean isAccessible() {
        LocalTime currentTime = LocalTime.now();
        LocalDate currentDate = LocalDate.now();
        if (Student.getStartDate().isBefore(currentDate) && Student.getEndDate().isAfter(currentDate))
            if (Student.getStartTime().isBefore(currentTime) && Student.getEndTime().isAfter(currentTime))
                return true;
        return false;
    }

}
