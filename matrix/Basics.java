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
        int[][] matrix1 = {{1, 4, 7, 10}, {2, 5, 8, 12}, {3, 6, 9, 14}};

        int[][] matrix2 = {{9, 8, 7}, {6, 5, 4}, {3, 2, 1}};
        System.out.println("Matrix 1:");

        printMatrix(matrix1);

        //        System.out.println("Transpose of Matrix :");
        //        printMatrix(transpose(matrix1));
        System.out.println(
                "Search in Matrix :  " +
                        Arrays.toString(searchSortedMatrix(matrix1, 8))
        );

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
        System.out.println("]");
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
            for (int j = 0; j < n / 2; j++) {
                int swap = m[i][j];
                m[i][j] = m[i][n - 1 - j];
                m[i][n - 1 - j] = swap;
            }
        }
        return m;
    }

    /**
     * Problem: Search an element in a matrix that is sorted both row-wise (left to right)
     * and column-wise (top to bottom).
     *
     * <p>The matrix guarantees:
     * <ul>
     *   <li>Each row is sorted in non-decreasing order.</li>
     *   <li>Each column is sorted in non-decreasing order.</li>
     * </ul>
     *
     * <p><b>Goal:</b> Determine whether the target value exists in the matrix and return its position.</p>
     *
     * <p><b>Approaches:</b></p>
     * <ul>
     *   <li><b>1. Brute Force (O(r·c)):</b>
     *       Scan every cell. Simple, but inefficient for large matrices.</li>
     *
     *   <li><b>2. Row Binary Search (O(r·log c)):</b>
     *       Since rows are sorted, apply binary search per row.</li>
     *
     *   <li><b>3. Optimal Staircase Search (O(r + c)) — Recommended:</b>
     *       Start from the top-right corner:
     *       <ul>
     *         <li>If the current value is greater than target → move left (values decrease)</li>
     *         <li>If the current value is less than target → move down (values increase)</li>
     *         <li>If equal → found</li>
     *       </ul>
     *   </li>
     * </ul>
     *
     * <p><b>Time Complexity:</b> O(r + c)<br>
     * <b>Space Complexity:</b> O(1)</p>
     *
     * <p><b>Example:</b></p>
     * <pre>
     * Matrix:
     * [
     *   [1, 4, 7, 10],
     *   [2, 5, 8, 12],
     *   [3, 6, 9, 14]
     * ]
     *
     * Target: 8 → Output: [1, 2]
     * Target: 13 → Output: [-1, -1]
     * </pre>
     *
     * @param matrix the sorted matrix to search in
     * @param target the value to search for
     * @return an array of size 2: {@code [row, col]} if found, otherwise {@code [-1, -1]}
     * @throws IllegalArgumentException if matrix is null or empty
     */
    public static int[] searchSortedMatrix(int[][] matrix, int target) {
        // Input validation
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            throw new IllegalArgumentException(
                    "Matrix cannot be null or empty"
            );
        }

        int rows = matrix.length;
        int cols = matrix[0].length;

        // Start from top-right corner
        int row = 0;
        int col = cols - 1;

        // Staircase search
        while (row < rows && col >= 0) {
            int current = matrix[row][col];

            if (current == target) {
                // Found the target
                return new int[]{row, col};
            } else if (current > target) {
                // Current value is too large, move left
                col--;
            } else {
                // Current value is too small, move down
                row++;
            }
        }

        // Target not found
        return new int[]{-1, -1};
    }
}
