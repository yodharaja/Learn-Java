package chapter8;

import java.util.Scanner;

/**
 * ============================================================
 * LESSON 8.1 — Exception Handling: Try, Catch, Finally
 * ============================================================
 *
 * An EXCEPTION is an abnormal event that occurs during program execution
 * and disrupts the normal flow of instructions.
 *
 * Why handle exceptions?
 *   - Prevent sudden app crashes.
 *   - Gracefully recover or show user-friendly error messages.
 *   - Clean up resources (files, network connections, database handles).
 *
 * Java Exception Hierarchy:
 *   Throwable
 *   ├── Error (Severe system issues — e.g., OutOfMemoryError, StackOverflowError)
 *   └── Exception (Recoverable conditions)
 *       ├── RuntimeException (Unchecked — NullPointerException, ArithmeticException)
 *       └── Checked Exceptions (IOException, SQLException, ClassNotFoundException)
 *
 * Blocks:
 *   - `try`: Code that might throw an exception.
 *   - `catch`: Code that handles a specific exception type.
 *   - `finally`: Code that ALWAYS runs, whether an exception occurred or not!
 */
public class TryCatch {

    public static void main(String[] args) {
        System.out.println("=== 1. BASIC TRY-CATCH (ARITHMETIC EXCEPTION) ===");
        try {
            int a = 10;
            int b = 0;
            int result = a / b; // Throws ArithmeticException: / by zero
            System.out.println("Result: " + result); // Skipped!
        } catch (ArithmeticException e) {
            System.out.println("  ❌ Caught ArithmeticException: Cannot divide by zero!");
            System.out.println("  Message: " + e.getMessage());
        }


        System.out.println("\n=== 2. MULTIPLE CATCH BLOCKS ===");
        // Rule: More SPECIFIC exceptions must be caught BEFORE general ones (Exception)!
        try {
            String text = null;
            // System.out.println(text.length()); // Would throw NullPointerException

            int[] arr = new int[3];
            arr[5] = 42; // Throws ArrayIndexOutOfBoundsException
        } catch (NullPointerException e) {
            System.out.println("  ❌ Caught NullPointerException: Object reference is null!");
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("  ❌ Caught ArrayIndexOutOfBoundsException: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("  ❌ Caught Generic Exception: " + e.getClass().getSimpleName());
        }


        System.out.println("\n=== 3. MULTI-CATCH BLOCK (Java 7+ Syntax) ===");
        // Catch multiple unrelated exceptions in a single catch block using the pipe '|' operator
        try {
            String numStr = "abc";
            int val = Integer.parseInt(numStr); // Throws NumberFormatException
        } catch (NumberFormatException | NullPointerException e) {
            System.out.println("  ❌ Caught parse/null error: " + e.getMessage());
        }


        System.out.println("\n=== 4. THE 'finally' BLOCK (GUARANTEED EXECUTION) ===");
        Scanner sc = null;
        try {
            sc = new Scanner("42 Hello");
            System.out.println("  Reading token: " + sc.nextInt());
            // Even if an exception happens or 'return' is hit, finally will run!
        } catch (Exception e) {
            System.out.println("  Caught exception during reading.");
        } finally {
            System.out.println("  [Finally Block] Executing guaranteed cleanup steps...");
            if (sc != null) {
                sc.close();
                System.out.println("  [Finally Block] Scanner resource safely closed ✓");
            }
        }


        System.out.println("\n=== 5. RETRIEVING EXCEPTION DETAILS ===");
        try {
            int[] data = {1, 2};
            int badAccess = data[10];
        } catch (Exception e) {
            System.out.println("  Exception Class  : " + e.getClass().getName());
            System.out.println("  Message          : " + e.getMessage());
            System.out.print("  Stack Trace Top  : ");
            StackTraceElement[] stack = e.getStackTrace();
            if (stack.length > 0) {
                System.out.println(stack[0]);
            }
        }

        // ============================================================
        // KEY TAKEAWAYS:
        // ============================================================
        // - `try` wraps dangerous operations.
        // - `catch` catches specific exception types (order specific -> general).
        // - `finally` executes unconditionally (used for closing resources).
        // - Multi-catch (`catch (A | B e)`) reduces code duplication.
        // ============================================================
    }
}
