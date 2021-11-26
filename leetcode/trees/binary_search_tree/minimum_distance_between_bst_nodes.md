### [783. Minimum Distance Between BST Nodes](https://leetcode.com/problems/minimum-distance-between-bst-nodes/)


Given the root of a Binary Search Tree (BST), return the minimum difference between the values of any two different nodes in the tree.

 

Example 1:

![](https://assets.leetcode.com/uploads/2021/02/05/bst1.jpg)
```
Input: root = [4,2,6,1,3]
Output: 1
```
Example 2:

```
Input: root = [1,0,48,null,null,12,49]
Output: 1
``` 

Constraints:

- The number of nodes in the tree is in the range [2, 100].
- $0 <= Node.val <= 10^5$
 

Note: This question is the same as 530: https://leetcode.com/problems/minimum-absolute-difference-in-bst/



### Solution

#### Approach 1: In-order traversal

```java
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {
    public int minDiffInBST(TreeNode root) {
        TreeNode[] pre = new TreeNode[1];
        int[] res = new int[1];
        res[0] = Integer.MAX_VALUE;
        inOrderTraversal(root, pre, res);
        return res[0];
    }
    
    private void inOrderTraversal(TreeNode root, TreeNode[] pre, int[] res) {
        if(root == null) {
            return;
        }
        
        inOrderTraversal(root.left, pre, res);
        
        if(pre[0] != null) {
            res[0] = Math.min(Math.abs(pre[0].val - root.val), res[0]);
        }
        pre[0] = root;

        inOrderTraversal(root.right, pre, res);
    }
}
```