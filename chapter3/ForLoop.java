package chapter3;

/**
 * ============================================================
 * LESSON 3.3 — For Loops
 * ============================================================
 *
 * Loops let you repeat a block of code multiple times.
 * The 'for' loop is the most common loop in Java.
 *
 * Syntax:
 *   for (initialization; condition; update) {
 *       // code to repeat
 *   }
 *
 * How it works:
 *   1. initialization runs ONCE (before the loop starts)
 *   2. condition is checked BEFORE each iteration
 *   3. code runs if condition is true
 *   4. update runs AFTER each iteration
 *   5. Go back to step 2
 */
public class ForLoop {

    public static void main(String[] args) {

        // ============================================================
        // 1. BASIC FOR LOOP
        // ============================================================
        System.out.println("=== BASIC FOR LOOP ===");

        // Print numbers 1 to 5
        for (int i = 1; i <= 5; i++) {
            System.out.println("i = " + i);
        }
        // i=1 → print → i++ → i=2 → print → i++ → ... → i=5 → print → i++ → i=6 → 6<=5 is false → STOP

        // IMPORTANT: 'i' only exists INSIDE the loop (it's scoped to the loop)
        // System.out.println(i);  ← ERROR! i doesn't exist here


        // ============================================================
        // 2. COUNTING VARIATIONS
        // ============================================================
        System.out.println("\n=== COUNTING VARIATIONS ===");

        // Count down
        System.out.print("Countdown: ");
        for (int i = 5; i >= 1; i--) {
            System.out.print(i + " ");
        }
        System.out.println("Go! 🚀");

        // Count by 2s (even numbers)
        System.out.print("Even numbers: ");
        for (int i = 2; i <= 20; i += 2) {
            System.out.print(i + " ");
        }
        System.out.println();

        // Count by 3s
        System.out.print("Multiples of 3: ");
        for (int i = 3; i <= 30; i += 3) {
            System.out.print(i + " ");
        }
        System.out.println();


        // ============================================================
        // 3. LOOP WITH ARRAYS
        // ============================================================
        System.out.println("\n=== LOOP WITH ARRAYS ===");

        int[] scores = {95, 87, 72, 64, 91};

        // Using index to access each element
        System.out.println("Scores:");
        for (int i = 0; i < scores.length; i++) {
            System.out.println("  Student " + (i + 1) + ": " + scores[i]);
        }

        // Calculate sum and average
        int sum = 0;
        for (int i = 0; i < scores.length; i++) {
            sum += scores[i];
        }
        double average = (double) sum / scores.length;
        System.out.println("Sum: " + sum + ", Average: " + average);


        // ============================================================
        // 4. ENHANCED FOR-EACH LOOP
        // ============================================================
        // When you don't need the index, use for-each — it's cleaner!
        // Syntax: for (type variable : array) { ... }
        System.out.println("\n=== FOR-EACH LOOP ===");

        String[] fruits = {"Apple", "Banana", "Cherry", "Date", "Elderberry"};

        // Traditional for loop
        System.out.println("Traditional:");
        for (int i = 0; i < fruits.length; i++) {
            System.out.println("  " + fruits[i]);
        }

        // For-each (cleaner!)
        System.out.println("For-each:");
        for (String fruit : fruits) {
            System.out.println("  " + fruit);
        }

        // For-each with numbers
        int[] numbers = {10, 20, 30, 40, 50};
        int total = 0;
        for (int num : numbers) {
            total += num;
        }
        System.out.println("Total: " + total);

        /*
         * When to use which?
         *   - for-each: When you just need to READ each element
         *   - traditional for: When you need the INDEX or want to MODIFY elements
         */


        // ============================================================
        // 5. NESTED FOR LOOPS (loop inside a loop)
        // ============================================================
        System.out.println("\n=== NESTED LOOPS ===");

        // Multiplication table (3x3)
        System.out.println("Multiplication table:");
        for (int row = 1; row <= 3; row++) {
            for (int col = 1; col <= 3; col++) {
                System.out.printf("%4d", row * col);  // %4d = right-aligned, 4 chars wide
            }
            System.out.println();  // new line after each row
        }
        // Outer loop runs 3 times
        // For EACH outer iteration, inner loop runs 3 times
        // Total iterations: 3 × 3 = 9


        // ============================================================
        // 6. PRACTICAL EXAMPLES
        // ============================================================
        System.out.println("\n=== PRACTICAL EXAMPLES ===");

        // Find the maximum value in an array
        int[] data = {23, 45, 12, 67, 34, 89, 11};
        int max = data[0];  // assume first element is max
        for (int i = 1; i < data.length; i++) {
            if (data[i] > max) {
                max = data[i];
            }
        }
        System.out.println("Max value: " + max);  // 89

        // Reverse a string
        String original = "JAVA";
        String reversed = "";
        for (int i = original.length() - 1; i >= 0; i--) {
            reversed += original.charAt(i);
        }
        System.out.println("\"" + original + "\" reversed = \"" + reversed + "\"");

        // Count vowels in a string
        String text = "Hello World";
        int vowelCount = 0;
        for (int i = 0; i < text.length(); i++) {
            char ch = Character.toLowerCase(text.charAt(i));
            if (ch == 'a' || ch == 'e' || ch == 'i' || ch == 'o' || ch == 'u') {
                vowelCount++;
            }
        }
        System.out.println("Vowels in \"" + text + "\": " + vowelCount);  // 3

        // Check if a number is prime
        int n = 29;
        boolean isPrime = true;
        if (n <= 1) {
            isPrime = false;
        } else {
            for (int i = 2; i <= Math.sqrt(n); i++) {
                if (n % i == 0) {
                    isPrime = false;
                    break;  // no need to check further
                }
            }
        }
        System.out.println(n + " is prime? " + isPrime);  // true


        // ============================================================
        // KEY TAKEAWAYS:
        // ============================================================
        // - for (init; condition; update) — classic counted loop
        // - for (type var : array) — for-each, cleaner when no index needed
        // - Loop variable (i) is scoped to the loop
        // - Nested loops: total iterations = outer × inner
        // - Use for-each for reading, traditional for when index/modify needed
        // - Common patterns: sum, max/min, reverse, search, count
        // ============================================================
    }
}
