package programs;

/**
 * ============================================================
 * PROGRAM 17: Count Digits in an Integer
 * ============================================================
 * Problem: WAP to count the number of digits in an integer using:
 *   1. Iterative division loop
 *   2. Logarithmic formula: `(int) Math.log10(n) + 1`
 *   3. String length conversion
 * ============================================================
 */
public class P17_CountDigitsInNumber {

    public static int countDigitsLoop(int n) {
        if (n == 0) return 1;
        n = Math.abs(n);
        int count = 0;
        while (n > 0) {
            count++;
            n /= 10;
        }
        return count;
    }

    public static int countDigitsLog(int n) {
        if (n == 0) return 1;
        return (int) Math.log10(Math.abs(n)) + 1;
    }

    public static void main(String[] args) {
        int[] numbers = {0, 5, 42, 100, 9999, 123456, -9876543};

        System.out.println("=== DIGIT COUNTING METHODS ===");
        for (int num : numbers) {
            System.out.printf("  Number: %-10d -> Count (Loop): %d | Count (Log): %d%n",
                    num, countDigitsLoop(num), countDigitsLog(num));
        }
    }
}
