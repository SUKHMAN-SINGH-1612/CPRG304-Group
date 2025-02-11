
package exercise2;

import java.util.Random;
import java.util.Scanner;

public class Driver {

    public static final int SIZE = 100;
    public static final int UPPER_BOUND = 10;

    public static void main(String[] args) {
        Integer[] nums = new Integer[SIZE];
        Random rand = new Random();
        int randnum = rand.nextInt(UPPER_BOUND);
        nums[0] = randnum;

        for (int i = 1; i < SIZE; i++) {
            randnum = rand.nextInt(UPPER_BOUND);
            nums[i] = nums[i - 1] + randnum;
            System.out.println(nums[i]);
        }

        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the target number: ");
        int target = scanner.nextInt();
        scanner.close();

        int index = binarySearch(nums, target);
        if (index != -1) {
            System.out.println("Target found at index: " + index);
        } else {
            System.out.println("Target not found.");
        }
    }

    public static int binarySearch(Integer[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;

            if (nums[mid] == target) {
                return mid;
            } else if (nums[mid] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return -1;
    }
}
