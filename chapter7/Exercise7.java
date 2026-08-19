package chapter7;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

/**
 * ============================================================
 * EXERCISE 7 — Student Academic Ranking & Gradebook Engine 🎓
 * ============================================================
 *
 * Build an academic management system that:
 *   1. Manages an array of `StudentGrade` objects:
 *      - Roll number, Student Name, Subject Scores array (`double[]`)
 *      - Calculates Total Marks, Percentage, GPA, Letter Grade
 *   2. Performs operations:
 *      - Ranks students by GPA / Total Percentage (Descending)
 *      - Searches for student by Roll Number or Name
 *      - Generates clean ASCII performance report cards
 *      - Uses `StringBuilder` for high-performance tabular formatting
 *      - Validates input with RegEx
 * ============================================================
 */

class StudentGrade implements Comparable<StudentGrade> {
    private String rollNo;
    private String name;
    private double[] marks; // [Math, Science, English, CS]
    private static final String[] SUBJECTS = {"Math", "Science", "English", "CS"};

    public StudentGrade(String rollNo, String name, double[] marks) {
        this.rollNo = rollNo;
        this.name = name;
        this.marks = Arrays.copyOf(marks, marks.length);
    }

    public String getRollNo() { return rollNo; }
    public String getName() { return name; }
    public double[] getMarks() { return marks; }

    public double getTotalMarks() {
        double total = 0;
        for (double m : marks) total += m;
        return total;
    }

    public double getPercentage() {
        return marks.length > 0 ? getTotalMarks() / marks.length : 0;
    }

    public String getLetterGrade() {
        double p = getPercentage();
        if (p >= 90) return "A+";
        if (p >= 80) return "A";
        if (p >= 70) return "B";
        if (p >= 60) return "C";
        if (p >= 50) return "D";
        return "F";
    }

    public double getGPA() {
        double p = getPercentage();
        return Math.min(4.0, (p / 20.0) - 1.0);
    }

    // Natural Sorting: By Total Percentage Descending (Rank 1 has highest marks)
    @Override
    public int compareTo(StudentGrade other) {
        return Double.compare(other.getPercentage(), this.getPercentage());
    }

    public String getReportCard() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("║ Roll: %-6s │ Name: %-18s │ Grade: %-2s │ GPA: %.2f ║%n",
                rollNo, name, getLetterGrade(), Math.max(0, getGPA())));
        sb.append("║   Marks: ");
        for (int i = 0; i < marks.length; i++) {
            sb.append(String.format("%s: %.1f  ", SUBJECTS[i], marks[i]));
        }
        sb.append(String.format("│ Avg: %.2f%% ║", getPercentage()));
        return sb.toString();
    }
}

class GradeBook {
    private StudentGrade[] roster;
    private int count;

    public GradeBook(int capacity) {
        this.roster = new StudentGrade[capacity];
        this.count = 0;
    }

    public void addStudent(StudentGrade student) {
        if (count < roster.length) {
            roster[count++] = student;
        } else {
            System.out.println("  ❌ GradeBook is at full capacity!");
        }
    }

    public void displayLeaderboard() {
        // Sort active slice of students by highest percentage
        StudentGrade[] activeList = Arrays.copyOf(roster, count);
        Arrays.sort(activeList); // Uses natural order (Percentage DESC)

        StringBuilder table = new StringBuilder();
        table.append("\n╔══════════════════════════════════════════════════════════════════════╗\n");
        table.append("║                    🏆 ACADEMIC LEADERBOARD & RANKS                   ║\n");
        table.append("╠══════╦═════════╦════════════════════╦════════════╦═══════╦═══════════╣\n");
        table.append("║ RANK ║ ROLL NO ║ STUDENT NAME       ║ PERCENTAGE ║ GRADE ║ GPA (4.0) ║\n");
        table.append("╠══════╬═════════╬════════════════════╬════════════╬═══════╬═══════════╣\n");

        for (int i = 0; i < activeList.length; i++) {
            StudentGrade s = activeList[i];
            table.append(String.format("║  #%-3d ║ %-7s ║ %-18s ║   %5.1f%%   ║  %-4s ║   %4.2f    ║%n",
                    (i + 1), s.getRollNo(), s.getName(), s.getPercentage(), s.getLetterGrade(), Math.max(0, s.getGPA())));
        }
        table.append("╚══════╩═════════╩════════════════════╩════════════╩═══════╩═══════════╝\n");

        System.out.println(table);
    }

    public StudentGrade findByRollNumber(String roll) {
        for (int i = 0; i < count; i++) {
            if (roster[i].getRollNo().equalsIgnoreCase(roll.trim())) {
                return roster[i];
            }
        }
        return null;
    }

    public void displayDetailedReportCards() {
        System.out.println("\n╔══════════════════════════════════════════════════════════════════════╗");
        System.out.println("║                    📜 DETAILED STUDENT REPORT CARDS                  ║");
        System.out.println("╠══════════════════════════════════════════════════════════════════════╣");
        for (int i = 0; i < count; i++) {
            System.out.println(roster[i].getReportCard());
            if (i < count - 1) {
                System.out.println("╟──────────────────────────────────────────────────────────────────────╢");
            }
        }
        System.out.println("╚══════════════════════════════════════════════════════════════════════╝");
    }
}

public class Exercise7 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("╔══════════════════════════════════════════════════╗");
        System.out.println("║        🎓 STUDENT GRADEBOOK & RANKING SYSTEM     ║");
        System.out.println("╚══════════════════════════════════════════════════╝");

        GradeBook gradeBook = new GradeBook(10);

        // Populate sample students
        gradeBook.addStudent(new StudentGrade("R101", "Yodha Raja", new double[]{98.0, 94.5, 91.0, 99.0}));
        gradeBook.addStudent(new StudentGrade("R102", "Sarah Jenkins", new double[]{85.0, 89.0, 78.5, 92.0}));
        gradeBook.addStudent(new StudentGrade("R103", "David Kim", new double[]{92.5, 96.0, 94.0, 97.5}));
        gradeBook.addStudent(new StudentGrade("R104", "Emma Watson", new double[]{72.0, 68.5, 75.0, 81.0}));
        gradeBook.addStudent(new StudentGrade("R105", "Michael Chang", new double[]{88.0, 84.0, 86.5, 90.0}));

        // Display Leaderboard
        gradeBook.displayLeaderboard();

        // Display Detailed Reports
        gradeBook.displayDetailedReportCards();

        // Search feature demonstration
        System.out.print("\n🔍 Search student by Roll Number (e.g. R101): ");
        String searchRoll = "R101";
        System.out.println(searchRoll);

        StudentGrade found = gradeBook.findByRollNumber(searchRoll);
        if (found != null) {
            System.out.println("✓ Found Record:");
            System.out.println(found.getReportCard());
        } else {
            System.out.println("❌ No student found with Roll Number: " + searchRoll);
        }

        sc.close();
    }
}
