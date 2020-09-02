### 285. Inorder Successor in BST

https://leetcode.com/problems/inorder-successor-in-bst/

Given a binary search tree and a node in it, find the in-order successor of that node in the BST.

The successor of a node p is the node with the smallest key greater than p.val.

 

Example 1:

![](https://assets.leetcode.com/uploads/2019/01/23/285_example_1.PNG)
```
Input: root = [2,1,3], p = 1
Output: 2
Explanation: 1's in-order successor node is 2. Note that both p and the return value is of TreeNode type.
```
Example 2:

![](https://assets.leetcode.com/uploads/2019/01/23/285_example_2.PNG)
```
Input: root = [5,3,6,2,4,null,null,1], p = 6
Output: null
Explanation: There is no in-order successor of the current node, so the answer is null.
``` 

Note:

1. If the given node has no in-order successor in the tree, return null.
2. It's guaranteed that the values of the tree are unique.

Solution:

Approach 1: Recursion

Complexity analysis:
- Time complexity: O(n)
- Space complexity: O(n)

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
    public TreeNode inorderSuccessor(TreeNode root, TreeNode p) {
        if(root == null) {
            return null;
        }
        // we are trying to find a value slightly bigger
        // than TreeNode p from a binary search tree
        if(root.val <= p.val) {
            // when root.val <= p.val, go right for bigger value
            return inorderSuccessor(root.right, p);
        } else {
            // when root.val > p.val, see if we can get smaller value
            TreeNode left = inorderSuccessor(root.left, p);
            return (left != null)? left : root;
        }
    }
}
```

Approach 2: Iterative inorder traversal

Complexity analysis:
- Time complexity: O(H<sub>p</sub>​) in the best case, when node p has a right child. Here H<sub>p</sub>​ is a height of node p.O(H) in the worst case of no right child. Here H is a tree height.
- Space complexity: O(1) in the best case, when node p has a right child. Otherwise, up to O(H) to keep the stack.

```java
class Solution {
  public TreeNode inorderSuccessor(TreeNode root, TreeNode p) {
    // the successor is somewhere lower in the right subtree
    // successor: one step right and then left till you can
    if (p.right != null) {
      p = p.right;
      while (p.left != null) p = p.left;
      return p;
    }

    // the successor is somewhere upper in the tree
    ArrayDeque<TreeNode> stack = new ArrayDeque<>();
    int inorder = Integer.MIN_VALUE;

    // inorder traversal : left -> node -> right
    while (!stack.isEmpty() || root != null) {
      // 1. go left till you can
      while (root != null) {
        stack.push(root);
        root = root.left;
      }

      // 2. all logic around the node
      root = stack.pop();
      // if the previous node was equal to p
      // then the current node is its successor
      if (inorder == p.val) return root;
      inorder = root.val;

      // 3. go one step right
      root = root.right;
    }

    // there is no successor
    return null;
  }
}
```