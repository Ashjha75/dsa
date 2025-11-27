# Array DSA Complete Guide - Java

## Theory Fundamentals

### What is an Array?

- Contiguous memory locations storing elements of same type
- Fixed size (static) or dynamic size (ArrayList)
- Index-based access: O(1) random access
- Homogeneous data structure

### Memory Layout

```
arr[0] arr[1] arr[2] arr[3] arr[4]
[100]  [104]  [108]  [112]  [116]  (memory addresses for int)
```

- Base address + (index × element_size)

### Time Complexities

| Operation       | Static Array | ArrayList      |
|-----------------|--------------|----------------|
| Access          | O(1)         | O(1)           |
| Search          | O(n)         | O(n)           |
| Insert (end)    | N/A          | O(1) amortized |
| Insert (middle) | N/A          | O(n)           |
| Delete (end)    | N/A          | O(1)           |
| Delete (middle) | N/A          | O(n)           |

### Space Complexity

- Static: O(n) - fixed
- ArrayList: O(n) - grows by 50% when full (1.5x capacity)

---

## 1. Static Arrays (Primitive)

### Declaration & Initialization

```java
// 1D Array
int[] arr = new int[5];                           // [0,0,0,0,0]
int[] arr = {1, 2, 3, 4, 5};
int[] arr = new int[]{1, 2, 3};

// Multi-type arrays
String[] names = {"John", "Jane"};
boolean[] flags = new boolean[3];                 // [false, false, false]
double[] prices = {10.5, 20.3, 30.1};

// 2D Array (Matrix)
int[][] matrix = new int[3][4];                   // 3 rows, 4 cols
int[][] matrix = {{1,2,3}, {4,5,6}, {7,8,9}};
int[][] jagged = new int[3][];                    // jagged array
jagged[0] = new int[2];
jagged[1] = new int[4];

// 3D Array
int[][][] cube = new int[2][3][4];
```

### CRUD Operations

```java
// CREATE - covered above

// READ
int val = arr[0];                                 // first element
int val = arr[arr.length - 1];                    // last element
int val = matrix[1][2];                           // 2D access

// UPDATE
arr[0] = 100;
matrix[1][2] = 50;

// DELETE - shift elements manually
void deleteAtIndex(int[] arr, int index, int size) {
    for (int i = index; i < size - 1; i++) {
        arr[i] = arr[i + 1];
    }
    // size--; (track size separately)
}
```

### Common Operations

```java
// Length
int len = arr.length;
int rows = matrix.length;
int cols = matrix[0].length;

// Clone
int[] copy = arr.clone();                         // shallow copy

// Fill
Arrays.fill(arr, 10);                             // fill with 10
Arrays.fill(arr, 1, 4, 5);                        // fill range [1,4) with 5

// Sort
Arrays.sort(arr);                                 // ascending
Arrays.sort(arr, Collections.reverseOrder());     // needs Integer[]
Arrays.sort(arr, 0, 3);                           // sort range [0,3)

// Binary Search (requires sorted array)
int index = Arrays.binarySearch(arr, 5);          // returns index or -(insertion_point)-1

// Compare
boolean equal = Arrays.equals(arr1, arr2);
boolean deepEqual = Arrays.deepEquals(matrix1, matrix2);  // for 2D

// Copy
int[] copy = Arrays.copyOf(arr, arr.length);
int[] copy = Arrays.copyOfRange(arr, 1, 4);       // [1,4)
System.arraycopy(src, 0, dest, 0, length);

// Convert to String
String str = Arrays.toString(arr);                // [1, 2, 3]
String str = Arrays.deepToString(matrix);         // for 2D
```

---

## 2. Dynamic Arrays (ArrayList)

### Declaration & Initialization

```java
import java.util.ArrayList;
import java.util.List;

ArrayList<Integer> list = new ArrayList<>();
ArrayList<Integer> list = new ArrayList<>(20);              // initial capacity
ArrayList<Integer> list = new ArrayList<>(Arrays.asList(1,2,3));
List<Integer> list = List.of(1,2,3);                        // immutable
List<Integer> list = new ArrayList<>(List.of(1,2,3));       // mutable copy
```

### CRUD Operations

