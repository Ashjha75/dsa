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

## 10. Collections Framework Deep Dive

### Collections Utility Class

```java
import java.util.Collections;

List<Integer> list = new ArrayList<>(Arrays.asList(5,2,8,1,9));

// Sorting
Collections.sort(list);                           // ascending
Collections.sort(list, Collections.reverseOrder());
Collections.sort(list, (a,b) -> b - a);

// Searching (requires sorted list)
int index = Collections.binarySearch(list, 5);    // O(log n)

// Reverse
Collections.reverse(list);

// Shuffle
Collections.shuffle(list);                        // random permutation

// Rotate
Collections.rotate(list, 2);                      // rotate right by 2
Collections.rotate(list, -2);                     // rotate left by 2

// Fill
Collections.fill(list, 0);                        // fill all with 0

// Copy
List<Integer> dest = new ArrayList<>(Collections.nCopies(list.size(), 0));
Collections.copy(dest, list);                     // dest must be >= size

// Swap
Collections.swap(list, 0, 4);                     // swap elements at indices

// Replace
Collections.replaceAll(list, 5, 10);              // replace all 5 with 10

// Frequency
int count = Collections.frequency(list, 5);       // count occurrences

// Min/Max
int min = Collections.min(list);
int max = Collections.max(list);
int min = Collections.min(list, (a,b) -> b - a); // custom comparator

// Disjoint
boolean noCommon = Collections.disjoint(list1, list2);  // true if no common

// AddAll
Collections.addAll(list, 1, 2, 3, 4, 5);         // varargs add

// Index methods
int firstIndex = Collections.indexOfSubList(list, sublist);
int lastIndex = Collections.lastIndexOfSubList(list, sublist);

// Unmodifiable wrappers
List<Integer> immutable = Collections.unmodifiableList(list);
// immutable.add(1);                              // throws exception

// Synchronized wrappers (thread-safe)
List<Integer> syncList = Collections.synchronizedList(list);
synchronized(syncList) {
    Iterator<Integer> it = syncList.iterator();
    while(it.hasNext()) { /* ... */ }
}

// Empty collections
List<Integer> empty = Collections.emptyList();
Set<Integer> emptySet = Collections.emptySet();
Map<Integer,Integer> emptyMap = Collections.emptyMap();

// Singleton collections
List<Integer> single = Collections.singletonList(5);  // immutable, 1 element
Set<Integer> singleSet = Collections.singleton(5);

// nCopies - repeated elements
List<Integer> repeated = Collections.nCopies(5, 10); // [10,10,10,10,10]

// Checked collections (runtime type safety)
List<Integer> checked = Collections.checkedList(list, Integer.class);
```

### Arrays Utility Class (Extended)

```java
import java.util.Arrays;

int[] arr = {5, 2, 8, 1, 9};

// Sorting
Arrays.sort(arr);                                 // full array
Arrays.sort(arr, 1, 4);                          // range [1,4)
Arrays.parallelSort(arr);                        // parallel sort (faster for large)

// Searching
int index = Arrays.binarySearch(arr, 5);         // must be sorted
int index = Arrays.binarySearch(arr, 1, 4, 5);  // in range [1,4)

// Fill
Arrays.fill(arr, 10);                            // fill all
Arrays.fill(arr, 1, 4, 5);                       // range [1,4)

// Compare
boolean equal = Arrays.equals(arr1, arr2);
int cmp = Arrays.compare(arr1, arr2);            // lexicographic
int cmp = Arrays.mismatch(arr1, arr2);           // first diff index, -1 if equal

// Copy
int[] copy = Arrays.copyOf(arr, arr.length);     // full copy
int[] copy = Arrays.copyOf(arr, 10);             // extend with 0s
int[] copy = Arrays.copyOfRange(arr, 1, 4);     // [1,4)

// String representation
String str = Arrays.toString(arr);                // 1D: [1, 2, 3]
String str = Arrays.deepToString(matrix);         // 2D/3D

// Hash code
int hash = Arrays.hashCode(arr);
int hash = Arrays.deepHashCode(matrix);

// Convert to List
List<Integer> list = Arrays.asList(1, 2, 3);     // fixed-size
List<Integer> list = new ArrayList<>(Arrays.asList(arr)); // mutable

// Stream
IntStream stream = Arrays.stream(arr);
IntStream stream = Arrays.stream(arr, 1, 4);     // range [1,4)

// Parallel prefix (cumulative operation)
int[] arr = {1, 2, 3, 4, 5};
Arrays.parallelPrefix(arr, (a, b) -> a + b);     // [1,3,6,10,15] cumulative sum

// Set all (parallel)
Arrays.setAll(arr, i -> i * 2);                  // arr[i] = i * 2
Arrays.parallelSetAll(arr, i -> i * i);          // arr[i] = i * i

// Split iterator
Spliterator.OfInt spliterator = Arrays.spliterator(arr);
```

### List Interface Complete Methods

