package de.krummacker.sorter;

public class BubbleSorterTest extends SorterBase {

    @Override
    protected Sorter<Integer> getSorter() {
        return new BubbleSorter<>();
    }
}