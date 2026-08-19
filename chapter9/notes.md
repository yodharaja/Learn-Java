# Chapter 9 — The Java Collections Framework

## Quick Reference Cheat Sheet

---

## 1. Collections Hierarchy Overview

```
                   Collection<E> (Interface)
                 /             |            \
            List<E>          Set<E>        Queue<E>
           /       \        /      \          |
    ArrayList  LinkedList HashSet TreeSet LinkedList (Deque)

                   Map<K, V> (Separate Hierarchy!)
                  /         \
             HashMap       TreeMap
```

---

## 2. Choosing the Right Data Structure

| Goal | Data Structure | Why? |
|---|---|---|
| Fast indexed access by position ($O(1)$) | `ArrayList<E>` | Dynamic contiguous array |
| Queue / Stack / Frequent insert at ends | `LinkedList<E>` | Doubly-linked list node pointers |
| Fast key-value associative lookup ($O(1)$) | `HashMap<K, V>` | Hash table bucket array |
| Key-value with sorted keys ($O(\log n)$) | `TreeMap<K, V>` | Red-Black binary search tree |
| Store unique elements (unordered) | `HashSet<E>` | Backed by HashMap |
| Store unique elements (sorted) | `TreeSet<E>` | Backed by TreeMap |

---

## 3. Core Map Methods (Java 8+)

```java
map.put(key, val);                         // Insert or replace
map.get(key);                              // Get value (or null)
map.getOrDefault(key, defaultVal);         // Safe fallback
map.putIfAbsent(key, val);                 // Only insert if key missing
map.computeIfAbsent(key, k -> new List<>());// Auto-initialize bucket

// Best way to iterate a Map:
for (Map.Entry<String, Double> entry : map.entrySet()) {
    System.out.println(entry.getKey() + " -> " + entry.getValue());
}
```

---

## 4. Mathematical Set Operations

```java
Set<T> union = new HashSet<>(setA);
union.addAll(setB);          // A ∪ B

Set<T> intersection = new HashSet<>(setA);
intersection.retainAll(setB); // A ∩ B

Set<T> difference = new HashSet<>(setA);
difference.removeAll(setB);  // A - B
```

---

## 5. `java.util.Collections` Utility Algorithms

- `Collections.sort(list)`: Sorts in natural ascending order.
- `Collections.reverse(list)`: Inverts element order.
- `Collections.shuffle(list)`: Randomizes order in-place.
- `Collections.min(list)` / `max(list)`: Finds minimum / maximum element.
- `Collections.unmodifiableList(list)`: Creates an immutable, read-only wrapper.
