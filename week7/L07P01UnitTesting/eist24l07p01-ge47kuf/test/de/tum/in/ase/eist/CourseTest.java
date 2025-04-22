package de.tum.in.ase.eist;

import org.junit.jupiter.api.*;
import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.*;

class CourseTest {
    private Course course = new Course("test");

	// TODO 1: Test getCourseTitle()
    @Test
    void testGetCourseTitle() {
        // Course = new Course("test");
        assertEquals("test", course.getTitle());
    }
	// TODO 2: Test getNumberOfAttendees()
    @Test
    void testNoAttendees() {
        // Course = new Course("test");
        course.setAttendees(new ArrayList<>());
        assertEquals(0, course.getNumberOfAttendees());
    }

    @Test
    void testThreeAttendees() {
        for (int i = 0; i < 3; i++) {
            course.addAttendee(new Student("fn" + i, "ln" + i, "bd" + i,
                    "mas" + i, "mis" + i));
        }
        assertEquals(3, course.getNumberOfAttendees());
    }
}
