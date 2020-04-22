### 94. Binary Tree Inorder Traversal

https://leetcode.com/problems/binary-tree-inorder-traversal/

Given a binary tree, return the inorder traversal of its nodes' values.

Example:
```
Input: [1,null,2,3]
   1
    \
     2
    /
   3

Output: [1,3,2]
```
Follow up: Recursive solution is trivial, could you do it iteratively?

Solution

Method 1: Recursive approach


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
    public List<Integer> inorderTraversal(TreeNode root) {
        ArrayList<Integer> res = new ArrayList<>();
        helper(root, res);
        return res;
    }

    private void helper(TreeNode root, ArrayList<Integer> res) {
        if(root == null) {
            return;
        }

        helper(root.left, res);
        res.add(root.val);
        helper(root.right, res);
    }
    
}
```

Method 2: Iterative approach
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
    public List<Integer> inorderTraversal(TreeNode root) {
        ArrayList<Integer> res = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        TreeNode cur = root;
        while(cur!=null || !stack.isEmpty()) {
            while(cur != null) {
                stack.add(cur);
                cur = cur.left;
            }
            cur = stack.pop();
            res.add(cur.val);
            cur = cur.right;
        }
        return res;
    }
}
```