package array;

import java.util.Arrays;

public class Basics {
    public static void main(String[] args) {
        int[] array = new int[]{9, 1, 4, 11, 6, 2, 99, 7, 8, 9, 5, 19};
        int index = customSearch(array, 11);
        int[] updatedArray = insertElement(array, 27, 3);
        int[] deletedArray = deleteElement(array, 6);
        int maxNumber = findMax(array);
        int secondMaxNumber = findSecondMax(array);
        int thirdLargest = thirdLargest(array);
        int[] removedDuplicatesArray = removeDuplicatesFromSorted(new int[]{1, 2, 2, 3, 4, 5, 5, 5, 6, 7, 7});
        int smallestNumber = findSmallest(array);
        boolean isArraySorted = isArraySorted(array);
        int[] reverseArray = reverseArray(array);

        System.out.println("|||||----------------------------------------------------------------------------------------------------|||||");
        System.out.println("- Index of 11: " + index);
        System.out.println("- After insertion: " + Arrays.toString(updatedArray));
        System.out.println("- After deletion: " + Arrays.toString(deletedArray));
        System.out.println("- Maximum number: " + maxNumber);
        System.out.println("- Second maximum number: " + secondMaxNumber);
        System.out.println("- Third largest number: " + thirdLargest);
        System.out.println("- Duplicates removed from sorted array: " + Arrays.toString(removedDuplicatesArray));
        System.out.println("- Smallest number: " + smallestNumber);
        System.out.println("- Is Array sorted: " + isArraySorted);
        System.out.println("- Reversed Array: " + Arrays.toString(reverseArray));
        System.out.println("|||||----------------------------------------------------------------------------------------------------|||||");
    }


