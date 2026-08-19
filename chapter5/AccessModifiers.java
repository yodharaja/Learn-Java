package chapter5;

/**
 * ============================================================
 * LESSON 5.2 — Access Modifiers & Encapsulation
 * ============================================================
 *
 * Java provides 4 access levels to control visibility of classes,
 * methods, and variables.
 *
 * Access Levels Table:
 * ┌───────────┬──────────────┬──────────────┬───────────────┬────────────────┐
 * │ Modifier  │ Same Class   │ Same Package │ Subclass (any)│ Outside Package│
 * ├───────────┼──────────────┼──────────────┼───────────────┼────────────────┤
 * │ private   │     YES      │      NO      │      NO       │       NO       │
 * │ default*  │     YES      │     YES      │      NO       │       NO       │
 * │ protected │     YES      │     YES      │     YES       │       NO       │
 * │ public    │     YES      │     YES      │     YES       │      YES       │
 * └───────────┴──────────────┴──────────────┴───────────────┴────────────────┘
 * *default = package-private (no modifier specified)
 *
 * ENCAPSULATION:
 *   - Bundling data (fields) and methods that operate on data into a single unit.
 *   - Restricting direct access to some components (usually by making fields private).
 *   - Access is provided safely via public methods (Getters and Setters).
 */

class Employee {
    // 1. private: only accessible inside Employee class
    private String ssn;
    private double salary;

    // 2. default (package-private): accessible anywhere within 'chapter5' package
    String department;

    // 3. protected: accessible within 'chapter5' package and any subclass (even in other packages)
    protected String role;

    // 4. public: accessible from anywhere in the program
    public String fullName;

    public Employee(String fullName, String ssn, double salary, String department, String role) {
        this.fullName = fullName;
        this.ssn = ssn;
        this.salary = salary;
        this.department = department;
        this.role = role;
    }

    // Public method to expose masked SSN safely
    public String getMaskedSSN() {
        if (ssn != null && ssn.length() >= 4) {
            return "***-**-" + ssn.substring(ssn.length() - 4);
        }
        return "N/A";
    }

    // Controlled getter for salary
    public double getSalary() {
        return this.salary;
    }

    // Controlled setter with business validation
    public void giveRaise(double percentage) {
        if (percentage > 0 && percentage <= 50) {
            this.salary += this.salary * (percentage / 100.0);
            System.out.printf("  ✓ Raise of %.1f%% applied. New salary: $%,.2f%n", percentage, this.salary);
        } else {
            System.out.println("  ❌ Invalid raise percentage (must be 1-50%).");
        }
    }
}

public class AccessModifiers {

    public static void main(String[] args) {
        Employee emp = new Employee("Jane Doe", "123-45-6789", 85000.0, "Engineering", "Lead Dev");

        System.out.println("=== 1. PUBLIC & DEFAULT ACCESS ===");
        // public: accessible directly
        System.out.println("Full Name (public)    : " + emp.fullName);
        // default: accessible because AccessModifiers is in the same package (chapter5)
        System.out.println("Department (default)  : " + emp.department);
        // protected: accessible within same package
        System.out.println("Role (protected)      : " + emp.role);

        System.out.println("\n=== 2. PRIVATE ACCESS & ENCAPSULATION ===");
        // The following lines would fail to compile because ssn and salary are private:
        // System.out.println(emp.ssn);    // ERROR: ssn has private access in Employee
        // emp.salary = -1000;            // ERROR: cannot corrupt internal state directly!

        // Access private data through secure public methods:
        System.out.println("SSN (Masked via method): " + emp.getMaskedSSN());
        System.out.printf("Salary (via getter)    : $%,.2f%n", emp.getSalary());

        System.out.println("\n=== 3. ENCAPSULATED STATE MUTATION ===");
        emp.giveRaise(10.0);   // Valid raise
        emp.giveRaise(120.0);  // Rejected by validation rule!

        // ============================================================
        // KEY TAKEAWAYS:
        // ============================================================
        // - Make fields 'private' to prevent external code from corrupting state.
        // - Provide 'public' or 'protected' methods for reading/updating data.
        // - Encapsulation ensures validation rules cannot be bypassed.
        // ============================================================
    }
}
