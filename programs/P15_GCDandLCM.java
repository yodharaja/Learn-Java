package programs;

/**
 * ============================================================
 * PROGRAM 15: GCD (HCF) and LCM Calculator
 * ============================================================
 * Problem: WAP to find the Greatest Common Divisor (GCD) and
 * Least Common Multiple (LCM) of two numbers using the Euclidean algorithm.
 *   - Formula: GCD(a, b) = GCD(b, a % b)
 *   - Formula: LCM(a, b) = (a * b) / GCD(a, b)
 * ============================================================
 */
public class P15_GCDandLCM {

    public static int findGCD(int a, int b) {
        while (b != 0) {
            int temp = b;
            b = a % b;
            a = temp;
        }
        return Math.abs(a);
    }

    public static long findLCM(int a, int b) {
        if (a == 0 || b == 0) return 0;
        return (Math.abs((long) a * b)) / findGCD(a, b);
    }

    public static void main(String[] args) {
        int[][] pairs = {
            {48, 18},
            {60, 48},
            {12, 15},
            {100, 25},
            {17, 19} // coprime
        };

        System.out.println("=== GCD AND LCM TESTS ===");
        for (int[] pair : pairs) {
            int a = pair[0], b = pair[1];
            System.out.printf("  Numbers: (%3d, %3d)  ->  GCD: %2d  |  LCM: %4d%n",
                    a, b, findGCD(a, b), findLCM(a, b));
        }
    }
}
