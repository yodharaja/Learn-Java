# Chapter 6 — OOP Part 2: Inheritance & Polymorphism

## Quick Reference Cheat Sheet

---

## 1. Inheritance (`extends`) & `super`

- Establishes an **IS-A** relationship (`Dog IS-A Animal`).
- Java supports **Single Class Inheritance** (a class can only extend one class).
- `super(...)`: Calls the superclass constructor (must be the **first line**).
- `super.method()`: Invokes superclass version of an overridden method.

```java
public class Car extends Vehicle {
    public Car(String brand) {
        super(brand); // Calls Vehicle constructor
    }
}
```

---

## 2. Method Overriding (`@Override`)

- Subclass replaces implementation of a parent class method.
- **Rules**:
  1. Identical method signature (name, return type, parameters).
  2. Access modifier cannot be *more restrictive* than parent.
  3. `private`, `static`, and `final` methods cannot be overridden.

---

## 3. Abstract Classes (`abstract`)

- Cannot be instantiated directly (`new Shape()` ❌).
- May contain **abstract methods** (no body) and **concrete methods** (with body).
- Subclasses must implement all abstract methods unless the subclass is also abstract.

```java
public abstract class Shape {
    protected String color;
    public abstract double calculateArea(); // No body
}
```

---

## 4. Interfaces (`interface` & `implements`)

- Define capability contracts ("CAN-DO").
- A class can implement **multiple interfaces** (`class Drone implements Flyable, Rechargeable`).
- All fields are implicitly `public static final`.
- Methods are implicitly `public abstract` (unless marked `default` or `static`).

```java
public interface Flyable {
    int MAX_ALT = 10000; // Constant
    void fly();          // Abstract method
    default void glide() { ... } // Java 8+ Default method
    static void check() { ... }  // Java 8+ Static utility
}
```

---

## 5. Abstract Class vs Interface

| Dimension | Abstract Class | Interface |
|---|---|---|
| Inheritance | Single (`extends Class`) | Multiple (`implements A, B, C`) |
| Variables | Any instance / static variables | Constants only (`public static final`) |
| Constructors | Yes | No |
| Speed | Slightly faster | Interface method lookup overhead |
| Philosophy | "IS-A" identity | "CAN-DO" behavioral contract |

---

## 6. Polymorphism & Type Casting

- **Upcasting**: Child -> Parent (automatic & safe).
  `Shape s = new Circle();`
- **Downcasting**: Parent -> Child (manual, requires care).
  `Circle c = (Circle) s;`
- **`instanceof` & Pattern Matching (Java 16+)**:

```java
// Classic:
if (obj instanceof Circle) {
    Circle c = (Circle) obj;
    c.draw();
}

// Java 16+ Pattern Matching:
if (obj instanceof Circle c) {
    c.draw();
}
```
