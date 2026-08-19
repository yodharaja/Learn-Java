package programs;

/**
 * ============================================================
 * PROGRAM 47: Abstract Class Shape Hierarchy
 * ============================================================
 * Problem: WAP to define an abstract class `GeometryShape`
 * with abstract methods `getArea()` and `getPerimeter()`, implemented
 * by `SquareGeometry` and `CircleGeometry`.
 * ============================================================
 */

abstract class GeometryShape {
    protected String color;

    public GeometryShape(String color) {
        this.color = color;
    }

    public abstract double getArea();
    public abstract double getPerimeter();

    public void printStats() {
        System.out.printf("  %s Shape -> Area: %.2f | Perimeter: %.2f%n",
                color, getArea(), getPerimeter());
    }
}

class SquareGeometry extends GeometryShape {
    private double side;

    public SquareGeometry(String color, double side) {
        super(color);
        this.side = side;
    }

    @Override
    public double getArea() { return side * side; }

    @Override
    public double getPerimeter() { return 4 * side; }
}

class CircleGeometry extends GeometryShape {
    private double radius;

    public CircleGeometry(String color, double radius) {
        super(color);
        this.radius = radius;
    }

    @Override
    public double getArea() { return Math.PI * radius * radius; }

    @Override
    public double getPerimeter() { return 2 * Math.PI * radius; }
}

public class P47_AbstractClassShapeHierarchy {

    public static void main(String[] args) {
        GeometryShape s1 = new SquareGeometry("Blue", 5.0);
        GeometryShape s2 = new CircleGeometry("Red", 4.0);

        s1.printStats();
        s2.printStats();
    }
}
