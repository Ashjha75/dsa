package array;

// find the second largest and second smallest
public class arr2 {
    public static void main(String[] args) {
        int[] arr = {1, 0, 3, 4, 5, 6, 7, 1, 1};


        int secondlargestElement = getsecondLargest(arr);
        System.out.println(secondlargestElement);

    }

    public static int getsecondLargest(int[] arr) {
        int largest = arr[0];
        int secondLargest = arr[0];

        for (int i = 1; i < arr.length; i++) {
            if (arr[i] > largest) {
                secondLargest = largest;
                largest = arr[i];
            }
            if (arr[i] < largest && arr[i] > secondLargest) {
                secondLargest = arr[i];
            }
        }
        return secondLargest;
    }
}
