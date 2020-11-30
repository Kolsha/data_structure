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

##### Solution

##### Approach 1: Recursive Approach
To solve this problem, we need to understand "What is the use of median". In statistics, the median is used for:

Dividing a set into two equal length subsets, that one subset is always greater than the other.

If we understand the use of median for dividing, we are very close to the answer.

First let's cut \text{A}A into two parts at a random position ii:

          left_A             |        right_A
    A[0], A[1], ..., A[i-1]  |  A[i], A[i+1], ..., A[m-1]

Since $\text{A}$ has $m$ elements, so there are $m+1$ kinds of cutting ($i = 0 \sim m$).

And we know:
    
$\text{len}(\text{left\_A}) = i, \text{len}(\text{right\_A}) = m - i$

Note: when $i = 0$, $\text{left\_A}$ is empty, and when $i = m$, $\text{right\_A}$ is empty.

With the same way, cut $\text{B}$ into two parts at a random position $j$:


          left_B             |        right_B
    B[0], B[1], ..., B[j-1]  |  B[j], B[j+1], ..., B[n-1]
Put \text{left\_A}left_A and \text{left\_B}left_B into one set, and put \text{right\_A}right_A and \text{right\_B}right_B into another set. Let's name them \text{left\_part}left_part and \text{right\_part}right_part:

          left_part          |        right_part
    A[0], A[1], ..., A[i-1]  |  A[i], A[i+1], ..., A[m-1]
    B[0], B[1], ..., B[j-1]  |  B[j], B[j+1], ..., B[n-1]
If we can ensure:

1. $\text{len}(\text{left\_part}) = \text{len}(\text{right\_part})$
2. $\max(\text{left\_part}) \leq \min(\text{right\_part})$

then we divide all elements in $\{\text{A}, \text{B}\}$ into two parts with equal length, and one part is always greater than the other. Then

$\text{median} = \frac{\text{max}(\text{left}\_\text{part}) + \text{min}(\text{right}\_\text{part})}{2}$

To ensure these two conditions, we just need to ensure:

1. $i + j = m - i + n - j$ (or: $m - i + n - j + 1$)
    - if $n \geq m$, we just need to set: $i = 0 \sim m, j = \frac{m + n + 1}{2} - i\\$
2. $\text{B}[j-1] \leq \text{A}[i]$ and $\text{A}[i-1] \leq \text{B}[j]$

ps.1 For simplicity, I presume $\text{A}[i-1], \text{B}[j-1], \text{A}[i], \text{B}[j]$ are always valid even if $i=0$, $i=m$, $j=0$, or $j=n$. I will talk about how to deal with these edge values at last.

ps.2 Why $n \geq m$? Because I have to make sure $j$ is non-negative since $0 \leq i \leq m$ and $j = \frac{m + n + 1}{2} - i$. If $n < m$, then $j$ may be negative, that will lead to wrong result.

So, all we need to do is:

Searching $i$ in $[0, m]$, to find an object $i$ such that:

$\qquad \text{B}[j-1] \leq \text{A}[i]$ and $\ \text{A}[i-1] \leq \text{B}[j]$, where $j = \frac{m + n + 1}{2} - i$

And we can do a binary search following steps described below:

1. Set $\text{imin} = 0$, $\text{imax} = m$, then start searching in $[\text{imin}, \text{imax}]$

2. Set $i = \frac{\text{imin} + \text{imax}}{2}$, $j = \frac{m + n + 1}{2} - i$

3. Now we have $\text{len}(\text{left}\_\text{part})=\text{len}(\text{right}\_\text{part})$. And there are only 3 situations that we may encounter:

    - $\text{B}[j-1] \leq \text{A}[i]$ and $\text{A}[i-1] \leq \text{B}[j]$ Means we have found the object i, so stop searching.

    - $\text{B}[j-1] > \text{A}[i]$ Means $\text{A}[i]$ is too small. We must adjust $i$ to get $\text{B}[j-1] \leq \text{A}[i]$.
    Can we increase $i$?
      Yes. Because when ii is increased, jj will be decreased.
      So \text{B}[j-1]B[j−1] is decreased and \text{A}[i]A[i] is increased, and \text{B}[j-1] \leq \text{A}[i]B[j−1]≤A[i] may
      be satisfied.
Can we decrease ii?
      No! Because when ii is decreased, jj will be increased.
      So \text{B}[j-1]B[j−1] is increased and \text{A}[i]A[i] is decreased, and \text{B}[j-1] \leq \text{A}[i]B[j−1]≤A[i] will
      be never satisfied.
So we must increase ii. That is, we must adjust the searching range to [i+1, \text{imax}][i+1,imax].
So, set \text{imin} = i+1imin=i+1, and goto 2.

\text{A}[i-1] > \text{B}[j]A[i−1]>B[j]:
Means \text{A}[i-1]A[i−1] is too big. And we must decrease ii to get \text{A}[i-1]\leq \text{B}[j]A[i−1]≤B[j].
That is, we must adjust the searching range to [\text{imin}, i-1][imin,i−1].
So, set \text{imax} = i-1imax=i−1, and goto 2.

When the object ii is found, the median is:

\max(\text{A}[i-1], \text{B}[j-1]),max(A[i−1],B[j−1]), when m + nm+n is odd

\frac{\max(\text{A}[i-1], \text{B}[j-1]) + \min(\text{A}[i], \text{B}[j])}{2}, 
2
max(A[i−1],B[j−1])+min(A[i],B[j])
​	
 , when m + nm+n is even

