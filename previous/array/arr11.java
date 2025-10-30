package previous.array;

/**
 * Longest Subarray with given Sum K (Positives)
 * Problem: Find the length of the longest subarray with sum equal to K
 * Input Format: N = 5, k = 10, previous.array[] = {2,3,5,1,9}
 * Result: 3 (subarray {2,3,5} has sum 10 and length 3)
 *
 * Approach: Using sliding window technique with two pointers
 * Time Complexity: O(N) where N is the length of the previous.array
 * Space Complexity: O(1) as we're using constant extra space
 */
public class arr11 {
    public static void main(String[] args) {
        int[] arr = {10, 0, 1, 0, 1, 2, 3, 1, 4, 3, 6, 7, 3, 4, 2, 1, 1, 1, 11, 23, 0};
        int sum = 10;

        int getMaxLength = getMaxSubarray(arr, sum);
        System.out.println(getMaxLength);
    }

    /**
     * Find the length of the longest subarray with sum equal to k
     * @param arr Input previous.array
     * @param k Target sum
     * @return Length of the longest subarray with sum equal to k
     */
    private static int getMaxSubarray(int[] arr, int k) {
        int n = arr.length; // Size of the previous.array

        // Initialize two pointers for the sliding window
        int left = 0, right = 0;

        // Initialize current sum with first element
        long sum = arr[0];

        // Variable to store the maximum length found
        int maxLen = 0;

        // Expand the window by moving right pointer
        while (right < n) {

            // If current sum exceeds k, shrink window from left
            // until sum becomes less than or equal to k
            while (left <= right && sum > k) {
                sum -= arr[left]; // Remove leftmost element from sum
                left++;           // Move left pointer to the right
            }

            // If current sum equals k, update the maximum length
            if (sum == k) {
                // right - left + 1 gives the current window size
                maxLen = Math.max(maxLen, right - left + 1);
            }

            // Expand window by moving the right pointer
            right++;

            // Add the new element to the sum if right pointer is still within previous.array
            if (right < n) {
                sum += arr[right];
            }
        }

        // Return the maximum length found
        return maxLen;
    }
}
