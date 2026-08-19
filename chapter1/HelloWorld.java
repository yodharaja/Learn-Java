package chapter1;

/**
 * ============================================================
 * LESSON 1.1 — Anatomy of a Java Program & Execution Model
 * ============================================================
 *
 * How Java Works:
 *   1. You write Java source code in a file named `HelloWorld.java`.
 *   2. The Java Compiler (`javac`) compiles source code into Bytecode (`HelloWorld.class`).
 *   3. The Java Virtual Machine (`JVM`) interprets and executes the bytecode on any OS
 *      (Write Once, Run Anywhere - WORA).
 *
 * JDK vs JRE vs JVM:
 *   - JDK (Java Development Kit)  = JRE + Development Tools (`javac`, debugger, doc tools).
 *   - JRE (Java Runtime Environment) = JVM + Standard Java Class Libraries.
 *   - JVM (Java Virtual Machine)     = Executes the compiled bytecode on native hardware.
 *
 * Line-by-Line Breakdown of the Main Method:
 *   - `public`   : Access modifier allowing the JVM launcher to call this method from outside.
 *   - `static`   : Allows the JVM to invoke `main()` without first creating an instance of the class.
 *   - `void`     : Method does not return any value to the caller.
 *   - `main`     : Standard entry-point method name recognized by the JVM.
 *   - `String[] args` : Array of command-line string arguments passed during launch.
 *   - `System.out.println()` :
 *       * `System`  : Built-in class in java.lang package providing system facilities.
 *       * `out`     : Static PrintStream object representing standard output (the console).
 *       * `println()`: Method that prints the argument followed by a newline.
 */
public class HelloWorld {

    public static void main(String[] args) {
        System.out.println("==========================================");
        System.out.println("  Hello, World! Welcome to Java Mastery!  ");
        System.out.println("==========================================");

        // Printing with and without newlines:
        System.out.print("1. print() does NOT add a newline. ");
        System.out.println("2. println() DOES add a newline.");

        // Command line arguments check:
        if (args.length > 0) {
            System.out.println("Command line arguments received:");
            for (int i = 0; i < args.length; i++) {
                System.out.printf("  args[%d] = %s%n", i, args[i]);
            }
        } else {
            System.out.println("No command-line arguments provided (Run with: java chapter1.HelloWorld arg1 arg2)");
        }

        // ============================================================
        // KEY TAKEAWAYS:
        // ============================================================
        // - Every standalone Java app starts execution at `public static void main(String[] args)`.
        // - Java is case-sensitive: `system` is not `System`, `Main` is not `main`.
        // - File name MUST match the public class name (`HelloWorld.java` -> `public class HelloWorld`).
        // ============================================================
    }
}
