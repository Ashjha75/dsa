

### 🧠 Two Pointers — Converging (Sorted Array Target Sum)

#### 1. 🧩 Concept / Theory

This technique uses **two pointers starting from opposite ends** of a **sorted array**.
We move them **towards each other** (converging) based on whether their sum is less than, greater than, or equal to a target value.

* If the current sum is **too small**, move the **left pointer rightward**.
* If the current sum is **too large**, move the **right pointer leftward**.
* If equal, we’ve found the pair.

It leverages the sorted property to eliminate half the search space in each move — like a binary search, but for pairs.

---

#### 2. 🧭 When to Use

Use this pattern when:

* The input **array is sorted** (or can be sorted).
* You need to find **pairs or triplets** satisfying a sum condition (e.g., Two Sum II, Three Sum, etc.).
* You want an **O(n)** alternative to nested loops (O(n²)).

---

#### 3. 💡 Example Problem

> **Problem:** Given a sorted array `arr` and a target sum, find the indices of two numbers that add up to the target.

**Input:** `arr = [1, 2, 3, 4, 6]`, `target = 6`
**Output:** `[1, 3]` → because `arr[1] + arr[3] = 2 + 4 = 6`

---

#### 4. 🧑‍💻 Java Implementation

```java
public class TwoSumSorted {
    public static int[] findPair(int[] arr, int target) {
        int left = 0;              // Start pointer
        int right = arr.length - 1; // End pointer

        while (left < right) {
            int sum = arr[left] + arr[right];

            if (sum == target)
                return new int[]{left, right};  // Found the pair

            if (sum < target)
                left++;  // Need a larger sum → move left pointer right
            else
                right--; // Need a smaller sum → move right pointer left
        }

        return new int[]{-1, -1}; // No valid pair
    }

    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 6};
        int target = 6;
        int[] result = findPair(arr, target);
        System.out.println("Pair indices: [" + result[0] + ", " + result[1] + "]");
    }
}
```

---

#### 5. ⏱️ Time & Space Complexity

* **Time Complexity:** `O(n)` → Each element is visited at most once.
* **Space Complexity:** `O(1)` → Constant extra space.

---

#### 6. 🧠 Dry Run

| Step | left | right | arr[left] | arr[right] | sum | Action                    |
| ---- | ---- | ----- | --------- | ---------- | --- | ------------------------- |
| 1    | 0    | 4     | 1         | 6          | 7   | sum > target → move right |
| 2    | 0    | 3     | 1         | 4          | 5   | sum < target → move left  |
| 3    | 1    | 3     | 2         | 4          | 6   | ✅ Found pair              |

✅ **Answer:** Indices `[1, 3]`



# 🧠 Two Pointers — Fast & Slow (Cycle Detection)

---

### 1. 🧩 Concept / Theory

The **Fast & Slow Pointer** technique (also called the **Tortoise and Hare** algorithm) uses two pointers moving at **different speeds** through a sequence (often a **linked list** or an **array of indices**).

* **Slow pointer** moves **one step** at a time.
* **Fast pointer** moves **two steps** at a time.

If there’s a **cycle (loop)** in the structure, the fast pointer will eventually “lap” the slow pointer — i.e., both will point to the same node again.
If there’s **no cycle**, the fast pointer will reach the end (`null`) first.

This is a clever way to detect loops **without using extra memory**, unlike hash-based approaches.

---

### 2. 🧭 When to Use

Use this pattern when:

* You need to **detect a cycle** in a **linked list**.
* You want to **find the start of the cycle** after confirming its existence.
* You are working on problems that involve **iterative relationships** or **repeated states** (e.g., detecting cycles in number transformations or arrays).

**Common problems:**

