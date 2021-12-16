### [153. Find Minimum in Rotated Sorted Array](https://leetcode.com/problems/find-minimum-in-rotated-sorted-array/)

Suppose an array of length `n` sorted in ascending order is **rotated** between `1` and `n` times. For example, the array `nums = [0,1,2,4,5,6,7]` might become:

- [4,5,6,7,0,1,2] if it was rotated `4` times.

- [0,1,2,4,5,6,7] if it was rotated `7` times.


Notice that **rotating** an array `[a[0], a[1], a[2], ..., a[n-1]]` 1 time results in the array `[a[n-1], a[0], a[1], a[2], ..., a[n-2]]`.

Given the sorted rotated array `nums` of **unique** elements, return the minimum element of this array.

You must write an algorithm that runs in `O(log n) time`.

 

Example 1:
```
Input: nums = [3,4,5,1,2]
Output: 1
Explanation: The original array was [1,2,3,4,5] rotated 3 times.
```
Example 2:
```
Input: nums = [4,5,6,7,0,1,2]
Output: 0
Explanation: The original array was [0,1,2,4,5,6,7] and it was rotated 4 times.
```
Example 3:
```
Input: nums = [11,13,15,17]
Output: 11
Explanation: The original array was [11,13,15,17] and it was rotated 4 times. 
``` 

Constraints:

- n == nums.length
- 1 <= n <= 5000
- -5000 <= nums[i] <= 5000
- All the integers of `nums` are **unique**.
- `nums` is sorted and rotated between `1` and `n` times.


##### Solution

##### Approach 1: Binary Search
Classic binary search problem.

Looking at subarray with index [start,end]. We can find out that if the first member is less than the last member, there's no rotation in the array. So we could directly return the first element in this subarray.

If the first element is larger than the last one, then we compute the element in the middle, and compare it with the first element. If value of the element in the middle is larger than the first element, we know the rotation is at the second half of this array. Else, it is in the first half in the array.

*Hint:
when do you use while (start < end) , when do you use while (start <= end)?

**Ans1**:
I guess any condition is ok for any problem. it's just matter of which problem is suited for which condition. For example if we use it for some problems (start<end), it requires less code and less if statements. And vice versa.

**When to use which?**
when you want to find an specific target element you use `lo <= hi`.

When you want to find an element which is not specific element you use `lo < hi`. for example when you want to find a smallest element or a lower_bound etc.

In other words, reduce search space by every step. And the remaining last element is your answer.

by the way, when while loop finishes, for some problem you need to process value of lo. because it is not processed in the while loop. because when `lo == hi` loop is over.


**Ans2**:
Using start < end and start <= end are just two of the many different ways to implement BS. The key difference is how the search terminates. In case of start <= end, we usually compare mid value to the given target inside the while loop, and terminate early if target is found.

In case of start < end, the loop terminates when start == end.
The start < end method is faster compared to the start <= end (yeah the one that terminates early is actually slower) because in the start <= end case, for every iteration of the while loop, there is that extra if condition which checks mid value to target.

However in the start < end case, this check is done only once when the loop terminates ex: nums[lo] == target or something similar (nums[hi] is also fine) . For this, the cost is 1 extra loop iteration.

**Ans3**:
We can use while (start <= end) by keeping a tracker ans = min(ans, nums[mid])

**Ans4**:
You use while (start <= end) if you are returning the match from inside the loop.

You use while (start < end) if you want to exit out of the loop first, and then use the result of start or end to return the match.

##### Complexity analysis
- Time complexity: O(logN)
- Space complexity: O(1)

```java
public class Solution {
    public int findMin(int[] nums) {
        if(nums.length == 1) {
            return nums[0];
        }

        int lo = 0, hi = nums.length - 1;
        
        while(lo < hi) {
            if(nums[lo] < nums[hi]) {
                return nums[lo];
            }
            
            int mid = lo + (hi - lo)/2;
            
            if(nums[mid] >= nums[lo]) {
                lo = mid + 1;
            } else {
                hi = mid;
            }
        }
        return nums[lo];
    }
}
```