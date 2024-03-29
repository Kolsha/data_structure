### [523. Continuous Subarray Sum](https://leetcode.com/problems/continuous-subarray-sum/)


Given a list of **non-negative** numbers and a target **integer** k, write a function to check if the array has a continuous subarray of size at least 2 that sums up to a multiple of *k*, that is, sums up to n*k where n is also an **integer**.

 

Example 1:
```
Input: [23, 2, 4, 6, 7],  k=6
Output: True
Explanation: Because [2, 4] is a continuous subarray of size 2 and sums up to 6.
```
Example 2:
```
Input: [23, 2, 6, 4, 7],  k=6
Output: True
Explanation: Because [23, 2, 6, 4, 7] is an continuous subarray of size 5 and sums up to 42.
``` 

Note:

- The length of the array won't exceed 10,000.
- You may assume the sum of all the numbers is in the range of a signed 32-bit integer.


##### Solution

##### [Approach 1: Math](https://leetcode.com/problems/continuous-subarray-sum/discuss/150330/Math-behind-the-solutions)
假设第 i 个数组和为 rem，接下来，如果有任何的连续数组和为 k 倍，则那第 j 个数组和为 rem + n * k 

##### Complexity analysis
- Time complexity: O(n)
- Space complexity: O(k)

```java
class Solution {
    public boolean checkSubarraySum(int[] nums, int k) {
        HashMap<Integer, Integer> map = new HashMap<>();
        map.put(0, -1);

        int rem = 0;
        for (int i = 0; i < nums.length; i++) {
            rem += nums[i];
            if (k != 0) {
                rem = rem % k;
            }
            Integer prev = map.get(rem);
            if (prev != null) {
                if (i - prev > 1) {
                    return true;
                }
            } else {
                map.put(rem, i);
            }
        }
        return false;

    }
}
```