* Detect cycle in a linked list.
* Find cycle length or entry point.
* Happy Number problem (LeetCode #202).

---

### 3. 💡 Example Problem

> **Problem:**
> Given the head of a linked list, determine if the list has a cycle.

**Example:**
Input: `head = [3 → 2 → 0 → -4 ↘]`
Loop: tail connects to node index 1
Output: `true`

---

### 4. 🧑‍💻 Java Implementation

```java
class ListNode {
    int val;
    ListNode next;
    ListNode(int x) { val = x; }
}

public class LinkedListCycle {
    public static boolean hasCycle(ListNode head) {
        if (head == null || head.next == null) return false;

        ListNode slow = head;       // Moves 1 step
        ListNode fast = head;       // Moves 2 steps

        while (fast != null && fast.next != null) {
            slow = slow.next;       // Move slow by 1
            fast = fast.next.next;  // Move fast by 2

            if (slow == fast)       // If they meet → cycle exists
                return true;
        }

        return false; // Fast reached end → no cycle
    }

    public static void main(String[] args) {
        // Create nodes
        ListNode head = new ListNode(3);
        head.next = new ListNode(2);
        head.next.next = new ListNode(0);
        head.next.next.next = new ListNode(-4);
        head.next.next.next.next = head.next; // Create cycle

        System.out.println("Cycle present: " + hasCycle(head)); // true
    }
}
```

---

### 5. ⏱️ Time & Space Complexity

| Complexity        | Explanation                                              |
| ----------------- | -------------------------------------------------------- |
| **Time:** `O(n)`  | In the worst case, both pointers traverse the list once. |
| **Space:** `O(1)` | Only two pointer variables are used.                     |

---

### 6. 🧠 Dry Run (Visualization)

Consider the linked list:
`3 → 2 → 0 → -4 ↘`
          ↑
             

| Step | Slow | Fast | Comment                    |
| ---- | ---- | ---- | -------------------------- |
| 1    | 3    | 3    | Start                      |
| 2    | 2    | 0    | Fast moves 2×              |
| 3    | 0    | 2    | —                          |
| 4    | -4   | -4   | ✅ They meet → Cycle exists |

---

### 🧩 Summary

| Aspect           | Description                                                         |
| ---------------- | ------------------------------------------------------------------- |
| **Pattern Type** | Fast & Slow Pointers                                                |
| **Best For**     | Detecting cycles, finding middle, loop entry                        |
| **Core Idea**    | Two pointers move at different speeds; meeting point signals a loop |

---


# 🧠 Two Pointers — Fixed Separation (Nth Node from End)

---

### 1. 🧩 Concept / Theory

The **Fixed Separation** two-pointer technique uses **two pointers separated by a fixed distance**.

* One pointer (`first`) is advanced by `n` steps ahead of the other pointer (`second`).
* Then, both pointers move **together one step at a time** until the `first` pointer reaches the end.
* At that point, the `second` pointer points to the **nth node from the end**.

This avoids the need to first **calculate the length of the list** and allows a **single-pass solution**.

---

### 2. 🧭 When to Use

Use this pattern when:

* You need to **find the nth node from the end** of a linked list.
* You want to **remove the nth node** from the end.
* You need to solve problems in **a single pass** without extra memory.

**Common problems:**

* Remove Nth node from end of linked list (LeetCode #19)
* Return the middle of a linked list (special case: n = length/2)

---

### 3. 💡 Example Problem

> **Problem:**
> Given a linked list `1 → 2 → 3 → 4 → 5` and `n = 2`, return the **2nd node from the end**.

**Input:** `head = [1,2,3,4,5]`, `n = 2`
**Output:** `4` → because the 2nd node from the end is `4`.

---

### 4. 🧑‍💻 Java Implementation

```java
class ListNode {
    int val;
    ListNode next;
    ListNode(int x) { val = x; }
}

public class NthNodeFromEnd {
    public static ListNode findNthFromEnd(ListNode head, int n) {
        ListNode first = head;
        ListNode second = head;

        // Move first pointer n steps ahead
        for (int i = 0; i < n; i++) {
            if (first == null) return null; // n is larger than list size
            first = first.next;
        }

        // Move both pointers until first reaches the end
        while (first != null) {
            first = first.next;
            second = second.next;
        }

        return second; // Second now points to nth node from end
    }

    public static void main(String[] args) {
        // Create linked list 1 → 2 → 3 → 4 → 5
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);
        head.next.next.next.next = new ListNode(5);

        int n = 2;
        ListNode nthNode = findNthFromEnd(head, n);
        System.out.println("Nth node from end: " + nthNode.val); // 4
    }
}
```

---

### 5. ⏱️ Time & Space Complexity

| Complexity        | Explanation                                         |
| ----------------- | --------------------------------------------------- |
| **Time:** `O(L)`  | Single pass through the list (`L` = length of list) |
| **Space:** `O(1)` | Only two pointers used                              |

---

### 6. 🧠 Dry Run

Linked list: `1 → 2 → 3 → 4 → 5`, `n = 2`

| Step                       | First Pointer | Second Pointer | Comment                         |
| -------------------------- | ------------- | -------------- | ------------------------------- |
| Initial                    | 1             | 1              | Move first n=2 steps ahead      |
| After moving first n steps | 3             | 1              |                                 |
| Move together              | 4             | 2              |                                 |
| Move together              | 5             | 3              |                                 |
| Move together              | null          | 4              | ✅ Second points to 2nd from end |

---

✅ **Answer:** Node with value `4`



# 🧠 Two Pointers — In-place Array Modification

---

### 1. 🧩 Concept / Theory

The **In-place Array Modification** pattern uses **two pointers to modify an array without extra space**.

* **One pointer** (fast) iterates through the array to inspect elements.
* **Another pointer** (slow) keeps track of the **position where the next valid element should be placed**.
* This is often used for **removing or moving elements** in-place while maintaining relative order.

Key idea: Instead of creating a new array, we **overwrite unwanted elements** by shifting valid elements forward.

---

### 2. 🧭 When to Use

Use this pattern when:

* You need to **remove duplicates** or specific values from an array in-place.
* You want to **move all zeros** to the end of the array while keeping order.
* You need **O(1) extra space** solutions instead of creating new arrays.

**Common problems:**

* Remove Element (LeetCode #27)
* Remove Duplicates from Sorted Array (LeetCode #26)
* Move Zeroes (LeetCode #283)

---

### 3. 💡 Example Problem

> **Problem:**
> Remove all occurrences of `val = 3` from `nums = [0,1,3,3,4,3,5]` **in-place** and return the new length.

**Input:** `[0,1,3,3,4,3,5]`, `val = 3`
**Output:** `[0,1,4,5,...]`, new length = 4

---

### 4. 🧑‍💻 Java Implementation

```java
public class RemoveElement {
    public static int removeElement(int[] nums, int val) {
        int slow = 0; // Position to place next valid element

        for (int fast = 0; fast < nums.length; fast++) {
            if (nums[fast] != val) {
                nums[slow] = nums[fast]; // Copy valid element forward
                slow++; // Move slow pointer
            }
        }

        return slow; // New length of modified array
    }

    public static void main(String[] args) {
        int[] nums = {0, 1, 3, 3, 4, 3, 5};
        int val = 3;
        int newLength = removeElement(nums, val);

        System.out.print("Modified array: [");
        for (int i = 0; i < newLength; i++) {
            System.out.print(nums[i] + (i < newLength - 1 ? ", " : ""));
        }
        System.out.println("]");
        System.out.println("New length: " + newLength);
    }
}
```

---

### 5. ⏱️ Time & Space Complexity

| Complexity        | Explanation                           |
| ----------------- | ------------------------------------- |
| **Time:** `O(n)`  | Single pass through the array         |
| **Space:** `O(1)` | In-place modification, no extra space |

---

### 6. 🧠 Dry Run

`nums = [0,1,3,3,4,3,5]`, `val = 3`

| Fast | Slow | nums after operation | Comment              |
| ---- | ---- | -------------------- | -------------------- |
| 0    | 0    | [0,...]              | 0 ≠ 3 → copy, slow++ |
| 1    | 1    | [0,1,...]            | 1 ≠ 3 → copy, slow++ |
| 2    | 2    | [0,1,...]            | 3 = val → skip       |
| 3    | 2    | [0,1,...]            | 3 = val → skip       |
| 4    | 2    | [0,1,4,...]          | 4 ≠ 3 → copy, slow++ |
| 5    | 3    | [0,1,4,...]          | 3 = val → skip       |
| 6    | 3    | [0,1,4,5,...]        | 5 ≠ 3 → copy, slow++ |

✅ **Modified array:** `[0,1,4,5]`, new length = 4

---



# 🧠 Two Pointers — String Comparison with Backspaces

---

### 1. 🧩 Concept / Theory

This pattern uses **two pointers moving from the end of strings towards the start** to simulate **backspace operations (`#`)** without actually building the resulting strings.

* **Pointers start at the last character** of each string.
* Move backwards, **skipping characters that are "backspaced"** using a counter for `#`.
* Compare the characters from the end after applying backspaces.

Key idea: **Process the string backwards**, so backspaces can be applied **on-the-fly** without extra space.

---

### 2. 🧭 When to Use

Use this pattern when:

* You need to **compare two strings** that include **backspace characters**.
* You want to **avoid using extra space** to reconstruct strings.
* The string may be **very large**, making in-place processing more efficient.

**Common problems:**

* Backspace String Compare (LeetCode #844)
* Comparing processed input logs with edits
* Undo-like behavior in text editors

---

### 3. 💡 Example Problem

> **Problem:**
> Compare two strings `S` and `T` with `#` representing backspaces, and check if they are equal after applying all backspaces.

**Input:** `S = "ab#c"`, `T = "ad#c"`
**Output:** `true` → Both become `"ac"` after backspaces.

---

### 4. 🧑‍💻 Java Implementation

```java
public class BackspaceStringCompare {

    public static boolean backspaceCompare(String S, String T) {
        int i = S.length() - 1;
        int j = T.length() - 1;

        while (i >= 0 || j >= 0) {
            int skipS = 0, skipT = 0;

            // Move i backward, skipping backspaces
            while (i >= 0) {
                if (S.charAt(i) == '#') { skipS++; i--; }
                else if (skipS > 0) { skipS--; i--; }
                else break;
            }

            // Move j backward, skipping backspaces
            while (j >= 0) {
                if (T.charAt(j) == '#') { skipT++; j--; }
                else if (skipT > 0) { skipT--; j--; }
                else break;
            }

            // Compare current characters
            if (i >= 0 && j >= 0 && S.charAt(i) != T.charAt(j)) return false;
            if ((i >= 0) != (j >= 0)) return false; // One string finished earlier

            i--; j--; // Move to next characters
        }

        return true;
    }

    public static void main(String[] args) {
        String S = "ab#c";
        String T = "ad#c";
        System.out.println("Strings equal after backspaces? " + backspaceCompare(S, T)); // true
    }
}
```

---

### 5. ⏱️ Time & Space Complexity

| Complexity           | Explanation                                                           |
| -------------------- | --------------------------------------------------------------------- |
| **Time:** `O(n + m)` | n = length of S, m = length of T; each character visited at most once |
| **Space:** `O(1)`    | Only pointers and counters used, no extra string reconstruction       |

---

### 6. 🧠 Dry Run

`S = "ab#c"`, `T = "ad#c"`

| i (S) | j (T) | Current S char | Current T char | Action                         |
| ----- | ----- | -------------- | -------------- | ------------------------------ |
| 3     | 3     | 'c'            | 'c'            | Compare → equal, move left     |
| 2     | 2     | '#'            | '#'            | Count backspace, skip          |
| 1     | 1     | 'b'            | 'd'            | Skip b and d due to backspaces |
| 0     | 0     | 'a'            | 'a'            | Compare → equal                |

✅ **Result:** Strings are equal after applying backspaces.


---

# 🧠 Two Pointers — Expanding From Center (Palindromes)

---

### 1. 🧩 Concept / Theory

The **Expanding From Center** pattern is commonly used to **find palindromic substrings**.

* A palindrome reads the same forward and backward.
* The idea: consider **each character (or pair of characters) as a center** and **expand pointers outward** (left and right) as long as characters match.
* This efficiently checks for all palindromes **without generating all substrings explicitly**.

Key idea: **Two pointers move in opposite directions from a center** to check symmetry.

---

### 2. 🧭 When to Use

Use this pattern when:

* You need to **find the longest palindrome** or **count all palindromic substrings**.
* Problems involve **string symmetry** around a center.
* You want an **O(n²)** solution that’s simple and avoids extra space for storing substrings.

**Common problems:**

* Longest Palindromic Substring (LeetCode #5)
* Palindromic Substrings count (LeetCode #647)
* Checking if a string or substring is a palindrome

---

### 3. 💡 Example Problem

> **Problem:**
> Given a string `s = "babad"`, find the **longest palindromic substring**.

**Input:** `"babad"`
**Output:** `"bab"` or `"aba"`

---

### 4. 🧑‍💻 Java Implementation

```java
public class LongestPalindrome {

    // Expand around center
    private static String expand(String s, int left, int right) {
        while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
            left--;
            right++;
        }
        return s.substring(left + 1, right); // Extract palindrome
    }

    public static String longestPalindrome(String s) {
        if (s == null || s.length() < 1) return "";

        String longest = "";
        for (int i = 0; i < s.length(); i++) {
            // Odd length palindrome
            String odd = expand(s, i, i);
            // Even length palindrome
            String even = expand(s, i, i + 1);

            // Update longest palindrome
            if (odd.length() > longest.length()) longest = odd;
            if (even.length() > longest.length()) longest = even;
        }

        return longest;
    }

    public static void main(String[] args) {
        String s = "babad";
        System.out.println("Longest palindrome: " + longestPalindrome(s)); // "bab" or "aba"
    }
}
```

---

### 5. ⏱️ Time & Space Complexity

| Complexity        | Explanation                                                                 |
| ----------------- | --------------------------------------------------------------------------- |
| **Time:** `O(n²)` | Each center expands at most n times, for n centers (including odd and even) |
| **Space:** `O(1)` | Only pointers and substring indices used (excluding output)                 |

---

### 6. 🧠 Dry Run

`s = "babad"`

**Step-by-step expansion:**

| Center    | Left | Right | Palindrome Found     |
| --------- | ---- | ----- | -------------------- |
| i=0 ('b') | 0    | 0     | "b"                  |
| i=1 ('a') | 1    | 1     | "a" → expand → "bab" |
| i=2 ('b') | 2    | 2     | "b" → expand → "aba" |
| i=3 ('a') | 3    | 3     | "a"                  |
| i=4 ('d') | 4    | 4     | "d"                  |

✅ **Longest palindrome:** `"bab"` (or `"aba"`)

---




# 🧠 Two Pointers — String Reversal

---

### 1. 🧩 Concept / Theory

The **String Reversal** pattern uses **two pointers moving towards each other** to reverse a string (or a part of it) **in-place**.

* **One pointer starts at the beginning** of the string/array.
* **The other pointer starts at the end**.
* Swap the characters at the two pointers and move them **towards each other** until they meet or cross.

Key idea: Efficiently reverses the string **without using extra space** for a new array.

---

### 2. 🧭 When to Use

Use this pattern when:

* You need to **reverse a string or a substring** in-place.
* You want to **swap characters symmetrically**.
* Commonly used as a **helper technique** in other string manipulation problems.

**Common problems:**

* Reverse String (LeetCode #344)
* Reverse Words in a String (LeetCode #151)
* Palindrome checking and transformations

---

### 3. 💡 Example Problem

> **Problem:**
> Reverse the string `"hello"` **in-place**.

**Input:** `"hello"`
**Output:** `"olleh"`

---

### 4. 🧑‍💻 Java Implementation

```java
public class ReverseString {

    public static void reverseCharArray(char[] s) {
        int left = 0;
        int right = s.length - 1;

        while (left < right) {
            // Swap characters
            char temp = s[left];
            s[left] = s[right];
            s[right] = temp;

            // Move pointers towards each other
            left++;
            right--;
        }
    }

    public static void main(String[] args) {
        char[] s = {'h','e','l','l','o'};
        reverseCharArray(s);

        System.out.print("Reversed string: ");
        for (char c : s) System.out.print(c); // olleh
    }
}
```

---

### 5. ⏱️ Time & Space Complexity

| Complexity        | Explanation                            |
| ----------------- | -------------------------------------- |
| **Time:** `O(n)`  | Each character is visited/swapped once |
| **Space:** `O(1)` | In-place reversal, no extra array used |

---

### 6. 🧠 Dry Run

`s = ['h','e','l','l','o']`

| Step | left | right | Array after swap      |
| ---- | ---- | ----- | --------------------- |
| 1    | 0    | 4     | ['o','e','l','l','h'] |
| 2    | 1    | 3     | ['o','l','l','e','h'] |
| 3    | 2    | 2     | Stop (pointers meet)  |

✅ **Reversed string:** `"olleh"`

---

🎯 **Summary of the 7 Two Pointer Patterns**

| Pattern                         | Key Idea                                                     |
| ------------------------------- | ------------------------------------------------------------ |
| Converging                      | Two pointers from opposite ends, e.g., target sum            |
| Fast & Slow                     | Two speeds to detect cycles                                  |
| Fixed Separation                | Two pointers separated by fixed distance, e.g., nth from end |
| In-place Array Modification     | Fast/slow pointers to overwrite unwanted elements            |
| String Comparison w/ Backspaces | Two pointers from end to compare strings with edits          |
| Expanding From Center           | Expand left/right to find palindromes                        |
| String Reversal                 | Swap characters with two pointers towards center             |

---

