package programs;

/**
 * ============================================================
 * PROGRAM 03: Simple and Compound Interest Calculator
 * ============================================================
 * Problem: WAP to calculate Simple Interest (SI) and Compound Interest (CI).
 *   - Simple Interest   = (P * R * T) / 100
 *   - Compound Interest = P * (1 + R/100)^T - P
 * ============================================================
 */
public class P03_SimpleAndCompoundInterest {

    public static double calculateSimpleInterest(double principal, double rate, double timeYears) {
        return (principal * rate * timeYears) / 100.0;
    }

    public static double calculateCompoundInterest(double principal, double rate, double timeYears) {
        double totalAmount = principal * Math.pow((1.0 + (rate / 100.0)), timeYears);
        return totalAmount - principal;
    }

    public static void main(String[] args) {
        double principal = 10000.0; // $10,000
        double rate = 7.5;          // 7.5% per annum
        double time = 5.0;          // 5 years

        double si = calculateSimpleInterest(principal, rate, time);
        double ci = calculateCompoundInterest(principal, rate, time);

        System.out.printf("Principal Amount : $%,.2f%n", principal);
        System.out.printf("Annual Rate      : %.2f%%%n", rate);
        System.out.printf("Time Duration    : %.1f Years%n", time);
        System.out.println("----------------------------------------");
        System.out.printf("Simple Interest   (SI): $%,.2f (Total: $%,.2f)%n", si, principal + si);
        System.out.printf("Compound Interest (CI): $%,.2f (Total: $%,.2f)%n", ci, principal + ci);
    }
}
