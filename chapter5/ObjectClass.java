package chapter5;

import java.util.Objects;

/**
 * ============================================================
 * LESSON 5.6 — The Root 'Object' Class
 * ============================================================
 *
 * In Java, EVERY class automatically inherits from java.lang.Object.
 * It is the root of the entire class hierarchy.
 *
 * Key methods inherited from Object:
 *   1. toString(): Returns string representation (default: ClassName@HexHashcode).
 *   2. equals(Object obj): Checks if two references point to the same memory address (default: ==).
 *   3. hashCode(): Returns integer hash of the object (used in HashMaps/HashSets).
 *   4. getClass(): Returns the runtime Class object.
 *
 * The Contract between equals() and hashCode():
 *   - If `a.equals(b)` is TRUE, then `a.hashCode()` MUST equal `b.hashCode()`.
 *   - Always override hashCode() whenever you override equals()!
 */

class Book {
    private String title;
    private String author;
    private String isbn;
    private double price;

    public Book(String title, String author, String isbn, double price) {
        this.title = title;
        this.author = author;
        this.isbn = isbn;
        this.price = price;
    }

    // 1. Overriding toString() to provide a readable description
    @Override
    public String toString() {
        return String.format("Book[ISBN='%s', Title='%s', Author='%s', Price=$%.2f]",
                isbn, title, author, price);
    }

    // 2. Overriding equals() to check logical equality by ISBN
    @Override
    public boolean equals(Object obj) {
        // Step 1: Check reference identity (same object in memory?)
        if (this == obj) return true;

        // Step 2: Check null and ensure exact same class type
        if (obj == null || getClass() != obj.getClass()) return false;

        // Step 3: Cast to Book and compare identifying fields
        Book other = (Book) obj;
        return Objects.equals(this.isbn, other.isbn);
    }

    // 3. Overriding hashCode() using java.util.Objects.hash()
    @Override
    public int hashCode() {
        return Objects.hash(isbn);
    }
}

public class ObjectClass {

    public static void main(String[] args) {
        Book b1 = new Book("Clean Code", "Robert C. Martin", "978-0132350884", 39.99);
        Book b2 = new Book("Clean Code", "Robert C. Martin", "978-0132350884", 45.00); // Same ISBN, diff price
        Book b3 = new Book("Effective Java", "Joshua Bloch", "978-0134685991", 49.99);

        System.out.println("=== 1. TOSTRING() OUTPUT ===");
        // When you pass an object to println, it calls .toString() automatically!
        System.out.println("Book 1: " + b1);
        System.out.println("Book 3: " + b3);

        System.out.println("\n=== 2. EQUALITY: == vs .equals() ===");
        // == checks if b1 and b2 are the same object in heap memory
        System.out.println("b1 == b2        : " + (b1 == b2) + " (different memory locations)");

        // .equals() checks logical equality by ISBN (our custom implementation)
        System.out.println("b1.equals(b2)   : " + b1.equals(b2) + " (same ISBN -> equal!)");
        System.out.println("b1.equals(b3)   : " + b1.equals(b3) + " (different ISBN -> not equal)");

        System.out.println("\n=== 3. HASHCODE CONTRACT ===");
        System.out.println("b1.hashCode()   : " + b1.hashCode());
        System.out.println("b2.hashCode()   : " + b2.hashCode());
        System.out.println("b3.hashCode()   : " + b3.hashCode());
        System.out.println("Are b1 & b2 hash codes identical? " + (b1.hashCode() == b2.hashCode()));

        System.out.println("\n=== 4. GETCLASS() METHOD ===");
        System.out.println("Runtime Class: " + b1.getClass().getName());

        // ============================================================
        // KEY TAKEAWAYS:
        // ============================================================
        // - All classes extend java.lang.Object.
        // - Override toString() for clean debugging/logging.
        // - Override equals() for meaningful content equality.
        // - Always override hashCode() when overriding equals().
        // ============================================================
    }
}
