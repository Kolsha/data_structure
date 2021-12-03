### [98. Validate Binary Search Tree](https://leetcode.com/problems/validate-binary-search-tree/)

Given a binary tree, determine if it is a valid binary search tree (BST).

Assume a BST is defined as follows:

- The left subtree of a node contains only nodes with keys less than the node's key.
- The right subtree of a node contains only nodes with keys greater than the node's key.
- Both the left and right subtrees must also be binary search trees.
 

Example 1:
```
    2
   / \
  1   3

Input: [2,1,3]
Output: true
```
Example 2:
```
    5
   / \
  1   4
     / \
    3   6

Input: [5,1,4,null,null,3,6]
Output: false
Explanation: The root node's value is 5 but its right child's value is 4.
```

Solution

Approach 1: Recursive one

Complexity analysis
<ul>
<li>Time complexity : <span class="katex"><span class="katex-mathml"><math><semantics><mrow><mi mathvariant="script">O</mi><mo>(</mo><mi>N</mi><mo>)</mo></mrow><annotation encoding="application/x-tex">\mathcal{O}(N)</annotation></semantics></math></span><span class="katex-html" aria-hidden="true"><span class="base"><span class="strut" style="height:1em;vertical-align:-0.25em;"></span><span class="mord"><span class="mord mathcal" style="margin-right:0.02778em;">O</span></span><span class="mopen">(</span><span class="mord mathdefault" style="margin-right:0.10903em;">N</span><span class="mclose">)</span></span></span></span> since we visit each node exactly once.</li>
<li>Space complexity : <span class="katex"><span class="katex-mathml"><math><semantics><mrow><mi mathvariant="script">O</mi><mo>(</mo><mi>N</mi><mo>)</mo></mrow><annotation encoding="application/x-tex">\mathcal{O}(N)</annotation></semantics></math></span><span class="katex-html" aria-hidden="true"><span class="base"><span class="strut" style="height:1em;vertical-align:-0.25em;"></span><span class="mord"><span class="mord mathcal" style="margin-right:0.02778em;">O</span></span><span class="mopen">(</span><span class="mord mathdefault" style="margin-right:0.10903em;">N</span><span class="mclose">)</span></span></span></span> since we keep up to the entire tree.</li>
</ul>

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
    public boolean isValidBST(TreeNode root) {
        Integer min = null;
        Integer max = null;
        return helper(root, min, max);
    }
    
    private boolean helper(TreeNode root, Integer min, Integer max) {
        if(root == null) {
            return true;
        }
        
        if(min != null && root.val <= min) {
            return false;
        }
        
        if(max != null && root.val >= max) {
            return false;
        }
        
        return helper(root.left, min, root.val) && helper(root.right, root.val, max);
    }
}
```

Approach 2: Iterative

Complexity Analysis
<ul>
<li>Time complexity : <span class="katex"><span class="katex-mathml"><math><semantics><mrow><mi mathvariant="script">O</mi><mo>(</mo><mi>N</mi><mo>)</mo></mrow><annotation encoding="application/x-tex">\mathcal{O}(N)</annotation></semantics></math></span><span class="katex-html" aria-hidden="true"><span class="base"><span class="strut" style="height:1em;vertical-align:-0.25em;"></span><span class="mord"><span class="mord mathcal" style="margin-right:0.02778em;">O</span></span><span class="mopen">(</span><span class="mord mathdefault" style="margin-right:0.10903em;">N</span><span class="mclose">)</span></span></span></span> since we visit each node exactly once.</li>
<li>Space complexity : <span class="katex"><span class="katex-mathml"><math><semantics><mrow><mi mathvariant="script">O</mi><mo>(</mo><mi>N</mi><mo>)</mo></mrow><annotation encoding="application/x-tex">\mathcal{O}(N)</annotation></semantics></math></span><span class="katex-html" aria-hidden="true"><span class="base"><span class="strut" style="height:1em;vertical-align:-0.25em;"></span><span class="mord"><span class="mord mathcal" style="margin-right:0.02778em;">O</span></span><span class="mopen">(</span><span class="mord mathdefault" style="margin-right:0.10903em;">N</span><span class="mclose">)</span></span></span></span> since we keep up to the entire tree.
<br>
<br></li>
</ul>

```java
class Solution {
  LinkedList<TreeNode> stack = new LinkedList();
  LinkedList<Integer> uppers = new LinkedList(),
          lowers = new LinkedList();

  public void update(TreeNode root, Integer lower, Integer upper) {
    stack.add(root);
    lowers.add(lower);
    uppers.add(upper);
  }

  public boolean isValidBST(TreeNode root) {
    Integer lower = null, upper = null, val;
    update(root, lower, upper);

    while (!stack.isEmpty()) {
      root = stack.poll();
      lower = lowers.poll();
      upper = uppers.poll();

      if (root == null) continue;
      val = root.val;
      if (lower != null && val <= lower) return false;
      if (upper != null && val >= upper) return false;
      update(root.right, val, upper);
      update(root.left, lower, val);
    }
    return true;
  }
}
```