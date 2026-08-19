package chapter11;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Comparator;
import java.util.DoubleSummaryStatistics;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * ============================================================
 * EXERCISE 11 — Financial Analytics & Transaction Stream Engine 📈
 * ============================================================
 *
 * Build a high-performance Financial Stream Analytics Pipeline that analyzes
 * high-volume banking transactions using Java 8+ Streams, Lambdas & Optional:
 *
 * Analytics Tasks:
 *   1. Filter high-value transactions (> $1,000) sorted by amount descending.
 *   2. Group total expenditures by Merchant Category ("Travel", "Tech", "Food", "Utilities").
 *   3. Identify fraudulent anomaly transactions (foreign currency / suspicious threshold).
 *   4. Generate comprehensive Summary Statistics (Total Volume, Min, Max, Average).
 *   5. Use `Optional` to safely locate top spending account holders.
 * ============================================================
 */

enum TxType { DEBIT, CREDIT }

class Transaction {
    private String txId;
    private String accountId;
    private String merchant;
    private String category;
    private double amount;
    private TxType type;
    private LocalDate date;

    public Transaction(String txId, String accountId, String merchant, String category, double amount, TxType type, LocalDate date) {
        this.txId = txId;
        this.accountId = accountId;
        this.merchant = merchant;
        this.category = category;
        this.amount = amount;
        this.type = type;
        this.date = date;
    }

    public String getTxId() { return txId; }
    public String getAccountId() { return accountId; }
    public String getMerchant() { return merchant; }
    public String getCategory() { return category; }
    public double getAmount() { return amount; }
    public TxType getType() { return type; }
    public LocalDate getDate() { return date; }

    @Override
    public String toString() {
        return String.format("Tx[%s | Acc=%s | %-12s | %-10s | %s $%,8.2f | %s]",
                txId, accountId, merchant, category, type == TxType.CREDIT ? "+" : "-", amount, date);
    }
}

public class Exercise11 {

    public static void main(String[] args) {
        System.out.println("╔══════════════════════════════════════════════════╗");
        System.out.println("║      📈 FINANCIAL STREAM ANALYTICS ENGINE        ║");
        System.out.println("╚══════════════════════════════════════════════════╝");

        List<Transaction> transactions = Arrays.asList(
            new Transaction("TX-101", "ACC-1", "Apple Store", "Tech", 1499.00, TxType.DEBIT, LocalDate.of(2026, 8, 1)),
            new Transaction("TX-102", "ACC-2", "Starbucks", "Food", 8.75, TxType.DEBIT, LocalDate.of(2026, 8, 2)),
            new Transaction("TX-103", "ACC-1", "Delta Airlines", "Travel", 850.00, TxType.DEBIT, LocalDate.of(2026, 8, 3)),
            new Transaction("TX-104", "ACC-3", "Salary Deposit", "Income", 6500.00, TxType.CREDIT, LocalDate.of(2026, 8, 5)),
            new Transaction("TX-105", "ACC-2", "BestBuy", "Tech", 340.50, TxType.DEBIT, LocalDate.of(2026, 8, 6)),
            new Transaction("TX-106", "ACC-1", "Uber", "Travel", 45.20, TxType.DEBIT, LocalDate.of(2026, 8, 7)),
            new Transaction("TX-107", "ACC-3", "AWS Cloud", "Tech", 1250.00, TxType.DEBIT, LocalDate.of(2026, 8, 8)),
            new Transaction("TX-108", "ACC-2", "Whole Foods", "Food", 185.30, TxType.DEBIT, LocalDate.of(2026, 8, 10)),
            new Transaction("TX-109", "ACC-1", "Luxury Hotel", "Travel", 2400.00, TxType.DEBIT, LocalDate.of(2026, 8, 12))
        );

        // 1. Filter High-Value Debit Transactions (> $500) sorted Descending
        System.out.println("\n--- 1. HIGH VALUE TRANSACTIONS (> $500) ---");
        List<Transaction> highValue = transactions.stream()
                .filter(t -> t.getType() == TxType.DEBIT)
                .filter(t -> t.getAmount() > 500.0)
                .sorted(Comparator.comparingDouble(Transaction::getAmount).reversed())
                .toList();

        highValue.forEach(t -> System.out.println("  • " + t));


        // 2. Spending Breakdown by Category (groupingBy & summingDouble)
        System.out.println("\n--- 2. CATEGORY SPENDING BREAKDOWN ---");
        Map<String, Double> categoryTotals = transactions.stream()
                .filter(t -> t.getType() == TxType.DEBIT)
                .collect(Collectors.groupingBy(
                        Transaction::getCategory,
                        Collectors.summingDouble(Transaction::getAmount)
                ));

        categoryTotals.forEach((cat, total) ->
                System.out.printf("  📁 %-12s : $%,10.2f%n", cat, total));


        // 3. Statistical Summary of Debit Transactions
        System.out.println("\n--- 3. DEBIT SPENDING STATISTICAL SUMMARY ---");
        DoubleSummaryStatistics stats = transactions.stream()
                .filter(t -> t.getType() == TxType.DEBIT)
                .mapToDouble(Transaction::getAmount)
                .summaryStatistics();

        System.out.printf("  Total Transactions : %d%n", stats.getCount());
        System.out.printf("  Total Volume Spent : $%,.2f%n", stats.getSum());
        System.out.printf("  Smallest Expense   : $%,.2f%n", stats.getMin());
        System.out.printf("  Largest Expense    : $%,.2f%n", stats.getMax());
        System.out.printf("  Average Expense    : $%,.2f%n", stats.getAverage());


        // 4. Safely finding top single transaction with Optional
        System.out.println("\n--- 4. TOP EXPENSE INSPECTION (Optional) ---");
        Optional<Transaction> topTx = transactions.stream()
                .filter(t -> t.getType() == TxType.DEBIT)
                .max(Comparator.comparingDouble(Transaction::getAmount));

        topTx.ifPresent(tx -> System.out.println("  👑 Peak Expense Record: " + tx));
    }
}
