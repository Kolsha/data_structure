### 152. Maximum Product Subarray
https://leetcode.com/problems/maximum-product-subarray/

Given an integer array nums, find the contiguous subarray within an array (containing at least one number) which has the largest product.

Example 1:
```
Input: [2,3,-2,4]
Output: 6
Explanation: [2,3] has the largest product 6.
```
Example 2:
```
Input: [-2,0,-1]
Output: 0
Explanation: The result cannot be 2, because [-2,-1] is not a subarray.
```

Solution
Failed: Time limit Exceeded
```java
class Solution {
    public int maxProduct(int[] nums) {
        int max = Integer.MIN_VALUE;
        for (int len = 1; len <= nums.length; len++) {
            for (int i = 0; i < nums.length; i++) {
                int j = i + len - 1;
                int product = nums[i];
                while (i < j && j < nums.length) {
                    product *= nums[j];
                    j--;
                }
                max = Math.max(max, product);
            }
        }
        return max;
    }
}
```

Method 2
```java
class Solution {
    public int maxProduct(int[] nums) {
        if(nums == null || nums.length == 0) {
            return 0;
        }

        int max = nums[0], min = nums[0], result = nums[0];
        for(int i = 1; i < nums.length; i++) {
            int temp = max;
            max = Math.max(Math.max(max*nums[i], min*nums[i]), nums[i]);
            min = Math.min(Math.min(temp*nums[i], min*nums[i]), nums[i]);
            if(max > result) {
                result = max;
            }
        }

        return result;
    }
}
```

