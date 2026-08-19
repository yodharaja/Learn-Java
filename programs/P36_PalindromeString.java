package programs;

/**
 * ============================================================
 * PROGRAM 36: Palindrome String Checker
 * ============================================================
 * Problem: WAP to check if a String is a valid Palindrome,
 * ignoring non-alphanumeric characters and case.
 *   - "A man, a plan, a canal: Panama" -> TRUE
 *   - "race a car" -> FALSE
 * ============================================================
 */
public class P36_PalindromeString {

    public static boolean isPalindromePhrase(String s) {
        if (s == null) return false;

        int left = 0, right = s.length() - 1;

        while (left < right) {
            while (left < right && !Character.isLetterOrDigit(s.charAt(left))) left++;
            while (left < right && !Character.isLetterOrDigit(s.charAt(right))) right--;

            if (Character.toLowerCase(s.charAt(left)) != Character.toLowerCase(s.charAt(right))) {
                return false;
            }
            left++;
            right--;
        }
        return true;
    }

    public static void main(String[] args) {
        String[] tests = {
            "A man, a plan, a canal: Panama",
            "race a car",
            "Was it a car or a cat I saw?",
            "No 'x' in Nixon",
            "Hello World"
        };

        for (String t : tests) {
            System.out.printf("  \"%-35s\" -> %s%n", t, isPalindromePhrase(t) ? "✓ PALINDROME" : "✗ NOT PALINDROME");
        }
    }
}
