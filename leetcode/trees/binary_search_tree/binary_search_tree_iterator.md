### [173. Binary Search Tree Iterator](https://leetcode.com/problems/binary-search-tree-iterator/)

Implement the `BSTIterator` class that represents an iterator over the in-order traversal of a binary search tree (BST):

- BSTIterator(TreeNode root) Initializes an object of the BSTIterator class. The root of the BST is given as part of the constructor. The pointer should be initialized to a non-existent number smaller than any element in the BST.<br/><br/>

- boolean hasNext() Returns true if there exists a number in the traversal to the right of the pointer, otherwise returns false.<br/><br/>

- int next() Moves the pointer to the right, then returns the number at the pointer.

Notice that by initializing the pointer to a non-existent smallest number, the first call to next() will return the smallest element in the BST.

You may assume that next() calls will always be valid. That is, there will be at least a next number in the in-order traversal when next() is called.

 

Example 1:

```
Input
["BSTIterator", "next", "next", "hasNext", "next", "hasNext", "next", "hasNext", "next", "hasNext"]
[[[7, 3, 15, null, null, 9, 20]], [], [], [], [], [], [], [], [], []]
Output
[null, 3, 7, true, 9, true, 15, true, 20, false]

Explanation
BSTIterator bSTIterator = new BSTIterator([7, 3, 15, null, null, 9, 20]);
bSTIterator.next();    // return 3
bSTIterator.next();    // return 7
bSTIterator.hasNext(); // return True
bSTIterator.next();    // return 9
bSTIterator.hasNext(); // return True
bSTIterator.next();    // return 15
bSTIterator.hasNext(); // return True
bSTIterator.next();    // return 20
bSTIterator.hasNext(); // return False
``` 

Constraints:

- The number of nodes in the tree is in the range $[1, 10^5]$.

- $0 <= Node.val <= 10^6$

- At most $10^5$ calls will be made to hasNext, and next.
 

Follow up:

- Could you implement `next()` and `hasNext()` to run in average `O(1)` time and use `O(h)` memory, where `h` is the height of the tree?


##### Solution

##### Approach 1: Inorder traversal

##### Complexity analysis
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
class BSTIterator {
    private List<TreeNode> holder = new ArrayList<>();

    public BSTIterator(TreeNode root) {
        initListInOrder(root, holder);
    }
    
    /** @return the next smallest number */
    public int next() {
        if(!holder.isEmpty()) {
            return holder.remove(0).val;
        }
        return -1;
    }
    
    /** @return whether we have a next smallest number */
    public boolean hasNext() {
        return !holder.isEmpty();
    }
    
    private void initListInOrder(TreeNode root, List<TreeNode> holder) {
        if(root == null) {
            return;
        }
        if(root.left != null) {
            initListInOrder(root.left, holder);
        }

        holder.add(root);
        
        if(root.right != null) {
            initListInOrder(root.right, holder);
        }
    }
}

/**
 * Your BSTIterator object will be instantiated and called as such:
 * BSTIterator obj = new BSTIterator(root);
 * int param_1 = obj.next();
 * boolean param_2 = obj.hasNext();
 */
```



##### Approach 2: Stack

##### Complexity analysis
So this can satisfy O(h) memory, hasNext() in O(1) time,
But next() is O(h) time.

The average time complexity of next() function is O(1) indeed in your case. As the next function can be called n times at most, and the number of right nodes in self.pushAll(tmpNode.right) function is maximal n in a tree which has n nodes, so the amortized time complexity is O(1).

https://leetcode.com/problems/binary-search-tree-iterator/discuss/52525/My-solutions-in-3-languages-with-Stack/53502

- Time complexity:
- Space complexity:

```java
/**
 * Definition for binary tree
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */

public class BSTIterator {
    
    private Stack<TreeNode> stack;
    public BSTIterator(TreeNode root) {
        stack = new Stack<>();
        TreeNode cur = root;
        while(cur != null) {
            stack.push(cur);
            if(cur.left != null) {
                cur = cur.left;
            } else {
                break;
            }
        }
    }

    /** @return whether we have a next smallest number */
    public boolean hasNext() {
        return !stack.isEmpty();
    }

    /** @return the next smallest number */
    public int next() {
        TreeNode node = stack.pop();
        TreeNode cur = node;
        // traversal right branch
        if(cur.right != null) {
            cur = cur.right;
            while(cur != null) {
                stack.push(cur);
                if(cur.left != null) {
                    cur = cur.left;
                } else {
                    break;
                }
            }
        }
        return node.val;
    }
}

/**
 * Your BSTIterator will be called like this:
 * BSTIterator i = new BSTIterator(root);
 * while (i.hasNext()) v[f()] = i.next();
 */
```