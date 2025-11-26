# ⚡ Kadane’s Algorithm (Maximum Subarray Sum)

## 🔹 Use Case

Use **Kadane’s Algorithm** when you need to find the **maximum sum of a contiguous subarray** in an array (like in
problems about profits, temperature streaks, score trends, etc.).

Think of it when:

- You are asked for **"maximum sum of a subarray"**.
- The array contains **positive and negative numbers**.
- The subarray must be **continuous**.

---

## 🔹 Intuition

At each element, decide:

- Continue the current subarray (add current element), **or**
- Start a new subarray from the current element.

We keep:

- `currentSum`: max sum ending at current index
- `maxSum`: best sum found so far

---

## 🔹 Java Code

```java
class Main {
    static int kadane(int[] arr) {
        int currentSum = arr[0];
        int maxSum = arr[0];

        for (int i = 1; i < arr.length; i++) {
            currentSum = Math.max(arr[i], currentSum + arr[i]);
            maxSum = Math.max(maxSum, currentSum);
        }
        return maxSum;
    }

    public static void main(String[] args) {
        int[] arr = {-2, 1, -3, 4, -1, 2, 1, -5, 4};
        System.out.println("Max Subarray Sum = " + kadane(arr)); // Output: 6
    }
}
````

---

## 🔹 Explanation

For each element:

```
currentSum = max(arr[i], currentSum + arr[i])
maxSum = max(maxSum, currentSum)
```

* If adding current element improves the sum → continue.
* If it worsens → start new subarray from current element.
* Track global maximum (`maxSum`) throughout.

---

## 🔹 Complexity

* **Time:** O(n)
* **Space:** O(1)

---

## 🔹 Tip

Kadane’s is useful whenever you hear:

> “Find maximum sum / profit / gain in a continuous sequence.”

