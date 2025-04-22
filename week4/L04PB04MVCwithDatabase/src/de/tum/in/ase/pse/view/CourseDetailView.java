package de.tum.in.ase.pse.view;

import de.tum.in.ase.pse.controller.Controller;
import de.tum.in.ase.pse.model.Course;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class CourseDetailView extends Stage implements Observer {

	private static final int PADDING = 10;
	private static final int SCENE_HEIGHT = 300;
	private static final int SCENE_WIDTH = 300;
	private static final int GRID_VGAP = 8;
	private static final int GRID_HGAP = 10;
	private final Label idText;
	private final TextField nameTextField;
	private Controller controller;
	private Course course;
	private Button saveButton;

	public CourseDetailView(Controller controller, Course course) {
		this.controller = controller;
		this.course = course;
		course.addObserver(this);

		this.idText = new Label(course.getId());
		this.nameTextField = new TextField(course.getName());

		generateUserInterface();
	}

	//TODO: Implement save(). This method should update the Course and then get saved with the controller
	private void save() {
		course.setName(nameTextField.getText());
		controller.updateCourse(this.course);
	}

	@Override
	public void update() {
		this.nameTextField.setText(course.getName());
		this.setTitle(course.toString());
	}

	private void generateUserInterface() {
		VBox vbox = new VBox();

		GridPane grid = new GridPane();
		grid.setPadding(new Insets(PADDING));
		grid.setVgap(GRID_VGAP);
		grid.setHgap(GRID_HGAP);

		Label idLabel = new Label("ID: ");
		GridPane.setConstraints(idLabel, 0, 0);
		GridPane.setConstraints(idText, 1, 0);
		Label nameLabel = new Label("Name ");
		GridPane.setConstraints(nameLabel, 0, 1);
		GridPane.setConstraints(nameTextField, 1, 1);

		saveButton = new Button("save changes");
		GridPane.setConstraints(saveButton, 0, 2);
		saveButton.setOnAction(event -> {
			save();
			Stage stage = (Stage) saveButton.getScene().getWindow();
			stage.close();
		});

		grid.getChildren().addAll(idLabel, nameLabel);
		grid.getChildren().addAll(idText, nameTextField);
		grid.getChildren().add(saveButton);
		vbox.getChildren().add(grid);

		Scene scene = new Scene(vbox, SCENE_WIDTH, SCENE_HEIGHT);
		setScene(scene);
		setTitle(this.course.getId() + " " + this.course.getName());
	}
}
