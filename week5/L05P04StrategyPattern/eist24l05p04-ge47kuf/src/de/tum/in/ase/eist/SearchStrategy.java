package de.tum.in.ase.eist;
import java.util.List;

public interface SearchStrategy {

    int performSearch(List<Chapter> chapterList, String s);
}
