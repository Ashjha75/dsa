package previous.array;

//simple linear search
public class arr6 {
    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        int searchElement = 0;

        int isPresent = searchElement(arr, searchElement);
        System.out.println(isPresent);

    }

    public static int searchElement(int[] arr, int searchElement) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == searchElement) {
                return i;
            }
        }
        return -1;
    }
}
