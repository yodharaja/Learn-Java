package programs;

/**
 * ============================================================
 * PROGRAM 11: Prime Number Checker & Range Generator
 * ============================================================
 * Problem: WAP to:
 *   a) Check if a number is Prime in O(√n) time.
 *   b) Find and print all Prime numbers between 1 and N.
 * ============================================================
 */
public class P11_PrimeNumberChecker {

    public static boolean isPrime(int n) {
        if (n <= 1) return false;
        if (n == 2 || n == 3) return true;
        if (n % 2 == 0 || n % 3 == 0) return false;

        // Check factors of the form 6k ± 1 up to sqrt(n)
        for (int i = 5; i * i <= n; i += 6) {
            if (n % i == 0 || n % (i + 2) == 0) return false;
        }
        return true;
    }

    public static void printPrimesInRange(int limit) {
        System.out.printf("Primes from 1 to %d: [ ", limit);
        int count = 0;
        for (int i = 1; i <= limit; i++) {
            if (isPrime(i)) {
                System.out.print(i + " ");
                count++;
            }
        }
        System.out.printf("] (Total: %d primes)%n", count);
    }

    public static void main(String[] args) {
        System.out.println("=== 1. SINGLE PRIME CHECKS ===");
        int[] testCases = {1, 2, 3, 4, 17, 19, 29, 49, 97, 100};
        for (int num : testCases) {
            System.out.printf("  %3d is prime? -> %s%n", num, isPrime(num) ? "YES ✓" : "NO ✗");
        }

        System.out.println("\n=== 2. PRIMES IN RANGE [1..50] ===");
        printPrimesInRange(50);
    }
}
