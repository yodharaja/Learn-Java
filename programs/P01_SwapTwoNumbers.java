package programs;

/**
 * ============================================================
 * PROGRAM 01: Swap Two Numbers
 * ============================================================
 * Problem: Write a Java Program (WAP) to swap two numbers:
 *   a) Using a temporary third variable
 *   b) Without using a third variable (arithmetic trick)
 *   c) Using bitwise XOR operator
 * ============================================================
 */
public class P01_SwapTwoNumbers {

    public static void main(String[] args) {
        System.out.println("=== 1. USING A THIRD (TEMP) VARIABLE ===");
        int a = 10, b = 20;
        System.out.printf("Before Swap: a = %d, b = %d%n", a, b);

        int temp = a;
        a = b;
        b = temp;
        System.out.printf("After Swap : a = %d, b = %d%n%n", a, b);


        System.out.println("=== 2. WITHOUT A THIRD VARIABLE (ARITHMETIC) ===");
        int x = 50, y = 100;
        System.out.printf("Before Swap: x = %d, y = %d%n", x, y);

        x = x + y; // x becomes 150
        y = x - y; // y becomes 50
        x = x - y; // x becomes 100
        System.out.printf("After Swap : x = %d, y = %d%n%n", x, y);


        System.out.println("=== 3. USING BITWISE XOR (FASTEST) ===");
        int p = 7, q = 3;
        System.out.printf("Before Swap: p = %d, q = %d%n", p, q);

        p = p ^ q;
        q = p ^ q;
        p = p ^ q;
        System.out.printf("After Swap : p = %d, q = %d%n", p, q);
    }
}
