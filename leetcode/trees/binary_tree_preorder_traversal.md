### 144. Binary Tree Preorder Traversal

https://leetcode.com/problems/binary-tree-preorder-traversal/

Given a binary tree, return the preorder traversal of its nodes' values.

Example:
```
Input: [1,null,2,3]
   1
    \
     2
    /
   3

Output: [1,2,3]
```
Follow up: Recursive solution is trivial, could you do it iteratively?

Solution

Method 1: Recursive approach

Time complexity: O(n)

Space complexity: O(n)

In Java
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
    public List<Integer> preorderTraversal(TreeNode root) {
        ArrayList<Integer> res = new ArrayList<>();
        helper(root, res);
        return res;
    }

    private void helper(TreeNode root, ArrayList<Integer> res) {
        if(root == null) {
            return;
        }

        res.add(root.val);
        helper(root.left, res);
        helper(root.right, res);
    }
}
```

In Python
```python
# Definition for a binary tree node.
# class TreeNode(object):
#     def __init__(self, x):
#         self.val = x
#         self.left = None
#         self.right = None

class Solution(object):
    def preorderTraversal(self, root):
        def helper(root, res):
            if root == None:
                return
            res.append(root.val)
            helper(root.left, res)
            helper(root.right, res)
        """
        :type root: TreeNode
        :rtype: List[int]
        """
        res = []
        helper(root, res)
        return res
```

Method 2: Iterative approach

In Java
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
    public List<Integer> preorderTraversal(TreeNode root) {
        ArrayList<Integer> res = new ArrayList<>();
        if(root == null) {
            return res;
        }
        Stack<TreeNode> stack = new Stack<>();
        stack.add(root);
        while(!stack.isEmpty()) {
            TreeNode visit = stack.pop();
            res.add(visit.val);
            // we need to visit the left node first, so we push the right node first
            if(visit.right!=null) {
                stack.add(visit.right);
            }

            if(visit.left!=null) {
                stack.add(visit.left);
            }
        }
        return res;
    }
}
```

In Python:
```python
# Definition for a binary tree node.
# class TreeNode(object):
#     def __init__(self, x):
#         self.val = x
#         self.left = None
#         self.right = None

class Solution(object):
    def preorderTraversal(self, root):
        """
        :type root: TreeNode
        :rtype: List[int]
        """
        res = []
        stack = [root]
        while len(stack)!= 0:
            node = stack.pop()
            if node != None:
                res.append(node.val)
                if node.right != None:
                    stack.append(node.right)
                if node.left != None:
                    stack.append(node.left)
        return res
```