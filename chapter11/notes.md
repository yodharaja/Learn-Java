# Chapter 11 — Functional Java: Lambdas & Streams

## Quick Reference Cheat Sheet

---

## 1. Lambda Syntax Cheat Sheet

```java
() -> System.out.println("No args")
x -> x * 2                          // Single arg (no parens needed)
(a, b) -> a + b                     // Multiple args
(a, b) -> {                         // Multi-line body with explicit return
    double sum = a + b;
    return sum / 2;
}
```

---

## 2. Standard Built-in Functional Interfaces (`java.util.function`)

| Interface | Method Signature | Return | Typical Usage |
|---|---|---|---|
| `Predicate<T>` | `boolean test(T t)` | `boolean` | `.filter(x -> x > 0)` |
| `Function<T, R>` | `R apply(T t)` | `R` | `.map(User::getEmail)` |
| `Consumer<T>` | `void accept(T t)` | `void` | `.forEach(System.out::println)` |
| `Supplier<T>` | `T get()` | `T` | `Optional.orElseGet(factory)` |
| `BiFunction<T,U,R>` | `R apply(T t, U u)` | `R` | Custom 2-input transformations |

---

## 3. Stream API Pipeline Architecture

```
[Collection / Array]
        │ .stream()
        ▼
[Intermediate Ops] (Lazy, Returns Stream<T>)
  - .filter(Predicate)
  - .map(Function)
  - .sorted(Comparator)
  - .distinct()
  - .limit(n) / .skip(n)
        │
        ▼
[Terminal Ops] (Triggers Execution, Consumes Stream)
  - .toList() / .collect(Collectors.toList())
  - .collect(Collectors.groupingBy(...))
  - .forEach(Consumer)
  - .reduce(...)
  - .count() / .min() / .max()
  - .anyMatch() / .allMatch()
```

---

## 4. `Optional<T>` Safe Container

```java
// Creation:
Optional<User> userOpt = Optional.ofNullable(findUser(id));

// Safe Extraction:
userOpt.ifPresent(u -> sendEmail(u.getEmail()));
User user = userOpt.orElse(DEFAULT_GUEST_USER);
User user = userOpt.orElseThrow(() -> new NotFoundException("Missing user"));

// Functional map chaining:
String domain = userOpt
    .map(User::getEmail)
    .map(email -> email.split("@")[1])
    .orElse("unknown.com");
```

---

## 5. Method Reference Variants (`::`)

| Pattern | Lambda Equivalent | Method Reference |
|---|---|---|
| Static Method | `s -> Integer.parseInt(s)` | `Integer::parseInt` |
| Specific Object Method | `msg -> System.out.println(msg)` | `System.out::println` |
| Arbitrary Object Method | `str -> str.toUpperCase()` | `String::toUpperCase` |
| Constructor | `() -> new ArrayList<>()` | `ArrayList::new` |
