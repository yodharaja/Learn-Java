# Chapter 12 — Multithreading & Concurrency Basics

## Quick Reference Cheat Sheet

---

## 1. Creating Threads

```java
// Option A: Lambda with Runnable (Preferred!)
Thread t = new Thread(() -> {
    System.out.println("Running on thread: " + Thread.currentThread().getName());
});
t.start(); // ALWAYS start(), never run()!

// Option B: Extending Thread
class MyThread extends Thread {
    public void run() { ... }
}
```

- `thread.join()`: Blocks current thread until the target thread finishes execution.
- `thread.setDaemon(true)`: Background thread that terminates when all user threads stop.

---

## 2. Thread Synchronization

Preventing race conditions on shared mutable data:

```java
// Synchronized block on mutex lock:
private final Object lock = new Object();

public void increment() {
    synchronized (lock) {
        count++;
    }
}

// Lock-Free Hardware Atomic Counter (Fastest):
private final AtomicInteger count = new AtomicInteger(0);
count.incrementAndGet();
```

---

## 3. ExecutorService Thread Pools

```java
// Create a fixed worker pool:
ExecutorService executor = Executors.newFixedThreadPool(4);

// Submit async Callable task (returns value):
Future<Double> future = executor.submit(() -> {
    return calculateComplexInterest();
});

// Await result:
Double result = future.get(); // Blocks until complete

// ALWAYS shutdown:
executor.shutdown();
```

---

## 4. `Runnable` vs `Callable<V>`

| Feature | `Runnable` | `Callable<V>` |
|---|---|---|
| Method | `void run()` | `V call()` |
| Return Value | ❌ None (`void`) | ✅ Returns type `V` |
| Exceptions | Cannot throw checked exceptions | Can throw checked exceptions |
| Execution | `new Thread(r)` or `executor.submit(r)` | `executor.submit(c)` -> returns `Future<V>` |
