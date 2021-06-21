### [659. Split Array into Consecutive Subsequences](https://leetcode.com/problems/split-array-into-consecutive-subsequences/)


You are given an integer array `nums` that is **sorted in non-decreasing order**.

Determine if it is possible to split `nums` into **one or more subsequences** such that **both** of the following conditions are true:

- Each subsequence is a consecutive increasing sequence (i.e. each integer is exactly one more than the previous integer).

- All subsequences have a length of 3 or more.

Return true if you can split nums according to the above conditions, or false otherwise.

A subsequence of an array is a new array that is formed from the original array by deleting some (can be none) of the elements without disturbing the relative positions of the remaining elements. (i.e., [1,3,5] is a subsequence of [1,2,3,4,5] while [1,3,2] is not).


Example 1:
```
Input: nums = [1,2,3,3,4,5]
Output: true
Explanation: nums can be split into the following subsequences:
[1,2,3,3,4,5] --> 1, 2, 3
[1,2,3,3,4,5] --> 3, 4, 5
```
Example 2:
```
Input: nums = [1,2,3,3,4,4,5,5]
Output: true
Explanation: nums can be split into the following subsequences:
[1,2,3,3,4,4,5,5] --> 1, 2, 3, 4, 5
[1,2,3,3,4,4,5,5] --> 3, 4, 5
```
Example 3:
```
Input: nums = [1,2,3,4,4,5]
Output: false
Explanation: It is impossible to split nums into consecutive increasing subsequences of length 3 or more.
``` 

Constraints:

- 1 <= nums.length <= $10^4$
- -1000 <= nums[i] <= 1000
- nums is sorted in non-decreasing order.

### Solution
#### Approach 1: HashMap
Apply greedy algorithm:
`left` is a hashmap, left[i] counts the number of i that I haven't placed yet.
`end` is a hashmap, end[i] counts the number of consecutive subsequences that ends at number i
Then I tried to split the nums one by one.
If I could neither add a number to the end of a existing consecutive subsequence nor find two following number in the left, I returned False

```java
class Solution {
    public boolean isPossible(int[] nums) {
        Map<Integer, Integer> left = new HashMap<>(), end = new HashMap<>();
        for (int i : nums) {
            freq.put(i, freq.getOrDefault(i, 0) + 1);
        }
    
        for (int i : nums) {
            if (freq.get(i) == 0) {
                continue;
            } else if (appendfreq.getOrDefault(i,0) > 0) {
                appendfreq.put(i, appendfreq.get(i) - 1);
                appendfreq.put(i + 1, appendfreq.getOrDefault(i+1,0) + 1);
            } else if (freq.getOrDefault(i+1,0) > 0 && freq.getOrDefault(i+2,0) > 0) {
                freq.put(i+1, freq.get(i+1) - 1);
                freq.put(i+2, freq.get(i+2) - 1);
                appendfreq.put(i+3, appendfreq.getOrDefault(i+3,0) + 1);
            } else {
                return false;
            }
            freq.put(i, freq.get(i) - 1);
        }
        return true;
    }
}
```