package programs;

/**
 * ============================================================
 * PROGRAM 04: Area and Perimeter of Geometric Shapes
 * ============================================================
 * Problem: WAP to compute Area & Perimeter/Circumference of:
 *   1. Circle: Area = π * r^2, Perimeter = 2 * π * r
 *   2. Rectangle: Area = l * w, Perimeter = 2 * (l + w)
 *   3. Triangle: Area = 0.5 * b * h
 *   4. Sphere Volume: (4/3) * π * r^3
 * ============================================================
 */
public class P04_AreaPerimeterAllShapes {

    public static void main(String[] args) {
        // Circle (r = 7.0)
        double r = 7.0;
        double circleArea = Math.PI * r * r;
        double circlePerim = 2 * Math.PI * r;
        System.out.printf("Circle (r=%.1f)       -> Area: %6.2f | Circumference: %6.2f%n", r, circleArea, circlePerim);

        // Rectangle (length = 12.0, width = 5.0)
        double l = 12.0, w = 5.0;
        double rectArea = l * w;
        double rectPerim = 2 * (l + w);
        System.out.printf("Rectangle (%.1fx%.1f)   -> Area: %6.2f | Perimeter    : %6.2f%n", l, w, rectArea, rectPerim);

        // Triangle (base = 8.0, height = 6.0)
        double b = 8.0, h = 6.0;
        double triArea = 0.5 * b * h;
        System.out.printf("Triangle (b=%.1f, h=%.1f) -> Area: %6.2f%n", b, h, triArea);

        // Sphere (r = 5.0)
        double sphereRadius = 5.0;
        double sphereVol = (4.0 / 3.0) * Math.PI * Math.pow(sphereRadius, 3);
        System.out.printf("Sphere (r=%.1f)       -> Volume: %6.2f%n", sphereRadius, sphereVol);
    }
}
