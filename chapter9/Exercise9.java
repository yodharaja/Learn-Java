package chapter9;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

/**
 * ============================================================
 * EXERCISE 9 — Contact Directory & Smart CRM Engine 📇
 * ============================================================
 *
 * Build a Contact Management & Address Book CRM leveraging the Collections Framework:
 *
 * Data Structures Used:
 *   - `Map<String, Contact>`: Fast O(1) primary lookup by Email/ID.
 *   - `Map<String, List<Contact>>`: Category indexing ("Family", "Work", "VIP").
 *   - `Set<String>`: Tagging engine with unique interest tags ("Java", "Cloud", "Gaming").
 *   - `List<Contact>`: Dynamic lists for sorted directory listings & search results.
 * ============================================================
 */

class Contact {
    private String id;
    private String name;
    private String email;
    private String phone;
    private String category;
    private Set<String> tags;

    public Contact(String id, String name, String email, String phone, String category) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.category = category;
        this.tags = new HashSet<>();
    }

    public String getId() { return id; }
    public String getName() { return name; }
    public String getEmail() { return email; }
    public String getPhone() { return phone; }
    public String getCategory() { return category; }
    public Set<String> getTags() { return tags; }

    public void addTag(String tag) {
        this.tags.add(tag.toLowerCase().trim());
    }

    @Override
    public String toString() {
        return String.format("Contact[ID=%s, Name='%-16s', Email='%-22s', Phone='%-12s', Cat='%-8s', Tags=%s]",
                id, name, email, phone, category, tags);
    }
}

class ContactCRM {
    // Primary index: ID -> Contact
    private Map<String, Contact> contactMap;
    // Secondary Category Index: Category Name -> List of Contacts
    private Map<String, List<Contact>> categoryIndex;

    public ContactCRM() {
        this.contactMap = new HashMap<>();
        this.categoryIndex = new HashMap<>();
    }

    public void addContact(Contact c) {
        contactMap.put(c.getId(), c);

        // Update Category index using computeIfAbsent (Java 8+)
        categoryIndex.computeIfAbsent(c.getCategory(), k -> new ArrayList<>()).add(c);
        System.out.println("  ✓ Added contact: " + c.getName() + " (" + c.getEmail() + ")");
    }

    public Contact findById(String id) {
        return contactMap.get(id);
    }

    public List<Contact> searchByName(String query) {
        List<Contact> results = new ArrayList<>();
        String lowerQuery = query.toLowerCase();
        for (Contact c : contactMap.values()) {
            if (c.getName().toLowerCase().contains(lowerQuery)) {
                results.add(c);
            }
        }
        return results;
    }

    public List<Contact> getContactsByCategory(String category) {
        return categoryIndex.getOrDefault(category, Collections.emptyList());
    }

    public List<Contact> getContactsByTag(String tag) {
        List<Contact> matched = new ArrayList<>();
        String searchTag = tag.toLowerCase().trim();
        for (Contact c : contactMap.values()) {
            if (c.getTags().contains(searchTag)) {
                matched.add(c);
            }
        }
        return matched;
    }

    public void displayAllSortedByName() {
        List<Contact> list = new ArrayList<>(contactMap.values());
        list.sort(Comparator.comparing(Contact::getName));

        System.out.println("\n╔════════════════════════════════════════════════════════════════════════════════════════════╗");
        System.out.println("║                                📇 CONTACT DIRECTORY (A-Z)                                  ║");
        System.out.println("╠════════════════════════════════════════════════════════════════════════════════════════════╣");
        for (Contact c : list) {
            System.out.println("║ " + c);
        }
        System.out.println("╚════════════════════════════════════════════════════════════════════════════════════════════╝");
    }
}

public class Exercise9 {

    public static void main(String[] args) {
        System.out.println("╔══════════════════════════════════════════════════╗");
        System.out.println("║         📇 SMART CONTACT & CRM SYSTEM            ║");
        System.out.println("╚══════════════════════════════════════════════════╝");

        ContactCRM crm = new ContactCRM();

        // Populate sample contacts
        Contact c1 = new Contact("C101", "Yodha Raja", "yodha@example.com", "555-0101", "Work");
        c1.addTag("Java"); c1.addTag("Architecture"); c1.addTag("Leader");

        Contact c2 = new Contact("C102", "Alex Mercer", "alex.m@domain.dev", "555-0102", "Work");
        c2.addTag("Java"); c2.addTag("Cloud");

        Contact c3 = new Contact("C103", "Sarah Connor", "sarah@cyberdyne.org", "555-0103", "VIP");
        c3.addTag("Security"); c3.addTag("AI");

        Contact c4 = new Contact("C104", "Emma Stone", "emma.s@hollywood.com", "555-0104", "Friends");
        c4.addTag("Acting"); c4.addTag("Cinema");

        crm.addContact(c1);
        crm.addContact(c2);
        crm.addContact(c3);
        crm.addContact(c4);

        // 1. Display full directory sorted
        crm.displayAllSortedByName();

        // 2. Fast O(1) primary lookup by ID
        System.out.println("\n🔍 Direct lookup for ID 'C103':");
        System.out.println("  " + crm.findById("C103"));

        // 3. Category Index Lookup
        System.out.println("\n📁 Contacts in 'Work' category:");
        for (Contact c : crm.getContactsByCategory("Work")) {
            System.out.println("  • " + c.getName() + " (" + c.getEmail() + ")");
        }

        // 4. Tag Matching Lookup (Set operations)
        System.out.println("\n🏷️ Contacts with tag 'java':");
        for (Contact c : crm.getContactsByTag("java")) {
            System.out.println("  • " + c.getName() + " | Tags: " + c.getTags());
        }
    }
}