```java
// CREATE
list.add(10);                                     // add at end - O(1)
list.add(0, 5);                                   // add at index - O(n)
list.addAll(Arrays.asList(1,2,3));               // add collection
list.addAll(2, Arrays.asList(7,8));              // add at index

// READ
int val = list.get(0);                            // O(1)
int first = list.getFirst();                      // Java 21+
int last = list.getLast();                        // Java 21+

// UPDATE
list.set(0, 100);                                 // O(1)

// DELETE
list.remove(0);                                   // by index - O(n)
list.remove(Integer.valueOf(10));                // by value - O(n)
list.removeAll(Arrays.asList(1,2));              // remove collection
list.removeIf(x -> x > 5);                        // conditional
list.clear();                                     // remove all
```

### Common Operations

```java
// Size & Capacity
int size = list.size();
boolean empty = list.isEmpty();
list.ensureCapacity(100);                         // hint capacity
list.trimToSize();                                // reduce to size

// Search
boolean exists = list.contains(5);                // O(n)
int index = list.indexOf(5);                      // first occurrence, -1 if not found
int index = list.lastIndexOf(5);                  // last occurrence

// Sublist
List<Integer> sub = list.subList(1, 4);          // [1,4) - backed by original

// Convert
Integer[] arr = list.toArray(new Integer[0]);
int[] arr = list.stream().mapToInt(i->i).toArray();

// Sort
Collections.sort(list);                           // ascending
Collections.sort(list, Collections.reverseOrder());
list.sort(Comparator.naturalOrder());
list.sort((a,b) -> b - a);                        // descending

// Reverse
Collections.reverse(list);

// Copy
List<Integer> copy = new ArrayList<>(list);
List<Integer> copy = list.stream().collect(Collectors.toList());

// Frequency
int count = Collections.frequency(list, 5);

// Min/Max
int min = Collections.min(list);
int max = Collections.max(list);

// Replace
Collections.replaceAll(list, 5, 10);              // replace 5 with 10
list.replaceAll(x -> x * 2);                      // transform each
```

---

## 3. Collections Framework - Lists

### List Interface Methods

```java
// All ArrayList methods +
list.listIterator();                              // bidirectional iterator
list.listIterator(2);                             // from index 2
```

### Other List Implementations

```java
// LinkedList - doubly-linked
LinkedList<Integer> ll = new LinkedList<>();
ll.addFirst(1);                                   // O(1)
ll.addLast(5);                                    // O(1)
ll.removeFirst();                                 // O(1)
ll.removeLast();                                  // O(1)
ll.peek();                                        // view first
ll.poll();                                        // remove first

// Vector - synchronized ArrayList
Vector<Integer> vec = new Vector<>();

// Stack - LIFO (extends Vector)
Stack<Integer> stack = new Stack<>();
stack.push(10);
stack.pop();
stack.peek();
stack.search(10);                                 // returns position from top
```

---

## 4. Streams API on Arrays

### Creating Streams

```java
import java.util.stream.*;

// From Array
int[] arr = {1,2,3,4,5};
IntStream stream = Arrays.stream(arr);
Stream<Integer> stream = Arrays.stream(arr).boxed();

// From ArrayList
Stream<Integer> stream = list.stream();
Stream<Integer> parallel = list.parallelStream();

// Generate streams
IntStream.range(0, 10);                           // 0 to 9
IntStream.rangeClosed(0, 10);                     // 0 to 10
IntStream.of(1,2,3,4,5);
```

### Common Stream Operations

