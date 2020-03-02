### 4. Median of Two Sorted Arrays

https://leetcode.com/problems/median-of-two-sorted-arrays/

There are two sorted arrays nums1 and nums2 of size m and n respectively.

Find the median of the two sorted arrays. The overall run time complexity should be O(log (m+n)).

You may assume nums1 and nums2 cannot be both empty.

Example 1:
```
nums1 = [1, 3]
nums2 = [2]

The median is 2.0
```
Example 2:
```
nums1 = [1, 2]
nums2 = [3, 4]

The median is (2 + 3)/2 = 2.5
```

Solution
Method 1: Since Median = (Median_Left + Median_Right) / 2.0, We will recursively find median left and median right from nums1 and nums2 array

```java
class Solution {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        if (nums1 == null || nums2 == null) {
            return 0.0;
        }
        // left half of the combined median
        int left = (nums1.length + nums2.length + 1) / 2;
        // right half of the combined median
        int right = (nums1.length + nums2.length + 2) / 2;
        // If the nums1.length + nums2.length is odd, the 2 function will return the same number
        // Else if nums1.length + nums2.length is even, the 2 function will return the left number and right number that make up a median

        int leftMid = findKth(nums1, 0, nums2, 0, left);
        int rightMid = findKth(nums1, 0, nums2, 0, right);
        return (leftMid + rightMid) / 2.0;
    }

    // find kth element from the combined array of nums1 and nums2
    int findKth(int[] nums1, int start1, int[] nums2, int start2, int k) {
        // If nums1 is exhausted, return kth number in nums2
        if (start1 > nums1.length - 1) {
            return nums2[start2 + k - 1];
        }
        // If nums2 is exhausted, return kth number in nums1
        if (start2 > nums2.length - 1) {
            return nums1[start1 + k - 1];
        }

        // If k == 1, return the first number
        // Since nums1 and nums2 is sorted, the smaller one among the start point of nums1 and nums2 is the first one
        if (k == 1) {
            return Math.min(nums1[start1], nums2[start2]);
        }

        
        int mid1 = (start1 + k / 2 - 1 < nums1.length) ? nums1[start1 + k / 2 - 1] : Integer.MAX_VALUE;
        int mid2 = (start2 + k / 2 - 1 < nums2.length) ? nums2[start2 + k / 2 - 1] : Integer.MAX_VALUE;
        // Throw away half of the array from nums1 or nums2. And cut k in half
        if (mid1 < mid2) {
            return findKth(nums1, start1 + k / 2, nums2, start2, k - k / 2);
        } else {
            return findKth(nums1, start1, nums2, start2 + k / 2, k - k / 2);
        }
    }
}
```
// todo
Method 2: check solution
https://leetcode.com/problems/median-of-two-sorted-arrays/solution/

