package de.tum.in.ase.eist;

import java.util.List;

public class Context {
    private SearchStrategy searchAlgorithm;
    private List<Chapter> book;

    public Context() {

    }

    public int search(String s) {
        return searchAlgorithm.performSearch(book, s);
    }

    public boolean isChaptersSortedByName() {
        if (book == null || book.size() <= 1) {
            return true;
        }
        for (int i = 1; i < book.size(); i++) {
            if (book.get(i - 1).getName().compareTo(book.get(i).getName()) > 0) {
                return false;
            }
        }
        return true;
    }


    public SearchStrategy getSearchAlgorithm() {
        return searchAlgorithm;
    }

    public void setSearchAlgorithm(SearchStrategy searchAlgorithm) {
        this.searchAlgorithm = searchAlgorithm;
    }

    public List<Chapter> getBook() {
        return book;
    }

    public void setBook(List<Chapter> book) {
        this.book = book;
    }
}
