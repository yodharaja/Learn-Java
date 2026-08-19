package chapter9;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

/**
 * ============================================================
 * LESSON 9.1 — ArrayList & Generics <T>
 * ============================================================
 *
 * Why use ArrayList over standard Arrays?
 *   - Arrays have FIXED size. Once created, they cannot grow or shrink.
 *   - `ArrayList<E>` is a RESIZABLE dynamic array. It grows automatically as elements are added.
 *   - Rich built-in methods: `add()`, `remove()`, `contains()`, `indexOf()`, `clear()`, `subList()`.
 *
 * Generics (`<T>`):
 *   - Type safety at compile time (`ArrayList<String>` only accepts Strings).
 *   - Primitives MUST use their Wrapper classes (`int` -> `Integer`, `double` -> `Double`).
 */
public class ArrayListDemo {

    public static void main(String[] args) {
        System.out.println("=== 1. CREATING & POPULATING AN ARRAYLIST ===");
        // Diamond operator '<>' infers type from declaration
        List<String> fruits = new ArrayList<>();

        // 1. add() elements
        fruits.add("Apple");
        fruits.add("Banana");
        fruits.add("Cherry");
        fruits.add("Mango");
        System.out.println("Initial Fruits: " + fruits);

        // 2. add at specific index
        fruits.add(1, "Blueberry"); // Inserts at index 1, shifting others right
        System.out.println("After insert at index 1: " + fruits);


        System.out.println("\n=== 2. READING & UPDATING ELEMENTS ===");
        // get(index)
        System.out.println("Element at index 2: " + fruits.get(2));

        // set(index, newElement) -> replaces old value
        fruits.set(0, "Avocado");
        System.out.println("After replacing index 0: " + fruits);

        // size() and isEmpty()
        System.out.printf("List Size: %d | Is Empty: %s%n", fruits.size(), fruits.isEmpty());


        System.out.println("\n=== 3. REMOVING & SEARCHING ===");
        // remove by index or by object
        fruits.remove(2); // removes element at index 2
        fruits.remove("Mango"); // removes first occurrence of "Mango"
        System.out.println("After removals: " + fruits);

        // contains() & indexOf()
        System.out.println("Contains 'Blueberry'? " + fruits.contains("Blueberry"));
        System.out.println("Index of 'Banana': " + fruits.indexOf("Banana"));


        System.out.println("\n=== 4. ITERATING OVER AN ARRAYLIST ===");
        // Approach A: Enhanced for-each loop
        System.out.print("For-each loop: ");
        for (String f : fruits) {
            System.out.print(f + " | ");
        }
        System.out.println();

        // Approach B: Iterator (Safe for removing while looping!)
        System.out.print("Iterator loop: ");
        Iterator<String> it = fruits.iterator();
        while (it.hasNext()) {
            String item = it.next();
            System.out.print(item + " ~ ");
        }
        System.out.println();

        // Approach C: ListIterator (Supports bi-directional navigation)
        System.out.print("ListIterator (Backward): ");
        ListIterator<String> listIt = fruits.listIterator(fruits.size());
        while (listIt.hasPrevious()) {
            System.out.print(listIt.previous() + " <- ");
        }
        System.out.println();


        System.out.println("\n=== 5. CONVERTING BETWEEN ARRAYS & ARRAYLISTS ===");
        // ArrayList -> Array
        String[] fruitArray = fruits.toArray(new String[0]);
        System.out.println("Converted to Array: " + Arrays.toString(fruitArray));

        // Array -> ArrayList
        String[] colors = {"Red", "Green", "Blue", "Yellow"};
        List<String> colorList = new ArrayList<>(Arrays.asList(colors));
        colorList.add("Purple");
        System.out.println("Converted from Array to Modifiable List: " + colorList);

        // ============================================================
        // KEY TAKEAWAYS:
        // ============================================================
        // - `ArrayList<E>` automatically resizes and provides fast random access ($O(1)$).
        // - Insertions/deletions in the middle require shifting ($O(n)$).
        // - Use `Iterator.remove()` to safely delete items during iteration.
        // - Program to interface: `List<String> list = new ArrayList<>();`.
        // ============================================================
    }
}
