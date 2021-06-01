### [102. Binary Tree Level Order Traversal](https://leetcode.com/problems/binary-tree-level-order-traversal/)

Given a binary tree, return the level order traversal of its nodes' values. (ie, from left to right, level by level).

For example:
![](https://assets.leetcode.com/uploads/2021/02/19/tree1.jpg)
```
Given binary tree [3,9,20,null,null,15,7],
    3
   / \
  9  20
    /  \
   15   7
```
return its level order traversal as:
```
[
  [3],
  [9,20],
  [15,7]
]
```

### Solution

#### Approach 1(Optimal): Recursive approach - DFS

##### Complexity Analysis
- Time complexity: O(n)
- Space complexity: O(n)

In Java
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
    public List<List<Integer>> levelOrder(TreeNode root) {
        return helper(root, 0, new ArrayList<>());
    }

    private List<List<Integer>> helper(TreeNode root, int level, List<List<Integer>> res) {
        if(root == null) {
            return res;
        }

        if(level == res.size()) {
            res.add(new ArrayList<>());
        }

        res.get(level).add(root.val);

        helper(root.left, level+1, res);
        helper(root.right, level+1, res);
        return res;
    }
}
```

#### Approach 2: Iterative approach - BFS

- Time complexity:

- Space complexity:


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
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if(root == null) {
            return res;
        }
        LinkedList<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        
        while(!queue.isEmpty()) {
            ArrayList<Integer> level = new ArrayList<>();
            ArrayList<TreeNode> next = new ArrayList<>();

            while(!queue.isEmpty()) {
                TreeNode tmp = queue.poll();
                if(tmp!=null) {
                    level.add(tmp.val);
                    if(tmp.left!=null) {
                        next.add(tmp.left);
                    }
                    
                    if(tmp.right!=null) {
                        next.add(tmp.right);
                    }
                }
            }
            res.add(level);
            queue.addAll(next);
        }
        return res;
    }
}
```