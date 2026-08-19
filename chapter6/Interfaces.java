package chapter6;

/**
 * ============================================================
 * LESSON 6.4 — Interfaces in Java
 * ============================================================
 *
 * An INTERFACE is a completely abstract type used to specify a contract
 * of what a class should do, without specifying how.
 *
 * Key Properties of Interfaces:
 *   1. Declared with 'interface' keyword.
 *   2. Implemented with 'implements' keyword (`class Drone implements Flyable, Rechargeable`).
 *   3. All fields in an interface are implicitly `public static final` (constants).
 *   4. Methods are implicitly `public abstract` (prior to Java 8).
 *   5. Java 8+ additions:
 *      - `default` methods: concrete methods with a default implementation in interface.
 *      - `static` methods: utility methods within the interface namespace.
 *   6. Java 9+ addition: `private` helper methods within the interface.
 *   7. A class can implement MULTIPLE interfaces (resolves multiple inheritance limitation!).
 *
 * Abstract Class vs Interface:
 * ┌──────────────────────┬────────────────────────────────┬───────────────────────────────┐
 * │ Feature              │ Abstract Class                 │ Interface                     │
 * ├──────────────────────┼────────────────────────────────┼───────────────────────────────┤
 * │ Inheritance          │ Single (`extends OneClass`)    │ Multiple (`implements A, B`)  │
 * │ State (Variables)    │ Can have instance variables    │ Constants only (public static)│
 * │ Constructors         │ Can have constructors          │ Cannot have constructors      │
 * │ Speed                │ Slightly faster                │ Requires interface lookup     │
 * │ Design Concept       │ "IS-A" identity relationship   │ "CAN-DO" capability contract  │
 * └──────────────────────┴────────────────────────────────┴───────────────────────────────┘
 */

// Interface 1: Flyable capability
interface Flyable {
    // Constant (implicitly public static final)
    int MAX_ALTITUDE_METERS = 10000;

    // Abstract method (implicitly public abstract)
    void takeOff();
    void fly(int altitude);
    void land();

    // Default method (Java 8+): provides optional default implementation
    default void emergencyLanding() {
        System.out.println("  ⚠️ [Flyable] Performing emergency landing protocol!");
        land();
    }
}

// Interface 2: Rechargeable capability
interface Rechargeable {
    void recharge(int minutes);
    int getBatteryPercentage();

    // Static utility method in interface (Java 8+)
    static void inspectBatterySafety(int batteryLevel) {
        if (batteryLevel < 10) {
            System.out.println("  ⚠️ [Battery Utility] Warning: Battery critically low (<10%)!");
        } else {
            System.out.println("  ✓ [Battery Utility] Battery level is nominal.");
        }
    }
}

// Class implementing MULTIPLE interfaces
class ElectricDrone implements Flyable, Rechargeable {
    private String modelName;
    private int battery;
    private boolean flying;

    public ElectricDrone(String modelName) {
        this.modelName = modelName;
        this.battery = 100;
        this.flying = false;
    }

    // Implementing Flyable methods
    @Override
    public void takeOff() {
        if (battery > 10) {
            flying = true;
            battery -= 5;
            System.out.printf("  🛸 Drone '%s' took off smoothly. (Battery: %d%%)%n", modelName, battery);
        } else {
            System.out.println("  ❌ Cannot take off: Low battery.");
        }
    }

    @Override
    public void fly(int altitude) {
        if (!flying) {
            System.out.println("  ❌ Drone must take off first.");
            return;
        }
        if (altitude > MAX_ALTITUDE_METERS) {
            System.out.printf("  ⚠️ Altitude %dm exceeds max allowed (%dm). Capping.%n",
                    altitude, MAX_ALTITUDE_METERS);
            altitude = MAX_ALTITUDE_METERS;
        }
        battery -= 15;
        System.out.printf("  🛸 Drone '%s' cruising at %d meters altitude.%n", modelName, altitude);
    }

    @Override
    public void land() {
        flying = false;
        System.out.printf("  🛬 Drone '%s' has landed safely.%n", modelName);
    }

    // Implementing Rechargeable methods
    @Override
    public void recharge(int minutes) {
        int added = minutes * 2;
        battery = Math.min(100, battery + added);
        System.out.printf("  ⚡ Drone '%s' recharged for %d mins. Battery: %d%%%n",
                modelName, minutes, battery);
    }

    @Override
    public int getBatteryPercentage() {
        return this.battery;
    }
}

public class Interfaces {

    public static void main(String[] args) {
        System.out.println("=== 1. MULTIPLE INTERFACE IMPLEMENTATION ===");
        ElectricDrone drone = new ElectricDrone("SkyFalcon-4K");

        // Use interface static method
        Rechargeable.inspectBatterySafety(drone.getBatteryPercentage());

        // Use Flyable capability
        drone.takeOff();
        drone.fly(1500);

        // Use default method inherited from Flyable
        drone.emergencyLanding();

        // Use Rechargeable capability
        drone.recharge(30);
        System.out.println("Battery after charging: " + drone.getBatteryPercentage() + "%");

        System.out.println("\n=== 2. POLYMORPHISM VIA INTERFACES ===");
        // You can use the interface type as a reference!
        Flyable flyingObject = drone;
        flyingObject.takeOff();
        flyingObject.land();

        Rechargeable energyDevice = drone;
        System.out.println("Energy device battery: " + energyDevice.getBatteryPercentage() + "%");

        // ============================================================
        // KEY TAKEAWAYS:
        // ============================================================
        // - Interfaces define 'what' behaviors a class must support.
        // - A class can implement multiple interfaces (`implements A, B, C`).
        // - Default methods (`default`) allow adding new methods without breaking existing classes.
        // - Static methods in interfaces act as scoped utility helpers.
        // ============================================================
    }
}
