package de.tum.in.ase.eist;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


class BookTest {
    private String[] pages = new String[0];
    private final Book testBook = new Book("title", "author", pages);


	// TODO 1: Test getTitle()
    @Test
    public void testGetTitle() {
        Assertions.assertEquals("title", testBook.getTitle());
    }
	// TODO 2: Test getAuthor()
    @Test
    public void testGetAuthor() {
        Assertions.assertEquals("author", testBook.getAuthor());
    }
    // TODO 3: Test getPages() when zero pages
    @Test
    public void testNoPages() {
        Assertions.assertEquals(0, testBook.getPageCount());
    }
    // TODO 4: Test getPages() when three pages
    @Test
    public void testThreePages() {
        String[] page = new String[3];
        for (int i = 0; i < page.length; i++) {
            page[i] = "p" + i;
        }
        Book threePage = new Book("t", "a", page);
        Assertions.assertEquals(3, threePage.getPageCount());
    }
}
