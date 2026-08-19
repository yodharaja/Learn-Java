package chapter10;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * ============================================================
 * LESSON 10.3 — File Metadata, Directories & File Management
 * ============================================================
 *
 * Managing files, folder structures, and inspecting file system metadata.
 *
 * Topics Covered:
 *   1. Checking existence (`exists()`, `isFile()`, `isDirectory()`).
 *   2. Creating directory hierarchies (`mkdirs()`, `Files.createDirectories()`).
 *   3. Inspecting metadata: File size in bytes/KB, last modified date, permissions (`canRead()`, `canWrite()`).
 *   4. Listing folder contents (`list()`, `listFiles()`, `Files.walk()`).
 *   5. Renaming and deleting files safely.
 */
public class FileOperations {

    public static void main(String[] args) {
        String testDirName = "demo_sandbox";
        File dir = new File(testDirName);

        System.out.println("=== 1. CREATING DIRECTORIES ===");
        if (!dir.exists()) {
            boolean created = dir.mkdirs();
            System.out.println("  Created directory hierarchy: " + created);
        } else {
            System.out.println("  Directory already exists.");
        }


        System.out.println("\n=== 2. CREATING FILES & INSPECTING METADATA ===");
        File file1 = new File(dir, "report_2026.csv");
        File file2 = new File(dir, "notes.txt");

        try {
            file1.createNewFile();
            file2.createNewFile();

            // Write small payload to file1
            Files.writeString(file1.toPath(), "id,name,score\n1,Yodha,100\n2,Raja,98\n");

            System.out.println("File Name         : " + file1.getName());
            System.out.println("Absolute Path     : " + file1.getAbsolutePath());
            System.out.println("Size in Bytes     : " + file1.length() + " bytes");
            System.out.println("Is File?          : " + file1.isFile());
            System.out.println("Is Directory?     : " + file1.isDirectory());
            System.out.println("Can Read/Write?   : " + file1.canRead() + " / " + file1.canWrite());

            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            System.out.println("Last Modified     : " + sdf.format(new Date(file1.lastModified())));
        } catch (IOException e) {
            System.out.println("  ❌ IO Error: " + e.getMessage());
        }


        System.out.println("\n=== 3. LISTING DIRECTORY CONTENTS ===");
        File[] directoryContents = dir.listFiles();
        if (directoryContents != null) {
            System.out.printf("Listing %d items inside '%s':%n", directoryContents.length, dir.getName());
            for (File f : directoryContents) {
                System.out.printf("  %s %-20s (%,d bytes)%n",
                        f.isDirectory() ? "📁" : "📄", f.getName(), f.length());
            }
        }


        System.out.println("\n=== 4. CLEANING UP SANDBOX DIRECTORY ===");
        if (directoryContents != null) {
            for (File f : directoryContents) {
                f.delete();
            }
        }
        boolean dirDeleted = dir.delete();
        System.out.println("  Directory cleanup completed: " + dirDeleted);

        // ============================================================
        // KEY TAKEAWAYS:
        // ============================================================
        // - Use `file.exists()` and `file.isFile()` before attempting file access.
        // - `dir.mkdirs()` creates missing parent directories recursively.
        // - A directory cannot be deleted with `delete()` until all files inside it are deleted first.
        // ============================================================
    }
}
