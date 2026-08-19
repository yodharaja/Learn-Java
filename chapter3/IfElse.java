package chapter3;

import java.util.Scanner;

/**
 * ============================================================
 * LESSON 3.1 — If-Else Statements
 * ============================================================
 *
 * Control flow lets your program make DECISIONS.
 * Instead of running every line top-to-bottom, you can skip
 * or choose blocks of code based on conditions.
 *
 * Syntax:
 *   if (condition) { ... }
 *   else if (condition) { ... }
 *   else { ... }
 *
 * The condition must be a boolean expression (true or false).
 */
public class IfElse {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        // ============================================================
        // 1. SIMPLE IF
        // ============================================================
        // Executes the block ONLY if condition is true
        System.out.println("=== SIMPLE IF ===");

        int temperature = 35;

        if (temperature > 30) {
            System.out.println("It's hot outside! ☀️");
        }
        // If temperature was 25, nothing would print — the block is skipped.


        // ============================================================
        // 2. IF-ELSE
        // ============================================================
        // Two paths: one if true, another if false
        System.out.println("\n=== IF-ELSE ===");

        int age = 16;

        if (age >= 18) {
            System.out.println("You can vote! 🗳️");
        } else {
            System.out.println("Sorry, you're too young to vote.");
            System.out.println("Wait " + (18 - age) + " more years.");
        }


        // ============================================================
        // 3. IF — ELSE IF — ELSE (Multiple conditions)
        // ============================================================
        // Checks conditions in order. First true one wins. At most ONE block runs.
        System.out.println("\n=== IF-ELSE IF-ELSE ===");

        int score = 78;

        if (score >= 90) {
            System.out.println("Grade: A ⭐");
        } else if (score >= 80) {
            System.out.println("Grade: B 👍");
        } else if (score >= 70) {
            System.out.println("Grade: C 👌");       // ← This one runs (78 >= 70)
        } else if (score >= 60) {
            System.out.println("Grade: D");
        } else {
            System.out.println("Grade: F ❌");
        }
        // Order matters! If you put (score >= 60) first, it would match
        // before reaching (score >= 70). Always check the strictest condition first.


        // ============================================================
        // 4. NESTED IF (if inside if)
        // ============================================================
        System.out.println("\n=== NESTED IF ===");

        boolean hasTicket = true;
        int passengerAge = 12;

        if (hasTicket) {
            System.out.println("Ticket verified ✓");

            if (passengerAge < 5) {
                System.out.println("Seat: Free (infant)");
            } else if (passengerAge < 13) {
                System.out.println("Seat: Child discount 50%");  // ← This runs
            } else {
                System.out.println("Seat: Full price");
            }
        } else {
            System.out.println("No ticket! Entry denied. ❌");
        }


        // ============================================================
        // 5. COMBINING CONDITIONS with && and ||
        // ============================================================
        System.out.println("\n=== COMBINED CONDITIONS ===");

        String username = "admin";
        String password = "secret123";

        // AND (&&) — both must be true
        if (username.equals("admin") && password.equals("secret123")) {
            System.out.println("Login successful! ✓");
        } else {
            System.out.println("Invalid credentials ✗");
        }

        // OR (||) — at least one must be true
        String day = "Saturday";
        if (day.equals("Saturday") || day.equals("Sunday")) {
            System.out.println(day + " is a weekend! 🎉");
        } else {
            System.out.println(day + " is a weekday.");
        }

        // NOT (!) — flip the condition
        boolean isRaining = false;
        if (!isRaining) {
            System.out.println("No umbrella needed! ☀️");
        }


        // ============================================================
        // 6. COMMON MISTAKES
        // ============================================================
        System.out.println("\n=== COMMON MISTAKES ===");

        int x = 5;

        // MISTAKE 1: Using = instead of ==
        // if (x = 10)  ← This ASSIGNS 10 to x, doesn't compare! WON'T COMPILE in Java.
        if (x == 10) { // ← Correct: double equals for comparison
            System.out.println("x is 10");
        }

        // MISTAKE 2: Comparing strings with ==
        String name = "Hello";
        // if (name == "Hello")  ← WRONG for strings!
        if (name.equals("Hello")) { // ← Correct: always use .equals()
            System.out.println("Name matches!");
        }

        // MISTAKE 3: Missing braces (single-line blocks)
        // This works but is DANGEROUS:
        if (x > 0)
            System.out.println("positive");
        // If you add another line, it runs ALWAYS — not part of the if!
        // Best practice: ALWAYS use braces { } even for single lines.


        // ============================================================
        // 7. INTERACTIVE EXAMPLE
        // ============================================================
        System.out.println("\n=== TRY IT! ===");
        System.out.print("Enter a number: ");
        int number = sc.nextInt();

        // Check multiple properties of the number
        if (number > 0) {
            System.out.println(number + " is positive");
        } else if (number < 0) {
            System.out.println(number + " is negative");
        } else {
            System.out.println("The number is zero");
        }

        if (number % 2 == 0) {
            System.out.println(number + " is even");
        } else {
            System.out.println(number + " is odd");
        }

        if (number >= 1 && number <= 100) {
            System.out.println(number + " is between 1 and 100");
        }

        sc.close();

        // ============================================================
        // KEY TAKEAWAYS:
        // ============================================================
        // - if checks a boolean condition
        // - else if adds more conditions (checked in order)
        // - else is the fallback when nothing matches
        // - Conditions can be combined with && (AND), || (OR), ! (NOT)
        // - Always use .equals() for String comparison
        // - Always use { } braces, even for single-line blocks
        // - Order matters in else-if chains — strictest condition first
        // ============================================================
    }
}
