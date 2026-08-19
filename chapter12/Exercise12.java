package chapter12;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

/**
 * ============================================================
 * EXERCISE 12 — Multi-Threaded Asset & Report Downloader ⚡
 * ============================================================
 *
 * Build a Concurrent Download & Batch Processing Engine featuring:
 *   1. Thread Pool (`ExecutorService`) with configurable worker pool size.
 *   2. `DownloadTask` implementing `Callable<DownloadResult>`:
 *      - Simulates variable latency network transfers
 *      - Returns downloaded byte size, duration, and status
 *   3. Real-time atomic progress counters (`AtomicInteger`, `AtomicLong`).
 *   4. Gathers all `Future<DownloadResult>` to print summary metrics.
 * ============================================================
 */

class DownloadResult {
    private String filename;
    private long bytesDownloaded;
    private long durationMs;
    private boolean success;

    public DownloadResult(String filename, long bytesDownloaded, long durationMs, boolean success) {
        this.filename = filename;
        this.bytesDownloaded = bytesDownloaded;
        this.durationMs = durationMs;
        this.success = success;
    }

    public String getFilename() { return filename; }
    public long getBytesDownloaded() { return bytesDownloaded; }
    public long getDurationMs() { return durationMs; }
    public boolean isSuccess() { return success; }

    @Override
    public String toString() {
        return String.format("  [%s] %-25s | %,8d KB | %4d ms | %s",
                success ? "✓" : "❌", filename, bytesDownloaded / 1024, durationMs, success ? "DONE" : "FAILED");
    }
}

class DownloadWorker implements Callable<DownloadResult> {
    private String filename;
    private long expectedSizeBytes;
    private static final AtomicInteger activeThreads = new AtomicInteger(0);

    public DownloadWorker(String filename, long expectedSizeBytes) {
        this.filename = filename;
        this.expectedSizeBytes = expectedSizeBytes;
    }

    @Override
    public DownloadResult call() {
        int currentActive = activeThreads.incrementAndGet();
        long startTime = System.currentTimeMillis();

        try {
            // Simulate variable network speed (100ms - 400ms)
            long simulatedDelay = (long) (100 + (Math.random() * 300));
            Thread.sleep(simulatedDelay);

            long elapsed = System.currentTimeMillis() - startTime;
            return new DownloadResult(filename, expectedSizeBytes, elapsed, true);
        } catch (InterruptedException e) {
            return new DownloadResult(filename, 0, System.currentTimeMillis() - startTime, false);
        } finally {
            activeThreads.decrementAndGet();
        }
    }
}

public class Exercise12 {

    public static void main(String[] args) {
        System.out.println("╔══════════════════════════════════════════════════╗");
        System.out.println("║      ⚡ MULTI-THREADED ASSET DOWNLOADER          ║");
        System.out.println("╚══════════════════════════════════════════════════╝");

        int workerPoolSize = 4;
        ExecutorService pool = Executors.newFixedThreadPool(workerPoolSize);

        List<DownloadWorker> tasks = List.of(
            new DownloadWorker("jdk-21-windows-x64.msi", 185_000_000L),
            new DownloadWorker("springboot-framework.zip", 42_500_000L),
            new DownloadWorker("database_backup_2026.sql", 650_000_000L),
            new DownloadWorker("ml_dataset_weights.bin", 320_000_000L),
            new DownloadWorker("ui_assets_bundle.tar.gz", 18_200_000L),
            new DownloadWorker("video_tutorial_1080p.mp4", 450_000_000L),
            new DownloadWorker("microservices_docs.pdf", 5_800_000L),
            new DownloadWorker("security_certificates.pem", 120_000L)
        );

        System.out.printf("Dispatching %,d download tasks across %d worker threads in pool...%n%n",
                tasks.size(), workerPoolSize);

        long overallStart = System.currentTimeMillis();

        try {
            // Submit all tasks concurrently
            List<Future<DownloadResult>> futures = pool.invokeAll(tasks);

            System.out.println("--- DOWNLOAD COMPLETION LOGS ---");
            long totalBytesDownloaded = 0;
            int successfulDownloads = 0;

            for (Future<DownloadResult> f : futures) {
                DownloadResult result = f.get(); // Await each result
                System.out.println(result);
                if (result.isSuccess()) {
                    totalBytesDownloaded += result.getBytesDownloaded();
                    successfulDownloads++;
                }
            }

            long totalTimeMs = System.currentTimeMillis() - overallStart;

            System.out.println("\n╔══════════════════════════════════════════════════╗");
            System.out.println("║             📊 DOWNLOAD SESSION REPORT           ║");
            System.out.println("╠══════════════════════════════════════════════════╣");
            System.out.printf("║  Files Transferred    : %d / %-24d ║%n", successfulDownloads, tasks.size());
            System.out.printf("║  Total Data Downloaded: %,.2f MB%-23s ║%n", (totalBytesDownloaded / (1024.0 * 1024.0)), "");
            System.out.printf("║  Total Wall-Clock Time: %d ms%-28s ║%n", totalTimeMs, "");
            System.out.printf("║  Throughput Efficiency: %,.2f MB/sec%-19s ║%n",
                    (totalBytesDownloaded / (1024.0 * 1024.0)) / (totalTimeMs / 1000.0), "");
            System.out.println("╚══════════════════════════════════════════════════╝");

        } catch (Exception e) {
            System.out.println("❌ Manager Failure: " + e.getMessage());
        } finally {
            pool.shutdown();
        }
    }
}
