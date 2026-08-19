package programs;

/**
 * ============================================================
 * PROGRAM 13: Fibonacci Series Generator
 * ============================================================
 * Problem: WAP to generate the Fibonacci series up to N terms:
 *   - Iterative approach (O(n) time, O(1) space)
 *   - Recursive approach
 *   Sequence: 0, 1, 1, 2, 3, 5, 8, 13, 21, 34, 55, ...
 * ============================================================
 */
public class P13_FibonacciSeries {

    public static void printFibonacciIterative(int terms) {
        if (terms <= 0) return;
        System.out.printf("Fibonacci (%d terms): ", terms);

        long a = 0, b = 1;
        for (int i = 1; i <= terms; i++) {
            System.out.print(a + " ");
            long next = a + b;
            a = b;
            b = next;
        }
        System.out.println();
    }

    public static long fibonacciRecursive(int n) {
        if (n <= 0) return 0;
        if (n == 1) return 1;
        return fibonacciRecursive(n - 1) + fibonacciRecursive(n - 2);
    }

    public static void main(String[] args) {
        System.out.println("=== ITERATIVE FIBONACCI ===");
        printFibonacciIterative(10);
        printFibonacciIterative(15);

        System.out.println("\n=== RECURSIVE Nth FIBONACCI TERM ===");
        for (int i = 0; i <= 8; i++) {
            System.out.printf("  fib(%d) = %d%n", i, fibonacciRecursive(i));
        }
    }
}
