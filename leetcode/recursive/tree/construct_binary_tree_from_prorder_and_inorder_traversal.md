### 105. Construct Binary Tree from Preorder and Inorder Traversal

https://leetcode.com/problems/construct-binary-tree-from-preorder-and-inorder-traversal/

Given preorder and inorder traversal of a tree, construct the binary tree.

Note:
You may assume that duplicates do not exist in the tree.

For example, given
```
preorder = [3,9,20,15,7]
inorder = [9,3,15,20,7]
```
Return the following binary tree:
```
    3
   / \
  9  20
    /  \
   15   7
```

Solution
```java
/**
 * Definition for a binary tree node. public class TreeNode { int val; TreeNode
 * left; TreeNode right; TreeNode(int x) { val = x; } }
 */
class Solution {
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        return helper(0, 0, inorder.length - 1, preorder, inorder);
    }

    public TreeNode helper(int preIdx, int inStartIdx, int inEndIdx, int[] preorder, int[] inorder) {
        if (preIdx > preorder.length - 1 || inStartIdx > inEndIdx) {
            return null;
        }
        TreeNode root = new TreeNode(preorder[preIdx]);
        int inIdx = 0;
        for (int i = inStartIdx; i <= inEndIdx; i++) {
            if (inorder[i] == root.val) {
                inIdx = i;
            }
        }
        root.left = helper(preIdx + 1, inStartIdx, inIdx - 1, preorder, inorder);
        // inIdx - inStartIdx + 1: left tree's size
        root.right = helper(preIdx + inIdx - inStartIdx + 1, inIdx + 1, inEndIdx, preorder, inorder);
        return root;

    }
}
```