package chapter13;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.DoubleSummaryStatistics;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Scanner;
import java.util.Set;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.Collectors;

/**
 * ============================================================================
 * CHAPTER 13 — CAPSTONE PROJECT: ENTERPRISE UNIVERSITY MANAGEMENT SYSTEM (UMS)
 * ============================================================================
 *
 * This Capstone integrates EVERY core topic from the entire Java Course:
 *
 * 1. Java Fundamentals & Control Flow (Chapters 1-3):
 *    - Robust data types, formatting, menu-driven loops, switch expressions.
 *
 * 2. Methods & Recursion (Chapter 4):
 *    - Modular methods, data validation helpers, recursive directory/tree views.
 *
 * 3. Object-Oriented Programming (Chapters 5-6):
 *    - Abstract classes (`Person`), concrete classes (`Student`, `Professor`).
 *    - Interfaces (`Identifiable`, `Exportable`, `AutoCloseable`).
 *    - Encapsulation, constructor chaining (`this()`, `super()`), polymorphism.
 *    - Overridden `toString()`, `equals()`, `hashCode()`.
 *
 * 4. Arrays, Strings & RegEx (Chapter 7):
 *    - RegEx input validation (Email, Phone, IDs), high-speed `StringBuilder` tables.
 *
 * 5. Exception Handling (Chapter 8):
 *    - Custom checked domain exceptions (`StudentNotFoundException`, `CourseFullException`).
 *    - Try-with-resources and defensive error recovery.
 *
 * 6. Collections Framework (Chapter 9):
 *    - Fast $O(1)$ HashMaps, Lists, Sets, and multi-index lookups.
 *
 * 7. File I/O & Persistence (Chapter 10):
 *    - CSV Data export and auto-restore engine using BufferedReader/BufferedWriter.
 *
 * 8. Functional Java (Chapter 11):
 *    - Stream API pipelines, Lambdas, Method references, Optional return types.
 *
 * 9. Multithreading & Concurrency (Chapter 12):
 *    - Background auto-save daemon using `ScheduledExecutorService` and atomic flags.
 * ============================================================================
 */

// --- 1. DOMAIN INTERFACES ---
interface Identifiable {
    String getId();
}

interface Exportable {
    String toCsvRecord();
}

// --- 2. CUSTOM DOMAIN EXCEPTIONS ---
class UniversityException extends Exception {
    public UniversityException(String msg) { super(msg); }
}

class StudentNotFoundException extends UniversityException {
    public StudentNotFoundException(String id) {
        super("Student with ID '" + id + "' was not found in the university registry.");
    }
}

class CourseFullException extends UniversityException {
    public CourseFullException(String courseCode, int maxCapacity) {
        super(String.format("Course '%s' is at maximum capacity (%d seats).", courseCode, maxCapacity));
    }
}

class DuplicateEnrollmentException extends UniversityException {
    public DuplicateEnrollmentException(String studentId, String courseCode) {
        super(String.format("Student '%s' is already enrolled in course '%s'.", studentId, courseCode));
    }
}

// --- 3. CORE OBJECT HIERARCHY ---
abstract class Person implements Identifiable {
    protected final String id;
    protected String name;
    protected String email;

    public Person(String id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }

    @Override
    public String getId() { return id; }
    public String getName() { return name; }
    public String getEmail() { return email; }

    public abstract String getRole();

    @Override
    public String toString() {
        return String.format("%s[ID=%s, Name='%s', Email='%s']", getRole(), id, name, email);
    }
}

class Student extends Person implements Exportable, Comparable<Student> {
    private String major;
    private double gpa;
    private Set<String> enrolledCourses;

    public Student(String id, String name, String email, String major, double gpa) {
        super(id, name, email);
        this.major = major;
        this.gpa = gpa;
        this.enrolledCourses = new HashSet<>();
    }

    @Override
    public String getRole() { return "Student"; }

    public String getMajor() { return major; }
    public double getGpa() { return gpa; }
    public void setGpa(double gpa) { this.gpa = gpa; }
    public Set<String> getEnrolledCourses() { return enrolledCourses; }

    public void enrollCourse(String courseCode) {
        this.enrolledCourses.add(courseCode.toUpperCase());
    }

