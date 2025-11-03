package array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Basics {
    public static void main(String[] args) {
        int[] array = new int[]{4, -1, -2, 5, 3, -6, 2, 4, -3, 2, 3, -2, 3};
        int index = customSearch(array, 11);
        int[] updatedArray = insertElement(array, 27, 3);
        int[] deletedArray = deleteElement(array, 6);
        int maxNumber = findMax(array);
        int secondMaxNumber = findSecondMax(array);
        int thirdLargest = thirdLargest(array);
        int[] removedDuplicatesArray = removeDuplicatesFromSorted(new int[]{1, 2, 2, 3, 4, 5, 5, 5, 6, 7, 7});
        int smallestNumber = findSmallest(array);
        boolean isArraySorted = isArraySorted(array);
        int[] reverseArray = reverseArray(array);
        int[] replaceWithMax = replaceWithMax(array);
        int[] leadersInArray = leadersInArray(array);
        int findMaxSum = findMaxSum(array);
        int containerWithMaxWater = containerWithMaxWater(array);
        int trapSum = trap(array);

        System.out.println("|||||----------------------------------------------------------------------------------------------------|||||");
        System.out.println("- Index of 11: " + index);
        System.out.println("- After insertion: " + Arrays.toString(updatedArray));
        System.out.println("- After deletion: " + Arrays.toString(deletedArray));
        System.out.println("- Maximum number: " + maxNumber);
        System.out.println("- Second maximum number: " + secondMaxNumber);
        System.out.println("- Third largest number: " + thirdLargest);
        System.out.println("- Duplicates removed from sorted array: " + Arrays.toString(removedDuplicatesArray));
        System.out.println("- Smallest number: " + smallestNumber);
        System.out.println("- Is Array sorted: " + isArraySorted);
        System.out.println("- Reversed Array: " + Arrays.toString(reverseArray));
        System.out.println("- Replace with max Array: " + Arrays.toString(replaceWithMax));
        System.out.println("- Leaders in Array: " + Arrays.toString(leadersInArray));
        System.out.println("-  Max Sum Is: " + findMaxSum);
        System.out.println("-  Container with max water: " + containerWithMaxWater);
        System.out.println("-  Trapping rain water: " + trapSum);
        System.out.println("|||||----------------------------------------------------------------------------------------------------|||||");
    }


    /**
     * Searches for a value in the array and returns its index.
     *
     * @return index of the value, or -1 if not found
     */
    public static int customSearch(int[] array, int searchValue) {
        if (array == null) {
            return -1;
        }

        for (int i = 0; i < array.length; i++) {
            if (array[i] == searchValue) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Insert element in specific position.
     *
     * @return updated array with new inserted element
     */
    public static int[] insertElement(int[] array, int value, int position) {
        if (array == null) {
            throw new IllegalArgumentException("Array cannot be null");
        }
        if (position < 0 || position > array.length) {
            throw new IllegalArgumentException("Invalid position: " + position);
        }

        int[] newArray = new int[array.length + 1];

        // copy elements before insertion point
        for (int i = 0; i < position; i++) {
            newArray[i] = array[i];
        }

        // insert new value
        newArray[position] = value;

        // copy remaining elements shifted by one
        for (int i = position; i < array.length; i++) {
            newArray[i + 1] = array[i];
        }

        return newArray;
    }

    /**
     * Delete the element at specific positions
     *
     * @return updated array
     */
    public static int[] deleteElement(int[] array, int position) {
        if (array == null) {
            throw new IllegalArgumentException("Array cannot be null");
        }
        if (position < 0 || position >= array.length) {
            throw new IllegalArgumentException("Invalid position: " + position);
        }
        if (array.length == 1) {
            return new int[0];
        }

        int[] newArray = new int[array.length - 1];

        // copy elements before the position
        for (int i = 0; i < position; i++) {
            newArray[i] = array[i];
        }

        // copy elements after the position shifted left by one
        for (int i = position + 1; i < array.length; i++) {
            newArray[i - 1] = array[i];
        }

        return newArray;
    }

    /**
     * Finds the maximum value in the array.
     */
    public static int findMax(int[] array) {
        if (array == null || array.length == 0) {
            throw new IllegalArgumentException("Array must have at least one element");
        }

        int max = array[0];
        for (int i = 1; i < array.length; i++) {
            if (array[i] > max) {
                max = array[i];
            }
        }
        return max;
    }

    /**
     * Finds the second maximum distinct value in the array.
     */
    public static int findSecondMax(int[] array) {
        if (array == null || array.length < 2) {
            throw new IllegalArgumentException("Array must have at least two elements");
        }

        int max = Integer.MIN_VALUE;
        int secondMax = Integer.MIN_VALUE;

        for (int num : array) {
            if (num > max) {
                secondMax = max;
                max = num;
            } else if (num > secondMax && num != max) {  // FIXED: Changed from num < max to num != max
                secondMax = num;
            }
        }

        if (secondMax == Integer.MIN_VALUE) {
            throw new IllegalStateException("No distinct second maximum found (all elements equal or less than two distinct values)");
        }

        return secondMax;
    }

    /**
     * Finds the third largest distinct value in the array.
     */
    public static int thirdLargest(int[] array) {
        if (array == null || array.length < 3) {
            throw new IllegalArgumentException("Array must have at least three elements");
        }

        int largest = Integer.MIN_VALUE;
        int secondLargest = Integer.MIN_VALUE;
        int thirdLargest = Integer.MIN_VALUE;

        for (int ele : array) {
            if (ele > largest) {
                thirdLargest = secondLargest;
                secondLargest = largest;
                largest = ele;
            } else if (ele > secondLargest && ele != largest) {
                thirdLargest = secondLargest;
                secondLargest = ele;
            } else if (ele > thirdLargest && ele != secondLargest && ele != largest) {
                thirdLargest = ele;
            }
        }

        if (thirdLargest == Integer.MIN_VALUE) {
            throw new IllegalStateException("Less than three distinct elements in array");
        }

        return thirdLargest;
    }

    /**
     * Removes consecutive duplicates from a SORTED array.
     * NOTE: This only works correctly on sorted arrays!
     */
    public static int[] removeDuplicatesFromSorted(int[] array) {
        if (array == null || array.length == 0) {
            return new int[0];
        }

        int index = 0;
        for (int i = 1; i < array.length; i++) {
            if (array[i] != array[index]) {
                index++;
                array[index] = array[i];
            }
        }

        return Arrays.copyOf(array, index + 1);
    }

    /**
     * Removes all duplicates from an unsorted array (if needed).
     */
    public static int[] removeDuplicatesFromUnsorted(int[] array) {
        if (array == null || array.length == 0) {
            return new int[0];
        }

        int[] temp = new int[array.length];
        int index = 0;

        for (int i = 0; i < array.length; i++) {
            boolean isDuplicate = false;
            for (int j = 0; j < index; j++) {
                if (array[i] == temp[j]) {
                    isDuplicate = true;
                    break;
                }
            }
            if (!isDuplicate) {
                temp[index++] = array[i];
            }
        }

        return Arrays.copyOf(temp, index);
    }

    /**
     * Finds the smallest value in the array.
     */
    public static int findSmallest(int[] array) {
        if (array == null || array.length == 0) {
            throw new IllegalArgumentException("Array must have at least one element");
        }

        int smallest = array[0];
        for (int ele : array) {
            if (ele < smallest) {
                smallest = ele;
            }
        }

        return smallest;
    }

    /**
     * Check if array is sorted in ascending order
     */
    private static boolean isArraySorted(int[] array) {
        // Remove this line! Don't overwrite the parameter

        if (array == null || array.length == 0) {
            throw new IllegalArgumentException("Array cannot be null or empty");
        }

        // Single element is considered sorted
        if (array.length == 1) {
            return true;
        }

        // Check if ALL consecutive pairs are in order
        for (int i = 1; i < array.length; i++) {
            if (array[i - 1] > array[i]) {  // If any pair is out of order
                return false;
            }
        }

        return true;
    }

    /**
     * Reverses the array in-place.
     *
     * @param array the array to reverse
     * @return the same array (reversed)
     */
    public static int[] reverseArray(int[] array) {
        if (array == null || array.length == 0) {
            throw new IllegalArgumentException("Array cannot be null or empty");
        }

        int left = 0;
        int right = array.length - 1;

        while (left < right) {
            // Swap elements
            int temp = array[left];
            array[left] = array[right];
            array[right] = temp;

            left++;
            right--;
        }

        return array;
    }

    /**
     * Replaces each element with the greatest element on its right side.
     * The last element is replaced with 0.
     *
     * @param array the input array
     * @return new array with elements replaced
     */
    public static int[] replaceWithMax(int[] array) {
        if (array == null || array.length == 0) {
            throw new IllegalArgumentException("Array cannot be null or empty");
        }

        int n = array.length;
        int[] result = new int[n];
        int max = array[n - 1];
        result[n - 1] = 0;

        for (int i = n - 2; i >= 0; i--) {
            result[i] = max;
            if (array[i] > max) {
                max = array[i];
            }
        }

        return result;
    }

    /**
     * Returns all leaders in the array (elements >= all elements to their right).
     *
     * @throws IllegalArgumentException if array is null or empty
     */
    public static int[] leadersInArray(int[] array) {
        if (array == null || array.length == 0) {
            throw new IllegalArgumentException("Array cannot be null or empty");
        }
        int n = array.length;
        List<Integer> leaders = new ArrayList<>();
        int max = array[n - 1];
        leaders.add(max);
        for (int i = n - 2; i >= 0; i--) {
            if (array[i] > max) {
                max = array[i];
                leaders.add(max);
            }
        }
        Collections.reverse(leaders);
        return leaders.stream().mapToInt(Integer::intValue).toArray();
    }

    /**
     * Problem: Find the maximum sum of a contiguous subarray (Kadane's Algorithm)
     * <p>
     * Example:
     * Input:  [-2, 1, -3, 4, -1, 2, 1, -5, 4]
     * Output: 6  // Subarray: [4, -1, 2, 1]
     * <p>
     * Constraints:
     * 1 <= arr.length <= 10^5
     * -10^4 <= arr[i] <= 10^4
     * <p>
     * Time Complexity: O(n)
     * Space Complexity: O(1)
     */
    public static int findMaxSum(int[] array) {
        if (array == null || array.length == 0) {
            throw new IllegalArgumentException("Array cannot be null or empty");
        }
        int max = Integer.MIN_VALUE;
        int currentSum = 0;
        for (int i = array.length - 1; i >= 0; i--) {
            currentSum += array[i];
            if (currentSum > max) {
                max = currentSum;
            }
            if (currentSum < 0) return 0;
        }
        return max;
    }

    /**
     * Problem: Container With Most Water
     * <p>
     * Given n non-negative integers where each represents a vertical line
     * on the x-axis, find two lines that together with the x-axis form a container
     * which can hold the most water.
     * <p>
     * Example:
     * Input:  [1,8,6,2,5,4,8,3,7]
     * Output: 49
     * (Lines at index 1 and 8 form the container: min(8,7) * (8 - 1) = 49)
     * <p>
     * Constraints:
     * 2 <= height.length <= 10^5
     * 0 <= height[i] <= 10^4
     * <p>
     * Time Complexity: O(n)
     * Space Complexity: O(1)
     */

    static int containerWithMaxWater(int[] array) {
        if (array == null || array.length == 0) {
            throw new IllegalArgumentException("Array cannot be null or empty");
        }

        int left = 0;
        int right = array.length - 1;
        int maxArea = 0;
        while (left < right) {

            int distance = right - left;
            int height = Math.min(array[left], array[right]);
            int area = distance * height;
            maxArea = Math.max(area, maxArea);

            if (array[left] < array[right]) {
                left++;
            } else {
                right--;
            }

        }
        return maxArea;
    }

    /**
     * Problem: Trapping Rain Water
     * <p>
     * Given n non-negative integers representing an elevation map where the width
     * of each bar is 1, compute how much water it can trap after raining.
     * <p>
     * Example:
     * Input:  [0,1,0,2,1,0,1,3,2,1,2,1]
     * Output: 6
     * <p>
     * Constraints:
     * 1 <= height.length <= 2 * 10^4
     * 0 <= height[i] <= 10^5
     * <p>
     * Time Complexity: O(n)
     * Space Complexity: O(n)
     */
    public class TrappingRainWater {

        /**
         * Calculates the total trapped rain water.
         *
         * @param height the elevation map
         * @return total trapped water
         */
        public static int trap(int[] height) {

            if (height == null || height.length == 0) {
                throw new IllegalArgumentException("Array cannot be null or empty");
            }

            int n = height.length;
            int[] left = new int[n];
            int[] right = new int[n];
            int totalWater = 0;

            // Step 1: Fill left array (max height to the left of each index)
            left[0] = height[0];
            for (int i = 1; i < n; i++) {
                left[i] = Math.max(left[i - 1], height[i]);
            }

            // Step 2: Fill right array (max height to the right of each index)
            right[n - 1] = height[n - 1];
            for (int i = n - 2; i >= 0; i--) {
                right[i] = Math.max(right[i + 1], height[i]);
            }

            // Step 3: Calculate trapped water
            for (int i = 0; i < n; i++) {
                totalWater += Math.min(left[i], right[i]) - height[i];
            }

            return totalWater;
        }

        // Example usage
        public static void main(String[] args) {
            int[] height = {0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1};
            System.out.println("Total trapped water: " + trap(height)); // Output: 6
        }
    }


}