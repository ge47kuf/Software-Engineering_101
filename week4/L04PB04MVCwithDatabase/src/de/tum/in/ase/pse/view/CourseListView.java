package de.tum.in.ase.pse.view;

import de.tum.in.ase.pse.controller.Controller;
import de.tum.in.ase.pse.model.Course;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.List;

public class CourseListView extends Stage implements Observer {

	private static final int SCENE_WIDTH = 400;
	private static final int SCENE_HEIGHT = 400;
	private ObservableList<Course> courses;
	private ListView<Course> courseListView;
	private Controller controller;


	public CourseListView(Controller controller, List<Course> courseList) {
		this.controller = controller;
		this.courses = FXCollections.observableArrayList(courseList);
		for (Course course : courseList) {
			course.addObserver(this);
		}
		this.courseListView = new ListView<>(courses);
		generateUserInterface();
		controller.setCourseListView(this);
	}

	// TODO: Implement selectCourse(). Send the controller the information to open up a new detail view.
	private void selectCourse(Course course) {
		controller.selectCourse(course);
	}

	// TODO Implement update(). This method should refresh the courseListView.
	@Override
	public void update() {
		displayCourses();
	}

	private void displayCourses() {
		courseListView.refresh();
	}

	private void generateUserInterface() {
		VBox vbox = new VBox();


		Label courseListLabel = new Label("Course List");
		courseListView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
		courseListView.setOnMouseClicked(event -> selectCourse(courseListView.getSelectionModel().getSelectedItem()));
		vbox.getChildren().addAll(courseListLabel, courseListView);

		Scene scene = new Scene(vbox, SCENE_WIDTH, SCENE_HEIGHT);
		setScene(scene);
		setTitle("Course List");
		displayCourses();
	}
}
