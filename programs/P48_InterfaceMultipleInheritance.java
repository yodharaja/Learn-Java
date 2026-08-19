package programs;

/**
 * ============================================================
 * PROGRAM 48: Multiple Inheritance with Interfaces
 * ============================================================
 * Problem: WAP to achieve multiple inheritance in Java
 * by implementing multiple interfaces (`Printable` and `Scannable`)
 * in a `MultifunctionPrinter` class.
 * ============================================================
 */

interface Printable {
    void printDocument(String document);
}

interface Scannable {
    void scanDocument(String document);
}

class MultifunctionPrinter implements Printable, Scannable {
    private String model;

    public MultifunctionPrinter(String model) {
        this.model = model;
    }

    @Override
    public void printDocument(String document) {
        System.out.println("  🖨️ [" + model + "] Printing: " + document);
    }

    @Override
    public void scanDocument(String document) {
        System.out.println("  📄 [" + model + "] Scanning: " + document);
    }
}

public class P48_InterfaceMultipleInheritance {

    public static void main(String[] args) {
        MultifunctionPrinter mfp = new MultifunctionPrinter("HP LaserJet Pro 4000");
        mfp.printDocument("Annual Financial Report.pdf");
        mfp.scanDocument("Signed_Contract.png");
    }
}
