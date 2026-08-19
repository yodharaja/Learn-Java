package chapter1;

import java.util.Scanner;

/**
 * ============================================================
 * EXERCISE 1 — Student ID & Profile Card Generator 📇
 * ============================================================
 *
 * Build an object-oriented program that:
 *   1. Defines a `StudentCard` class with fields for name, roll number, department, and score.
 *   2. Provides a method `generateBadge()` that renders a formatted ASCII badge.
 *   3. Creates two distinct student cards and displays their state.
 * ============================================================
 */

class StudentCard {
    String studentName;
    int rollNo;
    String department;
    double academicScore;

    void generateBadge() {
        System.out.println("╔══════════════════════════════════════════╗");
        System.out.printf("║  STUDENT ID: #%-26d ║%n", rollNo);
        System.out.println("╠══════════════════════════════════════════╣");
        System.out.printf("║  Name  : %-31s ║%n", studentName);
        System.out.printf("║  Dept  : %-31s ║%n", department);
        System.out.printf("║  Score : %-5.1f / 100.0                ║%n", academicScore);
        System.out.printf("║  Status: %-31s ║%n", academicScore >= 50.0 ? "PASSED (Good Standing)" : "PROBATION");
        System.out.println("╚══════════════════════════════════════════╝");
    }
}

public class Exercise1 {

    public static void main(String[] args) {
        System.out.println("=== STUDENT PROFILE & ID BADGE GENERATOR ===");

        // Create Student 1
        StudentCard s1 = new StudentCard();
        s1.studentName = "Yodha Raja";
        s1.rollNo = 1001;
        s1.department = "Computer Science";
        s1.academicScore = 98.5;

        // Create Student 2
        StudentCard s2 = new StudentCard();
        s2.studentName = "Sarah Connor";
        s2.rollNo = 1002;
        s2.department = "Cybersecurity";
        s2.academicScore = 94.0;

        System.out.println("\nBadge 1:");
        s1.generateBadge();

        System.out.println("\nBadge 2:");
        s2.generateBadge();
    }
}
