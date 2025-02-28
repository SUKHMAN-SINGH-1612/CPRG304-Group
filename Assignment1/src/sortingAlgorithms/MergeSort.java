package sortingAlgorithms;

import java.util.Arrays;
import java.util.Comparator;

/**
 * This class implements the merge sort algorithm for sorting an array of objects.
 */
public class MergeSort {
    /**
     * Sorts the specified array of objects using the given comparator.
     *
     * @param array The array to sort
     * @param comp  The comparator to use for sorting
     */
    public static <T> void sort(T[] array, Comparator<? super T> comp) {
        if (array.length > 1) {
            int mid = array.length / 2; // Use int instead of var
            T[] left = Arrays.copyOfRange(array, 0, mid);
            T[] right = Arrays.copyOfRange(array, mid, array.length);

            sort(left, comp);
            sort(right, comp);

            merge(array, left, right, comp);
        }
    }

    /**
     * Merges two sorted subarrays into a single sorted array.
     *
     * @param result The array to store the merged result
     * @param left   The left subarray
     * @param right  The right subarray
     * @param comp   The comparator to use for sorting
     */
    private static <T> void merge(T[] result, T[] left, T[] right, Comparator<? super T> comp) {
        int i = 0, j = 0, k = 0;
        while (i < left.length && j < right.length) {
            if (comp.compare(left[i], right[j]) <= 0) {
                result[k++] = left[i++];
            } else {
                result[k++] = right[j++];
            }
        }

        while (i < left.length) {
            result[k++] = left[i++];
        }

        while (j < right.length) {
            result[k++] = right[j++];
        }
    }
}
