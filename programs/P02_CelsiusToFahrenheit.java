package programs;

/**
 * ============================================================
 * PROGRAM 02: Celsius to Fahrenheit & Vice Versa Converter
 * ============================================================
 * Problem: WAP to convert temperature:
 *   - From Celsius to Fahrenheit: F = (C * 9/5) + 32
 *   - From Fahrenheit to Celsius: C = (F - 32) * 5/9
 * ============================================================
 */
public class P02_CelsiusToFahrenheit {

    public static double celsiusToFahrenheit(double celsius) {
        return (celsius * 9.0 / 5.0) + 32.0;
    }

    public static double fahrenheitToCelsius(double fahrenheit) {
        return (fahrenheit - 32.0) * (5.0 / 9.0);
    }

    public static void main(String[] args) {
        double[] testCelsius = {0.0, 25.0, 37.0, 100.0, -40.0};

        System.out.println("=== CELSIUS TO FAHRENHEIT ===");
        for (double c : testCelsius) {
            double f = celsiusToFahrenheit(c);
            System.out.printf("  %6.1f °C  =  %6.1f °F%n", c, f);
        }

        System.out.println("\n=== FAHRENHEIT TO CELSIUS (REVERSE) ===");
        double[] testFahrenheit = {32.0, 77.0, 98.6, 212.0, -40.0};
        for (double f : testFahrenheit) {
            double c = fahrenheitToCelsius(f);
            System.out.printf("  %6.1f °F  =  %6.1f °C%n", f, c);
        }
    }
}
