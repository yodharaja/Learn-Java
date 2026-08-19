package chapter9;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.TreeMap;

/**
 * ============================================================
 * LESSON 9.3 — HashMap & Map Interface
 * ============================================================
 *
 * A `Map<K, V>` stores data as KEY-VALUE pairs.
 *   - Keys must be UNIQUE. Duplicate keys overwrite the previous value.
 *   - Values can be duplicated.
 *   - Provides near-instant $O(1)$ lookup, insertion, and deletion by Key!
 *
 * Common Map Implementations:
 *   - `HashMap`: Unordered, fastest ($O(1)$).
 *   - `LinkedHashMap`: Preserves INSERTION order ($O(1)$).
 *   - `TreeMap`: Keys sorted in natural/custom order ($O(\log n)$ Red-Black tree).
 *
 * Useful Java 8+ Methods:
 *   - `getOrDefault(key, defaultVal)`
 *   - `putIfAbsent(key, val)`
 *   - `computeIfAbsent(key, mappingFunc)`
 */
public class HashMapDemo {

    public static void main(String[] args) {
        System.out.println("=== 1. HASHMAP CRUD OPERATIONS ===");
        Map<String, Double> productPrices = new HashMap<>();

        // 1. put(key, value)
        productPrices.put("Laptop", 1199.99);
        productPrices.put("Mechanical Keyboard", 129.50);
        productPrices.put("Gaming Mouse", 69.99);
        productPrices.put("4K Monitor", 349.00);

        // Overwriting a key
        productPrices.put("Gaming Mouse", 59.99); // Updates price

        System.out.println("Product Price Map: " + productPrices);

        // 2. get(key) & containsKey()
        System.out.println("Price of Laptop: $" + productPrices.get("Laptop"));
        System.out.println("Has 'Webcam'? " + productPrices.containsKey("Webcam"));

        // 3. getOrDefault() - Avoids null returns
        double webcamPrice = productPrices.getOrDefault("Webcam", 0.0);
        System.out.println("Webcam price (with fallback): $" + webcamPrice);

        // 4. putIfAbsent()
        productPrices.putIfAbsent("Laptop", 999.0); // Will NOT overwrite existing Laptop
        productPrices.putIfAbsent("Desk Mat", 24.99); // Will be inserted
        System.out.println("After putIfAbsent: " + productPrices);


        System.out.println("\n=== 2. ITERATING OVER A MAP ===");

        // Option A: Iterate through Key-Value EntrySet (Fastest & Best)
        System.out.println("Iterating through entrySet():");
        for (Map.Entry<String, Double> entry : productPrices.entrySet()) {
            System.out.printf("  • %-20s -> $%.2f%n", entry.getKey(), entry.getValue());
        }

        // Option B: Iterate through Keys (keySet())
        System.out.print("\nAll Product Keys: ");
        for (String key : productPrices.keySet()) {
            System.out.print(key + " | ");
        }
        System.out.println();


        System.out.println("\n=== 3. WORD FREQUENCY COUNTER PATTERN ===");
        // Classic interview pattern: Count word occurrences in text
        String passage = "java is fast java is robust java is everywhere and java is scalable";
        String[] words = passage.split(" ");

        Map<String, Integer> wordFrequency = new HashMap<>();
        for (String w : words) {
            // Using getOrDefault to increment frequency in one line!
            wordFrequency.put(w, wordFrequency.getOrDefault(w, 0) + 1);
        }

        System.out.println("Word Frequencies: " + wordFrequency);


        System.out.println("\n=== 4. MAP IMPLEMENTATION COMPARISON ===");
        // HashMap: Unordered
        Map<Integer, String> hash = new HashMap<>();
        // LinkedHashMap: Keeps insertion order
        Map<Integer, String> linked = new LinkedHashMap<>();
        // TreeMap: Keeps keys sorted
        Map<Integer, String> tree = new TreeMap<>();

        int[] sampleKeys = {50, 20, 80, 10, 40};
        for (int k : sampleKeys) {
            hash.put(k, "V" + k);
            linked.put(k, "V" + k);
            tree.put(k, "V" + k);
        }

        System.out.println("HashMap (Arbitrary order)       : " + hash);
        System.out.println("LinkedHashMap (Insertion order) : " + linked);
        System.out.println("TreeMap (Sorted Key order)      : " + tree);

        // ============================================================
        // KEY TAKEAWAYS:
        // ============================================================
        // - `HashMap` is the #1 data structure for key-value associative lookups ($O(1)$).
        // - Use `entrySet()` for iterating over key and value together.
        // - Always implement proper `equals()` and `hashCode()` on custom key objects!
        // ============================================================
    }
}
