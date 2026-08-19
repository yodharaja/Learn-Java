package chapter5;

/**
 * ============================================================
 * LESSON 5.3 — Getters, Setters & JavaBean Conventions
 * ============================================================
 *
 * GETTERS (Accessors) and SETTERS (Mutators) are standard methods
 * used to read and update private fields.
 *
 * Why use Getters & Setters instead of public fields?
 *   1. Validation: Prevent invalid data from entering the object (e.g. negative age, null name).
 *   2. Read-only or Write-only fields: Omit setter for read-only, omit getter for write-only.
 *   3. Computed Properties: Return calculated results without storing redundant state.
 *   4. Flexibility: Change internal implementation without breaking outside code.
 *
 * JavaBean Naming Conventions:
 *   - Getter: getFieldName()  (or isFieldName() for boolean)
 *   - Setter: setFieldName(value)
 */

class UserAccount {
    private String username;
    private String email;
    private int age;
    private boolean active;

    public UserAccount(String username, String email, int age) {
        setUsername(username); // Call setter to ensure validation runs during construction!
        setEmail(email);
        setAge(age);
        this.active = true;
    }

    // --- GETTERS & SETTERS WITH VALIDATION ---

    // 1. username
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        if (username != null && username.trim().length() >= 3) {
            this.username = username.trim();
        } else {
            System.out.println("  ❌ Invalid username: must have at least 3 characters.");
        }
    }

    // 2. email
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        if (email != null && email.contains("@") && email.contains(".")) {
            this.email = email.trim();
        } else {
            System.out.println("  ❌ Invalid email format: " + email);
        }
    }

    // 3. age
    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        if (age >= 13 && age <= 120) {
            this.age = age;
        } else {
            System.out.println("  ❌ Invalid age: must be between 13 and 120 (got " + age + ").");
        }
    }

    // 4. boolean getter uses 'is...' prefix
    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    // 5. Computed Property (No underlying variable stored!)
    public boolean isAdult() {
        return this.age >= 18;
    }

    public void printSummary() {
        System.out.printf("  User: @%s | Email: %s | Age: %d (Adult: %s) | Status: %s%n",
                username, email, age, isAdult() ? "Yes" : "No", active ? "Active" : "Inactive");
    }
}

public class GettersSetters {

    public static void main(String[] args) {
        System.out.println("=== 1. CREATING VALID USER ACCOUNT ===");
        UserAccount user = new UserAccount("yodha_raja", "yodha@example.com", 21);
        user.printSummary();

        System.out.println("\n=== 2. TESTING SETTER VALIDATIONS ===");
        System.out.println("Attempting to set invalid username 'ab':");
        user.setUsername("ab"); // Should be rejected

        System.out.println("Attempting to set invalid email 'not-an-email':");
        user.setEmail("not-an-email"); // Should be rejected

        System.out.println("Attempting to set age to -5:");
        user.setAge(-5); // Should be rejected

        System.out.println("\nState after rejected modifications (remains safely unchanged):");
        user.printSummary();

        System.out.println("\n=== 3. VALID UPDATES ===");
        user.setUsername("yodha_master");
        user.setEmail("yodha.master@code.dev");
        user.setAge(22);
        user.setActive(false);
        user.printSummary();

        // ============================================================
        // KEY TAKEAWAYS:
        // ============================================================
        // - Always keep fields private and expose them via getters/setters.
        // - Place validation logic inside setters and call setters from constructors.
        // - Boolean getters are conventionally named 'isProperty()'.
        // - Computed getters provide derived properties cleanly.
        // ============================================================
    }
}
