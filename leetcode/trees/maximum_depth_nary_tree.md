### 559. Maximum Depth of N-ary Tree
https://leetcode.com/problems/maximum-depth-of-n-ary-tree/

Given a n-ary tree, find its maximum depth.

The maximum depth is the number of nodes along the longest path from the root node down to the farthest leaf node.

Nary-Tree input serialization is represented in their level order traversal, each group of children is separated by the null value (See examples).

 

Example 1:
![](https://assets.leetcode.com/uploads/2018/10/12/narytreeexample.png)
```
Input: root = [1,null,3,2,4,null,5,6]
Output: 3
```

Example 2:

![](https://assets.leetcode.com/uploads/2019/11/08/sample_4_964.png)
```
Input: root = [1,null,2,3,4,5,null,null,6,7,null,8,null,9,10,null,null,11,null,12,null,13,null,null,14]
Output: 5
 
```

Constraints:

- The depth of the n-ary tree is less than or equal to 1000.
- The total number of nodes is between [0, 10^4].

Solution:
BFS
```java
class Solution {
    public int maxDepth(Node root) {
        if (root == null) {
            return 0;
        }

        Queue<Node> list = new LinkedList<>();
        list.offer(root);
        int depth = 0;
        while (!list.isEmpty()) {
            int childSize = list.size();
            for (int i = 0; i < childSize; i++) {
                Node curr = list.poll();
                for (Node child : curr.children) {
                    list.offer(child);
                }
            }
            depth++;
        }
        return depth;

    }
}
```

// todo DFS