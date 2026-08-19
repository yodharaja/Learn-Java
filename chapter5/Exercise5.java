package chapter5;

import java.util.Scanner;

/**
 * ============================================================
 * EXERCISE 5 — Bank Account & Transaction System 🏦
 * ============================================================
 *
 * Build a robust, object-oriented Bank Account simulation featuring:
 *   - Encapsulation with private fields and getter/setter validation
 *   - Constructors & Constructor Chaining (`this(...)`)
 *   - Static account counter & auto-generated unique account numbers
 *   - Constants (`static final`) for minimum balance, interest rate, fees
 *   - Methods for deposit, withdrawal, funds transfer, statement history
 *   - Overridden `toString()`, `equals()`, and `hashCode()`
 * ============================================================
 */

class BankAccount {
    // Static class constants
    public static final double MINIMUM_BALANCE = 500.00;
    public static final double OVERDRAFT_FEE = 35.00;
    public static final String BANK_NAME = "Antigravity Global Bank";

    // Static counter for auto-generating unique account IDs
    private static int nextAccountSeq = 1001;

    // Instance variables (Encapsulated)
    private final String accountNumber;
    private String accountHolderName;
    private double balance;
    private boolean active;
    private String[] transactionLogs;
    private int logCount;

    // 1. Constructor with default opening deposit
    public BankAccount(String accountHolderName) {
        this(accountHolderName, MINIMUM_BALANCE);
    }

    // 2. Parameterized constructor
    public BankAccount(String accountHolderName, double initialDeposit) {
        this.accountNumber = "ACC-" + (nextAccountSeq++);
        setAccountHolderName(accountHolderName);
        this.transactionLogs = new String[20];
        this.logCount = 0;
        this.active = true;

        if (initialDeposit >= MINIMUM_BALANCE) {
            this.balance = initialDeposit;
            logTransaction("Account opened with initial balance of $" + String.format("%.2f", initialDeposit));
        } else {
            System.out.printf("  ⚠️ Initial deposit $%.2f is below minimum ($%.2f). Defaulting to $%.2f%n",
                    initialDeposit, MINIMUM_BALANCE, MINIMUM_BALANCE);
            this.balance = MINIMUM_BALANCE;
            logTransaction("Account opened with minimum balance of $" + String.format("%.2f", MINIMUM_BALANCE));
        }
    }

    // --- ENCAPSULATED GETTERS & SETTERS ---

    public String getAccountNumber() {
        return accountNumber;
    }

    public String getAccountHolderName() {
        return accountHolderName;
    }

    public void setAccountHolderName(String name) {
        if (name != null && name.trim().length() >= 2) {
            this.accountHolderName = name.trim();
        } else {
            System.out.println("  ❌ Invalid name! Retaining previous holder name.");
        }
    }

    public double getBalance() {
        return balance;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
        logTransaction("Account status updated to: " + (active ? "ACTIVE" : "FROZEN"));
    }

    // --- TRANSACTION METHODS ---

    private void logTransaction(String entry) {
        if (logCount < transactionLogs.length) {
            transactionLogs[logCount++] = entry;
        } else {
            // Shift left and append at end if log buffer is full
            System.arraycopy(transactionLogs, 1, transactionLogs, 0, transactionLogs.length - 1);
            transactionLogs[transactionLogs.length - 1] = entry;
        }
    }

    public boolean deposit(double amount) {
        if (!active) {
            System.out.println("  ❌ Transaction rejected: Account is frozen.");
            return false;
        }
        if (amount <= 0) {
            System.out.println("  ❌ Deposit amount must be strictly positive.");
            return false;
        }

        this.balance += amount;
        logTransaction(String.format("Deposited +$%.2f (Balance: $%.2f)", amount, this.balance));
        System.out.printf("  ✓ Successfully deposited $%.2f into %s. New Balance: $%.2f%n",
                amount, accountNumber, this.balance);
        return true;
    }

