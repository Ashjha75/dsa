# ⚡ Moore’s Voting Algorithm (Majority Element)

## 🔹 Use Case

Use **Moore’s Voting Algorithm** to find the **majority element** (an element that appears more than ⌊n/2⌋ times) in an
array.

Think of it when:

- You are asked for the **majority element** in an array.
- The array is **not necessarily sorted**.
- There is **guaranteed to be a majority element** (or you want to check if one exists).

---

## 🔹 Intuition

- Maintain a **candidate** and a **count**.
- If count is 0, set current element as candidate.
- If current element equals candidate, increment count.
- Else, decrement count.

---

## 🔹 Java Code

```java
class Main {
    static int majorityElement(int[] nums) {
        int count = 0, candidate = 0;
        for (int num : nums) {
            if (count == 0) {
                candidate = num;
            }
            count += (num == candidate) ? 1 : -1;
        }
        // Optional: Verify candidate is actually majority
        // int freq = 0;
        // for (int num : nums) if (num == candidate) freq++;
        // if (freq > nums.length / 2) return candidate;
        // else throw new RuntimeException("No majority element");
        return candidate;
    }

    public static void main(String[] args) {
        int[] arr = {2, 2, 1, 1, 2, 2, 2};
        System.out.println("Majority Element = " + majorityElement(arr)); // Output: 2
    }
}
```

---

## 🔹 Explanation

For each element:

```
If count == 0: candidate = current element
If current == candidate: count++
Else: count--
```

* The majority element will survive as the candidate at the end.

---

## 🔹 Complexity

* **Time:** O(n)
* **Space:** O(1)

---

## 🔹 Tip

Moore’s Voting is optimal for finding a majority element in linear time and constant space.

