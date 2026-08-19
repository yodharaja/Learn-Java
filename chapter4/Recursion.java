package chapter4;

/**
 * ============================================================
 * LESSON 4.3 — Recursion
 * ============================================================
 *
 * Recursion = a method that CALLS ITSELF to solve a problem.
 *
 * Every recursive method needs:
 *   1. BASE CASE — when to stop (prevents infinite recursion!)
 *   2. RECURSIVE CASE — the method calls itself with a smaller problem
 *
 * Think of it like Russian nesting dolls (matryoshka):
 *   - Each doll contains a smaller doll inside
 *   - The smallest doll is the base case (nothing inside)
 *
 * Recursion vs Iteration:
 *   - Recursion: elegant, but uses more memory (call stack)
 *   - Iteration: more efficient, but sometimes less readable
 *   - Some problems are naturally recursive (trees, fractals)
 */
public class Recursion {

    // ============================================================
    // 1. COUNTING DOWN — Simplest recursion example
    // ============================================================
    static void countDown(int n) {
        // BASE CASE: stop when n reaches 0
        if (n <= 0) {
            System.out.println("Go! 🚀");
            return;  // stop recursion
        }

        // RECURSIVE CASE: print and call with smaller value
        System.out.println(n);
        countDown(n - 1);  // calls itself with n-1
    }
    /*
     * Call stack visualization for countDown(3):
     *
     *   countDown(3) → prints 3
     *     countDown(2) → prints 2
     *       countDown(1) → prints 1
     *         countDown(0) → prints "Go!" → returns
     *       returns
     *     returns
     *   returns
     */


    // ============================================================
    // 2. FACTORIAL — Classic recursion problem
    // ============================================================
    // n! = n × (n-1) × (n-2) × ... × 1
    // 5! = 5 × 4 × 3 × 2 × 1 = 120
    // 0! = 1 (by definition)
    static long factorial(int n) {
        // Base case
        if (n <= 1) {
            return 1;
        }
        // Recursive case: n! = n × (n-1)!
        return n * factorial(n - 1);
    }
    /*
     * factorial(5)
     *   = 5 × factorial(4)
     *   = 5 × 4 × factorial(3)
     *   = 5 × 4 × 3 × factorial(2)
     *   = 5 × 4 × 3 × 2 × factorial(1)
     *   = 5 × 4 × 3 × 2 × 1
     *   = 120
     */

    // Iterative version for comparison
    static long factorialIterative(int n) {
        long result = 1;
        for (int i = 2; i <= n; i++) {
            result *= i;
        }
        return result;
    }


    // ============================================================
    // 3. FIBONACCI SEQUENCE
    // ============================================================
    // 0, 1, 1, 2, 3, 5, 8, 13, 21, 34, ...
    // Each number = sum of the two before it
    // fib(0)=0, fib(1)=1, fib(n) = fib(n-1) + fib(n-2)
    static int fibonacci(int n) {
        // Base cases
        if (n <= 0) return 0;
        if (n == 1) return 1;

        // Recursive case
        return fibonacci(n - 1) + fibonacci(n - 2);
    }
    /*
     * fibonacci(5)
     *   = fib(4) + fib(3)
     *   = (fib(3) + fib(2)) + (fib(2) + fib(1))
     *   = ((fib(2)+fib(1)) + (fib(1)+fib(0))) + ((fib(1)+fib(0)) + 1)
     *   = ((1+1) + (1+0)) + ((1+0) + 1)
     *   = 5
     *
     * WARNING: This is very SLOW for large n!
     * fib(40) takes seconds, fib(50) takes minutes.
     * Each call branches into TWO more calls → exponential growth.
     * The iterative version is much faster.
     */

    // Iterative Fibonacci (much faster!)
    static int fibonacciIterative(int n) {
        if (n <= 0) return 0;
        if (n == 1) return 1;

        int prev2 = 0, prev1 = 1;
        int current = 0;
        for (int i = 2; i <= n; i++) {
            current = prev1 + prev2;
            prev2 = prev1;
            prev1 = current;
        }
        return current;
    }


    // ============================================================
    // 4. POWER — x^n using recursion
    // ============================================================
    // x^n = x × x^(n-1)
    // x^0 = 1
    static double power(double base, int exponent) {
        if (exponent == 0) return 1;
        if (exponent < 0) return 1.0 / power(base, -exponent);
        return base * power(base, exponent - 1);
    }


    // ============================================================
    // 5. SUM OF DIGITS
    // ============================================================
    // sumDigits(12345) = 1+2+3+4+5 = 15
    static int sumDigits(int n) {
        if (n < 0) n = -n;  // handle negative
        if (n < 10) return n;  // base case: single digit

        return (n % 10) + sumDigits(n / 10);
        // Last digit + sumDigits(remaining digits)
    }


