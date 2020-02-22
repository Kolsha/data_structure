### 1008. Construct Binary Search Tree from Preorder Traversal

https://leetcode.com/problems/construct-binary-search-tree-from-preorder-traversal/

Return the root node of a binary search tree that matches the given preorder traversal.

(Recall that a binary search tree is a binary tree where for every node, any descendant of node.left has a value < node.val, and any descendant of node.right has a value > node.val.  Also recall that a preorder traversal displays the value of the node first, then traverses node.left, then traverses node.right.)

 

Example 1:
![](https://assets.leetcode.com/uploads/2019/03/06/1266.png)
```
Input: [8,5,1,7,10,12]
Output: [8,5,10,1,7,null,12]
```
 

Note: 

1. 1 <= preorder.length <= 100
2. The values of preorder are distinct.

Solution

[Method 1](https://leetcode.com/problems/construct-binary-search-tree-from-preorder-traversal/discuss/252232/JavaC%2B%2BPython-O(N)-Solution)

Give the function a bound the maximum number it will handle.
The left recursion will take the elements smaller than node.val
The right recursion will take the remaining elements smaller than bound

Complexity
bstFromPreorder is called exactly N times.
It's same as a preorder traversal.
Time: O(N)

```java
/**
 * Definition for a binary tree node. public class TreeNode { int val; TreeNode
 * left; TreeNode right; TreeNode(int x) { val = x; } }
 */
class Solution {
    private int preIdx = 0;

    public TreeNode bstFromPreorder(int[] preorder) {
        return helper(preorder, Integer.MAX_VALUE);
    }

    public TreeNode helper(int[] preorder, int bound) {
        if (preIdx == preorder.length || preorder[preIdx] > bound) {
            return null;
        }

        TreeNode root = new TreeNode(preorder[preIdx++]);
        root.left = helper(preorder, root.val);
        root.right = helper(preorder, bound);
        return root;
    }
}
```