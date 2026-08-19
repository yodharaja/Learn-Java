# Chapter 10 — File I/O & Serialization

## Quick Reference Cheat Sheet

---

## 1. Traditional vs Modern Java NIO

| Task | Traditional (`java.io`) | Modern Java NIO (`java.nio.file`) |
|---|---|---|
| Read full file to string | `BufferedReader` loop | `Files.readString(path)` (Java 11+) |
| Write string to file | `BufferedWriter.write()` | `Files.writeString(path, text)` |
| Stream lines lazily | Custom buffer | `Files.lines(path)` |
| Check file exists | `file.exists()` | `Files.exists(path)` |
| Create directories | `dir.mkdirs()` | `Files.createDirectories(path)` |

---

## 2. Character Streams vs Byte Streams

- **Character Streams (`Reader` / `Writer`)**: Text files (Unicode `.txt`, `.csv`, `.json`).
- **Byte Streams (`InputStream` / `OutputStream`)**: Binary files (`.jpg`, `.mp4`, `.pdf`, `.ser`).
- **Buffer wrapping**: Always wrap raw file streams inside `BufferedReader` / `BufferedWriter` or `BufferedInputStream` / `BufferedOutputStream`.

---

## 3. Object Serialization (`Serializable`)

Converts in-memory Java objects to bytes for disk or network transfer.

```java
public class User implements Serializable {
    private static final long serialVersionUID = 1L;

    private String username;
    private transient String password; // Will NOT be saved to disk
}

// Saving:
try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("user.ser"))) {
    oos.writeObject(user);
}

// Loading:
try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("user.ser"))) {
    User u = (User) ois.readObject();
}
```

---

## 4. Useful File System Checks

```java
Path path = Paths.get("data/report.csv");

boolean exists = Files.exists(path);
long size = Files.size(path);
boolean isDir = Files.isDirectory(path);
Files.deleteIfExists(path);
```
