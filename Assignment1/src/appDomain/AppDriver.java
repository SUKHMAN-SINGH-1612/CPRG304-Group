
package appDomain;

import java.util.Scanner;
import shapes.*;
import shapeManager.*;
import sortingAlgorithms.SortingUtility; // Assume your sorting algorithms are here
import java.util.Comparator;

public class AppDriver {
    public static void main(String[] args) {
        String fileName = null;
        String sortType = "q"; // Default to Bubble Sort
        String compareType = "h"; // Default to height
        Scanner scanner = new Scanner(System.in);

        // Parse command line arguments
        for (int i = 0; i < args.length; i++) {
            switch (args[i].toLowerCase()) {
                case "-f":
                    if (i + 1 < args.length) {
                        fileName = args[++i];
                    } else {
                        System.out.println("The -f option requires a filename. Please enter the file path:");
                        fileName = scanner.nextLine().trim();
                    }
                    break;
                case "-s":
                    if (i + 1 < args.length) {
                        sortType = args[++i].toLowerCase();
                    }
                    break;
                case "-t":
                    if (i + 1 < args.length) {
                        compareType = args[++i].toLowerCase();
                    }
                    break;
            }
        }

        // Get filename if not provided
        if (fileName == null) {
            System.out.println("Please enter the file path (use -f <filename> to skip this prompt next time):");
            fileName = scanner.nextLine().trim();
        }

        try {
            // Load shapes
            System.out.println("Loading shapes from file: " + fileName);
            Shape[] shapes = ShapeFileReader.readShapesFromFile(fileName);
            System.out.println("Successfully loaded " + shapes.length + " shapes.");

            // Create comparator
            System.out.println("Creating comparator for type: " + compareType);
            Comparator<Shape> comparator = new ShapeComparator(compareType);

            // Sort and benchmark
            System.out.println("Starting sort using algorithm: " + sortType);
            long startTime = System.nanoTime();
            switch (sortType) {
                case "b":
                    SortingUtility.bubbleSort(shapes, comparator);
                    break;
                case "i":
                    SortingUtility.insertionSort(shapes, comparator);
                    break;
                case "s":
                    SortingUtility.selectionSort(shapes, comparator);
                    break;
                case "m":
                    SortingUtility.mergeSort(shapes, comparator);
                    break;
                case "q":
                    SortingUtility.quickSort(shapes, comparator);
                    break;
                case "z": // Your custom sort
                    SortingUtility.countingSort(shapes, comparator);
                    break;
                default:
                    throw new IllegalArgumentException("Invalid sorting algorithm: " + sortType);
            }
            long endTime = System.nanoTime();
            double duration = (endTime - startTime) / 1_000_000.0;

            // Print results
            System.out.println("Sorting completed. Printing results...");
            printSortedResults(shapes);
            System.out.printf("%s Sort run time was: %.3f milliseconds%n",
                getAlgorithmName(sortType), duration);

        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
            e.printStackTrace();
            System.exit(1);
        } finally {
            scanner.close();
        }
    }

    private static void printSortedResults(Shape[] shapes) {
        System.out.println("First element: " + shapes[0]);
        for (int i = 999; i < shapes.length; i += 1000) {
            System.out.println((i+1) + "-th element: " + shapes[i]);
        }
        System.out.println("Last element: " + shapes[shapes.length - 1]);
    }

    private static String getAlgorithmName(String sortType) {
        switch (sortType) {
            case "b": return "Bubble";
            case "i": return "Insertion";
            case "s": return "Selection";
            case "m": return "Merge";
            case "q": return "Quick";
            case "z": return "Counting"; // Replace with your algorithm name
            default: return "Unknown";
        }
    }
}
