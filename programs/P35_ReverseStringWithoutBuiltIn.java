package programs;

/**
 * ============================================================
 * PROGRAM 35: Reverse a String Without Built-in Functions
 * ============================================================
 * Problem: WAP to reverse a String manually using:
 *   a) Character array two-pointer swap
 *   b) StringBuilder manual prepend / backward traversal
 * ============================================================
 */
public class P35_ReverseStringWithoutBuiltIn {

    public static String reverseWithCharArray(String str) {
        if (str == null) return null;
        char[] chars = str.toCharArray();
        int left = 0, right = chars.length - 1;

        while (left < right) {
            char temp = chars[left];
            chars[left++] = chars[right];
            chars[right--] = temp;
        }

        return new String(chars);
    }

    public static void main(String[] args) {
        String[] words = {"Java", "Antigravity", "racecar", "Hello World 2026"};
        for (String w : words) {
            System.out.printf("  Original: \"%-18s\" -> Reversed: \"%s\"%n", w, reverseWithCharArray(w));
        }
    }
}
