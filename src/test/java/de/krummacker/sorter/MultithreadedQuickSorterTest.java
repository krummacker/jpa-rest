package de.krummacker.sorter;

public class MultithreadedQuickSorterTest extends SorterBase {

    @Override
    protected Sorter<Integer> getSorter() {
        return new MultithreadedQuickSorter<>();
    }
}