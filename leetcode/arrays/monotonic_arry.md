### 896. Monotonic Array

https://leetcode.com/problems/monotonic-array/

An array is monotonic if it is either monotone increasing or monotone decreasing.

An array A is monotone increasing if for all i <= j, A[i] <= A[j].  An array A is monotone decreasing if for all i <= j, A[i] >= A[j].

Return true if and only if the given array A is monotonic.

 

Example 1:
```
Input: [1,2,2,3]
Output: true
```
Example 2:
```
Input: [6,5,4,4]
Output: true
```
Example 3:
```
Input: [1,3,2]
Output: false
```
Example 4:
```
Input: [1,2,4,5]
Output: true
```
Example 5:
```
Input: [1,1,1]
Output: true
``` 

Note:

1. 1 <= A.length <= 50000
2. -100000 <= A[i] <= 100000

Solution
===

Method: 1 pass

Time complexity: O(n)

Space complexity: 1

In Java
```java
class Solution {
    public boolean isMonotonic(int[] nums) {
        boolean inc = true, dec = true;
        for (int i = 1; i < nums.length; i++) {
            inc &= nums[i] >= nums[i - 1];
            dec &= nums[i - 1] >= nums[i];
        }
        return inc || dec;
    }
}
```

In Python
```python
class Solution(object):
    def isMonotonic(self, A):
        """
        :type A: List[int]
        :rtype: bool
        """
        inc, dec = True, True
        for i in range(1, len(A)):
            inc = inc and (A[i] >= A[i-1])
            dec = dec and (A[i - 1] >= A[i])
        
        return inc or dec
```