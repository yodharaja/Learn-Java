package programs;

/**
 * ============================================================
 * PROGRAM 45: Single and Multilevel Inheritance with 'super'
 * ============================================================
 * Problem: WAP to demonstrate Single and Multilevel Inheritance
 * with constructor propagation using `super(...)`.
 * ============================================================
 */

// Level 1: Base Class
class DeviceBase {
    protected String brand;

    public DeviceBase(String brand) {
        this.brand = brand;
    }
}

// Level 2: Single Inheritance
class Computer extends DeviceBase {
    protected int ramGb;

    public Computer(String brand, int ramGb) {
        super(brand);
        this.ramGb = ramGb;
    }
}

// Level 3: Multilevel Inheritance
class SmartLaptop extends Computer {
    private boolean touchScreen;

    public SmartLaptop(String brand, int ramGb, boolean touchScreen) {
        super(brand, ramGb);
        this.touchScreen = touchScreen;
    }

    public void showDetails() {
        System.out.printf("  SmartLaptop[Brand: %s | RAM: %d GB | TouchScreen: %s]%n",
                brand, ramGb, touchScreen ? "Yes" : "No");
    }
}

public class P45_SingleAndMultilevelInheritance {

    public static void main(String[] args) {
        SmartLaptop myLaptop = new SmartLaptop("Apple MacBook", 36, false);
        myLaptop.showDetails();
    }
}
