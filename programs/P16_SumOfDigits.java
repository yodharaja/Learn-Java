package programs;

/**
 * ============================================================
 * PROGRAM 16: Sum and Product of Digits
 * ============================================================
 * Problem: WAP to calculate the sum and product of all digits of an integer.
 *   - Input : 12345
 *   - Sum   : 1 + 2 + 3 + 4 + 5 = 15
 *   - Prod  : 1 * 2 * 3 * 4 * 5 = 120
 * ============================================================
 */
public class P16_SumOfDigits {

    public static void computeDigitStats(int n) {
        int original = n;
        n = Math.abs(n);

        int sum = 0;
        long product = (n == 0) ? 0 : 1;

        if (n == 0) {
            sum = 0;
            product = 0;
        } else {
            while (n > 0) {
                int digit = n % 10;
                sum += digit;
                product *= digit;
                n /= 10;
            }
        }

        System.out.printf("Number: %-6d -> Sum of Digits: %2d | Product of Digits: %d%n",
                original, sum, product);
    }

    public static void main(String[] args) {
        int[] testCases = {12345, 987, 405, 7, 0, -234};

        System.out.println("=== SUM AND PRODUCT OF DIGITS ===");
        for (int num : testCases) {
            computeDigitStats(num);
        }
    }
}
