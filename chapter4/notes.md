# Chapter 4 — Methods (Functions)

## Quick Reference Cheat Sheet

---

## 1. Anatomy of a Method

```java
public static returnType methodName(parameterType param1, parameterType param2) {
    // Method body
    return result; // Required if returnType is not void
}
```

- **`returnType`**: The data type returned by method (`int`, `String`, `double`, etc.). Use `void` if nothing is returned.
- **Parameters vs Arguments**:
  - *Parameters*: Variables defined in method header (`int a, int b`).
  - *Arguments*: Actual values passed in when invoking the method (`add(5, 10)`).

---

## 2. Pass By Value in Java

> [!IMPORTANT]
> **Java is ALWAYS Pass-by-Value!**

- **Primitives (`int`, `double`, etc.)**: A copy of the value is passed. Modifications inside the method **do not** affect the original variable.
- **Objects & Arrays**: A copy of the *reference (memory address)* is passed. Modifying the contents/elements through the reference **will affect** the original object, but reassigning the reference itself (`arr = new int[5]`) will not change the original caller's reference.

---

## 3. Method Overloading

Multiple methods in the same class can have the **same name** if their parameter lists are distinct:

✅ Allowed differences:
1. Different **number** of parameters: `add(int, int)` vs `add(int, int, int)`
2. Different **types** of parameters: `add(int, int)` vs `add(double, double)`
3. Different **order** of types: `info(String, int)` vs `info(int, String)`

❌ NOT valid overloading:
- Changing **only the return type**: `int calc(int x)` vs `double calc(int x)` -> *Compilation error!*
- Changing **only the parameter names**: `void run(int speed)` vs `void run(int rate)` -> *Compilation error!*

---

## 4. Recursion

A method that calls itself. Every recursive function **must** have:
1. **Base Case**: The condition where recursion stops.
2. **Recursive Step**: The self-call moving towards the base case.

```java
// Factorial Example
public static long factorial(int n) {
    if (n <= 1) return 1;          // Base case
    return n * factorial(n - 1);  // Recursive step
}
```

> ⚠️ **StackOverflowError**: Happens if base case is missing or recursion runs too deep.

---

## 5. Variable Arguments (Varargs `...`)

Allows passing zero, one, or multiple arguments into a method as an array:

```java
public static int sum(int... nums) {
    int total = 0;
    for (int n : nums) total += n;
    return total;
}

// Calling:
sum();             // 0
sum(10, 20);       // 30
sum(1, 2, 3, 4, 5);// 15
```

### Varargs Rules:
1. Only **one** varargs parameter allowed per method.
2. The varargs parameter **must be the last parameter** in the list:
   `void log(String tag, int... values)` ✅
   `void log(int... values, String tag)` ❌

---

## 6. Common Mistakes to Avoid

1. **Missing `return` statement** in non-void method branches.
2. **Putting code after `return`** (unreachable code compiler error).
3. **Infinite recursion** by not reducing the problem toward base case.
4. **Modifying primitive arguments** expecting the caller's variable to change.
5. **Overloading only by return type**.
