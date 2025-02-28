package sortingAlgorithms;

import shapeManager.Shape; // Corrected import

import java.util.Arrays;

/**
 * This class implements the counting sort
 * algorithm for sorting an array of Shapes based on height.
 */
public class CountingSort {
    /**
     * Sorts the elements of an array by counting the number
     * of each unique element occurrence in the array.
     *
     * @param array The array to sort
     */
    public static void sort(Shape[] array) {
        if (array.length == 0)
            return;

        // Find the minimum and maximum height
        double minHeight = array[0].getHeight();
        double maxHeight = array[0].getHeight();

        for (Shape shape : array) {
            if (shape.getHeight() < minHeight) minHeight = shape.getHeight();
            if (shape.getHeight() > maxHeight) maxHeight = shape.getHeight();
        }

        // Define a scaling factor to avoid rounding issues
        final int SCALE = 1000; // Scale factor for floating-point precision
        int min = (int) Math.floor(minHeight * SCALE);
        int max = (int) Math.ceil(maxHeight * SCALE);

        // Count array initialization
        int[] count = new int[max - min + 1];
        Shape[] output = new Shape[array.length];

        // Count occurrences
        for (Shape shape : array) {
            count[(int) (shape.getHeight() * SCALE) - min]++;
        }

        // Modify count array to store cumulative sums
        for (int i = 1; i < count.length; i++) {
            count[i] += count[i - 1];
        }

        // Build output array (sorting in stable order)
        for (int i = array.length - 1; i >= 0; i--) {
            Shape shape = array[i];
            int index = (int) (shape.getHeight() * SCALE) - min;
            output[count[index] - 1] = shape;
            count[index]--;
        }

        // Copy sorted results back into original array
        System.arraycopy(output, 0, array, 0, array.length);
    }
}
