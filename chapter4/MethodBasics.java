package chapter4;

/**
 * ============================================================
 * LESSON 4.1 — Method Basics
 * ============================================================
 *
 * A METHOD is a reusable block of code that performs a specific task.
 * Think of it as a "mini-program" inside your program.
 *
 * Why use methods?
 *   1. REUSABILITY — write once, call many times
 *   2. ORGANIZATION — break complex code into smaller pieces
 *   3. READABILITY — methods have descriptive names
 *   4. DEBUGGING — easier to find and fix bugs
 *
 * Syntax:
 *   accessModifier returnType methodName(parameters) {
 *       // body
 *       return value;  // if returnType is not void
 *   }
 *
 * Parts:
 *   - accessModifier: public, private, etc. (more in Chapter 5)
 *   - returnType: what the method gives back (int, String, void, etc.)
 *   - methodName: what you call it (use camelCase!)
 *   - parameters: inputs the method needs (can be empty)
 *   - body: the code that runs
 *   - return: the value sent back to the caller
 */
public class MethodBasics {

    // ============================================================
    // 1. A SIMPLE METHOD (no parameters, no return)
    // ============================================================
    // 'static' because we call it from main (which is static)
    // 'void' means it returns nothing
    static void greet() {
        System.out.println("Hello! Welcome to Java Methods! 👋");
    }

    // ============================================================
    // 2. METHOD WITH PARAMETERS (inputs)
    // ============================================================
    // Parameters are variables that receive values when the method is called
    static void greetByName(String name) {
        System.out.println("Hello, " + name + "! Nice to meet you.");
    }

    // Multiple parameters
    static void printSum(int a, int b) {
        System.out.println(a + " + " + b + " = " + (a + b));
    }

    // ============================================================
    // 3. METHOD WITH RETURN VALUE
    // ============================================================
    // Return type is 'int' — this method gives back an integer
    static int add(int a, int b) {
        return a + b;  // sends the result back to the caller
        // Code after 'return' is UNREACHABLE and won't compile
    }

    static double calculateArea(double radius) {
        return Math.PI * radius * radius;
    }

    static boolean isEven(int number) {
        return number % 2 == 0;  // returns true or false
    }

    static String getGrade(int score) {
        if (score >= 90) return "A";
        if (score >= 80) return "B";
        if (score >= 70) return "C";
        if (score >= 60) return "D";
        return "F";
    }

    // ============================================================
    // 4. METHOD WITH MULTIPLE RETURN STATEMENTS
    // ============================================================
    // Only ONE return executes — the first one reached
    static String checkAge(int age) {
        if (age < 0) {
            return "Invalid age!";    // returns here if age < 0
        }
        if (age < 13) {
            return "Child";           // returns here if age < 13
        }
        if (age < 18) {
            return "Teenager";
        }
        return "Adult";               // default return
    }

    // ============================================================
    // 5. PASS BY VALUE (Important concept!)
    // ============================================================
    // Java ALWAYS passes by value.
    // For primitives: a COPY of the value is passed.
    // Changing the parameter does NOT change the original variable.
    static void tryToChange(int number) {
        number = 999;  // This changes the LOCAL copy only!
        System.out.println("  Inside method: number = " + number);
    }

    // For arrays/objects: a copy of the REFERENCE is passed.
    // So changes to the CONTENTS affect the original!
    static void modifyArray(int[] arr) {
        arr[0] = 999;  // This DOES change the original array!
        System.out.println("  Inside method: arr[0] = " + arr[0]);
    }

    // ============================================================
    // 6. UTILITY METHODS (practical examples)
    // ============================================================
    static int max(int a, int b) {
        return (a > b) ? a : b;
    }

    static int min(int a, int b) {
        return (a < b) ? a : b;
    }

    static int abs(int n) {
        return (n < 0) ? -n : n;
    }

