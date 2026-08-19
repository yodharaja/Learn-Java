package programs;

/**
 * ============================================================
 * PROGRAM 06: Quotient and Remainder Calculation
 * ============================================================
 * Problem: WAP to compute Quotient and Remainder of two numbers
 * and verify the Division Algorithm: Dividend = (Divisor * Quotient) + Remainder.
 * ============================================================
 */
public class P06_QuotientAndRemainder {

    public static void main(String[] args) {
        int dividend = 250;
        int divisor = 7;

        int quotient = dividend / divisor;
        int remainder = dividend % divisor;

        System.out.println("Dividend  : " + dividend);
        System.out.println("Divisor   : " + divisor);
        System.out.println("Quotient  : " + quotient);
        System.out.println("Remainder : " + remainder);

        // Verification
        int reconstructed = (divisor * quotient) + remainder;
        System.out.println("\nVerification: (" + divisor + " * " + quotient + ") + " + remainder + " = " + reconstructed);
        System.out.println("Algorithm valid? " + (reconstructed == dividend));
    }
}
