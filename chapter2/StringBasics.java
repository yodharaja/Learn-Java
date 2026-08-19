package chapter2;

/**
 * ============================================================
 * LESSON 2.4 — String Basics in Java
 * ============================================================
 *
 * String is NOT a primitive type — it's a CLASS (reference type).
 * But Java treats it specially:
 *   - You can create strings without 'new' keyword
 *   - Strings are IMMUTABLE (once created, cannot be changed)
 *   - String literals are stored in a special "String Pool"
 *
 * This lesson covers the most important String methods you'll use daily.
 */
public class StringBasics {

    public static void main(String[] args) {

        // ============================================================
        // 1. CREATING STRINGS
        // ============================================================
        System.out.println("=== CREATING STRINGS ===");

        // Method 1: String literal (preferred — uses String Pool)
        String name = "Yodha";

        // Method 2: Using 'new' keyword (creates new object in heap)
        String name2 = new String("Yodha");

        System.out.println("name  = " + name);
        System.out.println("name2 = " + name2);

        // Both have same content, but are stored differently in memory.
        // We'll explore this difference with == vs .equals() below.


        // ============================================================
        // 2. STRING LENGTH
        // ============================================================
        System.out.println("\n=== LENGTH ===");

        String message = "Hello, Java!";
        System.out.println("\"" + message + "\" has length: " + message.length());  // 12
        // Note: length() is a METHOD (has parentheses)
        //       Arrays use .length (no parentheses) — this is a common gotcha!


        // ============================================================
        // 3. ACCESSING CHARACTERS
        // ============================================================
        System.out.println("\n=== charAt() ===");

        String word = "JAVA";
        // Index:       0123   (0-based, just like arrays!)
        System.out.println("charAt(0) = " + word.charAt(0));  // J
        System.out.println("charAt(1) = " + word.charAt(1));  // A
        System.out.println("charAt(2) = " + word.charAt(2));  // V
        System.out.println("charAt(3) = " + word.charAt(3));  // A

        // Print each character:
        System.out.print("Characters: ");
        for (int i = 0; i < word.length(); i++) {
            System.out.print(word.charAt(i) + " ");
        }
        System.out.println();


        // ============================================================
        // 4. STRING CONCATENATION
        // ============================================================
        System.out.println("\n=== CONCATENATION ===");

        String first = "Yodha";
        String last = "Raja";

        // Method 1: + operator
        String full1 = first + " " + last;
        System.out.println("Using + : " + full1);

        // Method 2: concat()
        String full2 = first.concat(" ").concat(last);
        System.out.println("Using concat(): " + full2);

        // Concatenation with numbers:
        int age = 20;
        System.out.println(first + " is " + age + " years old");
        // Java automatically converts int to String during concatenation

        // Watch out for this common pitfall:
        System.out.println("1 + 2 = " + 1 + 2);      // "1 + 2 = 12" (string concat!)
        System.out.println("1 + 2 = " + (1 + 2));     // "1 + 2 = 3"  (parentheses force addition first)


        // ============================================================
        // 5. STRING COMPARISON
        // ============================================================
        System.out.println("\n=== COMPARISON ===");

        String s1 = "Hello";
        String s2 = "Hello";
        String s3 = new String("Hello");

        // == checks if REFERENCES point to the same object in memory
        System.out.println("s1 == s2 : " + (s1 == s2));   // true (same object in String Pool)
        System.out.println("s1 == s3 : " + (s1 == s3));   // false (s3 is a new object in heap)

        // .equals() checks if the CONTENT is the same (USE THIS!)
        System.out.println("s1.equals(s2) : " + s1.equals(s2));   // true
        System.out.println("s1.equals(s3) : " + s1.equals(s3));   // true

        // .equalsIgnoreCase() — ignores uppercase/lowercase
        System.out.println("\"hello\".equalsIgnoreCase(\"HELLO\") : " +
                "hello".equalsIgnoreCase("HELLO"));  // true

        // ⚠️ GOLDEN RULE: Always use .equals() to compare strings, NEVER ==

        // compareTo() — for sorting (returns int)
        // < 0 means first comes before second alphabetically
        // = 0 means equal
        // > 0 means first comes after second
        System.out.println("\"Apple\".compareTo(\"Banana\") : " + "Apple".compareTo("Banana")); // negative
        System.out.println("\"Banana\".compareTo(\"Apple\") : " + "Banana".compareTo("Apple")); // positive
        System.out.println("\"Apple\".compareTo(\"Apple\") : " + "Apple".compareTo("Apple"));   // 0


        // ============================================================
        // 6. USEFUL STRING METHODS
        // ============================================================
        System.out.println("\n=== USEFUL METHODS ===");

        String text = "  Hello, World!  ";

        // Case conversion
        System.out.println("toUpperCase() : " + text.toUpperCase());
        System.out.println("toLowerCase() : " + text.toLowerCase());

        // Trim whitespace from both ends
        System.out.println("trim()        : \"" + text.trim() + "\"");

        // Substring — extract part of a string
        String hello = "Hello, World!";
        System.out.println("substring(0, 5) : " + hello.substring(0, 5));   // "Hello"
        System.out.println("substring(7)    : " + hello.substring(7));       // "World!"
        // substring(start, end) — start is inclusive, end is exclusive

        // indexOf — find position of a character or substring
        System.out.println("indexOf('W')     : " + hello.indexOf('W'));       // 7
        System.out.println("indexOf(\"World\") : " + hello.indexOf("World")); // 7
        System.out.println("indexOf('Z')     : " + hello.indexOf('Z'));       // -1 (not found)

        // contains — check if substring exists
        System.out.println("contains(\"World\") : " + hello.contains("World")); // true
        System.out.println("contains(\"Java\")  : " + hello.contains("Java"));  // false

        // startsWith / endsWith
        System.out.println("startsWith(\"Hello\") : " + hello.startsWith("Hello"));  // true
        System.out.println("endsWith(\"!\")       : " + hello.endsWith("!"));        // true

        // replace
        System.out.println("replace('l', 'L')     : " + hello.replace('l', 'L'));
        System.out.println("replace(\"World\", \"Java\") : " + hello.replace("World", "Java"));

        // isEmpty / isBlank (isBlank is Java 11+)
        System.out.println("\"\" is empty : " + "".isEmpty());     // true
        System.out.println("\" \" is empty : " + " ".isEmpty());   // false
        System.out.println("\" \" is blank : " + " ".isBlank());   // true (only whitespace)


        // ============================================================
        // 7. STRING IMMUTABILITY
        // ============================================================
        System.out.println("\n=== IMMUTABILITY ===");

        String original = "Hello";
        String modified = original.toUpperCase();

        System.out.println("original : " + original);  // "Hello" (unchanged!)
        System.out.println("modified : " + modified);   // "HELLO" (new string)
        // .toUpperCase() did NOT change original — it created a NEW string.
        // ALL String methods return new strings. The original is never modified.


        // ============================================================
        // 8. STRING FORMATTING
        // ============================================================
        System.out.println("\n=== FORMATTING ===");

        String formatted = String.format("Name: %s, Age: %d, GPA: %.2f", "Yodha", 20, 9.567);
        System.out.println(formatted);  // Name: Yodha, Age: 20, GPA: 9.57

        // Common format specifiers:
        // %s  — String
        // %d  — integer (int, long)
        // %f  — floating point (float, double)
        // %.2f — float with 2 decimal places
        // %n  — newline (platform-independent)
        // %b  — boolean

        // You can also use printf (prints directly, no println needed):
        System.out.printf("Score: %d out of %d (%.1f%%)%n", 85, 100, 85.0);


        // ============================================================
        // KEY TAKEAWAYS:
        // ============================================================
        // - Strings are IMMUTABLE — methods return new strings
        // - Use .equals() to compare strings, NEVER ==
        // - .length() has parentheses (unlike array .length)
        // - Indexing is 0-based (just like arrays)
        // - substring(start, end) — start inclusive, end exclusive
        // - String.format() for clean string building
        // ============================================================
    }
}
