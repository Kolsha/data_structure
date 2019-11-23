### 645. Set Mismatch
https://leetcode.com/problems/set-mismatch/

The set `S` originally contains numbers from 1 to `n`. But unfortunately, due to the data error, one of the numbers in the set got duplicated to **another** number in the set, which results in repetition of one number and loss of another number.

Given an array `nums` representing the data status of this set after the error. Your task is to firstly find the number occurs twice and then find the number that is missing. Return them in the form of an array.

**Example 1:**

| Input            | Output |
| ---------------- | ------ |
| nums = [1,2,2,4] | [2,3]  |

Note:
The given array size will in the range [2, 10000].
The given array's numbers won't have any order.

**Solution:**
```java
class Solution {
    public int[] findErrorNums(int[] nums) {
        // {duplicate, missing}
        int[] res = new int[2]; 
        //For each number we found, set nums[number-1] to its negative value (<0)
        for(int i = 0; i < nums.length; i++) {
             //since index starts from 0, and the set starts from 1
            int idx = Math.abs(nums[i]) - 1;
            if(nums[idx] > 0) {
                nums[idx] = -nums[idx];
            } else {
                //have already been found
                res[0] = idx + 1; 
            }
        }
        // At this point, only nums[missingNumber-1] > 0, since it's not iterated
        for(int i = 0; i < nums.length; i++) {
            if(nums[i] < 0) {
                //restore the original values
                nums[i] = -nums[i]; 
            } else {
                //since index starts from 0, and the set starts from 1
                res[1] = i + 1;
            }
        }
        return res;
    }
}
```
