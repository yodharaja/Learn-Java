# Chapter 8 — Exception Handling

## Quick Reference Cheat Sheet

---

## 1. Java Exception Hierarchy

```
          Throwable
         /         \
      Error       Exception
                 /         \
  Checked Exceptions      RuntimeException (Unchecked)
  (IOException,           (NullPointerException,
   SQLException, etc.)     ArithmeticException,
                           IndexOutOfBoundsException)
```

- **Checked Exceptions**: Compiler forces you to handle them (`try-catch` or `throws`).
- **Unchecked Exceptions (`RuntimeException`)**: Bugs or logic mistakes; compiler doesn't force handling.
- **Errors**: Catastrophic JVM conditions (`OutOfMemoryError`) — do not attempt to catch.

---

## 2. Try - Catch - Finally Flow

```java
try {
    // Risky code that may throw exceptions
} catch (SpecificException e) {
    // Handle specific error
} catch (AnotherException | ThirdException e) { // Multi-catch (Java 7+)
    // Handle either exception
} catch (Exception e) {
    // Catch-all general fallback
} finally {
    // ALWAYS executes (ideal for cleanup)
}
```

> ⚠️ **Order Rule**: Always put more specific catch blocks **before** broader catch blocks (`NullPointerException` before `Exception`).

---

## 3. `throw` vs `throws`

| Keyword | Usage | Where | Example |
|---|---|---|---|
| `throw` | Actually triggers an exception instance | Inside method body | `throw new IllegalArgumentException("Bad input");` |
| `throws` | Declares checked exceptions a method might fire | In method signature | `public void readFile() throws IOException { ... }` |

---

## 4. Writing Custom Exceptions

### Custom Checked Exception:
```java
public class InsufficientFundsException extends Exception {
    public InsufficientFundsException(String msg) {
        super(msg);
    }
}
```

### Custom Unchecked Exception:
```java
public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(String msg) {
        super(msg);
    }
}
```

---

## 5. Try-With-Resources (`AutoCloseable`)

Automatically closes resources when exiting the `try` block:

```java
try (BufferedReader br = new BufferedReader(new FileReader("data.txt"))) {
    return br.readLine();
} // br.close() is called automatically here!
```

- Any class that implements `java.lang.AutoCloseable` can be used in try-with-resources.
- Multiple resources are closed in **reverse order** of declaration.
