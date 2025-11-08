package matrix;

import java.util.*;

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
public class Advance {

    public static void main(String[] args) {
        // Sample matrices for quick tests
        int[][] a = {
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9}
        };

        int[][] b = {
                {9, 8, 7},
                {6, 5, 4},
                {3, 2, 1}
        };

        System.out.println("Matrix A:");
        printMatrix(a);

        System.out.println("Matrix B:");
        printMatrix(b);

        System.out.println("A + B:");
        printMatrix(add(a, b));

        System.out.println("A x B:");
        printMatrix(multiply(a, b));

        System.out.println("Transpose of A:");
        printMatrix(transpose(a));

        System.out.println("A rotated 90° clockwise (new):");
        printMatrix(rotate90ClockwiseNew(a));

        int[][] rect = {
                {1, 2, 3, 4},
                {5, 6, 7, 8}
        };

        System.out.println("Rectangular matrix (spiral):");
        System.out.println(spiralOrder(rect));

        System.out.println("Search 5 in A -> " + Arrays.toString(search(a, 5)));

        int[][] pref = buildPrefixSum(a);
        System.out.println("Sum of submatrix [(0,0)-(1,1)]: " + submatrixSum(pref, 0, 0, 1, 1));

        System.out.println("Is A symmetric? " + isSymmetric(a));

        System.out.println("Saddle point in A [row, col, value] -> " + Arrays.toString(saddlePoint(a)));

