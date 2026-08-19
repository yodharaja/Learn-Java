# Chapter 1 — Java Basics, Classes & Objects

## Quick Reference Cheat Sheet

---

## 1. Java Architecture & Execution

```
[Java Source (.java)] ──(javac)──> [Bytecode (.class)] ──(JVM)──> [Native Machine Code]
```

- **JDK (Java Development Kit)**: Includes compiler (`javac`), debugger, and development tools.
- **JRE (Java Runtime Environment)**: JVM + Core class libraries required to run Java apps.
- **JVM (Java Virtual Machine)**: The virtual engine executing bytecode (enables WORA: *Write Once, Run Anywhere*).

---

## 2. Anatomy of `main()` Method

```java
public static void main(String[] args) {
    System.out.println("Hello, World!");
}
```

- `public`: Accessible to the JVM from outside the class.
- `static`: Can be invoked without creating an instance of the class first.
- `void`: Does not return any value.
- `main`: Conventional entry-point method name.
- `String[] args`: Array of command-line arguments.

---

## 3. Classes vs Objects

| Feature | Class | Object |
|---|---|---|
| Definition | Blueprint / Template / Logical construct | Physical instance in memory |
| Memory | Consumes no object memory until instantiated | Allocates memory in **Heap** |
| Creation | Declared with `class Name { ... }` | Created using `new ClassName()` |
| Example | `class Car { ... }` | `Car myCar = new Car();` |

---

## 4. Stack vs Heap Memory

```
┌───────────────────────────┐      ┌───────────────────────────────────┐
│       STACK MEMORY        │      │            HEAP MEMORY            │
├───────────────────────────┤      ├───────────────────────────────────┤
│ - Primitive variables     │      │ - All Objects created with 'new'  │
│   (int, double, boolean)  │      │ - Instance fields                 │
│ - Reference addresses     │ ────>│ - Garbage Collector reclaims      │
│ - Fast, method-scoped     │      │   unreferenced objects            │
└───────────────────────────┘      └───────────────────────────────────┘
```

> ⚠️ **Reference Copies**: `Car c2 = c1;` copies the **memory address**, NOT the object. Both references point to the exact same object in Heap!
