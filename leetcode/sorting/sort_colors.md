### 75. Sort Colors

https://leetcode.com/problems/sort-colors/


Given an array with n objects colored red, white or blue, sort them in-place so that objects of the same color are adjacent, with the colors in the order red, white and blue.

Here, we will use the integers 0, 1, and 2 to represent the color red, white, and blue respectively.

Note: You are not suppose to use the library's sort function for this problem.

Example:
```
Input: [2,0,2,1,1,0]
Output: [0,0,1,1,2,2]
```
Follow up:

A rather straight forward solution is a two-pass algorithm using counting sort.
First, iterate the array counting number of 0's, 1's, and 2's, then overwrite array with total number of 0's, then 1's and followed by 2's.
Could you come up with a one-pass algorithm using only constant space?

### Solution

#### Approach 1: 2 pass

Time Complexity: O(n)

Space Complexity: 1

In Java:
```java
class Solution {
    public void sortColors(int[] nums) {
        // 2 pass
        // int 0s
        int zeroCount = 0;
        int oneCount = 0;
        int twoCount = 0;
        for (int num : nums) {
            if (num == 0) {
                zeroCount++;
            } else if (num == 1) {
                oneCount++;
            } else {
                twoCount++;
            }
        }

        for (int i = 0; i < nums.length; i++) {
            if (i < zeroCount) {
                nums[i] = 0;
            } else if (i < oneCount + zeroCount) {
                nums[i] = 1;
            } else {
                nums[i] = 2;
            }
        }
    }
}
```

In Python:
```python
class Solution(object):
    def sortColors(self, nums):
        """
        :type nums: List[int]
        :rtype: None Do not return anything, modify nums in-place instead.
        """
        zero_count = 0
        one_count = 0
        two_count = 0
        for num in nums:
            if num == 0:
                zero_count+= 1
            elif num == 1:
                one_count+=1
            else:
                two_count+=1
        
        for i in range(len(nums)):
            if i < zero_count:
                nums[i] = 0
            elif i < zero_count + one_count:
                nums[i] = 1
            else:
                nums[i] = 2
```

Method 2: 1-pass is essentially the 3-way quick partition method in quicksort.

In Java:
```java
class Solution {
    public void sortColors(int[] nums) {
        // 1 pass
        int p1 = 0, p2 = nums.length - 1, index = 0;
        while (index <= p2) {
            if (nums[index] == 0) {
                nums[index] = nums[p1];
                nums[p1] = 0;
                p1++;
            }

            if (nums[index] == 2) {
                nums[index] = nums[p2];
                nums[p2] = 2;
                p2--;
                index--;
            }
            index++;
        }
    }
}
```

In Python:

```python
class Solution(object):
    def sortColors(self, nums):
        """
        :type nums: List[int]
        :rtype: None Do not return anything, modify nums in-place instead.
        """
        p1, p2, index = 0, len(nums) - 1, 0
        while(index <= p2):
            if nums[index] == 0:
                nums[index] = nums[p1]
                nums[p1] = 0
                p1+=1
            
            if nums[index] == 2:
                nums[index] = nums[p2]
                nums[p2] = 2
                p2-=1
                index-=1
            index+=1
    
```