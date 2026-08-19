package chapter2;

import java.util.Scanner;

/**
 * ============================================================
 * LESSON 2.5 — User Input with Scanner
 * ============================================================
 * So far we've only printed output. Now let's READ input from the user!
 * Java uses the Scanner class (from java.util package) to read
 * keyboard input from the console.
 * Steps:
 *   1. Import: import java.util.Scanner;
 *   2. Create: Scanner sc = new Scanner(System.in);
 *   3. Read:   sc.nextInt(), sc.nextLine(), etc.
 *   4. Close:  sc.close(); (good practice)
 */
public class UserInput {

    public static void main(String[] args) {

        // Create a Scanner object that reads from keyboard (System.in)
        Scanner sc = new Scanner(System.in);

        // ============================================================
        // 1. READING DIFFERENT DATA TYPES
        // ============================================================

        // Reading a String (full line, including spaces)
        System.out.print("Enter your name: ");
        String name = sc.nextLine();
        System.out.println("Hello, " + name + "!");

        // Reading an int
        System.out.print("Enter your age: ");
        int age = sc.nextInt();
        System.out.println("You are " + age + " years old.");

        // Reading a double
        System.out.print("Enter your GPA: ");
        double gpa = sc.nextDouble();
        System.out.println("Your GPA is " + gpa);

        // Reading a boolean
        System.out.print("Are you a student? (true/false): ");
        boolean isStudent = sc.nextBoolean();
        System.out.println("Student status: " + isStudent);


        // ============================================================
        // 2. SCANNER METHODS REFERENCE
        // ============================================================
        /*
         * Method           Reads
         * ------           -----
         * nextLine()       Full line (String, including spaces)
         * next()           Single word (String, stops at space)
         * nextInt()        int
         * nextLong()       long
         * nextFloat()      float
         * nextDouble()     double
         * nextBoolean()    boolean (true/false)
         * nextByte()       byte
         * nextShort()      short
         */


        // ============================================================
        // 3. COMMON PITFALL: nextInt() + nextLine()
        // ============================================================
        /*
         * PROBLEM:
         *   When you call nextInt() (or nextDouble(), etc.) it reads the number
         *   but LEAVES the newline character (\n) in the buffer.
         *   If you then call nextLine(), it reads that leftover \n and gives
         *   you an empty string!
         *
         * SOLUTION:
         *   Add an extra sc.nextLine() after nextInt() to consume the leftover \n.
         *
         * Example:
         *   System.out.print("Enter age: ");
         *   int age = sc.nextInt();
         *   sc.nextLine();              // <-- consume the leftover newline!
         *   System.out.print("Enter name: ");
         *   String name = sc.nextLine(); // Now this works correctly
         */

        // Let's demonstrate the fix:
        sc.nextLine(); // consume leftover newline from nextBoolean() above

        System.out.print("\nEnter a number: ");
        int num = sc.nextInt();
        sc.nextLine(); // <-- THE FIX: consume leftover newline

        System.out.print("Enter a sentence: ");
        String sentence = sc.nextLine(); // Now this reads correctly!
        System.out.println("Number: " + num + ", Sentence: \"" + sentence + "\"");


        // ============================================================
        // 4. next() vs nextLine()
        // ============================================================
        /*
         * next()     — reads ONE WORD (stops at whitespace)
         * nextLine() — reads the ENTIRE LINE (stops at Enter key)
         *
         * Example: User types "Yodha Raja"
         *   next()     → "Yodha"     (stops at space)
         *   nextLine() → "Yodha Raja" (reads everything until Enter)
         */

        System.out.print("\nEnter two words: ");
        String word1 = sc.next();
        String word2 = sc.next();
        System.out.println("Word 1: " + word1);
        System.out.println("Word 2: " + word2);


        // ============================================================
        // 5. CHECKING INPUT BEFORE READING (hasNext methods)
        // ============================================================
        /*
         * You can check if the next input is valid before reading:
         *
         *   sc.hasNextInt()     — is the next token an int?
         *   sc.hasNextDouble()  — is the next token a double?
         *   sc.hasNextLine()    — is there another line?
         *   sc.hasNext()        — is there another token?
         *
         * Example:
         *   System.out.print("Enter a number: ");
         *   if (sc.hasNextInt()) {
         *       int n = sc.nextInt();
         *   } else {
         *       System.out.println("That's not a number!");
         *   }
         */


        // ============================================================
        // 6. CLOSE THE SCANNER
        // ============================================================
        sc.close();
        // Closing releases the resource. Good practice!
        // Note: After closing, you can't read input anymore.


        // ============================================================
        // KEY TAKEAWAYS:
        // ============================================================
        // - Import java.util.Scanner
        // - Create: Scanner sc = new Scanner(System.in);
        // - Use nextInt(), nextDouble(), nextLine(), next() etc.
        // - ALWAYS add sc.nextLine() after nextInt/nextDouble to fix
        //   the newline bug
        // - next() reads one word, nextLine() reads full line
        // - Close the scanner when done: sc.close()
        // ============================================================
    }
}
