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

Approach 1: In-Order Traverse
In-order traversal in BST => sorted list with ascending value

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
    public int kthSmallest(TreeNode root, int k) {
        TreeNode res = new TreeNode(-1);
        int[] curr = new int[1];
        curr[0] = 0;
        bstInorderTraversal(root, res, k, curr);
        return res.val;
    }
    
    private void bstInorderTraversal(TreeNode root, TreeNode res, int k, int[] curr) {
        if(root == null) {
            return;
        }
        
        bstInorderTraversal(root.left, res, k, curr);
        curr[0]++;
        if(curr[0] == k) {
            res.val = root.val;
            return;
        }
        bstInorderTraversal(root.right, res, k, curr);
    }
}
```

[Other solutions](https://leetcode.com/problems/kth-smallest-element-in-a-bst/discuss/63660/3-ways-implemented-in-JAVA-(Python)%3A-Binary-Search-in-order-iterative-and-recursive)