package chapter1;

/**
 * ============================================================
 * LESSON 1.3 — Java Memory Model: Stack vs Heap & References
 * ============================================================
 *
 * Understanding how Java manages memory is crucial for mastering OOP:
 *
 * 1. STACK MEMORY:
 *    - Stores primitive local variables (`int x = 10;`).
 *    - Stores REFERENCE variables (memory addresses pointing to objects).
 *    - Fast, organized as LIFO (Last-In-First-Out) frames corresponding to method calls.
 *    - Memory is automatically reclaimed when a method finishes.
 *
 * 2. HEAP MEMORY:
 *    - Stores ALL actual OBJECT instances created with the `new` keyword.
 *    - Shared globally across all threads in the JVM.
 *    - Managed by the Garbage Collector (GC).
 *
 * 3. REFERENCE COPIES:
 *    - Assigning `obj2 = obj1` does NOT copy the object!
 *    - It copies the MEMORY ADDRESS (reference), so both point to the EXACT SAME object in Heap!
 */

class DeviceNode {
    String deviceName;
    int batteryLevel;

    DeviceNode(String name, int battery) {
        this.deviceName = name;
        this.batteryLevel = battery;
    }
}

public class MemoryModel {

    public static void main(String[] args) {
        System.out.println("=== 1. STACK PRIMITIVES VS HEAP OBJECTS ===");
        // 'primitiveVal' lives directly on the STACK
        int primitiveVal = 42;

        // 'd1' is a reference on the STACK pointing to a DeviceNode object in the HEAP
        DeviceNode d1 = new DeviceNode("Laptop", 100);

        System.out.println("Primitive on Stack : " + primitiveVal);
        System.out.println("Object in Heap     : " + d1.deviceName + " (" + d1.batteryLevel + "%)");


        System.out.println("\n=== 2. MULTIPLE REFERENCES TO THE SAME HEAP OBJECT ===");
        // d2 is created on STACK, pointing to the SAME heap address as d1:
        DeviceNode d2 = d1;

        System.out.println("Are d1 and d2 pointing to the same memory address? " + (d1 == d2));

        // Modifying through d2 modifies the shared object in the Heap!
        d2.batteryLevel = 45;

        System.out.println("d2.batteryLevel updated to 45%");
        System.out.println("Value seen via d1: " + d1.batteryLevel + "% (Changed!)");


        System.out.println("\n=== 3. NULL REFERENCES & GARBAGE COLLECTION ===");
        // When an object has ZERO references pointing to it, it becomes eligible for Garbage Collection
        DeviceNode d3 = new DeviceNode("TempSensor", 90);
        d3 = null; // Object is now orphaned in Heap -> Garbage Collector will reclaim its memory!

        System.out.println("d3 is now null. Trying to access fields would throw NullPointerException.");

        // ============================================================
        // KEY TAKEAWAYS:
        // ============================================================
        // - Primitive variables store raw values directly on the Stack.
        // - Object reference variables store Heap memory addresses on the Stack.
        // - Copying an object variable (`b = a`) copies the address, not the object.
        // - Unreferenced Heap objects are automatically cleaned up by the Garbage Collector.
        // ============================================================
    }
}