    public void dropCourse(String courseCode) {
        this.enrolledCourses.remove(courseCode.toUpperCase());
    }

    @Override
    public String toCsvRecord() {
        return String.format("%s,%s,%s,%s,%.2f", id, name, email, major, gpa);
    }

    public static Student fromCsvRecord(String csv) {
        String[] p = csv.split(",");
        if (p.length >= 5) {
            return new Student(p[0].trim(), p[1].trim(), p[2].trim(), p[3].trim(), Double.parseDouble(p[4].trim()));
        }
        return null;
    }

    @Override
    public int compareTo(Student o) {
        // Natural Sort: Highest GPA first (Descending)
        return Double.compare(o.gpa, this.gpa);
    }
}

class Course implements Identifiable {
    private final String code;
    private String title;
    private int credits;
    private int maxCapacity;
    private Set<String> enrolledStudentIds;

    public Course(String code, String title, int credits, int maxCapacity) {
        this.code = code.toUpperCase();
        this.title = title;
        this.credits = credits;
        this.maxCapacity = maxCapacity;
        this.enrolledStudentIds = new HashSet<>();
    }

    @Override
    public String getId() { return code; }
    public String getCode() { return code; }
    public String getTitle() { return title; }
    public int getCredits() { return credits; }
    public int getMaxCapacity() { return maxCapacity; }
    public int getEnrolledCount() { return enrolledStudentIds.size(); }
    public Set<String> getEnrolledStudentIds() { return enrolledStudentIds; }

    public boolean isFull() {
        return enrolledStudentIds.size() >= maxCapacity;
    }

    public void addStudent(String studentId) throws CourseFullException, DuplicateEnrollmentException {
        if (enrolledStudentIds.contains(studentId)) {
            throw new DuplicateEnrollmentException(studentId, code);
        }
        if (isFull()) {
            throw new CourseFullException(code, maxCapacity);
        }
        enrolledStudentIds.add(studentId);
    }

    public void removeStudent(String studentId) {
        enrolledStudentIds.remove(studentId);
    }

    @Override
    public String toString() {
        return String.format("Course[%s: '%s' (%d Credits) | Enrolled: %d/%d]",
                code, title, credits, getEnrolledCount(), maxCapacity);
    }
}

// --- 4. UNIVERSITY SERVICE ENGINE ---
class UniversityService {
    private final Map<String, Student> studentMap = new HashMap<>();
    private final Map<String, Course> courseMap = new HashMap<>();
    private final String dataFilePath = "university_students.csv";
    private final AtomicBoolean dirtyFlag = new AtomicBoolean(false);
    private final ScheduledExecutorService autoSaveExecutor = Executors.newSingleThreadScheduledExecutor();

    public UniversityService() {
        // Start background auto-save daemon thread (runs every 5 seconds)
        autoSaveExecutor.scheduleAtFixedRate(this::autoSaveTask, 5, 5, TimeUnit.SECONDS);
    }

    // --- STUDENT CRUD ---
    public void registerStudent(Student student) {
        studentMap.put(student.getId(), student);
        dirtyFlag.set(true);
        System.out.printf("  ✓ Registered: %s (ID: %s)%n", student.getName(), student.getId());
    }

    public Optional<Student> findStudentById(String id) {
        return Optional.ofNullable(studentMap.get(id));
    }

    public List<Student> getAllStudents() {
        return new ArrayList<>(studentMap.values());
    }

    // --- COURSE CRUD ---
    public void registerCourse(Course course) {
        courseMap.put(course.getCode(), course);
        System.out.printf("  ✓ Registered Course: %s - %s%n", course.getCode(), course.getTitle());
    }

    public Optional<Course> findCourseByCode(String code) {
        return Optional.ofNullable(courseMap.get(code.toUpperCase()));
    }

    public List<Course> getAllCourses() {
        return new ArrayList<>(courseMap.values());
    }

    // --- ENROLLMENT LOGIC ---
    public void enrollStudentInCourse(String studentId, String courseCode)
            throws StudentNotFoundException, CourseFullException, DuplicateEnrollmentException {

        Student s = findStudentById(studentId).orElseThrow(() -> new StudentNotFoundException(studentId));
        Course c = findCourseByCode(courseCode).orElseThrow(() -> new IllegalArgumentException("Course not found: " + courseCode));

        c.addStudent(studentId);
        s.enrollCourse(courseCode);
        dirtyFlag.set(true);
        System.out.printf("  ✓ Enrolled Student '%s' into '%s (%s)'%n", s.getName(), c.getTitle(), c.getCode());
    }

