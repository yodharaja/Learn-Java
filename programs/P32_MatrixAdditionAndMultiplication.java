package programs;

/**
 * ============================================================
 * PROGRAM 32: Matrix Addition and Matrix Multiplication
 * ============================================================
 * Problem: WAP to perform:
 *   a) Matrix Addition: C[i][j] = A[i][j] + B[i][j]
 *   b) Matrix Multiplication: C[i][j] = Σ (A[i][k] * B[k][j])
 * ============================================================
 */
public class P32_MatrixAdditionAndMultiplication {

    public static int[][] addMatrices(int[][] a, int[][] b) {
        int rows = a.length, cols = a[0].length;
        int[][] res = new int[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) res[i][j] = a[i][j] + b[i][j];
        }
        return res;
    }

    public static int[][] multiplyMatrices(int[][] a, int[][] b) {
        int r1 = a.length, c1 = a[0].length;
        int r2 = b.length, c2 = b[0].length;

        if (c1 != r2) throw new IllegalArgumentException("Matrix dimensions invalid for multiplication!");

        int[][] res = new int[r1][c2];
        for (int i = 0; i < r1; i++) {
            for (int j = 0; j < c2; j++) {
                for (int k = 0; k < c1; k++) {
                    res[i][j] += a[i][k] * b[k][j];
                }
            }
        }
        return res;
    }

    public static void printMatrix(int[][] m) {
        for (int[] row : m) {
            System.out.print("  [ ");
            for (int val : row) System.out.printf("%4d ", val);
            System.out.println("]");
        }
    }

    public static void main(String[] args) {
        int[][] m1 = {
            {1, 2, 3},
            {4, 5, 6}
        };

        int[][] m2 = {
            {7, 8},
            {9, 1},
            {2, 3}
        };

        System.out.println("=== MATRIX MULTIPLICATION (2x3 * 3x2 -> 2x2) ===");
        int[][] mult = multiplyMatrices(m1, m2);
        printMatrix(mult);
    }
}
