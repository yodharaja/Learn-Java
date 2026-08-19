package chapter2;

/*
 ============================================================
  LESSON 2.1 — Java Data Types
 ============================================================

  Java is a STATICALLY TYPED language.
  That means every variable must have a declared type BEFORE you use it.

  There are two categories of data types:
   1. Primitive types (8 total) — store simple values directly
   2. Reference types — store addresses pointing to objects (String, arrays, classes)

  This lesson covers all 8 primitive types.
*/

public class DataTypes {

    public static void main(String[] args) {

        // ============================================================
        // 1. INTEGER TYPES (whole numbers — no decimal point)
        // ============================================================

        // byte: 1 byte (8 bits), range: -128 to 127
        // Use when you know the value is small (saves memory)
        byte myByte = 100;
        System.out.println("byte  : " + myByte + "  (size: 1 byte, range: -128 to 127)");

        // short: 2 bytes (16 bits), range: -32,768 to 32,767
        short myShort = 30000;
        System.out.println("short : " + myShort + "  (size: 2 bytes, range: -32768 to 32767)");

        // int: 4 bytes (32 bits), range: about -2.1 billion to 2.1 billion
        // This is the DEFAULT and MOST USED integer type
        int myInt = 2_000_000_000; // underscores make large numbers readable!
        System.out.println("int   : " + myInt + "  (size: 4 bytes — the default choice)");

        // long: 8 bytes (64 bits), range: huge!
        // You MUST add 'L' at the end of the number
        long myLong = 9_000_000_000_000_000_000L;
        System.out.println("long  : " + myLong + "  (size: 8 bytes — needs 'L' suffix)");


        // ============================================================
        // 2. FLOATING-POINT TYPES (numbers with decimals)
        // ============================================================

        // float: 4 bytes, ~6-7 decimal digits of precision
        // You MUST add 'f' at the end
        float myFloat = 3.14159f;
        System.out.println("float : " + myFloat + "  (size: 4 bytes — needs 'f' suffix)");

        // double: 8 bytes, ~15 decimal digits of precision
        // This is the DEFAULT for decimals
        double myDouble = 3.141592653589793;
        System.out.println("double: " + myDouble + "  (size: 8 bytes — the default choice)");


        // ============================================================
        // 3. CHARACTER TYPE
        // ============================================================

        // char: 2 bytes, stores a SINGLE character using Unicode
        // Uses SINGLE quotes ' '  (not double quotes " ")
        char myChar = 'A';
        char heartEmoji = '\u2764'; // Unicode for ❤
        System.out.println("char  : " + myChar + "  (size: 2 bytes, single quotes)");
        System.out.println("char  : " + heartEmoji + "  (Unicode characters work too!)");


        // ============================================================
        // 4. BOOLEAN TYPE
        // ============================================================

        // boolean: 1 bit of info, but JVM uses more internally
        // Only two possible values: true or false
        boolean isJavaFun = true;
        boolean isHard = false;
        System.out.println("boolean: isJavaFun = " + isJavaFun);
        System.out.println("boolean: isHard = " + isHard);


        // ============================================================
        // 5. DEFAULT VALUES (when declared as class fields, NOT local)
        // ============================================================
        /*
         * Type        Default Value
         * ----        -------------
         * byte        0
         * short       0
         * int         0
         * long        0L
         * float       0.0f
         * double      0.0
         * char        '\u0000' (null character)
         * boolean     false
         *
         * NOTE: Local variables (inside methods) do NOT get default values.
         *       You MUST initialize them before use, or you'll get a compile error.
         */


        // ============================================================
        // 6. TYPE SIZES SUMMARY
        // ============================================================
        System.out.println("\n--- SIZE SUMMARY ---");
        System.out.println("byte   = " + Byte.SIZE + " bits");
        System.out.println("short  = " + Short.SIZE + " bits");
        System.out.println("int    = " + Integer.SIZE + " bits");
        System.out.println("long   = " + Long.SIZE + " bits");
        System.out.println("float  = " + Float.SIZE + " bits");
        System.out.println("double = " + Double.SIZE + " bits");
        System.out.println("char   = " + Character.SIZE + " bits");

        // ============================================================
        // 7. MIN & MAX VALUES
        // ============================================================
        System.out.println("\n--- RANGE SUMMARY ---");
        System.out.println("int    min = " + Integer.MIN_VALUE);
        System.out.println("int    max = " + Integer.MAX_VALUE);
        System.out.println("long   min = " + Long.MIN_VALUE);
        System.out.println("long   max = " + Long.MAX_VALUE);
        System.out.println("double max = " + Double.MAX_VALUE);


        // ============================================================
        // KEY TAKEAWAYS:
        // ============================================================
        // - Use 'int' for most whole numbers
        // - Use 'double' for most decimals
        // - Use 'boolean' for true/false flags
        // - Use 'String' (not a primitive!) for text
        // - Add 'L' suffix for long, 'f' suffix for float
        // ============================================================
    }
}
