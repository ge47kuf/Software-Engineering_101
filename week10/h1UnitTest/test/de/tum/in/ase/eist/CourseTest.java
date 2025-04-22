package de.tum.in.ase.eist;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

class CourseTest {
    private Course mathCourse;
    @BeforeEach
    public void setup() {
        mathCourse = new Course("math");
    }

	// TODO 1: Test getCourseTitle()
    @Test
    public void testGetCourseTitle() {
        assertEquals("math", mathCourse.getTitle());
    }

	// TODO 2: Test getNumberOfAttendees()
    @Test
    public void testNoAttendees() {
        assertEquals(0, mathCourse.getNumberOfAttendees());
    }

    @Test
    public void testThreeAttendees() {
        for (int i = 0; i < 3; i++) {
            mathCourse.addAttendee(new Student("student" + i, "l" + i,
                    "heute", "ma", "mi"));
        }
        assertEquals(3, mathCourse.getNumberOfAttendees());
    }

}
