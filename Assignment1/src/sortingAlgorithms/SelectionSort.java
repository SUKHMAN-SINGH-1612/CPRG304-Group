package sortingAlgorithms;

import java.util.Comparator;

/**
 * This class implements the selection sort algorithm for sorting an array of objects.
 */
public class SelectionSort {
    /**
     * Sorts the specified array of objects using the given comparator.
     *
     * @param array The array to sort
     * @param comp  The comparator to use for sorting
     */
    public static <T> void sort(T[] array, Comparator<? super T> comp) {
        for (int i = 0; i < array.length - 1; i++) {
            int minIdx = i;
            for (int j = i + 1; j < array.length; j++) {
                if (comp.compare(array[j], array[minIdx]) < 0) {
                    minIdx = j;
                }
            }

            // Swap two values around
            T temp = array[minIdx];
            array[minIdx] = array[i];
            array[i] = temp;
        }
    }
}
