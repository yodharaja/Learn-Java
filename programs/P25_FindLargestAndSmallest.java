package programs;

import java.util.Arrays;

/**
 * ============================================================
 * PROGRAM 25: Find Largest and Smallest Element in Array
 * ============================================================
 * Problem: WAP to find the maximum and minimum elements in an array in O(n).
 * ============================================================
 */
public class P25_FindLargestAndSmallest {

    public static void findMinMax(int[] arr) {
        if (arr == null || arr.length == 0) {
            System.out.println("Array is empty.");
            return;
        }

        int min = arr[0];
        int max = arr[0];

        for (int i = 1; i < arr.length; i++) {
            if (arr[i] < min) min = arr[i];
            if (arr[i] > max) max = arr[i];
        }

        System.out.println("Array : " + Arrays.toString(arr));
        System.out.printf("  Min : %d | Max : %d | Difference (Span): %d%n", min, max, (max - min));
    }

    public static void main(String[] args) {
        findMinMax(new int[]{45, 12, 89, 32, 99, 1, 67, 34});
        findMinMax(new int[]{-10, -5, -80, -2, -15});
    }
}
