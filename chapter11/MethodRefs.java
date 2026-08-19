package chapter11;

import java.util.Arrays;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * ============================================================
 * LESSON 11.5 — Method References (`::`)
 * ============================================================
 *
 * METHOD REFERENCES are compact, easy-to-read lambda expressions
 * for methods that already have a name.
 *
 * 4 Kinds of Method References:
 * ┌────────────────────────────────────┬─────────────────────────────┬──────────────────────────┐
 * │ Type                               │ Syntax                      │ Equivalent Lambda        │
 * ├────────────────────────────────────┼─────────────────────────────┼──────────────────────────┤
 * │ 1. Static Method Reference         │ `ClassName::staticMethod`   │ `(x) -> Class.method(x)` │
 * │ 2. Instance Method (of an object)  │ `instance::instanceMethod`  │ `(x) -> obj.method(x)`   │
 * │ 3. Instance Method (arbitrary obj) │ `ClassName::instanceMethod` │ `(obj) -> obj.method()`  │
 * │ 4. Constructor Reference           │ `ClassName::new`            │ `() -> new ClassName()`  │
 * └────────────────────────────────────┴─────────────────────────────┴──────────────────────────┘
 */

class AccountEntity {
    private String id;
    private double balance;

    public AccountEntity() {
        this("ACC-DEFAULT", 0.0);
    }

    public AccountEntity(String id, double balance) {
        this.id = id;
        this.balance = balance;
    }

    public String getId() { return id; }
    public double getBalance() { return balance; }

    public static boolean isSolvent(AccountEntity acc) {
        return acc.getBalance() > 0;
    }

    public void printSummary() {
        System.out.printf("  Account %s -> Balance: $%,.2f%n", id, balance);
    }
}

public class MethodRefs {

    public static void main(String[] args) {
        List<String> names = Arrays.asList("yodha", "alex", "sarah", "michael");

        System.out.println("=== 1. STATIC METHOD REFERENCE (ClassName::staticMethod) ===");
        // Lambda: s -> Integer.parseInt(s)
        Function<String, Integer> parser = Integer::parseInt;
        System.out.println("Parsed '420': " + parser.apply("420"));

        // Lambda: (a, b) -> Math.max(a, b)
        BiFunction<Double, Double, Double> maxFinder = Math::max;
        System.out.println("Max of 45.5 and 99.2: " + maxFinder.apply(45.5, 99.2));


        System.out.println("\n=== 2. INSTANCE METHOD OF EXISTING OBJECT (obj::method) ===");
        // Lambda: msg -> System.out.println(msg)
        Consumer<String> sysOut = System.out::println;
        sysOut.accept("  ✓ Printed via System.out::println method reference!");


        System.out.println("\n=== 3. INSTANCE METHOD OF ARBITRARY OBJECT (ClassName::method) ===");
        // Lambda: s -> s.toUpperCase()
        List<String> upperNames = names.stream()
                .map(String::toUpperCase) // ClassName::instanceMethod
                .toList();
        System.out.println("Upper Names: " + upperNames);


        System.out.println("\n=== 4. CONSTRUCTOR REFERENCE (ClassName::new) ===");
        // Lambda: () -> new AccountEntity()
        Supplier<AccountEntity> defaultAccFactory = AccountEntity::new;
        AccountEntity a1 = defaultAccFactory.get();
        a1.printSummary();

        // Lambda: (id, balance) -> new AccountEntity(id, balance)
        BiFunction<String, Double, AccountEntity> customAccFactory = AccountEntity::new;
        AccountEntity a2 = customAccFactory.apply("ACC-1002", 5400.0);
        a2.printSummary();

        // Filter using Static method reference:
        List<AccountEntity> accounts = List.of(a1, a2, new AccountEntity("ACC-1003", -50.0));
        System.out.println("\nSolvent Accounts (filtered via AccountEntity::isSolvent):");
        accounts.stream()
                .filter(AccountEntity::isSolvent) // Static method ref
                .forEach(AccountEntity::printSummary); // Instance method ref

        // ============================================================
        // KEY TAKEAWAYS:
        // ============================================================
        // - Method references (`::`) make stream pipelines cleaner and more readable.
        // - Use `Class::staticMethod`, `obj::instanceMethod`, `Class::instanceMethod`, or `Class::new`.
        // ============================================================
    }
}
