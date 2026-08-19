package programs;

/**
 * ============================================================
 * PROGRAM 37: Count Vowels, Consonants, Digits & Special Chars
 * ============================================================
 * Problem: WAP to count total vowels, consonants, numbers, and
 * special characters in a given string.
 * ============================================================
 */
public class P37_CountVowelsConsonantsDigits {

    public static void analyzeString(String str) {
        int vowels = 0, consonants = 0, digits = 0, specials = 0, spaces = 0;

        for (char ch : str.toCharArray()) {
            if (Character.isDigit(ch)) {
                digits++;
            } else if (Character.isLetter(ch)) {
                char lower = Character.toLowerCase(ch);
                if (lower == 'a' || lower == 'e' || lower == 'i' || lower == 'o' || lower == 'u') {
                    vowels++;
                } else {
                    consonants++;
                }
            } else if (Character.isWhitespace(ch)) {
                spaces++;
            } else {
                specials++;
            }
        }

        System.out.println("Input: \"" + str + "\"");
        System.out.printf("  Vowels: %d | Consonants: %d | Digits: %d | Spaces: %d | Special: %d%n%n",
                vowels, consonants, digits, spaces, specials);
    }

    public static void main(String[] args) {
        analyzeString("Java 21 Mastery @Antigravity #123!");
    }
}
