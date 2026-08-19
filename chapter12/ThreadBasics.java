package chapter12;

/**
 * ============================================================
 * LESSON 12.1 — Thread Basics, Runnable & Lifecycle
 * ============================================================
 *
 * A THREAD is the smallest unit of execution inside a process.
 * Multithreading allows Java applications to perform multiple tasks
 * simultaneously (concurrently or in parallel across multi-core CPUs).
 *
 * Two Ways to Create Threads:
 *   1. Extend `java.lang.Thread` class and override `run()`.
 *   2. Implement `java.lang.Runnable` interface (Recommended / Preferred).
 *      - Allows using modern Lambdas (`() -> { ... }`).
 *      - Doesn't waste single class inheritance (`extends`).
 *
 * Thread Lifecycle States:
 *   [NEW] -> start() -> [RUNNABLE] -> [TIMED_WAITING / BLOCKED] -> [TERMINATED]
 *
 * Key Methods:
 *   - `start()`: Creates a new OS thread and calls `run()` asynchronously.
 *   - `Thread.sleep(millis)`: Pauses the current thread.
 *   - `thread.join()`: Forces the current calling thread to wait until target thread finishes.
 */

// Approach 1: Extending Thread
class WorkerThread extends Thread {
    private String taskName;

    public WorkerThread(String taskName) {
        this.taskName = taskName;
    }

    @Override
    public void run() {
        System.out.println("  ⚙️ [" + taskName + "] started on OS thread: " + Thread.currentThread().getName());
        try {
            for (int i = 1; i <= 3; i++) {
                System.out.printf("  ⚙️ [%s] Progress step %d/3...%n", taskName, i);
                Thread.sleep(300); // simulate work
            }
        } catch (InterruptedException e) {
            System.out.println("  ❌ Worker interrupted!");
        }
        System.out.println("  ✓ [" + taskName + "] finished.");
    }
}

public class ThreadBasics {

    public static void main(String[] args) {
        System.out.println("=== 1. CREATING & STARTING THREADS ===");
        System.out.println("Main method running on thread: " + Thread.currentThread().getName());

        // 1. Thread via Class Extension
        WorkerThread worker1 = new WorkerThread("DatabaseBackupTask");

        // 2. Thread via Runnable Interface (Lambda syntax!)
        Thread worker2 = new Thread(() -> {
            System.out.println("  🌐 [NetworkSyncTask] started on thread: " + Thread.currentThread().getName());
            try {
                for (int i = 1; i <= 3; i++) {
                    System.out.printf("  🌐 [NetworkSyncTask] Syncing packet %d/3...%n", i);
                    Thread.sleep(200);
                }
            } catch (InterruptedException e) {
                System.out.println("  ❌ NetworkSync interrupted!");
            }
            System.out.println("  ✓ [NetworkSyncTask] finished.");
        }, "NetworkSync-Thread");

        // MUST call .start(), NEVER call .run() directly!
        // Calling .run() runs synchronously on main thread without creating a new thread!
        worker1.start();
        worker2.start();


        System.out.println("\n=== 2. COORDINATING THREADS WITH join() ===");
        System.out.println("Main thread waiting for background workers to complete...");

        try {
            // join() halts the main thread until worker1 and worker2 finish
            worker1.join();
            worker2.join();
        } catch (InterruptedException e) {
            System.out.println("Main thread interrupted.");
        }

        System.out.println("  🎉 All background threads completed! Main thread resumes.");


        System.out.println("\n=== 3. DAEMON THREADS ===");
        // Daemon threads run in background and automatically terminate when all user threads finish
        Thread garbageWatcher = new Thread(() -> {
            while (true) {
                try {
                    Thread.sleep(500);
                    // System.out.println("  [Daemon] Heartbeat check...");
                } catch (InterruptedException e) {
                    break;
                }
            }
        });
        garbageWatcher.setDaemon(true); // Marks thread as daemon
        garbageWatcher.start();
        System.out.println("Daemon thread started (will terminate automatically on main exit).");

        // ============================================================
        // KEY TAKEAWAYS:
        // ============================================================
        // - Implement `Runnable` or use Lambdas to define thread tasks.
        // - Always invoke `start()` to spawn a real asynchronous thread.
        // - Use `join()` to wait for thread completion.
        // - Daemon threads run silently in the background without keeping the JVM alive.
        // ============================================================
    }
}
