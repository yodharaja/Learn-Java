package chapter10;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * ============================================================
 * EXERCISE 10 — Persistent Contact & Data Storage Engine 💾
 * ============================================================
 *
 * Build a Persistent Data Storage Engine for the CRM that supports:
 *   1. CSV Export/Import using `BufferedReader` and `BufferedWriter`.
 *   2. Binary Snapshot Persistence with Object Serialization (`Serializable`).
 *   3. File backup and integrity verification using Java NIO `Files`.
 * ============================================================
 */

class StoredContact implements Serializable {
    private static final long serialVersionUID = 100L;

    private String id;
    private String name;
    private String email;
    private String phone;
    private String department;

    public StoredContact(String id, String name, String email, String phone, String department) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.department = department;
    }

    public String getId() { return id; }
    public String getName() { return name; }
    public String getEmail() { return email; }
    public String getPhone() { return phone; }
    public String getDepartment() { return department; }

    public String toCsvRow() {
        return String.format("%s,%s,%s,%s,%s", id, name, email, phone, department);
    }

    public static StoredContact fromCsvRow(String csvLine) {
        String[] parts = csvLine.split(",");
        if (parts.length >= 5) {
            return new StoredContact(parts[0].trim(), parts[1].trim(), parts[2].trim(), parts[3].trim(), parts[4].trim());
        }
        return null;
    }

    @Override
    public String toString() {
        return String.format("StoredContact[ID=%s, Name='%-15s', Email='%-22s', Dept='%-10s']",
                id, name, email, department);
    }
}

class ContactStorageEngine {

    // 1. Export list to CSV
    public static void exportToCsv(List<StoredContact> contacts, String filePath) throws IOException {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filePath))) {
            bw.write("id,name,email,phone,department");
            bw.newLine();
            for (StoredContact c : contacts) {
                bw.write(c.toCsvRow());
                bw.newLine();
            }
            System.out.println("  ✓ Exported " + contacts.size() + " records to CSV: " + filePath);
        }
    }

    // 2. Import list from CSV
    public static List<StoredContact> importFromCsv(String filePath) throws IOException {
        List<StoredContact> list = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line = br.readLine(); // skip header
            while ((line = br.readLine()) != null) {
                if (!line.trim().isEmpty()) {
                    StoredContact c = StoredContact.fromCsvRow(line);
                    if (c != null) list.add(c);
                }
            }
        }
        System.out.println("  ✓ Imported " + list.size() + " records from CSV: " + filePath);
        return list;
    }

    // 3. Save Binary Snapshot (Serialization)
    public static void saveBinarySnapshot(List<StoredContact> contacts, String binaryFilePath) throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(binaryFilePath))) {
            oos.writeObject(contacts);
            System.out.println("  ✓ Saved full binary snapshot to: " + binaryFilePath);
        }
    }

    // 4. Load Binary Snapshot (Deserialization)
    @SuppressWarnings("unchecked")
    public static List<StoredContact> loadBinarySnapshot(String binaryFilePath) throws IOException, ClassNotFoundException {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(binaryFilePath))) {
            List<StoredContact> loaded = (List<StoredContact>) ois.readObject();
            System.out.println("  ✓ Loaded binary snapshot (" + loaded.size() + " records) from: " + binaryFilePath);
            return loaded;
        }
    }
}

public class Exercise10 {

    public static void main(String[] args) {
        System.out.println("╔══════════════════════════════════════════════════╗");
        System.out.println("║      💾 PERSISTENT CRM FILE STORAGE ENGINE       ║");
        System.out.println("╚══════════════════════════════════════════════════╝");

        String csvPath = "contacts_export.csv";
        String binPath = "contacts_snapshot.dat";

        List<StoredContact> initialRoster = new ArrayList<>();
        initialRoster.add(new StoredContact("C-101", "Yodha Raja", "yodha@domain.com", "555-0101", "Engineering"));
        initialRoster.add(new StoredContact("C-102", "Alex Mercer", "alex@domain.dev", "555-0102", "Operations"));
        initialRoster.add(new StoredContact("C-103", "Sarah Connor", "sarah@cyberdyne.org", "555-0103", "Security"));

        try {
            // Step 1: Export and re-import via CSV
            System.out.println("\n--- 1. CSV EXPORT & RECOVERY TEST ---");
            ContactStorageEngine.exportToCsv(initialRoster, csvPath);

            List<StoredContact> fromCsv = ContactStorageEngine.importFromCsv(csvPath);
            for (StoredContact c : fromCsv) {
                System.out.println("  • " + c);
            }

            // Step 2: Binary Serialization snapshot
            System.out.println("\n--- 2. BINARY SNAPSHOT TEST ---");
            ContactStorageEngine.saveBinarySnapshot(initialRoster, binPath);

            List<StoredContact> fromBin = ContactStorageEngine.loadBinarySnapshot(binPath);
            System.out.println("Restored from binary snapshot matches: " + (fromBin.size() == initialRoster.size()));

            // Step 3: NIO File Info
            System.out.println("\n--- 3. FILE SYSTEM STORAGE METADATA ---");
            Path p = Paths.get(csvPath);
            System.out.printf("  CSV File Size: %d bytes | Absolute: %s%n",
                    Files.size(p), p.toAbsolutePath());

            // Cleanup files
            Files.deleteIfExists(Paths.get(csvPath));
            Files.deleteIfExists(Paths.get(binPath));
            System.out.println("\n🧹 All test storage files cleaned up successfully.");

        } catch (Exception e) {
            System.out.println("❌ Storage Engine Error: " + e.getMessage());
        }
    }
}
