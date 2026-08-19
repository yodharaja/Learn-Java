package programs;

/**
 * ============================================================
 * PROGRAM 12: Armstrong (Narcissistic) Number Checker
 * ============================================================
 * Problem: WAP to check if an N-digit number is an Armstrong number.
 *   - A number is Armstrong if the sum of its digits each raised to the
 *     power of total number of digits equals the original number.
 *   - 3-digit: 153 = 1^3 + 5^3 + 3^3 = 1 + 125 + 27 = 153 ✓
 *   - 4-digit: 1634 = 1^4 + 6^4 + 3^4 + 4^4 = 1 + 1296 + 81 + 256 = 1634 ✓
 * ============================================================
 */
public class P12_ArmstrongNumber {

    public static boolean isArmstrong(int n) {
        if (n < 0) return false;
        int original = n;

        // Count number of digits
        int digits = 0;
        int temp = n;
        while (temp > 0) {
            digits++;
            temp /= 10;
        }

        // Sum powers of digits
        int sum = 0;
        temp = n;
        while (temp > 0) {
            int digit = temp % 10;
            sum += Math.pow(digit, digits);
            temp /= 10;
        }

        return sum == original;
    }

    public static void main(String[] args) {
        int[] testCases = {153, 370, 371, 407, 1634, 8208, 9474, 123, 500};

        System.out.println("=== ARMSTRONG NUMBER TEST CASES ===");
        for (int num : testCases) {
            System.out.printf("  %5d -> %s%n",
                    num, isArmstrong(num) ? "✓ ARMSTRONG NUMBER" : "✗ Not Armstrong");
        }
    }
}
