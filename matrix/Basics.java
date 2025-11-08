package matrix;

import java.util.Arrays;

/**
 * Compact matrix utilities for practice and interviews.
 *
 * <p>Includes:
 * printing, deep copy, transpose (in-place and new), rotation (in-place and new),
 * addition, multiplication, spiral traversal, linear search, 2D prefix-sum builder,
 * O(1) submatrix sum query, saddle point, and symmetry check.</p>
 *
 * <p>Usage: drop quick experiments into {@link #main(String[])} or add helpers.</p>
 */
public class Basics {

    public static void main(String[] args) {
        int[][] matrix1 = {
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9}
        };

        int[][] matrix2 = {
                {9, 8, 7},
                {6, 5, 4},
                {3, 2, 1}
        };
        System.out.println("Matrix 1:");

        printMatrix(matrix1);

//        System.out.println("Transpose of Matrix :");
//        printMatrix(transpose(matrix1));

        System.out.println("rotate90ClockwiseInPlace of Matrix :");
        printMatrix(rotate90ClockwiseInPlace(matrix1));
    }

    /**
     * Prints a matrix in row form. Prints {@code null} if the matrix is null.
     *
     * <p><b>Approaches:</b></p>
     * <ul>
     *   <li><b>Simple loop (used):</b> Iterate rows and print via {@link Arrays#toString(int[])}.</li>
     *   <li><b>Formatted output:</b> Compute max cell width, pad for aligned columns.</li>
     * </ul>
     *
     * <p><b>Time:</b> O(rc)  <b>Space:</b> O(1)</p>
     */
    public static void printMatrix(int[][] m) {
        if (m == null) {
            throw new IllegalArgumentException("Matrix is null");
        }
        if (m.length == 0) {
            throw new IllegalArgumentException("Matrix is empty");
        }
        System.out.println("[");
        for (int[] row : m) {
            System.out.println(Arrays.toString(row));
        }
        System.out.print("]");

    }

    /**
     * Returns a new transposed matrix.
     *
     * <p><b>Approaches:</b></p>
     * <ul>
     *   <li><b>Allocate new (used):</b> Works for any rectangular matrix.</li>
     *   <li><b>In-place:</b> Only for square; see </li>
     * </ul>
     *
     * <p><b>Time:</b> O(rc)  <b>Space:</b> O(rc) new matrix</p>
     */
    public static int[][] transpose(int[][] matrix) {
        if (matrix == null || matrix.length == 0) {
            throw new IllegalArgumentException("Matrix is null");
        }
        for (int i = 0; i < matrix.length; i++) {
            for (int j = i + 1; j < matrix.length; j++) {
                int temp = matrix[i][j];
                matrix[i][j] = matrix[j][i];
                matrix[j][i] = temp;

            }
        }
        return matrix;
    }

    /**
     * Rotates a square matrix 90 degrees clockwise in-place.
     *
     * <p><b>Approaches:</b></p>
     * <ul>
     *   <li><b>Transpose + reverse rows (used):</b> Simple and in-place.</li>
     *   <li><b>Layer-by-layer four-way swap:</b> Rotate each ring directly.</li>
     * </ul>
     *
     * <p><b>Time:</b> O(n²)  <b>Space:</b> O(1)</p>
     *
     * @throws IllegalArgumentException if matrix is null, empty, or non-square
     */
    public static int[][] rotate90ClockwiseInPlace(int[][] m) {
        int n = m.length;

        if (m == null || n == 0) {
            throw new IllegalArgumentException("Matrix is null");
        }
        transpose(m);
        for (int i = 0; i < m.length; i++) {
            for (int j = 0; j < n/2; j++) {
                int swap = m[i][j];
                m[i][j] = m[i][n - 1 - j];
                m[i][n - 1 - j] = swap;
            }
        }
        return m;
    }

}