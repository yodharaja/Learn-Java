package chapter4;

import java.util.Scanner;

/**
 * ============================================================
 * EXERCISE 4 — Mini Math & Number Utility Toolkit 🧮
 * ============================================================
 *
 * Build a modular math utility console app that includes functions for:
 *   1. isPrime(int n)            -> Check if number is prime
 *   2. factorial(int n)          -> Recursive or iterative factorial
 *   3. gcd(int a, int b)         -> Greatest Common Divisor (Euclidean algorithm)
 *   4. lcm(int a, int b)         -> Least Common Multiple using GCD
 *   5. isArmstrong(int n)        -> e.g. 153 = 1^3 + 5^3 + 3^3
 *   6. stats(double... values)   -> Display min, max, sum, average using varargs
 *
 * Concepts tested:
 *   - Defining methods with parameters and return values
 *   - Method Overloading
 *   - Recursion & Iteration
 *   - Variable arguments (Varargs)
 *   - Clean method breakdown and reuse
 * ============================================================
 */
public class Exercise4 {

    // 1. Prime check
    public static boolean isPrime(int n) {
        if (n <= 1) return false;
        if (n == 2) return true;
        if (n % 2 == 0) return false;
        for (int i = 3; i <= Math.sqrt(n); i += 2) {
            if (n % i == 0) return false;
        }
        return true;
    }

    // 2. Factorial (handles up to 20! without overflow in long)
    public static long factorial(int n) {
        if (n < 0) return -1; // invalid
        if (n <= 1) return 1;
        long fact = 1;
        for (int i = 2; i <= n; i++) {
            fact *= i;
        }
        return fact;
    }

    // 3. Greatest Common Divisor (Euclidean algorithm with recursion)
    public static int gcd(int a, int b) {
        if (b == 0) return Math.abs(a);
        return gcd(b, a % b);
    }

    // Overloaded GCD for 3 numbers
    public static int gcd(int a, int b, int c) {
        return gcd(gcd(a, b), c);
    }

    // 4. Least Common Multiple: (a * b) / gcd(a, b)
    public static long lcm(int a, int b) {
        if (a == 0 || b == 0) return 0;
        return Math.abs((long) a * b) / gcd(a, b);
    }

    // 5. Armstrong Number check (e.g., 153 -> 1^3 + 5^3 + 3^3 = 153)
    public static boolean isArmstrong(int n) {
        if (n < 0) return false;
        int original = n;
        int digits = String.valueOf(n).length();
        int sum = 0;
        
        int temp = n;
        while (temp > 0) {
            int digit = temp % 10;
            sum += Math.pow(digit, digits);
            temp /= 10;
        }
        return sum == original;
    }

    // 6. Varargs Statistics Utility
    public static void printStats(String title, double... numbers) {
        System.out.println("\n📊 " + title);
        if (numbers.length == 0) {
            System.out.println("  No numbers provided.");
            return;
        }

        double min = numbers[0];
        double max = numbers[0];
        double sum = 0;

        for (double num : numbers) {
            if (num < min) min = num;
            if (num > max) max = num;
            sum += num;
        }
        double avg = sum / numbers.length;

        System.out.printf("  Count: %d | Min: %.2f | Max: %.2f | Sum: %.2f | Avg: %.2f%n",
                numbers.length, min, max, sum, avg);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        boolean running = true;

        System.out.println("╔══════════════════════════════════════════════════╗");
        System.out.println("║         🧮 MINI MATH UTILITY TOOLKIT             ║");
        System.out.println("╚══════════════════════════════════════════════════╝");

        while (running) {
            System.out.println("\nSelect an operation:");
            System.out.println("  1. Prime Checker");
            System.out.println("  2. Factorial");
            System.out.println("  3. GCD & LCM");
            System.out.println("  4. Armstrong Number Check");
            System.out.println("  5. Varargs Statistics Demo");
            System.out.println("  6. Run Automated Self-Test Suite");
            System.out.println("  7. Exit");
            System.out.print("Your choice (1-7): ");

            if (!sc.hasNextInt()) {
                System.out.println("❌ Invalid input! Please enter a number.");
                sc.next();
                continue;
            }

            int choice = sc.nextInt();

            switch (choice) {
                case 1 -> {
                    System.out.print("Enter an integer to check if prime: ");
                    int num = sc.nextInt();
                    System.out.println(num + " is " + (isPrime(num) ? "a PRIME number! ✓" : "NOT a prime number. ✗"));
                }
                case 2 -> {
                    System.out.print("Enter an integer (0 to 20): ");
                    int num = sc.nextInt();
                    if (num < 0 || num > 20) {
                        System.out.println("⚠️ Please enter a number between 0 and 20.");
                    } else {
                        System.out.println(num + "! = " + factorial(num));
                    }
                }
                case 3 -> {
                    System.out.print("Enter first integer: ");
                    int a = sc.nextInt();
                    System.out.print("Enter second integer: ");
                    int b = sc.nextInt();
                    System.out.println("GCD(" + a + ", " + b + ") = " + gcd(a, b));
                    System.out.println("LCM(" + a + ", " + b + ") = " + lcm(a, b));
                }
                case 4 -> {
                    System.out.print("Enter a number: ");
                    int num = sc.nextInt();
                    System.out.println(num + " is " + (isArmstrong(num) ? "an ARMSTRONG number! ✓" : "NOT an Armstrong number. ✗"));
                }
                case 5 -> {
                    printStats("Sample Class Marks", 85.5, 92.0, 78.0, 95.5, 88.0);
                    printStats("Daily Temperatures", 24.5, 26.0, 22.8, 29.1, 23.4, 25.0);
                }
                case 6 -> {
                    System.out.println("\n--- RUNNING SELF TEST ---");
                    System.out.println("isPrime(29)       == true   : " + (isPrime(29) == true ? "PASS" : "FAIL"));
                    System.out.println("factorial(5)      == 120    : " + (factorial(5) == 120 ? "PASS" : "FAIL"));
                    System.out.println("gcd(48, 18)       == 6      : " + (gcd(48, 18) == 6 ? "PASS" : "FAIL"));
                    System.out.println("gcd(12, 24, 36)   == 12     : " + (gcd(12, 24, 36) == 12 ? "PASS" : "FAIL"));
                    System.out.println("lcm(12, 18)       == 36     : " + (lcm(12, 18) == 36 ? "PASS" : "FAIL"));
                    System.out.println("isArmstrong(153)  == true   : " + (isArmstrong(153) == true ? "PASS" : "FAIL"));
                    System.out.println("isArmstrong(123)  == false  : " + (isArmstrong(123) == false ? "PASS" : "FAIL"));
                    System.out.println("All automated tests completed! ✓");
                }
                case 7 -> {
                    System.out.println("Exiting Math Toolkit. Keep practicing! 👋");
                    running = false;
                }
                default -> System.out.println("⚠️ Unknown choice! Please pick 1-7.");
            }
        }

        sc.close();
    }
}
