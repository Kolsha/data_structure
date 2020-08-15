### 350. Intersection of Two Arrays II

https://leetcode.com/problems/intersection-of-two-arrays-ii/

Given two arrays, write a function to compute their intersection.

Example 1:
```
Input: nums1 = [1,2,2,1], nums2 = [2,2]
Output: [2,2]
```
Example 2:
```
Input: nums1 = [4,9,5], nums2 = [9,4,9,8,4]
Output: [4,9]
```
Note:

- Each element in the result should appear as many times as it shows in both arrays.
- The result can be in any order.

Follow up:

- What if the given array is already sorted? How would you optimize your algorithm?
- What if nums1's size is small compared to nums2's size? Which algorithm is better?
- What if elements of nums2 are stored on disk, and the memory is limited such that you cannot load all elements into the memory at once?

Solution

Approach 1: HashMap

Complexity Analysis
- Time complexity: O(n + m)
- Space complexity: O(min(n, m))
```java
public int[] intersect(int[] nums1, int[] nums2) {
    if (nums1.length > nums2.length) {
        return intersect(nums2, nums1);
    }
    HashMap<Integer, Integer> m = new HashMap<>();
    for (int n : nums1) {
        m.put(n, m.getOrDefault(n, 0) + 1);
    }
    int k = 0;
    for (int n : nums2) {
        int cnt = m.getOrDefault(n, 0);
        if (cnt > 0) {
            nums1[k++] = n;
            m.put(n, cnt - 1);
        }
    }
    return Arrays.copyOfRange(nums1, 0, k);
}
```
Follow-ups 1:
Complexity analysis
- Time Complexity: O(nlogn + mlogm), where n and m are the lengths of the arrays. We sort two arrays independently, and then do a linear scan.
- Space Complexity: from O(logn + logm) to O(n + m), depending on the implementation of the sorting algorithm. For the complexity analysis purposes, we ignore the memory required by inputs and outputs.




```java
public int[] intersect(int[] nums1, int[] nums2) {
    Arrays.sort(nums1);
    Arrays.sort(nums2);
    int i = 0, j = 0, k = 0;
    while (i < nums1.length && j < nums2.length) {
        if (nums1[i] < nums2[j]) {
            ++i;
        } else if (nums1[i] > nums2[j]) {
            ++j;
        } else {
            nums1[k++] = nums1[i++];
            ++j;
        }
    }
    return Arrays.copyOfRange(nums1, 0, k);
}
```

Follow - ups 3:
- If only nums2 cannot fit in memory, put all elements of nums1 into a HashMap, read chunks of array that fit into the memory, and record the intersections.

- If both nums1 and nums2 are so huge that neither fit into the memory, sort them individually (external sort), then read 2 elements from each array at a time in memory, record intersections.
