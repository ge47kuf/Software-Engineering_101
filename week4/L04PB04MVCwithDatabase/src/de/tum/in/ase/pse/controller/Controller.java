package de.tum.in.ase.pse.controller;

import de.tum.in.ase.pse.model.Course;
import de.tum.in.ase.pse.model.CourseDAO;
import de.tum.in.ase.pse.view.CourseDetailView;
import de.tum.in.ase.pse.view.CourseListView;

import java.util.List;

public class Controller {

    private CourseListView courseListView;
    private CourseDetailView courseDetailView;
    private CourseDAO courseDAO;

    public Controller(boolean db) {
        if (db) {
            this.courseDAO = new CourseDAO(true);
        } else {
            this.courseDAO = new CourseDAO();
        }
    }

    // TODO: Implement updateCourse(). This should update CourseDAO with the given Course and notify all
    //  Observers of this Course.
    public void updateCourse(Course course) {
        courseDAO.updateCourse(course);
        course.notifyObservers();
    }

    // TODO: Implement selectCourse(). This method should initialize courseDetailView and display it.
    public void selectCourse(Course course) {
        courseDetailView = new CourseDetailView(this, course);
        courseDetailView.show();
    }

    public void addCourse(Course course) {
        courseDAO.addCourse(course);
        course.notifyObservers();
    }

    public void setCourseDAO(CourseDAO courseDAO) {
        this.courseDAO = courseDAO;
    }

    public void setCourseListView(CourseListView courseListView) {
        this.courseListView = courseListView;
    }

    public List<Course> getAllCourses() {
        return courseDAO.getAll();
    }
}
