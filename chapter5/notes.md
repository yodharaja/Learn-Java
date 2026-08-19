# Chapter 5 — OOP Part 1: Classes Deep Dive

## Quick Reference Cheat Sheet

---

## 1. Constructors & Constructor Chaining

- Same name as class, **no return type**.
- Automatically called on `new ClassName()`.
- Use `this(...)` to call another constructor in the same class (must be the **first line**).

```java
public class User {
    private String name;
    private int age;

    public User() {
        this("Anonymous", 18); // Chained call
    }

    public User(String name, int age) {
        this.name = name;
        this.age = age;
    }
}
```

---

## 2. Access Modifiers Table

| Modifier | Same Class | Same Package | Subclass (Any) | World (Outside) |
|---|---|---|---|---|
| `private` | ✅ | ❌ | ❌ | ❌ |
| *(default)* | ✅ | ✅ | ❌ | ❌ |
| `protected` | ✅ | ✅ | ✅ | ❌ |
| `public` | ✅ | ✅ | ✅ | ✅ |

> **Encapsulation Rule**: Keep fields `private` and provide controlled access through `public` getters and setters.

---

## 3. JavaBean Conventions & Getters/Setters

- Getters: `getPropertyName()` (or `isPropertyName()` for `boolean`)
- Setters: `setPropertyName(value)`
- Setters should contain data validation logic.

```java
public class Account {
    private double balance;

    public double getBalance() { return balance; }

    public void setBalance(double balance) {
        if (balance >= 0) this.balance = balance;
    }
}
```

---

## 4. The `static` Keyword

- Belongs to the **class itself**, not individual instances.
- Shared across all objects in memory.
- `static` methods **cannot** reference `this` or instance variables.
- Utility functions should usually be `public static`.

```java
public class MathConstants {
    public static final double PI = 3.14159;
    public static int square(int x) { return x * x; }
}
```

---

## 5. The `final` Keyword

- `final variable`: Constant (cannot be reassigned).
- `final method`: Cannot be overridden in subclasses.
- `final class`: Cannot be extended/inherited (e.g. `String`).

---

## 6. Overriding `Object` Class Methods

Every class in Java extends `java.lang.Object`.

```java
@Override
public String toString() {
    return "User[name=" + name + "]";
}

@Override
public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    User other = (User) o;
    return Objects.equals(this.name, other.name);
}

@Override
public int hashCode() {
    return Objects.hash(name);
}
```

> **Contract**: If `a.equals(b) == true`, then `a.hashCode() == b.hashCode()`.
