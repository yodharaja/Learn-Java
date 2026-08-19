package chapter10;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * ============================================================
 * LESSON 10.1 — Classic File I/O: Reader & Writer
 * ============================================================
 *
 * Java provides Stream and Character based classes for reading & writing:
 *   - Character Streams (`Reader` / `Writer`): Handle 16-bit Unicode text characters.
 *   - Byte Streams (`InputStream` / `OutputStream`): Handle raw binary data (images, video, audio).
 *
 * Why use `BufferedReader` / `BufferedWriter`?
 *   - Wrapping a `FileReader`/`FileWriter` in a buffered stream drastically reduces
 *     the number of physical disk reads/writes by buffering chunks into RAM memory.
 *   - Provides convenient line-based reading (`readLine()`).
 */
public class FileReadWrite {

    public static void main(String[] args) {
        String testFilePath = "chapter10_sample.txt";

        System.out.println("=== 1. WRITING TEXT TO A FILE (BufferedWriter) ===");
        // FileWriter(path, appendBoolean) -> false overwrites, true appends
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(testFilePath))) {
            writer.write("--- CHAPTER 10 JAVA FILE I/O ---");
            writer.newLine(); // Platform-independent newline
            writer.write("Line 1: Java supports robust text and binary file operations.");
            writer.newLine();
            writer.write("Line 2: BufferedReader improves disk throughput with memory buffers.");
            writer.newLine();
            writer.write("Line 3: Always use try-with-resources to prevent file lock leaks!");
            writer.newLine();
            System.out.println("  ✓ Successfully wrote 3 lines to " + testFilePath);
        } catch (IOException e) {
            System.out.println("  ❌ Write Error: " + e.getMessage());
        }


        System.out.println("\n=== 2. READING TEXT FROM A FILE LINE-BY-LINE (BufferedReader) ===");
        try (BufferedReader reader = new BufferedReader(new FileReader(testFilePath))) {
            String currentLine;
            int lineNumber = 1;
            while ((currentLine = reader.readLine()) != null) {
                System.out.printf("  [%d] %s%n", lineNumber++, currentLine);
            }
        } catch (IOException e) {
            System.out.println("  ❌ Read Error: " + e.getMessage());
        }


        System.out.println("\n=== 3. APPENDING CONTENT TO AN EXISTING FILE ===");
        try (BufferedWriter appendWriter = new BufferedWriter(new FileWriter(testFilePath, true))) {
            appendWriter.write("Line 4 (Appended): Antigravity Java Mastery 2026.");
            appendWriter.newLine();
            System.out.println("  ✓ Appended new line to " + testFilePath);
        } catch (IOException e) {
            System.out.println("  ❌ Append Error: " + e.getMessage());
        }


        // Clean up temporary demo file
        File cleanupFile = new File(testFilePath);
        if (cleanupFile.exists()) {
            boolean deleted = cleanupFile.delete();
            System.out.println("\n🧹 Cleaned up temporary demo file: " + deleted);
        }

        // ============================================================
        // KEY TAKEAWAYS:
        // ============================================================
        // - Wrap `FileReader`/`FileWriter` inside `BufferedReader`/`BufferedWriter` for performance.
        // - `readLine()` returns `null` when end-of-file (EOF) is reached.
        // - Set `FileWriter(path, true)` to append instead of overwriting.
        // ============================================================
    }
}
