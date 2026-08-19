package programs;

import java.util.LinkedHashSet;
import java.util.Set;

/**
 * ============================================================
 * PROGRAM 40: Remove Duplicate Characters from String
 * ============================================================
 * Problem: WAP to remove all duplicate characters from a string,
 * preserving the original order of first appearance.
 *   - Input : "programming"
 *   - Output: "progami"
 * ============================================================
 */
public class P40_RemoveDuplicateCharacters {

    public static String removeDuplicates(String str) {
        if (str == null) return null;

        Set<Character> seen = new LinkedHashSet<>();
        for (char c : str.toCharArray()) {
            seen.add(c);
        }

        StringBuilder sb = new StringBuilder();
        for (char c : seen) sb.append(c);
        return sb.toString();
    }

    public static void main(String[] args) {
        String[] words = {"programming", "banana", "antigravity", "hello world"};
        for (String w : words) {
            System.out.printf("  \"%-15s\" -> \"%s\"%n", w, removeDuplicates(w));
        }
    }
}
