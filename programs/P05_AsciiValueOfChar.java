package programs;

/**
 * ============================================================
 * PROGRAM 05: ASCII Value and Character Conversions
 * ============================================================
 * Problem: WAP to print the ASCII / Unicode value of characters
 * and perform case toggling arithmetic.
 * ============================================================
 */
public class P05_AsciiValueOfChar {

    public static void main(String[] args) {
        char[] sampleChars = {'A', 'Z', 'a', 'z', '0', '9', '$', '@', ' '};

        System.out.println("=== CHARACTER TO ASCII TABLE ===");
        for (char c : sampleChars) {
            int ascii = (int) c;
            System.out.printf("  Character: '%c'  ->  ASCII Value: %3d  (Hex: 0x%02X)%n", c, ascii, ascii);
        }

        System.out.println("\n=== CASE CONVERSION VIA ASCII ARITHMETIC (±32) ===");
        char lower = 'g';
        char upper = (char) (lower - 32); // 'a'(97) - 32 = 'A'(65)
        System.out.printf("  Lowercase '%c' - 32 = Uppercase '%c'%n", lower, upper);

        char upperK = 'K';
        char lowerK = (char) (upperK + 32);
        System.out.printf("  Uppercase '%c' + 32 = Lowercase '%c'%n", upperK, lowerK);
    }
}