Now let's consider the edges values i=0,i=m,j=0,j=ni=0,i=m,j=0,j=n where \text{A}[i-1],\text{B}[j-1],\text{A}[i],\text{B}[j]A[i−1],B[j−1],A[i],B[j] may not exist. Actually this situation is easier than you think.

What we need to do is ensuring that \text{max}(\text{left}\_\text{part}) \leq \text{min}(\text{right}\_\text{part})max(left_part)≤min(right_part). So, if ii and jj are not edges values (means \text{A}[i-1], \text{B}[j-1],\text{A}[i],\text{B}[j]A[i−1],B[j−1],A[i],B[j] all exist), then we must check both \text{B}[j-1] \leq \text{A}[i]B[j−1]≤A[i] and \text{A}[i-1] \leq \text{B}[j]A[i−1]≤B[j]. But if some of \text{A}[i-1],\text{B}[j-1],\text{A}[i],\text{B}[j]A[i−1],B[j−1],A[i],B[j] don't exist, then we don't need to check one (or both) of these two conditions. For example, if i=0i=0, then \text{A}[i-1]A[i−1] doesn't exist, then we don't need to check \text{A}[i-1] \leq \text{B}[j]A[i−1]≤B[j]. So, what we need to do is:

Searching ii in [0, m][0,m], to find an object ii such that:

(j = 0(j=0 or i = mi=m or \text{B}[j-1] \leq \text{A}[i])B[j−1]≤A[i]) and
(i = 0(i=0 or j = nj=n or \text{A}[i-1] \leq \text{B}[j]),A[i−1]≤B[j]), where j = \frac{m + n + 1}{2} - ij= 
2
m+n+1
​	
 −i

And in a searching loop, we will encounter only three situations:

(j = 0(j=0 or i = mi=m or \text{B}[j-1] \leq \text{A}[i])B[j−1]≤A[i]) and
(i = 0(i=0 or j = nj=n or \text{A}[i-1] \leq \text{B}[j])A[i−1]≤B[j])
Means ii is perfect, we can stop searching.
j > 0j>0 and i < mi<m and \text{B}[j - 1] > \text{A}[i]B[j−1]>A[i]
Means ii is too small, we must increase it.
i > 0i>0 and j < nj<n and \text{A}[i - 1] > \text{B}[j]A[i−1]>B[j]
Means ii is too big, we must decrease it.
Thanks to @Quentin.chen for pointing out that: i < m \implies j > 0i<m⟹j>0 and i > 0 \implies j < ni>0⟹j<n. Because:

m \leq n, i < m \implies j = \frac{m+n+1}{2} - i > \frac{m+n+1}{2} - m \geq \frac{2m+1}{2} - m \geq 0m≤n,i<m⟹j= 
2
m+n+1
​	
 −i> 
2
m+n+1
​	
 −m≥ 
2
2m+1
​	
 −m≥0

m \leq n, i > 0 \implies j = \frac{m+n+1}{2} - i < \frac{m+n+1}{2} \leq \frac{2n+1}{2} \leq nm≤n,i>0⟹j= 
2
m+n+1
​	
 −i< 
2
m+n+1
​	
 ≤ 
2
2n+1
​	
 ≤n

So in situation 2. and 3. , we don't need to check whether j > 0j>0 and whether j < nj<n.


##### Complexity Analysis

- Time complexity: O\big(\log\big(\text{min}(m,n)\big)\big)O(log(min(m,n))).
At first, the searching range is [0, m][0,m]. And the length of this searching range will be reduced by half after each loop. So, we only need \log(m)log(m) loops. Since we do constant operations in each loop, so the time complexity is O\big(\log(m)\big)O(log(m)). Since m \leq nm≤n, so the time complexity is O\big(\log\big(\text{min}(m,n)\big)\big)O(log(min(m,n))).

- Space complexity: O(1)O(1).
We only need constant memory to store 99 local variables, so the space complexity is O(1)O(1).

```java
class Solution {
    public double findMedianSortedArrays(int[] A, int[] B) {
        int m = A.length;
        int n = B.length;
        if (m > n) { // to ensure m<=n
            int[] temp = A; A = B; B = temp;
            int tmp = m; m = n; n = tmp;
        }
        int iMin = 0, iMax = m, halfLen = (m + n + 1) / 2;
        while (iMin <= iMax) {
            int i = (iMin + iMax) / 2;
            int j = halfLen - i;
            if (i < iMax && B[j-1] > A[i]){
                iMin = i + 1; // i is too small
            }
            else if (i > iMin && A[i-1] > B[j]) {
                iMax = i - 1; // i is too big
            }
            else { // i is perfect
                int maxLeft = 0;
                if (i == 0) { maxLeft = B[j-1]; }
                else if (j == 0) { maxLeft = A[i-1]; }
                else { maxLeft = Math.max(A[i-1], B[j-1]); }
                if ( (m + n) % 2 == 1 ) { return maxLeft; }

                int minRight = 0;
                if (i == m) { minRight = B[j]; }
                else if (j == n) { minRight = A[i]; }
                else { minRight = Math.min(B[j], A[i]); }

                return (maxLeft + minRight) / 2.0;
            }
        }
        return 0.0;
    }
}
```



Method 1: Since Median = (Median_Left + Median_Right) / 2.0, We will recursively find median left and median right from nums1 and nums2 array

##### Complexity analysis
Time Complexity: O(log(m + n))
Space Complexity: O(log(m + n))
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

