package programs;

import java.util.Arrays;

/**
 * ============================================================
 * PROGRAM 31: Merge Two Sorted Arrays into One
 * ============================================================
 * Problem: WAP to merge two sorted arrays of sizes M and N
 * into a third sorted array of size M+N in O(M+N) time.
 * ============================================================
 */
public class P31_MergeTwoSortedArrays {

    public static int[] mergeSorted(int[] arr1, int[] arr2) {
        int n1 = arr1.length;
        int n2 = arr2.length;
        int[] merged = new int[n1 + n2];

        int i = 0, j = 0, k = 0;

        while (i < n1 && j < n2) {
            if (arr1[i] <= arr2[j]) {
                merged[k++] = arr1[i++];
            } else {
                merged[k++] = arr2[j++];
            }
        }

        // Copy remaining elements of arr1 (if any)
        while (i < n1) merged[k++] = arr1[i++];

        // Copy remaining elements of arr2 (if any)
        while (j < n2) merged[k++] = arr2[j++];

        return merged;
    }

    public static void main(String[] args) {
        int[] a = {1, 3, 5, 7, 9};
        int[] b = {2, 4, 6, 8, 10, 12, 14};

        System.out.println("Array 1 : " + Arrays.toString(a));
        System.out.println("Array 2 : " + Arrays.toString(b));

        int[] result = mergeSorted(a, b);
        System.out.println("Merged  : " + Arrays.toString(result));
    }
}
