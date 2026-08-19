package chapter2;

/**
 * ============================================================
 * LESSON 2.3 — Operators in Java
 * ============================================================
 *
 * Operators are symbols that perform operations on variables and values.
 *
 * Categories covered:
 *   1. Arithmetic operators     (+, -, *, /, %)
 *   2. Assignment operators     (=, +=, -=, *=, /=, %=)
 *   3. Relational operators     (==, !=, >, <, >=, <=)
 *   4. Logical operators        (&&, ||, !)
 *   5. Unary operators          (++, --, +, -, ~)
 *   6. Bitwise operators        (&, |, ^, <<, >>)
 *   7. Ternary operator         (? :)
 */
public class Operators {

    public static void main(String[] args) {

        // ============================================================
        // 1. ARITHMETIC OPERATORS
        // ============================================================
        System.out.println("=== ARITHMETIC ===");

        int a = 17, b = 5;

        System.out.println(a + " + " + b + " = " + (a + b));   // 22  (addition)
        System.out.println(a + " - " + b + " = " + (a - b));   // 12  (subtraction)
        System.out.println(a + " * " + b + " = " + (a * b));   // 85  (multiplication)
        System.out.println(a + " / " + b + " = " + (a / b));   // 3   (integer division — truncates!)
        System.out.println(a + " % " + b + " = " + (a % b));   // 2   (modulus — remainder)

        // Modulus is super useful:
        // - Check if even:  num % 2 == 0
        // - Check if divisible: num % x == 0
        // - Get last digit: num % 10
        int num = 246;
        System.out.println(num + " is even? " + (num % 2 == 0));  // true
        System.out.println("Last digit of " + num + ": " + (num % 10));  // 6


        // ============================================================
        // 2. ASSIGNMENT OPERATORS
        // ============================================================
        System.out.println("\n=== ASSIGNMENT ===");

        int x = 10;
        System.out.println("x = " + x);        // 10

        x += 5;   // same as x = x + 5
        System.out.println("x += 5  → " + x);  // 15

        x -= 3;   // same as x = x - 3
        System.out.println("x -= 3  → " + x);  // 12

        x *= 2;   // same as x = x * 2
        System.out.println("x *= 2  → " + x);  // 24

        x /= 4;   // same as x = x / 4
        System.out.println("x /= 4  → " + x);  // 6

        x %= 4;   // same as x = x % 4
        System.out.println("x %= 4  → " + x);  // 2


        // ============================================================
        // 3. RELATIONAL (COMPARISON) OPERATORS
        // ============================================================
        // These return boolean (true/false) — used in if/while conditions
        System.out.println("\n=== RELATIONAL ===");

        int p = 10, q = 20;

        System.out.println(p + " == " + q + " → " + (p == q));   // false (equal to?)
        System.out.println(p + " != " + q + " → " + (p != q));   // true  (not equal to?)
        System.out.println(p + " > "  + q + " → " + (p > q));    // false (greater than?)
        System.out.println(p + " < "  + q + " → " + (p < q));    // true  (less than?)
        System.out.println(p + " >= " + q + " → " + (p >= q));   // false (greater or equal?)
        System.out.println(p + " <= " + q + " → " + (p <= q));   // true  (less or equal?)


        // ============================================================
        // 4. LOGICAL OPERATORS
        // ============================================================
        // Combine multiple boolean conditions
        System.out.println("\n=== LOGICAL ===");

        boolean sunny = true;
        boolean warm = false;

        // && (AND) — both must be true
        System.out.println("sunny && warm  → " + (sunny && warm));   // false

        // || (OR) — at least one must be true
        System.out.println("sunny || warm  → " + (sunny || warm));   // true

        // ! (NOT) — flips true ↔ false
        System.out.println("!sunny         → " + (!sunny));          // false

        // Practical example:
        int age = 20;
        boolean hasID = true;
        boolean canEnter = age >= 18 && hasID;
        System.out.println("Age " + age + ", hasID = " + hasID + " → canEnter? " + canEnter);

        // SHORT-CIRCUIT evaluation:
        // && stops if first condition is false (doesn't check second)
        // || stops if first condition is true  (doesn't check second)
        // This is important when the second condition has side effects!


        // ============================================================
        // 5. UNARY OPERATORS
        // ============================================================
        System.out.println("\n=== UNARY (++, --) ===");

        int count = 5;

        // Post-increment: uses current value FIRST, then adds 1
        System.out.println("count++ → " + count++);  // prints 5, then count becomes 6
        System.out.println("count is now: " + count); // 6

        // Pre-increment: adds 1 FIRST, then uses the new value
        System.out.println("++count → " + (++count)); // count becomes 7, prints 7

        // Post-decrement: uses current value FIRST, then subtracts 1
        System.out.println("count-- → " + count--);   // prints 7, then count becomes 6

        // Pre-decrement: subtracts 1 FIRST, then uses the new value
        System.out.println("--count → " + (--count));  // count becomes 5, prints 5


        // ============================================================
        // 6. BITWISE OPERATORS (Advanced — optional for now)
        // ============================================================
        // These work on individual bits (0s and 1s)
        System.out.println("\n=== BITWISE (Advanced) ===");

        int m = 5;  // binary: 0101
        int n = 3;  // binary: 0011

        System.out.println("5 & 3  = " + (m & n));   // 1  (AND: 0001)
        System.out.println("5 | 3  = " + (m | n));   // 7  (OR:  0111)
        System.out.println("5 ^ 3  = " + (m ^ n));   // 6  (XOR: 0110)
        System.out.println("~5     = " + (~m));       // -6 (NOT: flips all bits)
        System.out.println("5 << 1 = " + (m << 1));  // 10 (left shift: multiply by 2)
        System.out.println("5 >> 1 = " + (m >> 1));  // 2  (right shift: divide by 2)


        // ============================================================
        // 7. TERNARY OPERATOR (? :)
        // ============================================================
        // Shorthand for if-else. Syntax: condition ? valueIfTrue : valueIfFalse
        System.out.println("\n=== TERNARY ===");

        int marks = 75;
        String result = marks >= 40 ? "PASS" : "FAIL";
        System.out.println("Marks: " + marks + " → " + result);

        int number1 = 15, number2 = 23;
        int max = number1 > number2 ? number1 : number2;
        System.out.println("Max of " + number1 + " and " + number2 + " = " + max);

        // Nested ternary (use sparingly — can be hard to read!)
        int score = 85;
        String grade = score >= 90 ? "A" :
                       score >= 80 ? "B" :
                       score >= 70 ? "C" :
                       score >= 60 ? "D" : "F";
        System.out.println("Score " + score + " → Grade " + grade);


        // ============================================================
        // 8. OPERATOR PRECEDENCE (Order of Operations)
        // ============================================================
        System.out.println("\n=== PRECEDENCE ===");

        // Java follows math order: (), *, /, %, +, -
        int result1 = 2 + 3 * 4;        // 14 (not 20! multiplication first)
        int result2 = (2 + 3) * 4;      // 20 (parentheses first)
        System.out.println("2 + 3 * 4     = " + result1);
        System.out.println("(2 + 3) * 4   = " + result2);

        // TIP: When in doubt, use parentheses to make your intent clear!


        // ============================================================
        // KEY TAKEAWAYS:
        // ============================================================
        // - % (modulus) gives remainder — great for even/odd checks
        // - += -= *= /= are shorthand assignment operators
        // - == checks equality, = assigns value (common mistake!)
        // - && is AND, || is OR, ! is NOT (short-circuit evaluation)
        // - ++ and -- have pre/post versions (order matters!)
        // - ?: ternary is a compact if-else
        // - Use parentheses when precedence is unclear
        // ============================================================
    }
}
