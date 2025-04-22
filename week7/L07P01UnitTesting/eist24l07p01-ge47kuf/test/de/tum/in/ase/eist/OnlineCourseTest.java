package de.tum.in.ase.eist;

import org.junit.jupiter.api.*;

import java.net.MalformedURLException;
import java.net.URL;
import static org.junit.jupiter.api.Assertions.*;

class OnlineCourseTest {
    private OnlineCourse onlineCourse = new OnlineCourse("test");

    // TODO 3: Test setOnlineCourseUrl()
    @Test
    void testSetOnlineCourseUrlWithValidUrl() {
        try {
            URL url = new URL("https://www.example.com");
            onlineCourse.setUrl("https://www.example.com");
            assertEquals(url, onlineCourse.getUrl());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    @Test
    void testSetOnlineCourseUrlWithInvalidUrl() {
        assertThrows(MalformedURLException.class, () -> {
            onlineCourse.setUrl("invalidURL");
        });
    }
}
