package programs;

import java.util.Arrays;

/**
 * ============================================================
 * PROGRAM 30: Bubble Sort and Selection Sort Algorithms
 * ============================================================
 * Problem: WAP to implement from scratch:
 *   a) Bubble Sort (with early-exit optimization flag)
 *   b) Selection Sort
 * ============================================================
 */
public class P30_BubbleSortAndSelectionSort {

    public static void bubbleSort(int[] arr) {
        int n = arr.length;
        for (int i = 0; i < n - 1; i++) {
            boolean swapped = false;
            for (int j = 0; j < n - i - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                    swapped = true;
                }
            }
            if (!swapped) break; // Optimization: already sorted!
        }
    }

    public static void selectionSort(int[] arr) {
        int n = arr.length;
        for (int i = 0; i < n - 1; i++) {
            int minIdx = i;
            for (int j = i + 1; j < n; j++) {
                if (arr[j] < arr[minIdx]) {
                    minIdx = j;
                }
            }
            // Swap min with current element
            int temp = arr[minIdx];
            arr[minIdx] = arr[i];
            arr[i] = temp;
        }
    }

    public static void main(String[] args) {
        int[] arr1 = {64, 34, 25, 12, 22, 11, 90};
        System.out.println("=== 1. BUBBLE SORT ===");
        System.out.println("Before: " + Arrays.toString(arr1));
        bubbleSort(arr1);
        System.out.println("After : " + Arrays.toString(arr1));

        int[] arr2 = {29, 10, 14, 37, 13};
        System.out.println("\n=== 2. SELECTION SORT ===");
        System.out.println("Before: " + Arrays.toString(arr2));
        selectionSort(arr2);
        System.out.println("After : " + Arrays.toString(arr2));
    }
}
