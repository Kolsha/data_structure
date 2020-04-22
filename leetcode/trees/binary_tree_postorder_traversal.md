### 145. Binary Tree Postorder Traversal

https://leetcode.com/problems/binary-tree-postorder-traversal/

Given a binary tree, return the postorder traversal of its nodes' values.

Example:
```
Input: [1,null,2,3]
   1
    \
     2
    /
   3

Output: [3,2,1]
```
Follow up: Recursive solution is trivial, could you do it iteratively?

Solution

Method 1: Recursive approach

Time complexity: O(n)

Space complexity: O(n)

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
    public List<Integer> postorderTraversal(TreeNode root) {
        ArrayList<Integer> res = new ArrayList<>();
        helper(root, res);
        return res;
    }

    private void helper(TreeNode root, ArrayList<Integer> res) {
        if(root == null) {
            return;
        }

        helper(root.left, res);
        helper(root.right, res);
        res.add(root.val);
    }
}
```

Method 2: Iterative approach

Time complexity: O(n)

Space complexity: O(n)

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
    public List<Integer> postorderTraversal(TreeNode root) {
        LinkedList<Integer> res = new LinkedList<>();
        Stack<TreeNode> stack = new Stack<>();
        stack.add(root);
        while(!stack.isEmpty()) {
            TreeNode node = stack.pop();
            if(node!=null) {
                res.addFirst(node.val);
                stack.add(node.left);
                stack.add(node.right);
            }
        }
        return res;
    }
}
```