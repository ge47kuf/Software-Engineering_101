package de.tum.in.ase.eist.Model;


public class Employee extends Observable {
    private String name;
    private int id;
    private String token;
    private Role role;
    private Event eventPreference;
    private boolean isRegistered = false;

    public Employee() {
        name = "";
    }

    public Employee(String name, int id, String token, Role role, Event eventPreference) {
        this.name = name;
        this.id = id;
        this.token = token;
        this.role = role;
        this.eventPreference = eventPreference;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Event getEventPreference() {
        return eventPreference;
    }

    public void setEventPreference(Event eventPreference) {
        this.eventPreference = eventPreference;
    }

    public boolean getIsRegistered() {
        return isRegistered;
    }

    public void setIsRegistered(Boolean isRegistered) {
        this.isRegistered = isRegistered;
    }

    @Override
    public String toString() {
        return name;
    }
}