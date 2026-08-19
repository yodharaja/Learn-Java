package chapter4;

/**
 * ============================================================
 * LESSON 4.2 — Method Overloading
 * ============================================================
 *
 * Method overloading = defining MULTIPLE METHODS with the SAME NAME
 * but DIFFERENT parameter lists.
 *
 * Java knows which method to call based on:
 *   1. Number of parameters
 *   2. Type of parameters
 *   3. Order of parameter types
 *
 * NOT based on:
 *   ✗ Return type (won't compile if only return type differs)
 *   ✗ Parameter names (names don't matter, types do)
 *
 * Why overload?
 *   - Same action, different input types
 *   - Cleaner API — user doesn't need to remember many method names
 *   - Example: System.out.println() is overloaded for int, double, String, etc.!
 */
public class MethodOverloading {

    // ============================================================
    // 1. OVERLOADING BY NUMBER OF PARAMETERS
    // ============================================================

    // No parameters
    static int add() {
        return 0;
    }

    // Two parameters
    static int add(int a, int b) {
        return a + b;
    }

    // Three parameters
    static int add(int a, int b, int c) {
        return a + b + c;
    }


    // ============================================================
    // 2. OVERLOADING BY TYPE OF PARAMETERS
    // ============================================================

    // Two ints
    static void display(int a, int b) {
        System.out.println("Two ints: " + a + ", " + b);
    }

    // Two doubles
    static void display(double a, double b) {
        System.out.println("Two doubles: " + a + ", " + b);
    }

    // Two strings
    static void display(String a, String b) {
        System.out.println("Two strings: " + a + ", " + b);
    }


    // ============================================================
    // 3. OVERLOADING BY ORDER OF PARAMETER TYPES
    // ============================================================

    static void info(String name, int age) {
        System.out.println(name + " is " + age + " years old");
    }

    static void info(int age, String name) {
        System.out.println("Age " + age + ": " + name);
    }


    // ============================================================
    // 4. REAL-WORLD EXAMPLE: Area Calculator
    // ============================================================

    // Area of a square (1 parameter)
    static double area(double side) {
        System.out.print("  Square (side=" + side + "): ");
        return side * side;
    }

    // Area of a rectangle (2 parameters)
    static double area(double length, double width) {
        System.out.print("  Rectangle (" + length + "x" + width + "): ");
        return length * width;
    }

    // Area of a circle (1 parameter but different name context — use int to distinguish)
    // Note: We can't overload with same parameter types, so let's use a different approach
    static double circleArea(double radius) {
        System.out.print("  Circle (r=" + radius + "): ");
        return Math.PI * radius * radius;
    }

    // Area of a triangle (3 parameters — base, height)
    static double area(double base, double height, boolean isTriangle) {
        System.out.print("  Triangle (b=" + base + ", h=" + height + "): ");
        return 0.5 * base * height;
    }


    // ============================================================
    // 5. REAL-WORLD EXAMPLE: String Formatter
    // ============================================================

    static String format(String text) {
        return text.trim();  // just trim
    }

    static String format(String text, boolean uppercase) {
        text = text.trim();
        return uppercase ? text.toUpperCase() : text.toLowerCase();
    }

    static String format(String text, int maxLength) {
        text = text.trim();
        if (text.length() > maxLength) {
            return text.substring(0, maxLength) + "...";
        }
        return text;
    }


    // ============================================================
    // 6. TYPE PROMOTION IN OVERLOADING
    // ============================================================

    static void show(int x) {
        System.out.println("  int version: " + x);
    }

    static void show(double x) {
        System.out.println("  double version: " + x);
    }

    // When no exact match exists, Java PROMOTES the type:
    // byte → short → int → long → float → double


    // ============================================================
    // MAIN — Demonstrate everything
    // ============================================================
    public static void main(String[] args) {

        // --- 1. Different number of parameters ---
        System.out.println("=== BY NUMBER OF PARAMETERS ===");
        System.out.println("add()         = " + add());
        System.out.println("add(5, 3)     = " + add(5, 3));
        System.out.println("add(5, 3, 2)  = " + add(5, 3, 2));
        // Java picks the right version based on argument count!


        // --- 2. Different types ---
        System.out.println("\n=== BY TYPE OF PARAMETERS ===");
        display(10, 20);              // calls int version
        display(3.14, 2.71);          // calls double version
        display("Hello", "World");    // calls String version


        // --- 3. Different order ---
        System.out.println("\n=== BY ORDER OF TYPES ===");
        info("Yodha", 20);    // calls (String, int) version
        info(20, "Yodha");    // calls (int, String) version


        // --- 4. Area calculator ---
        System.out.println("\n=== AREA CALCULATOR ===");
        System.out.println(area(5));                    // Square: 25.0
        System.out.println(area(10, 4));                // Rectangle: 40.0
        System.out.println(circleArea(7));              // Circle: ~153.94
        System.out.println(area(6, 8, true));           // Triangle: 24.0


        // --- 5. String formatter ---
        System.out.println("\n=== STRING FORMATTER ===");
        System.out.println("format(\"  Hello  \")          = \"" + format("  Hello  ") + "\"");
        System.out.println("format(\"  Hello  \", true)    = \"" + format("  Hello  ", true) + "\"");
        System.out.println("format(\"Hello World!\", 5)    = \"" + format("Hello World!", 5) + "\"");


        // --- 6. Type promotion ---
        System.out.println("\n=== TYPE PROMOTION ===");
        show(42);        // exact match → int version
        show(3.14);      // exact match → double version

        byte b = 5;
        show(b);         // no byte version → promoted to int
        // Promotion chain: byte → short → int → long → float → double

        // What if we pass a float?
        float f = 2.5f;
        show(f);         // no float version → promoted to double


        // --- 7. What WON'T compile ---
        System.out.println("\n=== WHAT WON'T WORK ===");
        System.out.println("These would cause compile errors:");
        System.out.println("  ✗ Overloading by return type only");
        System.out.println("  ✗ Same parameter types, different names only");
        /*
         * DOES NOT COMPILE:
         *
         * static int  calculate(int a) { return a; }
         * static double calculate(int a) { return a; }
         * // ERROR: Same parameters (int), only return type differs
         *
         * static void print(int x) { ... }
         * static void print(int y) { ... }
         * // ERROR: Same signature! Parameter names don't matter
         */


        // ============================================================
        // KEY TAKEAWAYS:
        // ============================================================
        // - Same method name + different parameters = overloading
        // - Java decides which to call based on argument types
        // - Can differ by: number, type, or order of parameters
        // - CANNOT differ by: return type only or parameter names only
        // - If no exact match, Java promotes types (byte→int→double)
        // - println() is overloaded — that's why it handles int, String, etc.
        // - Makes your code cleaner — one name for related operations
        // ============================================================
    }
}
