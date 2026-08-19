package chapter9;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;

/**
 * ============================================================
 * LESSON 9.2 — LinkedList, Queues & Stacks (Deque)
 * ============================================================
 *
 * `LinkedList<E>` in Java is a DOUBLY-LINKED LIST implementation.
 * Each element is a Node containing data + pointers to `prev` and `next` nodes.
 *
 * It implements multiple core interfaces:
 *   - `List<E>`: Indexed position-based list access.
 *   - `Queue<E>`: First-In-First-Out (FIFO) queue (`offer`, `poll`, `peek`).
 *   - `Deque<E>`: Double-Ended Queue, also used as LIFO Stack (`push`, `pop`, `peek`).
 *
 * ArrayList vs LinkedList:
 * ┌──────────────────────┬────────────────────────────────┬───────────────────────────────┐
 * │ Operation            │ ArrayList                      │ LinkedList                    │
 * ├──────────────────────┼────────────────────────────────┼───────────────────────────────┤
 * │ Get by index         │ O(1) — Instant                 │ O(n) — Must traverse nodes    │
 * │ Add/Remove at ends   │ O(1) amortized                 │ O(1) — Instant pointer update │
 * │ Add/Remove in middle │ O(n) — Array shift required    │ O(1) once node is reached     │
 * │ Memory Overhead      │ Lower (contiguous memory)      │ Higher (Node pointers prev/nxt│
 * └──────────────────────┴────────────────────────────────┴───────────────────────────────┘
 */
public class LinkedListDemo {

    public static void main(String[] args) {
        System.out.println("=== 1. LINKEDLIST AS A FIFO QUEUE (FIRST-IN, FIRST-OUT) ===");
        // Queue represents tasks/messages waiting for processing
        Queue<String> customerQueue = new LinkedList<>();

        // offer() - adds to the end of queue
        customerQueue.offer("Ticket #101 (Alice)");
        customerQueue.offer("Ticket #102 (Bob)");
        customerQueue.offer("Ticket #103 (Charlie)");
        System.out.println("Current Queue: " + customerQueue);

        // peek() - inspect next element without removing
        System.out.println("Next to serve (peek): " + customerQueue.peek());

        // poll() - retrieves and removes head of queue
        System.out.println("Serving customer: " + customerQueue.poll());
        System.out.println("Serving customer: " + customerQueue.poll());
        System.out.println("Remaining in Queue: " + customerQueue);


        System.out.println("\n=== 2. LINKEDLIST AS A LIFO STACK (LAST-IN, FIRST-OUT) ===");
        // Deque (Double Ended Queue) is the modern, recommended stack in Java
        Deque<String> browserHistory = new LinkedList<>();

        // push() - adds to the top of stack
        browserHistory.push("https://google.com");
        browserHistory.push("https://github.com");
        browserHistory.push("https://antigravity.dev");
        System.out.println("Browser History Stack (Top to Bottom): " + browserHistory);

        // peek() - current top page
        System.out.println("Current active page: " + browserHistory.peek());

        // pop() - navigate back (removes top page)
        System.out.println("Clicking BACK button -> Popped: " + browserHistory.pop());
        System.out.println("Now viewing: " + browserHistory.peek());


        System.out.println("\n=== 3. DOUBLE-ENDED DEQUE OPERATIONS ===");
        LinkedList<Integer> deque = new LinkedList<>();
        deque.addFirst(10); // [10]
        deque.addLast(20);  // [10, 20]
        deque.addFirst(5);  // [5, 10, 20]
        deque.addLast(30);  // [5, 10, 20, 30]

        System.out.println("Deque Elements: " + deque);
        System.out.println("First element: " + deque.getFirst());
        System.out.println("Last element : " + deque.getLast());

        deque.removeFirst(); // removes 5
        deque.removeLast();  // removes 30
        System.out.println("After removing both ends: " + deque);

        // ============================================================
        // KEY TAKEAWAYS:
        // ============================================================
        // - Use `ArrayList` by default for 90% of list use-cases (faster reads, lower memory).
        // - Use `LinkedList` when building Queues, Stacks, or when doing frequent head/tail inserts.
        // ============================================================
    }
}
