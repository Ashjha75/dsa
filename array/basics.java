package array;

import java.util.Arrays;

public class basics {

    public static void main(String[] args) {
        int[] array = {9, 1, 4, 11, 6, 2,99, 7, 8, 9, 5};
//        call customSearch
        int index = customSearch(array, 11);
//        call insertion
        int[] updatedarray = insertion(array, 27, 3);
//        call deleteItems
        int[] deletedarray = deleteItems(array, 6);
//        call maxNumber
        int maxNumber = findmax(array);

        System.out.println(index);
        System.out.println(Arrays.toString(updatedarray));
        System.out.println(Arrays.toString(deletedarray));
        System.out.println(maxNumber);

    }


    //    simple custom search
    public static int customSearch(int[] array, int searchValue) {
        for (int i = 0; i < array.length; i++) {
            if (array[i] == searchValue) {
                return i;
            }

        }
        return -1;
    }

    //    insertion
    public static int[] insertion(int[] array, int value, int position) {

        int[] newArray = new int[array.length + 1];

        for (int i = 0; i < position; i++) {
            newArray[i] = array[i];
        }
        newArray[position] = value;

        for (int i = position + 1; i <= array.length; i++) {
            newArray[i] = array[i - 1];
        }
        return newArray;
    }

    //    deletion
    public static int[] deleteItems(int[] array, int value) {
//        search
//        delete
//        swap

        for (int i = 0; i < array.length; i++) {
            if (array[i] == value) {
                array[i] = array[array.length - 1];
            }
        }
        return array;
    }

    //    find max number
    public static int findmax(int[] array) {
        int max = array[0];
        for (int i = 1; i < array.length; i++) {
            if (array[i] > max) {
                max = array[i];
            }
        }
        return max;
    }
}
