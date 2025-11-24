<<<<<<< HEAD
package array;

import java.util.*;

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
        printFrequencies(array);
        int trapSumOptimized = trapOptimized(new int[]{4, -1, -2, 5, 3, -6, 2, 4, -3, 2, 3, -2, 3});
        int maxones = maxones(array);
        int[] moveZerosToEnd = moveZerosToEnd(array);
        int findMinSubarraySum = findMinSubarraySum(array);
        printMinimumSumSubArray(array);
        int maxProfit = maxProfit(array);
        boolean hasZeroSumSubarray = hasZeroSumSubarray(array);
        int maxIndexDiff = maxIndexDiff(new int[]{4, -1, -2, 5, 3, -6, 2, 4, -3, 2, 3, -2, 3});
        int[] twoSumSorted= twoSumSorted(new int[]{1,2,4,6,7,9,12,90},10);


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
        System.out.println("-  Trapping rain water Optimized: " + trapSumOptimized);
        System.out.println("-  Max length of subarray with ones: " + maxones);
        System.out.println("- Move Zeros to end: " + Arrays.toString(moveZerosToEnd));
        System.out.println("-  Min Sum Is: " + findMinSubarraySum);
        System.out.println("-  Max profit is: " + maxProfit);
        System.out.println("-  Is there any sum with zero: " + hasZeroSumSubarray);
        System.out.println("-  Max index Differnec is : " + maxIndexDiff);
        System.out.println("-  Two Sum sorted : " + Arrays.toString(twoSumSorted));


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


    /**
     * Problem: Print Frequency of Elements in a Sorted Array.
     * <p>
     * Given a sorted array of integers, print each unique element along with
     * its frequency of occurrence. The array is assumed to be sorted in
     * non-decreasing order.
     * </p>
     *
     * <p><b>Example:</b></p>
     * <pre>
     * Input:  [1, 1, 2, 2, 2, 3, 4, 4]
     * Output:
     * 1 → 2
     * 2 → 3
     * 3 → 1
     * 4 → 2
     * </pre>
     *
     * <p><b>Time Complexity:</b> O(n)<br>
     * <b>Space Complexity:</b> O(1)</p>
     *
     * @param array the sorted input array
     */
    public static void printFrequencies(int[] array) {

        if (array == null || array.length == 0) {
            throw new IllegalArgumentException("Array cannot be null or empty");
        }

        int count = 1;

        for (int i = 1; i < array.length; i++) {
            if (array[i] == array[i - 1]) {
                count++;
            } else {
                System.out.println(array[i - 1] + " → " + count);
                count = 1;
            }
        }

        // Print frequency of the last element
        System.out.println(array[array.length - 1] + " → " + count);
    }

    /**
     * Problem: Maximum Consecutive 1s in a Binary Array.
     * <p>
     * Given a binary array (containing only 0s and 1s), find the length of the
     * longest contiguous subarray consisting entirely of 1s.
     * </p>
     *
     * <p><b>Example:</b></p>
     * <pre>
     * Input:  [1, 1, 0, 1, 1, 1]
     * Output: 3
     * </pre>
     *
     * <p><b>Follow-up:</b> If allowed to flip at most one 0 → 1, find the maximum
     * possible length of consecutive 1s.</p>
     *
     * <p><b>Time Complexity:</b> O(n)<br>
     * <b>Space Complexity:</b> O(1)</p>
     */
    public static int maxones(int[] array) {
        if (array == null || array.length == 0) {
            throw new IllegalArgumentException("Array cannot be null or empty");
        }
        int max = 0;
        int state = 0;
        for (int i = 1; i < array.length; i++) {
            if (array[i] == 1) {
                state++;
                max = Math.max(state, max);

            } else {
                state = 0;
            }
        }
        return max;
    }

    /**
     * Problem: Move All Zeros to the End of the Array.
     * <p>
     * Given an integer array, move all zeros to the end while maintaining
     * the relative order of the non-zero elements. The operation must be done
     * in-place (without using extra arrays).
     * </p>
     *
     * <p><b>Example:</b></p>
     * <pre>
     * Input:  [0, 1, 0, 3, 12]
     * Output: [1, 3, 12, 0, 0]
     * </pre>
     *
     * <p><b>Constraints:</b></p>
     * <ul>
     *   <li>1 ≤ array.length ≤ 10⁵</li>
     *   <li>-10⁹ ≤ array[i] ≤ 10⁹</li>
     * </ul>
     *
     * <p><b>Time Complexity:</b> O(n)<br>
     * <b>Space Complexity:</b> O(1)</p>
     */
    public static int[] moveZerosToEnd(int[] array) {
        array = new int[]{8, 0, 1, 3, 0, 0, 5};
        if (array == null || array.length == 0) {
            throw new IllegalArgumentException("Array cannot be null or empty");
        }
        int pointer = 0;
        for (int i = 0; i < array.length; i++) {
            if (array[i] != 0) {
                array[pointer++] = array[i];
            }
        }
        while (pointer < array.length) {
            array[pointer++] = 0;
        }
        return array;
    }


    /**
     * Problem: Minimum Sum Subarray.
     * <p>
     * Given an integer array, find the contiguous subarray (containing at least one element)
     * which has the smallest possible sum, and return that sum.
     * </p>
     *
     * <p><b>Example 1:</b></p>
     * <pre>
     * Input:  [-2, 1, -3, 4, -1, 2, 1, -5, 4]
     * Output: -6
     * Explanation: The subarray [-2, 1, -3, 4, -1, 2, 1, -5, 4] has a minimum-sum subarray [-5, 4, -1, 2, 1] = -6.
     * </pre>
     *
     * <p><b>Example 2:</b></p>
     * <pre>
     * Input:  [1, 2, 3, 4]
     * Output: 1
     * Explanation: The smallest subarray is just [1].
     * </pre>
     *
     * <p><b>Approach:</b></p>
     * <ul>
     *   <li>Use a variation of Kadane's algorithm to track the current and minimum sums.</li>
     *   <li>At each step, update the current sum as the minimum of the current element
     *   or the current element added to the previous current sum.</li>
     *   <li>Track the overall minimum sum seen so far.</li>
     * </ul>
     *
     * <p><b>Time Complexity:</b> O(n)<br>
     * <b>Space Complexity:</b> O(1)</p>
     */
    public static int findMinSubarraySum(int[] array) {
        if (array == null || array.length == 0) {
            throw new IllegalArgumentException("Array cannot be null or empty");
        }

        int currentSum = array[0];
        int minSum = array[0];

        for (int i = 1; i < array.length; i++) {
            currentSum = Math.min(array[i], currentSum + array[i]);
            minSum = Math.min(minSum, currentSum);
        }

        return minSum;
    }

    /**
     * Problem: Print Elements of the Minimum Sum Subarray (Kadane’s Variation)
     * <p>
     * Given an integer array, find and print the contiguous subarray (containing at least one element)
     * which has the smallest possible sum.
     * </p>
     *
     * <p><b>Example:</b></p>
     * <pre>
     * Input:  [4, -1, -2, 5, 3, -6, 2, 4, -3, 2, 3, -2, 3]
     * Output:
     * Minimum Sum: -6
     * Subarray: [-6]
     * </pre>
     */
    public static void printMinimumSumSubArray(int[] array) {
        if (array == null || array.length == 0) {
            throw new IllegalArgumentException("Array cannot be null or empty");
        }

        int currentSum = array[0];
        int minSum = array[0];

        int start = 0;
        int end = 0;
        int tempStart = 0;

        for (int i = 1; i < array.length; i++) {
            // Decide whether to start a new subarray or continue
            if (array[i] < currentSum + array[i]) {
                currentSum = array[i];
                tempStart = i;
            } else {
                currentSum += array[i];
            }

            // Update global minimum
            if (currentSum < minSum) {
                minSum = currentSum;
                start = tempStart;
                end = i;
            }
        }

        // Print the subarray
        System.out.print("Minimum Sum Subarray: [");
        for (int i = start; i <= end; i++) {
            System.out.print(array[i] + (i < end ? ", " : ""));
        }
        System.out.println("]");
    }

    /**
     * Problem: Best Time to Buy and Sell Stock
     * <p>
     * You are given an array {@code prices}, where {@code prices[i]} represents the stock price on the {@code i-th} day.
     * Find the maximum profit you can achieve by buying and selling the stock once.
     * </p>
     *
     * <p><b>Example:</b></p>
     * <pre>
     * Input:  [7, 1, 5, 3, 6, 4]
     * Output: 5
     * Explanation:
     * Buy on day 2 (price = 1), sell on day 5 (price = 6), profit = 6 - 1 = 5
     * </pre>
     *
     * <p><b>Approaches:</b></p>
     * <ul>
     *   <li><b>1st  Brute Force (O(n²)):</b> Try every pair of (buy, sell) days and find the max profit.
     *       - Two nested loops → inefficient for large inputs.</li>
     *   <li><b>2nd Optimized with Right-Max Array (O(n)): </b> Precompute the max price to the right for each day,
     *       then compute potential profits by subtracting the current price from that right max.</li>
     *   <li><b>3rd Most Optimal Single Pass (O(n)): </b> Track the minimum price so far and compute profit at each step
     *       as {@code prices[i] - minPrice}. Update max profit dynamically.</li>
     * </ul>
     *
     * <p><b>Time Complexity:</b> O(n)<br>
     * <b>Space Complexity:</b> O(1)</p>
     *
     * @param prices array of stock prices
     * @return maximum achievable profit from one buy-sell transaction
     * @throws IllegalArgumentException if prices array is null or empty
     */
    public static int maxProfit(int[] prices) {
        if (prices == null || prices.length == 0) {
            throw new IllegalArgumentException("Array cannot be null or empty");
        }

        // Track the minimum price (best buy so far)
        int minPrice = prices[0];
        // Track the maximum profit achievable
        int maxProfit = 0;

        // Iterate through all prices
        for (int i = 1; i < prices.length; i++) {

            // Update minimum price if a lower price is found
            if (prices[i] < minPrice) {
                minPrice = prices[i];
            }

            // Calculate potential profit if selling at current price
            int profit = prices[i] - minPrice;

            // Update max profit if current profit is higher
            if (profit > maxProfit) {
                maxProfit = profit;
            }
        }

        return maxProfit;
    }

    /**
     * Problem: Subarray With Zero Sum
     * <p>
     * You are given an integer array {@code arr}. Determine whether it contains
     * any **non-empty** subarray whose elements sum to zero.
     *
     * <p><b>Example:</b></p>
     * <pre>
     * Input:  [4, 2, -6, 1]
     * Output: true
     * Explanation:
     * Subarray [4, 2, -6] has sum = 0.
     * </pre>
     *
     * <p><b>Approaches:</b></p>
     * <ul>
     *   <li><b>1st Brute Force (O(n²)):</b> Check all subarray sums using nested loops.
     *       - Inefficient for large inputs.</li>
     *
     *   <li><b>2nd Optimized Using Prefix Sum + HashSet (O(n)):</b>
     *       - Maintain a running prefix sum.
     *       - If the prefix sum becomes zero or repeats, it means there exists
     *         a subarray whose sum is zero.</li>
     * </ul>
     *
     * <p><b>Key Idea (Optimal Approach):</b><br>
     * If the cumulative sum repeats at two different indices, the elements between them sum to zero.
     * </p>
     *
     * <p><b>Time Complexity:</b> O(n)<br>
     * <b>Space Complexity:</b> O(n) for storing prefix sums</p>
     *
     * @param arr the input array of integers
     * @return {@code true} if a zero-sum subarray exists, otherwise {@code false}
     * @throws IllegalArgumentException if the input array is null or empty
     */
    public static boolean hasZeroSumSubarray(int[] arr) {
        if (arr == null || arr.length == 0) {
            throw new IllegalArgumentException("Array cannot be null or empty");
        }
        int sum = 0;

        Set<Integer> set = new HashSet<>();
        for (int value : arr) {
            sum += value;
            if (sum == 0 || set.contains(sum)) {
                return true;
            }
            set.add(sum);
        }

        return false;
    }

    /**
     * Problem: Trapping Rain Water (Space Optimized)
     * <p>
     * Given an array {@code height} where each element represents the height of a bar,
     * compute how much total water can be trapped after raining.
     *
     * <p><b>Key Insight:</b><br>
     * Water above index {@code i} is determined by:
     * {@code min(maxLeft, maxRight) - height[i]}.
     *
     * <p><b>Approaches:</b></p>
     * <ul>
     *   <li><b>1. Brute Force (O(n²)):</b> For each index, scan left and right to find boundaries.</li>
     *   <li><b>2. Prefix Max Arrays (O(n) time, O(n) space):</b>
     *       Precompute leftMax[] and rightMax[].</li>
     *   <li><b>3. Two Pointer Method (O(n) time, O(1) extra space) — Recommended:</b>
     *       Maintain pointers {@code left} and {@code right}, and dynamic
     *       boundary trackers {@code leftMax} and {@code rightMax}.
     *       Move the pointer with the smaller boundary inward.</li>
     * </ul>
     *
     * <p><b>Time Complexity:</b> O(n)<br>
     * <b>Space Complexity:</b> O(1)</p>
     *
     * <p><b>Example:</b></p>
     * <pre>
     * Input:  height = [4, 2, 0, 3, 2, 5]
     * Output: 9
     * Explanation: Water trapped = 2 + 4 + 1 + 2 = 9
     * </pre>
     *
     * @param height non-negative heights of bars
     * @return total units of trapped water
     * @throws IllegalArgumentException if height array is null or empty
     */
    public static int trapOptimized(int[] height) {
        if (height == null || height.length == 0) {
            throw new IllegalArgumentException("Heights cannot be null or empty");
        }

        int left = 0;
        int right = height.length - 1;
        int leftMax = 0;
        int rightMax = 0;
        int ans = 0;

        while (left <= right) {

            if (leftMax <= rightMax) {
                // left side is bounded
                if (height[left] < leftMax) {
                    ans += leftMax - height[left];
                } else {
                    leftMax = height[left];
                }
                left++;
            } else {
                // right side is bounded
                if (height[right] < rightMax) {
                    ans += rightMax - height[right];
                } else {
                    rightMax = height[right];
                }
                right--;
            }
        }

        return ans;
    }

    /**
     * Problem: Maximum Index Difference (j - i) such that arr[j] > arr[i]
     *
     * <p>You are given an integer array {@code arr}. Find the maximum possible value of
     * {@code (j - i)} such that:
     *
     * <pre>
     * i < j  AND  arr[j] > arr[i]
     * </pre>
     *
     * <p><b>Example:</b></p>
     * <pre>
     * Input:  arr = [3, 5, 4, 2, 6]
     * Valid pairs (i, j) where arr[j] > arr[i]:
     * (0,1), (0,2), (0,4), (2,4), (3,4)
     *
     * Maximum j-i is (0,4) → answer = 4
     * </pre>
     *
     * <p><b>Approaches:</b></p>
     * <ul>
     *   <li><b>1. Brute Force (O(n²)):</b>
     *       Check all pairs (i, j) with i < j. Very slow for large inputs.</li>
     *
     *   <li><b>2. Precompute + Two Pointer (O(n)) — Recommended:</b>
     *     <ul>
     *       <li>Create {@code leftMin[]} where {@code leftMin[i]} is the minimum value from index 0 to i.</li>
     *       <li>Create {@code rightMax[]} where {@code rightMax[j]} is the maximum value from index j to end.</li>
     *       <li>Use two pointers (i = 0, j = 0). If {@code leftMin[i] < rightMax[j]}, move j and update result.
     *           Else move i.</li>
     *     </ul>
     *     This avoids unnecessary comparisons and solves efficiently.</li>
     * </ul>
     *
     * <p><b>Time Complexity:</b> O(n)<br>
     * <b>Space Complexity:</b> O(n) (due to helper arrays)</p>
     *
     * @param arr input array
     * @return maximum difference (j - i) satisfying arr[j] > arr[i], or 0 if no such pair exists
     * @throws IllegalArgumentException if {@code arr} is null or empty
     */
    public static int maxIndexDiff(int[] arr) {
        if (arr == null || arr.length == 0) {
            throw new IllegalArgumentException("Array cannot be null or empty");
        }
        int n = arr.length;

        int[] leftMin = new int[n];
        int[] rightMax = new int[n];

        leftMin[0] = arr[0];
        for (int i = 1; i < n; i++) leftMin[i] = Math.min(leftMin[i - 1], arr[i]);

        rightMax[n - 1] = arr[n - 1];
        for (int i = n - 2; i >= 0; i--) rightMax[i] = Math.max(rightMax[i + 1], arr[i]);

        int ans = 0;
        int i = 0;
        int j = 0;
        while (i < n && j < n) {
            if (leftMin[i] < rightMax[j]) {   // strict '>' condition for arr[j] > arr[i]
                ans = Math.max(ans, j - i);
                j++;
            } else {
                i++;
            }
        }
        return ans;
    }

    /**
     * Problem: Two Sum in a Sorted Array
     * <p>
     * You are given a sorted integer array {@code nums} (sorted in non-decreasing order)
     * and a target value {@code target}. Find two numbers such that:
     *
     * <pre>
     * nums[left] + nums[right] == target
     * </pre>
     * <p>
     * Return their indices (or the numbers themselves depending on requirement).
     *
     * <p><b>Approaches:</b></p>
     * <ul>
     *
     *   <li><b>1. Brute Force (O(n²)):</b>
     *       Check every pair. Not efficient.</li>
     *
     *   <li><b>2. Two-Pointer Method (O(n)) — Recommended for Sorted Arrays:</b>
     *     <ul>
     *       <li>Initialize two pointers:
     *           <pre>left = 0, right = nums.length - 1</pre></li>
     *       <li>If {@code nums[left] + nums[right] == target} → solution found.</li>
     *       <li>If the sum is too small → increase {@code left} (need bigger sum).</li>
     *       <li>If the sum is too large → decrease {@code right} (need smaller sum).</li>
     *     </ul>
     *   </li>
     *
     * </ul>
     *
     * <p><b>Time Complexity:</b> O(n)<br>
     * <b>Space Complexity:</b> O(1)</p>
     *
     * <p><b>Example:</b></p>
     * <pre>
     * nums = [1, 2, 3, 4, 6], target = 6
     * Output: [1, 3]  // because nums[1] + nums[3] = 2 + 4 = 6
     * </pre>
     *
     * @param nums   sorted array of integers
     * @param target the required sum
     * @return array {@code [leftIndex, rightIndex]} if found, otherwise {@code [-1, -1]}
     * @throws IllegalArgumentException if nums is null or empty
     */
    public static int[] twoSumSorted(int[] nums, int target) {

        if (nums == null || nums.length == 0) {
            throw new IllegalArgumentException("Array cannot be null or empty");
        }
        int l = 0;
        int r = nums.length - 1;
        while (l < r) {
            if (nums[l] + nums[r] == target) {
                return new int[]{l, r };
            }else if (nums[l] + nums[r] < target) {
                l++;
            }
            else{
                r--;
            }
        }
        return new int[]{-1, -1};
    }

    /**
     * Problem: 3-Sum Equal to Target (Unsorted Array)
     *
     * Given an integer array {@code nums} and a target value {@code target},
     * find all unique triplets {@code (i, j, k)} such that:
     *
     * <pre>
     * nums[i] + nums[j] + nums[k] == target
     * i, j, k are all different indices
     * </pre>
     *
     * <p><b>Approach (Optimal O(n²)):</b></p>
     * <ul>
     *   <li>Sort the array first.</li>
     *   <li>Fix one index {@code i} in a loop.</li>
     *   <li>Use two-pointer search on the remaining subarray to find
     *       pairs that sum to {@code target - nums[i]}.</li>
     *   <li>Skip duplicates for {@code i}, {@code left}, and {@code right}
     *       to avoid duplicate triplets in the result.</li>
     * </ul>
     *
     * <p><b>Why This Works:</b><br>
     * Sorting allows efficient elimination of duplicates and enables
     * a two-pointer sweep to find valid pairs in linear time per fix.</p>
     *
     * <p><b>Time Complexity:</b> O(n²)<br>
     * <b>Space Complexity:</b> O(1) extra (excluding result list)</p>
     *
     * <p><b>Example:</b></p>
     * <pre>
     * nums = [2, 3, 4, 1, 6, -1], target = 8
     * Output: [[2, 3, 3], [1, 2, 5], ...] (actual output depends on unique values)
     * </pre>
     *
     * @param nums input unsorted integer array
     * @param target the desired sum of the triplet
     * @return list of unique triplets where the sum equals target
     * @throws IllegalArgumentException if nums is null or has length < 3
     */
    public static List<List<Integer>> threeSumTarget(int[] nums, int target) {

        if(nums == null || nums.length < 3){
            throw new IllegalArgumentException("Array cannot be null or empty");
        }
        Arrays.sort(nums);
        return Collections.emptyList();
    }

}
=======
package array;

