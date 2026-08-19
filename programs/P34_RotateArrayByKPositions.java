package programs;

import java.util.Arrays;

/**
 * ============================================================
 * PROGRAM 34: Rotate an Array by K Positions
 * ============================================================
 * Problem: WAP to rotate an array to the right by K steps in O(n) time & O(1) space.
 *   - Technique: 3-step reversal algorithm
 *   - Input : [1, 2, 3, 4, 5, 6, 7], k = 3
 *   - Output: [5, 6, 7, 1, 2, 3, 4]
 * ============================================================
 */
public class P34_RotateArrayByKPositions {

    public static void rotate(int[] nums, int k) {
        if (nums == null || nums.length == 0) return;
        k = k % nums.length; // normalize k

        // Step 1: Reverse entire array
        reverse(nums, 0, nums.length - 1);
        // Step 2: Reverse first k elements
        reverse(nums, 0, k - 1);
        // Step 3: Reverse remaining elements
        reverse(nums, k, nums.length - 1);
    }

    private static void reverse(int[] nums, int start, int end) {
        while (start < end) {
            int temp = nums[start];
            nums[start++] = nums[end];
            nums[end--] = temp;
        }
    }

    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 5, 6, 7};
        System.out.println("Before Rotation (k=3): " + Arrays.toString(arr));
        rotate(arr, 3);
        System.out.println("After Rotation       : " + Arrays.toString(arr));
    }
}
