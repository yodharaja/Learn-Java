package programs;

import java.util.Arrays;

/**
 * ============================================================
 * PROGRAM 26: Find Second Largest Element in Array
 * ============================================================
 * Problem: WAP to find the Second Largest element in an array
 * in a SINGLE pass (O(n) time and O(1) space) without sorting.
 * ============================================================
 */
public class P26_SecondLargestElement {

    public static int findSecondLargest(int[] arr) {
        if (arr == null || arr.length < 2) return Integer.MIN_VALUE;

        int largest = Integer.MIN_VALUE;
        int secondLargest = Integer.MIN_VALUE;

        for (int num : arr) {
            if (num > largest) {
                secondLargest = largest;
                largest = num;
            } else if (num > secondLargest && num != largest) {
                secondLargest = num;
            }
        }

        return secondLargest;
    }

    public static void main(String[] args) {
        int[][] testArrays = {
            {12, 35, 1, 10, 34, 1},
            {10, 10, 10},
            {5, 20, 15, 30, 25},
            {-10, -40, -20, -5}
        };

        System.out.println("=== SECOND LARGEST ELEMENT TESTS ===");
        for (int[] arr : testArrays) {
            int second = findSecondLargest(arr);
            System.out.printf("  Array: %-25s -> Second Largest: %s%n",
                    Arrays.toString(arr), (second == Integer.MIN_VALUE ? "None (all duplicates)" : second));
        }
    }
}