import java.util.*;

public class Basics {

	public static void main(String[] args) {
		int[] array = new int[] { 4, -1, -2, 5, 3, -6, 2, 4, -3, 2, 3, -2, 3 };
		int index = customSearch(array, 11);
		int[] updatedArray = insertElement(array, 27, 3);
		int[] deletedArray = deleteElement(array, 6);
		int maxNumber = findMax(array);
		int secondMaxNumber = findSecondMax(array);
		int thirdLargest = thirdLargest(array);
		int[] removedDuplicatesArray = removeDuplicatesFromSorted(
			new int[] { 1, 2, 2, 3, 4, 5, 5, 5, 6, 7, 7 }
		);
		int smallestNumber = findSmallest(array);
		boolean isArraySorted = isArraySorted(array);
		int[] reverseArray = reverseArray(array);
		int[] replaceWithMax = replaceWithMax(array);
		int[] leadersInArray = leadersInArray(array);
		int findMaxSum = findMaxSum(array);
		int containerWithMaxWater = containerWithMaxWater(array);
		int trapSum = trap(array);
		printFrequencies(array);
		int trapSumOptimized = trapOptimized(
			new int[] { 4, -1, -2, 5, 3, -6, 2, 4, -3, 2, 3, -2, 3 }
		);
		int maxones = maxones(array);
		int[] moveZerosToEnd = moveZerosToEnd(array);
		int findMinSubarraySum = findMinSubarraySum(array);
		printMinimumSumSubArray(array);
		int maxProfit = maxProfit(array);
		boolean hasZeroSumSubarray = hasZeroSumSubarray(array);
		int maxIndexDiff = maxIndexDiff(
			new int[] { 4, -1, -2, 5, 3, -6, 2, 4, -3, 2, 3, -2, 3 }
		);
		int[] twoSumSorted = twoSumSorted(
			new int[] { 1, 2, 4, 6, 7, 9, 12, 90 },
			10
		);

		System.out.println(
			"|||||----------------------------------------------------------------------------------------------------|||||"
		);
		System.out.println("- Index of 11: " + index);
		System.out.println(
			"- After insertion: " + Arrays.toString(updatedArray)
		);
		System.out.println(
			"- After deletion: " + Arrays.toString(deletedArray)
		);
		System.out.println("- Maximum number: " + maxNumber);
		System.out.println("- Second maximum number: " + secondMaxNumber);
		System.out.println("- Third largest number: " + thirdLargest);
		System.out.println(
			"- Duplicates removed from sorted array: " +
				Arrays.toString(removedDuplicatesArray)
		);
		System.out.println("- Smallest number: " + smallestNumber);
		System.out.println("- Is Array sorted: " + isArraySorted);
		System.out.println(
			"- Reversed Array: " + Arrays.toString(reverseArray)
		);
		System.out.println(
			"- Replace with max Array: " + Arrays.toString(replaceWithMax)
		);
		System.out.println(
			"- Leaders in Array: " + Arrays.toString(leadersInArray)
		);
		System.out.println("-  Max Sum Is: " + findMaxSum);
		System.out.println(
			"-  Container with max water: " + containerWithMaxWater
		);
		System.out.println("-  Trapping rain water: " + trapSum);
		System.out.println(
			"-  Trapping rain water Optimized: " + trapSumOptimized
		);
		System.out.println("-  Max length of subarray with ones: " + maxones);
		System.out.println(
			"- Move Zeros to end: " + Arrays.toString(moveZerosToEnd)
		);
		System.out.println("-  Min Sum Is: " + findMinSubarraySum);
		System.out.println("-  Max profit is: " + maxProfit);
		System.out.println(
			"-  Is there any sum with zero: " + hasZeroSumSubarray
		);
		System.out.println("-  Max index Differnec is : " + maxIndexDiff);
		System.out.println(
			"-  Two Sum sorted : " + Arrays.toString(twoSumSorted)
		);

		System.out.println(
			"|||||----------------------------------------------------------------------------------------------------|||||"
		);
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
			throw new IllegalArgumentException(
				"Array must have at least one element"
			);
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
			throw new IllegalArgumentException(
				"Array must have at least two elements"
			);
		}

