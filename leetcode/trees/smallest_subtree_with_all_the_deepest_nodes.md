### 865. Smallest Subtree with all the Deepest Nodes

https://leetcode.com/problems/smallest-subtree-with-all-the-deepest-nodes/

Given a binary tree rooted at root, the depth of each node is the shortest distance to the root.

A node is deepest if it has the largest depth possible among any node in the entire tree.

The subtree of a node is that node, plus the set of all descendants of that node.

Return the node with the largest depth such that it contains all the deepest nodes in its subtree.

Example 1:
![](https://s3-lc-upload.s3.amazonaws.com/uploads/2018/07/01/sketch1.png)
```
Input: [3,5,1,6,2,0,8,null,null,7,4]
Output: [2,7,4]
Explanation:

We return the node with value 2, colored in yellow in the diagram.
The nodes colored in blue are the deepest nodes of the tree.
The input "[3, 5, 1, 6, 2, 0, 8, null, null, 7, 4]" is a serialization of the given tree.
The output "[2, 7, 4]" is a serialization of the subtree rooted at the node with value 2.
Both the input and output have TreeNode type.
``` 

Note:

- The number of nodes in the tree will be between 1 and 500.
- The values of each node are unique.

Solution

Method 1: Recursive + Pair
Time complexity: O(N)
Space complexity: O(TreeHeight)
Write a sub function deep(TreeNode root).
Return a pair(int depth, TreeNode subtreeWithAllDeepest)

In sub function deep(TreeNode root):

if root == null, return pair(0, null)
left = deep(root.left)
right = deep(root.left)

if left depth == right depth,
deepest nodes both in the left and right subtree,
return pair (left.depth + 1, root)

if left depth > right depth,
deepest nodes only in the left subtree,
return pair (left.depth + 1, left subtree)

if left depth < right depth,
deepest nodes only in the right subtree,
return pair (right.depth + 1, right subtree)

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
    public TreeNode subtreeWithAllDeepest(TreeNode root) {
        return deep(root).getValue();
    }
    
    Pair<Integer, TreeNode> deep(TreeNode root) {
        if(root == null) {
            return new Pair(0, null);
        }
        
        Pair<Integer, TreeNode> l = deep(root.left);
        Pair<Integer, TreeNode> r = deep(root.right);
        int d1 = l.getKey();
        int d2 = r.getKey();
        return new Pair(Math.max(d1, d2)+1, (d1 == d2)? root: (d1 > d2)? l.getValue():r.getValue());
    }
}
```