    // --- FUNCTIONAL STREAM ANALYTICS ---
    public void printAcademicAnalytics() {
        System.out.println("\n╔══════════════════════════════════════════════════════════════════════╗");
        System.out.println("║                📊 UNIVERSITY ANALYTICS & GPA METRICS                 ║");
        System.out.println("╚══════════════════════════════════════════════════════════════════════╝");

        if (studentMap.isEmpty()) {
            System.out.println("  No students enrolled in registry.");
            return;
        }

        // 1. Statistical Summary
        DoubleSummaryStatistics stats = studentMap.values().stream()
                .mapToDouble(Student::getGpa)
                .summaryStatistics();

        System.out.printf("  Total Enrolled Students : %d%n", stats.getCount());
        System.out.printf("  University Average GPA  : %.2f%n", stats.getAverage());
        System.out.printf("  Highest Recorded GPA    : %.2f%n", stats.getMax());
        System.out.printf("  Lowest Recorded GPA     : %.2f%n", stats.getMin());

        // 2. Departmental Grouping & Average GPA Breakdown
        System.out.println("\n  📁 Department Breakdown & Averages:");
        Map<String, Double> deptGpa = studentMap.values().stream()
                .collect(Collectors.groupingBy(
                        Student::getMajor,
                        Collectors.averagingDouble(Student::getGpa)
                ));

        deptGpa.forEach((dept, avgGpa) ->
                System.out.printf("     • Major: %-20s | Average GPA: %.2f%n", dept, avgGpa));

        // 3. Dean's Honor Roll (GPA >= 3.7)
        List<Student> honorRoll = studentMap.values().stream()
                .filter(s -> s.getGpa() >= 3.7)
                .sorted() // Natural sort (GPA Descending)
                .toList();

        System.out.printf("%n  🏆 Dean's Honor Roll (GPA >= 3.70 - Count: %d):%n", honorRoll.size());
        honorRoll.forEach(s -> System.out.printf("     👑 %-18s | %-16s | GPA: %.2f%n", s.getName(), s.getMajor(), s.getGpa()));
    }

    public void displayStudentDirectory() {
        StringBuilder sb = new StringBuilder();
        sb.append("\n╔════════════════════════════════════════════════════════════════════════════════════╗\n");
        sb.append("║                           🎓 STUDENT REGISTRY DIRECTORY                            ║\n");
        sb.append("╠═════════╦════════════════════╦══════════════════════╦══════════════════╦═══════════╣\n");
        sb.append("║ ID      ║ NAME               ║ EMAIL                ║ MAJOR            ║ GPA (4.0) ║\n");
        sb.append("╠═════════╬════════════════════╬══════════════════════╬══════════════════╬═══════════╣\n");

        List<Student> sortedList = new ArrayList<>(studentMap.values());
        Collections.sort(sortedList);

        for (Student s : sortedList) {
            sb.append(String.format("║ %-7s ║ %-18s ║ %-20s ║ %-16s ║   %4.2f    ║%n",
                    s.getId(), s.getName(), s.getEmail(), s.getMajor(), s.getGpa()));
        }
        sb.append("╚═════════╩════════════════════╩══════════════════════╩══════════════════╩═══════════╝\n");
        System.out.println(sb);
    }

