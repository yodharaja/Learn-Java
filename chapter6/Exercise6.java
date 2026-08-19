package chapter6;

import java.util.Scanner;

/**
 * ============================================================
 * EXERCISE 6 — Shape & Canvas Graphic Engine 🎨
 * ============================================================
 *
 * Build an object-oriented vector drawing and geometry engine featuring:
 *   1. Interface `Drawable` with `draw()` and default `renderBorder()`.
 *   2. Interface `Scalable` with `scale(double factor)`.
 *   3. Abstract class `Shape2D` implementing `Drawable` and `Scalable` with:
 *      - color, filled status, center coordinates (x, y)
 *      - abstract `calculateArea()` and `calculatePerimeter()`
 *   4. Concrete shape subclasses:
 *      - `Circle`
 *      - `Rectangle`
 *      - `Triangle` (with Heron's formula for area)
 *   5. Class `Canvas` that holds polymorphic arrays of `Shape2D`, calculates
 *      total stats, filters shapes, and renders them.
 * ============================================================
 */

interface Drawable {
    void draw();

    default void renderBorder(String borderStyle) {
        System.out.println("  [Border Engine] Applying '" + borderStyle + "' border effect.");
    }
}

interface Scalable {
    void scale(double factor);
}

abstract class Shape2D implements Drawable, Scalable {
    protected String name;
    protected String color;
    protected boolean filled;
    protected double x;
    protected double y;

    public Shape2D(String name, String color, boolean filled, double x, double y) {
        this.name = name;
        this.color = color;
        this.filled = filled;
        this.x = x;
        this.y = y;
    }

    public abstract double calculateArea();
    public abstract double calculatePerimeter();

    public void move(double dx, double dy) {
        this.x += dx;
        this.y += dy;
        System.out.printf("  Moved %s to position (%.1f, %.1f)%n", name, this.x, this.y);
    }

    public String getName() { return name; }
    public String getColor() { return color; }
    public double getX() { return x; }
    public double getY() { return y; }

    @Override
    public String toString() {
        return String.format("%s [Color=%s, Filled=%s, Pos=(%.1f, %.1f), Area=%.2f, Perim=%.2f]",
                name, color, filled, x, y, calculateArea(), calculatePerimeter());
    }
}

class Circle extends Shape2D {
    private double radius;

    public Circle(String color, boolean filled, double x, double y, double radius) {
        super("Circle", color, filled, x, y);
        this.radius = radius;
    }

    public double getRadius() { return radius; }

    @Override
    public double calculateArea() {
        return Math.PI * radius * radius;
    }

    @Override
    public double calculatePerimeter() {
        return 2 * Math.PI * radius;
    }

    @Override
    public void scale(double factor) {
        if (factor > 0) {
            this.radius *= factor;
            System.out.printf("  Scaled Circle radius to %.2f (factor %.2fx)%n", radius, factor);
        }
    }

    @Override
    public void draw() {
        System.out.printf("  🎨 Drawing %s Circle at (%.1f, %.1f) with radius %.2f ⭕%n",
                color, x, y, radius);
    }
}

class Rectangle extends Shape2D {
    private double width;
    private double height;

    public Rectangle(String color, boolean filled, double x, double y, double width, double height) {
        super("Rectangle", color, filled, x, y);
        this.width = width;
        this.height = height;
    }

    public double getWidth() { return width; }
    public double getHeight() { return height; }

    @Override
    public double calculateArea() {
        return width * height;
    }

    @Override
    public double calculatePerimeter() {
        return 2 * (width + height);
    }

    @Override
    public void scale(double factor) {
        if (factor > 0) {
            this.width *= factor;
            this.height *= factor;
            System.out.printf("  Scaled Rectangle to %.2fx%.2f (factor %.2fx)%n", width, height, factor);
        }
    }

    @Override
    public void draw() {
        System.out.printf("  🎨 Drawing %s Rectangle at (%.1f, %.1f) of size %.2fx%.2f ▭%n",
                color, x, y, width, height);
    }
}

