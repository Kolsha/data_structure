### 230. Kth Smallest Element in a BST
https://leetcode.com/problems/kth-smallest-element-in-a-bst/

Given a binary search tree, write a function kthSmallest to find the kth smallest element in it.

Note:
You may assume k is always valid, 1 ≤ k ≤ BST's total elements.

Example 1:
```
Input: root = [3,1,4,null,2], k = 1
   3
  / \
 1   4
  \
   2
Output: 1
```
Example 2:
```
Input: root = [5,3,6,2,4,null,null,1], k = 3
       5
      / \
     3   6
    / \
   2   4
  /
 1
Output: 3
```
Follow up:
What if the BST is modified (insert/delete operations) often and you need to find the kth smallest frequently? How would you optimize the kthSmallest routine?

Solution:

In-Order Traverse approach:
```java
/**
 * Definition for a binary tree node. public class TreeNode { int val; TreeNode
 * left; TreeNode right; TreeNode(int x) { val = x; } }
 */
class Solution {
    private int curr = 0;

    public int kthSmallest(TreeNode root, int k) {
        if (root == null) {
            return -1;
        }

        TreeNode result = new TreeNode(-1);
        getIndexedNodeFromBST(root, k, result);
        return result.val;
    }

    private void getIndexedNodeFromBST(TreeNode root, int k, TreeNode result) {
        if (root == null) {
            return;
        }

        if (root.left != null) {
            getIndexedNodeFromBST(root.left, k, result);
        }

        curr++;
        if (curr == k) {
            result.val = root.val;
            result.left = root.left;
            result.right = root.right;
        }

        if (root.right != null) {
            getIndexedNodeFromBST(root.right, k, result);
        }

    }
}
```

[Other solutions](https://leetcode.com/problems/kth-smallest-element-in-a-bst/discuss/63660/3-ways-implemented-in-JAVA-(Python)%3A-Binary-Search-in-order-iterative-and-recursive)