package de.krummacker.sorter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * Sorts Lists using the quick sort algorithm. It uses multiple threads to get the work done faster. This speedup is
 * especially visible for lists from 50,000 to 8,000,000 elements. Smaller and interestingly also larger lists are
 * better handled by a single-threaded sorter.
 *
 * @param <T> the type of objects to be sorted
 */
public class MultithreadedQuickSorter<T extends Comparable<T>> extends MedianQuickSorter<T> {

    private static final Logger log = LoggerFactory.getLogger(MultithreadedQuickSorter.class);

    /**
     * The thread pool with the threads that do the sorting work.
     */
    private final ExecutorService executorService = Executors.newWorkStealingPool();

    /**
     * Through testing this has turned out to be a reasonable number. A higher value would increase the number of
     * threads and the CPU usage but there is no measurable speed improvement.
     */
    private final int threadCreationThreshold = Runtime.getRuntime().availableProcessors() / 2;

    /**
     * Creates a new MultithreadedQuickSorter.
     */
    public MultithreadedQuickSorter() {
        // Intentionally left empty
    }

    @Override
    public List<T> sort(List<T> input) {
        return sort(input, 0);
    }

    /**
     * Internal sort method that keeps track of the nesting level ("deepness").
     *
     * @param input    the list to be sorted
     * @param deepness the nesting level that we are in
     * @return the sorted list
     */
    private List<T> sort(List<T> input, int deepness) {

        if (input.size() < 2) {
            // already sorted
            return input;
        }

        int pivotIndex = determinePivotIndex(input);
        T pivot = input.get(pivotIndex);

        // This implementation creates new lists in every step but this has turned out to be quicker than using the
        // add() and remove() methods in ArrayList. Let's make each sub list as big as the input list, just in case.
        // This way we avoid rebuilding the internal array.
        List<T> smaller = new ArrayList<>(input.size());
        List<T> bigger = new ArrayList<>(input.size());

        // This is a very old-fashioned way to iterate a list but it allows us to skip the pivot element.
        for (int i = 0; i < input.size(); ++i) {
            if (i == pivotIndex) {
                continue;
            }
            T element = input.get(i);
            if (element.compareTo(pivot) < 0) {
                smaller.add(element);
            } else {
                bigger.add(element);
            }
        }

        List<T> first;
        List<T> last;

        if (deepness < threadCreationThreshold) {

            Future<List<T>> firstFuture = executorService.submit(() -> sort(smaller, deepness + 1));
            Future<List<T>> lastFuture = executorService.submit(() -> sort(bigger, deepness + 1));

            try {
                first = firstFuture.get();
                last = lastFuture.get();
            } catch (InterruptedException | ExecutionException e) {
                log.error("caught unexpected exception while dealing with Future", e);
                return Collections.emptyList();
            }
        } else {
            first = sort(smaller, deepness + 1);
            last = sort(bigger, deepness + 1);
        }

        List<T> result = new ArrayList<>(input.size());
        result.addAll(first);
        result.add(pivot);
        result.addAll(last);
        return result;
    }
}
