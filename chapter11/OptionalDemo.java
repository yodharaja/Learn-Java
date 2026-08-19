package chapter11;

import java.util.Optional;

/**
 * ============================================================
 * LESSON 11.4 — The `Optional<T>` Container Class
 * ============================================================
 *
 * Sir Tony Hoare famously called `null` his "Billion-Dollar Mistake".
 * `NullPointerException` (NPE) is the most frequent runtime crash in software.
 *
 * Java 8 introduced `java.util.Optional<T>`:
 *   - A container object which may or may not contain a non-null value.
 *   - Explicitly communicates to callers that a return value might be absent.
 *   - Encourages functional handling instead of repetitive `if (x != null)` checks.
 *
 * Core Methods:
 *   - `Optional.of(val)` (throws NPE if val is null)
 *   - `Optional.ofNullable(val)` (safe wrapper for potentially null values)
 *   - `Optional.empty()`
 *   - `isPresent()` & `isEmpty()` (Java 11+)
 *   - `orElse(defaultVal)`, `orElseGet(supplier)`, `orElseThrow(exceptionSupplier)`
 *   - `map()`, `filter()`, `ifPresent(consumer)`
 */
public class OptionalDemo {

    // Simulating database lookup that might return null
    public static Optional<String> findUserEmailById(String userId) {
        if ("USR-101".equals(userId)) {
            return Optional.of("yodha@example.com");
        }
        return Optional.empty(); // Cleanly represents "not found"
    }

    public static void main(String[] args) {
        System.out.println("=== 1. CREATING OPTIONALS ===");
        Optional<String> presentOpt = Optional.of("Active Value");
        Optional<String> nullOpt = Optional.ofNullable(null); // Safe!

        System.out.println("presentOpt.isPresent(): " + presentOpt.isPresent());
        System.out.println("nullOpt.isEmpty()     : " + nullOpt.isEmpty());


        System.out.println("\n=== 2. SAFELY EXTRACTING VALUES ===");
        Optional<String> user1 = findUserEmailById("USR-101");
        Optional<String> user2 = findUserEmailById("USR-999");

        // Approach A: orElse(fallback)
        System.out.println("User 1 Email: " + user1.orElse("no-email@domain.com"));
        System.out.println("User 2 Email: " + user2.orElse("no-email@domain.com"));

        // Approach B: orElseGet(supplier) -> Lazy evaluation
        String fallbackGenerated = user2.orElseGet(() -> "generated_" + System.currentTimeMillis() + "@fallback.com");
        System.out.println("Lazy fallback: " + fallbackGenerated);


        System.out.println("\n=== 3. FUNCTIONAL TRANSFORMATIONS (map & ifPresent) ===");
        // Execute only if value exists
        user1.ifPresent(email -> System.out.println("  ✓ Sending notification to: " + email));

        // Transform email to domain name safely
        String domain = user1
                .map(email -> email.substring(email.indexOf("@") + 1))
                .map(String::toUpperCase)
                .orElse("UNKNOWN DOMAIN");

        System.out.println("Extracted Domain: " + domain);


        System.out.println("\n=== 4. THROWING CUSTOM EXCEPTIONS IF ABSENT ===");
        try {
            String email = user2.orElseThrow(() -> new IllegalArgumentException("User account not found in database!"));
        } catch (IllegalArgumentException e) {
            System.out.println("  ❌ Caught expected error: " + e.getMessage());
        }

        // ============================================================
        // KEY TAKEAWAYS:
        // ============================================================
        // - Return `Optional<T>` from methods that may legitimately find no result.
        // - Avoid calling `opt.get()` directly without checking (throws NoSuchElementException).
        // - Use `orElse()`, `orElseGet()`, `map()`, and `ifPresent()` for elegant null safety.
        // ============================================================
    }
}
