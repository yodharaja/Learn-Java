package programs;

/**
 * ============================================================
 * PROGRAM 08: Odd or Even Check Using Bitwise Operator
 * ============================================================
 * Problem: WAP to check if a number is Even or Odd without using
 * the modulus (%) operator, by utilizing bitwise AND (&).
 *
 * Logic:
 *   - In binary, the least significant bit (LSB) of any odd number is 1.
 *   - The LSB of any even number is 0.
 *   - Therefore: `(n & 1) == 0` -> EVEN, `(n & 1) == 1` -> ODD.
 * ============================================================
 */
public class P08_OddOrEvenBitwise {

    public static boolean isEvenBitwise(int n) {
        return (n & 1) == 0;
    }

    public static void main(String[] args) {
        int[] testNumbers = {0, 1, 2, 13, 24, 99, 100, -5, -8};

        System.out.println("=== BITWISE ODD/EVEN CHECKER ===");
        for (int num : testNumbers) {
            boolean even = isEvenBitwise(num);
            String binary = Integer.toBinaryString(num);
            System.out.printf("  Number: %4d (Binary LSB: %c) -> %s%n",
                    num, binary.charAt(binary.length() - 1), even ? "EVEN" : "ODD");
        }
    }
}
