package de.tum.in.ase.eist;

import org.easymock.*;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;

import java.time.LocalDate;

import static org.easymock.EasyMock.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(EasyMockExtension.class)
class DiscussionTest {
    // TODO implement the tests
    public static final int YEAR = 2000;
    public static final int MONTH = 2;
    public static final int DAY = 2;

    @TestSubject
    private Discussion discussion = new Discussion();

    @Mock
    private Course courseMock;
    @Mock
    private Comment commentMock;

    @Test
    public void testComment() {
        expect(commentMock.save()).andReturn(true);
        replay(commentMock);

        int commentNrCheck = discussion.getNumberOfComments();
        assertTrue(discussion.addComment(commentMock));
        assertEquals(++commentNrCheck, discussion.getNumberOfComments());

        verify(commentMock);
    }
    @Test
    public void testCommentIfSavingFails() {
        expect(commentMock.save()).andReturn(false);
        replay(commentMock);

        int commentNrCheck = discussion.getNumberOfComments();
        assertFalse(discussion.addComment(commentMock));
        assertEquals(commentNrCheck, discussion.getNumberOfComments());

        verify(commentMock);
    }
    @Test
    public void testStartCourseDiscussion() {
        Person student = new Student("fn", "ln",
                LocalDate.of(YEAR, MONTH, DAY), "ms", "ms");
        String topic = "cTp";

        expect(courseMock.isDiscussionAllowed(student)).andReturn(true);
        replay(courseMock);

        assertTrue(discussion.startCourseDiscussion(courseMock, student, topic));
        // check if startCourseDiscussion() check the given parameter
        assertEquals(courseMock, discussion.getCourse());
        assertEquals(topic, discussion.getTopic());

        verify(courseMock);
    }
}
