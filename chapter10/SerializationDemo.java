package chapter10;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/**
 * ============================================================
 * LESSON 10.4 — Object Serialization & Deserialization
 * ============================================================
 *
 * SERIALIZATION: Converting an in-memory Java Object into a byte stream
 * that can be saved to a file, sent over network, or stored in a cache.
 *
 * DESERIALIZATION: Reconstructing the byte stream back into an active Java Object in RAM.
 *
 * Key Concepts:
 *   1. `implements Serializable`: Marker interface (has no methods) that authorizes serialization.
 *   2. `serialVersionUID`: Unique version ID ensuring sender and receiver have compatible class definitions.
 *   3. `transient` keyword: Marks sensitive or non-serializable fields to be EXCLUDED during serialization.
 *      - Transient fields reset to their default value (`null`, `0`, `false`) on deserialization.
 *   4. `ObjectOutputStream` (`writeObject()`) & `ObjectInputStream` (`readObject()`).
 */

class UserProfile implements Serializable {
    // Unique version identifier for serialization compatibility
    private static final long serialVersionUID = 1L;

    private String userId;
    private String username;
    private String email;

    // 'transient' field: will NEVER be serialized to disk (security & privacy)
    private transient String rawPassword;
    private transient int tempSessionToken;

    public UserProfile(String userId, String username, String email, String rawPassword) {
        this.userId = userId;
        this.username = username;
        this.email = email;
        this.rawPassword = rawPassword;
        this.tempSessionToken = 998877;
    }

    @Override
    public String toString() {
        return String.format("UserProfile[ID=%s, User='%s', Email='%s', Pass='%s', Token=%d]",
                userId, username, email, rawPassword, tempSessionToken);
    }
}

public class SerializationDemo {

    public static void main(String[] args) {
        String binaryStorageFile = "user_profile.ser";

        UserProfile originalUser = new UserProfile("USR-101", "yodha_master", "yodha@learning.java", "superSecret123!");
        System.out.println("=== 1. ORIGINAL OBJECT BEFORE SERIALIZATION ===");
        System.out.println("Original: " + originalUser);


        System.out.println("\n=== 2. SERIALIZING OBJECT TO DISK (ObjectOutputStream) ===");
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(binaryStorageFile))) {
            oos.writeObject(originalUser);
            System.out.println("  ✓ Object serialized and persisted to " + binaryStorageFile);
        } catch (IOException e) {
            System.out.println("  ❌ Serialization failed: " + e.getMessage());
        }


        System.out.println("\n=== 3. DESERIALIZING OBJECT FROM DISK (ObjectInputStream) ===");
        UserProfile restoredUser = null;
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(binaryStorageFile))) {
            // readObject() returns Object, must downcast to target class
            restoredUser = (UserProfile) ois.readObject();
            System.out.println("  ✓ Object restored successfully from disk byte stream!");
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("  ❌ Deserialization failed: " + e.getMessage());
        }

        System.out.println("\nRestored Object State:");
        System.out.println("Restored: " + restoredUser);
        System.out.println("\nNotice:");
        System.out.println("  - userId, username, and email were successfully preserved.");
        System.out.println("  - rawPassword is null and tempSessionToken is 0 because they were marked 'transient'!");


        // Clean up
        File f = new File(binaryStorageFile);
        if (f.exists()) f.delete();

        // ============================================================
        // KEY TAKEAWAYS:
        // ============================================================
        // - Implement `Serializable` to enable object persistence.
        // - Always declare a static `serialVersionUID`.
        // - Use `transient` on sensitive fields like passwords, secrets, or temporary cache state.
        // ============================================================
    }
}
