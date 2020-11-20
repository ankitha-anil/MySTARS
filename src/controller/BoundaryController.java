package controller;

import boundary.*;
import actor.Actor;

import java.io.IOException;

public class BoundaryController {

    public static void callAdminFunctionInterface(Actor actor) throws IOException {
        AdminFunctionsInterface.main(null, actor);
    }

    public static void callStudentUpdateInterface(Actor actor) {
        StudentUpdateInterface.main(null, actor);
    }

    public static void callCourseUpdateInterface(Actor actor) {
        CourseUpdateInterface.main(null, actor);
    }

    public static void callIndexUpdateInterface(Actor actor, String courseCode) {
        IndexUpdateInterface.main(null, actor, courseCode);
    }

    public static void callEmailAdminInterface(Actor actor) {
        EmailAdminInterface.main(null, actor);
    }

    public static void callStudentFunctionInterface(Actor actor) throws IOException {
        StudentFunctionsInterface.main(null, actor);
    }
}
