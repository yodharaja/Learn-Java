package chapter1;

/**
 * ============================================================
 * LESSON 1.2 — Classes, Objects & Instance State
 * ============================================================
 *
 * What is a Class?
 *   - A CLASS is a blueprint, template, or logical construct.
 *   - It defines WHAT attributes (fields) and WHAT behaviors (methods)
 *     objects of this type will have.
 *   - A class does NOT take up memory for object data until an instance is created.
 *
 * What is an Object?
 *   - An OBJECT is an actual physical instance in RAM memory created from a class blueprint.
 *   - Created using the `new` operator: `Student s1 = new Student();`.
 *   - Each object has its own distinct STATE (values of its instance fields).
 */

// Blueprint Class: Pen
class Pen {
    // Instance Fields (Attributes / State)
    String brand;
    String inkColor;
    double tipSizeMm;
    boolean isClickable;

    // Instance Behaviors (Methods)
    void write(String text) {
        System.out.printf("  ✍️ [%s Pen in %s ink] Writing: \"%s\"%n", brand, inkColor, text);
    }

    void click() {
        if (isClickable) {
            System.out.printf("  *Click!* The %s pen is toggled.%n", brand);
        } else {
            System.out.printf("  The %s pen has a cap (not clickable).%n", brand);
        }
    }
}

// Blueprint Class: Student
class Student {
    int rollNumber;
    String fullName;
    double score;

    void displayProfile() {
        System.out.printf("  Student #%d: %-15s | Score: %.1f%n", rollNumber, fullName, score);
    }
}

public class ClassesAndObjects {

    public static void main(String[] args) {
        System.out.println("=== 1. CREATING OBJECTS FROM THE PEN CLASS ===");

        // Instantiating first object
        Pen bluePen = new Pen();
        bluePen.brand = "Pilot G2";
        bluePen.inkColor = "Royal Blue";
        bluePen.tipSizeMm = 0.7;
        bluePen.isClickable = true;

        // Instantiating second distinct object
        Pen redPen = new Pen();
        redPen.brand = "Bic Cristal";
        redPen.inkColor = "Crimson Red";
        redPen.tipSizeMm = 1.0;
        redPen.isClickable = false;

        // Calling behaviors on individual instances:
        bluePen.write("Learning Java OOP is exciting!");
        bluePen.click();

        redPen.write("Grading assignment papers...");
        redPen.click();


        System.out.println("\n=== 2. CREATING MULTIPLE STUDENT OBJECTS ===");
        Student s1 = new Student();
        s1.rollNumber = 101;
        s1.fullName = "Yodha Raja";
        s1.score = 98.5;

        Student s2 = new Student();
        s2.rollNumber = 102;
        s2.fullName = "Sarah Connor";
        s2.score = 95.0;

        s1.displayProfile();
        s2.displayProfile();

        // ============================================================
        // KEY TAKEAWAYS:
        // ============================================================
        // - Class = Blueprint (e.g., Pen specification).
        // - Object = Instance in memory (e.g., bluePen, redPen).
        // - Use the dot operator `.` to access fields and invoke methods (`object.field`, `object.method()`).
        // ============================================================
    }
}