    // ============================================================
    // 6. REVERSE A STRING
    // ============================================================
    static String reverse(String str) {
        // Base case: empty or single character
        if (str.length() <= 1) {
            return str;
        }
        // Take last char + reverse the rest
        return str.charAt(str.length() - 1) + reverse(str.substring(0, str.length() - 1));
    }


    // ============================================================
    // 7. PALINDROME CHECK
    // ============================================================
    static boolean isPalindrome(String str) {
        // Base case
        if (str.length() <= 1) return true;

        // Check first and last characters
        if (str.charAt(0) != str.charAt(str.length() - 1)) {
            return false;
        }

        // Recurse on the middle portion
        return isPalindrome(str.substring(1, str.length() - 1));
    }


    // ============================================================
    // 8. BINARY SEARCH (recursive)
    // ============================================================
    // Searches for a target in a SORTED array
    // Returns index if found, -1 if not found
    static int binarySearch(int[] arr, int target, int low, int high) {
        // Base case: not found
        if (low > high) return -1;

        int mid = (low + high) / 2;

        if (arr[mid] == target) {
            return mid;  // found!
        } else if (arr[mid] < target) {
            return binarySearch(arr, target, mid + 1, high);  // search right half
        } else {
            return binarySearch(arr, target, low, mid - 1);   // search left half
        }
    }


    // ============================================================
    // MAIN
    // ============================================================
    public static void main(String[] args) {

        // --- 1. Countdown ---
        System.out.println("=== COUNTDOWN ===");
        countDown(5);

        // --- 2. Factorial ---
        System.out.println("\n=== FACTORIAL ===");
        for (int i = 0; i <= 10; i++) {
            System.out.println(i + "! = " + factorial(i));
        }
        System.out.println("20! = " + factorial(20));  // works with long

        // --- 3. Fibonacci ---
        System.out.println("\n=== FIBONACCI ===");
        System.out.print("Fibonacci sequence: ");
        for (int i = 0; i <= 15; i++) {
            System.out.print(fibonacciIterative(i) + " ");
        }
        System.out.println();

        // Compare recursive vs iterative speed
        System.out.println("\nSpeed comparison for fib(35):");
        long start = System.currentTimeMillis();
        int result1 = fibonacci(35);
        long time1 = System.currentTimeMillis() - start;

        start = System.currentTimeMillis();
        int result2 = fibonacciIterative(35);
        long time2 = System.currentTimeMillis() - start;

        System.out.println("  Recursive: " + result1 + " (" + time1 + " ms)");
        System.out.println("  Iterative: " + result2 + " (" + time2 + " ms)");
        System.out.println("  Iterative is MUCH faster!");

        // --- 4. Power ---
        System.out.println("\n=== POWER ===");
        System.out.println("2^10 = " + (int) power(2, 10));   // 1024
        System.out.println("3^4  = " + (int) power(3, 4));    // 81
        System.out.println("5^0  = " + (int) power(5, 0));    // 1
        System.out.println("2^-3 = " + power(2, -3));          // 0.125

        // --- 5. Sum of digits ---
        System.out.println("\n=== SUM OF DIGITS ===");
        System.out.println("sumDigits(12345) = " + sumDigits(12345));  // 15
        System.out.println("sumDigits(9999)  = " + sumDigits(9999));   // 36

        // --- 6. Reverse ---
        System.out.println("\n=== REVERSE STRING ===");
        System.out.println("reverse(\"JAVA\")  = " + reverse("JAVA"));
        System.out.println("reverse(\"Hello\") = " + reverse("Hello"));

        // --- 7. Palindrome ---
        System.out.println("\n=== PALINDROME ===");
        String[] words = {"racecar", "madam", "hello", "level", "java"};
        for (String word : words) {
            System.out.println("  \"" + word + "\" → " + (isPalindrome(word) ? "✓ Palindrome" : "✗ Not palindrome"));
        }

        // --- 8. Binary Search ---
        System.out.println("\n=== BINARY SEARCH ===");
        int[] sorted = {2, 5, 8, 12, 16, 23, 38, 45, 67, 91};
        System.out.print("Array: ");
        for (int n : sorted) System.out.print(n + " ");
        System.out.println();

        int target = 23;
        int index = binarySearch(sorted, target, 0, sorted.length - 1);
        System.out.println("Search for " + target + ": found at index " + index);

        target = 50;
        index = binarySearch(sorted, target, 0, sorted.length - 1);
        System.out.println("Search for " + target + ": " + (index == -1 ? "not found" : "index " + index));


        // ============================================================
        // KEY TAKEAWAYS:
        // ============================================================
        // - Recursion = method calling itself
        // - ALWAYS need a base case to prevent infinite recursion
        // - Each recursive call adds to the CALL STACK (uses memory)
        // - StackOverflowError = too many recursive calls (no base case or too deep)
        // - Recursion is elegant but often slower than iteration
        // - Use recursion for: trees, divide-and-conquer, backtracking
        // - Use iteration for: simple loops, performance-critical code
        // ============================================================
    }
}
