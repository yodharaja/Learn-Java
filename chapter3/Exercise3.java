package chapter3;

import java.util.Scanner;

/**
 * ============================================================
 * EXERCISE 3 — Number Guessing Game 🎲
 * ============================================================
 *
 * Build a game where:
 *   1. Computer picks a random number between 1 and 100
 *   2. User guesses the number
 *   3. Program says "Too high!", "Too low!", or "Correct!"
 *   4. Tracks number of attempts
 *   5. Asks if user wants to play again
 *
 * Concepts used from this chapter:
 *   - if-else (comparing guess to answer)
 *   - while loop (keep guessing until correct)
 *   - do-while (play again loop)
 *   - break (exit when correct)
 *   - Scanner (user input)
 *
 * TRY IT YOURSELF FIRST! Then compare with the solution below.
 * ============================================================
 */
public class Exercise3 {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        String playAgain;

        System.out.println("╔══════════════════════════════════════╗");
        System.out.println("║     🎲 NUMBER GUESSING GAME 🎲      ║");
        System.out.println("║   Guess a number between 1 and 100  ║");
        System.out.println("╚══════════════════════════════════════╝");

        do {
            // ============================================================
            // STEP 1: Generate a random number between 1 and 100
            // ============================================================
            // Math.random() returns a double between 0.0 (inclusive) and 1.0 (exclusive)
            // Multiply by 100 → 0.0 to 99.99
            // Add 1 → 1.0 to 100.99
            // Cast to int → 1 to 100
            int secretNumber = (int) (Math.random() * 100) + 1;

            int attempts = 0;
            int maxAttempts = 7;  // For 1-100, you can always win in 7 tries with binary search!
            boolean won = false;

            System.out.println("\n🎯 I'm thinking of a number between 1 and 100.");
            System.out.println("   You have " + maxAttempts + " attempts. Good luck!\n");

            // ============================================================
            // STEP 2: Game loop — keep guessing
            // ============================================================
            while (attempts < maxAttempts) {
                attempts++;

                System.out.print("Attempt " + attempts + "/" + maxAttempts + " → Your guess: ");

                // Input validation
                if (!sc.hasNextInt()) {
                    System.out.println("  ❌ Please enter a valid number!");
                    sc.next();  // clear invalid input
                    attempts--;  // don't count invalid attempt
                    continue;
                }

                int guess = sc.nextInt();

                // ============================================================
                // STEP 3: Compare guess with secret number
                // ============================================================
                if (guess < 1 || guess > 100) {
                    System.out.println("  ⚠️  Please guess between 1 and 100!");
                    attempts--;  // don't count out-of-range attempt
                } else if (guess < secretNumber) {
                    System.out.println("  📈 Too LOW! Go higher.");

                    // Hint: show range
                    if (secretNumber - guess > 20) {
                        System.out.println("     (Way too low!)");
                    }
                } else if (guess > secretNumber) {
                    System.out.println("  📉 Too HIGH! Go lower.");

                    if (guess - secretNumber > 20) {
                        System.out.println("     (Way too high!)");
                    }
                } else {
                    // guess == secretNumber
                    won = true;
                    System.out.println("  🎉 CORRECT! You got it!");
                    break;  // exit the loop
                }

                // Show remaining attempts
                int remaining = maxAttempts - attempts;
                if (remaining > 0 && !won) {
                    System.out.println("     (" + remaining + " attempts remaining)");
                }
            }

            // ============================================================
            // STEP 4: Game over — show results
            // ============================================================
            System.out.println("\n──────────────────────────────────────");
            if (won) {
                // Rate performance
                String rating;
                if (attempts == 1) {
                    rating = "🏆 IMPOSSIBLE! Lucky guess!";
                } else if (attempts <= 3) {
                    rating = "⭐ AMAZING! You're a genius!";
                } else if (attempts <= 5) {
                    rating = "👍 GREAT JOB!";
                } else {
                    rating = "😅 Close one!";
                }

                System.out.println("You won in " + attempts + " attempt" + (attempts > 1 ? "s" : "") + "!");
                System.out.println(rating);
            } else {
                System.out.println("💀 GAME OVER! You ran out of attempts.");
                System.out.println("The number was: " + secretNumber);
            }
            System.out.println("──────────────────────────────────────");

            // ============================================================
            // STEP 5: Play again?
            // ============================================================
            sc.nextLine();  // consume leftover newline
            System.out.print("\nPlay again? (yes/no): ");
            playAgain = sc.nextLine();

        } while (playAgain.equalsIgnoreCase("yes") || playAgain.equalsIgnoreCase("y"));

        System.out.println("\nThanks for playing! 👋");
        sc.close();

        // ============================================================
        // EXPECTED OUTPUT (example run):
        // ============================================================
        /*
         * ╔══════════════════════════════════════╗
         * ║     🎲 NUMBER GUESSING GAME 🎲      ║
         * ║   Guess a number between 1 and 100  ║
         * ╚══════════════════════════════════════╝
         *
         * 🎯 I'm thinking of a number between 1 and 100.
         *    You have 7 attempts. Good luck!
         *
         * Attempt 1/7 → Your guess: 50
         *   📉 Too HIGH! Go lower.
         *      (6 attempts remaining)
         * Attempt 2/7 → Your guess: 25
         *   📈 Too LOW! Go higher.
         *      (5 attempts remaining)
         * Attempt 3/7 → Your guess: 37
         *   📈 Too LOW! Go higher.
         *      (4 attempts remaining)
         * Attempt 4/7 → Your guess: 42
         *   🎉 CORRECT! You got it!
         *
         * ──────────────────────────────────────
         * You won in 4 attempts!
         * 👍 GREAT JOB!
         * ──────────────────────────────────────
         */

        // ============================================================
        // BONUS CHALLENGES:
        // ============================================================
        // 1. Add difficulty levels (Easy: 1-50, Medium: 1-100, Hard: 1-500)
        // 2. Track best score across multiple games
        // 3. Add a hint system ("The number is even/odd")
        // 4. Show a progress bar of attempts used
        // ============================================================
    }
}
