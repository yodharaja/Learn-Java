package chapter6;

/**
 * ============================================================
 * LESSON 6.1 — Inheritance in Java
 * ============================================================
 *
 * INHERITANCE allows a class (subclass/child) to inherit fields and
 * methods from another class (superclass/parent).
 *
 * Why use Inheritance?
 *   1. Code Reusability: Common code stays in parent class.
 *   2. Method Overriding: Child class can customize inherited behavior.
 *   3. Polymorphism: Treat child objects as parent references.
 *
 * Keywords:
 *   - 'extends': Declares inheritance (`class Dog extends Animal`).
 *   - 'super': Refers to the immediate parent class.
 *     * `super(...)`: Calls the parent class constructor (must be 1st line!).
 *     * `super.method()`: Calls the parent version of an overridden method.
 *     * `super.field`: Accesses a parent class field.
 *
 * Java Inheritance Rules:
 *   - Java supports SINGLE inheritance for classes (a class can only extend ONE class).
 *   - Multiple inheritance is achieved through INTERFACES (Lesson 6.4).
 *   - Constructors are NOT inherited, but child constructors ALWAYS invoke a parent constructor.
 */

// Superclass (Parent)
class Vehicle {
    protected String brand;
    protected String model;
    protected int year;
    protected double currentSpeed;

    public Vehicle(String brand, String model, int year) {
        System.out.println("  [Parent] Vehicle constructor called");
        this.brand = brand;
        this.model = model;
        this.year = year;
        this.currentSpeed = 0;
    }

    public void accelerate(double speedIncrease) {
        this.currentSpeed += speedIncrease;
        System.out.printf("  %s %s accelerated to %.1f km/h%n", brand, model, currentSpeed);
    }

    public void brake(double speedDecrease) {
        this.currentSpeed = Math.max(0, this.currentSpeed - speedDecrease);
        System.out.printf("  %s %s slowed down to %.1f km/h%n", brand, model, currentSpeed);
    }

    public void displayInfo() {
        System.out.printf("  Vehicle: %d %s %s (Current Speed: %.1f km/h)%n",
                year, brand, model, currentSpeed);
    }
}

// Subclass (Child 1): Car
class Car extends Vehicle {
    private int numberOfDoors;
    private boolean convertible;

    public Car(String brand, String model, int year, int numberOfDoors, boolean convertible) {
        // MUST call super constructor as first line:
        super(brand, model, year);
        System.out.println("  [Child] Car constructor called");
        this.numberOfDoors = numberOfDoors;
        this.convertible = convertible;
    }

    public void openTrunk() {
        System.out.printf("  Opening trunk of %s %s...%n", brand, model);
    }

    // Extended displayInfo reusing parent logic with super.displayInfo()
    @Override
    public void displayInfo() {
        super.displayInfo(); // Reusing parent's display logic
        System.out.printf("    -> Details: Doors=%d, Convertible=%s%n",
                numberOfDoors, convertible ? "Yes" : "No");
    }
}

// Subclass (Child 2): ElectricCar (Multi-level Inheritance)
class ElectricCar extends Car {
    private double batteryCapacityKWh;
    private double currentBatteryPercent;

    public ElectricCar(String brand, String model, int year, int doors, double batteryCapacity) {
        super(brand, model, year, doors, false);
        System.out.println("  [Grandchild] ElectricCar constructor called");
        this.batteryCapacityKWh = batteryCapacity;
        this.currentBatteryPercent = 100.0;
    }

    public void chargeBattery(double percent) {
        this.currentBatteryPercent = Math.min(100.0, this.currentBatteryPercent + percent);
        System.out.printf("  ⚡ Charged %s. Battery is now at %.1f%%%n", model, currentBatteryPercent);
    }

    @Override
    public void displayInfo() {
        super.displayInfo();
        System.out.printf("    -> Battery: %.1f kWh (%.1f%% remaining)%n",
                batteryCapacityKWh, currentBatteryPercent);
    }
}

public class Inheritance {

    public static void main(String[] args) {
        System.out.println("=== 1. CREATING A BASE VEHICLE ===");
        Vehicle basic = new Vehicle("Generic", "Utility", 2020);
        basic.displayInfo();
        basic.accelerate(50);

        System.out.println("\n=== 2. CREATING A CAR (INHERITANCE IN ACTION) ===");
        Car sedan = new Car("Toyota", "Camry", 2024, 4, false);
        // Inherited methods from Vehicle:
        sedan.accelerate(80);
        sedan.brake(30);
        // Car-specific method:
        sedan.openTrunk();
        sedan.displayInfo();

        System.out.println("\n=== 3. MULTI-LEVEL INHERITANCE (ElectricCar) ===");
        ElectricCar tesla = new ElectricCar("Tesla", "Model 3", 2025, 4, 75.0);
        tesla.accelerate(100);
        tesla.chargeBattery(15);
        tesla.displayInfo();

        // ============================================================
        // KEY TAKEAWAYS:
        // ============================================================
        // - 'extends' creates an IS-A relationship (ElectricCar IS-A Car IS-A Vehicle).
        // - Subclasses inherit all non-private fields and methods from superclasses.
        // - 'super(...)' calls the parent constructor and MUST be the first statement in child constructor.
        // - 'super.method()' lets a child invoke the parent's version of an overridden method.
        // ============================================================
    }
}
