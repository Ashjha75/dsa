package array;

//Longest Subarray with given Sum K(Positives)
//Input Format: N = 5, k = 10, array[] = {2,3,5,1,9}
//Result: 3
public class arr11 {
    public static void main(String[] args) {
        int[] arr = {10, 0, 1, 0, 1, 2, 3, 1, 4, 3, 6, 7, 3, 4, 2, 1, 1, 1, 11, 23, 0};
        int sum = 10;

        int getMaxLength = getMaxSubarray(arr, sum);
        System.out.println(getMaxLength);
    }

    private static int getMaxSubarray(int[] arr, int k) {
        int n = arr.length; // size of the array.

        int left = 0, right = 0; // 2 pointers
        long sum = arr[0];
        int maxLen = 0;
        while (right < n) {
            // if sum > k, reduce the subarray from left
            // until sum becomes less or equal to k:
            while (left <= right && sum > k) {
                sum -= arr[left];
                left++;
            }

            // if sum = k, update the maxLen i.e. answer:
            if (sum == k) {
                maxLen = Math.max(maxLen, right - left + 1);
            }

            // Move forward thw right pointer:
            right++;
            if (right < n) sum += arr[right];
        }

        return maxLen;
    }
}
