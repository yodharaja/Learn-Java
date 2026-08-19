package chapter9;

import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.TreeSet;

/**
 * ============================================================
 * LESSON 9.4 — Set Interface & Set Operations
 * ============================================================
 *
 * A `Set<E>` is a collection that CANNOT contain DUPLICATE elements.
 * Modeled after mathematical set theory.
 *
 * Implementations:
 *   - `HashSet`: Backed by HashMap, unordered, fastest ($O(1)$ operations).
 *   - `LinkedHashSet`: Maintains insertion order ($O(1)$).
 *   - `TreeSet`: Stores elements in natural/custom sorted order ($O(\log n)$).
 *
 * Set Algebraic Operations:
 *   - Union: `setA.addAll(setB)`
 *   - Intersection: `setA.retainAll(setB)`
 *   - Difference: `setA.removeAll(setB)`
 */
public class HashSetDemo {

    public static void main(String[] args) {
        System.out.println("=== 1. ELIMINATING DUPLICATES WITH HASHSET ===");
        Set<String> uniqueUsernames = new HashSet<>();

        // Adding elements
        uniqueUsernames.add("yodha_dev");
        uniqueUsernames.add("alex_coder");
        uniqueUsernames.add("sarah_99");

        // Attempting to add duplicate
        boolean addedDuplicate = uniqueUsernames.add("yodha_dev"); // returns false!

        System.out.println("Set Contents: " + uniqueUsernames);
        System.out.println("Was duplicate 'yodha_dev' added? " + addedDuplicate);


        System.out.println("\n=== 2. SET IMPLEMENTATION ORDER COMPARISON ===");
        String[] rawTags = {"java", "backend", "cloud", "docker", "kubernetes", "aws", "backend", "java"};

        Set<String> hashSet = new HashSet<>();
        Set<String> linkedSet = new LinkedHashSet<>();
        Set<String> treeSet = new TreeSet<>();

        for (String tag : rawTags) {
            hashSet.add(tag);
            linkedSet.add(tag);
            treeSet.add(tag);
        }

        System.out.println("HashSet (No guaranteed order) : " + hashSet);
        System.out.println("LinkedHashSet (Insertion order): " + linkedSet);
        System.out.println("TreeSet (Alphabetical sorted) : " + treeSet);


        System.out.println("\n=== 3. MATHEMATICAL SET OPERATIONS ===");
        Set<Integer> groupA = new HashSet<>(Set.of(1, 2, 3, 4, 5));
        Set<Integer> groupB = new HashSet<>(Set.of(4, 5, 6, 7, 8));

        System.out.println("Group A: " + groupA);
        System.out.println("Group B: " + groupB);

        // 1. UNION (A ∪ B)
        Set<Integer> union = new HashSet<>(groupA);
        union.addAll(groupB);
        System.out.println("Union (A ∪ B)         : " + union);

        // 2. INTERSECTION (A ∩ B)
        Set<Integer> intersection = new HashSet<>(groupA);
        intersection.retainAll(groupB);
        System.out.println("Intersection (A ∩ B)  : " + intersection);

        // 3. DIFFERENCE (A - B)
        Set<Integer> difference = new HashSet<>(groupA);
        difference.removeAll(groupB);
        System.out.println("Difference (A - B)    : " + difference);

        // ============================================================
        // KEY TAKEAWAYS:
        // ============================================================
        // - Use `Set` whenever element uniqueness is required.
        // - `HashSet` is fastest, `TreeSet` keeps sorted elements, `LinkedHashSet` preserves order.
        // - Set operations (`addAll`, `retainAll`, `removeAll`) cleanly solve overlapping group problems.
        // ============================================================
    }
}
