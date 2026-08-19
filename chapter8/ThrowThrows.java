package chapter8;

import java.io.IOException;

/**
 * ============================================================
 * LESSON 8.2 — 'throw' vs 'throws' & Checked vs Unchecked
 * ============================================================
 *
 * Concepts:
 *   - 'throw': Keyword used to explicitly fire an exception object (`throw new IllegalArgumentException("...");`).
 *   - 'throws': Keyword used in method signatures to declare what checked exceptions the method might produce.
 *
 * Checked vs Unchecked Exceptions:
 * ┌──────────────────────┬────────────────────────────────┬───────────────────────────────┐
 * │ Feature              │ Checked Exception              │ Unchecked Exception           │
 * ├──────────────────────┼────────────────────────────────┼───────────────────────────────┤
 * │ Hierarchy            │ Direct child of `Exception`    │ Subclasses of `RuntimeException`│
 * │ Compiler Check       │ FORCED by compiler             │ NOT forced at compile time    │
 * │ Requirement          │ MUST catch OR declare `throws` │ Optional handling             │
 * │ Examples             │ `IOException`, `SQLException`  │ `NullPointerException`, `/ 0` │
 * │ Meaning              │ External recoverable failures  │ Bug / programmer logic error  │
 * └──────────────────────┴────────────────────────────────┴───────────────────────────────┘
 */
public class ThrowThrows {

    // 1. Method throwing an UNCHECKED Exception (RuntimeException)
    // No 'throws' declaration strictly required in signature
    public static void validateAge(int age) {
        if (age < 0) {
            // Throwing unchecked exception directly
            throw new IllegalArgumentException("Age cannot be negative! Received: " + age);
        }
        if (age < 18) {
            throw new SecurityException("Access Denied: Must be at least 18 years old.");
        }
        System.out.println("  ✓ Age validation passed (" + age + " years).");
    }

    // 2. Method declaring and throwing a CHECKED Exception (IOException)
    // MUST declare 'throws IOException' in signature!
    public static void simulateFileTransfer(String filePath) throws IOException {
        System.out.println("  [Network] Attempting to connect to file stream: " + filePath);
        if (filePath == null || !filePath.endsWith(".dat")) {
            throw new IOException("Failed to read file: invalid format or path not found (" + filePath + ")");
        }
        System.out.println("  ✓ File transfer completed successfully.");
    }

    // 3. Method propagating checked exception up the call stack
    public static void processBatch() throws IOException {
        System.out.println("  [Batch Processor] Starting batch transfer job...");
        simulateFileTransfer("corrupted_file.txt"); // Will throw IOException up to main!
    }

    public static void main(String[] args) {
        System.out.println("=== 1. THROWING & CATCHING UNCHECKED EXCEPTIONS ===");
        try {
            validateAge(22);
            validateAge(-5); // Will trigger throw
        } catch (IllegalArgumentException e) {
            System.out.println("  ❌ Caught: " + e.getMessage());
        }

        try {
            validateAge(15); // Will trigger SecurityException
        } catch (SecurityException e) {
            System.out.println("  ❌ Caught: " + e.getMessage());
        }


        System.out.println("\n=== 2. HANDLING DECLARED CHECKED EXCEPTIONS ===");
        try {
            simulateFileTransfer("payload.dat");
            processBatch(); // Propagates IOException
        } catch (IOException e) {
            System.out.println("  ❌ Caught Checked Exception: " + e.getMessage());
        }

        // ============================================================
        // KEY TAKEAWAYS:
        // ============================================================
        // - `throw` is an action (throws an instantiated exception object).
        // - `throws` is a declaration (alerts callers that a checked exception can happen).
        // - Checked exceptions force callers to handle them with `try-catch` or re-declare `throws`.
        // - Unchecked exceptions (`RuntimeException`) represent programming mistakes.
        // ============================================================
    }
}
