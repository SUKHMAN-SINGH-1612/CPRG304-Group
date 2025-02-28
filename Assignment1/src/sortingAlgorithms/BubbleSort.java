package sortingAlgorithms;

import java.util.Comparator;

/**
 * This class implements the bubble sort
 * algorithm for sorting an array of objects.
 */
public class BubbleSort {
    /**
     * Sorts the specified array of objects
     * using the given comparator.
     *
     * @param array The array to sort
     * @param comp  The comparator to use for sorting
     */
    public static <T> void sort(T[] array, Comparator<? super T> comp) {
        int n = array.length; // Use array.length instead of array.length - 1
        for (int i = 0; i < n - 1; i++) { // Ensure full iteration
            for (int j = 0; j < n - 1 - i; j++) { // Avoid unnecessary comparisons
                if (comp.compare(array[j], array[j + 1]) > 0) {
                    T temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                }
            }
        }
    }
}
