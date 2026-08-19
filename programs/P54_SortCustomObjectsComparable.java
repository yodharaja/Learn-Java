package programs;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * ============================================================
 * PROGRAM 54: Sort Custom Objects with Comparable and Comparator
 * ============================================================
 * Problem: WAP to sort a List of `Developer` objects:
 *   a) By ID (Natural Order using `Comparable<Developer>`)
 *   b) By Salary Descending (using `Comparator`)
 *   c) By Name Alphabetically (using Lambda Comparator)
 * ============================================================
 */

class Developer implements Comparable<Developer> {
    private int id;
    private String name;
    private double salary;

    public Developer(int id, String name, double salary) {
        this.id = id;
        this.name = name;
        this.salary = salary;
    }

    public int getId() { return id; }
    public String getName() { return name; }
    public double getSalary() { return salary; }

    @Override
    public int compareTo(Developer o) {
        return Integer.compare(this.id, o.id); // Ascending ID
    }

    @Override
    public String toString() {
        return String.format("Developer[ID=%3d, Name='%-15s', Salary=$%,9.2f]", id, name, salary);
    }
}

public class P54_SortCustomObjectsComparable {

    public static void main(String[] args) {
        List<Developer> team = new ArrayList<>();
        team.add(new Developer(105, "Yodha Raja", 125000.0));
        team.add(new Developer(101, "Sarah Connor", 140000.0));
        team.add(new Developer(103, "Alex Mercer", 98000.0));
        team.add(new Developer(102, "David Kim", 115000.0));

        // 1. Natural Sort (Comparable - By ID)
        System.out.println("=== 1. SORTED BY ID (NATURAL ORDER) ===");
        Collections.sort(team);
        team.forEach(d -> System.out.println("  " + d));

        // 2. Sort by Salary Descending (Comparator)
        System.out.println("\n=== 2. SORTED BY SALARY DESCENDING ===");
        team.sort((d1, d2) -> Double.compare(d2.getSalary(), d1.getSalary()));
        team.forEach(d -> System.out.println("  " + d));

        // 3. Sort by Name Alphabetically
        System.out.println("\n=== 3. SORTED BY NAME (A-Z) ===");
        team.sort(Comparator.comparing(Developer::getName));
        team.forEach(d -> System.out.println("  " + d));
    }
}
