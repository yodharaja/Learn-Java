package programs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * ============================================================
 * PROGRAM 53: Element Frequency Counter Using HashMap
 * ============================================================
 * Problem: WAP to count the frequency of each element in an
 * ArrayList using a `HashMap<T, Integer>`.
 * ============================================================
 */
public class P53_ArrayListFrequencyCounter {

    public static <T> Map<T, Integer> getFrequencies(List<T> list) {
        Map<T, Integer> freqMap = new HashMap<>();
        for (T item : list) {
            freqMap.put(item, freqMap.getOrDefault(item, 0) + 1);
        }
        return freqMap;
    }

    public static void main(String[] args) {
        List<String> votes = Arrays.asList("Java", "Python", "Java", "Go", "Rust", "Java", "Python", "C++", "Go");

        Map<String, Integer> results = getFrequencies(votes);

        System.out.println("=== LIST ELEMENT FREQUENCY RESULTS ===");
        results.forEach((tech, count) -> System.out.printf("  • %-10s : %d occurrences%n", tech, count));
    }
}
