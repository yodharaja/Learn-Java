package programs;

import java.util.Arrays;
import java.util.List;

/**
 * ============================================================
 * PROGRAM 55: Filter, Map & Reduce Pipeline with Stream API
 * ============================================================
 * Problem: WAP to take a list of integers, filter out only the
 * even numbers, square each of them, and calculate the sum using `reduce()`.
 * ============================================================
 */
public class P55_StreamFilterMapReduce {

    public static void main(String[] args) {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        System.out.println("Original Numbers: " + numbers);

        // Step 1: Filter Even -> [2, 4, 6, 8, 10]
        // Step 2: Map to Square -> [4, 16, 36, 64, 100]
        // Step 3: Reduce to Sum -> 4 + 16 + 36 + 64 + 100 = 220
        int sumOfSquaresOfEvens = numbers.stream()
                .filter(n -> n % 2 == 0)
                .map(n -> n * n)
                .reduce(0, Integer::sum);

        System.out.println("Sum of Squares of Even Numbers: " + sumOfSquaresOfEvens);
    }
}
