package chapter5;

/**
 * ============================================================
 * LESSON 5.5 — The 'final' Keyword
 * ============================================================
 *
 * In Java, 'final' means "non-modifiable" or "constant".
 * It can be applied to:
 *
 * 1. FINAL VARIABLES:
 *    - Once assigned a value, it can NEVER be reassigned.
 *    - Often combined with 'static' to create class constants (`public static final`).
 *    - Blank final variables can be initialized once inside a constructor.
 *
 * 2. FINAL METHODS:
 *    - Cannot be overridden by subclasses (locks behavior).
 *
 * 3. FINAL CLASSES:
 *    - Cannot be extended/inherited (e.g. `java.lang.String`, `java.lang.Math`).
 */

// 1. A Final Class (cannot be sub-classed)
final class ImmutableConfig {
    private final String dbUrl;
    private final int port;

    public ImmutableConfig(String dbUrl, int port) {
        this.dbUrl = dbUrl;
        this.port = port;
    }

    public String getDbUrl() { return dbUrl; }
    public int getPort() { return port; }
}

// 2. Class demonstrating final fields and final methods
class PaymentProcessor {
    // Static constant (convention: UPPERCASE_WITH_UNDERSCORES)
    public static final double DEFAULT_TAX_RATE = 0.08;
    public static final String CURRENCY = "USD";

    // Blank final instance variable (MUST be initialized in constructor)
    private final String merchantId;

    public PaymentProcessor(String merchantId) {
        this.merchantId = merchantId;
    }

    public String getMerchantId() {
        return merchantId;
    }

    // Final method: cannot be overridden by any derived payment classes
    public final void verifyTransactionSecurity() {
        System.out.println("  [Security] Verifying SSL handshake and HMAC token...");
    }

    // Regular method: can be overridden
    public void processPayment(double amount) {
        verifyTransactionSecurity();
        double totalWithTax = amount + (amount * DEFAULT_TAX_RATE);
        System.out.printf("  ✓ Processed payment of $%.2f (including tax) for Merchant %s%n",
                totalWithTax, merchantId);
    }
}

public class FinalKeyword {

    public static void main(String[] args) {
        System.out.println("=== 1. FINAL LOCAL VARIABLES & CONSTANTS ===");
        final int MAX_USERS = 100;
        // MAX_USERS = 200; // COMPILATION ERROR: cannot assign a value to final variable

        System.out.println("Max Users Constant: " + MAX_USERS);
        System.out.println("Default Tax Rate  : " + (PaymentProcessor.DEFAULT_TAX_RATE * 100) + "%");
        System.out.println("Standard Currency : " + PaymentProcessor.CURRENCY);

        System.out.println("\n=== 2. FINAL INSTANCE FIELDS ===");
        PaymentProcessor processor = new PaymentProcessor("MERCH-9941");
        System.out.println("Processor Merchant ID: " + processor.getMerchantId());
        processor.processPayment(250.0);

        System.out.println("\n=== 3. FINAL IMMUTABLE CLASS OBJECT ===");
        ImmutableConfig config = new ImmutableConfig("jdbc:postgresql://localhost:5432/appdb", 5432);
        System.out.println("DB URL: " + config.getDbUrl());
        System.out.println("Port  : " + config.getPort());

        // ============================================================
        // KEY TAKEAWAYS:
        // ============================================================
        // - 'final variable': value cannot change once assigned.
        // - 'final method': prevents method overriding in child classes.
        // - 'final class': prevents inheritance completely.
        // - Use 'public static final' for application-wide constants.
        // ============================================================
    }
}
