package array;

public class arr1 {
    public static void main(String[] args) {
        int[] nums = {21, 432, 34, 1, 0, 0, 0, 0, 1, 2, 1343, 554, 3, 4, 2332, 243, 32, 32, 32, 5, 23,};
        int maxNumber = getMax(nums);
        System.out.println(maxNumber);
    }

    public static int getMax(int[] arr) {
        int max = arr[0];
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] > max) {
                max = arr[i];
            }
        }
        return max;
    }
}
