package programs;

/**
 * ============================================================
 * PROGRAM 22: Hollow Square and Rectangle Patterns
 * ============================================================
 * Problem: WAP to print a Hollow Square and Hollow Rectangle pattern.
 *
 * Example (5 x 6):
 * * * * * *
 * *       *
 * *       *
 * *       *
 * * * * * *
 * ============================================================
 */
public class P22_HollowSquarePattern {

    public static void printHollowRectangle(int rows, int cols) {
        System.out.printf("=== HOLLOW RECTANGLE (%d x %d) ===%n", rows, cols);

        for (int r = 1; r <= rows; r++) {
            for (int c = 1; c <= cols; c++) {
                if (r == 1 || r == rows || c == 1 || c == cols) {
                    System.out.print("* ");
                } else {
                    System.out.print("  ");
                }
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        printHollowRectangle(5, 5);
        System.out.println();
        printHollowRectangle(4, 8);
    }
}
