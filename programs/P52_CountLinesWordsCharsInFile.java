package programs;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * ============================================================
 * PROGRAM 52: Count Lines, Words, and Characters in a File
 * ============================================================
 * Problem: WAP to count the total lines, total words, and
 * total characters inside a specified text file.
 * ============================================================
 */
public class P52_CountLinesWordsCharsInFile {

    public static void countFileMetrics(String filePath) {
        int lines = 0, words = 0, characters = 0;

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                lines++;
                characters += line.length();

                String[] tokens = line.trim().split("\\s+");
                if (tokens.length > 0 && !tokens[0].isEmpty()) {
                    words += tokens.length;
                }
            }

            System.out.println("=== FILE METRICS SUMMARY ===");
            System.out.printf("  Total Lines      : %d%n", lines);
            System.out.printf("  Total Words      : %d%n", words);
            System.out.printf("  Total Characters : %d%n", characters);

        } catch (IOException e) {
            System.out.println("❌ Error reading file: " + e.getMessage());
        }
    }

    public static void main(String[] args) throws IOException {
        String sampleFile = "metric_test.txt";

        // Create sample file
        try (FileWriter fw = new FileWriter(sampleFile)) {
            fw.write("First line with four words.\n");
            fw.write("Second line.\n");
            fw.write("Third line with several more words in Java.\n");
        }

        countFileMetrics(sampleFile);

        // Cleanup
        new File(sampleFile).delete();
    }
}
