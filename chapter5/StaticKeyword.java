package chapter5;

/**
 * ============================================================
 * LESSON 5.4 — The 'static' Keyword
 * ============================================================
 *
 * In Java, 'static' means "belongs to the CLASS itself, rather than
 * to any specific object instance".
 *
 * What can be static?
 *   1. Static Variables: Shared across ALL instances of the class (stored in Metaspace/Class area).
 *   2. Static Methods: Can be invoked without creating an instance (`Math.sqrt()`, `Integer.parseInt()`).
 *   3. Static Blocks: Static initialization code that runs ONCE when the class is first loaded.
 *   4. Static Nested Classes (covered in advanced chapters).
 *
 * Critical Rules:
 *   - Static methods CANNOT access instance variables (`this.name`) or instance methods directly.
 *   - Instance methods CAN access both static and instance members freely.
 */

class Counter {
    // Instance variable: each Counter object has its OWN 'instanceCount'
    int instanceCount = 0;

    // Static variable: SHARED across ALL Counter objects in memory
    static int globalCount = 0;

    // Constant static variable (often used with 'final')
    public static final String APP_NAME = "CounterApp v1.0";

    // Static initialization block: runs ONCE when JVM loads the Counter class
    static {
        System.out.println("  [System] Counter class loaded into JVM memory!");
        globalCount = 1000; // initialize starting ID offset
    }

    public Counter() {
        instanceCount++;
        globalCount++;
    }

    // Instance method: can access both instance and static members
    public void showCounts() {
        System.out.printf("  Instance count: %d | Global shared count: %d%n",
                this.instanceCount, globalCount);
    }

    // Static method: can only access static members
    public static void displayGlobalSummary() {
        System.out.println("  Total objects created so far (via static method): " + globalCount);
        // System.out.println(this.instanceCount); // ERROR: 'this' cannot be referenced from static context
    }
}

// Utility class: contains only static methods and constants
class MathUtil {
    // Private constructor prevents anyone from instantiating this utility class
    private MathUtil() {}

    public static final double PI = 3.141592653589793;

    public static int square(int n) {
        return n * n;
    }

    public static int cube(int n) {
        return n * n * n;
    }

    public static double circleCircumference(double radius) {
        return 2 * PI * radius;
    }
}

public class StaticKeyword {

    public static void main(String[] args) {
        System.out.println("=== 1. ACCESSING STATIC MEMBERS WITHOUT INSTANTIATION ===");
        System.out.println("App Name: " + Counter.APP_NAME);
        Counter.displayGlobalSummary();

        System.out.println("\n=== 2. INSTANCE VS STATIC VARIABLES ===");
        Counter c1 = new Counter();
        System.out.print("After creating c1 -> ");
        c1.showCounts();

        Counter c2 = new Counter();
        System.out.print("After creating c2 -> ");
        c2.showCounts();

        Counter c3 = new Counter();
        System.out.print("After creating c3 -> ");
        c3.showCounts();

        System.out.println("\nNotice:");
        System.out.println("  Each object's 'instanceCount' is always 1 (independent).");
        System.out.println("  The shared 'globalCount' incremented to 1003 (shared across all).");

        System.out.println("\n=== 3. CALLING STATIC UTILITY METHODS ===");
        System.out.println("5 squared = " + MathUtil.square(5));
        System.out.println("3 cubed   = " + MathUtil.cube(3));
        System.out.printf("Circumference (r=7): %.2f%n", MathUtil.circleCircumference(7));

        // ============================================================
        // KEY TAKEAWAYS:
        // ============================================================
        // - 'static' belongs to the class, not individual objects.
        // - Use static variables for counters, global configs, or shared state.
        // - Use static methods for pure utility functions (like Math.pow, Arrays.sort).
        // - Static methods cannot use 'this' or reference non-static fields directly.
        // ============================================================
    }
}
