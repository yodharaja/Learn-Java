package chapter8;

import java.util.Scanner;

/**
 * ============================================================
 * EXERCISE 8 — Secure ATM & Payment Gateway Simulation 🏧
 * ============================================================
 *
 * Build an enterprise-grade ATM simulator that uses robust
 * Exception Handling to model real-world financial failure states:
 *
 * Custom Exception Classes:
 *   1. `InvalidPinException`          -> Wrong PIN entered (after 3 attempts: Account Locked)
 *   2. `InsufficientFundsException`   -> Requested amount exceeds balance (includes deficit info)
 *   3. `AccountFrozenException`       -> Transactions attempted on locked/suspended accounts
 *   4. `DailyLimitExceededException`  -> Exceeding daily withdrawal limit ($1,000)
 *
 * Features:
 *   - AutoCloseable `AtmSession` resource for session initialization & secure logout
 *   - Detailed transaction receipts and graceful recovery loops
 * ============================================================
 */

// Custom Checked Exceptions
class InvalidPinException extends Exception {
    private int remainingAttempts;

    public InvalidPinException(String message, int remainingAttempts) {
        super(message);
        this.remainingAttempts = remainingAttempts;
    }

    public int getRemainingAttempts() { return remainingAttempts; }
}

class InsufficientFundsException extends Exception {
    private double currentBalance;
    private double requestedAmount;

    public InsufficientFundsException(double currentBalance, double requestedAmount) {
        super(String.format("Deficit of $%.2f. Available: $%.2f | Requested: $%.2f",
                (requestedAmount - currentBalance), currentBalance, requestedAmount));
        this.currentBalance = currentBalance;
        this.requestedAmount = requestedAmount;
    }

    public double getDeficit() { return requestedAmount - currentBalance; }
}

class AccountFrozenException extends Exception {
    public AccountFrozenException(String message) {
        super(message);
    }
}

class DailyLimitExceededException extends Exception {
    private double dailyLimit;

    public DailyLimitExceededException(double dailyLimit, double requestedAmount) {
        super(String.format("Request $%.2f exceeds daily max limit of $%.2f", requestedAmount, dailyLimit));
        this.dailyLimit = dailyLimit;
    }
}

// AutoCloseable Session Wrapper
class AtmSession implements AutoCloseable {
    private String cardNumber;
    private boolean authenticated;

    public AtmSession(String cardNumber) {
        this.cardNumber = cardNumber;
        this.authenticated = false;
        System.out.println("  💳 [ATM Hardware] Card inserted: " + cardNumber);
    }

    public void setAuthenticated(boolean auth) { this.authenticated = auth; }
    public boolean isAuthenticated() { return authenticated; }

    @Override
    public void close() {
        System.out.println("  ⏏️ [ATM Hardware] Ejecting card " + cardNumber + "... Session Terminated securely.");
    }
}

// ATM Account Model
class BankCardAccount {
    public static final double DAILY_LIMIT = 1000.00;
    private String cardNumber;
    private String correctPin;
    private double balance;
    private boolean frozen;
    private int failedAttempts;
    private double dailyWithdrawn;

    public BankCardAccount(String cardNumber, String correctPin, double balance) {
        this.cardNumber = cardNumber;
        this.correctPin = correctPin;
        this.balance = balance;
        this.frozen = false;
        this.failedAttempts = 0;
        this.dailyWithdrawn = 0;
    }

    public void authenticate(String pin) throws InvalidPinException, AccountFrozenException {
        if (frozen) {
            throw new AccountFrozenException("Account is LOCKED due to excessive failed attempts or fraud alerts.");
        }

        if (!this.correctPin.equals(pin)) {
            failedAttempts++;
            int remaining = 3 - failedAttempts;
            if (remaining <= 0) {
                this.frozen = true;
                throw new AccountFrozenException("3 consecutive failed PIN attempts. Card has been seized and frozen.");
            }
            throw new InvalidPinException("Incorrect PIN entered!", remaining);
        }

        failedAttempts = 0; // reset on success
        System.out.println("  ✓ PIN verified successfully!");
    }

    public void withdraw(double amount)
            throws InsufficientFundsException, AccountFrozenException, DailyLimitExceededException {

        if (frozen) {
            throw new AccountFrozenException("Cannot withdraw: Account is currently frozen.");
        }

        if (amount <= 0) {
            throw new IllegalArgumentException("Withdrawal amount must be strictly positive.");
        }

        if (dailyWithdrawn + amount > DAILY_LIMIT) {
            throw new DailyLimitExceededException(DAILY_LIMIT, dailyWithdrawn + amount);
        }

        if (amount > balance) {
            throw new InsufficientFundsException(balance, amount);
        }

        balance -= amount;
        dailyWithdrawn += amount;
        System.out.printf("  💵 Dispensing $%.2f... New Balance: $%.2f (Daily used: $%.2f/%.2f)%n",
                amount, balance, dailyWithdrawn, DAILY_LIMIT);
    }

    public double getBalance() { return balance; }
}

public class Exercise8 {

    public static void main(String[] args) {
        System.out.println("╔══════════════════════════════════════════════════╗");
        System.out.println("║          🏧 SECURE ATM SIMULATOR                 ║");
        System.out.println("╚══════════════════════════════════════════════════╝");

        BankCardAccount account = new BankCardAccount("4111-9988-7766-5544", "4321", 850.00);

        // Session 1: Test wrong PIN and Recovery
        System.out.println("\n--- SCENARIO 1: WRONG PIN ATTEMPTS ---");
        try (AtmSession session = new AtmSession("4111-9988-7766-5544")) {
            account.authenticate("1111"); // Invalid
        } catch (InvalidPinException e) {
            System.out.println("  ❌ Auth Failed: " + e.getMessage());
            System.out.println("     Remaining attempts before lock: " + e.getRemainingAttempts());
        } catch (AccountFrozenException e) {
            System.out.println("  ❌ Security Lock: " + e.getMessage());
        }

        // Session 2: Test Successful Auth + Overdraw Attempt + Daily Limit
        System.out.println("\n--- SCENARIO 2: SUCCESSFUL LOGIN & TRANSACTION TRAPS ---");
        try (AtmSession session = new AtmSession("4111-9988-7766-5544")) {
            account.authenticate("4321"); // Correct PIN
            session.setAuthenticated(true);

            System.out.println("\nAttempting $200 withdrawal:");
            account.withdraw(200.00); // Valid

            System.out.println("\nAttempting $1,500 withdrawal (Exceeds Balance):");
            account.withdraw(1500.00); // Will trigger InsufficientFundsException

        } catch (InvalidPinException | AccountFrozenException e) {
            System.out.println("  ❌ Auth Error: " + e.getMessage());
        } catch (InsufficientFundsException e) {
            System.out.println("  ❌ Transaction Denied: " + e.getMessage());
            System.out.printf("     Shortfall amount: $%.2f%n", e.getDeficit());
        } catch (DailyLimitExceededException e) {
            System.out.println("  ❌ Limit Error: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("  ❌ Unexpected System Failure: " + e);
        }
    }
}
