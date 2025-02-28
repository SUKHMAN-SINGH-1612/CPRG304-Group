package sortingAlgorithms;

import java.util.Comparator;

/**
 * This class implements the insertion sort
 * algorithm for sorting an array of elements.
 */
public class InsertionSort {
    /**
     * Sorts the specified array of objects using the given comparator.
     *
     * @param array The array to sort
     * @param comp  The comparator to use for sorting
     */
    public static <T> void sort(T[] array, Comparator<? super T> comp) {
        for (int i = 1; i < array.length; i++) {
            T key = array[i]; // Use explicit type T instead of var
            int j = i - 1;
            
            while (j >= 0 && comp.compare(array[j], key) > 0) {
                array[j + 1] = array[j];
                j--;
            }
            array[j + 1] = key;
        }
    }
}
