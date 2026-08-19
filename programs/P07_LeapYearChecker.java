package programs;

/**
 * ============================================================
 * PROGRAM 07: Leap Year Checker
 * ============================================================
 * Problem: WAP to check if a year is a Leap Year.
 *   Rule:
 *   - Divisible by 4 AND NOT divisible by 100
 *   - OR Divisible by 400 (Century Leap Year)
 * ============================================================
 */
public class P07_LeapYearChecker {

    public static boolean isLeapYear(int year) {
        return (year % 4 == 0 && year % 100 != 0) || (year % 400 == 0);
    }

    public static void main(String[] args) {
        int[] years = {1900, 2000, 2020, 2024, 2025, 2026, 2400};

        System.out.println("=== LEAP YEAR TEST CASES ===");
        for (int y : years) {
            boolean leap = isLeapYear(y);
            System.out.printf("  Year %4d : %s (Days in Feb: %d)%n",
                    y, leap ? "✓ LEAP YEAR" : "✗ COMMON YEAR", leap ? 29 : 28);
        }
    }
}
