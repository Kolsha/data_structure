### 669. Trim a Binary Search Tree
Given a binary search tree and the lowest and highest boundaries as `L` and `R`, trim the tree so that all its elements lies in `[L, R]` (R >= L). You might need to change the root of the tree, so the result should return the new root of the trimmed binary search tree.

Example 1:
```
Input: 
    1
   / \
  0   2

  L = 1
  R = 2

Output: 
    1
      \
       2
```
Example 2:
```
Input: 
    3
   / \
  0   4
   \
    2
   /
  1

  L = 1
  R = 3

Output: 
      3
     / 
   2   
  /
 1
 ```

 Solution:
 ```java
 /**
 * Definition for a binary tree node. public class TreeNode { int val; TreeNode
 * left; TreeNode right; TreeNode(int x) { val = x; } }
 */
class Solution {
    // Time Complexity - We will have to visit every node once so O(N);
    public TreeNode trimBST(TreeNode root, int L, int R) {
        if (root == null)
            return null;

        // If the value of this node is less than L, then the whole left subtree will be
        // smaller,
        // so we can discard the left sub tree and return the root of the trimmed right
        // sub tree
        if (root.val < L)
            return trimBST(root.right, L, R);

        // If the value of this node is greater than R, then the whole right subtree
        // will be greater
        // so we can discard the right sub tree and return the root of the trimmed left
        // sub tree
        if (root.val > R)
            return trimBST(root.left, L, R);

        // If the value of this node does not need to be deleted,
        // we need to pass this recursive call downwards to both sides
        root.left = trimBST(root.left, L, R);
        root.right = trimBST(root.right, L, R);

        // All the processing of the subtrees done, now return this node
        return root;
    }
}
 ```