package programs;

/**
 * ============================================================
 * PROGRAM 19: Full Star Pyramid and Diamond Patterns
 * ============================================================
 * Problem: WAP to print:
 *   a) Centered Full Star Pyramid
 *   b) Full Star Diamond Pattern
 * ============================================================
 */
public class P19_StarPyramidDiamond {

    public static void printPyramid(int n) {
        System.out.println("--- Full Star Pyramid (n=" + n + ") ---");
        for (int row = 1; row <= n; row++) {
            for (int space = 1; space <= n - row; space++) System.out.print(" ");
            for (int star = 1; star <= (2 * row - 1); star++) System.out.print("*");
            System.out.println();
        }
    }

    public static void printDiamond(int n) {
        System.out.println("\n--- Full Star Diamond (n=" + n + ") ---");
        // Upper Pyramid
        for (int row = 1; row <= n; row++) {
            for (int s = 1; s <= n - row; s++) System.out.print(" ");
            for (int st = 1; st <= (2 * row - 1); st++) System.out.print("*");
            System.out.println();
        }
        // Lower Inverted Pyramid
        for (int row = n - 1; row >= 1; row--) {
            for (int s = 1; s <= n - row; s++) System.out.print(" ");
            for (int st = 1; st <= (2 * row - 1); st++) System.out.print("*");
            System.out.println();
        }
    }

    public static void main(String[] args) {
        printPyramid(5);
        printDiamond(5);
    }
}
