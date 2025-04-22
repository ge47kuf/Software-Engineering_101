package de.tum.in.ase.eist;
import org.easymock.*;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;

import java.time.LocalDate;

import static org.easymock.EasyMock.*;
import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(EasyMockExtension.class)
class DiscussionTest {
    // wegen badCoding
    public static final int YEAR = 2000;
    public static final int MONTH = 2;
    public static final int DAY = 2;
    @TestSubject//SUT
    private Discussion discussion;
    @Mock//TestModel
    private Course courseMock;
    @Mock//TestModel
    private Comment commentMock;
    @BeforeEach
    public void setup() {
        this.discussion = new Discussion();
    }
    // TODO implement the tests
    @Test
    public void testComment() {
        expect(commentMock.save()).andReturn(true);
        replay(commentMock);

        discussion.addComment(commentMock);
        assertEquals(1, discussion.getNumberOfComments());

        verify(commentMock);
    }
    @Test
    public void testCommentIfSavingFails() {
        expect(commentMock.save()).andReturn(false);
        replay(commentMock);

        assertFalse(discussion.addComment(commentMock));
        assertEquals(0, discussion.getNumberOfComments());

        verify(commentMock);
    }
    @Test
    public void testStartCourseDiscussion() {
        Person lecturer = new Lecturer("john", "schnee",
                LocalDate.of(YEAR, MONTH, DAY));
        expect(courseMock.isDiscussionAllowed(lecturer)).andReturn(true);

        replay(courseMock);

        assertTrue(discussion.startCourseDiscussion(courseMock, lecturer, "topic"));
        // bc methode set course and topic to the attribute
        assertEquals(courseMock, discussion.getCourse());
        assertEquals("topic", discussion.getTopic());

        verify(courseMock);
    }
}
