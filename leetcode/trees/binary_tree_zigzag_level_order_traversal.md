### 103. Binary Tree Zigzag Level Order Traversal

https://leetcode.com/problems/binary-tree-zigzag-level-order-traversal

Given a binary tree, return the zigzag level order traversal of its nodes' values. (ie, from left to right, then right to left for the next level and alternate between).

For example:
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

Solution
![](https://leetcode.com/problems/binary-tree-zigzag-level-order-traversal/Figures/103/103_BFS.png)
Approach 1: Recursive DFS approach
Complexity analysis:
- Time complexity:
- Space complexity:

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
class Solution {
  public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
    if (root == null) {
      return new ArrayList<List<Integer>>();
    }

    List<List<Integer>> results = new ArrayList<List<Integer>>();

    // add the root element with a delimiter to kick off the BFS loop
    LinkedList<TreeNode> node_queue = new LinkedList<TreeNode>();
    node_queue.addLast(root);
    node_queue.addLast(null);

    LinkedList<Integer> level_list = new LinkedList<Integer>();
    boolean is_order_left = true;

    while (node_queue.size() > 0) {
      TreeNode curr_node = node_queue.poll();
      if (curr_node != null) {
        if (is_order_left) {
          level_list.addLast(curr_node.val);
        } else {
          level_list.addFirst(curr_node.val);
        }

        if (curr_node.left != null) {
          node_queue.addLast(curr_node.left);
        }

        if (curr_node.right != null) {
          node_queue.addLast(curr_node.right);
        }
      } else {
        // we finish the scan of one level
        results.add(level_list);
        level_list = new LinkedList<Integer>();
        // prepare for the next level
        if (node_queue.size() > 0) {
          node_queue.addLast(null);
        }
        is_order_left = !is_order_left;
      }
    }
    return results;
  }
}
```