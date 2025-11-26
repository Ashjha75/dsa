## Collections Framework Deep Dive

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
