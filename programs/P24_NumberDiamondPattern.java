package programs;

/**
 * ============================================================
 * PROGRAM 24: Palindromic Number Pyramid Pattern
 * ============================================================
 * Problem: WAP to print a palindromic number pyramid pattern.
 *
 * Example (n = 5):
 *         1
 *       2 1 2
 *     3 2 1 2 3
 *   4 3 2 1 2 3 4
 * 5 4 3 2 1 2 3 4 5
 * ============================================================
 */
public class P24_NumberDiamondPattern {

    public static void printPalindromicPyramid(int n) {
        System.out.println("=== PALINDROMIC NUMBER PYRAMID (n=" + n + ") ===");

        for (int r = 1; r <= n; r++) {
            // Spaces
            for (int s = 1; s <= (n - r); s++) System.out.print("  ");

            // Descending numbers: r down to 1
            for (int d = r; d >= 1; d--) System.out.print(d + " ");

            // Ascending numbers: 2 up to r
            for (int a = 2; a <= r; a++) System.out.print(a + " ");

            System.out.println();
        }
    }

    public static void main(String[] args) {
        printPalindromicPyramid(5);
    }
}
