package de.krummacker.sorter;

public class RandomPivotQuickSorterTest extends SorterBase {

    @Override
    protected Sorter<Integer> getSorter() {
        return new RandomPivotQuickSorter<>();
    }
}