package de.tum.in.ase.eist;
import java.util.List;

public class BinarySearch implements SearchStrategy {

    @Override
    public int performSearch(List<Chapter> book, String name) {
        if (book == null || name == null) {
            return -1;
        }
        System.out.print("bin");
        return binarySearch(book, name);
    }

    private int binarySearch(List<Chapter> book, String name) {
        // book.sort(Comparator.comparing(Chapter::getName));
        int low  = 0;
        int high = book.size() - 1;

        while (low <= high) {
            int mid = (low + high) / 2;
            Chapter tmp = book.get(mid);
            int x = tmp.getName().compareTo(name);

            if (x == 0) {
                return tmp.getPageNumber();
            } else if (x > 0) {
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return -1;
    }
}
