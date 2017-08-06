package de.krummacker.sorter;

public class StandardApiSorterTest extends SorterBase {

    @Override
    protected Sorter<Integer> getSorter() {
        return new StandardApiSorter<>();
    }
}