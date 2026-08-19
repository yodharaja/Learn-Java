package chapter5;

/**
 * ============================================================
 * LESSON 5.1 — Constructors in Java
 * ============================================================
 *
 * A CONSTRUCTOR is a special method used to initialize objects.
 * It is called automatically when an object is created with 'new'.
 *
 * Rules for constructors:
 *   1. Must have the EXACT SAME NAME as the class.
 *   2. Must NOT have any return type (not even 'void').
 *   3. Can be overloaded (multiple constructors with different parameters).
 *
 * Constructor Types:
 *   - Default Constructor: Automatically provided by Java if you don't write any constructor.
 *   - No-argument Constructor: Explicit constructor taking no arguments.
 *   - Parameterized Constructor: Constructor that takes arguments to set initial values.
 *
 * 'this' keyword:
 *   - 'this.field' refers to the current object's field (resolves name shadowing).
 *   - 'this(...)' calls another constructor within the same class (must be 1st line!).
 */

class StudentRecord {
    String name;
    int rollNo;
    double gpa;
    String department;

    // 1. No-argument constructor (sets default values)
    StudentRecord() {
        // 'this(...)' calls the 4-parameter constructor below!
        // Constructor chaining avoids repeating initialization code.
        this("Unknown", 0, 0.0, "General");
        System.out.println("  [Info] No-arg constructor called");
    }

    // 2. Parameterized constructor (name and rollNo only)
    StudentRecord(String name, int rollNo) {
        this(name, rollNo, 0.0, "Undeclared");
    }

    // 3. Fully parameterized constructor
    StudentRecord(String name, int rollNo, double gpa, String department) {
        // 'this.name' refers to instance field, 'name' refers to the parameter
        this.name = name;
        this.rollNo = rollNo;
        this.gpa = gpa;
        this.department = department;
    }

    // 4. Copy constructor: creates a new object by copying another object
    StudentRecord(StudentRecord other) {
        this(other.name, other.rollNo, other.gpa, other.department);
    }

    void display() {
        System.out.printf("  Roll #%d: %s | Dept: %s | GPA: %.2f%n",
                rollNo, name, department, gpa);
    }
}

public class Constructors {

    public static void main(String[] args) {
        System.out.println("=== 1. DEFAULT / NO-ARG CONSTRUCTOR ===");
        StudentRecord s1 = new StudentRecord();
        s1.display();

        System.out.println("\n=== 2. PARAMETERIZED CONSTRUCTOR (2 Args) ===");
        StudentRecord s2 = new StudentRecord("Yodha Raja", 101);
        s2.display();

        System.out.println("\n=== 3. FULLY PARAMETERIZED CONSTRUCTOR ===");
        StudentRecord s3 = new StudentRecord("Alex Morgan", 102, 3.92, "Computer Science");
        s3.display();

        System.out.println("\n=== 4. COPY CONSTRUCTOR ===");
        StudentRecord s4 = new StudentRecord(s3); // s4 has identical data to s3
        s4.name = "Alex Morgan (Clone)";          // modifying s4 doesn't affect s3
        System.out.print("Original: "); s3.display();
        System.out.print("Copy:     "); s4.display();

        // ============================================================
        // KEY TAKEAWAYS:
        // ============================================================
        // - Constructors initialize new object state on creation.
        // - If you define ANY constructor, Java will NOT provide default constructor.
        // - Use 'this(args)' to call another constructor in the same class (Constructor Chaining).
        // - 'this(args)' MUST be the first statement in a constructor.
        // ============================================================
    }
}
