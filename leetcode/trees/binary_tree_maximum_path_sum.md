### [124. Binary Tree Maximum Path Sum](https://leetcode.com/problems/binary-tree-maximum-path-sum/)


Given a non-empty binary tree, find the maximum path sum.

For this problem, a path is defined as any sequence of nodes from some starting node to any node in the tree along the parent-child connections. The path must contain at least one node and does not need to go through the root.

Example 1:
```
Input: [1,2,3]

       1
      / \
     2   3

Output: 6
```
Example 2:
```
Input: [-10,9,20,null,null,15,7]

   -10
   / \
  9  20
    /  \
   15   7

Output: 42
```

#### Solution

##### Approach 1: Recursive

##### Complexity analysis
- Time complexity : O(n) where N is number of nodes, since we visit each node not more than 2 times.

- Space complexity : O(nlogn). We have to keep a recursion stack of the size of the tree height, which is O(nlogn) for the binary tree.
```java
class Solution {
    int maxSum = Integer.MIN_VALUE;

    public int maxPathSum(TreeNode root) {
        maxGain(root);
        return maxSum;
    }

    public int maxGain(TreeNode root) {
        if (root == null) {
            return 0;
        }

        int leftGain = Math.max(maxGain(root.left), 0);
        int rightGain = Math.max(maxGain(root.right), 0);
        int newSum = root.val + leftGain + rightGain;
        maxSum = Math.max(maxSum, newSum);
        return root.val + Math.max(leftGain, rightGain);
    }
}
```