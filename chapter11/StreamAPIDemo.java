package chapter11;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * ============================================================
 * LESSON 11.3 — The Stream API Pipeline
 * ============================================================
 *
 * A STREAM is a sequence of elements supporting sequential and parallel
 * aggregate operations (map-filter-reduce paradigm).
 *
 * Stream Characteristics:
 *   1. Does NOT store data (operates on source data structures).
 *   2. Functional & Non-mutating (does not change original collection).
 *   3. Lazily Evaluated: Intermediate operations execute ONLY when a terminal op is reached.
 *   4. Single-use: Once consumed by a terminal op, a stream cannot be reused.
 *
 * Stream Pipeline Architecture:
 *   [Source] -> [Intermediate Ops: filter, map, sorted] -> [Terminal Op: collect, forEach, reduce]
 */

class CourseStudent {
    private String name;
    private String major;
    private double gpa;
    private int year;

    public CourseStudent(String name, String major, double gpa, int year) {
        this.name = name;
        this.major = major;
        this.gpa = gpa;
        this.year = year;
    }

    public String getName() { return name; }
    public String getMajor() { return major; }
    public double getGPA() { return gpa; }
    public int getYear() { return year; }

    @Override
    public String toString() {
        return String.format("%s (Major: %s, GPA: %.2f, Year %d)", name, major, gpa, year);
    }
}

public class StreamAPIDemo {

    public static void main(String[] args) {
        List<CourseStudent> students = Arrays.asList(
            new CourseStudent("Yodha Raja", "CS", 3.95, 3),
            new CourseStudent("Alex Morgan", "CS", 3.40, 2),
            new CourseStudent("Sarah Connor", "Cybersecurity", 3.85, 4),
            new CourseStudent("David Kim", "Math", 3.90, 3),
            new CourseStudent("Emma Stone", "Arts", 3.10, 1),
            new CourseStudent("Michael Chang", "CS", 3.75, 4),
            new CourseStudent("Lisa Ray", "Math", 3.60, 2)
        );

        System.out.println("=== 1. FILTERING, MAPPING & COLLECTING (Top CS Students) ===");
        // Task: Find names of all CS students with GPA >= 3.5, sorted alphabetically
        List<String> topCsStudents = students.stream()
                .filter(s -> s.getMajor().equalsIgnoreCase("CS")) // Intermediate
                .filter(s -> s.getGPA() >= 3.5)                   // Intermediate
                .map(CourseStudent::getName)                      // Intermediate (transform to String)
                .sorted()                                         // Intermediate
                .collect(Collectors.toList());                    // Terminal

        System.out.println("Top CS Students: " + topCsStudents);


        System.out.println("\n=== 2. NUMERIC AGGREGATION WITH REDUCE & SUMMARY STATS ===");
        // Calculate average GPA of all students
        double averageGpa = students.stream()
                .mapToDouble(CourseStudent::getGPA)
                .average()
                .orElse(0.0);
        System.out.printf("Overall Class Average GPA: %.2f%n", averageGpa);

        // Max GPA student
        CourseStudent topScholar = students.stream()
                .max(Comparator.comparingDouble(CourseStudent::getGPA))
                .orElse(null);
        System.out.println("Top Scholar: " + topScholar);


        System.out.println("\n=== 3. GROUPING BY (COLLECTORS.GROUPINGBY) ===");
        // Group students by Major into a Map<String, List<CourseStudent>>
        Map<String, List<CourseStudent>> byMajor = students.stream()
                .collect(Collectors.groupingBy(CourseStudent::getMajor));

        byMajor.forEach((major, list) -> {
            System.out.println("  📁 Major [" + major + "] (Count: " + list.size() + "):");
            list.forEach(s -> System.out.println("     • " + s.getName() + " (GPA: " + s.getGPA() + ")"));
        });


        System.out.println("\n=== 4. MATCHING PREDICATES (allMatch, anyMatch, noneMatch) ===");
        boolean anyPerfect4 = students.stream().anyMatch(s -> s.getGPA() >= 4.0);
        boolean allPassing = students.stream().allMatch(s -> s.getGPA() >= 2.0);
        System.out.println("Any student with 4.0+ GPA? " + anyPerfect4);
        System.out.println("Are all students passing (GPA >= 2.0)? " + allPassing);

        // ============================================================
        // KEY TAKEAWAYS:
        // ============================================================
        // - `filter()` discards items that fail a boolean Predicate.
        // - `map()` transforms each item into another form.
        // - `collect(Collectors.toList())` or `toList()` gathers items into a list.
        // - `groupingBy()` partitions data into buckets with zero SQL overhead.
        // ============================================================
    }
}
