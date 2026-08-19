package programs;

/**
 * ============================================================
 * PROGRAM 43: Class Encapsulation with Getters & Setters
 * ============================================================
 * Problem: WAP to create an encapsulated `EmployeeRecord` class
 * with private fields, validation rules in setters, and formatted getters.
 * ============================================================
 */

class EmployeeRecord {
    private String empId;
    private String name;
    private double salary;

    public EmployeeRecord(String empId, String name, double salary) {
        this.empId = empId;
        setName(name);
        setSalary(salary);
    }

    public String getEmpId() { return empId; }
    public String getName() { return name; }
    public double getSalary() { return salary; }

    public void setName(String name) {
        if (name != null && !name.trim().isEmpty()) {
            this.name = name.trim();
        }
    }

    public void setSalary(double salary) {
        if (salary >= 0) {
            this.salary = salary;
        } else {
            System.out.println("  ❌ Salary cannot be negative.");
        }
    }

    public void display() {
        System.out.printf("  Employee[%s: %s | Salary: $%,.2f]%n", empId, name, salary);
    }
}

public class P43_StudentClassEncapsulation {

    public static void main(String[] args) {
        EmployeeRecord emp = new EmployeeRecord("EMP-101", "Yodha Raja", 85000.0);
        emp.display();

        emp.setSalary(-500); // rejected
        emp.setSalary(95000.0); // accepted
        emp.display();
    }
}
