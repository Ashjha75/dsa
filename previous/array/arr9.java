package previous.array;

public class arr9 {
    public static void main(String[] args) {
        int[] arr = {0, 0, 0, 0, 0, 0, 0, 1};
        int maxConsecutiveCount = findMaxConsecutiveOnes(arr);
        System.out.println(maxConsecutiveCount);
    }

    public static int findMaxConsecutiveOnes(int[] nums) {
        int maxConsecutiveCount = 0;
        int consecutiveCount = 0;
        for (int num : nums) {
            if (num == 1) {
                consecutiveCount++;
                if (consecutiveCount > maxConsecutiveCount) {
                    maxConsecutiveCount = consecutiveCount;
                }
            } else {
                consecutiveCount = 0;
            }
        }
        return maxConsecutiveCount;
    }
}