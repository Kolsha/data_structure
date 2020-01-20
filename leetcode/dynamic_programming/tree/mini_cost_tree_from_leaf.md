### 1130. Minimum Cost Tree From Leaf Values

https://leetcode.com/problems/minimum-cost-tree-from-leaf-values/

Given an array `arr` of positive integers, consider all binary trees such that:

- Each node has either 0 or 2 children;
- The values of arr correspond to the values of each <b>leaf</b> in an in-order traversal of the tree.  (Recall that a node is a leaf if and only if it has 0 children.)
- The value of each non-leaf node is equal to the product of the largest leaf value in its left and right subtree respectively.

Among all possible binary trees considered, return the smallest possible sum of the values of each non-leaf node.  It is guaranteed this sum fits into a 32-bit integer.

 

Example 1:
```
Input: arr = [6,2,4]
Output: 32
Explanation:
There are two possible trees.  The first has non-leaf node sum 36, and the second has non-leaf node sum 32.

    24            24
   /  \          /  \
  12   4        6    8
 /  \               / \
6    2             2   4
 
```
Constraints:

- 2 <= arr.length <= 40
- 1 <= arr[i] <= 15
- It is guaranteed that the answer fits into a 32-bit signed integer (ie. it is less than 2^31).

Solution:
Method 1: Dynamic Programming Approach
```java
class Solution {
    public int mctFromLeafValues(int[] arr) {
        int[][] max = new int[arr.length][arr.length];
        for(int i = 0; i < arr.length; i++) {
            int localMax = 0;
            for(int j = i; j < arr.length; j++) {
                if(localMax < arr[j]) {
                    localMax = arr[j];
                }
                max[i][j] = localMax;
            }
        }
        // Recurrence: dp[i, j] = dp[i, k] + dp[k + 1, j] + max(A[i, k]) * max(A[k + 1, j])
        int[][] dp = new int[arr.length][arr.length];
        for(int len = 1; len < arr.length; len++) {
            for(int left = 0; left+ len < arr.length; left++) {
                int right = left + len;
                System.out.println("left: "+ left+", right: "+ right+ ", len: "+ len);
                dp[left][right] = Integer.MAX_VALUE;
                if(len == 1) {
                    dp[left][right] = arr[left] * arr[right]; 
                } else {
                    for(int k = left; k < right; k++) {
                        dp[left][right] = Math.min(dp[left][right],
                                                   dp[left][k] + dp[k+1][right] + max[left][k] * max[k+1][right]);
                    }
                }
            }
        }
        return dp[0][arr.length - 1];
    }
}
```
[Method 2: Stack Approach](https://leetcode.com/problems/minimum-cost-tree-from-leaf-values/discuss/339959/One-Pass-O(N)-Time-and-Space)
// todo
