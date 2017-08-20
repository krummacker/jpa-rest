package de.krummacker.sorter;

import de.krummacker.tools.Tools;

import java.util.Arrays;
import java.util.List;

/**
 * Main class for performance testing various sorters.
 */
public class SorterPerformanceTester {

    public static void main(String[] args) {

        // Sorters are ordered by resilience against stack overflow errors and then performance.
        List<Sorter<Integer>> sorters = Arrays.asList(
                new RemoveAddQuickSorter<Integer>(),
                new QuickSorter<Integer>(),
                new RandomPivotQuickSorter<Integer>(),
                new BubbleSorter<Integer>(),
                new MedianQuickSorter<Integer>(),
                new MultithreadedQuickSorter<Integer>(),
                new StandardApiSorter<Integer>());

        for (int i = 1000; i <= 8000; i += 1000) {
            System.out.print("Number of elements: " + i + "; ");
            for (Sorter<Integer> sorter : sorters) {
                List<Integer> input = Tools.createRandomList(i);

                long before = System.currentTimeMillis();
                List<Integer> sorted = sorter.sort(input);
                long after = System.currentTimeMillis();
                System.out.print("Random: " + sorter.getClass().getSimpleName() + " in ms: " + (after - before) + "; ");

                before = System.currentTimeMillis();
                sorter.sort(sorted);
                after = System.currentTimeMillis();
                System.out.print("Sorted: " + sorter.getClass().getSimpleName() + " in ms: " + (after - before) + "; ");
            }
            System.out.println();
        }
    }
}
