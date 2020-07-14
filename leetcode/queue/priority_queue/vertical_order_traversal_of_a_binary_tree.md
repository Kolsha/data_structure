### 987. Vertical Order Traversal of a Binary Tree

Given a binary tree, return the vertical order traversal of its nodes values.

For each node at position `(X, Y)`, its left and right children respectively will be at positions `(X-1, Y-1)` and `(X+1, Y-1)`.

Running a vertical line from `X = -infinity` to `X = +infinity`, whenever the vertical line touches some nodes, we report the values of the nodes in order from top to bottom (decreasing `Y` coordinates).

If two nodes have the same position, then the value of the node that is reported first is the value that is smaller.

Return an list of non-empty reports in order of `X` coordinate.  Every report will have a list of values of nodes.

 

Example 1:
![](./../res/1236_example_1.png)

```
Input: [3,9,20,null,null,15,7]
Output: [[9],[3,15],[20],[7]]
Explanation: 
Without loss of generality, we can assume the root node is at position (0, 0):
Then, the node with value 9 occurs at position (-1, -1);
The nodes with values 3 and 15 occur at positions (0, 0) and (0, -2);
The node with value 20 occurs at position (1, -1);
The node with value 7 occurs at position (2, -2).
```
Example 2:
![](./../res/tree2.png)
```
Input: [1,2,3,4,5,6,7]
Output: [[4],[2],[1,5,6],[3],[7]]
Explanation: 
The node with value 5 and the node with value 6 have the same position according to the given scheme.
However, in the report "[1,5,6]", the node value of 5 comes first since 5 is smaller than 6.
``` 

Note:

The tree will have between 1 and 1000 nodes.
Each node's value will be between 0 and 1000.

Solution

Approach 1: PriorityQueue + Tree traversal

Complexity Analysis:

Time Complexity:

Space Complexity:

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
    public List<List<Integer>> verticalTraversal(TreeNode root) {
        Comparator<Node> cmp = new Comparator<>() {
            @Override
            public int compare(Node a, Node b) {
                if(a.column!=b.column) {
                    return a.column - b.column;
                } else {
                    if(a.level!=b.level) {
                        return a.level - b.level;
                    } else {
                        return a.value - b.value;
                    }
                }
            }
        };
        PriorityQueue<Node> queue = new PriorityQueue<>(cmp);        
        int[] min = new int[] {Integer.MAX_VALUE};
        int[] max = new int[] {Integer.MIN_VALUE};
        preOrderTraversal(root, 0, 0, queue, min, max);
                
        ArrayList<List<Integer>> res = new ArrayList<>();
        
        for(int col = min[0]; col <= max[0]; col++) {
            ArrayList<Integer> temp = new ArrayList<>();
            Node node = queue.peek();
            while(node!= null && node.column == col) {
                temp.add(queue.poll().value);
                node = queue.peek();
            }
            res.add(temp);
        }

        return res;
    }
    
    private void preOrderTraversal(TreeNode root, int level, int col, PriorityQueue<Node> queue, int[] min, int[] max) {
        if(root == null) {
            return;
        }
        
        Node node = new Node(col, level, root.val);
        // update max and min
        if(node.column > max[0]) {
            max[0] = node.column;
        }
        
        if(node.column < min[0]) {
            min[0] = node.column;
        }
        
        // add queue
        queue.add(node);        
        
        // visiting child node recursively
        preOrderTraversal(root.left, level+1, col-1, queue, min, max);
        preOrderTraversal(root.right, level+1, col+1, queue, min, max);         
    }
    
    static class Node {
        int column;
        int level;
        int value;

        Node(int col, int lvl, int val) {
            column = col;
            level = lvl;
            value = val;
        }
        
        @Override
        public String toString() {
            return "【Node-> col: "+column+", lvl: "+ level + ", val: "+ value+" 】";
        }
    }
}
```