```java
List<Integer> list = new ArrayList<>();

// Positional Access
list.get(0);                                      // get element
list.set(0, 10);                                  // replace element
list.add(5);                                      // add at end
list.add(0, 5);                                   // add at index
list.remove(0);                                   // remove at index
list.remove(Integer.valueOf(5));                  // remove by value

// Search
list.indexOf(5);                                  // first occurrence
list.lastIndexOf(5);                              // last occurrence
list.contains(5);                                 // boolean

// Bulk Operations
list.addAll(Arrays.asList(1,2,3));               // add collection
list.addAll(0, Arrays.asList(1,2,3));            // add at index
list.removeAll(Arrays.asList(1,2));              // remove all occurrences
list.retainAll(Arrays.asList(1,2));              // keep only these
list.clear();                                     // remove all

// View Operations
List<Integer> sub = list.subList(1, 4);          // [1,4) backed view
sub.clear();                                      // clears from original too

// Iteration
Iterator<Integer> it = list.iterator();
while(it.hasNext()) {
    int val = it.next();
    if(val == 5) it.remove();                     // safe remove during iteration
}

ListIterator<Integer> lit = list.listIterator(); // bidirectional
ListIterator<Integer> lit = list.listIterator(2); // start at index 2
while(lit.hasNext()) {
    int val = lit.next();
    lit.set(val * 2);                             // modify during iteration
    lit.add(100);                                 // add during iteration
}
while(lit.hasPrevious()) {
    int val = lit.previous();
}

// forEach
list.forEach(x -> System.out.println(x));
list.forEach(System.out::println);

// replaceAll
list.replaceAll(x -> x * 2);                      // transform each element

// removeIf
list.removeIf(x -> x > 5);                        // conditional remove

// sort
list.sort(Comparator.naturalOrder());
list.sort((a,b) -> b - a);
list.sort(null);                                  // natural order

// Size operations
int size = list.size();
boolean empty = list.isEmpty();

// Conversion
Object[] arr = list.toArray();
Integer[] arr = list.toArray(new Integer[0]);
Integer[] arr = list.toArray(Integer[]::new);

// Equals & HashCode
boolean eq = list.equals(list2);
int hash = list.hashCode();
```

### ArrayList Specific Methods

```java
ArrayList<Integer> list = new ArrayList<>();

// Capacity management
list.ensureCapacity(100);                         // hint capacity
list.trimToSize();                                // reduce to current size

// Clone (shallow)
ArrayList<Integer> clone = (ArrayList<Integer>) list.clone();

// Java 21+ methods
list.addFirst(5);                                 // O(n)
list.addLast(10);                                 // O(1)
int first = list.getFirst();
int last = list.getLast();
list.removeFirst();                               // O(n)
list.removeLast();                                // O(1)
```

### Vector & Stack

```java
// Vector - synchronized ArrayList
Vector<Integer> vec = new Vector<>();
vec.capacity();                                   // current capacity
vec.addElement(5);                                // legacy add
vec.removeElement(5);                             // legacy remove
vec.firstElement();                               // first element
vec.lastElement();                                // last element
vec.setSize(10);                                  // resize

// Stack - LIFO (legacy, use Deque instead)
Stack<Integer> stack = new Stack<>();
stack.push(5);                                    // add to top
int top = stack.pop();                            // remove from top
int top = stack.peek();                           // view top
boolean empty = stack.empty();
int pos = stack.search(5);                        // position from top (1-based)
```

### LinkedList (Deque Implementation)

```java
LinkedList<Integer> list = new LinkedList<>();

// Deque operations
list.addFirst(1);                                 // O(1)
list.addLast(5);                                  // O(1)
list.removeFirst();                               // O(1)
list.removeLast();                                // O(1)
list.getFirst();
list.getLast();
list.peekFirst();                                 // null if empty
list.peekLast();
list.pollFirst();                                 // remove and return, null if empty
list.pollLast();

// Queue operations
list.offer(5);                                    // add to tail
list.poll();                                      // remove from head
list.peek();                                      // view head
list.element();                                   // view head, throws if empty
list.remove();                                    // remove head, throws if empty

// Stack operations
list.push(5);                                     // addFirst
list.pop();                                       // removeFirst
```

### Collectors for Arrays/Lists

