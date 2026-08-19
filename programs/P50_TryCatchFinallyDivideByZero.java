package programs;

/**
 * ============================================================
 * PROGRAM 50: Try-Catch-Finally and Multi-Catch Handling
 * ============================================================
 * Problem: WAP to demonstrate try, multi-catch, and finally block
 * handling `ArithmeticException`, `ArrayIndexOutOfBoundsException`,
 * and `NumberFormatException`.
 * ============================================================
 */
public class P50_TryCatchFinallyDivideByZero {

    public static void safeExecute(String numStr, int divisor, int arrayIndex) {
        System.out.printf("%nExecuting with numStr='%s', divisor=%d, idx=%d:%n", numStr, divisor, arrayIndex);
        int[] buffer = {10, 20, 30};

        try {
            int parsed = Integer.parseInt(numStr);
            int result = parsed / divisor;
            int item = buffer[arrayIndex];
            System.out.printf("  ✓ Success: Result=%d, Item=%d%n", result, item);

        } catch (ArithmeticException | NumberFormatException e) {
            System.out.println("  ❌ Caught Math/Parse Error: " + e.getMessage());
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("  ❌ Caught Array Bounds Error: " + e.getMessage());
        } finally {
            System.out.println("  [Finally] Cleanup routine completed unconditionally.");
        }
    }

    public static void main(String[] args) {
        safeExecute("100", 2, 1);    // Success
        safeExecute("100", 0, 1);    // Divide by zero
        safeExecute("abc", 5, 0);    // Parse error
        safeExecute("500", 10, 10);  // Array index error
    }
}
