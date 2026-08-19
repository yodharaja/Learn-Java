package chapter2;

import java.util.Scanner;

/**
 * ============================================================
 * EXERCISE 2 — Simple Calculator
 * ============================================================
 *
 * Build a calculator that:
 *   1. Asks the user for two numbers
 *   2. Asks for an operator (+, -, *, /, %)
 *   3. Performs the calculation
 *   4. Displays the result
 *
 * Concepts used from this chapter:
 *   - Data types (int/double)
 *   - Scanner (user input)
 *   - Operators (arithmetic)
 *   - String comparison (.equals())
 *   - Type casting (for division)
 *
 * TRY IT YOURSELF FIRST! Then compare with the solution below.
 * ============================================================
 */
public class Exercise2 {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        // ============================================================
        // STEP 1: Read two numbers from the user
        // ============================================================
        System.out.println("===========================");
        System.out.println("   SIMPLE CALCULATOR");
        System.out.println("===========================");

        System.out.print("Enter first number: ");
        double num1 = sc.nextDouble();

        System.out.print("Enter second number: ");
        double num2 = sc.nextDouble();

        // Consume the leftover newline (remember the pitfall from lesson 2.5!)
        sc.nextLine();

        // ============================================================
        // STEP 2: Read the operator
        // ============================================================
        System.out.print("Enter operator (+, -, *, /, %): ");
        String operator = sc.nextLine();

        // ============================================================
        // STEP 3: Perform the calculation
        // ============================================================
        double result = 0;
        boolean validOperator = true;

        // Using .equals() to compare strings (lesson 2.4!)
        if (operator.equals("+")) {
            result = num1 + num2;
        } else if (operator.equals("-")) {
            result = num1 - num2;
        } else if (operator.equals("*")) {
            result = num1 * num2;
        } else if (operator.equals("/")) {
            // Handle division by zero!
            if (num2 == 0) {
                System.out.println("ERROR: Cannot divide by zero!");
                sc.close();
                return; // exit the program
            }
            result = num1 / num2;
        } else if (operator.equals("%")) {
            if (num2 == 0) {
                System.out.println("ERROR: Cannot modulo by zero!");
                sc.close();
                return;
            }
            result = num1 % num2;
        } else {
            System.out.println("ERROR: Invalid operator '" + operator + "'");
            validOperator = false;
        }

        // ============================================================
        // STEP 4: Display the result
        // ============================================================
        if (validOperator) {
            // Using String.format for clean output (lesson 2.4!)
            System.out.println("---------------------------");
            System.out.printf("  %.2f %s %.2f = %.2f%n", num1, operator, num2, result);
            System.out.println("---------------------------");

            // Bonus: show if result is an integer or decimal
            if (result == (int) result) {
                System.out.println("  (Integer result: " + (int) result + ")");
            }
        }

        sc.close();

        // ============================================================
        // EXPECTED OUTPUT (example run):
        // ============================================================
        /*
         * ===========================
         *    SIMPLE CALCULATOR
         * ===========================
         * Enter first number: 15
         * Enter second number: 4
         * Enter operator (+, -, *, /, %): *
         * ---------------------------
         *   15.00 * 4.00 = 60.00
         * ---------------------------
         *   (Integer result: 60)
         */

        // ============================================================
        // BONUS CHALLENGES:
        // ============================================================
        // 1. Add power (^) operation using Math.pow(num1, num2)
        // 2. Make it loop so user can do multiple calculations
        //    (Hint: use a while loop — you'll learn this in Chapter 3!)
        // 3. Add square root option using Math.sqrt()
        // ============================================================
    }
}