		int max = Integer.MIN_VALUE;
		int secondMax = Integer.MIN_VALUE;

		for (int num : array) {
			if (num > max) {
				secondMax = max;
				max = num;
			} else if (num > secondMax && num != max) {
				// FIXED: Changed from num < max to num != max
				secondMax = num;
			}
		}

		if (secondMax == Integer.MIN_VALUE) {
			throw new IllegalStateException(
				"No distinct second maximum found (all elements equal or less than two distinct values)"
			);
		}

		return secondMax;
	}

	/**
	 * Finds the third largest distinct value in the array.
	 */
	public static int thirdLargest(int[] array) {
		if (array == null || array.length < 3) {
			throw new IllegalArgumentException(
				"Array must have at least three elements"
			);
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
			} else if (
				ele > thirdLargest && ele != secondLargest && ele != largest
			) {
				thirdLargest = ele;
			}
		}

		if (thirdLargest == Integer.MIN_VALUE) {
			throw new IllegalStateException(
				"Less than three distinct elements in array"
			);
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
			throw new IllegalArgumentException(
				"Array must have at least one element"
			);
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
			if (array[i - 1] > array[i]) {
				// If any pair is out of order
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

	/**
	 * Problem: Print Frequency of Elements in a Sorted Array.
	 * <p>
	 * Given a sorted array of integers, print each unique element along with
	 * its frequency of occurrence. The array is assumed to be sorted in
	 * non-decreasing order.
	 * </p>
	 *
	 * <p><b>Example:</b></p>
	 * <pre>
	 * Input:  [1, 1, 2, 2, 2, 3, 4, 4]
	 * Output:
	 * 1 → 2
	 * 2 → 3
	 * 3 → 1
	 * 4 → 2
	 * </pre>
	 *
	 * <p><b>Time Complexity:</b> O(n)<br>
	 * <b>Space Complexity:</b> O(1)</p>
	 *
	 * @param array the sorted input array
	 */
	public static void printFrequencies(int[] array) {
		if (array == null || array.length == 0) {
			throw new IllegalArgumentException("Array cannot be null or empty");
		}

		int count = 1;

		for (int i = 1; i < array.length; i++) {
			if (array[i] == array[i - 1]) {
				count++;
			} else {
				System.out.println(array[i - 1] + " → " + count);
				count = 1;
			}
		}

		// Print frequency of the last element
		System.out.println(array[array.length - 1] + " → " + count);
	}

	/**
	 * Problem: Maximum Consecutive 1s in a Binary Array.
	 * <p>
	 * Given a binary array (containing only 0s and 1s), find the length of the
	 * longest contiguous subarray consisting entirely of 1s.
	 * </p>
	 *
	 * <p><b>Example:</b></p>
	 * <pre>
	 * Input:  [1, 1, 0, 1, 1, 1]
	 * Output: 3
	 * </pre>
	 *
	 * <p><b>Follow-up:</b> If allowed to flip at most one 0 → 1, find the maximum
	 * possible length of consecutive 1s.</p>
	 *
	 * <p><b>Time Complexity:</b> O(n)<br>
	 * <b>Space Complexity:</b> O(1)</p>
	 */
	public static int maxones(int[] array) {
		if (array == null || array.length == 0) {
			throw new IllegalArgumentException("Array cannot be null or empty");
		}
		int max = 0;
		int state = 0;
		for (int i = 1; i < array.length; i++) {
			if (array[i] == 1) {
				state++;
				max = Math.max(state, max);
			} else {
				state = 0;
			}
		}
		return max;
	}

	/**
	 * Problem: Move All Zeros to the End of the Array.
	 * <p>
	 * Given an integer array, move all zeros to the end while maintaining
	 * the relative order of the non-zero elements. The operation must be done
	 * in-place (without using extra arrays).
	 * </p>
	 *
	 * <p><b>Example:</b></p>
	 * <pre>
	 * Input:  [0, 1, 0, 3, 12]
	 * Output: [1, 3, 12, 0, 0]
	 * </pre>
	 *
	 * <p><b>Constraints:</b></p>
	 * <ul>
	 *   <li>1 ≤ array.length ≤ 10⁵</li>
	 *   <li>-10⁹ ≤ array[i] ≤ 10⁹</li>
	 * </ul>
	 *
	 * <p><b>Time Complexity:</b> O(n)<br>
	 * <b>Space Complexity:</b> O(1)</p>
	 */
	public static int[] moveZerosToEnd(int[] array) {
		array = new int[] { 8, 0, 1, 3, 0, 0, 5 };
		if (array == null || array.length == 0) {
			throw new IllegalArgumentException("Array cannot be null or empty");
		}
		int pointer = 0;
		for (int i = 0; i < array.length; i++) {
			if (array[i] != 0) {
				array[pointer++] = array[i];
			}
		}
		while (pointer < array.length) {
			array[pointer++] = 0;
		}
		return array;
	}

	/**
	 * Problem: Minimum Sum Subarray.
	 * <p>
	 * Given an integer array, find the contiguous subarray (containing at least one element)
	 * which has the smallest possible sum, and return that sum.
	 * </p>
	 *
	 * <p><b>Example 1:</b></p>
	 * <pre>
	 * Input:  [-2, 1, -3, 4, -1, 2, 1, -5, 4]
	 * Output: -6
	 * Explanation: The subarray [-2, 1, -3, 4, -1, 2, 1, -5, 4] has a minimum-sum subarray [-5, 4, -1, 2, 1] = -6.
	 * </pre>
	 *
	 * <p><b>Example 2:</b></p>
	 * <pre>
	 * Input:  [1, 2, 3, 4]
	 * Output: 1
	 * Explanation: The smallest subarray is just [1].
	 * </pre>
	 *
	 * <p><b>Approach:</b></p>
	 * <ul>
	 *   <li>Use a variation of Kadane's algorithm to track the current and minimum sums.</li>
	 *   <li>At each step, update the current sum as the minimum of the current element
	 *   or the current element added to the previous current sum.</li>
	 *   <li>Track the overall minimum sum seen so far.</li>
	 * </ul>
	 *
	 * <p><b>Time Complexity:</b> O(n)<br>
	 * <b>Space Complexity:</b> O(1)</p>
	 */
	public static int findMinSubarraySum(int[] array) {
		if (array == null || array.length == 0) {
			throw new IllegalArgumentException("Array cannot be null or empty");
		}

		int currentSum = array[0];
		int minSum = array[0];

		for (int i = 1; i < array.length; i++) {
			currentSum = Math.min(array[i], currentSum + array[i]);
			minSum = Math.min(minSum, currentSum);
		}

		return minSum;
	}

	/**
	 * Problem: Print Elements of the Minimum Sum Subarray (Kadane’s Variation)
	 * <p>
	 * Given an integer array, find and print the contiguous subarray (containing at least one element)
	 * which has the smallest possible sum.
	 * </p>
	 *
	 * <p><b>Example:</b></p>
	 * <pre>
	 * Input:  [4, -1, -2, 5, 3, -6, 2, 4, -3, 2, 3, -2, 3]
	 * Output:
	 * Minimum Sum: -6
	 * Subarray: [-6]
	 * </pre>
	 */
	public static void printMinimumSumSubArray(int[] array) {
		if (array == null || array.length == 0) {
			throw new IllegalArgumentException("Array cannot be null or empty");
		}

		int currentSum = array[0];
		int minSum = array[0];

		int start = 0;
		int end = 0;
		int tempStart = 0;

		for (int i = 1; i < array.length; i++) {
			// Decide whether to start a new subarray or continue
			if (array[i] < currentSum + array[i]) {
				currentSum = array[i];
				tempStart = i;
			} else {
				currentSum += array[i];
			}

			// Update global minimum
			if (currentSum < minSum) {
				minSum = currentSum;
				start = tempStart;
				end = i;
			}
		}

		// Print the subarray
		System.out.print("Minimum Sum Subarray: [");
		for (int i = start; i <= end; i++) {
			System.out.print(array[i] + (i < end ? ", " : ""));
		}
		System.out.println("]");
	}

	/**
	 * Problem: Best Time to Buy and Sell Stock
	 * <p>
	 * You are given an array {@code prices}, where {@code prices[i]} represents the stock price on the {@code i-th} day.
	 * Find the maximum profit you can achieve by buying and selling the stock once.
	 * </p>
	 *
	 * <p><b>Example:</b></p>
	 * <pre>
	 * Input:  [7, 1, 5, 3, 6, 4]
	 * Output: 5
	 * Explanation:
	 * Buy on day 2 (price = 1), sell on day 5 (price = 6), profit = 6 - 1 = 5
	 * </pre>
	 *
	 * <p><b>Approaches:</b></p>
	 * <ul>
	 *   <li><b>1st  Brute Force (O(n²)):</b> Try every pair of (buy, sell) days and find the max profit.
	 *       - Two nested loops → inefficient for large inputs.</li>
	 *   <li><b>2nd Optimized with Right-Max Array (O(n)): </b> Precompute the max price to the right for each day,
	 *       then compute potential profits by subtracting the current price from that right max.</li>
	 *   <li><b>3rd Most Optimal Single Pass (O(n)): </b> Track the minimum price so far and compute profit at each step
	 *       as {@code prices[i] - minPrice}. Update max profit dynamically.</li>
	 * </ul>
	 *
	 * <p><b>Time Complexity:</b> O(n)<br>
	 * <b>Space Complexity:</b> O(1)</p>
	 *
	 * @param prices array of stock prices
	 * @return maximum achievable profit from one buy-sell transaction
	 * @throws IllegalArgumentException if prices array is null or empty
	 */
	public static int maxProfit(int[] prices) {
		if (prices == null || prices.length == 0) {
			throw new IllegalArgumentException("Array cannot be null or empty");
		}

		// Track the minimum price (best buy so far)
		int minPrice = prices[0];
		// Track the maximum profit achievable
		int maxProfit = 0;

		// Iterate through all prices
		for (int i = 1; i < prices.length; i++) {
			// Update minimum price if a lower price is found
			if (prices[i] < minPrice) {
				minPrice = prices[i];
			}

			// Calculate potential profit if selling at current price
			int profit = prices[i] - minPrice;

			// Update max profit if current profit is higher
			if (profit > maxProfit) {
				maxProfit = profit;
			}
		}

		return maxProfit;
	}

	/**
	 * Problem: Subarray With Zero Sum
	 * <p>
	 * You are given an integer array {@code arr}. Determine whether it contains
	 * any **non-empty** subarray whose elements sum to zero.
	 *
	 * <p><b>Example:</b></p>
	 * <pre>
	 * Input:  [4, 2, -6, 1]
	 * Output: true
	 * Explanation:
	 * Subarray [4, 2, -6] has sum = 0.
	 * </pre>
	 *
	 * <p><b>Approaches:</b></p>
	 * <ul>
	 *   <li><b>1st Brute Force (O(n²)):</b> Check all subarray sums using nested loops.
	 *       - Inefficient for large inputs.</li>
	 *
	 *   <li><b>2nd Optimized Using Prefix Sum + HashSet (O(n)):</b>
	 *       - Maintain a running prefix sum.
	 *       - If the prefix sum becomes zero or repeats, it means there exists
	 *         a subarray whose sum is zero.</li>
	 * </ul>
	 *
	 * <p><b>Key Idea (Optimal Approach):</b><br>
	 * If the cumulative sum repeats at two different indices, the elements between them sum to zero.
	 * </p>
	 *
	 * <p><b>Time Complexity:</b> O(n)<br>
	 * <b>Space Complexity:</b> O(n) for storing prefix sums</p>
	 *
	 * @param arr the input array of integers
	 * @return {@code true} if a zero-sum subarray exists, otherwise {@code false}
	 * @throws IllegalArgumentException if the input array is null or empty
	 */
	public static boolean hasZeroSumSubarray(int[] arr) {
		if (arr == null || arr.length == 0) {
			throw new IllegalArgumentException("Array cannot be null or empty");
		}
		int sum = 0;

		Set<Integer> set = new HashSet<>();
		for (int value : arr) {
			sum += value;
			if (sum == 0 || set.contains(sum)) {
				return true;
			}
			set.add(sum);
		}

		return false;
	}

	/**
	 * Problem: Trapping Rain Water (Space Optimized)
	 * <p>
	 * Given an array {@code height} where each element represents the height of a bar,
	 * compute how much total water can be trapped after raining.
	 *
	 * <p><b>Key Insight:</b><br>
	 * Water above index {@code i} is determined by:
	 * {@code min(maxLeft, maxRight) - height[i]}.
	 *
	 * <p><b>Approaches:</b></p>
	 * <ul>
	 *   <li><b>1. Brute Force (O(n²)):</b> For each index, scan left and right to find boundaries.</li>
	 *   <li><b>2. Prefix Max Arrays (O(n) time, O(n) space):</b>
	 *       Precompute leftMax[] and rightMax[].</li>
	 *   <li><b>3. Two Pointer Method (O(n) time, O(1) extra space) — Recommended:</b>
	 *       Maintain pointers {@code left} and {@code right}, and dynamic
	 *       boundary trackers {@code leftMax} and {@code rightMax}.
	 *       Move the pointer with the smaller boundary inward.</li>
	 * </ul>
	 *
	 * <p><b>Time Complexity:</b> O(n)<br>
	 * <b>Space Complexity:</b> O(1)</p>
	 *
	 * <p><b>Example:</b></p>
	 * <pre>
	 * Input:  height = [4, 2, 0, 3, 2, 5]
	 * Output: 9
	 * Explanation: Water trapped = 2 + 4 + 1 + 2 = 9
	 * </pre>
	 *
	 * @param height non-negative heights of bars
	 * @return total units of trapped water
	 * @throws IllegalArgumentException if height array is null or empty
	 */
	public static int trapOptimized(int[] height) {
		if (height == null || height.length == 0) {
			throw new IllegalArgumentException(
				"Heights cannot be null or empty"
			);
		}

		int left = 0;
		int right = height.length - 1;
		int leftMax = 0;
		int rightMax = 0;
		int ans = 0;

		while (left <= right) {
			if (leftMax <= rightMax) {
				// left side is bounded
				if (height[left] < leftMax) {
					ans += leftMax - height[left];
				} else {
					leftMax = height[left];
				}
				left++;
			} else {
				// right side is bounded
				if (height[right] < rightMax) {
					ans += rightMax - height[right];
				} else {
					rightMax = height[right];
				}
				right--;
			}
		}

		return ans;
	}

	/**
	 * Problem: Maximum Index Difference (j - i) such that arr[j] > arr[i]
	 *
	 * <p>You are given an integer array {@code arr}. Find the maximum possible value of
	 * {@code (j - i)} such that:
	 *
	 * <pre>
	 * i < j  AND  arr[j] > arr[i]
	 * </pre>
	 *
	 * <p><b>Example:</b></p>
	 * <pre>
	 * Input:  arr = [3, 5, 4, 2, 6]
	 * Valid pairs (i, j) where arr[j] > arr[i]:
	 * (0,1), (0,2), (0,4), (2,4), (3,4)
	 *
	 * Maximum j-i is (0,4) → answer = 4
	 * </pre>
	 *
	 * <p><b>Approaches:</b></p>
	 * <ul>
	 *   <li><b>1. Brute Force (O(n²)):</b>
	 *       Check all pairs (i, j) with i < j. Very slow for large inputs.</li>
	 *
	 *   <li><b>2. Precompute + Two Pointer (O(n)) — Recommended:</b>
	 *     <ul>
	 *       <li>Create {@code leftMin[]} where {@code leftMin[i]} is the minimum value from index 0 to i.</li>
	 *       <li>Create {@code rightMax[]} where {@code rightMax[j]} is the maximum value from index j to end.</li>
	 *       <li>Use two pointers (i = 0, j = 0). If {@code leftMin[i] < rightMax[j]}, move j and update result.
	 *           Else move i.</li>
	 *     </ul>
	 *     This avoids unnecessary comparisons and solves efficiently.</li>
	 * </ul>
	 *
	 * <p><b>Time Complexity:</b> O(n)<br>
	 * <b>Space Complexity:</b> O(n) (due to helper arrays)</p>
	 *
	 * @param arr input array
	 * @return maximum difference (j - i) satisfying arr[j] > arr[i], or 0 if no such pair exists
	 * @throws IllegalArgumentException if {@code arr} is null or empty
	 */
	public static int maxIndexDiff(int[] arr) {
		if (arr == null || arr.length == 0) {
			throw new IllegalArgumentException("Array cannot be null or empty");
		}
		int n = arr.length;

		int[] leftMin = new int[n];
		int[] rightMax = new int[n];

		leftMin[0] = arr[0];
		for (int i = 1; i < n; i++) leftMin[i] = Math.min(
			leftMin[i - 1],
			arr[i]
		);

		rightMax[n - 1] = arr[n - 1];
		for (int i = n - 2; i >= 0; i--) rightMax[i] = Math.max(
			rightMax[i + 1],
			arr[i]
		);

		int ans = 0;
		int i = 0;
		int j = 0;
		while (i < n && j < n) {
			if (leftMin[i] < rightMax[j]) {
				// strict '>' condition for arr[j] > arr[i]
				ans = Math.max(ans, j - i);
				j++;
			} else {
				i++;
			}
		}
		return ans;
	}

	/**
	 * Problem: Two Sum in a Sorted Array
	 * <p>
	 * You are given a sorted integer array {@code nums} (sorted in non-decreasing order)
	 * and a target value {@code target}. Find two numbers such that:
	 *
	 * <pre>
	 * nums[left] + nums[right] == target
	 * </pre>
	 * <p>
	 * Return their indices (or the numbers themselves depending on requirement).
	 *
	 * <p><b>Approaches:</b></p>
	 * <ul>
	 *
	 *   <li><b>1. Brute Force (O(n²)):</b>
	 *       Check every pair. Not efficient.</li>
	 *
	 *   <li><b>2. Two-Pointer Method (O(n)) — Recommended for Sorted Arrays:</b>
	 *     <ul>
	 *       <li>Initialize two pointers:
	 *           <pre>left = 0, right = nums.length - 1</pre></li>
	 *       <li>If {@code nums[left] + nums[right] == target} → solution found.</li>
	 *       <li>If the sum is too small → increase {@code left} (need bigger sum).</li>
	 *       <li>If the sum is too large → decrease {@code right} (need smaller sum).</li>
	 *     </ul>
	 *   </li>
	 *
	 * </ul>
	 *
	 * <p><b>Time Complexity:</b> O(n)<br>
	 * <b>Space Complexity:</b> O(1)</p>
	 *
	 * <p><b>Example:</b></p>
	 * <pre>
	 * nums = [1, 2, 3, 4, 6], target = 6
	 * Output: [1, 3]  // because nums[1] + nums[3] = 2 + 4 = 6
	 * </pre>
	 *
	 * @param nums   sorted array of integers
	 * @param target the required sum
	 * @return array {@code [leftIndex, rightIndex]} if found, otherwise {@code [-1, -1]}
	 * @throws IllegalArgumentException if nums is null or empty
	 */
	public static int[] twoSumSorted(int[] nums, int target) {
		if (nums == null || nums.length == 0) {
			throw new IllegalArgumentException("Array cannot be null or empty");
		}
		int l = 0;
		int r = nums.length - 1;
		while (l < r) {
			if (nums[l] + nums[r] == target) {
				return new int[] { l, r };
			} else if (nums[l] + nums[r] < target) {
				l++;
			} else {
				r--;
			}
		}
		return new int[] { -1, -1 };
	}

	/**
	 * Problem: 3-Sum Equal to Target (Unsorted Array)
	 *
	 * Given an integer array {@code nums} and a target value {@code target},
	 * find all unique triplets {@code (i, j, k)} such that:
	 *
	 * <pre>
	 * nums[i] + nums[j] + nums[k] == target
	 * i, j, k are all different indices
	 * </pre>
	 *
	 * <p><b>Approach (Optimal O(n²)):</b></p>
	 * <ul>
	 *   <li>Sort the array first.</li>
	 *   <li>Fix one index {@code i} in a loop.</li>
	 *   <li>Use two-pointer search on the remaining subarray to find
	 *       pairs that sum to {@code target - nums[i]}.</li>
	 *   <li>Skip duplicates for {@code i}, {@code left}, and {@code right}
	 *       to avoid duplicate triplets in the result.</li>
	 * </ul>
	 *
	 * <p><b>Why This Works:</b><br>
	 * Sorting allows efficient elimination of duplicates and enables
	 * a two-pointer sweep to find valid pairs in linear time per fix.</p>
	 *
	 * <p><b>Time Complexity:</b> O(n²)<br>
	 * <b>Space Complexity:</b> O(1) extra (excluding result list)</p>
	 *
	 * <p><b>Example:</b></p>
	 * <pre>
	 * nums = [2, 3, 4, 1, 6, -1], target = 8
	 * Output: [[2, 3, 3], [1, 2, 5], ...] (actual output depends on unique values)
	 * </pre>
	 *
	 * @param nums input unsorted integer array
	 * @param target the desired sum of the triplet
	 * @return list of unique triplets where the sum equals target
	 * @throws IllegalArgumentException if nums is null or has length < 3
	 */
	public static List<List<Integer>> threeSumTarget(int[] nums, int target) {
		if (nums == null || nums.length < 3) {
			throw new IllegalArgumentException("Array cannot be null or empty");
		}
		Arrays.sort(nums);
		return Collections.emptyList();
	}
}
>>>>>>> 738865d82568c1febe06f1bc9211220684560f8e
