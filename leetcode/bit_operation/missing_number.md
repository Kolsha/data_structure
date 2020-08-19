### 268. Missing Number

https://leetcode.com/problems/missing-number/

Given an array containing n distinct numbers taken from 0, 1, 2, ..., n, find the one that is missing from the array.

Example 1:
```
Input: [3,0,1]
Output: 2
```
Example 2:
```
Input: [9,6,4,2,3,5,7,0,1]
Output: 8
```
Note:
Your algorithm should run in linear runtime complexity. Could you implement it using only constant extra space complexity?

Solution

Approach 1: Sum

Complexity anlaysis:
- Time complexity: O(n)
- Space complexity: O(1)

```java
class Solution {
    public int missingNumber(int[] nums) {
        int n = nums.length;
        int sum = (1 + n) * n / 2;
        for(int num: nums) {
            sum -= num;
        }
        return sum;
    }
}
```

Approach 2: Bit manipulation
Because we know that nums contains n numbers and that it is missing exactly one number on the range `[0..n-1]`, we know that nn definitely replaces the missing number in nums. Therefore, if we initialize an integer to n and XOR it with every index and value, we will be left with the missing number.

Complexity analysis:
- Time complexity: O(n)
- Space complexity: O(1)

```java
class Solution {
    public int missingNumber(int[] nums) {
        int missing = nums.length;
        for (int i = 0; i < nums.length; i++) {
            missing ^= i ^ nums[i];
        }
        return missing;
    }
}
```