```java
import java.util.stream.Collectors;

List<Integer> list = Arrays.asList(1,2,3,4,5,2,3);

// toList
List<Integer> result = list.stream().collect(Collectors.toList());

// toSet
Set<Integer> set = list.stream().collect(Collectors.toSet());

// toCollection
ArrayList<Integer> arrayList = list.stream()
    .collect(Collectors.toCollection(ArrayList::new));

// joining (for strings)
String joined = list.stream()
    .map(String::valueOf)
    .collect(Collectors.joining(", ", "[", "]"));  // [1, 2, 3]

// counting
long count = list.stream().collect(Collectors.counting());

// summingInt
int sum = list.stream().collect(Collectors.summingInt(Integer::intValue));

// averagingInt
double avg = list.stream().collect(Collectors.averagingInt(Integer::intValue));

// summarizingInt (all stats at once)
IntSummaryStatistics stats = list.stream()
    .collect(Collectors.summarizingInt(Integer::intValue));
stats.getCount();
stats.getSum();
stats.getMin();
stats.getMax();
stats.getAverage();

// groupingBy
Map<Integer, List<Integer>> grouped = list.stream()
    .collect(Collectors.groupingBy(x -> x % 2));  // by even/odd

Map<Integer, Long> frequency = list.stream()
    .collect(Collectors.groupingBy(x -> x, Collectors.counting()));

Map<Integer, Integer> sumByGroup = list.stream()
    .collect(Collectors.groupingBy(x -> x % 2,
             Collectors.summingInt(Integer::intValue)));

// partitioningBy (boolean key)
Map<Boolean, List<Integer>> partitioned = list.stream()
    .collect(Collectors.partitioningBy(x -> x > 3));

// toMap
Map<Integer, Integer> map = list.stream()
    .collect(Collectors.toMap(x -> x, x -> x * x, (a,b) -> a)); // key, value, merge

// reducing
int product = list.stream()
    .collect(Collectors.reducing(1, (a,b) -> a * b));

Optional<Integer> max = list.stream()
    .collect(Collectors.reducing(Integer::max));

// filtering (downstream)
Map<Integer, Long> filteredFreq = list.stream()
    .collect(Collectors.groupingBy(x -> x,
             Collectors.filtering(x -> x > 2, Collectors.counting())));

// mapping (downstream)
Map<Integer, List<Integer>> mapped = list.stream()
    .collect(Collectors.groupingBy(x -> x % 2,
             Collectors.mapping(x -> x * 2, Collectors.toList())));

// collectingAndThen (post-process)
List<Integer> unmodifiable = list.stream()
    .collect(Collectors.collectingAndThen(
        Collectors.toList(),
        Collections::unmodifiableList));
```

### Comparator Factory Methods

```java
// Natural order
Comparator<Integer> natural = Comparator.naturalOrder();
Comparator<Integer> reverse = Comparator.reverseOrder();

// Comparing by key
Comparator<String> byLength = Comparator.comparing(String::length);
Comparator<String> byLengthReverse = Comparator.comparing(String::length).reversed();

// Comparing int/long/double (avoids boxing)
Comparator<String> byLen = Comparator.comparingInt(String::length);
Comparator<String> byLen = Comparator.comparingLong(String::length);
Comparator<String> byLen = Comparator.comparingDouble(String::length);

// Multiple criteria
Comparator<Person> cmp = Comparator
    .comparing(Person::getAge)
    .thenComparing(Person::getName)
    .thenComparingInt(Person::getHeight);

// Null handling
Comparator<Integer> nullFirst = Comparator.nullsFirst(Comparator.naturalOrder());
Comparator<Integer> nullLast = Comparator.nullsLast(Comparator.naturalOrder());

// For arrays
int[][] arr = {{1,5}, {2,3}, {1,2}};
Arrays.sort(arr, Comparator
    .comparingInt((int[] a) -> a[0])
    .thenComparingInt(a -> a[1]));
```

### Iterator & Spliterator

```java
// Iterator
Iterator<Integer> it = list.iterator();
while(it.hasNext()) {
    Integer val = it.next();
    if(val > 5) it.remove();  // only valid operation
}

// forEach remaining
it.forEachRemaining(System.out::println);

// ListIterator (bidirectional)
ListIterator<Integer> lit = list.listIterator();
lit.hasNext();
lit.next();
lit.hasPrevious();
lit.previous();
lit.nextIndex();
lit.previousIndex();
lit.remove();
lit.set(10);
lit.add(20);

// Spliterator (for parallel processing)
Spliterator<Integer> split = list.spliterator();
split.tryAdvance(x -> System.out.println(x));     // process one element
split.forEachRemaining(System.out::println);      // process remaining
Spliterator<Integer> split2 = split.trySplit();   // split for parallel

// Characteristics
split.characteristics();  // ORDERED, SIZED, SUBSIZED, etc.
split.estimateSize();
split.getExactSizeIfKnown();
```

### Immutable Collections (Java 9+)

```java
// List
List<Integer> immutable = List.of(1, 2, 3);
List<Integer> immutable = List.copyOf(list);

// Set
Set<Integer> immutable = Set.of(1, 2, 3);
Set<Integer> immutable = Set.copyOf(list);

// Map
Map<Integer, String> immutable = Map.of(1, "one", 2, "two");
Map<Integer, String> immutable = Map.ofEntries(
    Map.entry(1, "one"),
    Map.entry(2, "two")
);

// All throw UnsupportedOperationException on modification
// All are null-hostile (throw NPE on null)
```

### Array Deque (Recommended over Stack)

```java
Deque<Integer> stack = new ArrayDeque<>();        // use as stack
stack.push(1);
stack.pop();
stack.peek();

Deque<Integer> queue = new ArrayDeque<>();        // use as queue
queue.offer(1);
queue.poll();
queue.peek();
```

---
