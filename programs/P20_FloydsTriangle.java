package programs;

/**
 * ============================================================
 * PROGRAM 20: Floyd's Triangle Pattern
 * ============================================================
 * Problem: WAP to print Floyd's Triangle of natural numbers.
 *
 * Example (n = 5):
 * 1
 * 2  3
 * 4  5  6
 * 7  8  9  10
 * 11 12 13 14 15
 * ============================================================
 */
public class P20_FloydsTriangle {

    public static void printFloydsTriangle(int rows) {
        System.out.println("=== FLOYD'S TRIANGLE (rows=" + rows + ") ===");
        int currentNum = 1;

        for (int r = 1; r <= rows; r++) {
            for (int c = 1; c <= r; c++) {
                System.out.printf("%-4d", currentNum++);
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        printFloydsTriangle(5);
    }
}
