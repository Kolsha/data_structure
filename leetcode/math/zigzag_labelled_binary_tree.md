### 1104. Path In Zigzag Labelled Binary Tree
https://leetcode.com/problems/path-in-zigzag-labelled-binary-tree/

In an infinite binary tree where every node has two children, the nodes are labelled in row order.

In the odd numbered rows (ie., the first, third, fifth,...), the labelling is left to right, while in the even numbered rows (second, fourth, sixth,...), the labelling is right to left.
![](https://assets.leetcode.com/uploads/2019/06/24/tree.png)


Given the `label` of a node in this tree, return the labels in the path from the root of the tree to the node with that `label`.

**Solution:**
<br/>from:<br/>
https://leetcode.com/problems/path-in-zigzag-labelled-binary-tree/discuss/323312/Simple-solution-in-java-(Using-properties-of-complete-binary-tree)-(O-log-N)

1. Calculate current depth of the label
2. Calculate offset (for each depth, values lie from 2^d -> 2^(d+1) -1
3. Find the real parent based on offset
4. Repeat until we reach 1

e.g. parent of 14 is 4

1. depth = 3, values in this depth lie from 8 to 15 (since it is a complete binary tree)
2. offset = 15 - 14 = 1
3. real parent of 14 = parent of ( 8 + offset ) = parent (9) = 9/2 = 4
```java
class Solution {
    public List<Integer> pathInZigZagTree(int label) {
        int parent = label;
        ArrayList<Integer> list = new ArrayList<>();
        list.add(0, parent);

        while (parent != 1) {
            int depth = (int) (Math.log(parent) / Math.log(2));
            int offset = (int) Math.pow(2, depth + 1) - 1 - parent;
            parent = (int) (Math.pow(2, depth) + offset) / 2;
            list.add(0, parent);
        }
        return list;
    }
}
```