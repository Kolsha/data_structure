### 538. Convert BST to Greater Tree
https://leetcode.com/problems/convert-bst-to-greater-tree/

Given a Binary Search Tree (BST), convert it to a Greater Tree such that every key of the original BST is changed to the original key plus sum of all keys greater than the original key in BST.

Example:
```
Input: The root of a Binary Search Tree like this:
              5
            /   \
           2     13

Output: The root of a Greater Tree like this:
             18
            /   \
          20     13
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
    private int sum = 0;
    public TreeNode convertBST(TreeNode root) {
        if(root == null) {
            return null;
        }
        
        if(root.right!=null){
            convertBST(root.right);  
        }
        
        sum+=root.val;
        root.val=sum;
        
        if(root.left!=null){
            convertBST(root.left);
        }
       return root;
    }
}
```