package chapter2;

/**
 * ============================================================
 * LESSON 2.2 — Type Casting in Java
 * ============================================================
 *
 * Type casting = converting a value from one data type to another.
 *
 * Two kinds:
 *   1. WIDENING (implicit) — small type → big type (automatic, safe)
 *   2. NARROWING (explicit) — big type → small type (manual, risky)
 *
 * Size order: byte → short → int → long → float → double
 *             (smallest)                         (largest)
 */
public class TypeCasting {

    public static void main(String[] args) {

        // ============================================================
        // 1. WIDENING CASTING (Implicit / Automatic)
        // ============================================================
        // Java does this AUTOMATICALLY because no data is lost.
        // Going from a smaller type to a bigger type.

        System.out.println("=== WIDENING (Automatic) ===");

        int myInt = 42;
        double myDouble = myInt;  // int → double (automatic!)
        System.out.println("int 42 → double: " + myDouble);  // 42.0

        byte myByte = 10;
        int fromByte = myByte;    // byte → int (automatic!)
        System.out.println("byte 10 → int: " + fromByte);    // 10

        int score = 95;
        long bigScore = score;    // int → long (automatic!)
        System.out.println("int 95 → long: " + bigScore);    // 95

        // The full widening chain:
        // byte → short → int → long → float → double
        byte a = 1;
        short b = a;     // byte → short
        int c = b;       // short → int
        long d = c;      // int → long
        float e = d;     // long → float
        double f = e;    // float → double
        System.out.println("byte 1 through the chain → double: " + f);


        // ============================================================
        // 2. NARROWING CASTING (Explicit / Manual)
        // ============================================================
        // You must tell Java explicitly with (type) syntax.
        // WARNING: Data may be LOST or TRUNCATED!

        System.out.println("\n=== NARROWING (Manual) ===");

        double pi = 3.14159;
        int truncatedPi = (int) pi;  // double → int (decimal part is LOST!)
        System.out.println("double 3.14159 → int: " + truncatedPi);  // 3 (not rounded — truncated!)

        int bigNumber = 130;
        byte smallNumber = (byte) bigNumber;  // int → byte (overflow!)
        System.out.println("int 130 → byte: " + smallNumber);  // -126 (overflow wraps around!)
        // Why -126? byte range is -128 to 127. 130 overflows past 127.

        long bigLong = 100000L;
        int fromLong = (int) bigLong;  // long → int (safe here because value fits)
        System.out.println("long 100000 → int: " + fromLong);  // 100000

        double price = 99.99;
        int roundedPrice = (int) price;  // Truncates, does NOT round!
        System.out.println("double 99.99 → int: " + roundedPrice);  // 99


        // ============================================================
        // 3. CHAR ↔ INT CASTING
        // ============================================================
        // Characters are stored as numbers (Unicode/ASCII values)

        System.out.println("\n=== CHAR ↔ INT ===");

        char letter = 'A';
        int asciiValue = letter;          // char → int (widening, automatic)
        System.out.println("'A' as int: " + asciiValue);     // 65

        int number = 90;
        char fromNumber = (char) number;  // int → char (narrowing, manual)
        System.out.println("int 90 as char: " + fromNumber); // Z

        // Fun trick: convert lowercase to uppercase
        char lower = 'z';
        char upper = (char) (lower - 32);  // ASCII: 'a' is 97, 'A' is 65 (difference = 32)
        System.out.println("'" + lower + "' to uppercase: '" + upper + "'");


        // ============================================================
        // 4. STRING TO NUMBER CONVERSIONS (Parsing)
        // ============================================================
        // These are NOT casting — they use wrapper class methods.

        System.out.println("\n=== STRING → NUMBER (Parsing) ===");

        String numStr = "42";
        int parsed = Integer.parseInt(numStr);    // String → int
        System.out.println("String \"42\" → int: " + parsed);

        String decStr = "3.14";
        double parsedDouble = Double.parseDouble(decStr);  // String → double
        System.out.println("String \"3.14\" → double: " + parsedDouble);

        // Number → String
        int num = 100;
        String str = String.valueOf(num);    // int → String
        String str2 = Integer.toString(num); // also works
        String str3 = num + "";              // concatenation trick (simplest)
        System.out.println("int 100 → String: \"" + str + "\"");


        // ============================================================
        // 5. COMMON PITFALL — INTEGER DIVISION
        // ============================================================

        System.out.println("\n=== PITFALL: Integer Division ===");

        int x = 7;
        int y = 2;
        System.out.println("7 / 2 = " + (x / y));          // 3 (not 3.5!)
        System.out.println("7 / 2.0 = " + (x / 2.0));      // 3.5 (one operand is double)
        System.out.println("(double) 7 / 2 = " + ((double) x / y));  // 3.5 (cast first)

        // ============================================================
        // KEY TAKEAWAYS:
        // ============================================================
        // - Widening (small → big) is automatic and safe
        // - Narrowing (big → small) needs explicit (type) cast, can lose data
        // - (int) truncates decimals — does NOT round
        // - Integer / Integer = Integer (use double for decimal results)
        // - Use Integer.parseInt() to convert String → int
        // ============================================================
    }
}
