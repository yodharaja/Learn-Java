package chapter7;

/**
 * ============================================================
 * LESSON 7.3 — StringBuilder vs StringBuffer vs String
 * ============================================================
 *
 * Why do we need StringBuilder?
 *   - `java.lang.String` is IMMUTABLE. Every time you concatenate (`s += "abc"`),
 *     Java allocates a NEW object in memory and copies the old characters.
 *   - In loops with thousands of concatenations, `String` creates extreme memory overhead
 *     and slows execution exponentially ($O(n^2)$).
 *   - `StringBuilder` is MUTABLE. It uses a resizable internal character buffer
 *     and modifies text IN-PLACE without creating new objects ($O(n)$).
 *
 * Comparison:
 * ┌───────────────┬──────────────┬────────────────┬───────────────────────────────┐
 * │ Class         │ Mutability   │ Thread-Safe?   │ Performance / Typical Use     │
 * ├───────────────┼──────────────┼────────────────┼───────────────────────────────┤
 * │ String        │ Immutable    │ Yes (Safe)     │ Constants, keys, fixed text   │
 * │ StringBuilder │ Mutable      │ No (Fast)      │ Single-threaded text building │
 * │ StringBuffer  │ Mutable      │ Yes (Sync)     │ Multi-threaded text building  │
 * └───────────────┴──────────────┴────────────────┴───────────────────────────────┘
 */
public class StringBuilderDemo {

    public static void main(String[] args) {
        System.out.println("=== 1. STRINGBUILDER CORE OPERATIONS ===");
        StringBuilder sb = new StringBuilder("Hello");

        // 1. append() - add text to end
        sb.append(" World");
        sb.append('!');
        sb.append(" (Year ").append(2026).append(")");
        System.out.println("After append : " + sb);

        // 2. insert() - add text at specific index
        sb.insert(5, ", Awesome");
        System.out.println("After insert : " + sb);

        // 3. replace() - replace substring in range [start, end)
        int idx = sb.indexOf("Awesome");
        if (idx != -1) {
            sb.replace(idx, idx + "Awesome".length(), "Modern");
        }
        System.out.println("After replace: " + sb);

        // 4. delete() & deleteCharAt()
        sb.delete(sb.indexOf("("), sb.length()); // delete from '(' to end
        System.out.println("After delete : " + sb);

        // 5. reverse()
        StringBuilder palindromeCheck = new StringBuilder("racecar");
        palindromeCheck.reverse();
        System.out.println("Reversed 'racecar': " + palindromeCheck);


        System.out.println("\n=== 2. CAPACITY & BUFFER MANAGEMENT ===");
        StringBuilder buffer = new StringBuilder(100); // initial capacity = 100
        System.out.println("Initial length   : " + buffer.length());
        System.out.println("Initial capacity : " + buffer.capacity());

        buffer.append("Learning Java with Antigravity");
        System.out.println("Updated length   : " + buffer.length());
        System.out.println("Updated capacity : " + buffer.capacity());


        System.out.println("\n=== 3. BENCHMARK: STRING (+) vs STRINGBUILDER ===");
        int iterations = 30000;

        // Test 1: String Concatenation (+)
        long startStr = System.currentTimeMillis();
        String strResult = "";
        for (int i = 0; i < iterations; i++) {
            strResult += "a";
        }
        long durationStr = System.currentTimeMillis() - startStr;

        // Test 2: StringBuilder append()
        long startSb = System.currentTimeMillis();
        StringBuilder sbResult = new StringBuilder();
        for (int i = 0; i < iterations; i++) {
            sbResult.append("a");
        }
        long durationSb = System.currentTimeMillis() - startSb;

        System.out.printf("Time taken to concatenate %d characters:%n", iterations);
        System.out.printf("  • String (+)        : %d ms (Extremely slow!)%n", durationStr);
        System.out.printf("  • StringBuilder     : %d ms (Near instantaneous!)%n", durationSb);
        System.out.printf("  -> StringBuilder was approx %,.0f%% faster!%n",
                ((double) durationStr / Math.max(1, durationSb)) * 100);

        // ============================================================
        // KEY TAKEAWAYS:
        // ============================================================
        // - Never concatenate strings using '+' in long loops.
        // - Always use `StringBuilder` for dynamic, multi-step text construction.
        // - Use `StringBuffer` only when synchronized thread-safety is strictly needed.
        // ============================================================
    }
}
