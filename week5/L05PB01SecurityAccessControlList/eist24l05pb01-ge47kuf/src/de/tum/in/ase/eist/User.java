package de.tum.in.ase.eist;

import java.io.File;

public class User{
    private String name;
    private ReferenceMonitor monitor;

    public User(String name) {
        this.name = name;
        this.monitor = new ReferenceMonitor();
    }

    public void readFile(File file) {
        monitor.readFile(this, file);
    }
    public void writeFile(File file, String content) {
        monitor.writeFile(this, file, content);
    }
    public void executeFile(File file) {
        monitor.executeFile(this, file);
    }

    public String getName() {
        return name;
    }

    public ReferenceMonitor getMonitor() {
        return monitor;
    }
}
