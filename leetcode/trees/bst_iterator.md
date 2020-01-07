### 173. Binary Search Tree Iterator

https://leetcode.com/problems/binary-search-tree-iterator/

Implement an iterator over a binary search tree (BST). Your iterator will be initialized with the root node of a BST.

Calling next() will return the next smallest number in the BST.

Example:

![](https://assets.leetcode.com/uploads/2018/12/25/bst-tree.png)
```
BSTIterator iterator = new BSTIterator(root);
iterator.next();    // return 3
iterator.next();    // return 7
iterator.hasNext(); // return true
iterator.next();    // return 9
iterator.hasNext(); // return true
iterator.next();    // return 15
iterator.hasNext(); // return true
iterator.next();    // return 20
iterator.hasNext(); // return false
```
Note:

- next() and hasNext() should run in average O(1) time and uses O(h) memory, where h is the height of the tree.
- You may assume that next() call will always be valid, that is, there will be at least a next smallest number in the BST when next() is called.

Solution:
1. Space(N), Time(1)
```java
/**
 * Definition for a binary tree node. public class TreeNode { int val; TreeNode
 * left; TreeNode right; TreeNode(int x) { val = x; } }
 */
class BSTIterator {
    private List<TreeNode> holder = new ArrayList<>();

    public BSTIterator(TreeNode root) {
        initListInOrder(root, holder);
    }

    /** @return the next smallest number */
    public int next() {
        if (!holder.isEmpty()) {
            return holder.remove(0).val;
        }
        return -1;
    }

    /** @return whether we have a next smallest number */
    public boolean hasNext() {
        return !holder.isEmpty();
    }

    private void initListInOrder(TreeNode root, List<TreeNode> holder) {
        if (root == null) {
            return;
        }
        if (root.left != null) {
            initListInOrder(root.left, holder);
        }

        holder.add(root);

        if (root.right != null) {
            initListInOrder(root.right, holder);
        }
    }
}
```
2. Space(Log(N)), Time(1)
From: https://leetcode.com/problems/binary-search-tree-iterator/discuss/52526/Ideal-Solution-using-Stack-(Java)

// todo
```java
```