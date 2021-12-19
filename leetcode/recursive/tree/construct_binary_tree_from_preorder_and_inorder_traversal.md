### [105. Construct Binary Tree from Preorder and Inorder Traversal](https://leetcode.com/problems/construct-binary-tree-from-preorder-and-inorder-traversal/)


Given preorder and inorder traversal of a tree, construct the binary tree.

Note:
You may assume that duplicates do not exist in the tree.

For example, given
```
preorder = [3,9,20,15,7]
inorder = [9,3,15,20,7]
```
Return the following binary tree:
```
    3
   / \
  9  20
    /  \
   15   7
```
##### Solution

There are two general strategies to traverse a tree:

- Breadth First Search `(BFS)`
  We scan through the tree level by level, following the order of height, from top to bottom. The nodes on higher level would be visited before the ones with lower levels.

- Depth First Search `(DFS)`
  In this strategy, we adopt the depth as the priority, so that one would start from a root and reach all the way down to certain leaf, and then back to root to reach another branch.
  
  The DFS strategy can further be distinguished as preorder, inorder, and postorder depending on the relative order among the root node, left node and right node.

On the following figure the nodes are numerated in the order you visit them, please follow 1-2-3-4-5 to compare different strategies.
![](./res/145_transverse.png)

##### Approach 1: HashMap caching + Recursion

##### Complexity analysis
- Time complexity: O(n)
- Space complexity: O(n)

```java
/**
 * Definition for a binary tree node. public class TreeNode { int val; TreeNode
 * left; TreeNode right; TreeNode(int x) { val = x; } }
 */
class Solution {
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for(int index = 0; index < inorder.length; index++) {
            map.put(inorder[index], index);
        }
        
        return helper(preorder, inorder, map, 0, 0, inorder.length - 1);
    }
    
    public TreeNode helper(int[] preorder, int[] inorder, HashMap<Integer, Integer> map, int preIdx, int inStart, int inEnd) {
        if(preIdx > preorder.length - 1 || inStart > inEnd) {
            return null;
        }
        TreeNode root = new TreeNode(preorder[preIdx]);
        // find left nodes from inorder
        int inorderRootIdx = map.get(root.val);
        // Left tree's size: inorderRootIdx - inStart
        int leftLengh = inorderRootIdx - inStart;
        root.left = helper(preorder, inorder, map, preIdx + 1, inStart, inorderRootIdx - 1);
        root.right = helper(preorder, inorder, map, preIdx + 1 + leftLengh, inorderRootIdx + 1, inEnd);
        return root;
    }

}
```


##### Approach 2: Iterative
Keep pushing the nodes from the preorder into a stack (and keep making the tree by adding nodes to the left of the previous node) until the top of the stack matches the inorder.

At this point, pop the top of the stack until the top does not equal inorder (keep a flag to note that you have made a pop).

Repeat 1 and 2 until preorder is empty. The key point is that whenever the flag is set, insert a node to the right and reset the flag.

##### Complexity analysis
- Time complexity:
- Space complexity:

```java
/**
 * Definition for a binary tree node. public class TreeNode { int val; TreeNode
 * left; TreeNode right; TreeNode(int x) { val = x; } }
 */
class Solution {

    public TreeNode buildTree(int[] preorder, int[] inorder) {
        Stack<TreeNode> s = new Stack<>();
        TreeNode root = new TreeNode(preorder[0]), cur = root;
        for (int i = 1, j = 0; i < preorder.length; i++) {
            if (cur.val != inorder[j]) {
                cur.left = new TreeNode(preorder[i]);
                s.push(cur);
                cur = cur.left;
            } else {
                j++;
                while (!s.empty() && s.peek().val == inorder[j]) {
                    cur = s.pop();
                    j++;
                }
                cur = cur.right = new TreeNode(preorder[i]);
            }
        }
        return root;
    }    
}
```