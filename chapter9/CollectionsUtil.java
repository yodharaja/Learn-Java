package chapter9;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * ============================================================
 * LESSON 9.5 — java.util.Collections Utility Class
 * ============================================================
 *
 * `java.util.Collections` is a utility class consisting exclusively
 * of static methods that operate on or return collections.
 *
 * Essential Algorithms & Wrappers:
 *   1. `Collections.sort(list)` & `Collections.reverse(list)`
 *   2. `Collections.shuffle(list)` (Randomize order)
 *   3. `Collections.min(list)` & `Collections.max(list)`
 *   4. `Collections.frequency(list, object)` (Count occurrences)
 *   5. `Collections.binarySearch(list, key)`
 *   6. `Collections.unmodifiableList(list)` (Read-only immutable wrapper)
 *   7. `Collections.synchronizedList(list)` (Thread-safe wrapper)
 */
public class CollectionsUtil {

    public static void main(String[] args) {
        List<Integer> numbers = new ArrayList<>(List.of(45, 12, 85, 32, 89, 39, 69, 44, 42, 12, 99));

        System.out.println("=== 1. SORTING, REVERSING & SHUFFLING ===");
        System.out.println("Original: " + numbers);

        // 1. Sort ascending
        Collections.sort(numbers);
        System.out.println("Sorted  : " + numbers);

        // 2. Reverse
        Collections.reverse(numbers);
        System.out.println("Reversed: " + numbers);

        // 3. Shuffle (Randomize order)
        Collections.shuffle(numbers);
        System.out.println("Shuffled: " + numbers);


        System.out.println("\n=== 2. MIN, MAX & FREQUENCY ===");
        System.out.println("Minimum element : " + Collections.min(numbers));
        System.out.println("Maximum element : " + Collections.max(numbers));
        System.out.println("Frequency of 12 : " + Collections.frequency(numbers, 12));


        System.out.println("\n=== 3. IMMUTABLE UNMODIFIABLE WRAPPERS ===");
        List<String> mutableRoles = new ArrayList<>();
        mutableRoles.add("ADMIN");
        mutableRoles.add("USER");

        // Create read-only view
        List<String> readOnlyRoles = Collections.unmodifiableList(mutableRoles);
        System.out.println("Read-only Roles View: " + readOnlyRoles);

        try {
            readOnlyRoles.add("SUPERADMIN"); // Throws UnsupportedOperationException!
        } catch (UnsupportedOperationException e) {
            System.out.println("  ❌ Caught expected error: Cannot modify an unmodifiable collection!");
        }

        // ============================================================
        // KEY TAKEAWAYS:
        // ============================================================
        // - `Collections` utility provides standard collection algorithms.
        // - `unmodifiableList/Set/Map` protects internal state from outside tampering.
        // ============================================================
    }
}