```java
// Filter
list.stream().filter(x -> x > 5).collect(Collectors.toList());

// Map
list.stream().map(x -> x * 2).collect(Collectors.toList());
int[] arr2 = Arrays.stream(arr).map(x -> x * 2).toArray();

// FlatMap
int[][] matrix = {{1,2}, {3,4}};
int[] flat = Arrays.stream(matrix).flatMapToInt(Arrays::stream).toArray();

// Reduce
int sum = Arrays.stream(arr).reduce(0, (a,b) -> a + b);
int sum = Arrays.stream(arr).sum();
int product = Arrays.stream(arr).reduce(1, (a,b) -> a * b);

// Collect
List<Integer> result = stream.collect(Collectors.toList());
Set<Integer> set = stream.collect(Collectors.toSet());

// forEach
list.forEach(System.out::println);
list.forEach(x -> System.out.print(x + " "));

// Sorted
list.stream().sorted().collect(Collectors.toList());
list.stream().sorted(Comparator.reverseOrder()).collect(Collectors.toList());

// Distinct
list.stream().distinct().collect(Collectors.toList());

// Limit & Skip
list.stream().limit(5).collect(Collectors.toList());
list.stream().skip(2).collect(Collectors.toList());

// Count
long count = list.stream().filter(x -> x > 5).count();

// AnyMatch, AllMatch, NoneMatch
boolean any = list.stream().anyMatch(x -> x > 5);
boolean all = list.stream().allMatch(x -> x > 0);
boolean none = list.stream().noneMatch(x -> x < 0);

// FindFirst, FindAny
Optional<Integer> first = list.stream().filter(x -> x > 5).findFirst();
Optional<Integer> any = list.stream().filter(x -> x > 5).findAny();

// Min, Max
Optional<Integer> min = list.stream().min(Comparator.naturalOrder());
Optional<Integer> max = list.stream().max(Comparator.naturalOrder());
OptionalInt min = Arrays.stream(arr).min();
OptionalInt max = Arrays.stream(arr).max();

// Average
OptionalDouble avg = Arrays.stream(arr).average();

// Grouping
Map<Boolean, List<Integer>> grouped = list.stream()
    .collect(Collectors.groupingBy(x -> x > 5));

Map<Integer, Long> freq = list.stream()
    .collect(Collectors.groupingBy(x -> x, Collectors.counting()));

// Partitioning
Map<Boolean, List<Integer>> partitioned = list.stream()
    .collect(Collectors.partitioningBy(x -> x > 5));

// Joining (for Strings)
String joined = list.stream()
    .map(String::valueOf)
    .collect(Collectors.joining(", "));

// toArray
Integer[] arr = list.stream().toArray(Integer[]::new);
int[] arr = list.stream().mapToInt(i->i).toArray();
```

---

## 5. Matrix Operations

### Traversal Patterns

```java
int[][] matrix = {{1,2,3}, {4,5,6}, {7,8,9}};
int rows = matrix.length;
int cols = matrix[0].length;

// Row-wise
for (int i = 0; i < rows; i++) {
    for (int j = 0; j < cols; j++) {
        System.out.print(matrix[i][j] + " ");
    }
}

// Column-wise
for (int j = 0; j < cols; j++) {
    for (int i = 0; i < rows; i++) {
        System.out.print(matrix[i][j] + " ");
    }
}

// Diagonal (main)
for (int i = 0; i < rows; i++) {
    System.out.print(matrix[i][i] + " ");
}

// Diagonal (anti)
for (int i = 0; i < rows; i++) {
    System.out.print(matrix[i][cols - 1 - i] + " ");
}

// Spiral Order
// (implement based on problem)

// Enhanced for-each
for (int[] row : matrix) {
    for (int val : row) {
        System.out.print(val + " ");
    }
}
```

### Matrix Operations

```java
// Transpose
int[][] transpose = new int[cols][rows];
for (int i = 0; i < rows; i++) {
    for (int j = 0; j < cols; j++) {
        transpose[j][i] = matrix[i][j];
    }
}

// Rotate 90° clockwise (for square matrix)
// 1. Transpose
// 2. Reverse each row
for (int i = 0; i < n; i++) {
    for (int j = i; j < n; j++) {
        int temp = matrix[i][j];
        matrix[i][j] = matrix[j][i];
        matrix[j][i] = temp;
    }
}
for (int i = 0; i < n; i++) {
    for (int j = 0; j < n/2; j++) {
        int temp = matrix[i][j];
        matrix[i][j] = matrix[i][n-1-j];
        matrix[i][n-1-j] = temp;
    }
}

// Matrix Addition/Subtraction
for (int i = 0; i < rows; i++) {
    for (int j = 0; j < cols; j++) {
        result[i][j] = mat1[i][j] + mat2[i][j];
    }
}
```

---

## 6. Important Patterns & Techniques

### Two Pointers

```java
// Find pair with sum
int left = 0, right = arr.length - 1;
while (left < right) {
    int sum = arr[left] + arr[right];
    if (sum == target) return true;
    else if (sum < target) left++;
    else right--;
}
```

### Sliding Window

```java
// Max sum subarray of size k
int windowSum = 0, maxSum = 0;
for (int i = 0; i < k; i++) windowSum += arr[i];
maxSum = windowSum;
for (int i = k; i < arr.length; i++) {
    windowSum += arr[i] - arr[i - k];
    maxSum = Math.max(maxSum, windowSum);
}
```

### Prefix Sum

```java
int[] prefix = new int[n];
prefix[0] = arr[0];
for (int i = 1; i < n; i++) {
    prefix[i] = prefix[i-1] + arr[i];
}
// Range sum [l, r] = prefix[r] - prefix[l-1]
```

### Kadane's Algorithm (Max Subarray Sum)

