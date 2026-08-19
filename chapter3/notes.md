# Chapter 3 — Control Flow

## Quick Reference Cheat Sheet

---

## 1. If-Else

```java
// Simple if
if (condition) {
    // runs if true
}

// If-else
if (condition) {
    // true path
} else {
    // false path
}

// If — else if — else
if (score >= 90) {
    grade = "A";
} else if (score >= 80) {
    grade = "B";
} else if (score >= 70) {
    grade = "C";
} else {
    grade = "F";
}
```

> **Rule**: Check strictest condition first. Only ONE block runs.

---

## 2. Switch

```java
// Traditional (needs break!)
switch (variable) {
    case value1:
        // code
        break;
    case value2:
        // code
        break;
    default:
        // fallback
}

// Enhanced (Java 14+, no break needed)
String result = switch (day) {
    case 1 -> "Monday";
    case 2 -> "Tuesday";
    case 6, 7 -> "Weekend";
    default -> "Other";
};
```

| Feature | Traditional | Enhanced (14+) |
|---|---|---|
| Break needed? | ✅ Yes | ❌ No |
| Fall-through? | ⚠️ Yes | ❌ No |
| Returns value? | ❌ No | ✅ Yes (expression) |
| Syntax | `case: ... break;` | `case -> ...` |

> Works with: `int`, `byte`, `short`, `char`, `String`, `enum`

---

## 3. For Loop

```java
// Traditional for loop
for (int i = 0; i < 10; i++) {
    System.out.println(i);
}

// For-each (read-only, no index)
for (String item : array) {
    System.out.println(item);
}
```

| Use For | Use For-Each |
|---|---|
| Need the index | Just reading elements |
| Need to modify elements | Simpler, cleaner code |
| Custom step size (i += 2) | Any iterable collection |

---

## 4. While & Do-While

```java
// While — checks BEFORE (may never run)
while (condition) {
    // code
    // update!
}

// Do-While — checks AFTER (always runs at least once)
do {
    // code
} while (condition);  // ← semicolon!
```

| | while | do-while |
|---|---|---|
| Checks condition | Before body | After body |
| Minimum runs | 0 | 1 |
| Best for | Unknown iterations | Input validation, menus |

---

## 5. Break & Continue

```java
// break — EXIT the entire loop
for (int i = 0; i < 100; i++) {
    if (i == 5) break;  // stops at i=5
}

// continue — SKIP to next iteration
for (int i = 0; i < 10; i++) {
    if (i % 2 == 0) continue;  // skip even numbers
    System.out.println(i);      // prints 1, 3, 5, 7, 9
}

// labeled break — exit OUTER loop from inner loop
outerLoop:
for (int i = 0; i < 10; i++) {
    for (int j = 0; j < 10; j++) {
        if (condition) break outerLoop;
    }
}
```

---

## 6. Pattern Formulas

| Pattern | Spaces per row | Stars per row |
|---|---|---|
| Right triangle | 0 | `row` |
| Pyramid | `n - row` | `2 * row - 1` |
| Inverted pyramid | `row - 1` | `2 * (n - row + 1) - 1` |
| Diamond | Pyramid + Inverted | Combined |

---

## 7. Useful Snippets

```java
// Random number between min and max (inclusive)
int random = (int)(Math.random() * (max - min + 1)) + min;

// Check even/odd
if (num % 2 == 0)  // even
if (num % 2 != 0)  // odd

// Swap two variables
int temp = a;
a = b;
b = temp;

// Sum digits of a number
while (num > 0) {
    int digit = num % 10;
    sum += digit;
    num /= 10;
}
```

---

## Common Mistakes to Avoid

1. **Infinite loop** — forgetting to update the loop variable
2. **Off-by-one errors** — `<` vs `<=`, starting at 0 vs 1
3. **Missing break in switch** — causes fall-through
4. **Semicolon after if/for** — `if (x > 5);` ← empty body!
5. **Using `=` instead of `==`** in conditions
6. **Forgetting do-while semicolon** — `} while (cond);`
