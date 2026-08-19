package chapter6;

/**
 * ============================================================
 * LESSON 6.5 — Polymorphism, Type Casting & Pattern Matching
 * ============================================================
 *
 * POLYMORPHISM (Greek for "many forms") is the ability of a single
 * interface or reference to represent different underlying forms (data types).
 *
 * Two main forms:
 *   1. Compile-time Polymorphism: Method Overloading (Chapter 4.2).
 *   2. Runtime Polymorphism: Method Overriding & Dynamic Dispatch (Chapter 6.2).
 *
 * Object Type Casting:
 *   - UPCASTING: Subclass -> Superclass (Automatic & Safe).
 *     `Animal a = new Dog();`
 *   - DOWNCASTING: Superclass -> Subclass (Manual & Risky).
 *     `Dog d = (Dog) a;`
 *     Throws `ClassCastException` at runtime if the object is NOT actually a Dog!
 *
 * 'instanceof' Operator:
 *   - Checks if an object is an instance of a specific class or interface before downcasting.
 *   - Pattern Matching for instanceof (Java 16+):
 *     `if (obj instanceof Dog d) { d.fetch(); }` eliminates explicit casting boilerplate!
 */

class Device {
    protected String serialNo;

    public Device(String serialNo) {
        this.serialNo = serialNo;
    }

    public void powerOn() {
        System.out.println("  [Device] Powering on generic device " + serialNo);
    }
}

class SmartPhone extends Device {
    private String operatingSystem;

    public SmartPhone(String serialNo, String os) {
        super(serialNo);
        this.operatingSystem = os;
    }

    @Override
    public void powerOn() {
        System.out.println("  📱 Smartphone (" + operatingSystem + ") booting up with lock screen.");
    }

    public void makeCall(String number) {
        System.out.println("  📞 Dialing " + number + " from " + serialNo);
    }
}

class SmartWatch extends Device {
    private int heartRateSensorBpm;

    public SmartWatch(String serialNo) {
        super(serialNo);
        this.heartRateSensorBpm = 72;
    }

    @Override
    public void powerOn() {
        System.out.println("  ⌚ Smartwatch screen illuminates with heart rate widget.");
    }

    public void trackHeartRate() {
        System.out.println("  ❤️ Current pulse: " + heartRateSensorBpm + " BPM");
    }
}

public class Polymorphism {

    public static void main(String[] args) {
        System.out.println("=== 1. UPCASTING (Implicit & Safe) ===");
        // Upcasting: Storing specific child objects into general parent references
        Device dev1 = new SmartPhone("SN-IPHONE-15", "iOS 18");
        Device dev2 = new SmartWatch("SN-GALAXY-WATCH-6");

        // Calls overridden methods dynamically:
        dev1.powerOn();
        dev2.powerOn();

        // dev1.makeCall("555-0199"); // COMPILATION ERROR: Device class doesn't know about makeCall()!

        System.out.println("\n=== 2. DOWNCASTING WITH CLASSIC INSTANCEOF ===");
        Device[] inventory = { dev1, dev2, new Device("SN-OLD-ROUTER") };

        for (Device item : inventory) {
            item.powerOn();

            // Classic instanceof check + manual casting
            if (item instanceof SmartPhone) {
                SmartPhone phone = (SmartPhone) item; // Downcast
                phone.makeCall("555-1234");
            } else if (item instanceof SmartWatch) {
                SmartWatch watch = (SmartWatch) item; // Downcast
                watch.trackHeartRate();
            } else {
                System.out.println("  Generic device (no special actions).");
            }
            System.out.println();
        }

        System.out.println("=== 3. PATTERN MATCHING FOR INSTANCEOF (Java 16+) ===");
        // Cleaner syntax: combines type check AND cast assignment in one step!
        for (Device item : inventory) {
            if (item instanceof SmartPhone phone) {
                System.out.print("  [Pattern Match] Detected Phone -> ");
                phone.makeCall("911");
            } else if (item instanceof SmartWatch watch) {
                System.out.print("  [Pattern Match] Detected Watch -> ");
                watch.trackHeartRate();
            }
        }

        // ============================================================
        // KEY TAKEAWAYS:
        // ============================================================
        // - Upcasting (Child -> Parent) is automatic, enables polymorphism.
        // - Downcasting (Parent -> Child) requires manual cast `(Type)` and can throw ClassCastException.
        // - Always guard downcasting using `instanceof` or pattern matching for instanceof.
        // ============================================================
    }
}
