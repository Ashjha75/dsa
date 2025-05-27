package array;

// move zero from array at last

public class arr5 {
    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 0, 0, 1, 2, 30, 0, 9, 8, 0, 0, 3, 0, 0,};

        moveZeroes(arr);
        for (int j : arr) {
            System.out.print(j + " ");
        }

    }

    public static void moveZeroes(int[] nums) {
        int j = -1;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 0) {
                j = i;
                break;
            }
        }
        for (int i = j + 1; i < nums.length; i++) {
            if (nums[i] != 0) {
                int temp = nums[j];
                nums[j] = nums[i];
                nums[i] = temp;
                j++;
            }
        }
    }
}
