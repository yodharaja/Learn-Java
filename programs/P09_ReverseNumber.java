package programs;

/**
 * ============================================================
 * PROGRAM 09: Reverse an Integer Number
 * ============================================================
 * Problem: WAP to reverse the digits of an integer number.
 *   - Input : 12345
 *   - Output: 54321
 *   - Input : -987
 *   - Output: -789
 * ============================================================
 */
public class P09_ReverseNumber {

    public static int reverseInteger(int n) {
        boolean isNegative = n < 0;
        n = Math.abs(n);

        int reversed = 0;
        while (n > 0) {
            int lastDigit = n % 10;
            reversed = (reversed * 10) + lastDigit;
            n /= 10;
        }

        return isNegative ? -reversed : reversed;
    }

    public static void main(String[] args) {
        int[] numbers = {12345, 987654, 100, 7, -456, 12003};

        System.out.println("=== INTEGER REVERSAL ===");
        for (int num : numbers) {
            System.out.printf("  Original: %7d  ->  Reversed: %7d%n", num, reverseInteger(num));
        }
    }
}