    public boolean withdraw(double amount) {
        if (!active) {
            System.out.println("  ❌ Transaction rejected: Account is frozen.");
            return false;
        }
        if (amount <= 0) {
            System.out.println("  ❌ Withdrawal amount must be positive.");
            return false;
        }

        if (this.balance - amount < MINIMUM_BALANCE) {
            System.out.printf("  ❌ Insufficient funds! Withdrawal of $%.2f violates min balance of $%.2f (Current: $%.2f)%n",
                    amount, MINIMUM_BALANCE, this.balance);
            return false;
        }

        this.balance -= amount;
        logTransaction(String.format("Withdrew -$%.2f (Balance: $%.2f)", amount, this.balance));
        System.out.printf("  ✓ Successfully withdrew $%.2f from %s. New Balance: $%.2f%n",
                amount, accountNumber, this.balance);
        return true;
    }

    public boolean transferTo(BankAccount destination, double amount) {
        if (destination == null) {
            System.out.println("  ❌ Destination account does not exist.");
            return false;
        }
        if (destination == this) {
            System.out.println("  ❌ Cannot transfer money to the same account.");
            return false;
        }

        System.out.printf("  [Transfer] Initiating $%.2f from %s -> %s...%n",
                amount, this.accountNumber, destination.accountNumber);

        if (this.withdraw(amount)) {
            destination.deposit(amount);
            logTransaction(String.format("Transferred -$%.2f to %s", amount, destination.getAccountNumber()));
            return true;
        }
        return false;
    }

    public void printMiniStatement() {
        System.out.println("\n╔══════════════════════════════════════════════════╗");
        System.out.printf("║  MINI STATEMENT: %-31s ║%n", accountNumber);
        System.out.printf("║  Holder: %-39s ║%n", accountHolderName);
        System.out.printf("║  Balance: $%-37.2f ║%n", balance);
        System.out.printf("║  Status: %-38s ║%n", active ? "Active" : "FROZEN");
        System.out.println("╠══════════════════════════════════════════════════╣");
        System.out.println("║  Recent Activity:                                ║");
        for (int i = 0; i < logCount; i++) {
            System.out.printf("║   %d. %-42s ║%n", (i + 1), transactionLogs[i]);
        }
        System.out.println("╚══════════════════════════════════════════════════╝");
    }

    @Override
    public String toString() {
        return String.format("BankAccount[No='%s', Holder='%s', Balance=$%.2f, Active=%s]",
                accountNumber, accountHolderName, balance, active);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BankAccount that = (BankAccount) o;
        return accountNumber.equals(that.accountNumber);
    }

    @Override
    public int hashCode() {
        return accountNumber.hashCode();
    }
}

public class Exercise5 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("╔══════════════════════════════════════════════════╗");
        System.out.println("║         🏦 " + BankAccount.BANK_NAME + "       ║");
        System.out.println("╚══════════════════════════════════════════════════╝");

        // Create sample accounts
        BankAccount acc1 = new BankAccount("Yodha Raja", 2500.0);
        BankAccount acc2 = new BankAccount("Sarah Connor", 1200.0);

        System.out.println("Created initial accounts:");
        System.out.println("  1: " + acc1);
        System.out.println("  2: " + acc2);

        // Perform banking operations
        System.out.println("\n--- 1. DEPOSITS & WITHDRAWALS ---");
        acc1.deposit(500.0);
        acc1.withdraw(300.0);
        acc1.withdraw(4000.0); // Should fail (exceeds balance limit)

        System.out.println("\n--- 2. INTER-ACCOUNT FUNDS TRANSFER ---");
        acc1.transferTo(acc2, 700.0);

        System.out.println("\n--- 3. ACCOUNT FREEZE & SECURITY ---");
        acc2.setActive(false);
        acc2.deposit(100.0); // Should fail (frozen)
        acc2.setActive(true);
        acc2.deposit(100.0); // Should succeed

        System.out.println("\n--- 4. MINI STATEMENTS ---");
        acc1.printMiniStatement();
        acc2.printMiniStatement();

        sc.close();
    }
}
