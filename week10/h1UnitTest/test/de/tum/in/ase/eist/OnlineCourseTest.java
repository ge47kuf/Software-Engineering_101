package de.tum.in.ase.eist;

import org.junit.jupiter.api.*;

import java.net.MalformedURLException;
import java.net.URL;

import static org.junit.jupiter.api.Assertions.*;

class OnlineCourseTest {
    private OnlineCourse onlineCourse;
    @BeforeEach
    public void setup() {
        onlineCourse = new OnlineCourse("testCourse");
    }

	// TODO 3: Test setOnlineCourseUrl()
    @Test
    public void testSetOnlineCourseUrlWithValidUrl() {
        try {
            URL validURL = new URL("https://www.example.com");
            onlineCourse.setUrl(String.valueOf(validURL));
            assertEquals(validURL, onlineCourse.getUrl());
        } catch (MalformedURLException e) {
            fail("MalformedURLException was thrown");
        }
    }

    @Test
    public void testSetOnlineCourseUrlWithInvalidUrl() {
        assertThrows(MalformedURLException.class, () -> onlineCourse.setUrl("www.example.com"));
    }
}
