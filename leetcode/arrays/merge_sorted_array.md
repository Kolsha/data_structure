### [88. Merge Sorted Array](https://leetcode.com/problems/merge-sorted-array/)


Given two sorted integer arrays nums1 and nums2, merge nums2 into nums1 as one sorted array.

Note:

- The number of elements initialized in nums1 and nums2 are m and n respectively.
- You may assume that nums1 has enough space (size that is equal to m + n) to hold additional elements from nums2.

Example:
```
Input:
nums1 = [1,2,3,0,0,0], m = 3
nums2 = [2,5,6],       n = 3

Output: [1,2,2,3,5,6]
``` 

Constraints:

- -10^9 <= nums1[i], nums2[i] <= 10^9
- nums1.length == m + n
- nums2.length == n

##### Approach 1

##### Complexity analysis
- Time complexity : $\mathcal{O}(n + m)$
- Space complexity : $\mathcal{O}(1)$

```java
class Solution {
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int zeroPtr = m + n -1;
        int nums2Ptr = n - 1;
        int nums1Ptr = m -1;
        while(nums1Ptr >= 0 && nums2Ptr >= 0) {
            if(nums1[nums1Ptr] > nums2[nums2Ptr]) {
                nums1[zeroPtr--] = nums1[nums1Ptr--];
            } else {
                nums1[zeroPtr--] = nums2[nums2Ptr--];
            }
        }

        // Because if nums1 is bigger and nums 2 is shorter, 
        // when nums2 reaches the end, nothing needs to be done for nums1 
        // (we insert nums2 into nums1). Nums1 remains the same in that way.

        while(nums2Ptr >= 0) {
            nums1[zeroPtr--] = nums2[nums2Ptr--];
        }
    }
}
```