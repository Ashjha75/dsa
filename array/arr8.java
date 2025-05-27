package array;

//find missing number
public class arr8 {
    public static void main(String[] args) {
        int[] arr = {1, 2, 0, 3, 6, 4};

        System.out.println(missingNumber(arr));

    }

    public static int missingNumber(int[] nums) {
        int N = nums.length;
        int totalSum = N * (N + 1) / 2;
        int sum = 0;
        for (int i = 0; i < N; i++) {
            sum += nums[i];
        }
        return (totalSum - sum);
    }
}
