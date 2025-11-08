package matrix;

import java.util.*;

/**
 * Matrix utilities - a compact, ready-to-use class for common matrix
 * operations and small examples. Drop new matrix problems into the
 * {@code main} method or add new static helpers below.
 *
 * <p>Contains: printing, transpose (in-place and non-destructive),
 * rotation, addition, multiplication, spiral traversal, search,
 * prefix-sum and submatrix sum, saddle point, and symmetry check.</p>
 *
 * <p>Author: Converted from array utilities for quick matrix practice.</p>
 */
public class Basics {

    public static void main(String[] args) {
        // Example matrices you can quickly replace when adding new problems
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

        System.out.println("A rotated 90deg clockwise (new matrix):");
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

        System.out.println("Saddle point in A (row,col,value) -> " + Arrays.toString(saddlePoint(a)));

        // In-place operations (note: will modify the input)
        int[][] copyForRotate = deepCopy(a);
        rotate90ClockwiseInPlace(copyForRotate);
        System.out.println("A rotated 90deg in-place:");
        printMatrix(copyForRotate);

        System.out.println("Done. Add new matrix problems to main or create new helper methods below.");
    }

    // ----------------------------- Utilities -----------------------------

    /**
     * Prints a matrix to stdout in a readable tabular form.
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
     * Returns a deep copy of a matrix.
     */
    public static int[][] deepCopy(int[][] m) {
        if (m == null) return null;
        int[][] out = new int[m.length][];
        for (int i = 0; i < m.length; i++) {
            out[i] = Arrays.copyOf(m[i], m[i].length);
        }
        return out;
    }

    // ------------------------ Basic Matrix Ops ---------------------------

    /**
     * Matrix addition. Returns a new matrix of the same dimensions.
     * Throws IllegalArgumentException when dimensions mismatch.
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
     * Matrix multiplication (a x b). Throws IllegalArgumentException if inner dimensions mismatch.
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
            for (int j = 0; j < c2; j++) {
                int sum = 0;
                for (int k = 0; k < c1; k++) sum += a[i][k] * b[k][j];
                res[i][j] = sum;
            }
        }
        return res;
    }

    /**
     * Returns the transpose of a matrix (non-destructive). Works for rectangular matrices.
     */
    public static int[][] transpose(int[][] m) {
        if (m == null) return null;
        int r = m.length;
        int c = m[0].length;
        int[][] t = new int[c][r];
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) t[j][i] = m[i][j];
        }
        return t;
    }

    // --------------------- In-place square operations --------------------

    /**
     * In-place transpose for square matrices.
     */
    public static void transposeInPlace(int[][] m) {
        if (m == null) throw new IllegalArgumentException("Matrix cannot be null");
        int n = m.length;
        if (n == 0 || m[0].length != n)
            throw new IllegalArgumentException("In-place transpose requires a non-empty square matrix");
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                int tmp = m[i][j];
                m[i][j] = m[j][i];
                m[j][i] = tmp;
            }
        }
    }

    /**
     * Rotate a square matrix 90 degrees clockwise in-place.
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
     * Rotate a matrix 90 degrees clockwise and return a new matrix. Works for rectangular matrices.
     */
    public static int[][] rotate90ClockwiseNew(int[][] m) {
        return transpose(m == null ? null : reverseRows(m));
    }

    private static int[][] reverseRows(int[][] m) {
        if (m == null) return null;
        int r = m.length;
        int[][] out = new int[r][];
        for (int i = 0; i < r; i++) out[i] = Arrays.copyOf(m[r - 1 - i], m[r - 1 - i].length);
        return out;
    }

    // ------------------------- Search & Traversals ----------------------

    /**
     * Search for a value. Returns [row, col] or {-1, -1} if not found.
     */
    public static int[] search(int[][] m, int target) {
        if (m == null) return new int[]{-1, -1};
        for (int i = 0; i < m.length; i++) {
            for (int j = 0; j < m[i].length; j++) {
                if (m[i][j] == target) return new int[]{i, j};
            }
        }
        return new int[]{-1, -1};
    }

    /**
     * Returns elements of the matrix in spiral order (row-major start).
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

    // ---------------------- Prefix-sum & Submatrix Sum ------------------

    /**
     * Builds a prefix-sum (2D) matrix where pref[i][j] is sum of rectangle
     * from (0,0) to (i,j) inclusive. Useful for O(1) submatrix sum queries.
     */
    public static int[][] buildPrefixSum(int[][] m) {
        if (m == null) return null;
        int r = m.length;
        int c = m[0].length;
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
     * Returns the sum of submatrix defined by top-left (r1,c1) and bottom-right (r2,c2)
     * inclusive using the prefix-sum matrix.
     */
    public static int submatrixSum(int[][] pref, int r1, int c1, int r2, int c2) {
        if (pref == null) throw new IllegalArgumentException("Prefix matrix cannot be null");
        int res = pref[r2][c2];
        if (r1 > 0) res -= pref[r1 - 1][c2];
        if (c1 > 0) res -= pref[r2][c1 - 1];
        if (r1 > 0 && c1 > 0) res += pref[r1 - 1][c1 - 1];
        return res;
    }

    // ------------------------ Misc Helpers ------------------------------

    /**
     * Returns a saddle point [row, col, value] or {-1,-1,Integer.MIN_VALUE} when none.
     * A saddle point is an element which is the minimum in its row and the maximum in its column.
     */
    public static int[] saddlePoint(int[][] m) {
        if (m == null || m.length == 0) return new int[]{-1, -1, Integer.MIN_VALUE};
        int r = m.length;
        int c = m[0].length;
        for (int i = 0; i < r; i++) {
            int minCol = 0;
            for (int j = 1; j < c; j++) if (m[i][j] < m[i][minCol]) minCol = j;
            int candidate = m[i][minCol];
            boolean isSaddle = true;
            for (int k = 0; k < r; k++) if (m[k][minCol] > candidate) { isSaddle = false; break; }
            if (isSaddle) return new int[]{i, minCol, candidate};
        }
        return new int[]{-1, -1, Integer.MIN_VALUE};
    }

    /**
     * Checks if a square matrix is symmetric (equal to its transpose).
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

    // ------------------------- Validation --------------------------------

    private static void validateSameDimensions(int[][] a, int[][] b) {
        if (a == null || b == null) throw new IllegalArgumentException("Matrices cannot be null");
        if (a.length == 0 || b.length == 0) throw new IllegalArgumentException("Matrices cannot be empty");
        if (a.length != b.length || a[0].length != b[0].length)
            throw new IllegalArgumentException("Matrices must have same dimensions");
    }

}

