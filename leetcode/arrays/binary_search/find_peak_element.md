### [162. Find Peak Element](https://leetcode.com/problems/find-peak-element/)


A peak element is an element that is greater than its neighbors.

Given an input array nums, where nums[i] ≠ nums[i+1], find a peak element and return its index.

The array may contain multiple peaks, in that case return the index to any one of the peaks is fine.

You may imagine that nums[-1] = nums[n] = -∞.

Example 1:
```
Input: nums = [1,2,3,1]
Output: 2
Explanation: 3 is a peak element and your function should return the index number 2.
```
Example 2:
```
Input: nums = [1,2,1,3,5,6,4]
Output: 1 or 5 
Explanation: Your function can return either index number 1 where the peak element is 2, 
             or index number 5 where the peak element is 6.
```

**Follow up:** Your solution should be in logarithmic complexity.

### Solution

#### Approach 1: Linear Scan
case 1:
![](https://leetcode.com/problems/find-peak-element/Figures/162/Find_Peak_Case1.PNG)

case 2:
![](https://leetcode.com/problems/find-peak-element/Figures/162/Find_Peak_Case2.PNG)

case 3:
![](https://leetcode.com/problems/find-peak-element/Figures/162/Find_Peak_Case3.PNG)

##### Complexity analysis:
- Time complexity: O(n)
- Space complexity: O(1)

```java
public class Solution {
    public int findPeakElement(int[] nums) {
        for (int i = 0; i < nums.length - 1; i++) {
            if (nums[i] > nums[i + 1])
                return i;
        }
        return nums.length - 1;
    }
}
```

#### Approach 2: Recursive Binary Search
##### Algorithm

We can view any given sequence in numsnums array as alternating ascending and descending sequences. By making use of this, and the fact that we can return any peak as the result, we can make use of Binary Search to find the required peak element.

In case of simple Binary Search, we work on a sorted sequence of numbers and try to find out the required number by reducing the search space at every step. In this case, we use a modification of this simple Binary Search to our advantage. We start off by finding the middle element, mid from the given numsnums array. If this element happens to be lying in a descending sequence of numbers. or a local falling slope(found by comparing nums[i] to its right neighbour), it means that the peak will always lie towards the left of this element. Thus, we reduce the search space to the left of mid(including itself) and perform the same process on left subarray.

If the middle element, mid lies in an ascending sequence of numbers, or a rising slope(found by comparing nums[i] to its right neighbour), it obviously implies that the peak lies towards the right of this element. Thus, we reduce the search space to the right of midmid and perform the same process on the right subarray.

In this way, we keep on reducing the search space till we eventually reach a state where only one element is remaining in the search space. This single element is the peak element.

```java
public class Solution {
    public int findPeakElement(int[] nums) {
        return search(nums, 0, nums.length - 1);
    }
    public int search(int[] nums, int l, int r) {
        if (l == r) {
            return l;
        }
        int mid = (l + r) / 2;
        if (nums[mid] > nums[mid + 1]) {
            return search(nums, l, mid);
        }
        return search(nums, mid + 1, r);
    }
}
```