    static void printArray(int[] arr) {
        System.out.print("[");
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i]);
            if (i < arr.length - 1) System.out.print(", ");
        }
        System.out.println("]");
    }

    static double average(int[] arr) {
        int sum = 0;
        for (int num : arr) {
            sum += num;
        }
        return (double) sum / arr.length;
    }


    // ============================================================
    // MAIN METHOD — Entry point, calls all the methods above
    // ============================================================
    public static void main(String[] args) {

        // --- 1. Calling a simple method ---
        System.out.println("=== SIMPLE METHOD ===");
        greet();          // Call the method by its name
        greet();          // Call it again — reusable!


        // --- 2. Calling methods with parameters ---
        System.out.println("\n=== METHODS WITH PARAMETERS ===");
        greetByName("Yodha");       // "Yodha" is the ARGUMENT (actual value)
        greetByName("Raja");        // Different argument, same method

        printSum(10, 20);           // Arguments: 10 and 20
        printSum(100, 200);

        // TERMINOLOGY:
        // Parameter = variable in method definition (placeholder)
        // Argument  = actual value passed when calling the method


        // --- 3. Using return values ---
        System.out.println("\n=== METHODS WITH RETURN VALUES ===");

        int result = add(15, 25);    // Store the returned value
        System.out.println("15 + 25 = " + result);

        // You can also use the return value directly:
        System.out.println("7 + 3 = " + add(7, 3));

        double area = calculateArea(5.0);
        System.out.printf("Circle area (r=5): %.2f%n", area);

        System.out.println("Is 42 even? " + isEven(42));    // true
        System.out.println("Is 17 even? " + isEven(17));    // false

        System.out.println("Score 85 → Grade " + getGrade(85));  // B


        // --- 4. Multiple returns ---
        System.out.println("\n=== MULTIPLE RETURNS ===");
        System.out.println("Age 10: " + checkAge(10));   // Child
        System.out.println("Age 15: " + checkAge(15));   // Teenager
        System.out.println("Age 25: " + checkAge(25));   // Adult
        System.out.println("Age -1: " + checkAge(-1));   // Invalid


        // --- 5. Pass by value demo ---
        System.out.println("\n=== PASS BY VALUE ===");

        int myNum = 42;
        System.out.println("Before method: myNum = " + myNum);
        tryToChange(myNum);
        System.out.println("After method:  myNum = " + myNum);  // Still 42!

        System.out.println("\nArrays are different (reference):");
        int[] myArr = {1, 2, 3};
        System.out.print("Before: "); printArray(myArr);
        modifyArray(myArr);
        System.out.print("After:  "); printArray(myArr);  // arr[0] is now 999!


        // --- 6. Using utility methods ---
        System.out.println("\n=== UTILITY METHODS ===");
        System.out.println("max(10, 20) = " + max(10, 20));
        System.out.println("min(10, 20) = " + min(10, 20));
        System.out.println("abs(-42) = " + abs(-42));

        int[] scores = {85, 92, 78, 95, 88};
        System.out.print("Scores: "); printArray(scores);
        System.out.println("Average: " + average(scores));


        // --- 7. Methods calling other methods ---
        System.out.println("\n=== METHODS CALLING METHODS ===");
        int[] data = {23, 45, 12, 67, 34};
        System.out.print("Data: "); printArray(data);
        System.out.println("Max: " + max(max(max(max(data[0], data[1]), data[2]), data[3]), data[4]));
        // ^ Nested calls! Each max() returns a value used by the next.
        // In Chapter 4.4 we'll see a cleaner way with varargs.


        // ============================================================
        // KEY TAKEAWAYS:
        // ============================================================
        // - Methods = reusable blocks of code
        // - void = returns nothing; other types must use 'return'
        // - Parameters are inputs; return value is the output
        // - Parameter = placeholder, Argument = actual value
        // - Java is PASS BY VALUE:
        //     - Primitives: copy of value (original unchanged)
        //     - Objects/arrays: copy of reference (contents can change)
        // - Methods must be 'static' to be called from 'static main'
        // - Name methods using camelCase: calculateArea, isEven, getGrade
        // ============================================================
    }
}
