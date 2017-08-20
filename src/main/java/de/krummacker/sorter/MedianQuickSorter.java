package de.krummacker.sorter;

import java.util.ArrayList;
import java.util.List;

/**
 * Sorts Lists using the quick sort algorithm. It picks a pivot based on the median of the first, the last and the
 * middle element.
 *
 * @param <T> the type of objects to be sorted
 */
public class MedianQuickSorter<T extends Comparable<T>> implements Sorter<T> {

    /**
     * Creates a new MedianQuickSorter.
     */
    public MedianQuickSorter() {
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

        if (input.size() < 2) {
            // already sorted
            return input;
        }

        int pivotIndex = determinePivotIndex(input);
        T pivot = input.get(pivotIndex);

        // This implementation creates new lists in every step but this has turned out to be quicker than using the
        // add() and remove() methods in ArrayList.
        List<T> smaller = new ArrayList<>();
        List<T> bigger = new ArrayList<>();

        // This is a very old-fashioned way to iterate a list but this allows us to skip the pivot element.
        for (int i = 0; i < input.size(); ++i) {
            if (i == pivotIndex) continue;
            T element = input.get(i);
            if (element.compareTo(pivot) < 0) {
                smaller.add(element);
            } else {
                bigger.add(element);
            }
        }

        List<T> first = sort(smaller);
        List<T> last = sort(bigger);

        List<T> result = new ArrayList<>();
        result.addAll(first);
        result.add(pivot);
        result.addAll(last);
        return result;
    }
}
