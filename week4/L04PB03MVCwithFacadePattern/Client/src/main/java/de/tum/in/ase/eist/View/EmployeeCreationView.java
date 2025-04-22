package de.tum.in.ase.eist.View;

import de.tum.in.ase.eist.Controller.Controller;
import de.tum.in.ase.eist.Model.Employee;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class EmployeeCreationView extends Stage implements Observer {
    private static final int PADDING = 10;
    private static final int SCENE_HEIGHT = 300;
    private static final int SCENE_WIDTH = 300;

    private static final int GRID_VGAP = 8;
    private static final int GRID_HGAP = 10;

    private Button saveButton;

    private Employee employee;
    private Controller controller;
    private TextField employeeNameTextField;

    public EmployeeCreationView(Controller controller, Employee employee) {
        this.employee = employee;
        this.controller = controller;
        this.employeeNameTextField = new TextField();
        this.employee.addObserver(this);

        generateUserInterface();
    }

    @Override
    public void update() {
        this.employeeNameTextField.setText(employee.getName());
        this.setTitle(employee.getName());
    }

    // TODO 3: Implement save() which is triggered when clicking the 'Save Changes' button
    private void save() {
        //TODO: set the employee name from the text field before adding it
        this.employee.setName(employeeNameTextField.getText());
        this.controller.addNewEmployee(employee);
    }

    private void generateUserInterface() {
        VBox vbox = new VBox();

        GridPane grid = new GridPane();
        grid.setPadding(new Insets(PADDING));
        grid.setVgap(GRID_VGAP);
        grid.setHgap(GRID_HGAP);

        Label employeeLabel = new Label("Employee: ");
        GridPane.setConstraints(employeeLabel, 0, 0);
        GridPane.setConstraints(employeeNameTextField, 1, 0);

        saveButton = new Button("Save Changes");
        GridPane.setConstraints(saveButton, 0, 2);
        saveButton.setOnAction(employee -> save());


        grid.getChildren().addAll(employeeLabel, saveButton, employeeNameTextField);
        vbox.getChildren().add(grid);

        Scene scene = new Scene(vbox, SCENE_WIDTH, SCENE_HEIGHT);
        setScene(scene);
    }
}