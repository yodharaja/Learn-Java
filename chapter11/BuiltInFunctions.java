package chapter11;

import java.time.LocalDateTime;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

/**
 * ============================================================
 * LESSON 11.2 — Standard Built-in Functional Interfaces
 * ============================================================
 *
 * Java provides standard functional interfaces in `java.util.function`.
 *
 * The Core 4 Interfaces:
 * ┌───────────────┬────────────────────┬──────────────┬────────────────────────────────┐
 * │ Interface     │ Method Signature   │ Return Type  │ Typical Use Case               │
 * ├───────────────┼────────────────────┼──────────────┼────────────────────────────────┤
 * │ Predicate<T>  │ boolean test(T t)  │ boolean      │ Filtering / boolean conditions │
 * │ Function<T,R> │ R apply(T t)       │ R            │ Transforming / mapping data    │
 * │ Consumer<T>   │ void accept(T t)   │ void         │ Actions with side-effects/print│
 * │ Supplier<T>   │ T get()            │ T            │ Factory generation / lazy value│
 * └───────────────┴────────────────────┴──────────────┴────────────────────────────────┘
 *
 * Binary Variants:
 *   - `BiPredicate<T, U>`, `BiFunction<T, U, R>`, `BiConsumer<T, U>`
 */
public class BuiltInFunctions {

    public static void main(String[] args) {
        System.out.println("=== 1. PREDICATE<T> — CONDITIONAL FILTERING ===");
        Predicate<Integer> isEven = n -> n % 2 == 0;
        Predicate<Integer> isPositive = n -> n > 0;

        // Chaining predicates with .and(), .or(), .negate()
        Predicate<Integer> isPositiveEven = isEven.and(isPositive);

        System.out.println("Is 42 positive & even? " + isPositiveEven.test(42));
        System.out.println("Is -4 positive & even? " + isPositiveEven.test(-4));
        System.out.println("Is 13 odd? (negate)    " + isEven.negate().test(13));


        System.out.println("\n=== 2. FUNCTION<T, R> — DATA TRANSFORMATION ===");
        // Transforms String into Integer length
        Function<String, Integer> stringLength = String::length;
        // Transforms Integer into double Tax
        Function<Integer, Double> calculateTax = amount -> amount * 0.15;

        // Function composition with andThen()
        Function<String, Double> lengthTaxPipeline = stringLength.andThen(calculateTax);

        System.out.println("Length of 'Antigravity': " + stringLength.apply("Antigravity"));
        System.out.println("Composed Pipeline for 'JavaMaster': " + lengthTaxPipeline.apply("JavaMaster"));


        System.out.println("\n=== 3. CONSUMER<T> — ACCEPT & EXECUTE ===");
        Consumer<String> printer = msg -> System.out.println("  [Log] " + msg);
        Consumer<String> alertPrinter = msg -> System.out.println("  🔔 ALERT: " + msg);

        // Chaining consumers with andThen()
        Consumer<String> dualLog = printer.andThen(alertPrinter);
        dualLog.accept("System memory reached 88% capacity!");


        System.out.println("\n=== 4. SUPPLIER<T> — FACTORY & LAZY EVALUATION ===");
        // Generates / supplies values on demand with no input
        Supplier<String> timestampSupplier = () -> LocalDateTime.now().toString();
        Supplier<Double> randomScore = () -> Math.round(Math.random() * 1000) / 10.0;

        System.out.println("Generated Timestamp: " + timestampSupplier.get());
        System.out.println("Generated Score    : " + randomScore.get());


        System.out.println("\n=== 5. BIFUNCTION<T, U, R> — TWO ARGUMENTS ===");
        BiFunction<String, Integer, String> userFormatter =
                (name, age) -> String.format("User %s is %d years old (Born ~%d)", name, age, 2026 - age);

        System.out.println(userFormatter.apply("Yodha Raja", 21));

        // ============================================================
        // KEY TAKEAWAYS:
        // ============================================================
        // - `Predicate<T>`: Takes 1 item -> returns boolean.
        // - `Function<T, R>`: Takes 1 item -> returns mapped result R.
        // - `Consumer<T>`: Takes 1 item -> executes void action.
        // - `Supplier<T>`: Takes 0 items -> returns fresh object T.
        // ============================================================
    }
}
