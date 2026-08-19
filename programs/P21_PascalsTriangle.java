package programs;

/**
 * ============================================================
 * PROGRAM 21: Pascal's Triangle
 * ============================================================
 * Problem: WAP to print Pascal's Triangle for N rows.
 *   - Formula: C(n, k) = C(n, k-1) * (n - k + 1) / k
 *
 * Example (n = 5):
 *         1
 *       1   1
 *     1   2   1
 *   1   3   3   1
 * 1   4   6   4   1
 * ============================================================
 */
public class P21_PascalsTriangle {

    public static void printPascalsTriangle(int rows) {
        System.out.println("=== PASCAL'S TRIANGLE (rows=" + rows + ") ===");

        for (int i = 0; i < rows; i++) {
            // Leading spaces
            for (int s = 0; s < rows - i - 1; s++) System.out.print("  ");

            int val = 1;
            for (int j = 0; j <= i; j++) {
                System.out.printf("%4d", val);
                val = val * (i - j) / (j + 1);
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        printPascalsTriangle(6);
    }
}
