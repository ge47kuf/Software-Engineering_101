package de.tum.in.ase.eist;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class ReferenceMonitor {

    /*
     TODO
      Add a private, final variable acl of type HashMap<UserObjectTuple, Set<Permission>>.
      Do not forget to initialize it in the constructor.
    */
    private final HashMap<UserObjectTuple, Set<Permission>> acl;

    public ReferenceMonitor() {
        this.acl = new HashMap<>();
    }

    /*
     TODO
      Check if a user has a specified Permission for a File
    */
    public boolean checkPermission(User user, File file, Permission permission) {
        // object = file
        UserObjectTuple tmp = new UserObjectTuple(user, file);
        // user nicht in db
        if (!acl.containsKey(tmp)) {
            return false;
        }

        Set<Permission> permissions = acl.getOrDefault(tmp, null);
        if (permissions == null) {
            return false;
        }

        return permissions.contains(permission);
    }

    /*
     TODO
      Add a specific permission for a given user and file to the acl.
    */
    public void addPermission(User user, File file, Permission permission) {
        // object = file
        UserObjectTuple tmp = new UserObjectTuple(user, file);

        // add permission, if user not in db -> add to db
        Set<Permission> permissions = acl.computeIfAbsent(tmp, k -> new HashSet<>());
        permissions.add(permission);
        // acl.put(tmp, permissions);
    }

    /*
     TODO
      Remove a specific permission for a given user and file from the acl.
    */
    public void removePermission(User user, File file, Permission permission) {
        // object = file
        UserObjectTuple tmp = new UserObjectTuple(user, file);
        // user nicht in db
        if (!acl.containsKey(tmp)) {
            return;
        }
        // get rm and check
        Set<Permission> permissions = acl.getOrDefault(tmp, null);
        if (permissions == null) {
            return;
        }
        permissions.remove(permission);

    }

    public void readFile(User user, File file) {
        if (!this.checkPermission(user, file, Permission.READ)) {
            return ;
        }

        System.out.println("Reading file " + file.getName() + " for user " + user.getName());
        Path path = Paths.get(file.getAbsolutePath());
        try {
            byte[] bytes = java.nio.file.Files.readAllBytes(path);
            System.out.println("File contents: " + new String(bytes));
        } catch (java.io.IOException e) {
            System.out.println("Error reading file " + file.getName() + ": " + e.getMessage());
        }
    }

    public void writeFile(User user, File file, String content) {
        if (!this.checkPermission(user, file, Permission.WRITE)) {
            return ;
        }
        System.out.println("Writing to file " + file.getName() + " for user " + user.getName());
        Path path = Paths.get(file.getAbsolutePath());
        try {
            java.nio.file.Files.write(path, content.getBytes());
        } catch (java.io.IOException e) {
            System.out.println("Error writing file " + file.getName() + ": " + e.getMessage());
        }
    }

    public void executeFile(User user, File file) {
        if (!this.checkPermission(user, file, Permission.EXECUTE)) {
            return ;
        }

        System.out.println("Executing file " + file.getName() + " for user " + user.getName());
        try {
            Runtime.getRuntime().exec(file.getAbsolutePath());
        } catch (java.io.IOException e) {
            System.out.println("Error executing file " + file.getName() + ": " + e.getMessage());
        }
    }

    /*
     TODO
      Adjust the getter to return the access control list
    */
    public HashMap<UserObjectTuple, Set<Permission>> getAcl() {
        return this.acl;
    }

}
