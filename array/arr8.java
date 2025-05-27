package array;

import java.util.ArrayList;
import java.util.HashSet;

public class arr8 {
    public static void main(String[] args) {
        int[] arr = {1, 2, 0, 3, 6, 4};
        System.out.println(missingNumber(arr));
    }

    public static int missingNumber(int[] nums) {
        ArrayList<Integer> set = new ArrayList<>();
        for (int num : nums) {
            set.add(num);
        }
        int N = nums.length;
        for (int i = 0; i <= N; i++) {
            if (!set.contains(i)) {
                return i;
            }
        }
        return -1; // Should not reach here if input is valid
    }

//    using sum
public int missingNumber2(int[] nums) {
    int N =nums.length;
    int totalSum=N*(N+1)/2;
    int sum=0;
    for(int i=0;i<N;i++){
        totalSum-=nums[i];
    }
    return (totalSum);
}

}