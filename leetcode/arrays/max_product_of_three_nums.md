### 628. Maximum Product of Three Numbers

Given an integer array, find three numbers whose product is maximum and output the maximum product.

Example 1:
```
Input: [1,2,3]
Output: 6
``` 

Example 2:
```
Input: [1,2,3,4]
Output: 24
``` 

Note:

1. The length of the given array will be in range [3,104] and all elements are in the range [-1000, 1000].
2. Multiplication of any three numbers in the input won't exceed the range of 32-bit signed integer.

Solution:
Method: Just compare the product of the 3 largest values and product of the 2 smallest value and the largest value.

```java
class Solution {
    public int maximumProduct(int[] nums) {
        Arrays.sort(nums);
        return Math.max(nums[0]*nums[1]*nums[nums.length-1], nums[nums.length-3]*nums[nums.length-2]*nums[nums.length-1]);
    }
}
```

```java
class Solution {
    public int maximumProduct(int[] nums) {
        int max1 = Integer.MIN_VALUE, max2 = Integer.MIN_VALUE, max3 = Integer.MIN_VALUE, min1 = Integer.MAX_VALUE,
                min2 = Integer.MAX_VALUE;
        for (int value : nums) {
            if (value > max1) {
                max3 = max2;
                max2 = max1;
                max1 = value;
            } else if (value > max2) {
                max3 = max2;
                max2 = value;
            } else if (value > max3) {
                max3 = value;
            }

            if (value < min1) {
                min2 = min1;
                min1 = value;
            } else if (value < min2) {
                min2 = value;
            }

        }

        return Math.max(max1 * max2 * max3, min1 * min2 * max1);
    }
}
```