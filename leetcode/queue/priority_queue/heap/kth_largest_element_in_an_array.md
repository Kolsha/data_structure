### 215. Kth Largest Element in an Array

https://leetcode.com/problems/kth-largest-element-in-an-array/

Find the kth largest element in an unsorted array. Note that it is the kth largest element in the sorted order, not the kth distinct element.

Example 1:
```
Input: [3,2,1,5,6,4] and k = 2
Output: 5
```
Example 2:
```
Input: [3,2,3,1,2,4,5,5,6] and k = 4
Output: 4
```
Note:
- You may assume k is always valid, 1 ≤ k ≤ array's length.

Solution

Approach 1: Sorting

Complexity analysis:
- Time complexity: O(nlogn)
- Space complexity: O(1)

```java
class Solution {
    public int findKthLargest(int[] nums, int k) {
        final int N = nums.length;
        Arrays.sort(nums);
        return nums[N - k];
    }
}
```

Approach 2: Heap

Complexity analysis:
- Time complexity: O(nlogn)
- Space complexity: O(n)

```java
class Solution {
    public int findKthLargest(int[] nums, int k) {
        Comparator<Integer> comp = new Comparator<>() {
            @Override
            public int compare(Integer a, Integer b) {
                return b - a;
            }
        };
        
        PriorityQueue<Integer> queue = new PriorityQueue<>(comp);
        for(int num : nums) {
            queue.add(num);
        }
        while(k > 1) {
            queue.poll();
            k--;
        }
        return queue.peek();
    }
}
```

```java
class Solution {
    public int findKthLargest(int[] nums, int k) {
        PriorityQueue<Integer> queue = new PriorityQueue<>((a, b) -> a - b);
        
        for(int num: nums) {
            queue.offer(num);
            if(queue.size() > k) {
                queue.poll();
            }
        }
        
        return queue.peek();
    }
}
```