package chapter3;

import java.util.Scanner;

/**
 * ============================================================
 * LESSON 3.2 — Switch-Case Statement
 * ============================================================
 *
 * Switch is an alternative to long if-else-if chains.
 * Best used when you're comparing ONE variable against MANY fixed values.
 *
 * Traditional syntax:
 *   switch (variable) {
 *       case value1:
 *           // code
 *           break;
 *       case value2:
 *           // code
 *           break;
 *       default:
 *           // fallback
 *   }
 *
 * Works with: int, byte, short, char, String, enums
 * Does NOT work with: long, float, double, boolean
 */
public class SwitchCase {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        // ============================================================
        // 1. BASIC SWITCH
        // ============================================================
        System.out.println("=== BASIC SWITCH ===");

        int dayNumber = 3;

        switch (dayNumber) {
            case 1:
                System.out.println("Monday");
                break;
            case 2:
                System.out.println("Tuesday");
                break;
            case 3:
                System.out.println("Wednesday");  // ← This runs
                break;
            case 4:
                System.out.println("Thursday");
                break;
            case 5:
                System.out.println("Friday");
                break;
            case 6:
                System.out.println("Saturday");
                break;
            case 7:
                System.out.println("Sunday");
                break;
            default:
                System.out.println("Invalid day number!");
        }


        // ============================================================
        // 2. WHY break IS IMPORTANT — FALL-THROUGH
        // ============================================================
        System.out.println("\n=== FALL-THROUGH (without break) ===");

        int month = 3;
        // Without break, execution "falls through" to the next case!
        switch (month) {
            case 1:
                System.out.println("January");
                // no break — falls through!
            case 2:
                System.out.println("February");
                // no break — falls through!
            case 3:
                System.out.println("March");    // prints this
                // no break — falls through!
            case 4:
                System.out.println("April");    // AND this!
                // no break — falls through!
            case 5:
                System.out.println("May");      // AND this!
                break;                          // finally stops here
            default:
                System.out.println("Other month");
        }
        // Output: March, April, May
        // This is usually a BUG! Always use break unless you want fall-through.


        // ============================================================
        // 3. INTENTIONAL FALL-THROUGH (Grouping cases)
        // ============================================================
        System.out.println("\n=== GROUPED CASES ===");

        // Sometimes fall-through is useful — grouping cases with the same action
        int monthNum = 7;
        String season;

        switch (monthNum) {
            case 12:
            case 1:
            case 2:
                season = "Winter ❄️";
                break;
            case 3:
            case 4:
            case 5:
                season = "Spring 🌸";
                break;
            case 6:
            case 7:
            case 8:
                season = "Summer ☀️";  // ← monthNum 7 matches here
                break;
            case 9:
            case 10:
            case 11:
                season = "Autumn 🍂";
                break;
            default:
                season = "Invalid month";
        }
        System.out.println("Month " + monthNum + " → " + season);


        // ============================================================
        // 4. SWITCH WITH STRINGS
        // ============================================================
        System.out.println("\n=== SWITCH WITH STRINGS ===");

        String command = "start";

        switch (command.toLowerCase()) {  // .toLowerCase() for case-insensitive matching
            case "start":
                System.out.println("Starting the engine... 🚀");
                break;
            case "stop":
                System.out.println("Engine stopped. 🛑");
                break;
            case "status":
                System.out.println("Engine is running. ✅");
                break;
            default:
                System.out.println("Unknown command: " + command);
        }


        // ============================================================
        // 5. SWITCH WITH CHAR
        // ============================================================
        System.out.println("\n=== SWITCH WITH CHAR ===");

        char grade = 'B';

        switch (grade) {
            case 'A':
                System.out.println("Excellent! 90-100%");
                break;
            case 'B':
                System.out.println("Good job! 80-89%");  // ← This runs
                break;
            case 'C':
                System.out.println("Average. 70-79%");
                break;
            case 'D':
                System.out.println("Below average. 60-69%");
                break;
            case 'F':
                System.out.println("Failed. Below 60%");
                break;
            default:
                System.out.println("Invalid grade.");
        }


        // ============================================================
        // 6. ENHANCED SWITCH (Java 14+) — Arrow Syntax
        // ============================================================
        System.out.println("\n=== ENHANCED SWITCH (Java 14+) ===");

        // The arrow syntax (->):
        //   - No need for 'break' — no fall-through!
        //   - Cleaner and shorter
        //   - Can be used as an expression (returns a value)

        int day = 4;

        // Switch as an EXPRESSION (returns a value)
        String dayName = switch (day) {
            case 1 -> "Monday";
            case 2 -> "Tuesday";
            case 3 -> "Wednesday";
            case 4 -> "Thursday";      // ← Returns this
            case 5 -> "Friday";
            case 6, 7 -> "Weekend!";   // Multiple values with comma
            default -> "Invalid";
        };
        System.out.println("Day " + day + " = " + dayName);

        // Enhanced switch with blocks (use 'yield' to return from a block)
        String dayType = switch (day) {
            case 1, 2, 3, 4, 5 -> {
                System.out.println("  (processing weekday...)");
                yield "Weekday";  // 'yield' returns a value from a block
            }
            case 6, 7 -> {
                System.out.println("  (processing weekend...)");
                yield "Weekend";
            }
            default -> "Invalid";
        };
        System.out.println("Day " + day + " is a " + dayType);


        // ============================================================
        // 7. WHEN TO USE SWITCH vs IF-ELSE
        // ============================================================
        /*
         * USE SWITCH when:
         *   - Comparing ONE variable against MANY specific values
         *   - Values are constants (1, 2, 3 or "A", "B", "C")
         *   - Clean, readable alternative to long if-else-if chains
         *
         * USE IF-ELSE when:
         *   - Conditions involve ranges (score > 90)
         *   - Conditions involve multiple variables (age > 18 && hasID)
         *   - Conditions use boolean logic (&&, ||, !)
         *   - Comparing with non-constant expressions
         */


        // ============================================================
        // 8. INTERACTIVE EXAMPLE
        // ============================================================
        System.out.println("\n=== TRY IT! ===");
        System.out.print("Enter an operator (+, -, *, /): ");
        String op = sc.nextLine();

        String meaning = switch (op) {
            case "+" -> "Addition";
            case "-" -> "Subtraction";
            case "*" -> "Multiplication";
            case "/" -> "Division";
            default -> "Unknown operator";
        };
        System.out.println(op + " means " + meaning);

        sc.close();

        // ============================================================
        // KEY TAKEAWAYS:
        // ============================================================
        // - switch compares ONE variable against many values
        // - ALWAYS use break to prevent fall-through (traditional style)
        // - Grouping cases = intentional fall-through
        // - Enhanced switch (->) has no fall-through, cleaner syntax
        // - Enhanced switch can be an expression (returns a value)
        // - Use 'yield' to return from a block in switch expressions
        // - Works with: int, byte, short, char, String, enum
        // ============================================================
    }
}
