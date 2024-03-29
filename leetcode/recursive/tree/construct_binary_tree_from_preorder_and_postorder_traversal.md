### [889. Construct Binary Tree from Preorder and Postorder Traversal](https://leetcode.com/problems/construct-binary-tree-from-preorder-and-postorder-traversal/)


Return any binary tree that matches the given preorder and postorder traversals.

Values in the traversals pre and post are distinct positive integers.

 

Example 1:
```
Input: pre = [1,2,4,5,3,6,7], post = [4,5,2,6,7,3,1]
Output: [1,2,3,4,5,6,7]
 
```
Note:

- 1 <= pre.length == post.length <= 30
- pre[] and post[] are both permutations of 1, 2, ..., pre.length.
- It is guaranteed an answer exists. If there exists multiple answers, you can return any of them.


##### Solution

##### Approach 1: Recursive

Create a node TreeNode(pre[preIndex]) as the root.

Becasue root node will be lastly iterated in post order,
if root.val == post[posIndex],
it means we have constructed the whole tree,

If we haven't completed constructed the whole tree,
So we recursively constructFromPrePost for left sub tree and right sub tree.

And finally, we'll reach the posIndex that root.val == post[posIndex].
We increment posIndex and return our root node.


##### Complexity analysis
- Time complexity: Time O(N), as we iterate both pre index and post index only once.
- Space complexity: O(height), depending on the height of constructed tree.


```java
/**
 * Definition for a binary tree node. public class TreeNode { int val; TreeNode
 * left; TreeNode right; TreeNode(int x) { val = x; } }
 */
class Solution {
    int preIdx = 0;
    int postIdx = 0;

    public TreeNode constructFromPrePost(int[] pre, int[] post) {
        TreeNode root = new TreeNode(pre[preIdx++]);
        if (root.val != post[postIdx]) {
            root.left = constructFromPrePost(pre, post);
        }
        if (root.val != post[postIdx]) {
            root.right = constructFromPrePost(pre, post);
        }
        postIdx++;
        return root;
    }
}
```


##### Approach 2: Iterative

##### Complexity analysis
- Time complexity:
- Space complexity:

```java
    public TreeNode constructFromPrePost(int[] pre, int[] post) {
        Deque<TreeNode> s = new ArrayDeque<>();
        s.offer(new TreeNode(pre[0]));
        for (int i = 1, j = 0; i < pre.length; ++i) {
            TreeNode node = new TreeNode(pre[i]);
            while (s.getLast().val == post[j]) {
                s.pollLast(); j++;
            }
            if (s.getLast().left == null) s.getLast().left = node;
            else s.getLast().right = node;
            s.offer(node);
        }
        return s.getFirst();
    }
```