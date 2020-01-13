### 530. Minimum Absolute Difference in BST
https://leetcode.com/problems/minimum-absolute-difference-in-bst/

Given a binary search tree with non-negative values, find the minimum absolute difference between values of any two nodes.

Example:
```
Input:

   1
    \
     3
    /
   2

Output:
1

Explanation:
The minimum absolute difference is 1, which is the difference between 2 and 1 (or between 2 and 3).
```

[Solution](https://leetcode.com/problems/minimum-absolute-difference-in-bst/discuss/99905/Two-Solutions-in-order-traversal-and-a-more-general-way-using-TreeSet):
```java
/**
 * Definition for a binary tree node. public class TreeNode { int val; TreeNode
 * left; TreeNode right; TreeNode(int x) { val = x; } }
 */
class Solution {
    private int diff = Integer.MAX_VALUE;
    private Integer preVal = null;

    public int getMinimumDifference(TreeNode root) {
        if (root == null) {
            return 0;
        }

        if (root.left != null) {
            getMinimumDifference(root.left);
        }

        if (preVal != null) {
            diff = Math.min(diff, root.val - preVal);
        }
        preVal = root.val;

        if (root.right != null) {
            getMinimumDifference(root.right);
        }

        return diff;
    }
}
```

// todo what if not bst
 