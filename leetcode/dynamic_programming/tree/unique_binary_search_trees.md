### 96. Unique Binary Search Trees
https://leetcode.com/problems/unique-binary-search-trees/

Given n, how many structurally unique BST's (binary search trees) that store values 1 ... n?

Example:
```
Input: 3
Output: 5
Explanation:
Given n = 3, there are a total of 5 unique BST's:

   1         3     3      2      1
    \       /     /      / \      \
     3     2     1      1   3      2
    /     /       \                 \
   2     1         2                 3
```

Solution:

[Apply dynamic programming](https://leetcode.com/problems/unique-binary-search-trees/discuss/31666/DP-Solution-in-6-lines-with-explanation.-F(i-n)-G(i-1)-*-G(n-i))

```java
class Solution {
    public int numTrees(int n) {
        int[] dp = new int[n + 1];
        dp[0] = 1;
        dp[1] = 1;
        // increasing node number
        for (int i = 2; i <= n; i++) {
            // iter throu each node as root node
            for (int j = 1; j <= i; j++) {
                int temp = dp[j - 1] * dp[i - j];
                dp[i] += temp;
            }

        }
        return dp[n];
    }
}
```