### 108. Convert Sorted Array to Binary Search Tree

https://leetcode.com/problems/convert-sorted-array-to-binary-search-tree/

Given an array where elements are sorted in ascending order, convert it to a height balanced BST.

For this problem, a height-balanced binary tree is defined as a binary tree in which the depth of the two subtrees of every node never differ by more than 1.

Example:
```
Given the sorted array: [-10,-3,0,5,9],

One possible answer is: [0,-3,9,-10,null,5], which represents the following height balanced BST:

      0
     / \
   -3   9
   /   /
 -10  5
```

Solution

Approach 1: Recursive

Complexity analysis

- Time complexity: O(n) since we visit each node once.
- Space complexity: O(n). O(n) to keep the output, and O(logn) for the recursion stack.

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
    public TreeNode sortedArrayToBST(int[] nums) {
        if(nums == null) {
            return null;
        }
        return toBST(nums, 0, nums.length - 1);
    }
    
    private TreeNode toBST(int[] nums, int low, int high) {
        if(low > high) {
            return null;
        }
        int mid = low + (high - low)/2;
        TreeNode root = new TreeNode(nums[mid]);
        root.left = toBST(nums, low, mid - 1);
        root.right = toBST(nums, mid + 1, high);
        return root;
    }
}
```