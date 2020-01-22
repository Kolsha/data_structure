### 366. Find Leaves of Binary Tree

https://leetcode.com/problems/find-leaves-of-binary-tree/

Given a binary tree, collect a tree's nodes as if you were doing this: Collect and remove all leaves, repeat until the tree is empty.

 

Example:
```
Input: [1,2,3,4,5]
  
          1
         / \
        2   3
       / \     
      4   5    

Output: [[4,5,3],[2],[1]]
 

Explanation:

1. Removing the leaves [4,5,3] would result in this tree:

          1
         / 
        2          
 

2. Now removing the leaf [2] would result in this tree:

          1          
 

3. Now removing the leaf [1] would result in the empty tree:

          []       
```

[Solution]()

```java
/**
 * Definition for a binary tree node. public class TreeNode { int val; TreeNode
 * left; TreeNode right; TreeNode(int x) { val = x; } }
 */
class Solution {
    public List<List<Integer>> findLeaves(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        findLeaves(root, res);
        return res;
    }

    private int findLeaves(TreeNode root, List<List<Integer>> res) {
        if (root == null) {
            return -1;
        }
        int level = Math.max(findLeaves(root.left, res), findLeaves(root.right, res)) + 1;
        if (res.size() <= level) {
            res.add(new ArrayList<>());
        }
        res.get(level).add(root.val);
        return level;
    }
}
```