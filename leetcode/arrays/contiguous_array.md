### 525. Contiguous Array

https://leetcode.com/problems/contiguous-array/

Given a binary array, find the maximum length of a contiguous subarray with equal number of 0 and 1.

Example 1:
```
Input: [0,1]
Output: 2
Explanation: [0, 1] is the longest contiguous subarray with equal number of 0 and 1.
```
Example 2:
```
Input: [0,1,0]
Output: 2
Explanation: [0, 1] (or [1, 0]) is a longest contiguous subarray with equal number of 0 and 1.
```

- Note: The length of the given binary array will not exceed 50,000.

Solution

Method 1: HashMap<Sum, Index> approach

Time complexity: O(n)

Space complexity: O(n)

```java
class Solution {
    public int findMaxLength(int[] nums) {
        if(nums == null || nums.length < 2) {
            return 0;
        }
        
        for(int i = 0; i < nums.length; i++) {
            if(nums[i] == 0) {
                nums[i] = -1;
            }
        }

        int max = 0;
        // sum, index mapping
        HashMap<Integer, Integer> map = new HashMap<>();
        map.put(0, -1);
        int sum = 0;
        for(int i = 0; i < nums.length; i++) {
            sum += nums[i];
            if(map.containsKey(sum)) {
                max = Math.max(max, i - map.get(sum));
            } else {
                map.put(sum, i);
            }
        }
        return max;
    }
}
```