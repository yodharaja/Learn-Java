package programs;

/**
 * ============================================================
 * PROGRAM 18: Strong (Krishnamurthy / Special) Number
 * ============================================================
 * Problem: WAP to check if a number is a Strong Number.
 *   - A number is Strong if the sum of the factorials of its digits
 *     equals the number itself.
 *   - Example: 145 = 1! + 4! + 5! = 1 + 24 + 120 = 145 ✓
 * ============================================================
 */
public class P18_StrongNumber {

    private static final int[] FACTORIAL_LOOKUP = {1, 1, 2, 6, 24, 120, 720, 5040, 40320, 362880};

    public static boolean isStrongNumber(int n) {
        if (n <= 0) return false;
        int original = n;
        int sum = 0;

        while (n > 0) {
            int digit = n % 10;
            sum += FACTORIAL_LOOKUP[digit];
            n /= 10;
        }

        return sum == original;
    }

    public static void main(String[] args) {
        int[] testCases = {1, 2, 145, 40585, 123, 500};

        System.out.println("=== STRONG NUMBER CHECKER ===");
        for (int num : testCases) {
            System.out.printf("  %5d -> %s%n",
                    num, isStrongNumber(num) ? "✓ STRONG NUMBER" : "✗ Not Strong");
        }
    }
}
