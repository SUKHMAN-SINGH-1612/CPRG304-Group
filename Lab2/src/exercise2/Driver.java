
package exercise2;

import java.util.Random;
import java.util.Scanner;

public class Driver {

    // Constants for the size of the array and the upper bound for random numbers
    public static final int SIZE = 100;
    public static final int UPPER_BOUND = 10;

    public static void main(String[] args) {
        // Create an array to hold the integers
        Integer[] nums = new Integer[SIZE];
        Random rand = new Random();

        // Generate the first random number
        int randnum = rand.nextInt(UPPER_BOUND);
        nums[0] = randnum;

        // Fill the array with sorted random numbers
        for (int i = 1; i < SIZE; i++) {
            randnum = rand.nextInt(UPPER_BOUND);
            nums[i] = nums[i - 1] + randnum;
            System.out.println(nums[i]);
        }

        // Prompt the user for the target number
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the target number: ");
        int target = scanner.nextInt();
        scanner.close();

        // Perform binary search to find the target number
        int index = binarySearch(nums, target);
        if (index != -1) {
            System.out.println("Target found at index: " + index);
        } else {
            System.out.println("Target not found.");
        }
    }

    // Method to perform binary search on a sorted array
    public static int binarySearch(Integer[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;

        // Continue searching while the left index is less than or equal to the right index
        while (left <= right) {
            // Calculate the middle index
            int mid = left + (right - left) / 2;

            // Check if the target is at the middle index
            if (nums[mid] == target) {
                return mid;
            } 
            // If the target is greater, ignore the left half
            else if (nums[mid] < target) {
                left = mid + 1;
            } 
            // If the target is smaller, ignore the right half
            else {
                right = mid - 1;
            }
        }

        // Return -1 if the target is not found
        return -1;
    }
}
