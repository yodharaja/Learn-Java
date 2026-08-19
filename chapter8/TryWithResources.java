package chapter8;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.StringReader;
import java.io.IOException;

/**
 * ============================================================
 * LESSON 8.4 — Try-With-Resources & AutoCloseable
 * ============================================================
 *
 * Before Java 7, closing resources (files, sockets, DB connections)
 * required verbose, error-prone nested `finally` blocks.
 *
 * `try-with-resources` (Java 7+):
 *   - Automatically closes ANY resource that implements the `java.lang.AutoCloseable` interface.
 *   - Resources are closed in REVERSE order of their declaration.
 *   - Closes resources even if an exception occurs during execution.
 *
 * Syntax:
 *   try (Resource res1 = new Resource(); Resource res2 = new Resource()) {
 *       // use resources
 *   } catch (Exception e) {
 *       // handle
 *   }
 */

// Custom Resource implementing AutoCloseable
class DatabaseConnection implements AutoCloseable {
    private String connectionUrl;

    public DatabaseConnection(String url) {
        this.connectionUrl = url;
        System.out.println("  🔌 [DB Resource] Connected to " + url);
    }

    public void executeQuery(String sql) {
        System.out.println("  ⚡ [DB Resource] Running query: " + sql);
    }

    @Override
    public void close() {
        System.out.println("  🛑 [DB Resource] Closing connection to " + connectionUrl + " (Safely Released!)");
    }
}

public class TryWithResources {

    public static void main(String[] args) {
        System.out.println("=== 1. TRY-WITH-RESOURCES WITH BUILT-IN READERS ===");
        String sampleData = "Line 1: Java Basics\nLine 2: OOP Principles\nLine 3: Exception Handling";

        // BufferedReader automatically closed at the end of the try block!
        try (BufferedReader reader = new BufferedReader(new StringReader(sampleData))) {
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println("  Read: " + line);
            }
        } catch (IOException e) {
            System.out.println("  ❌ IO Error: " + e.getMessage());
        }


        System.out.println("\n=== 2. CUSTOM AUTOCLOSEABLE RESOURCE IN ACTION ===");
        try (DatabaseConnection db = new DatabaseConnection("jdbc:mysql://localhost:3306/production_db")) {
            db.executeQuery("SELECT * FROM users WHERE active = 1");
            // No finally block needed! 'db.close()' is automatically invoked here!
        } catch (Exception e) {
            System.out.println("  ❌ DB Failure: " + e.getMessage());
        }


        System.out.println("\n=== 3. MULTIPLE RESOURCES (REVERSE CLOSING ORDER) ===");
        try (
            DatabaseConnection db1 = new DatabaseConnection("db://server-primary");
            DatabaseConnection db2 = new DatabaseConnection("db://server-backup")
        ) {
            System.out.println("  Syncing primary and backup databases...");
            // db2 is closed first, then db1 (LIFO order)!
        }

        // ============================================================
        // KEY TAKEAWAYS:
        // ============================================================
        // - Any class implementing `AutoCloseable` can be managed by `try-with-resources`.
        // - Replaces boilerplate `finally { res.close(); }` code.
        // - Multiple resources are separated by semicolons and closed in reverse order.
        // ============================================================
    }
}
