//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

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
        int[] removedDuplicatesArray = removeDuplicates(new int[]{1, 2, 2, 3, 4, 5, 5, 5, 6, 7, 7});
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

    public static int customSearch(int[] array, int searchValue) {
        for(int i = 0; i < array.length; ++i) {
            if (array[i] == searchValue) {
                return i;
            }
        }

        return -1;
    }

    public static int[] insertElement(int[] array, int value, int position) {
        if (position >= 0 && position <= array.length) {
            int[] newArray = new int[array.length + 1];

            for(int i = 0; i < position; ++i) {
                newArray[i] = array[i];
            }

            newArray[position] = value;

            for(int i = position; i < array.length; ++i) {
                newArray[i + 1] = array[i];
            }

            return newArray;
        } else {
            throw new IllegalArgumentException("Invalid position");
        }
    }

    public static int[] deleteElement(int[] array, int value) {
        int index = customSearch(array, value);
        if (index == -1) {
            System.out.println("Value not found, no deletion performed.");
            return array;
        } else {
            int[] newArray = new int[array.length - 1];
            int i = 0;

            for(int j = 0; i < array.length; ++i) {
                if (i != index) {
                    newArray[j++] = array[i];
                }
            }

            return newArray;
        }
    }

    public static int findMax(int[] array) {
        int max = array[0];

        for(int i = 1; i < array.length; ++i) {
            if (array[i] > max) {
                max = array[i];
            }
        }

        return max;
    }

    public static int findSecondMax(int[] array) {
        if (array.length < 2) {
            throw new IllegalArgumentException("Array must have at least two elements");
        } else {
            int max = Integer.MIN_VALUE;
            int secondMax = Integer.MIN_VALUE;

            for(int num : array) {
                if (num > max) {
                    secondMax = max;
                    max = num;
                } else if (num > secondMax && num < max) {
                    secondMax = num;
                }
            }

            if (secondMax == Integer.MIN_VALUE) {
                throw new IllegalStateException("No distinct second maximum found (all elements equal?)");
            } else {
                return secondMax;
            }
        }
    }

    public static int[] removeDuplicates(int[] array) {
        if (array != null && array.length != 0) {
            int index = 0;

            for(int i = 1; i < array.length; ++i) {
                if (array[i] != array[index]) {
                    ++index;
                    array[index] = array[i];
                }
            }

            return Arrays.copyOf(array, index + 1);
        } else {
            return new int[0];
        }
    }

    public static int findSmallest(int[] array) {
        if (array != null && array.length != 0) {
            int smallest = array[0];

            for(int ele : array) {
                if (ele < smallest) {
                    smallest = ele;
                }
            }

            return smallest;
        } else {
            throw new IllegalArgumentException("Array must have at least two elements");
        }
    }

    public static int thirdLargest(int[] array) {
        if (array != null && array.length >= 3) {
            Integer largest = Integer.MIN_VALUE;
            Integer secondLargest = Integer.MIN_VALUE;
            Integer thirdLargest = Integer.MIN_VALUE;

            for(int ele : array) {
                if (ele != largest && ele != secondLargest && ele != thirdLargest) {
                    if (largest != Integer.MIN_VALUE && ele <= largest) {
                        if (secondLargest != Integer.MIN_VALUE && ele <= secondLargest) {
                            if (thirdLargest == Integer.MIN_VALUE || ele > thirdLargest) {
                                thirdLargest = ele;
                            }
                        } else {
                            thirdLargest = secondLargest;
                            secondLargest = ele;
                        }
                    } else {
                        thirdLargest = secondLargest;
                        secondLargest = largest;
                        largest = ele;
                    }
                }
            }

            if (thirdLargest == Integer.MIN_VALUE) {
                throw new IllegalStateException("Less than three distinct elements in array");
            } else {
                return thirdLargest;
            }
        } else {
            throw new IllegalArgumentException("Array must have at least three elements");
        }
    }
}
