## 1. Program
> `Program 1` - print Hello World on the user’s console.

## 2. Source Code

```java
class Program1 {
    public static void main(String[] args) {
        System.out.println("Hello, Java! its yodha raja");
    }
}
```

## 3. Output

```text
Hello, Java! its yodha raja
```

## 4. Understanding the Complete Program

1. The first line is:
    ```java
    class Program1 {}
    ```
2. This declares a Java class named `Program1`.
3. Inside the class we have:

    ```java
    public static void main(String[] args)
    ```
    this is the entry point used by the Java launcher.
    It contains:

    1. `public` 
        * is an access modifier.
        * It indicates that the method is accessible from outside the class.

    2. `static` 
        * Method belongs to the class rather than requiring an instance of that class for normal invocation.
        * A static method is associated with the class.

    3. `void`
        * void indicates that the `main()` method does not return a value.
    4. `main`
        * main is the conventional name of the application entry-point method.

4. Inside the method we have:

    ```java
    System.out.println("Hello, Java!");
    ```

5. This statement sends text to standard output.
    * `System` is a class provided by the Java platform.It provides various system-related facilities.
    * `out` means we are accessing the standard output stream provided by the Java runtime.
    * `println()` is a method used to print a value followed by a line terminator.
