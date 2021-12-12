### [100. Same Tree](https://leetcode.com/problems/same-tree/)

Given the roots of two binary trees p and q, write a function to check if they are the same or not.

Two binary trees are considered the same if they are structurally identical, and the nodes have the same value.

 

Example 1:
![](https://assets.leetcode.com/uploads/2020/12/20/ex1.jpg)
```
Input: p = [1,2,3], q = [1,2,3]
Output: true
```
Example 2:
![](https://assets.leetcode.com/uploads/2020/12/20/ex2.jpg)
```
Input: p = [1,2], q = [1,null,2]
Output: false
```
Example 3:
![](https://assets.leetcode.com/uploads/2020/12/20/ex3.jpg)
```
Input: p = [1,2,1], q = [1,1,2]
Output: false
``` 

Constraints:

- The number of nodes in both trees is in the range [0, 100].
- $-10^4$ <= Node.val <= $10^4$

### Solution
#### Approach 1: Recursive

##### Complexity analysis
- Time complexity:
- Space complexity:

###### Java
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
    public boolean isSameTree(TreeNode p, TreeNode q) {
        if (p == null && q == null) {
            return true;
        } else if (p == null || q == null) {
            return false;
        } else if (p.val != q.val) {
            return false;
        }
        
        return isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
    }
}
```

#### Approach 2: Iterative

##### Complexity analysis
- Time complexity:
- Space complexity:

###### Java
```java
public boolean isSameTree(TreeNode p, TreeNode q) {        
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(p);
        queue.add(q);
        while(!queue.isEmpty()){
            TreeNode f = queue.poll();
            TreeNode s = queue.poll();
            if(f == null && s == null){
                continue;
            } else if(f == null || s == null || f.val != s.val){
                return false;
            }
            queue.add(f.left);
            queue.add(s.left);
            queue.add(f.right);
            queue.add(s.right);
        }
        return true;
    }
```

###### Python

```python
# Definition for a binary tree node.
# class TreeNode:
#     def __init__(self, val=0, left=None, right=None):
#         self.val = val
#         self.left = left
#         self.right = right
class Solution:
    def isSameTree(self, p: TreeNode, q: TreeNode) -> bool:
        if not p and not q:
            return True
        elif not p or not q:
            return False
        elif p.val != q.val:
            return False

        return self.isSameTree(p.left, q.left) and self.isSameTree(p.right, q.right)
```

