package array;

import java.util.Arrays;

public class Basics {

    public static void main(String[] args) {

        int[] array = {9, 1, 4, 11, 6, 2, 99, 7, 8, 9, 5, 19};

        // call customSearch
        int index = customSearch(array, 11);

        // call insertion
        int[] updatedArray = insertElement(array, 27, 3);

        // call deleteItems
        int[] deletedArray = deleteElement(array, 6);

        // call findMax
        int maxNumber = findMax(array);

        // call findSecondMax
        int secondMaxNumber = findSecondMax(array);
        // call thirdLargest
        int thirdLargest = thirdLargest(array);

        // call removeDuplicates
        int[] removedDuplicatesArray = removeDuplicates(new int[]{1, 2, 2, 3, 4, 5, 5, 5, 6, 7, 7});

        // call findSmallest
        int smallestNumber = findSmallest(array);

        System.out.println("|||||----------------------------------------------------------------------------------------------------|||||");

        System.out.println("- Index of 11: " + index);
        System.out.println("- After insertion: " + Arrays.toString(updatedArray));
        System.out.println("- After deletion: " + Arrays.toString(deletedArray));
        System.out.println("- Maximum number: " + maxNumber);
        System.out.println("- Second maximum number: " + secondMaxNumber);
        System.out.println("- Duplicates removed from array: " + Arrays.toString(removedDuplicatesArray));
        System.out.println("- Smallest number: " + smallestNumber);
        System.out.println("- 3rd Largest: " + thirdLargest);

        System.out.println("|||||----------------------------------------------------------------------------------------------------|||||");

    }

    // 🔍 Simple linear search
    public static int customSearch(int[] array, int searchValue) {
        for (int i = 0; i < array.length; i++) {
            if (array[i] == searchValue) {
                return i;
            }
        }
        return -1;
    }

    // ➕ Insert an element at a given position
    public static int[] insertElement(int[] array, int value, int position) {
        if (position < 0 || position > array.length) {
            throw new IllegalArgumentException("Invalid position");
        }

        int[] newArray = new int[array.length + 1];

        // Copy before position
        for (int i = 0; i < position; i++) {
            newArray[i] = array[i];
        }

        // Insert value
        newArray[position] = value;

        // Copy rest
        for (int i = position; i < array.length; i++) {
            newArray[i + 1] = array[i];
        }

        return newArray;
    }

    // ❌ Delete first occurrence of a value
    public static int[] deleteElement(int[] array, int value) {
        int index = customSearch(array, value);
        if (index == -1) {
            System.out.println("Value not found, no deletion performed.");
            return array;
        }

        int[] newArray = new int[array.length - 1];
        for (int i = 0, j = 0; i < array.length; i++) {
            if (i == index) continue; // skip the deleted item
            newArray[j++] = array[i];
        }
        return newArray;
    }

    // 🔝 Find maximum number
    public static int findMax(int[] array) {
        int max = array[0];
        for (int i = 1; i < array.length; i++) {
            if (array[i] > max) {
                max = array[i];
            }
        }
        return max;
    }

    // 🥈 Find second maximum (distinct)
    public static int findSecondMax(int[] array) {
        if (array.length < 2) {
            throw new IllegalArgumentException("Array must have at least two elements");
        }

        int max = Integer.MIN_VALUE;
        int secondMax = Integer.MIN_VALUE;

        for (int num : array) {
            if (num > max) {
                secondMax = max;
                max = num;
            } else if (num > secondMax && num < max) {
                secondMax = num;
            }
        }

        if (secondMax == Integer.MIN_VALUE) {
            throw new IllegalStateException("No distinct second maximum found (all elements equal?)");
        }

        return secondMax;
    }
//    {1,2,2,3,4,5,5,5,6,7,7}

    //    remove duplicate from sorted array
    public static int[] removeDuplicates(int[] array) {
        if (array == null || array.length == 0) {
            return new int[0];
        }

        int index = 0; // pointer to track unique position

        for (int i = 1; i < array.length; i++) {
            if (array[i] != array[index]) {
                index++;
                array[index] = array[i];
            }
        }

        // Copy only unique elements
        return Arrays.copyOf(array, index + 1);
    }

    //    Smallest elemnt in given array
    public static int findSmallest(int[] array) {
        if (array == null || array.length == 0) {
            throw new IllegalArgumentException("Array must have at least two elements");
        }
        int smallest = array[0];
        for (int ele : array) {
            if (ele < smallest) {
                smallest = ele;
            }
        }
        return smallest;
    }


    //    find 3rd largest element
    public static int thirdLargest(int[] array) {
        if (array == null || array.length < 3) {
            throw new IllegalArgumentException("Array must have at least three elements");
        }

        Integer largest = null;
        Integer secondLargest = null;
        Integer thirdLargest = null;

        for (int ele : array) {
            if (ele == largest || ele == secondLargest || ele == thirdLargest) {
                continue; // skip duplicates
            }

            if (largest == null || ele > largest) {
                thirdLargest = secondLargest;
                secondLargest = largest;
                largest = ele;
            } else if (secondLargest == null || ele > secondLargest) {
                thirdLargest = secondLargest;
                secondLargest = ele;
            } else if (thirdLargest == null || ele > thirdLargest) {
                thirdLargest = ele;
            }
        }

        if (thirdLargest == null) {
            throw new IllegalStateException("Less than three distinct elements in array");
        }

        return thirdLargest;
    }


}
