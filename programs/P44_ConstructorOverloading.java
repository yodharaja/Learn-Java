package programs;

/**
 * ============================================================
 * PROGRAM 44: Constructor Overloading and Chaining (this)
 * ============================================================
 * Problem: WAP to demonstrate Constructor Overloading and
 * Constructor Chaining using `this(...)`.
 * ============================================================
 */

class Laptop {
    String brand;
    int ramGb;
    double price;

    // No-arg constructor
    public Laptop() {
        this("Generic", 8, 499.99); // chains to 3-arg constructor
    }

    // 2-arg constructor
    public Laptop(String brand, int ramGb) {
        this(brand, ramGb, 799.99);
    }

    // 3-arg constructor
    public Laptop(String brand, int ramGb, double price) {
        this.brand = brand;
        this.ramGb = ramGb;
        this.price = price;
    }

    public void printSpecs() {
        System.out.printf("  Laptop Specs: %s | %d GB RAM | $%,.2f%n", brand, ramGb, price);
    }
}

public class P44_ConstructorOverloading {

    public static void main(String[] args) {
        Laptop l1 = new Laptop();
        Laptop l2 = new Laptop("Dell XPS", 16);
        Laptop l3 = new Laptop("Apple MacBook Pro", 32, 2499.00);

        l1.printSpecs();
        l2.printSpecs();
        l3.printSpecs();
    }
}
