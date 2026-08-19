package chapter6;

/**
 * ============================================================
 * LESSON 6.3 — Abstract Classes & Abstract Methods
 * ============================================================
 *
 * An ABSTRACT CLASS is a restricted class that CANNOT be instantiated
 * directly (you cannot do `new AbstractClass()`).
 *
 * Why use Abstract Classes?
 *   - Serves as a base blueprint/template for a group of related subclasses.
 *   - Enforces common method contracts while allowing subclasses to implement details.
 *
 * Key Rules:
 *   1. Declared with the 'abstract' keyword.
 *   2. Abstract methods have NO body (`abstract void draw();`), ending with a semicolon.
 *   3. If a class contains ANY abstract method, the class MUST be declared abstract.
 *   4. Subclasses MUST override all abstract methods, unless the subclass itself is also abstract.
 *   5. Abstract classes CAN have:
 *      - Regular (concrete) methods with full implementation
 *      - Instance variables & constants
 *      - Constructors (called via `super()` by subclasses)
 */

abstract class GraphicShape {
    protected String color;
    protected boolean filled;

    // Abstract class constructor
    public GraphicShape(String color, boolean filled) {
        this.color = color;
        this.filled = filled;
    }

    // --- ABSTRACT METHODS (Must be implemented by subclasses) ---
    public abstract double calculateArea();
    public abstract double calculatePerimeter();
    public abstract void draw();

    // --- CONCRETE METHODS (Shared implementation for all shapes) ---
    public void printDescription() {
        System.out.printf("  Shape[Color=%s, Filled=%s, Area=%.2f, Perimeter=%.2f]%n",
                color, filled ? "Yes" : "No", calculateArea(), calculatePerimeter());
    }

    public String getColor() { return color; }
    public void setColor(String color) { this.color = color; }
}

class CircleShape extends GraphicShape {
    private double radius;

    public CircleShape(String color, boolean filled, double radius) {
        super(color, filled);
        this.radius = radius;
    }

    @Override
    public double calculateArea() {
        return Math.PI * radius * radius;
    }

    @Override
    public double calculatePerimeter() {
        return 2 * Math.PI * radius;
    }

    @Override
    public void draw() {
        System.out.printf("  Drawing a %s circle with radius %.2f ⭕%n", color, radius);
    }
}

class RectangleShape extends GraphicShape {
    private double width;
    private double height;

    public RectangleShape(String color, boolean filled, double width, double height) {
        super(color, filled);
        this.width = width;
        this.height = height;
    }

    @Override
    public double calculateArea() {
        return width * height;
    }

    @Override
    public double calculatePerimeter() {
        return 2 * (width + height);
    }

    @Override
    public void draw() {
        System.out.printf("  Drawing a %s rectangle (%.2f x %.2f) ▭%n", color, width, height);
    }
}

public class AbstractClasses {

    public static void main(String[] args) {
        System.out.println("=== 1. USING ABSTRACT CLASS SUBCLASSES ===");
        // GraphicShape shape = new GraphicShape("Red", true); // COMPILATION ERROR: cannot instantiate abstract class!

        GraphicShape circle = new CircleShape("Blue", true, 5.0);
        GraphicShape rect = new RectangleShape("Green", false, 4.0, 6.0);

        circle.draw();
        circle.printDescription();

        System.out.println();
        rect.draw();
        rect.printDescription();

        System.out.println("\n=== 2. POLYMORPHIC PROCESSING OF ABSTRACT OBJECTS ===");
        GraphicShape[] canvas = {
            new CircleShape("Yellow", true, 3.5),
            new RectangleShape("Orange", true, 8.0, 2.5),
            new CircleShape("Purple", false, 1.2)
        };

        double totalArea = 0;
        for (GraphicShape s : canvas) {
            s.draw();
            totalArea += s.calculateArea();
        }
        System.out.printf("%nTotal combined canvas area: %.2f sq units%n", totalArea);

        // ============================================================
        // KEY TAKEAWAYS:
        // ============================================================
        // - Use abstract classes when classes share common code + state, but need customized core behaviors.
        // - Abstract methods have no body and force child classes to provide implementation.
        // - An abstract class can have constructors, instance fields, and concrete methods.
        // ============================================================
    }
}
