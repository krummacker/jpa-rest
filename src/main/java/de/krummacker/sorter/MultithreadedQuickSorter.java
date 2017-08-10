package de.krummacker.sorter;

import java.util.ArrayList;
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
public class MultithreadedQuickSorter<T extends Comparable> implements Sorter<T> {

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
    }

    /**
     * Determines the index of the best pivot for the specified list. It is the median of the first, middle and last
     * element of the list.
     *
     * @param input the list of which the best pivot index should be computed
     * @return the index of the pivot
     */
    private int determinePivotIndex(List<T> input) {

        int startIndex = 0;
        int middleIndex = input.size() / 2;
        int endIndex = input.size() - 1;

        T a = input.get(startIndex);
        T b = input.get(middleIndex);
        T c = input.get(endIndex);

        if (a.compareTo(b) > 0) {
            if (b.compareTo(c) > 0) {
                return middleIndex;
            } else {
                if (a.compareTo(c) > 0) {
                    return endIndex;
                } else {
                    return startIndex;
                }
            }
        } else {
            if (b.compareTo(c) < 0) {
                return middleIndex;
            } else {
                if (a.compareTo(c) < 0) {
                    return endIndex;
                } else {
                    return startIndex;
                }
            }
        }
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
            if (i == pivotIndex) continue;
            T element = input.get(i);
            if (element.compareTo(pivot) < 0) {
                smaller.add(element);
            } else {
                bigger.add(element);
            }
        }

        List<T> first = null;
        List<T> last = null;

        if (deepness < threadCreationThreshold) {

            Future<List<T>> firstFuture = executorService.submit(() -> sort(smaller, deepness + 1));
            Future<List<T>> lastFuture = executorService.submit(() -> sort(bigger, deepness + 1));

            try {
                first = firstFuture.get();
                last = lastFuture.get();
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
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