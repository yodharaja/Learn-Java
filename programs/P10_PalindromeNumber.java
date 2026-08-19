package programs;

/**
 * ============================================================
 * PROGRAM 10: Palindrome Number Checker
 * ============================================================
 * Problem: WAP to check if a given number is a Palindrome
 * (reads the same forward and backward, e.g., 12321, 4554).
 * ============================================================
 */
public class P10_PalindromeNumber {

    public static boolean isPalindrome(int number) {
        if (number < 0) return false; // negative numbers are not palindromic (e.g. -121 != 121-)
        int original = number;
        int reversed = 0;

        while (number > 0) {
            int digit = number % 10;
            reversed = (reversed * 10) + digit;
            number /= 10;
        }

        return original == reversed;
    }

    public static void main(String[] args) {
        int[] testCases = {121, 12321, 4554, 1001, 7, 123, -121, 10};

        System.out.println("=== PALINDROME NUMBER TESTS ===");
        for (int n : testCases) {
            System.out.printf("  Number: %6d  ->  %s%n",
                    n, isPalindrome(n) ? "✓ PALINDROME" : "✗ NOT PALINDROME");
        }
    }
}
