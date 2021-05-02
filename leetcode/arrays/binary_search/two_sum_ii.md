### 167. [Two Sum II - Input array is sorted](https://leetcode.com/problems/two-sum-ii-input-array-is-sorted/)

Given an array of integers that is already **sorted in ascending order**, find two numbers such that they add up to a specific target number.

The function twoSum should return indices of the two numbers such that they add up to the target, where index1 must be less than index2.

Note:

Your returned answers (both index1 and index2) are not zero-based.
You may assume that each input would have **exactly one solution** and you **may not** use the same element twice.
 

Example 1:
```
Input: numbers = [2,7,11,15], target = 9
Output: [1,2]
Explanation: The sum of 2 and 7 is 9. Therefore index1 = 1, index2 = 2.
```
Example 2:
```
Input: numbers = [2,3,4], target = 6
Output: [1,3]
```
Example 3:
```
Input: numbers = [-1,0], target = -1
Output: [1,2]
``` 

Constraints:

- 2 <= numbers.length <= 3 * $10^4$
- -1000 <= numbers[i] <= 1000
- numbers is sorted in increasing order.
- -1000 <= target <= 1000
- Only one valid answer exists.

### Solution

#### Approach 1: Two Pointers

##### Complexity analysis
- Time complexity: O(n)
- Space complexity: O(1)

##### Java
```java
class Solution {
    public int[] twoSum(int[] numbers, int target) {
        int lo = 0;
        int hi = numbers.length - 1;
        
        while(lo < hi) {
            int sum = numbers[lo] + numbers[hi];
            if(sum == target) {
                return new int[] {lo+1, hi+1};
            } else if (sum < target) {
                lo++;
            } else if(sum > target) {
                hi--;
            }
        }
        return new int[]{0, 0};
    }
}
```

##### Python
```python
class Solution:
    def twoSum(self, numbers: List[int], target: int) -> List[int]:
        lo, hi = 0, len(numbers) - 1
        while lo < hi:
            sum_val = numbers[lo] + numbers[hi]
            if sum_val == target:
                return lo+1, hi+1
            elif sum_val < target:
                lo = lo + 1
            else:
                hi = hi - 1
        return 0, 0
```

#### Approach 2: HashMap


#### Approach 3: Binary Search



ref: https://leetcode.com/problems/two-sum-ii-input-array-is-sorted/discuss/51249/Python-different-solutions-(two-pointer-dictionary-binary-search).
