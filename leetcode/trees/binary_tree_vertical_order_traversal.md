### 314. Binary Tree Vertical Order Traversal

https://leetcode.com/problems/binary-tree-vertical-order-traversal/

Given a binary tree, return the vertical order traversal of its nodes' values. (ie, from top to bottom, column by column).

If two nodes are in the same row and column, the order should be from left to right.

Examples 1:
```
Input: [3,9,20,null,null,15,7]

   3
  /\
 /  \
 9  20
    /\
   /  \
  15   7 

Output:

[
  [9],
  [3,15],
  [20],
  [7]
]
```
Examples 2:
```
Input: [3,9,8,4,0,1,7]

     3
    /\
   /  \
   9   8
  /\  /\
 /  \/  \
 4  01   7 

Output:

[
  [4],
  [9],
  [3,0,1],
  [8],
  [7]
]
```
Examples 3:
```
Input: [3,9,8,4,0,1,7,null,null,null,2,5] (0's right child is 2 and 1's left child is 5)

     3
    /\
   /  \
   9   8
  /\  /\
 /  \/  \
 4  01   7
    /\
   /  \
   5   2

Output:

[
  [4],
  [9,5],
  [3,0,1],
  [8,2],
  [7]
]
```

Solution

Method 1: HashMap + 2-LinkedList level traversal

Time complexity: O(n)

Space complexity: O(n)

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
    public List<List<Integer>> verticalOrder(TreeNode root) {
        ArrayList<List<Integer>> res = new ArrayList<>();
        if (root == null) {
            return res;
        }

        HashMap<Integer, List<Integer>> map = new HashMap<>();
        // level traversal
        LinkedList<TreeNode> list = new LinkedList<>();
        LinkedList<Integer> colList = new LinkedList<>();
        list.add(root);
        colList.add(0);
        int min = Integer.MAX_VALUE, max = Integer.MIN_VALUE;
        while (!list.isEmpty()) {
            TreeNode tmp = list.poll();
            int colNum = colList.poll();
            if (map.containsKey(colNum)) {
                map.get(colNum).add(tmp.val);
            } else {
                // new col number
                ArrayList<Integer> arrayList = new ArrayList<>();
                arrayList.add(tmp.val);
                map.put(colNum, arrayList);
                min = Math.min(min, colNum);
                max = Math.max(max, colNum);
            }

            if (tmp.left != null) {
                list.offer(tmp.left);
                colList.offer(colNum - 1);
            }

            if (tmp.right != null) {
                list.offer(tmp.right);
                colList.offer(colNum + 1);
            }
        }

        for (int i = min; i <= max; i++) {
            res.add(map.get(i));
        }
        return res;
    }
}
```