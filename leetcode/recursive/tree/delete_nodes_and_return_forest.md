### 1110. Delete Nodes And Return Forest

https://leetcode.com/problems/delete-nodes-and-return-forest/

Given the root of a binary tree, each node in the tree has a distinct value.

After deleting all nodes with a value in to_delete, we are left with a forest (a disjoint union of trees).

Return the roots of the trees in the remaining forest.  You may return the result in any order.

 

Example 1:


```
Input: root = [1,2,3,4,5,6,7], to_delete = [3,5]
Output: [[1,2,null,4],[6],[7]]
``` 

Constraints:

- The number of nodes in the given tree is at most 1000.
- Each node has a distinct value between 1 and 1000.
- to_delete.length <= 1000
- to_delete contains distinct values between 1 and 1000.

Solution
```java
/**
 * Definition for a binary tree node. public class TreeNode { int val; TreeNode
 * left; TreeNode right; TreeNode(int x) { val = x; } }
 */
class Solution {
    public List<TreeNode> delNodes(TreeNode root, int[] to_delete) {
        HashSet<Integer> set = new HashSet<>();
        for (int val : to_delete) {
            set.add(val);
        }

        ArrayList<TreeNode> res = new ArrayList<>();
        helper(res, set, root, true);
        return res;
    }

    private TreeNode helper(ArrayList<TreeNode> res, HashSet<Integer> del, TreeNode root, boolean isRoot) {
        if (root == null) {
            return null;
        }
        boolean isDel = del.contains(root.val);
        if (!isDel && isRoot) {
            res.add(root);
        }
        root.left = helper(res, del, root.left, isDel);
        root.right = helper(res, del, root.right, isDel);

        return isDel ? null : root;
    }
}
```