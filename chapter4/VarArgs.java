package chapter4;

/**
 * ============================================================
 * LESSON 4.4 — Variable Arguments (Varargs)
 * ============================================================
 *
 * Varargs let a method accept ANY NUMBER of arguments of the same type.
 *
 * Syntax: type... paramName
 *   - The three dots (...) after the type
 *   - Inside the method, it behaves like an array
 *
 * Rules:
 *   1. Only ONE varargs parameter per method
 *   2. It must be the LAST parameter
 *   3. You can pass 0 or more arguments
 */
public class VarArgs {

    // ============================================================
    // 1. BASIC VARARGS
    // ============================================================
    // 'int... numbers' means "accept any number of ints"
    static int sum(int... numbers) {
        // 'numbers' is treated as an int[] array inside the method
        int total = 0;
        for (int n : numbers) {
            total += n;
        }
        return total;
    }

    // ============================================================
    // 2. VARARGS WITH OTHER PARAMETERS
    // ============================================================
    // Varargs must be the LAST parameter
    static void printScores(String studentName, int... scores) {
        System.out.print(studentName + "'s scores: ");
        int total = 0;
        for (int score : scores) {
            System.out.print(score + " ");
            total += score;
        }
        double avg = scores.length > 0 ? (double) total / scores.length : 0;
        System.out.printf("(avg: %.1f)%n", avg);
    }

    // ============================================================
    // 3. FINDING MIN AND MAX
    // ============================================================
    static int max(int first, int... rest) {
        // Requiring 'first' ensures at least one argument
        int max = first;
        for (int n : rest) {
            if (n > max) max = n;
        }
        return max;
    }

    static int min(int first, int... rest) {
        int min = first;
        for (int n : rest) {
            if (n < min) min = n;
        }
        return min;
    }

    // ============================================================
    // 4. STRING VARARGS
    // ============================================================
    static String join(String separator, String... parts) {
        if (parts.length == 0) return "";

        StringBuilder result = new StringBuilder(parts[0]);
        for (int i = 1; i < parts.length; i++) {
            result.append(separator).append(parts[i]);
        }
        return result.toString();
    }

    // ============================================================
    // 5. CHECKING ALL / ANY CONDITIONS
    // ============================================================
    static boolean allPositive(int... numbers) {
        for (int n : numbers) {
            if (n <= 0) return false;
        }
        return numbers.length > 0;  // empty = false
    }

    static boolean anyNegative(int... numbers) {
        for (int n : numbers) {
            if (n < 0) return true;
        }
        return false;
    }

    // ============================================================
    // 6. VARARGS WITH OVERLOADING
    // ============================================================
    // Be careful! This can cause ambiguity.

    static void show(String msg) {
        System.out.println("Single string: " + msg);
    }

    static void show(String... msgs) {
        System.out.println("Varargs: " + msgs.length + " strings");
        for (String m : msgs) {
            System.out.println("  - " + m);
        }
    }

    // ============================================================
    // 7. PRACTICAL EXAMPLE: Logger
    // ============================================================
    static void log(String level, String message, Object... extras) {
        System.out.print("[" + level.toUpperCase() + "] " + message);
        if (extras.length > 0) {
            System.out.print(" | Details: ");
            for (Object extra : extras) {
                System.out.print(extra + " ");
            }
        }
        System.out.println();
    }


    // ============================================================
    // MAIN
    // ============================================================
    public static void main(String[] args) {

        // --- 1. Basic varargs ---
        System.out.println("=== BASIC VARARGS ===");
        System.out.println("sum()           = " + sum());              // 0 args → 0
        System.out.println("sum(5)          = " + sum(5));             // 1 arg → 5
        System.out.println("sum(1, 2, 3)    = " + sum(1, 2, 3));      // 3 args → 6
        System.out.println("sum(10,20,30,40)= " + sum(10, 20, 30, 40)); // 4 args → 100

        // You can also pass an array!
        int[] numbers = {1, 2, 3, 4, 5};
        System.out.println("sum(array)      = " + sum(numbers));       // 15


        // --- 2. With other parameters ---
        System.out.println("\n=== WITH OTHER PARAMETERS ===");
        printScores("Yodha", 95, 88, 92, 78);
        printScores("Raja", 80, 85);
        printScores("New Student");  // 0 scores — that's fine!


        // --- 3. Min and Max ---
        System.out.println("\n=== MIN & MAX ===");
        System.out.println("max(5, 3, 8, 1, 9) = " + max(5, 3, 8, 1, 9));
        System.out.println("min(5, 3, 8, 1, 9) = " + min(5, 3, 8, 1, 9));
        System.out.println("max(42)             = " + max(42));  // single value


        // --- 4. String join ---
        System.out.println("\n=== STRING JOIN ===");
        System.out.println(join(", ", "Apple", "Banana", "Cherry"));
        System.out.println(join(" → ", "Start", "Middle", "End"));
        System.out.println(join("-", "2026", "08", "20"));


        // --- 5. All/Any checks ---
        System.out.println("\n=== ALL / ANY CHECKS ===");
        System.out.println("allPositive(1,2,3) = " + allPositive(1, 2, 3));    // true
        System.out.println("allPositive(1,-2,3)= " + allPositive(1, -2, 3));   // false
        System.out.println("anyNegative(1,2,3) = " + anyNegative(1, 2, 3));    // false
        System.out.println("anyNegative(1,-2,3)= " + anyNegative(1, -2, 3));   // true


        // --- 6. Overloading with varargs ---
        System.out.println("\n=== OVERLOADING ===");
        // show("Hello");  // AMBIGUOUS! Both versions match. Uncomment to see error.
        show("A", "B", "C");  // Calls varargs version (3 args)
        // Java prefers the SPECIFIC match over varargs when possible


        // --- 7. Logger ---
        System.out.println("\n=== LOGGER ===");
        log("info", "Application started");
        log("warning", "Low memory", "used: 90%", "free: 256MB");
        log("error", "File not found", "path: /data/config.json", "code: 404");


        // ============================================================
        // KEY TAKEAWAYS:
        // ============================================================
        // - Syntax: type... name (three dots after the type)
        // - Inside the method, varargs = array
        // - Only ONE varargs per method, must be LAST parameter
        // - Can pass 0, 1, or many arguments
        // - Can also pass an actual array
        // - Be careful with overloading + varargs (ambiguity!)
        // - Use cases: sum(), max(), join(), logging, formatting
        // ============================================================
    }
}
