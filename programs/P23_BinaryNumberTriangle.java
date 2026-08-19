package programs;

/**
 * ============================================================
 * PROGRAM 23: Alternating 0-1 Binary Triangle
 * ============================================================
 * Problem: WAP to print a binary 0-1 alternating triangle pattern.
 *
 * Example (n = 5):
 * 1
 * 0 1
 * 1 0 1
 * 0 1 0 1
 * 1 0 1 0 1
 * ============================================================
 */
public class P23_BinaryNumberTriangle {

    public static void printBinaryTriangle(int n) {
        System.out.println("=== 0-1 BINARY TRIANGLE (n=" + n + ") ===");

        for (int r = 1; r <= n; r++) {
            for (int c = 1; c <= r; c++) {
                // If (r + c) is even -> print 1, else print 0
                if ((r + c) % 2 == 0) {
                    System.out.print("1 ");
                } else {
                    System.out.print("0 ");
                }
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        printBinaryTriangle(5);
    }
}