class Triangle extends Shape2D {
    private double sideA, sideB, sideC;

    public Triangle(String color, boolean filled, double x, double y, double a, double b, double c) {
        super("Triangle", color, filled, x, y);
        this.sideA = a;
        this.sideB = b;
        this.sideC = c;
    }

    @Override
    public double calculatePerimeter() {
        return sideA + sideB + sideC;
    }

    @Override
    public double calculateArea() {
        // Heron's formula
        double s = calculatePerimeter() / 2.0;
        return Math.sqrt(Math.max(0, s * (s - sideA) * (s - sideB) * (s - sideC)));
    }

    @Override
    public void scale(double factor) {
        if (factor > 0) {
            this.sideA *= factor;
            this.sideB *= factor;
            this.sideC *= factor;
            System.out.printf("  Scaled Triangle sides to (%.1f, %.1f, %.1f)%n", sideA, sideB, sideC);
        }
    }

    @Override
    public void draw() {
        System.out.printf("  🎨 Drawing %s Triangle at (%.1f, %.1f) with sides (%.1f, %.1f, %.1f) △%n",
                color, x, y, sideA, sideB, sideC);
    }
}

class GraphicCanvas {
    private Shape2D[] shapes;
    private int count;

    public GraphicCanvas(int capacity) {
        this.shapes = new Shape2D[capacity];
        this.count = 0;
    }

    public void addShape(Shape2D shape) {
        if (count < shapes.length) {
            shapes[count++] = shape;
            System.out.println("  ✓ Added to Canvas: " + shape.getName());
        } else {
            System.out.println("  ❌ Canvas is full!");
        }
    }

    public void renderAll() {
        System.out.println("\n╔══════════════════════════════════════════════════╗");
        System.out.println("║            🖌️ RENDERING GRAPHIC CANVAS           ║");
        System.out.println("╚══════════════════════════════════════════════════╝");
        for (int i = 0; i < count; i++) {
            shapes[i].draw();
            shapes[i].renderBorder("Solid Shadow");
        }
    }

    public void printCanvasReport() {
        double totalArea = 0;
        double totalPerimeter = 0;

        System.out.println("\n📊 CANVAS STATISTICAL SUMMARY:");
        for (int i = 0; i < count; i++) {
            Shape2D s = shapes[i];
            System.out.println("  " + (i + 1) + ". " + s);
            totalArea += s.calculateArea();
            totalPerimeter += s.calculatePerimeter();
        }

        System.out.printf("%n  Total Shape Count     : %d%n", count);
        System.out.printf("  Combined Total Area   : %.2f sq units%n", totalArea);
        System.out.printf("  Combined Perimeter    : %.2f units%n", totalPerimeter);
    }

    public void scaleAll(double factor) {
        System.out.printf("%n🔍 Scaling all canvas shapes by %.2fx:%n", factor);
        for (int i = 0; i < count; i++) {
            shapes[i].scale(factor);
        }
    }
}

public class Exercise6 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("╔══════════════════════════════════════════════════╗");
        System.out.println("║      🎨 VECTOR GRAPHICS & SHAPE ENGINE           ║");
        System.out.println("╚══════════════════════════════════════════════════╝");

        GraphicCanvas canvas = new GraphicCanvas(5);

        // Add diverse shapes polymorphically
        canvas.addShape(new Circle("Vibrant Blue", true, 10, 20, 5.0));
        canvas.addShape(new Rectangle("Emerald Green", true, 0, 0, 12.0, 6.0));
        canvas.addShape(new Triangle("Crimson Red", false, 30, 40, 3.0, 4.0, 5.0));
        canvas.addShape(new Circle("Sunflower Yellow", true, 50, 50, 8.5));

        // Render Canvas
        canvas.renderAll();

        // Print full polymorphic calculations report
        canvas.printCanvasReport();

        // Scale everything up by 1.5x
        canvas.scaleAll(1.5);

        // Print updated calculations after scale
        canvas.printCanvasReport();

        sc.close();
    }
}
