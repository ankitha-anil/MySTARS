package controller;

import boundary.*;
import actor.Actor;

import java.io.IOException;

/**
 * Class that manages all the interfaces being called
 *
 * @author Anon
 */
public class BoundaryController {

    /**
     * Calls the interface for an admin who has logged in
     *
     * @param actor the admin
     * @throws IOException
     */
    public static void callAdminFunctionInterface(Actor actor) throws IOException {
        AdminFunctionsInterface.main(null, actor);
    }

    /**
     * Calls the interface for updating a student's details ( admin function)
     *
     * @param actor the admin
     */
    public static void callStudentUpdateInterface(Actor actor) {
        StudentUpdateInterface.main(null, actor);
    }

    /**
     * Calls the interface to update a course's details (admin function)
     *
     * @param actor the admin
     */
    public static void callCourseUpdateInterface(Actor actor) {
        CourseUpdateInterface.main(null, actor);
    }

    /**
     * Calls the interface to update an index's details for a particular course (the admin)
     *
     * @param actor      the admin
     * @param courseCode course code of the index to be updated
     */
    public static void callIndexUpdateInterface(Actor actor, String courseCode) {
        IndexUpdateInterface.main(null, actor, courseCode);
    }

    /**
     * Calls the interface for sending an email notification
     *
     * @param actor the admin
     */
    public static void callEmailAdminInterface(Actor actor) {
        EmailAdminInterface.main(null, actor);
    }

    /**
     * Calls the interface for a student who has logged in
     *
     * @param actor the admin
     * @throws IOException
     */
    public static void callStudentFunctionInterface(Actor actor) throws IOException {
        StudentFunctionsInterface.main(null, actor);
    }
}
