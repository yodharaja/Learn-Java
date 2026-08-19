package programs;

/**
 * ============================================================
 * PROGRAM 33: Matrix Transpose and Symmetry Verification
 * ============================================================
 * Problem: WAP to:
 *   a) Find the Transpose of a matrix (swapping rows and columns)
 *   b) Check if a matrix is Symmetric (Matrix == Transpose)
 * ============================================================
 */
public class P33_MatrixTransposeAndSymmetry {

    public static int[][] transpose(int[][] matrix) {
        int rows = matrix.length;
        int cols = matrix[0].length;
        int[][] trans = new int[cols][rows];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                trans[j][i] = matrix[i][j];
            }
        }
        return trans;
    }

    public static boolean isSymmetric(int[][] matrix) {
        if (matrix.length != matrix[0].length) return false;
        int n = matrix.length;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < i; j++) {
                if (matrix[i][j] != matrix[j][i]) return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        int[][] sym = {
            {1, 2, 3},
            {2, 4, 5},
            {3, 5, 6}
        };

        System.out.println("Is matrix symmetric? " + isSymmetric(sym));
    }
}
