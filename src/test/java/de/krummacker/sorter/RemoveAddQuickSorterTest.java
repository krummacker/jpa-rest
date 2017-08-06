package de.krummacker.sorter;

public class RemoveAddQuickSorterTest extends SorterBase {

    @Override
    protected Sorter<Integer> getSorter() {
        return new RemoveAddQuickSorter<>();
    }
}