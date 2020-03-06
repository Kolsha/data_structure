### 236. Lowest Common Ancestor of a Binary Tree

https://leetcode.com/problems/lowest-common-ancestor-of-a-binary-tree/

Given a binary tree, find the lowest common ancestor (LCA) of two given nodes in the tree.

According to the definition of LCA on Wikipedia: “The lowest common ancestor is defined between two nodes p and q as the lowest node in T that has both p and q as descendants (where we allow a node to be a descendant of itself).”

Given the following binary tree:  root = [3,5,1,6,2,0,8,null,null,7,4]

![](https://assets.leetcode.com/uploads/2018/12/14/binarytree.png)

Example 1:
```
Input: root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 1
Output: 3
Explanation: The LCA of nodes 5 and 1 is 3.
```
Example 2:
```
Input: root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 4
Output: 5
Explanation: The LCA of nodes 5 and 4 is 5, since a node can be a descendant of itself according to the LCA definition.
``` 

Note:

- All of the nodes' values will be unique.
- p and q are different and both values will exist in the binary tree.

Solution
Method 1: Recursive
Take an example of p=0, q=8 with the given example from leetcode. Initially we have 

leftTree1 = LCA(3.left = 5, p, q), rightTree1 = LCA(3.right = 1, p, q). 

Since leftTree does not have p, q it will have null eventually, whereas rightTree will have 1 because:
leftTree2 = LCA(1.left=0, p, q) = 0 
rightTree = LCA(1.right=8, p, q) = 8. 

In this case, since left != null && right != null we return current root=1. leftTree1 = null and rightTree1=1 and the final answer becomes 1.

```java
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class Solution {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if(root == null || root == p || root == q) {
            return root;
        }
        TreeNode left = lowestCommonAncestor(root.left, p, q);
        TreeNode right = lowestCommonAncestor(root.right, p, q);
        if(left == null) {
            return right;
        } else if (right == null) {
            return left;
        } else {
            return root;
        }

    }
}
```