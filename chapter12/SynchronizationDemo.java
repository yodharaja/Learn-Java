package chapter12;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * ============================================================
 * LESSON 12.2 — Thread Synchronization & Race Conditions
 * ============================================================
 *
 * A RACE CONDITION occurs when multiple threads attempt to read and modify
 * shared mutable state concurrently without proper synchronization,
 * leading to corrupted data.
 *
 * Solutions:
 *   1. `synchronized` methods: Locks the entire method on the instance monitor (`this`).
 *   2. `synchronized(lock)` blocks: Fine-grained locking on a specific mutex object.
 *   3. `java.util.concurrent.atomic` (e.g. `AtomicInteger`): Lock-free, hardware-level CAS
 *      (Compare-And-Swap) operations for maximum performance!
 *   4. `volatile` keyword: Guarantees visibility of variable changes across CPU caches.
 */

// 1. Unsafe Counter (Has Race Condition!)
class UnsafeCounter {
    private int count = 0;

    // 'count++' is NOT atomic! It is 3 CPU ops: READ -> MODIFY -> WRITE
    public void increment() {
        count++;
    }

    public int getCount() { return count; }
}

// 2. Synchronized Counter (Thread-Safe with intrinsic lock)
class SynchronizedCounter {
    private int count = 0;
    private final Object lock = new Object(); // Custom mutex lock

    // Using synchronized block
    public void increment() {
        synchronized (lock) {
            count++;
        }
    }

    public int getCount() {
        synchronized (lock) {
            return count;
        }
    }
}

// 3. Atomic Counter (Lock-Free, High Performance)
class AtomicCounter {
    private final AtomicInteger count = new AtomicInteger(0);

    public void increment() {
        count.incrementAndGet(); // Atomic CAS operation!
    }

    public int getCount() {
        return count.get();
    }
}

public class SynchronizationDemo {

    public static void main(String[] args) throws InterruptedException {
        int totalIncrementsPerThread = 10000;

        System.out.println("=== 1. TESTING UNSAFE COUNTER (RACE CONDITION) ===");
        UnsafeCounter unsafe = new UnsafeCounter();

        Thread t1 = new Thread(() -> {
            for (int i = 0; i < totalIncrementsPerThread; i++) unsafe.increment();
        });
        Thread t2 = new Thread(() -> {
            for (int i = 0; i < totalIncrementsPerThread; i++) unsafe.increment();
        });

        t1.start(); t2.start();
        t1.join(); t2.join();

        System.out.println("Expected Count : 20000");
        System.out.println("Actual Count   : " + unsafe.getCount() + " (Data lost due to Race Condition!)");


        System.out.println("\n=== 2. TESTING SYNCHRONIZED COUNTER (THREAD-SAFE) ===");
        SynchronizedCounter safeSync = new SynchronizedCounter();

        Thread t3 = new Thread(() -> {
            for (int i = 0; i < totalIncrementsPerThread; i++) safeSync.increment();
        });
        Thread t4 = new Thread(() -> {
            for (int i = 0; i < totalIncrementsPerThread; i++) safeSync.increment();
        });

        t3.start(); t4.start();
        t3.join(); t4.join();

        System.out.println("Expected Count : 20000");
        System.out.println("Actual Count   : " + safeSync.getCount() + " (✓ Perfectly synchronized!)");


        System.out.println("\n=== 3. TESTING ATOMIC INTEGER COUNTER (LOCK-FREE CAS) ===");
        AtomicCounter atomicSafe = new AtomicCounter();

        Thread t5 = new Thread(() -> {
            for (int i = 0; i < totalIncrementsPerThread; i++) atomicSafe.increment();
        });
        Thread t6 = new Thread(() -> {
            for (int i = 0; i < totalIncrementsPerThread; i++) atomicSafe.increment();
        });

        t5.start(); t6.start();
        t5.join(); t6.join();

        System.out.println("Expected Count : 20000");
        System.out.println("Actual Count   : " + atomicSafe.getCount() + " (✓ High speed Atomic CAS!)");

        // ============================================================
        // KEY TAKEAWAYS:
        // ============================================================
        // - `count++` is not atomic; always protect shared mutable state.
        // - Use `synchronized` blocks when coordinating multi-variable updates.
        // - Prefer `AtomicInteger` / `AtomicLong` for simple counters (lock-free speed).
        // ============================================================
    }
}
