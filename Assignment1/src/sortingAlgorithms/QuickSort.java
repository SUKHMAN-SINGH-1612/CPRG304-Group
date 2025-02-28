package sortingAlgorithms;

import java.util.Comparator;

/**
 * This class implements the quicksort algorithm for sorting an array of objects.
 */
public class QuickSort {
    /**
     * Sorts the specified array of objects using the given comparator.
     *
     * @param array The array to sort
     * @param comp  The comparator to use for sorting
     */
    public static <T> void sort(T[] array, Comparator<? super T> comp) {
        quickSort(array, 0, array.length - 1, comp);
    }

    /**
     * Partitions the array around a pivot element.
     *
     * @param array The array to swap elements in
     * @param low   The lower index of the subarray to sort
     * @param high  The higher index of the subarray to sort
     * @param comp  The comparator to use for sorting
     *
     * @return The index of the pivot element
     */
    private static <T> int part(T[] array, int low, int high, Comparator<? super T> comp) {
        T pivot = array[high]; // Explicitly declare as T
        int i = low - 1;
        for (int j = low; j < high; j++) {
            if (comp.compare(array[j], pivot) <= 0) {
                i++;
                swap(array, i, j);
            }
        }

        swap(array, i + 1, high);
        return i + 1;
    }

    /**
     * Swaps two elements in the array.
     *
     * @param array The array to swap elements in
     * @param i     The index of the first element
     * @param j     The index of the second element
     */
    private static <T> void swap(T[] array, int i, int j) {
        T temp = array[i]; // Explicitly declare as T
        array[i] = array[j];
        array[j] = temp;
    }

    /**
     * Recursively sorts the array using the quicksort algorithm.
     *
     * @param array The array to swap elements in
     * @param low   The lower index of the subarray to sort
     * @param high  The higher index of the subarray to sort
     * @param comp  The comparator to use for sorting
     */
    private static <T> void quickSort(T[] array, int low, int high, Comparator<? super T> comp) {
        if (low < high) {
            int pivotIndex = part(array, low, high, comp); // Explicitly declare as int
            quickSort(array, low, pivotIndex - 1, comp);
            quickSort(array, pivotIndex + 1, high, comp);
        }
    }
}
