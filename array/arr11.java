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

    private static int getMaxSubarray(int[] arr, int sum) {
//        we will take 2 pointer approach
        int left = 0;
        int right = 0;
        int maxLength = 0;
        int currentSum = arr[0];
        while (right < arr.length) {
            currentSum += arr[right];
        }
        return currentSum;
    }
}