```java
int maxSum = arr[0], currentSum = arr[0];
for (int i = 1; i < arr.length; i++) {
    currentSum = Math.max(arr[i], currentSum + arr[i]);
    maxSum = Math.max(maxSum, currentSum);
}
```

### Dutch National Flag (3-way partition)

```java
int low = 0, mid = 0, high = n - 1;
while (mid <= high) {
    if (arr[mid] == 0) swap(arr, low++, mid++);
    else if (arr[mid] == 1) mid++;
    else swap(arr, mid, high--);
}
```

### Moore's Voting Algorithm (Majority Element)

```java
int candidate = 0, count = 0;
for (int num : arr) {
    if (count == 0) candidate = num;
    count += (num == candidate) ? 1 : -1;
}
```

---

## 7. Comparators & Sorting

### Custom Comparator

```java
// Array of objects
Arrays.sort(arr, (a, b) -> a - b);                // ascending
Arrays.sort(arr, (a, b) -> b - a);                // descending
Arrays.sort(arr, Comparator.naturalOrder());
Arrays.sort(arr, Comparator.reverseOrder());

// ArrayList
list.sort((a, b) -> a - b);
list.sort(Comparator.comparingInt(x -> x));

// 2D array sorting
Arrays.sort(intervals, (a, b) -> a[0] - b[0]);    // by first element
Arrays.sort(intervals, Comparator.comparingInt(a -> a[0]));

// Multiple criteria
Arrays.sort(people, (a, b) -> {
    if (a[0] != b[0]) return b[0] - a[0];         // descending by first
    return a[1] - b[1];                            // ascending by second
});
```

---

## 8. Common Pitfalls

### Integer Overflow

```java
// Wrong
int mid = (left + right) / 2;
// Right
int mid = left + (right - left) / 2;
```

### Null Checks

```java
if (arr == null || arr.length == 0) return;
if (list == null || list.isEmpty()) return;
```

### Comparison Issues

```java
// Wrong for large numbers
Arrays.sort(arr, (a, b) -> a - b);                // can overflow
// Right
Arrays.sort(arr, (a, b) -> Integer.compare(a, b));
```

### Immutable Lists

```java
List<Integer> list = Arrays.asList(1,2,3);        // fixed size
list.add(4);                                       // throws UnsupportedOperationException

List<Integer> list = List.of(1,2,3);              // immutable
list.set(0, 5);                                    // throws UnsupportedOperationException
```

---

## 9. Quick Reference

### Conversion Cheatsheet

```java
// Array to List
List<Integer> list = Arrays.asList(arr);          // fixed size
List<Integer> list = new ArrayList<>(Arrays.asList(arr));
List<Integer> list = Arrays.stream(arr).boxed().collect(Collectors.toList());

// List to Array
Integer[] arr = list.toArray(new Integer[0]);
int[] arr = list.stream().mapToInt(i->i).toArray();

// Array to Stream
IntStream stream = Arrays.stream(arr);
Stream<Integer> stream = Arrays.stream(arr).boxed();

// List to Stream
Stream<Integer> stream = list.stream();

// String to char array
char[] chars = str.toCharArray();

// char array to String
String str = new String(chars);
String str = String.valueOf(chars);

// int[] to Integer[]
Integer[] boxed = Arrays.stream(arr).boxed().toArray(Integer[]::new);

// Integer[] to int[]
int[] unboxed = Arrays.stream(boxedArr).mapToInt(i->i).toArray();
```

### Import Statements

```java
import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;
import java.util.Collections;
import java.util.Comparator;
import java.util.stream.*;
import java.util.function.*;
```

---

## 10. Interview Focus Areas

### Must Know Problems

1. Two Sum / Two Pointer problems
2. Kadane's Algorithm (Max subarray sum)
3. Sliding Window (fixed/variable)
4. Prefix Sum / Difference Array
5. Matrix traversal (spiral, diagonal, rotate)
6. Sorting variations (custom comparators)
7. Binary Search on array
8. Dutch National Flag
9. Merge intervals
10. Stock buy/sell problems

### Key Concepts

- Time/Space complexity analysis
- In-place vs extra space
- Stable vs unstable sorting
- Pass by reference vs value
- Shallow vs deep copy
- Immutability considerations
- Edge cases (empty, single element, duplicates)

### Optimization Techniques

- Avoid repeated work (use prefix sum, memoization)
- Two pointers instead of nested loops
- HashMap for O(1) lookup
- Sort first if helps reduce complexity
- Use built-in methods (Arrays.sort, Collections methods)
- Stream API for cleaner code (when performance not critical)
