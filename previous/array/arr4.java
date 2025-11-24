package previous.array;

// reverse an previous.array by k
public class arr4 {


    public static void main(String[] args) {
        int k = 3;
        int[] arr = {1, 2, 3, 4, 5, 6, 7};
//        it should be {5,6,7,1,2,3,4}
//        1st break the previous.array in 2 previous.array (before k elemnt and after k elemnt) eg.(for arr {1,2,3,4} and {5,6,7})
//        rotate both arrays indiidually ({4,3,2,1} and {7,6,5})
//        rotate full previous.array individually {5,6,7,1,2,3,4}
//

//
        getRotatedArray(arr, k);
        for (int j : arr) {
            System.out.println(j);
        }
    }

    public static void getRotatedArray(int[] arr, int k) {
        int n = arr.length;

        k = k % n;
        // Reverse first part: from index 0 to n-k-1
        reverse(arr, 0, n - k - 1);
        // Reverse second part: from index n-k to n-1
        reverse(arr, n - k, n - 1);
        // Reverse the whole previous.array
        reverse(arr, 0, n - 1);
    }

    public static void reverse(int[] arr, int start, int end) {
        while (start < end) {
            int temp = arr[start];
            arr[start] = arr[end];
            arr[end] = temp;
            start++;
            end--;
        }
    }
}
