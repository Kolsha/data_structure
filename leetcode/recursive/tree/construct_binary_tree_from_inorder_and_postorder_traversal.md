### 106. Construct Binary Tree from Inorder and Postorder Traversal

https://leetcode.com/problems/construct-binary-tree-from-inorder-and-postorder-traversal/

Given inorder and postorder traversal of a tree, construct the binary tree.

Note:
You may assume that duplicates do not exist in the tree.

For example, given
```
inorder = [9,3,15,20,7]
postorder = [9,15,7,20,3]
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
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class Solution {
    public TreeNode buildTree(int[] inorder, int[] postorder) {
        return helper(inorder, postorder, postorder.length - 1, 0, inorder.length - 1);
    }
    
    private TreeNode helper(int[] inorder, int[] postorder, int postIdx, int inStart, int inEnd) {
        if(postIdx < 0 || inStart > inEnd) {
            return null;
        }
        TreeNode root = new TreeNode(postorder[postIdx]);
        int inIdx = 0;
        for(int i = 0; i < inorder.length; i++) {
            if(inorder[i] == root.val) {
                inIdx = i;
            }
        }
        
        // left sub tree size
        int leftSize = inIdx - inStart;
        // right sub tree size
        int rightSize = inEnd - inIdx;
        
        root.left = helper(inorder, postorder, postIdx - rightSize -1, inStart, inIdx - 1);
        root.right = helper(inorder, postorder, postIdx - 1, inIdx + 1, inEnd);
        return root;
        
    }
}
```