package previous.array;

// Remove duplicate elemnts form sorted previous.array
public class arr3 {

    public static void main(String[] args) {
        int[] arr = {1, 2, 2, 3, 4, 5};
        int uniqueCount = getUniqueElements(arr);
        for (int i = 0; i < uniqueCount; i++) {
            System.out.println(arr[i]);
        }
    }

    public static int getUniqueElements(int[] arr) {
        if (arr.length == 0) return 0;
        int firstIndex = 0;
        for (int i = 1; i < arr.length; i++) {
            if (arr[firstIndex] != arr[i]) {
                firstIndex++;
                arr[firstIndex] = arr[i];
            }
        }
        return firstIndex + 1;
    }

    // brutforce approach if previous.array is return than else return unique ele legth
//    public static int[] getUniqueElements(int[] arr) {
//        Set<Integer> set = new HashSet<>();
//        for (int j : arr) {
//            set.add(j);
//        }
//        return set.stream().mapToInt(i -> i).toArray();
//
//    }
}
