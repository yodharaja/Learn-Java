package programs;

import java.util.Arrays;

/**
 * ============================================================
 * PROGRAM 29: Linear Search and Binary Search Implementations
 * ============================================================
 * Problem: WAP to implement:
 *   1. Linear Search: O(n) on unsorted arrays
 *   2. Binary Search: O(log n) on sorted arrays
 * ============================================================
 */
public class P29_LinearAndBinarySearch {

    public static int linearSearch(int[] arr, int target) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == target) return i; // found
        }
        return -1;
    }

    public static int binarySearch(int[] sortedArr, int target) {
        int low = 0;
        int high = sortedArr.length - 1;

        while (low <= high) {
            int mid = low + (high - low) / 2; // avoids integer overflow

            if (sortedArr[mid] == target) {
                return mid; // found
            } else if (sortedArr[mid] < target) {
                low = mid + 1; // search right
            } else {
                high = mid - 1; // search left
            }
        }
        return -1; // not found
    }

    public static void main(String[] args) {
        int[] data = {45, 12, 85, 32, 89, 39, 69, 44};
        System.out.println("=== 1. LINEAR SEARCH (Unsorted Array) ===");
        System.out.println("Array: " + Arrays.toString(data));
        System.out.println("Search for 89 -> index: " + linearSearch(data, 89));
        System.out.println("Search for 99 -> index: " + linearSearch(data, 99));

        System.out.println("\n=== 2. BINARY SEARCH (Sorted Array) ===");
        Arrays.sort(data);
        System.out.println("Sorted Array: " + Arrays.toString(data));
        System.out.println("Binary Search for 45 -> index: " + binarySearch(data, 45));
        System.out.println("Binary Search for 100 -> index: " + binarySearch(data, 100));
    }
}
