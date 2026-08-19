package programs;

import java.util.Arrays;

/**
 * ============================================================
 * PROGRAM 38: Anagram Checker
 * ============================================================
 * Problem: WAP to check if two strings are Anagrams (contain identical
 * characters in different order, e.g., "listen" & "silent").
 * ============================================================
 */
public class P38_AnagramCheck {

    public static boolean isAnagram(String s1, String s2) {
        if (s1 == null || s2 == null) return false;

        // Clean spaces and lowercase
        String clean1 = s1.replaceAll("\\s", "").toLowerCase();
        String clean2 = s2.replaceAll("\\s", "").toLowerCase();

        if (clean1.length() != clean2.length()) return false;

        char[] a1 = clean1.toCharArray();
        char[] a2 = clean2.toCharArray();
        Arrays.sort(a1);
        Arrays.sort(a2);

        return Arrays.equals(a1, a2);
    }

    public static void main(String[] args) {
        String[][] pairs = {
            {"listen", "silent"},
            {"Debit Card", "Bad Credit"},
            {"Astronomer", "Moon starer"},
            {"Java", "Python"}
        };

        for (String[] p : pairs) {
            System.out.printf("  (\"%s\", \"%s\") -> %s%n",
                    p[0], p[1], isAnagram(p[0], p[1]) ? "✓ ANAGRAMS" : "✗ NOT ANAGRAMS");
        }
    }
}
