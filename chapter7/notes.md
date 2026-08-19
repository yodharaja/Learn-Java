# Chapter 7 — Arrays & Strings In-Depth

## Quick Reference Cheat Sheet

---

## 1. Multi-Dimensional & Jagged Arrays

- **2D Rectangular Array**: `int[][] matrix = new int[3][3];`
- **Jagged Array** (varying column lengths):
```java
int[][] jagged = new int[3][];
jagged[0] = new int[2];
jagged[1] = new int[4];
jagged[2] = new int[1];
```

---

## 2. `java.util.Arrays` Essential Methods

| Method | Purpose |
|---|---|
| `Arrays.toString(arr)` | Convert 1D array to readable `[a, b, c]` string |
| `Arrays.deepToString(arr)` | Convert multi-dimensional array to nested string |
| `Arrays.sort(arr)` | Sorts primitives / Comparable objects in ascending order |
| `Arrays.binarySearch(arr, key)` | Fast binary search (array **must** be sorted first) |
| `Arrays.copyOf(arr, length)` | Clone / resize array |
| `Arrays.copyOfRange(arr, from, to)` | Slice sub-array `[from, to)` |
| `Arrays.fill(arr, val)` | Set every element to `val` |
| `Arrays.equals(a, b)` | Deep element-by-element equality check |

---

## 3. String RegEx Operations

- **Split**: `String[] words = text.split("[,\\s]+");`
- **Pattern Match**: `boolean isValid = email.matches("^[A-Za-z0-9._%+-]+@.+");`
- **Replace with RegEx**: `String clean = text.replaceAll("[^0-9]", "");`
- **Join**: `String csv = String.join(", ", "A", "B", "C");`

---

## 4. `String` vs `StringBuilder` vs `StringBuffer`

| Class | Mutability | Thread-Safe? | Best For |
|---|---|---|---|
| `String` | Immutable | Yes | Fixed strings, dictionary keys, constants |
| `StringBuilder` | Mutable | No (Fast) | Single-threaded heavy concatenation / loops |
| `StringBuffer` | Mutable | Yes (Synchronized) | Multi-threaded shared string mutation |

> ⚠️ **Rule**: Never do `str += "x"` in a loop with $>100$ iterations. Use `StringBuilder.append()` instead!

---

## 5. Sorting Object Arrays

### 1. Natural Order with `Comparable<T>`:
```java
public class Student implements Comparable<Student> {
    private double gpa;

    @Override
    public int compareTo(Student other) {
        return Double.compare(this.gpa, other.gpa); // Ascending
    }
}
// Usage:
Arrays.sort(students);
```

### 2. Custom Order with `Comparator<T>`:
```java
// Descending by GPA:
Arrays.sort(students, (s1, s2) -> Double.compare(s2.getGpa(), s1.getGpa()));

// Multi-criteria sorting with method references:
Arrays.sort(students, Comparator.comparing(Student::getName)
                                .thenComparingDouble(Student::getGpa));
```
