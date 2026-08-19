package programs;

/**
 * ============================================================
 * PROGRAM 49: Custom Exception & Voting Age Validation
 * ============================================================
 * Problem: WAP to define a custom checked exception `InvalidAgeException`
 * and throw it if an applicant's age is less than 18.
 * ============================================================
 */

class InvalidAgeException extends Exception {
    private int inputAge;

    public InvalidAgeException(String message, int age) {
        super(message + " (Provided Age: " + age + ")");
        this.inputAge = age;
    }

    public int getInputAge() { return inputAge; }
}

public class P49_CustomExceptionAgeValidation {

    public static void validateVotingEligibility(int age) throws InvalidAgeException {
        if (age < 0 || age > 120) {
            throw new IllegalArgumentException("Age out of physical human bounds!");
        }
        if (age < 18) {
            throw new InvalidAgeException("Ineligible to vote: Must be at least 18 years old", age);
        }
        System.out.println("  ✓ Eligibility confirmed: Access granted to voting ballot.");
    }

    public static void main(String[] args) {
        int[] applicantAges = {22, 16, 45, -3};

        for (int age : applicantAges) {
            System.out.println("Checking applicant with age " + age + ":");
            try {
                validateVotingEligibility(age);
            } catch (InvalidAgeException e) {
                System.out.println("  ❌ Registration Denied: " + e.getMessage());
            } catch (IllegalArgumentException e) {
                System.out.println("  ❌ Parameter Error: " + e.getMessage());
            }
        }
    }
}
