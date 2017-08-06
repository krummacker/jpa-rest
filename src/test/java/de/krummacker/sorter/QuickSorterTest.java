package de.krummacker.sorter;

public class QuickSorterTest extends SorterBase {

    @Override
    protected Sorter<Integer> getSorter() {
        return new QuickSorter<>();
    }
}