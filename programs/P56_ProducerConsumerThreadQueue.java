package programs;

import java.util.LinkedList;
import java.util.Queue;

/**
 * ============================================================
 * PROGRAM 56: Multithreaded Producer-Consumer with wait() & notify()
 * ============================================================
 * Problem: WAP to implement the classic Producer-Consumer pattern
 * using a shared bounded queue with `wait()` and `notifyAll()`.
 * ============================================================
 */

class BoundedBuffer {
    private final Queue<Integer> queue = new LinkedList<>();
    private final int capacity;

    public BoundedBuffer(int capacity) {
        this.capacity = capacity;
    }

    public synchronized void produce(int value) throws InterruptedException {
        while (queue.size() == capacity) {
            System.out.println("  ⚠️ Buffer is FULL! Producer waiting...");
            wait();
        }
        queue.offer(value);
        System.out.printf("  🟢 [PRODUCER] Produced item: %d (Buffer Size: %d)%n", value, queue.size());
        notifyAll();
    }

    public synchronized int consume() throws InterruptedException {
        while (queue.isEmpty()) {
            System.out.println("  ⚠️ Buffer is EMPTY! Consumer waiting...");
            wait();
        }
        int value = queue.poll();
        System.out.printf("  🔴 [CONSUMER] Consumed item: %d (Buffer Size: %d)%n", value, queue.size());
        notifyAll();
        return value;
    }
}

public class P56_ProducerConsumerThreadQueue {

    public static void main(String[] args) throws InterruptedException {
        BoundedBuffer buffer = new BoundedBuffer(3);

        Thread producer = new Thread(() -> {
            try {
                for (int i = 1; i <= 6; i++) {
                    buffer.produce(i * 10);
                    Thread.sleep(150);
                }
            } catch (InterruptedException ignored) {}
        });

        Thread consumer = new Thread(() -> {
            try {
                for (int i = 1; i <= 6; i++) {
                    buffer.consume();
                    Thread.sleep(300); // Consumer is slower to test full buffer condition
                }
            } catch (InterruptedException ignored) {}
        });

        System.out.println("=== PRODUCER-CONSUMER CONCURRENCY DEMO ===");
        producer.start();
        consumer.start();

        producer.join();
        consumer.join();
        System.out.println("✓ All items produced and consumed safely without race conditions.");
    }
}
