package programs;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * ============================================================
 * PROGRAM 39: Find First Non-Repeated Character in String
 * ============================================================
 * Problem: WAP to find the first character in a string that does not repeat.
 *   - Input : "swiss" -> Output: 'w'
 *   - Input : "teeter" -> Output: 'r'
 * ============================================================
 */
public class P39_FirstNonRepeatedChar {

    public static Character findFirstNonRepeated(String str) {
        if (str == null || str.isEmpty()) return null;

        Map<Character, Integer> counts = new LinkedHashMap<>();
        for (char c : str.toCharArray()) {
            counts.put(c, counts.getOrDefault(c, 0) + 1);
        }

        for (Map.Entry<Character, Integer> entry : counts.entrySet()) {
            if (entry.getValue() == 1) {
                return entry.getKey();
            }
        }

        return null; // All repeat
    }

    public static void main(String[] args) {
        String[] samples = {"swiss", "teeter", "aabbcc", "antigravity"};
        for (String s : samples) {
            Character c = findFirstNonRepeated(s);
            System.out.printf("  \"%-12s\" -> First Non-repeated: %s%n",
                    s, (c == null ? "None" : "'" + c + "'"));
        }
    }
}
