### 1302. Deepest Leaves Sum
https://leetcode.com/problems/deepest-leaves-sum/

Given a binary tree, return the sum of values of its deepest leaves.
 

Example 1:
![](https://assets.leetcode.com/uploads/2019/07/31/1483_ex1.png)
```
Input: root = [1,2,3,4,5,null,6,7,null,null,null,null,8]
Output: 15
```

Solution:
Method 1: DFS depth, DFS  leaves sum
```java
/**
 * Definition for a binary tree node. public class TreeNode { int val; TreeNode
 * left; TreeNode right; TreeNode(int x) { val = x; } }
 */
class Solution {
    public int deepestLeavesSum(TreeNode root) {
        int[] depth = new int[1];
        findDepth(root, 0, depth);

        int[] sum = new int[1];
        getSumAtDepth(root, depth[0], 0, sum);
        return sum[0];
    }

    private void getSumAtDepth(TreeNode root, int depth, int curLevel, int[] sum) {
        if (root == null) {
            return;
        }
        curLevel++;

        if (depth == curLevel) {
            sum[0] += root.val;
        }
        getSumAtDepth(root.left, depth, curLevel, sum);
        getSumAtDepth(root.right, depth, curLevel, sum);
    }

    private void findDepth(TreeNode root, int curLevel, int[] depth) {
        if (root == null) {
            return;
        }
        depth[0] = Math.max(depth[0], ++curLevel);
        findDepth(root.left, curLevel, depth);
        findDepth(root.right, curLevel, depth);
    }

}
```