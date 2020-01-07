### 872. Leaf-Similar Trees
https://leetcode.com/problems/leaf-similar-trees/

Consider all the leaves of a binary tree.  From left to right order, the values of those leaves form a leaf value sequence.
![](https://s3-lc-upload.s3.amazonaws.com/uploads/2018/07/16/tree.png)


For example, in the given tree above, the leaf value sequence is `(6, 7, 4, 9, 8)`.

Two binary trees are considered leaf-similar if their leaf value sequence is the same.

Return `true` if and only if the two given trees with head nodes `root1` and `root2` are leaf-similar.

 

Note:

- Both of the given trees will have between 1 and 100 nodes.

Solution:
from: https://leetcode.com/problems/leaf-similar-trees/discuss/152329/C%2B%2BJavaPython-O(H)-Space

General methode is that traverse DFS whole tree to a list and compare two lists.

Here I share an idea of comparing node by node using O(H) space,
where H is the height of the tree.

Use a `stack<TreeNode>` to keep `dfs path`.
`dfs(stack)` will return next leaf.
Check leaves one by one, until the end or difference.
Only `O(H)` space for stack, no extra space for comparation.
`O(1)` is also possible if we can modify the tree.

```java
/**
 * Definition for a binary tree node. public class TreeNode { int val; TreeNode
 * left; TreeNode right; TreeNode(int x) { val = x; } }
 */
class Solution {
    public boolean leafSimilar(TreeNode root1, TreeNode root2) {
        Stack<TreeNode> s1 = new Stack<>();
        Stack<TreeNode> s2 = new Stack<>();
        s1.push(root1);
        s2.push(root2);
        while (!s1.isEmpty() && !s2.isEmpty()) {
            if (dfs(s1) != dfs(s2)) {
                return false;
            }
        }
        return s1.isEmpty() && s2.isEmpty();
    }

    private int dfs(Stack<TreeNode> stack) {
        while (true) {
            TreeNode top = stack.pop();
            if (top.right != null) {
                stack.push(top.right);
            }
            if (top.left != null) {
                stack.push(top.left);
            }
            if (top.left == null && top.right == null) {
                return top.val;
            }
        }
    }

}
```