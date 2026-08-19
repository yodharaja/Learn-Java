package chapter11;

/**
 * ============================================================
 * LESSON 11.1 — Lambda Expressions & Functional Interfaces
 * ============================================================
 *
 * Introduced in Java 8, LAMBDA EXPRESSIONS enable Functional Programming in Java.
 * A lambda is an anonymous function (has no name, but has parameters, a body, and a return type).
 *
 * Syntax:
 *   (parameters) -> { body }
 *
 * Syntax shortcuts:
 *   - Zero parameters: `() -> System.out.println("Hi")`
 *   - One parameter: `x -> x * 2` (parentheses optional)
 *   - Multiple parameters: `(a, b) -> a + b`
 *   - Single line body: curly braces `{}` and `return` keyword are optional.
 *
 * FUNCTIONAL INTERFACE:
 *   - An interface that contains EXACTLY ONE abstract method (Single Abstract Method - SAM).
 *   - Marked with optional `@FunctionalInterface` annotation (causes compiler error if >1 abstract method).
 *   - Lambdas can ONLY be used where a Functional Interface is expected.
 */

@FunctionalInterface
interface MathOperation {
    double compute(double a, double b);
}

@FunctionalInterface
interface StringTransformer {
    String transform(String input);
}

@FunctionalInterface
interface Greeter {
    void sayHello(String name);
}

public class LambdaBasics {

    public static void main(String[] args) {
        System.out.println("=== 1. CUSTOM FUNCTIONAL INTERFACES WITH LAMBDAS ===");

        // Implementing MathOperation via lambdas:
        MathOperation addition = (a, b) -> a + b;
        MathOperation subtraction = (a, b) -> a - b;
        MathOperation multiplication = (a, b) -> a * b;
        MathOperation division = (a, b) -> {
            if (b == 0) throw new ArithmeticException("Division by zero!");
            return a / b;
        };

        System.out.println("10 + 5 = " + addition.compute(10, 5));
        System.out.println("10 - 5 = " + subtraction.compute(10, 5));
        System.out.println("10 * 5 = " + multiplication.compute(10, 5));
        System.out.println("10 / 5 = " + division.compute(10, 5));


        System.out.println("\n=== 2. STRING TRANSFORMER LAMBDAS ===");
        StringTransformer shout = s -> s.toUpperCase() + "!!!";
        StringTransformer whisper = s -> s.toLowerCase() + "...";
        StringTransformer reverse = s -> new StringBuilder(s).reverse().toString();

        String greeting = "Hello Java";
        System.out.println("Shout   : " + shout.transform(greeting));
        System.out.println("Whisper : " + whisper.transform(greeting));
        System.out.println("Reverse : " + reverse.transform(greeting));


        System.out.println("\n=== 3. PASSING LAMBDAS AS ARGUMENTS TO METHODS ===");
        // Higher-Order Function: Method that accepts a lambda behavior as parameter
        executeOperation(20, 4, (x, y) -> Math.pow(x, y), "20 ^ 4 (Power)");
        executeOperation(48, 18, (x, y) -> (x + y) / 2.0, "Average");

        // Void functional interface
        Greeter greeter = name -> System.out.println("  👋 Welcome aboard, " + name + "!");
        greeter.sayHello("Yodha Raja");

        // ============================================================
        // KEY TAKEAWAYS:
        // ============================================================
        // - Lambdas treat functions as first-class citizens / method arguments.
        // - `@FunctionalInterface` declares a Single Abstract Method (SAM) contract.
        // - Lambdas eliminate boilerplate anonymous inner class code.
        // ============================================================
    }

    public static void executeOperation(double a, double b, MathOperation op, String description) {
        double result = op.compute(a, b);
        System.out.printf("  %s -> Result: %.2f%n", description, result);
    }
}
