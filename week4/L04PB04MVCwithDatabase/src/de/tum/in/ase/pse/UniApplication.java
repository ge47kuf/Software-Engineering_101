package de.tum.in.ase.pse;

import de.tum.in.ase.pse.controller.Controller;
import de.tum.in.ase.pse.model.Course;
import de.tum.in.ase.pse.view.CourseListView;
import javafx.application.Application;
import javafx.stage.Stage;


public final class UniApplication extends Application {

    /**
     * main method in order to be called from University Main.
     */
    public static void startApp(String[] args) {
        launch(args);
    }

    /**
     * This method is setting up everything for local testing.
     */
    @Override
    public void start(Stage primaryStage) {
        CourseListView courseListView = getCourseListView();
        courseListView.show();

    }
    private static CourseListView getCourseListView() {
        Controller controller = new Controller(true);

        if (controller.getAllCourses().size() == 0) {
            controller.addCourse(new Course("IN2081", "Design Patterns"));
            controller.addCourse(new Course("IN0006", "EIST"));
            controller.addCourse(new Course("IN0012", "Interactive Learning"));
            controller.addCourse(new Course("IN0014", "Agile Project Management"));
        }

        return new CourseListView(controller, controller.getAllCourses());

    }

}