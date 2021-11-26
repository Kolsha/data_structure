### [99. Recover Binary Search Tree](https://leetcode.com/problems/recover-binary-search-tree/)

You are given the root of a binary search tree (BST), where exactly two nodes of the tree were swapped by mistake. Recover the tree without changing its structure.

Follow up: A solution using O(n) space is pretty straight forward. Could you devise a constant space solution?

 

Example 1:
![](https://assets.leetcode.com/uploads/2020/10/28/recover1.jpg)
```
Input: root = [1,3,null,null,2]
Output: [3,1,null,null,2]
Explanation: 3 cannot be a left child of 1 because 3 > 1. Swapping 1 and 3 makes the BST valid.
```
Example 2:
![](https://assets.leetcode.com/uploads/2020/10/28/recover2.jpg)
```
Input: root = [3,1,4,null,null,2]
Output: [2,1,4,null,null,3]
Explanation: 2 cannot be in the right subtree of 3 because 2 < 3. Swapping 2 and 3 makes the BST valid.
``` 

Constraints:

- The number of nodes in the tree is in the range [2, 1000].
- $-2^{31} <= Node.val <= 2^{31} - 1$

### Solution

#### Approach 1: In-order travasal

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
    public void recoverTree(TreeNode root) {
        TreeNode[] first = new TreeNode[1];
        TreeNode[] second = new TreeNode[1];
        TreeNode[] pre = new TreeNode[1];

        inorder(root, pre, first, second);

        int tmp = first[0].val;
        first[0].val = second[0].val;
        second[0].val = tmp;
    }
    
    private void inorder(TreeNode root, TreeNode[] pre,TreeNode[] first,TreeNode[] second) {
        if(root == null) {
            return;
        }
        
        inorder(root.left, pre, first, second);
        
        if(pre[0]!= null && pre[0].val >= root.val && first[0] == null) {
            first[0] = pre[0];
        }
        
        if(pre[0]!=null && pre[0].val >= root.val && first[0] != null) {
            second[0] = root;
        }
        
        pre[0] = root;

        inorder(root.right, pre, first, second);
    }
}
```