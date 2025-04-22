package de.tum.in.ase.eist.foodpalace;

public class Employee {
    private final String name;
    private final boolean isQualified;

    public Employee(String name, boolean isQualified) {
        this.name = name;
        this.isQualified = isQualified;
    }

    public String getName() {
        return name;
    }

    public boolean isQualified() {
        return isQualified;
    }
}
