package de.tum.in.ase.eist;
import java.util.List;

public class LinearSearch implements SearchStrategy {
    @Override
    public int performSearch(List<Chapter> book, String name) {
        if (book == null || name == null) {
            return -1;
        }
        System.out.print("linear");
        return linearSearch(book, name);
    }

    private int linearSearch(List<Chapter> book, String name) {
        for (int i = 0; i < book.size(); i++) {
            Chapter c = book.get(i);

            if (name.equals(c.getName())) {
                return c.getPageNumber();
            }
        }
        return -1;
    }
}
