package chapter3;

/**
 * ============================================================
 * LESSON 3.5 — Pattern Printing with Nested Loops
 * ============================================================
 *
 * Pattern problems are classic loop exercises that build your
 * understanding of nested loops. They're also common in interviews!
 *
 * The key insight:
 *   - OUTER loop controls ROWS (how many lines)
 *   - INNER loop controls COLUMNS (what to print on each line)
 *
 * Approach for any pattern:
 *   1. Count the rows
 *   2. For each row, figure out: how many spaces? how many stars?
 *   3. Write the inner loops accordingly
 */
public class Patterns {

    public static void main(String[] args) {

        int n = 5;  // number of rows (change this to experiment!)

        // ============================================================
        // PATTERN 1: Right Triangle (left-aligned)
        // ============================================================
        /*
         * *
         * * *
         * * * *
         * * * * *
         * * * * * *
         */
        System.out.println("=== Pattern 1: Right Triangle ===");
        for (int row = 1; row <= n; row++) {       // 5 rows
            for (int col = 1; col <= row; col++) {  // row 1 → 1 star, row 2 → 2 stars, etc.
                System.out.print("* ");
            }
            System.out.println();
        }


        // ============================================================
        // PATTERN 2: Inverted Right Triangle
        // ============================================================
        /*
         * * * * * *
         * * * * *
         * * * *
         * * *
         * *
         */
        System.out.println("\n=== Pattern 2: Inverted Triangle ===");
        for (int row = n; row >= 1; row--) {        // start from n, go down
            for (int col = 1; col <= row; col++) {
                System.out.print("* ");
            }
            System.out.println();
        }


        // ============================================================
        // PATTERN 3: Number Triangle
        // ============================================================
        /*
         * 1
         * 1 2
         * 1 2 3
         * 1 2 3 4
         * 1 2 3 4 5
         */
        System.out.println("\n=== Pattern 3: Number Triangle ===");
        for (int row = 1; row <= n; row++) {
            for (int col = 1; col <= row; col++) {
                System.out.print(col + " ");
            }
            System.out.println();
        }


        // ============================================================
        // PATTERN 4: Pyramid (centered)
        // ============================================================
        /*
         *         *
         *       * * *
         *     * * * * *
         *   * * * * * * *
         * * * * * * * * * *
         */
        System.out.println("\n=== Pattern 4: Pyramid ===");
        for (int row = 1; row <= n; row++) {
            // Print leading spaces
            for (int space = 1; space <= n - row; space++) {
                System.out.print("  ");
            }
            // Print stars
            for (int star = 1; star <= 2 * row - 1; star++) {
                System.out.print("* ");
            }
            System.out.println();
        }
        // Logic: Row 1 → (n-1) spaces, 1 star
        //        Row 2 → (n-2) spaces, 3 stars
        //        Row k → (n-k) spaces, (2k-1) stars


        // ============================================================
        // PATTERN 5: Inverted Pyramid
        // ============================================================
        /*
         * * * * * * * * * *
         *   * * * * * * *
         *     * * * * *
         *       * * *
         *         *
         */
        System.out.println("\n=== Pattern 5: Inverted Pyramid ===");
        for (int row = n; row >= 1; row--) {
            for (int space = 1; space <= n - row; space++) {
                System.out.print("  ");
            }
            for (int star = 1; star <= 2 * row - 1; star++) {
                System.out.print("* ");
            }
            System.out.println();
        }


        // ============================================================
        // PATTERN 6: Diamond
        // ============================================================
        /*
         *         *
         *       * * *
         *     * * * * *
         *   * * * * * * *
         * * * * * * * * * *
         *   * * * * * * *
         *     * * * * *
         *       * * *
         *         *
         */
        System.out.println("\n=== Pattern 6: Diamond ===");
        // Upper half (pyramid)
        for (int row = 1; row <= n; row++) {
            for (int space = 1; space <= n - row; space++) {
                System.out.print("  ");
            }
            for (int star = 1; star <= 2 * row - 1; star++) {
                System.out.print("* ");
            }
            System.out.println();
        }
        // Lower half (inverted pyramid, starting from n-1)
        for (int row = n - 1; row >= 1; row--) {
            for (int space = 1; space <= n - row; space++) {
                System.out.print("  ");
            }
            for (int star = 1; star <= 2 * row - 1; star++) {
                System.out.print("* ");
            }
            System.out.println();
        }


        // ============================================================
        // PATTERN 7: Hollow Rectangle
        // ============================================================
        /*
         * * * * * *
         * *       *
         * *       *
         * *       *
         * * * * * *
         */
        System.out.println("\n=== Pattern 7: Hollow Rectangle ===");
        int rows = 5, cols = 6;
        for (int row = 1; row <= rows; row++) {
            for (int col = 1; col <= cols; col++) {
                // Print star if: first/last row OR first/last column
                if (row == 1 || row == rows || col == 1 || col == cols) {
                    System.out.print("* ");
                } else {
                    System.out.print("  ");
                }
            }
            System.out.println();
        }


        // ============================================================
        // PATTERN 8: Floyd's Triangle (sequential numbers)
        // ============================================================
        /*
         * 1
         * 2 3
         * 4 5 6
         * 7 8 9 10
         * 11 12 13 14 15
         */
        System.out.println("\n=== Pattern 8: Floyd's Triangle ===");
        int num = 1;
        for (int row = 1; row <= n; row++) {
            for (int col = 1; col <= row; col++) {
                System.out.printf("%-4d", num);  // left-aligned, 4 chars wide
                num++;
            }
            System.out.println();
        }


        // ============================================================
        // PATTERN 9: Butterfly
        // ============================================================
        /*
         * *               *
         * * *           * *
         * * * *       * * *
         * * * * *   * * * *
         * * * * * * * * * *
         * * * * *   * * * *
         * * * * *     * * *
         * * * *         * *
         * *               *
         */
        System.out.println("\n=== Pattern 9: Butterfly ===");
        // Upper half
        for (int row = 1; row <= n; row++) {
            // Left stars
            for (int col = 1; col <= row; col++) {
                System.out.print("* ");
            }
            // Middle spaces
            for (int space = 1; space <= 2 * (n - row); space++) {
                System.out.print("  ");
            }
            // Right stars
            for (int col = 1; col <= row; col++) {
                System.out.print("* ");
            }
            System.out.println();
        }
        // Lower half
        for (int row = n - 1; row >= 1; row--) {
            for (int col = 1; col <= row; col++) {
                System.out.print("* ");
            }
            for (int space = 1; space <= 2 * (n - row); space++) {
                System.out.print("  ");
            }
            for (int col = 1; col <= row; col++) {
                System.out.print("* ");
            }
            System.out.println();
        }


        // ============================================================
        // KEY TAKEAWAYS:
        // ============================================================
        // - Outer loop = rows, inner loop = columns
        // - For centered patterns: leading spaces = (n - row)
        // - Pyramid stars per row: (2 * row - 1)
        // - Diamond = pyramid + inverted pyramid
        // - Hollow patterns: print star only at edges
        // - Approach: sketch the pattern, count spaces & stars per row
        // ============================================================
    }
}
