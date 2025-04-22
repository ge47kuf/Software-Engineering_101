package de.tum.in.ase.eist.Controller;

import de.tum.in.ase.eist.Model.Employee;
import de.tum.in.ase.eist.Model.Event;
import de.tum.in.ase.eist.View.EmployeeCreationView;
import de.tum.in.ase.eist.View.EventListView;
import de.tum.in.ase.eist.View.EventRegistrationView;

public class Controller {
    private EventListView eventListView;
    private EventRegistrationView eventRegistrationView;
    private EmployeeCreationView employeeCreationView;

    public void displayEvent(Event event) {
        this.eventRegistrationView = new EventRegistrationView(this, event);
        this.eventRegistrationView.show();
    }

    public void setEventListView(EventListView eventListView) {
        this.eventListView = eventListView;
    }

    public void setEventRegistrationView(EventRegistrationView eventRegistrationView) {
        this.eventRegistrationView = eventRegistrationView;
    }

    public void saveEvent(Event event) {
        this.eventListView.saveEvent(event);
        event.notifyObservers();
    }


    // TODO 3: Implement addNewEmployee(...). Add the employee to the eventRegistrationView and then notify
    //  all of its observers
    public void addNewEmployee(Employee employee) {
        eventRegistrationView.addNewEmployee(employee); //in diese meth wurde employee geadded und addObserver
        employee.notifyObservers(); // wichtig da employee implement observable ist
    }

    public void displayEmployee(Employee employee) {
        this.employeeCreationView = new EmployeeCreationView(this, employee);
        this.employeeCreationView.show();
    }
}
