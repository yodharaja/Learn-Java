package chapter7;

import java.util.Arrays;
import java.util.Comparator;

/**
 * ============================================================
 * LESSON 7.4 — Arrays of Objects & Custom Sorting
 * ============================================================
 *
 * Arrays can store objects as well as primitives.
 *
 * Topics Covered:
 *   1. Allocating and initializing object arrays.
 *   2. Handling `NullPointerException` in partially filled arrays.
 *   3. Natural Sorting with `Comparable<T>` interface (`compareTo()`).
 *   4. Custom Multi-Criteria Sorting with `Comparator<T>` (Lambdas & method references).
 *   5. Searching object arrays with binary search.
 */

class Product implements Comparable<Product> {
    private String id;
    private String name;
    private double price;
    private double rating;

    public Product(String id, String name, double price, double rating) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.rating = rating;
    }

    public String getId() { return id; }
    public String getName() { return name; }
    public double getPrice() { return price; }
    public double getRating() { return rating; }

    // 1. Natural Sorting: By Price Ascending (implements Comparable<Product>)
    @Override
    public int compareTo(Product other) {
        return Double.compare(this.price, other.price);
    }

    @Override
    public String toString() {
        return String.format("Product[ID=%s, Name='%-15s', Price=$%6.2f, Rating=%.1f★]",
                id, name, price, rating);
    }
}

public class ArrayOfObjects {

    public static void main(String[] args) {
        System.out.println("=== 1. CREATING & POPULATING AN ARRAY OF OBJECTS ===");
        Product[] inventory = {
            new Product("P101", "Mechanical KB", 129.99, 4.8),
            new Product("P102", "Wireless Mouse", 49.50, 4.5),
            new Product("P103", "4K Monitor", 349.00, 4.9),
            new Product("P104", "USB-C Hub", 29.99, 4.2),
            new Product("P105", "Noise-Canceling", 199.99, 4.7)
        };

        System.out.println("Original Inventory:");
        printInventory(inventory);


        System.out.println("\n=== 2. NATURAL SORTING (COMPARABLE - PRICE ASCENDING) ===");
        // Uses Product.compareTo()
        Arrays.sort(inventory);
        printInventory(inventory);


        System.out.println("\n=== 3. CUSTOM SORTING (COMPARATOR - RATING DESCENDING) ===");
        // Sorting by Rating High -> Low using Comparator
        Arrays.sort(inventory, new Comparator<Product>() {
            @Override
            public int compare(Product p1, Product p2) {
                return Double.compare(p2.getRating(), p1.getRating());
            }
        });
        printInventory(inventory);


        System.out.println("\n=== 4. MODERN COMPARATOR WITH LAMBDAS (NAME ALPHABETICAL) ===");
        // Modern Java syntax: Comparator.comparing()
        Arrays.sort(inventory, Comparator.comparing(Product::getName));
        printInventory(inventory);


        System.out.println("\n=== 5. MULTI-LEVEL COMPARATOR ===");
        // Create duplicate price items to test secondary sorting
        Product[] items = {
            new Product("A1", "Alpha", 50.0, 4.2),
            new Product("B2", "Beta", 20.0, 4.9),
            new Product("C3", "Gamma", 50.0, 4.8) // Same price as Alpha, higher rating
        };

        // Sort by Price ASC, then by Rating DESC
        Arrays.sort(items, Comparator.comparingDouble(Product::getPrice)
                                     .thenComparing((p1, p2) -> Double.compare(p2.getRating(), p1.getRating())));

        System.out.println("Multi-level Sorted Items (Price ASC, Rating DESC):");
        printInventory(items);

        // ============================================================
        // KEY TAKEAWAYS:
        // ============================================================
        // - Implement `Comparable<T>` for the default/natural sorting rule.
        // - Use `Comparator<T>` or `Comparator.comparing(...)` for flexible alternate sorting rules.
        // - Chaining comparators with `.thenComparing()` allows multi-key sorting.
        // ============================================================
    }

    private static void printInventory(Product[] arr) {
        for (Product p : arr) {
            System.out.println("  " + p);
        }
    }
}
