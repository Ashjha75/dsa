package previous.array;

import java.util.HashMap;

//Find the number that appears once, and the other numbers twice
public class arr10 {
    public static void main(String[] args) {
        int[] arr = {4, 1, 2, 1, 2};

    }

    public int singleNumber(int[] nums) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            if (map.containsKey(num)) {
                map.put(num, map.get(num) + 1);
            } else {
                map.put(num, 1);
            }
        }
        int sum = 0;
        for (int num : map.keySet()) {
            if (map.get(num) == 1) {
                sum += num;
            }
        }
        return sum;
    }
}
