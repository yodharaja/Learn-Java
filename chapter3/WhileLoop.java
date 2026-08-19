package chapter3;

import java.util.Scanner;

/**
 * ============================================================
 * LESSON 3.4 — While & Do-While Loops, Break & Continue
 * ============================================================
 *
 * while loop: checks condition BEFORE each iteration
 *   → Might not run at all if condition is false from the start
 *
 * do-while loop: checks condition AFTER each iteration
 *   → Always runs AT LEAST ONCE
 *
 * break: immediately EXIT the loop
 * continue: SKIP the rest of this iteration, jump to next
 */
public class WhileLoop {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        // ============================================================
        // 1. BASIC WHILE LOOP
        // ============================================================
        System.out.println("=== WHILE LOOP ===");

        // Syntax:
        //   while (condition) {
        //       // code
        //       // update (important! otherwise infinite loop)
        //   }

        int count = 1;
        while (count <= 5) {
            System.out.println("Count: " + count);
            count++;  // ← DON'T FORGET THIS! Without it → infinite loop!
        }
        // Check condition → run body → update → check condition → ...


        // ============================================================
        // 2. WHILE vs FOR — When to use which?
        // ============================================================
        /*
         * USE FOR when:
         *   - You know HOW MANY times to loop (counted loop)
         *   - for (int i = 0; i < 10; i++)
         *
         * USE WHILE when:
         *   - You DON'T know how many times to loop
         *   - Loop until some condition changes
         *   - Reading input until user says "quit"
         *   - Processing data until end of file
         */

        // Example: Sum digits of a number (don't know how many digits)
        System.out.println("\n=== SUM DIGITS ===");
        int number = 12345;
        int original = number;
        int digitSum = 0;

        while (number > 0) {
            int digit = number % 10;  // get last digit
            digitSum += digit;
            number /= 10;            // remove last digit
        }
        System.out.println("Sum of digits of " + original + " = " + digitSum);
        // 1+2+3+4+5 = 15


        // ============================================================
        // 3. DO-WHILE LOOP
        // ============================================================
        System.out.println("\n=== DO-WHILE LOOP ===");

        // Syntax:
        //   do {
        //       // code (runs at least once!)
        //   } while (condition);  ← note the semicolon!

        // Key difference: body runs FIRST, condition checked AFTER
        // This guarantees the body runs at least once.

        int x = 10;

        // Regular while — condition false from start, body NEVER runs
        while (x < 5) {
            System.out.println("while: This won't print");
        }

        // Do-while — body runs ONCE even though condition is false
        do {
            System.out.println("do-while: This prints once! (x = " + x + ")");
        } while (x < 5);
        // Body runs → checks x < 5 → false → stops


        // ============================================================
        // 4. DO-WHILE FOR INPUT VALIDATION
        // ============================================================
        // Perfect use case: Ask user for input, repeat until valid
        System.out.println("\n=== INPUT VALIDATION ===");

        int userAge;
        do {
            System.out.print("Enter your age (1-120): ");
            userAge = sc.nextInt();
            if (userAge < 1 || userAge > 120) {
                System.out.println("  Invalid! Try again.");
            }
        } while (userAge < 1 || userAge > 120);
        System.out.println("Your age: " + userAge + " ✓");


        // ============================================================
        // 5. BREAK — Exit the loop immediately
        // ============================================================
        System.out.println("\n=== BREAK ===");

        // break stops the loop entirely, execution continues after the loop

        // Find first negative number in array
        int[] data = {5, 12, 8, -3, 7, 15};
        for (int i = 0; i < data.length; i++) {
            if (data[i] < 0) {
                System.out.println("First negative: " + data[i] + " at index " + i);
                break;  // stop searching, we found it
            }
        }

        // Break with while — exit when target found
        int target = 42;
        int guess = 0;
        while (true) {  // infinite loop!
            guess += 7;
            if (guess >= target) {
                System.out.println("First multiple of 7 >= " + target + " is " + guess);
                break;  // exit the infinite loop
            }
        }


        // ============================================================
        // 6. CONTINUE — Skip to next iteration
        // ============================================================
        System.out.println("\n=== CONTINUE ===");

        // continue skips the REST of the current iteration
        // and jumps to the next iteration of the loop

        // Print only odd numbers from 1 to 10
        System.out.print("Odd numbers: ");
        for (int i = 1; i <= 10; i++) {
            if (i % 2 == 0) {
                continue;  // skip even numbers, jump to next i
            }
            System.out.print(i + " ");
        }
        System.out.println();

        // Skip negative numbers when calculating sum
        int[] mixed = {10, -5, 20, -8, 30, -1, 40};
        int positiveSum = 0;
        for (int val : mixed) {
            if (val < 0) {
                continue;  // skip negatives
            }
            positiveSum += val;
        }
        System.out.println("Sum of positives: " + positiveSum);  // 100


        // ============================================================
        // 7. LABELED BREAK (Breaking out of nested loops)
        // ============================================================
        System.out.println("\n=== LABELED BREAK ===");

        // Normal break only exits the INNER loop.
        // To exit an OUTER loop from inside, use a label.

        outerLoop:  // ← this is a label
        for (int row = 1; row <= 5; row++) {
            for (int col = 1; col <= 5; col++) {
                if (row * col > 10) {
                    System.out.println("\n  Breaking at row=" + row + ", col=" + col);
                    break outerLoop;  // breaks the OUTER loop!
                }
                System.out.print(row * col + "\t");
            }
            System.out.println();
        }


        // ============================================================
        // 8. INFINITE LOOP PATTERNS
        // ============================================================
        System.out.println("\n=== INFINITE LOOP PATTERNS ===");

        // Pattern 1: while(true) with break
        // Already shown above

        // Pattern 2: for(;;) — less common, same as while(true)
        // for (;;) { break; }

        // WARNING: An infinite loop without a break will freeze your program!
        // Always have an exit condition.

        // Common use: Menu-driven programs
        /*
         * while (true) {
         *     System.out.println("1. Add  2. View  3. Exit");
         *     int choice = sc.nextInt();
         *     if (choice == 3) break;
         *     // ... handle other choices
         * }
         */

        sc.close();

        // ============================================================
        // KEY TAKEAWAYS:
        // ============================================================
        // - while: check condition FIRST → may never run
        // - do-while: run body FIRST → always runs at least once
        // - Use 'for' when you know the count, 'while' when you don't
        // - break: exit the loop immediately
        // - continue: skip to the next iteration
        // - Labeled break: exit outer loops from inner loops
        // - ALWAYS ensure your loop has an exit condition!
        // ============================================================
    }
}
