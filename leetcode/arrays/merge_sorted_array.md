### 88. Merge Sorted Array

https://leetcode.com/problems/merge-sorted-array/

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

Solution

Approach 1: Two Pointers

Complexity Analysis

<ul>
<li>Time complexity : <span class="katex"><span class="katex-mathml"><math><semantics><mrow><mi mathvariant="script">O</mi><mo>(</mo><mi>n</mi><mo>+</mo><mi>m</mi><mo>)</mo></mrow><annotation encoding="application/x-tex">\mathcal{O}(n + m)</annotation></semantics></math></span><span class="katex-html" aria-hidden="true"><span class="base"><span class="strut" style="height:1em;vertical-align:-0.25em;"></span><span class="mord"><span class="mord mathcal" style="margin-right:0.02778em;">O</span></span><span class="mopen">(</span><span class="mord mathdefault">n</span><span class="mspace" style="margin-right:0.2222222222222222em;"></span><span class="mbin">+</span><span class="mspace" style="margin-right:0.2222222222222222em;"></span></span><span class="base"><span class="strut" style="height:1em;vertical-align:-0.25em;"></span><span class="mord mathdefault">m</span><span class="mclose">)</span></span></span></span>.</li>
<li>Space complexity : <span class="katex"><span class="katex-mathml"><math><semantics><mrow><mi mathvariant="script">O</mi><mo>(</mo><mn>1</mn><mo>)</mo></mrow><annotation encoding="application/x-tex">\mathcal{O}(1)</annotation></semantics></math></span><span class="katex-html" aria-hidden="true"><span class="base"><span class="strut" style="height:1em;vertical-align:-0.25em;"></span><span class="mord"><span class="mord mathcal" style="margin-right:0.02778em;">O</span></span><span class="mopen">(</span><span class="mord">1</span><span class="mclose">)</span></span></span></span>.</li>
</ul>

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
        while(nums2Ptr >= 0) {
            nums1[zeroPtr--] = nums2[nums2Ptr--];
        }
    }
}
```