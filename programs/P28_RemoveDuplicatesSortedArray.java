package programs;

import java.util.Arrays;

/**
 * ============================================================
 * PROGRAM 28: Remove Duplicates from Sorted Array In-Place
 * ============================================================
 * Problem: WAP to remove duplicates from a sorted array in-place
 * such that each element appears only once and returns the new length.
 *   - Input : [1, 1, 2, 2, 3, 4, 4, 5]
 *   - Output: Length = 5, Array prefix = [1, 2, 3, 4, 5]
 *   - Time: O(n), Space: O(1)
 * ============================================================
 */
public class P28_RemoveDuplicatesSortedArray {

    public static int removeDuplicates(int[] nums) {
        if (nums == null || nums.length == 0) return 0;

        int writeIndex = 1;

        for (int i = 1; i < nums.length; i++) {
            if (nums[i] != nums[i - 1]) {
                nums[writeIndex] = nums[i];
                writeIndex++;
            }
        }

        return writeIndex;
    }

    public static void main(String[] args) {
        int[] sorted = {1, 1, 2, 2, 3, 4, 4, 4, 5, 6, 6};
        System.out.println("Original Sorted Array : " + Arrays.toString(sorted));

        int newLen = removeDuplicates(sorted);

        System.out.println("Unique Element Count  : " + newLen);
        System.out.print("Deduped Array Prefix  : [ ");
        for (int i = 0; i < newLen; i++) {
            System.out.print(sorted[i] + " ");
        }
        System.out.println("]");
    }
}