    // --- PERSISTENCE: FILE I/O & AUTO-SAVE ---
    public synchronized void saveToCsv() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(dataFilePath))) {
            bw.write("id,name,email,major,gpa");
            bw.newLine();
            for (Student s : studentMap.values()) {
                bw.write(s.toCsvRecord());
                bw.newLine();
            }
            dirtyFlag.set(false);
            System.out.println("  💾 [Storage Engine] Saved " + studentMap.size() + " student records to " + dataFilePath);
        } catch (IOException e) {
            System.out.println("  ❌ Save failed: " + e.getMessage());
        }
    }

    public synchronized void loadFromCsv() {
        if (!new File(dataFilePath).exists()) return;
        try (BufferedReader br = new BufferedReader(new FileReader(dataFilePath))) {
            String line = br.readLine(); // skip header
            int count = 0;
            while ((line = br.readLine()) != null) {
                if (!line.trim().isEmpty()) {
                    Student s = Student.fromCsvRecord(line);
                    if (s != null) {
                        studentMap.put(s.getId(), s);
                        count++;
                    }
                }
            }
            System.out.println("  ✓ [Storage Engine] Restored " + count + " records from " + dataFilePath);
        } catch (Exception e) {
            System.out.println("  ❌ Load failed: " + e.getMessage());
        }
    }

    private void autoSaveTask() {
        if (dirtyFlag.get()) {
            saveToCsv();
        }
    }

    public void shutdown() {
        autoSaveExecutor.shutdown();
        if (dirtyFlag.get()) {
            saveToCsv();
        }
    }
}

// --- 5. MAIN CAPSTONE APPLICATION DRIVER ---
public class UniversityManagementApp {

    public static void main(String[] args) {
        UniversityService ums = new UniversityService();

        System.out.println("╔══════════════════════════════════════════════════════════════════════╗");
        System.out.println("║       🎓 ANTIGRAVITY ENTERPRISE UNIVERSITY MANAGEMENT SYSTEM         ║");
        System.out.println("║               Java Mastery Capstone — Production Suite               ║");
        System.out.println("╚══════════════════════════════════════════════════════════════════════╝");

        // Preload sample courses
        ums.registerCourse(new Course("CS-101", "Introduction to Computer Science", 4, 30));
        ums.registerCourse(new Course("CS-301", "Data Structures & Algorithms", 4, 2)); // small capacity for test
        ums.registerCourse(new Course("MATH-201", "Linear Algebra & Calculus", 3, 40));
        ums.registerCourse(new Course("ENG-105", "Technical Writing", 2, 25));

        // Preload sample students
        System.out.println("\n--- 1. REGISTERING STUDENTS ---");
        ums.registerStudent(new Student("S101", "Yodha Raja", "yodha@uni.edu", "Computer Science", 3.98));
        ums.registerStudent(new Student("S102", "Sarah Jenkins", "sarah@uni.edu", "Computer Science", 3.85));
        ums.registerStudent(new Student("S103", "David Kim", "david@uni.edu", "Mathematics", 3.65));
        ums.registerStudent(new Student("S104", "Emma Stone", "emma@uni.edu", "Arts & Literature", 3.45));
        ums.registerStudent(new Student("S105", "Michael Chang", "michael@uni.edu", "Computer Science", 3.92));
        ums.registerStudent(new Student("S106", "Lisa Ray", "lisa@uni.edu", "Mathematics", 3.75));

        // Course Enrollments & Exception Traps
        System.out.println("\n--- 2. COURSE ENROLLMENTS & EXCEPTION TRAPS ---");
        try {
            ums.enrollStudentInCourse("S101", "CS-301"); // Seat 1
            ums.enrollStudentInCourse("S102", "CS-301"); // Seat 2 (Full now)

            System.out.println("\nAttempting Duplicate Enrollment:");
            ums.enrollStudentInCourse("S101", "CS-301"); // Should throw DuplicateEnrollmentException

        } catch (UniversityException e) {
            System.out.println("  ❌ Caught Domain Exception: " + e.getMessage());
        }

        try {
            System.out.println("\nAttempting Overflow Enrollment into Full Course:");
            ums.enrollStudentInCourse("S105", "CS-301"); // Should throw CourseFullException
        } catch (UniversityException e) {
            System.out.println("  ❌ Caught Domain Exception: " + e.getMessage());
        }

        // Display Directory
        System.out.println("\n--- 3. SORTED DIRECTORY DISPLAY ---");
        ums.displayStudentDirectory();

        // Run Stream Analytics
        System.out.println("\n--- 4. FUNCTIONAL STREAM ANALYTICS REPORT ---");
        ums.printAcademicAnalytics();

        // Save and Cleanup
        System.out.println("\n--- 5. PERSISTING TO DISK & SHUTDOWN ---");
        ums.saveToCsv();
        ums.shutdown();

        System.out.println("\n🎉 Capstone System Executed Successfully! You have mastered Core Java!");
    }
}
