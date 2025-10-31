package previous.array;

// find the second largest and second smallest
public class arr2 {
    public static void main(String[] args) {
        int[] arr = {1, 0, 3, 4, 5, 6, 7, 1, 1};


        int secondlargestElement = getsecondLargest(arr);
        int secondSmallestElement = getsecondSmallest(arr);
        System.out.println(secondlargestElement);
        System.out.println(secondSmallestElement);

    }
//Sabse pehle, largest aur secondLargest ko previous.array ke pehle element se initialize karo.
//Array ke dusre element se lekar end tak loop chalao.
//Agar current element largest se bada hai, toh secondLargest ko largest bana do aur largest ko current element bana do.
//Agar current element largest se chhota hai aur secondLargest se bada hai, toh secondLargest ko current element bana do.
//Loop ke baad, secondLargest return karo.

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

    public static int getsecondSmallest(int[] arr) {

        int smallest = arr[0];
        int secondsmallest = arr[0];

        for (int i = 1; i < arr.length; i++) {
            if (arr[i] < smallest) {
                secondsmallest = smallest;
                smallest = arr[i];
            }
            if (arr[i] > smallest && arr[i] < secondsmallest) {
                secondsmallest = arr[i];
            }
        }
        return secondsmallest;

    }
}
