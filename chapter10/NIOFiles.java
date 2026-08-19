package chapter10;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.List;
import java.util.stream.Stream;

/**
 * ============================================================
 * LESSON 10.2 — Modern Java NIO.2 (`Path` & `Files` API)
 * ============================================================
 *
 * Java 7+ and Java 11+ introduced the modern `java.nio.file` package:
 *   - `Path`: Replaces `java.io.File` with an immutable, platform-agnostic representation.
 *   - `Files`: Utility class filled with convenient, high-performance static methods.
 *
 * Modern Convenience Methods:
 *   - `Files.writeString(path, text)` (Java 11+)
 *   - `Files.readString(path)` (Java 11+)
 *   - `Files.readAllLines(path)`
 *   - `Files.lines(path)`: Stream lines lazily without loading the entire file into RAM!
 *   - `Files.exists(path)`, `Files.copy()`, `Files.move()`, `Files.delete()`
 */
public class NIOFiles {

    public static void main(String[] args) {
        Path demoPath = Paths.get("nio_sample.log");

        System.out.println("=== 1. QUICK ONE-LINER WRITING (Java 11+ Files.writeString) ===");
        try {
            String initialContent = "TIMESTAMP=2026-08-20T00:30:00Z | LEVEL=INFO | MSG=Server started\n" +
                                   "TIMESTAMP=2026-08-20T00:30:05Z | LEVEL=WARN | MSG=High memory consumption\n" +
                                   "TIMESTAMP=2026-08-20T00:30:10Z | LEVEL=ERROR | MSG=Connection timeout\n";

            Files.writeString(demoPath, initialContent);
            System.out.println("  ✓ Wrote log file via Files.writeString() to: " + demoPath.toAbsolutePath());
        } catch (IOException e) {
            System.out.println("  ❌ Write failed: " + e.getMessage());
        }


        System.out.println("\n=== 2. QUICK ONE-LINER READING (Files.readString & readAllLines) ===");
        try {
            // Read entire file into single String
            String fileContents = Files.readString(demoPath);
            System.out.println("--- Entire File Content (readString) ---");
            System.out.println(fileContents);

            // Read into List of Strings
            List<String> allLines = Files.readAllLines(demoPath);
            System.out.println("Line count: " + allLines.size());
        } catch (IOException e) {
            System.out.println("  ❌ Read failed: " + e.getMessage());
        }


        System.out.println("=== 3. APPENDING WITH STANDARD OPEN OPTIONS ===");
        try {
            String newLog = "TIMESTAMP=2026-08-20T00:30:15Z | LEVEL=INFO | MSG=Health check OK\n";
            Files.writeString(demoPath, newLog, StandardOpenOption.APPEND);
            System.out.println("  ✓ Appended new log line.");
        } catch (IOException e) {
            System.out.println("  ❌ Append failed: " + e.getMessage());
        }


        System.out.println("\n=== 4. LAZY STREAM-BASED FILE READING (Files.lines()) ===");
        // Ideal for massive multi-gigabyte log files: memory efficient!
        try (Stream<String> logStream = Files.lines(demoPath)) {
            System.out.println("Filtering only ERROR logs via Stream:");
            logStream
                .filter(line -> line.contains("LEVEL=ERROR"))
                .forEach(errLine -> System.out.println("  🚨 Found: " + errLine));
        } catch (IOException e) {
            System.out.println("  ❌ Stream failed: " + e.getMessage());
        }


        // Clean up
        try {
            Files.deleteIfExists(demoPath);
            System.out.println("\n🧹 Cleaned up: " + demoPath);
        } catch (IOException e) {
            System.out.println("  Failed cleanup: " + e.getMessage());
        }

        // ============================================================
        // KEY TAKEAWAYS:
        // ============================================================
        // - Always prefer `Path` and `Files` in modern Java for file operations.
        // - `Files.readString()` and `Files.writeString()` eliminate boilerplate for small-to-medium files.
        // - `Files.lines()` streams lines on-demand without memory bottlenecks.
        // ============================================================
    }
}