    /**
     * Searches for a value in the array and returns its index.
     *
     * @return index of the value, or -1 if not found
     */
    public static int customSearch(int[] array, int searchValue) {
        if (array == null) {
            return -1;
        }

        for (int i = 0; i < array.length; i++) {
            if (array[i] == searchValue) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Inserts an element at the specified position.
     */
    public static int[] insertElement(int[] array, int value, int position) {
        if (array == null) {
            throw new IllegalArgumentException("Array cannot be null");
        }
        if (position < 0 || position > array.length) {
            throw new IllegalArgumentException("Invalid position: " + position);
        }

        int[] newArray = new int[array.length + 1];

        // Copy elements before insertion point
        for (int i = 0; i < position; i++) {
            newArray[i] = array[i];
        }

        // Insert new value
        newArray[position] = value;

        // Copy elements after insertion point
        for (int i = position; i < array.length; i++) {
            newArray[i + 1] = array[i];
        }

        return newArray;
    }

    /**
     * Deletes the first occurrence of a value from the array.
     */
    public static int[] deleteElement(int[] array, int value) {
        if (array == null) {
            throw new IllegalArgumentException("Array cannot be null");
        }

        int index = customSearch(array, value);
        if (index == -1) {
            System.out.println("Value not found, no deletion performed.");
            return array;
        }

        int[] newArray = new int[array.length - 1];
        int j = 0;

        for (int i = 0; i < array.length; i++) {
            if (i != index) {
                newArray[j++] = array[i];
            }
        }

        return newArray;
    }

    /**
     * Finds the maximum value in the array.
     */
    public static int findMax(int[] array) {
        if (array == null || array.length == 0) {
            throw new IllegalArgumentException("Array must have at least one element");
        }

        int max = array[0];
        for (int i = 1; i < array.length; i++) {
            if (array[i] > max) {
                max = array[i];
            }
        }
        return max;
    }

    /**
     * Finds the second maximum distinct value in the array.
     */
    public static int findSecondMax(int[] array) {
        if (array == null || array.length < 2) {
            throw new IllegalArgumentException("Array must have at least two elements");
        }

        int max = Integer.MIN_VALUE;
        int secondMax = Integer.MIN_VALUE;

        for (int num : array) {
            if (num > max) {
                secondMax = max;
                max = num;
            } else if (num > secondMax && num != max) {  // FIXED: Changed from num < max to num != max
                secondMax = num;
            }
        }

        if (secondMax == Integer.MIN_VALUE) {
            throw new IllegalStateException("No distinct second maximum found (all elements equal or less than two distinct values)");
        }

        return secondMax;
    }

    /**
     * Finds the third largest distinct value in the array.
     */
    public static int thirdLargest(int[] array) {
        if (array == null || array.length < 3) {
            throw new IllegalArgumentException("Array must have at least three elements");
        }

        int largest = Integer.MIN_VALUE;
        int secondLargest = Integer.MIN_VALUE;
        int thirdLargest = Integer.MIN_VALUE;

        for (int ele : array) {
            if (ele > largest) {
                thirdLargest = secondLargest;
                secondLargest = largest;
                largest = ele;
            } else if (ele > secondLargest && ele != largest) {
                thirdLargest = secondLargest;
                secondLargest = ele;
            } else if (ele > thirdLargest && ele != secondLargest && ele != largest) {
                thirdLargest = ele;
            }
        }

        if (thirdLargest == Integer.MIN_VALUE) {
            throw new IllegalStateException("Less than three distinct elements in array");
        }

        return thirdLargest;
    }

    /**
     * Removes consecutive duplicates from a SORTED array.
     * NOTE: This only works correctly on sorted arrays!
     */
    public static int[] removeDuplicatesFromSorted(int[] array) {
        if (array == null || array.length == 0) {
            return new int[0];
        }

        int index = 0;
        for (int i = 1; i < array.length; i++) {
            if (array[i] != array[index]) {
                index++;
                array[index] = array[i];
            }
        }

        return Arrays.copyOf(array, index + 1);
    }

    /**
     * Removes all duplicates from an unsorted array (if needed).
     */
    public static int[] removeDuplicatesFromUnsorted(int[] array) {
        if (array == null || array.length == 0) {
            return new int[0];
        }

        int[] temp = new int[array.length];
        int index = 0;

        for (int i = 0; i < array.length; i++) {
            boolean isDuplicate = false;
            for (int j = 0; j < index; j++) {
                if (array[i] == temp[j]) {
                    isDuplicate = true;
                    break;
                }
            }
            if (!isDuplicate) {
                temp[index++] = array[i];
            }
        }

        return Arrays.copyOf(temp, index);
    }

    /**
     * Finds the smallest value in the array.
     */
    public static int findSmallest(int[] array) {
        if (array == null || array.length == 0) {
            throw new IllegalArgumentException("Array must have at least one element");
        }

        int smallest = array[0];
        for (int ele : array) {
            if (ele < smallest) {
                smallest = ele;
            }
        }

        return smallest;
    }

    /**
     * Check if array is sorted in ascending order
     */
    private static boolean isArraySorted(int[] array) {
        // Remove this line! Don't overwrite the parameter

        if (array == null || array.length == 0) {
            throw new IllegalArgumentException("Array cannot be null or empty");
        }

        // Single element is considered sorted
        if (array.length == 1) {
            return true;
        }

        // Check if ALL consecutive pairs are in order
        for (int i = 1; i < array.length; i++) {
            if (array[i - 1] > array[i]) {  // If any pair is out of order
                return false;
            }
        }

        return true;
    }

    /**
     * Reverses the array in-place.
     * @param array the array to reverse
     * @return the same array (reversed)
     */
    public static int[] reverseArray(int[] array) {
        if (array == null || array.length == 0) {
            throw new IllegalArgumentException("Array cannot be null or empty");
        }

        int left = 0;
        int right = array.length - 1;

        while (left < right) {
            // Swap elements
            int temp = array[left];
            array[left] = array[right];
            array[right] = temp;

            left++;
            right--;
        }

        return array;
    }


}