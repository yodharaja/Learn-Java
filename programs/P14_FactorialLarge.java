package programs;

import java.math.BigInteger;

/**
 * ============================================================
 * PROGRAM 14: Factorial (Handling Arbitrarily Large Numbers)
 * ============================================================
 * Problem: WAP to calculate factorial of a number using:
 *   a) Standard loop for small inputs
 *   b) `java.math.BigInteger` for large factorials (e.g. 50! or 100!)
 * ============================================================
 */
public class P14_FactorialLarge {

    public static long factorialStandard(int n) {
        if (n < 0) return -1;
        long fact = 1;
        for (int i = 2; i <= n; i++) fact *= i;
        return fact;
    }

    public static BigInteger factorialBigInteger(int n) {
        if (n < 0) return BigInteger.valueOf(-1);
        BigInteger result = BigInteger.ONE;
        for (int i = 2; i <= n; i++) {
            result = result.multiply(BigInteger.valueOf(i));
        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println("=== STANDARD FACTORIAL (0..15) ===");
        for (int i = 0; i <= 10; i++) {
            System.out.printf("  %2d! = %,d%n", i, factorialStandard(i));
        }

        System.out.println("\n=== LARGE FACTORIALS (BIGINTEGER) ===");
        System.out.println("25! = " + factorialBigInteger(25));
        System.out.println("50! = " + factorialBigInteger(50));
        System.out.println("100! = " + factorialBigInteger(100));
    }
}
