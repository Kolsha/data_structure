### 116. Populating Next Right Pointers in Each Node

https://leetcode.com/problems/populating-next-right-pointers-in-each-node/

You are given a **perfect binary tree** where all leaves are on the same level, and every parent has two children. The binary tree has the following definition:
```
struct Node {
  int val;
  Node *left;
  Node *right;
  Node *next;
}
```
Populate each next pointer to point to its next right node. If there is no next right node, the next pointer should be set to `NULL`.

Initially, all next pointers are set to `NULL`.

 

Follow up:

- You may only use constant extra space.
- Recursive approach is fine, you may assume implicit stack space does not count as extra space for this problem.


Solution

Approach 1: Level order traversal
Level order traversal template
```java
 while (!Q.empty())
 {
     size = Q.size()
     for i in range 0..size
     {
         node = Q.pop()
         Q.push(node.left)
         Q.push(node.right)
     }
 }
```

Complexity analysis:
- Time complexity: O(n)  - Since we process each node exactly once
- Space complexity: O(n) - This is a perfect binary tree which means the last level contains N/2 nodes. The space complexity for breadth first traversal is the space occupied by the queue which is dependent upon the maximum number of nodes in particular level. So, in this case, the space complexity would be O(N)O(N).

```java
/*
// Definition for a Node.
class Node {
    public int val;
    public Node left;
    public Node right;
    public Node next;

    public Node() {}
    
    public Node(int _val) {
        val = _val;
    }

    public Node(int _val, Node _left, Node _right, Node _next) {
        val = _val;
        left = _left;
        right = _right;
        next = _next;
    }
};
*/

class Solution {
    public Node connect(Node root) {
        if(root == null) {
            return root;
        }

        Queue<Node> queue = new LinkedList<>();
        queue.add(root);
        while(!queue.isEmpty()) {
            int size = queue.size();
            for(int i = 0; i < size; i++) {
                Node node = queue.poll();
                if(i < size - 1) {
                    node.next = queue.peek();
                }
                
                if(node.left != null) {
                    queue.add(node.left);
                }
                
                if(node.right != null) {
                    queue.add(node.right);
                }
            }
        }
        return root;
    }
}
```