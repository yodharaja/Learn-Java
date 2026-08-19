package programs;

import java.util.Arrays;

/**
 * ============================================================
 * PROGRAM 27: Reverse an Array In-Place
 * ============================================================
 * Problem: WAP to reverse an array in-place without allocating
 * a second array (Two-Pointer Technique in O(n/2) steps).
 * ============================================================
 */
public class P27_ReverseArrayInPlace {

    public static void reverse(int[] arr) {
        if (arr == null || arr.length <= 1) return;

        int left = 0;
        int right = arr.length - 1;

        while (left < right) {
            // Swap left and right elements
            int temp = arr[left];
            arr[left] = arr[right];
            arr[right] = temp;

            left++;
            right--;
        }
    }

    public static void main(String[] args) {
        int[] oddArray = {1, 2, 3, 4, 5};
        int[] evenArray = {10, 20, 30, 40, 50, 60};

        System.out.println("Odd Array Before  : " + Arrays.toString(oddArray));
        reverse(oddArray);
        System.out.println("Odd Array After   : " + Arrays.toString(oddArray));

        System.out.println("\nEven Array Before : " + Arrays.toString(evenArray));
        reverse(evenArray);
        System.out.println("Even Array After  : " + Arrays.toString(evenArray));
    }
}
