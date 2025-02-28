
package sortingAlgorithms;

import java.util.Comparator;
import shapeManager.Shape;

public class SortingUtility {

    public static void bubbleSort(Shape[] array, Comparator<Shape> comp) {
        BubbleSort.sort(array, comp);
    }

    public static void insertionSort(Shape[] array, Comparator<Shape> comp) {
        InsertionSort.sort(array, comp);
    }

    public static void selectionSort(Shape[] array, Comparator<Shape> comp) {
        SelectionSort.sort(array, comp);
    }

    public static void mergeSort(Shape[] array, Comparator<Shape> comp) {
        MergeSort.sort(array, comp);
    }

    public static void quickSort(Shape[] array, Comparator<Shape> comp) {
        QuickSort.sort(array, comp);
    }

    public static void countingSort(Shape[] array, Comparator<Shape> comp) {
        CountingSort.sort(array);
    }
}
