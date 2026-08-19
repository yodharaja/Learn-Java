package chapter7;

import java.util.Arrays;

/**
 * ============================================================
 * LESSON 7.1 — Multi-Dimensional Arrays & Arrays Utility
 * ============================================================
 *
 * In Java, multi-dimensional arrays are "arrays of arrays".
 *
 * Topics Covered:
 *   1. 2D Rectangular Matrices (Rows x Columns).
 *   2. Jagged (Ragged) Arrays: Arrays where sub-arrays have different lengths.
 *   3. Matrix operations: Addition, Transposition.
 *   4. `java.util.Arrays` utility class:
 *      - `Arrays.toString()` & `Arrays.deepToString()`
 *      - `Arrays.sort()` & `Arrays.parallelSort()`
 *      - `Arrays.binarySearch()`
 *      - `Arrays.copyOf()` & `Arrays.copyOfRange()`
 *      - `Arrays.fill()` & `Arrays.equals()`
 */
public class ArrayOps {

    public static void main(String[] args) {
        System.out.println("=== 1. 2D MATRICES (3x3 MATRIX) ===");
        int[][] matrixA = {
            {1, 2, 3},
            {4, 5, 6},
            {7, 8, 9}
        };

        int[][] matrixB = {
            {9, 8, 7},
            {6, 5, 4},
            {3, 2, 1}
        };

        System.out.println("Matrix A:");
        printMatrix(matrixA);

        System.out.println("\nMatrix B:");
        printMatrix(matrixB);

        // Matrix Addition: C[i][j] = A[i][j] + B[i][j]
        int[][] sumMatrix = new int[3][3];
        for (int r = 0; r < 3; r++) {
            for (int c = 0; c < 3; c++) {
                sumMatrix[r][c] = matrixA[r][c] + matrixB[r][c];
            }
        }
        System.out.println("\nMatrix A + B:");
        printMatrix(sumMatrix);


        System.out.println("\n=== 2. JAGGED (RAGGED) ARRAYS ===");
        // Rows with varying column lengths
        int[][] jagged = new int[4][];
        jagged[0] = new int[]{1};
        jagged[1] = new int[]{2, 3};
        jagged[2] = new int[]{4, 5, 6};
        jagged[3] = new int[]{7, 8, 9, 10};

        System.out.println("Jagged Array Structure:");
        for (int r = 0; r < jagged.length; r++) {
            System.out.printf("  Row %d (len %d): %s%n",
                    r, jagged[r].length, Arrays.toString(jagged[r]));
        }


        System.out.println("\n=== 3. JAVA.UTIL.ARRAYS UTILITY METHODS ===");

        // 1. Sorting primitives
        int[] numbers = {45, 12, 85, 32, 89, 39, 69, 44, 42, 1, 99};
        System.out.println("Original Array: " + Arrays.toString(numbers));

        Arrays.sort(numbers);
        System.out.println("Sorted Array  : " + Arrays.toString(numbers));

        // 2. Binary Search (MUST be sorted first!)
        int target = 44;
        int foundIndex = Arrays.binarySearch(numbers, target);
        System.out.printf("Binary search for %d -> found at index %d%n", target, foundIndex);

        int missing = 50;
        int missingIndex = Arrays.binarySearch(numbers, missing);
        System.out.printf("Binary search for %d (missing) -> insertion point formula (-insertionPoint - 1) = %d%n",
                missing, missingIndex);

        // 3. Array Copying
        int[] fullCopy = Arrays.copyOf(numbers, numbers.length);
        int[] subSlice = Arrays.copyOfRange(numbers, 2, 6); // index 2 to 5 inclusive
        System.out.println("Full Copy : " + Arrays.toString(fullCopy));
        System.out.println("Sub-slice [2..5]: " + Arrays.toString(subSlice));

        // 4. Equality check
        System.out.println("numbers.equals(fullCopy)         : " + numbers.equals(fullCopy) + " (checks reference)");
        System.out.println("Arrays.equals(numbers, fullCopy) : " + Arrays.equals(numbers, fullCopy) + " (checks elements)");

        // 5. Fill
        int[] flags = new int[5];
        Arrays.fill(flags, 7);
        System.out.println("Filled Array with 7s: " + Arrays.toString(flags));

        // 6. Deep Printing for Multi-dimensional Arrays
        System.out.println("Arrays.deepToString(matrixA): " + Arrays.deepToString(matrixA));
    }

    public static void printMatrix(int[][] m) {
        for (int[] row : m) {
            System.out.print("  [ ");
            for (int val : row) {
                System.out.printf("%3d ", val);
            }
            System.out.println("]");
        }
    }
}
