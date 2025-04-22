package de.tum.in.ase.pse.model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


/**
 * You don't need to change this class
 **/


public class CourseDAO {

    private Connection connection;

    private boolean db;

    private List<Course> courses;

    public CourseDAO(boolean inMemory) {
        db = true;
        try {
            String url;

            if (inMemory) {
                url = "jdbc:sqlite::memory:";
            } else {
                url = "jdbc:sqlite:pingu.db";
            }

            connection = DriverManager.getConnection(url);
            initializeDatabase();
        } catch (SQLException e) {
            throw new RuntimeException("Error connecting to the SQLite database", e);
        }
    }

    public CourseDAO() {
        db = false;
        this.courses = new ArrayList<>();
    }

    private void initializeDatabase() throws SQLException {
        try (Statement stmt = connection.createStatement()) {
            // Create a new table for courses
            String sql = "CREATE TABLE IF NOT EXISTS courses (id TEXT PRIMARY KEY, name TEXT NOT NULL)";
            stmt.execute(sql);
        }
    }

    public void addCourse(Course course) {
        if (!db) {
            this.courses.add(course);
            return;
        }

        String sql = "INSERT INTO courses (id, name) VALUES (?,?)";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, course.getId());
            pstmt.setString(2, course.getName());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void updateCourse(Course course) {
        if (!db) {
            for (Course course1 : courses) {
                if (course1.getId().equals(course.getId())) {
                    course1.setName(course.getName());
                    return;
                }
            }
            throw new RuntimeException("No element with id " + course.getId() + " found.");
        }

        String sql = "UPDATE courses SET name = ? WHERE id = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, course.getName());
            pstmt.setString(2, course.getId());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Course> getAll() {
        if (!db) {
            return List.copyOf(courses);
        }

        List<Course> courses = new ArrayList<>();
        String sql = "SELECT id, name FROM courses";

        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Course course = new Course(rs.getString("id"), rs.getString("name"));
                courses.add(course);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return courses;
    }
}

