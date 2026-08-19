# Chapter 2 — Variables, Data Types & Operators

## Quick Reference Cheat Sheet

---

## 1. Primitive Data Types

| Type | Size | Range | Default | Example |
|---|---|---|---|---|
| `byte` | 1 byte | -128 to 127 | 0 | `byte b = 100;` |
| `short` | 2 bytes | -32,768 to 32,767 | 0 | `short s = 30000;` |
| `int` | 4 bytes | ±2.1 billion | 0 | `int i = 42;` |
| `long` | 8 bytes | ±9.2 quintillion | 0L | `long l = 100L;` |
| `float` | 4 bytes | ~7 decimal digits | 0.0f | `float f = 3.14f;` |
| `double` | 8 bytes | ~15 decimal digits | 0.0 | `double d = 3.14;` |
| `char` | 2 bytes | Unicode character | '\u0000' | `char c = 'A';` |
| `boolean` | — | true / false | false | `boolean b = true;` |

> **Rule of thumb**: Use `int` for whole numbers, `double` for decimals.

---

## 2. Type Casting

```
Widening (automatic, safe):
byte → short → int → long → float → double

Narrowing (manual, risky — needs explicit cast):
double → float → long → int → short → byte
```

```java
// Widening (auto)
int x = 42;
double d = x;          // 42.0

// Narrowing (manual)
double pi = 3.14;
int truncated = (int) pi;  // 3 (NOT rounded!)
```

> ⚠️ **Integer division pitfall**: `7 / 2 = 3` (not 3.5). Fix: `7 / 2.0` or `(double) 7 / 2`

---

## 3. Operators

### Arithmetic
| Operator | Meaning | Example |
|---|---|---|
| `+` | Addition | `5 + 3 = 8` |
| `-` | Subtraction | `5 - 3 = 2` |
| `*` | Multiplication | `5 * 3 = 15` |
| `/` | Division | `5 / 3 = 1` (int) |
| `%` | Modulus (remainder) | `5 % 3 = 2` |

### Assignment Shorthand
`+=`, `-=`, `*=`, `/=`, `%=`
```java
x += 5;  // same as x = x + 5
```

### Comparison (return boolean)
`==`, `!=`, `>`, `<`, `>=`, `<=`

### Logical
| Operator | Meaning | Example |
|---|---|---|
| `&&` | AND | `true && false = false` |
| `\|\|` | OR | `true \|\| false = true` |
| `!` | NOT | `!true = false` |

### Increment/Decrement
```java
x++;  // post-increment (use then add)
++x;  // pre-increment (add then use)
x--;  // post-decrement
--x;  // pre-decrement
```

### Ternary
```java
String result = (score >= 40) ? "PASS" : "FAIL";
```

---

## 4. Strings (Key Points)

```java
// Creation
String name = "Yodha";

// ⚠️ Compare with .equals(), NEVER ==
"Hello".equals("Hello")          // true (correct!)
"Hello" == "Hello"               // sometimes true, unreliable

// Essential methods
str.length()                     // length (has parentheses!)
str.charAt(0)                    // character at index
str.toUpperCase()                // "HELLO"
str.toLowerCase()                // "hello"
str.trim()                       // remove whitespace
str.substring(0, 5)              // extract portion
str.indexOf("World")             // find position (-1 if not found)
str.contains("World")            // true/false
str.replace("old", "new")        // replace text
str.split(" ")                   // split into array
String.format("Hi %s", name)     // formatted string

// Strings are IMMUTABLE — methods return NEW strings
```

---

## 5. Scanner (User Input)

```java
import java.util.Scanner;

Scanner sc = new Scanner(System.in);

String line = sc.nextLine();     // full line
String word = sc.next();         // single word
int num     = sc.nextInt();      // integer
double dec  = sc.nextDouble();   // decimal
boolean b   = sc.nextBoolean();  // true/false

sc.close();                      // close when done
```

> ⚠️ **The #1 pitfall**: After `nextInt()` or `nextDouble()`, add `sc.nextLine()` to consume the leftover newline before reading a string.

---

## 6. Parsing (String ↔ Number)

```java
// String → Number
int n    = Integer.parseInt("42");
double d = Double.parseDouble("3.14");

// Number → String
String s = String.valueOf(42);
String s = 42 + "";             // quick trick
```

---

## Common Mistakes to Avoid

1. **Using `=` instead of `==`** in conditions
2. **Using `==` to compare Strings** — use `.equals()`
3. **Forgetting `L` suffix for long**, `f` suffix for float
4. **Integer division** giving unexpected results (`7/2 = 3`)
5. **Not consuming newline** after `nextInt()` before `nextLine()`
6. **Uninitialized local variables** — Java won't compile
