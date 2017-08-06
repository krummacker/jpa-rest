package de.krummacker.sorter;

import java.util.Collections;
import java.util.List;

/**
 * Sorts Lists using the standard sort algorithm of the API.
 *
 * @param <T> the type of objects to be sorted
 */
public class StandardApiSorter<T extends Comparable> implements Sorter<T> {

    /**
     * Creates a new StandardApiSorter.
     */
    public StandardApiSorter() {
    }

    @Override
    public List<T> sort(List<T> input) {
        Collections.sort(input);
        return input;
    }
}
