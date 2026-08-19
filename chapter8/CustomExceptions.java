package chapter8;

/**
 * ============================================================
 * LESSON 8.3 — Custom Application Exceptions
 * ============================================================
 *
 * Why build Custom Exceptions?
 *   1. Domain-specific naming (`InsufficientFundsException` vs generic `Exception`).
 *   2. Carry custom diagnostic metadata (e.g. account ID, error codes, failed timestamp).
 *   3. Cleaner error triage and differentiated catch handling in enterprise apps.
 *
 * How to create them:
 *   - Custom Checked: Extend `java.lang.Exception`.
 *   - Custom Unchecked: Extend `java.lang.RuntimeException`.
 */

// 1. Custom Checked Exception: OrderNotFoundException
class OrderNotFoundException extends Exception {
    private String orderId;

    public OrderNotFoundException(String orderId) {
        super("Order ID '" + orderId + "' was not found in the order database.");
        this.orderId = orderId;
    }

    public String getOrderId() {
        return orderId;
    }
}

// 2. Custom Unchecked Business Exception: PaymentFailedException
class PaymentFailedException extends RuntimeException {
    private String errorCode;
    private double attemptedAmount;

    public PaymentFailedException(String message, String errorCode, double attemptedAmount) {
        super(message);
        this.errorCode = errorCode;
        this.attemptedAmount = attemptedAmount;
    }

    public String getErrorCode() { return errorCode; }
    public double getAttemptedAmount() { return attemptedAmount; }

    @Override
    public String toString() {
        return String.format("PaymentFailedException[Code=%s, Amount=$%.2f, Message='%s']",
                errorCode, attemptedAmount, getMessage());
    }
}

// Business service demonstrating custom exceptions
class ECommerceService {

    public static void fulfillOrder(String orderId, double amount, double accountBalance)
            throws OrderNotFoundException {

        if (orderId == null || !orderId.startsWith("ORD-")) {
            throw new OrderNotFoundException(orderId);
        }

        if (amount > accountBalance) {
            throw new PaymentFailedException(
                    "Insufficient card balance to complete checkout.",
                    "ERR_INSUFFICIENT_FUNDS",
                    amount
            );
        }

        System.out.printf("  ✓ Order %s for $%.2f fulfilled successfully!%n", orderId, amount);
    }
}

public class CustomExceptions {

    public static void main(String[] args) {
        System.out.println("=== 1. SUCCESSFUL ORDER FLOW ===");
        try {
            ECommerceService.fulfillOrder("ORD-1001", 150.0, 500.0);
        } catch (OrderNotFoundException | PaymentFailedException e) {
            System.out.println("  ❌ Failed: " + e.getMessage());
        }

        System.out.println("\n=== 2. TRIGGERING CUSTOM CHECKED EXCEPTION ===");
        try {
            ECommerceService.fulfillOrder("INVALID_ID_99", 50.0, 500.0);
        } catch (OrderNotFoundException e) {
            System.out.println("  ❌ Caught Checked Exception: " + e.getMessage());
            System.out.println("     Missing Order ID: " + e.getOrderId());
        }

        System.out.println("\n=== 3. TRIGGERING CUSTOM UNCHECKED EXCEPTION ===");
        try {
            ECommerceService.fulfillOrder("ORD-2002", 999.0, 100.0);
        } catch (PaymentFailedException e) {
            System.out.println("  ❌ Caught Custom Payment Exception: " + e);
            System.out.println("     Error Code: " + e.getErrorCode());
            System.out.printf("     Attempted: $%.2f%n", e.getAttemptedAmount());
        } catch (OrderNotFoundException e) {
            System.out.println("  ❌ Order error: " + e.getMessage());
        }

        // ============================================================
        // KEY TAKEAWAYS:
        // ============================================================
        // - Extend `Exception` for checked custom exceptions (forcing callers to handle).
        // - Extend `RuntimeException` for unchecked custom business logic errors.
        // - Include specific error codes and metadata to simplify logging and user feedback.
        // ============================================================
    }
}