        // In-place operations
        int[][] copyForRotate = deepCopy(a);
        rotate90ClockwiseInPlace(copyForRotate);
        System.out.println("A rotated 90° in-place:");
        printMatrix(copyForRotate);
    }

    // ---------------------------------------------------------------------
    // Utilities
    // ---------------------------------------------------------------------

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
            System.out.println("null");
            return;
        }
        for (int[] row : m) {
            System.out.println(Arrays.toString(row));
        }
    }

    /**
     * Deep-copies a matrix.
     *
     * <p><b>Approaches:</b></p>
     * <ul>
     *   <li><b>Row copy (used):</b> {@link Arrays#copyOf(int[], int)} per row.</li>
     *   <li><b>System.arraycopy:</b> Faster for large rows, same complexity.</li>
     * </ul>
     *
     * <p><b>Time:</b> O(rc)  <b>Space:</b> O(rc)</p>
     */
    public static int[][] deepCopy(int[][] m) {
        if (m == null) return null;
        int[][] out = new int[m.length][];
        for (int i = 0; i < m.length; i++) {
            out[i] = Arrays.copyOf(m[i], m[i].length);
        }
        return out;
    }

    // ---------------------------------------------------------------------
    // Basic Matrix Ops
    // ---------------------------------------------------------------------

    /**
     * Adds two matrices element-wise.
     *
     * <p><b>Approaches:</b></p>
     * <ul>
     *   <li><b>Direct nested loops (used):</b> Fastest and simplest.</li>
     *   <li><b>Streams:</b> Less readable and slower. Avoid in hot paths.</li>
     * </ul>
     *
     * <p><b>Time:</b> O(rc)  <b>Space:</b> O(rc) for the result</p>
     *
     * @throws IllegalArgumentException if matrices are null, empty, or dimension-mismatched
     */
    public static int[][] add(int[][] a, int[][] b) {
        validateSameDimensions(a, b);
        int r = a.length;
        int c = a[0].length;
        int[][] res = new int[r][c];
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                res[i][j] = a[i][j] + b[i][j];
            }
        }
        return res;
    }

    /**
     * Multiplies matrices {@code a x b}.
     *
     * <p><b>Approaches:</b></p>
     * <ul>
     *   <li><b>Naive triple loop (used):</b> Best for small to medium sizes.</li>
     *   <li><b>Cache-aware ordering:</b> Loop reordering / blocking improves locality.</li>
     *   <li><b>Strassen/Winograd:</b> Sub-cubic asymptotics for large square matrices. Complex and unstable for ints.</li>
     * </ul>
     *
     * <p><b>Time:</b> O(r1·c1·c2)  <b>Space:</b> O(r1·c2)</p>
     *
     * @throws IllegalArgumentException if inputs are null or inner dimensions mismatch
     */
    public static int[][] multiply(int[][] a, int[][] b) {
        if (a == null || b == null) throw new IllegalArgumentException("Input matrices cannot be null");
        int r1 = a.length;
        int c1 = a[0].length;
        int r2 = b.length;
        int c2 = b[0].length;
        if (c1 != r2) throw new IllegalArgumentException("Inner dimensions must match for multiplication");

        int[][] res = new int[r1][c2];
        for (int i = 0; i < r1; i++) {
            for (int k = 0; k < c1; k++) {      // i-k-j order is cache friendlier
                int aik = a[i][k];
                for (int j = 0; j < c2; j++) {
                    res[i][j] += aik * b[k][j];
                }
            }
        }
        return res;
    }

    /**
     * Returns a new transposed matrix.
     *
     * <p><b>Approaches:</b></p>
     * <ul>
     *   <li><b>Allocate new (used):</b> Works for any rectangular matrix.</li>
     *   <li><b>In-place:</b> Only for square; see {@link #transposeInPlace(int[][])}.</li>
     * </ul>
     *
     * <p><b>Time:</b> O(rc)  <b>Space:</b> O(rc) new matrix</p>
     */
    public static int[][] transpose(int[][] m) {
        if (m == null) return null;
        int r = m.length;
        int c = m[0].length;
        int[][] t = new int[c][r];
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                t[j][i] = m[i][j];
            }
        }
        return t;
    }

    // ---------------------------------------------------------------------
    // In-place square operations
    // ---------------------------------------------------------------------

    /**
     * In-place transpose for a non-empty square matrix.
     *
     * <p><b>Approaches:</b></p>
     * <ul>
     *   <li><b>Swap upper with lower triangle (used):</b> No extra memory.</li>
     *   <li><b>Cycle decomposition:</b> Useful for general in-place permutation reasoning.</li>
     * </ul>
     *
     * <p><b>Time:</b> O(n²)  <b>Space:</b> O(1)</p>
     *
     * @throws IllegalArgumentException if matrix is null, empty, or non-square
     */
    public static void transposeInPlace(int[][] m) {
        if (m == null) throw new IllegalArgumentException("Matrix cannot be null");
        int n = m.length;
        if (n == 0 || m[0].length != n) {
            throw new IllegalArgumentException("In-place transpose requires a non-empty square matrix");
        }
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                int tmp = m[i][j];
                m[i][j] = m[j][i];
                m[j][i] = tmp;
            }
        }
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
    public static void rotate90ClockwiseInPlace(int[][] m) {
        transposeInPlace(m);
        int n = m.length;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n / 2; j++) {
                int tmp = m[i][j];
                m[i][j] = m[i][n - 1 - j];
                m[i][n - 1 - j] = tmp;
            }
        }
    }

    /**
     * Returns a new matrix rotated 90 degrees clockwise.
     * Works for rectangular matrices.
     *
     * <p><b>Approaches:</b></p>
     * <ul>
     *   <li><b>Reverse rows then transpose (used):</b> Clean and general.</li>
     *   <li><b>Direct mapping:</b> {@code out[j][r-1-i] = m[i][j]} without intermediate step.</li>
     * </ul>
     *
     * <p><b>Time:</b> O(rc)  <b>Space:</b> O(rc)</p>
     */
    public static int[][] rotate90ClockwiseNew(int[][] m) {
        return transpose(m == null ? null : reverseRows(m));
    }

    private static int[][] reverseRows(int[][] m) {
        if (m == null) return null;
        int r = m.length;
        int[][] out = new int[r][];
        for (int i = 0; i < r; i++) {
            out[i] = Arrays.copyOf(m[r - 1 - i], m[r - 1 - i].length);
        }
        return out;
    }

    // ---------------------------------------------------------------------
    // Search & Traversals
    // ---------------------------------------------------------------------

    /**
     * Linear search in row-major order.
     *
     * <p><b>Approaches:</b></p>
     * <ul>
     *   <li><b>Linear scan (used):</b> For unsorted matrices.</li>
     *   <li><b>Row/col-sorted matrix:</b> Start top-right and walk left/down in O(r+c).</li>
     *   <li><b>Fully sorted flattenable:</b> Binary search on virtual 1D in O(log rc).</li>
     * </ul>
     *
     * <p><b>Time:</b> O(rc)  <b>Space:</b> O(1)</p>
     */
    public static int[] search(int[][] m, int target) {
        if (m == null) return new int[] {-1, -1};
        for (int i = 0; i < m.length; i++) {
            for (int j = 0; j < m[i].length; j++) {
                if (m[i][j] == target) return new int[] {i, j};
            }
        }
        return new int[] {-1, -1};
    }

    /**
     * Spiral order traversal.
     *
     * <p><b>Approaches:</b></p>
     * <ul>
     *   <li><b>Layer boundaries (used):</b> Track top/bottom/left/right and shrink.</li>
     *   <li><b>Visited set:</b> Simpler mental model for irregular shapes; O(rc) extra space.</li>
     * </ul>
     *
     * <p><b>Time:</b> O(rc)  <b>Space:</b> O(1) extra</p>
     */
    public static List<Integer> spiralOrder(int[][] m) {
        List<Integer> out = new ArrayList<>();
        if (m == null || m.length == 0) return out;

        int top = 0, bottom = m.length - 1;
        int left = 0, right = m[0].length - 1;

        while (top <= bottom && left <= right) {
            for (int j = left; j <= right; j++) out.add(m[top][j]);
            top++;

            for (int i = top; i <= bottom && left <= right; i++) out.add(m[i][right]);
            right--;

            if (top <= bottom) {
                for (int j = right; j >= left; j--) out.add(m[bottom][j]);
                bottom--;
            }
            if (left <= right) {
                for (int i = bottom; i >= top; i--) out.add(m[i][left]);
                left++;
            }
        }
        return out;
    }

    // ---------------------------------------------------------------------
    // Prefix-sum & Submatrix Sum
    // ---------------------------------------------------------------------

    /**
     * Builds a 2D prefix-sum matrix where
     * {@code pref[i][j]} is the sum of the rectangle {@code (0,0) .. (i,j)} inclusive.
     *
     * <p><b>Approaches:</b></p>
     * <ul>
     *   <li><b>Inclusive scan (used):</b> Row running sum + column accumulation.</li>
     *   <li><b>1-based padded grid:</b> Allocate (r+1)x(c+1) to avoid bounds checks.</li>
     * </ul>
     *
     * <p><b>Time:</b> O(rc)  <b>Space:</b> O(rc)</p>
     * <p><b>Note:</b> Use {@code long} if sum can overflow int.</p>
     *
     * @throws IllegalArgumentException if {@code m} is empty or jagged
     */
    public static int[][] buildPrefixSum(int[][] m) {
        if (m == null) return null;
        if (m.length == 0) throw new IllegalArgumentException("Matrix cannot be empty");
        int r = m.length, c = m[0].length;
        for (int[] row : m) {
            if (row.length != c) throw new IllegalArgumentException("Jagged matrix is not supported");
        }

        int[][] pref = new int[r][c];
        for (int i = 0; i < r; i++) {
            int rowSum = 0;
            for (int j = 0; j < c; j++) {
                rowSum += m[i][j];
                pref[i][j] = rowSum + (i > 0 ? pref[i - 1][j] : 0);
            }
        }
        return pref;
    }

    /**
     * O(1) sum of a submatrix using a 2D prefix-sum.
     *
     * <p><b>Approaches:</b></p>
     * <ul>
     *   <li><b>Inclusion–exclusion on prefix (used):</b> Standard O(1) query with O(rc) build.</li>
     *   <li><b>2D Fenwick/Segment Tree:</b> For frequent point updates + queries.</li>
     * </ul>
     *
     * <p><b>Time:</b> O(1) per query after O(rc) build  <b>Space:</b> O(rc)</p>
     *
     * @throws IllegalArgumentException if {@code pref} is null
     * @throws ArrayIndexOutOfBoundsException if indices are out of range
     */
    public static int submatrixSum(int[][] pref, int r1, int c1, int r2, int c2) {
        if (pref == null) throw new IllegalArgumentException("Prefix matrix cannot be null");
        int res = pref[r2][c2];
        if (r1 > 0) res -= pref[r1 - 1][c2];
        if (c1 > 0) res -= pref[r2][c1 - 1];
        if (r1 > 0 && c1 > 0) res += pref[r1 - 1][c1 - 1];
        return res;
    }

    // ---------------------------------------------------------------------
    // Misc
    // ---------------------------------------------------------------------

    /**
     * Finds a saddle point if it exists.
     *
     * <p>Definition: an element that is the minimum in its row and the maximum in its column.</p>
     *
     * <p><b>Approaches:</b></p>
     * <ul>
     *   <li><b>Row-min + column scan (used):</b> Pick row min, validate as col max.</li>
     *   <li><b>Precompute row mins + col maxes:</b> Two passes then check in O(1) per cell.</li>
     * </ul>
     *
     * <p><b>Time:</b> O(rc)  <b>Space:</b> O(1) or O(r+c) with precompute</p>
     *
     * @return {@code [row, col, value]} if found, else {@code {-1, -1, Integer.MIN_VALUE}}
     */
    public static int[] saddlePoint(int[][] m) {
        if (m == null || m.length == 0) return new int[] {-1, -1, Integer.MIN_VALUE};
        int r = m.length, c = m[0].length;

        for (int i = 0; i < r; i++) {
            int minCol = 0;
            for (int j = 1; j < c; j++) {
                if (m[i][j] < m[i][minCol]) minCol = j;
            }
            int candidate = m[i][minCol];
            boolean isSaddle = true;
            for (int k = 0; k < r; k++) {
                if (m[k][minCol] > candidate) { isSaddle = false; break; }
            }
            if (isSaddle) return new int[] {i, minCol, candidate};
        }
        return new int[] {-1, -1, Integer.MIN_VALUE};
    }

    /**
     * Checks if a matrix is symmetric with respect to its main diagonal.
     *
     * <p><b>Approaches:</b></p>
     * <ul>
     *   <li><b>Upper-triangle check (used):</b> Compare {@code m[i][j]} with {@code m[j][i]} for j&gt;i.</li>
     *   <li><b>Compute transpose then compare:</b> Simpler but O(rc) extra space.</li>
     * </ul>
     *
     * <p><b>Time:</b> O(n²) for n×n  <b>Space:</b> O(1)</p>
     */
    public static boolean isSymmetric(int[][] m) {
        if (m == null) return true;
        int n = m.length;
        if (n == 0 || m[0].length != n) return false;
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if (m[i][j] != m[j][i]) return false;
            }
        }
        return true;
    }

    // ---------------------------------------------------------------------
    // Validation
    // ---------------------------------------------------------------------

    private static void validateSameDimensions(int[][] a, int[][] b) {
        if (a == null || b == null) throw new IllegalArgumentException("Matrices cannot be null");
        if (a.length == 0 || b.length == 0) throw new IllegalArgumentException("Matrices cannot be empty");
        if (a[0].length == 0 || b[0].length == 0) throw new IllegalArgumentException("Matrices must be non-empty");
        if (a.length != b.length || a[0].length != b[0].length) {
            throw new IllegalArgumentException("Matrices must have same dimensions");
        }
    }
}
