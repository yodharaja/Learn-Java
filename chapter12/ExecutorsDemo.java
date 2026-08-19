package chapter12;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

/**
 * ============================================================
 * LESSON 12.3 — Thread Pools, ExecutorService, Callable & Future
 * ============================================================
 *
 * Why avoid creating raw `new Thread()` manually in production?
 *   - Creating an OS thread is expensive (1MB stack memory per thread).
 *   - Uncapped thread creation causes `OutOfMemoryError` under high traffic.
 *   - A THREAD POOL maintains a fixed queue of reusable worker threads.
 *
 * `Callable<V>` vs `Runnable`:
 *   - `Runnable`: Cannot return a value (`void run()`), cannot throw checked exceptions.
 *   - `Callable<V>`: Can RETURN a result (`V call()`), can throw checked exceptions!
 *
 * `Future<V>`:
 *   - A handle representing the pending asynchronous result of a Callable task.
 *   - `future.get()`: Blocks until the task completes and returns the result `V`.
 *   - `future.isDone()`: Checks if calculation finished.
 */
public class ExecutorsDemo {

    public static void main(String[] args) {
        System.out.println("=== 1. FIXED THREAD POOL WITH RUNNABLE TASKS ===");
        // Create a pool with 3 reusable worker threads
        ExecutorService executor = Executors.newFixedThreadPool(3);

        // Submit 6 tasks to the pool (they share the 3 worker threads)
        for (int i = 1; i <= 6; i++) {
            final int taskId = i;
            executor.submit(() -> {
                System.out.printf("  [Pool Worker] Task %d running on %s%n",
                        taskId, Thread.currentThread().getName());
                try {
                    Thread.sleep(200);
                } catch (InterruptedException ignored) {}
            });
        }


        System.out.println("\n=== 2. ASYNCHRONOUS TASKS WITH CALLABLE & FUTURE ===");
        // Callable tasks that calculate heavy mathematical results and return values
        List<Callable<Long>> mathTasks = new ArrayList<>();

        for (int n = 35; n <= 39; n++) {
            final int num = n;
            mathTasks.add(() -> {
                long start = System.currentTimeMillis();
                long fib = calculateFibonacci(num);
                long elapsed = System.currentTimeMillis() - start;
                System.out.printf("  ✓ [Async Compute] fib(%d) = %,d (%d ms on %s)%n",
                        num, fib, elapsed, Thread.currentThread().getName());
                return fib;
            });
        }

        try {
            // submit all Callable tasks and receive List of Future handles
            List<Future<Long>> futures = executor.invokeAll(mathTasks);

            System.out.println("\nRetrieving results from Future handles (future.get()):");
            long totalSum = 0;
            for (Future<Long> f : futures) {
                totalSum += f.get(); // Blocks until result is computed
            }
            System.out.printf("Total Combined Fibonacci Sum: %,d%n", totalSum);

        } catch (InterruptedException | ExecutionException e) {
            System.out.println("❌ Execution error: " + e.getMessage());
        }


        System.out.println("\n=== 3. GRACEFUL SHUTDOWN OF EXECUTOR SERVICE ===");
        // ALWAYS shutdown ExecutorService, otherwise JVM will remain running forever!
        executor.shutdown(); // Reject new tasks, complete existing ones
        try {
            if (!executor.awaitTermination(3, TimeUnit.SECONDS)) {
                executor.shutdownNow(); // Force kill if not finished
            }
            System.out.println("  ✓ Thread Pool successfully terminated.");
        } catch (InterruptedException e) {
            executor.shutdownNow();
        }

        // ============================================================
        // KEY TAKEAWAYS:
        // ============================================================
        // - Always use `ExecutorService` (Thread Pools) in modern Java applications.
        // - Use `Callable<T>` when tasks need to return values or throw exceptions.
        // - `Future<T>.get()` blocks to retrieve the result.
        // - Always invoke `executor.shutdown()` to release thread resources.
        // ============================================================
    }

    private static long calculateFibonacci(int n) {
        if (n <= 1) return n;
        long a = 0, b = 1;
        for (int i = 2; i <= n; i++) {
            long temp = a + b;
            a = b;
            b = temp;
        }
        return b;
    }
}
