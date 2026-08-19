package chapter7;

import java.util.Arrays;

/**
 * ============================================================
 * LESSON 7.2 — Advanced String Methods & Regular Expressions
 * ============================================================
 *
 * Strings in Java have built-in support for Regular Expressions (RegEx)
 * and rich text transformation methods.
 *
 * Topics Covered:
 *   1. String Splitting (`split(regex)`) with delimiters.
 *   2. Pattern matching (`matches(regex)`).
 *   3. Replacement with RegEx (`replaceAll()`, `replaceFirst()`).
 *   4. Substrings, trimming, stripping (Java 11+ `strip()`, `stripLeading()`).
 *   5. String joining (`String.join()`).
 *   6. Converting chars <-> byte array / char array.
 */
public class StringMethods {

    public static void main(String[] args) {
        System.out.println("=== 1. SPLITTING WITH REGEX DELIMITERS ===");

        // Splitting a CSV record
        String csvLine = "101,Yodha Raja,Computer Science,95.5,Active";
        String[] fields = csvLine.split(",");
        System.out.println("CSV Fields: " + Arrays.toString(fields));

        // Splitting by multiple whitespace characters or punctuation
        String messyText = "Java,   Python; C++\tGo|Rust";
        String[] languages = messyText.split("[,;\\|\\s]+");
        System.out.println("Extracted Languages: " + Arrays.toString(languages));


        System.out.println("\n=== 2. REGEX PATTERN VALIDATION (matches()) ===");

        // Email validation regex
        String email1 = "yodha.raja@domain.com";
        String email2 = "invalid-email@";
        String emailRegex = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$";

        System.out.printf("  '%s' is valid email? %s%n", email1, email1.matches(emailRegex));
        System.out.printf("  '%s' is valid email? %s%n", email2, email2.matches(emailRegex));

        // Phone number validation: (123) 456-7890 or 123-456-7890
        String phone1 = "123-456-7890";
        String phoneRegex = "\\d{3}-\\d{3}-\\d{4}";
        System.out.printf("  '%s' is valid format? %s%n", phone1, phone1.matches(phoneRegex));


        System.out.println("\n=== 3. REPLACING WITH REGEX ===");
        String rawData = "Product Order #1042 placed on 2026-08-20 for $450.99 (Card: 4111-2222-3333-4444)";

        // Mask credit card numbers except last 4 digits
        String masked = rawData.replaceAll("\\d{4}-\\d{4}-\\d{4}-(\\d{4})", "****-****-****-$1");
        System.out.println("Masked Output: " + masked);

        // Strip all non-digit characters to isolate order number
        String digitsOnly = "Order #98765-ABC".replaceAll("[^0-9]", "");
        System.out.println("Extracted Digits: " + digitsOnly);


        System.out.println("\n=== 4. STRING JOINING (String.join) ===");
        String[] tags = {"Java", "Backend", "SpringBoot", "Microservices"};
        String joinedTags = String.join(" | ", tags);
        System.out.println("Joined Tags: " + joinedTags);


        System.out.println("\n=== 5. CHAR ARRAYS & BYTE ARRAYS ===");
        String sample = "Hello Java";
        char[] charArr = sample.toCharArray();
        System.out.println("Char Array: " + Arrays.toString(charArr));

        // Invert case using char array
        for (int i = 0; i < charArr.length; i++) {
            char c = charArr[i];
            if (Character.isUpperCase(c)) charArr[i] = Character.toLowerCase(c);
            else if (Character.isLowerCase(c)) charArr[i] = Character.toUpperCase(c);
        }
        String inverted = new String(charArr);
        System.out.println("Inverted Case: " + inverted);

        // ============================================================
        // KEY TAKEAWAYS:
        // ============================================================
        // - `split(regex)` parses delimited text into arrays.
        // - `matches(regex)` checks full-string conformance against patterns.
        // - `replaceAll(regex, replacement)` transforms substrings with regex power.
        // - `String.join(delimiter, elements)` merges arrays cleanly.
        // ============================================================
    }
}
