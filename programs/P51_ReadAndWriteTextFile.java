package programs;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * ============================================================
 * PROGRAM 51: Read and Write Text File
 * ============================================================
 * Problem: WAP to write multiple lines of text to a disk file
 * using `BufferedWriter` and read it back line-by-line using `BufferedReader`.
 * ============================================================
 */
public class P51_ReadAndWriteTextFile {

    public static void main(String[] args) {
        String filename = "program51_output.txt";

        // 1. Write to File
        System.out.println("=== 1. WRITING TO FILE ===");
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filename))) {
            bw.write("Java Mastery Course - Program 51");
            bw.newLine();
            bw.write("File I/O operations are essential for persistence.");
            bw.newLine();
            bw.write("Try-with-resources prevents file lock leaks.");
            bw.newLine();
            System.out.println("  ✓ Successfully created and wrote to " + filename);
        } catch (IOException e) {
            System.out.println("  ❌ Write failed: " + e.getMessage());
        }

        // 2. Read from File
        System.out.println("\n=== 2. READING FROM FILE ===");
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            int lineNo = 1;
            while ((line = br.readLine()) != null) {
                System.out.printf("  Line %d: %s%n", lineNo++, line);
            }
        } catch (IOException e) {
            System.out.println("  ❌ Read failed: " + e.getMessage());
        }

        // Cleanup
        new File(filename).delete();
    }
}
