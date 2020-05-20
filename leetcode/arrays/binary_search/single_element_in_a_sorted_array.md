### 540. Single Element in a Sorted Array

https://leetcode.com/problems/single-element-in-a-sorted-array/

You are given a sorted array consisting of only integers where every element appears exactly twice, except for one element which appears exactly once. Find this single element that appears only once.

Follow up: Your solution should run in O(log n) time and O(1) space.

 

Example 1:
```
Input: nums = [1,1,2,3,3,4,4,8,8]
Output: 2
```
Example 2:
```
Input: nums = [3,3,7,7,10,11,11]
Output: 10
``` 

Constraints:

- 1 <= nums.length <= 10^5
- 0 <= nums[i] <= 10^5

Solution

Method 1: Binary Search

1. int mid = (start + end)/2; is not good, better should be int mid = start + (end - start)/2; to avoid Integer overflow for start + end
2. This solution combines some cases which makes it unclear for ppl to understand, clear code but kind of more lines should be:

```java
class Solution {
    public int singleNonDuplicate(int[] nums) {
        int lo = 0, hi = nums.length - 1;

        while(lo < hi) {
            int mid = lo + (hi - lo)/2;
            // if mid is even
            if((mid & 1) == 0) {
                if(nums[mid] == nums[mid+1]) {
                    lo = mid + 2;
                } else {
                    hi = mid;
                }
            }

            // if mid is odd
            if((mid & 1) != 0) {
                if(nums[mid] == nums[mid - 1]) {
                    lo = mid + 1;
                } else {
                    hi = mid;
                }
            }
        }
        return nums[lo];
    }
}
```

Then you can merge some code:

1. The same code can be merged is: hi = mid

2. lo = mid + 2 and lo = mid + 1 can be merged to lo = mid + 1(minor lose possibly)

3. Merge the condition for checking odd/even number:

```java
int temp = mid % 2 == 0 ? mid + 1: mid - 1;
if (nums[mid] == nums[temp]) {
	lo = mid + 1;
} else {
	hi = mid;
}
```
Final code:

```java
class Solution {
    public int singleNonDuplicate(int[] nums) {
        // corner case
        if (nums == null || nums.length == 0)
            return -1;

        int lo = 0;
        int hi = nums.length - 1;
        while (lo < hi) {
            int mid = lo + (hi - lo) / 2;
            // trick here
            // int temp = mid % 2 == 0 ? mid + 1: mid - 1;
            int temp = mid ^ 1; // if even, mid + 1; if odd, mid - 1
            if (nums[mid] == nums[temp]) {
                // if mid is even, then nums[mid] = nums[mid + 1], single number is >= mid + 2
                // if mid is odd, then nums[mid] = nums[mid - 1], single number is >= mid + 1
                // so we choose mid + 1
                lo = mid + 1;
            } else {
                // maybe nums[hi] is the single numer or
                // maybe the single number is to the left of nums[hi]
                // <= hi
                hi = mid;
            }
        }
        return nums[lo];
    }
}
```