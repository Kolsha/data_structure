### [103. Binary Tree Zigzag Level Order Traversal](https://leetcode.com/problems/binary-tree-zigzag-level-order-traversal)

Given a binary tree, return the zigzag level order traversal of its nodes' values. (ie, from left to right, then right to left for the next level and alternate between).

For example:
![](https://assets.leetcode.com/uploads/2021/02/19/tree1.jpg)
```
Given binary tree [3,9,20,null,null,15,7],
    3
   / \
  9  20
    /  \
   15   7
return its zigzag level order traversal as:
[
  [3],
  [20,9],
  [15,7]
]
```
Constraints:

- The number of nodes in the tree is in the range [0, 2000].
- -100 <= Node.val <= 100


### Solution
![](https://leetcode.com/problems/binary-tree-zigzag-level-order-traversal/Figures/103/103_BFS.png)


#### Approach 1: Recursive DFS approach

##### Complexity analysis:
- Time complexity: O(n)
- Space complexity: O(n)

```java
public class Solution {
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        travel(root, res, 0);
        return res;
    }
    
    private void travel(TreeNode curr, List<List<Integer>> sol, int level) {
        if(curr == null) {
            return;
        }
        
        // add a arraylist for the level
        if(sol.size() <= level) {
            List<Integer> newLevel = new ArrayList<>();
            sol.add(newLevel);
        }
        
        List<Integer> collection  = sol.get(level);
        if((level & 1) == 0) {
            // last in first out
            collection.add(curr.val);
        } else {
            // first in first out
            collection.add(0, curr.val);
        }

        travel(curr.left, sol, level + 1);
        travel(curr.right, sol, level + 1);
    }
}
```


Approach 2: BFS

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
public class Solution {
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if(root == null) {
            return res;
        }
        
        Queue<TreeNode> q = new LinkedList<>();
        q.add(root);
        boolean order = true;
        
        while(!q.isEmpty()) {
            List<Integer> tmp = new ArrayList<>();
            int size = q.size();
            for(int i = 0; i < size; ++i) {
                TreeNode n = q.poll();
                if(order) {
                    tmp.add(n.val);
                } else {
                    tmp.add(0, n.val);
                }
            
                if(n.left != null) {
                    q.add(n.left);
                }
            
                if(n.right != null) {
                    q.add(n.right);
                }
            }
            res.add(tmp);
            order = !order;
        }
        return res;
    }
}
```