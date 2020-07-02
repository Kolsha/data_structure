### 129. Sum Root to Leaf Numbers

https://leetcode.com/problems/sum-root-to-leaf-numbers/

Given a binary tree containing digits from 0-9 only, each root-to-leaf path could represent a number.

An example is the root-to-leaf path 1->2->3 which represents the number 123.

Find the total sum of all root-to-leaf numbers.

Note: A leaf is a node with no children.

Example:
```
Input: [1,2,3]
    1
   / \
  2   3
Output: 25
Explanation:
The root-to-leaf path 1->2 represents the number 12.
The root-to-leaf path 1->3 represents the number 13.
Therefore, sum = 12 + 13 = 25.
```
Example 2:
```
Input: [4,9,0,5,1]
    4
   / \
  9   0
 / \
5   1
Output: 1026
Explanation:
The root-to-leaf path 4->9->5 represents the number 495.
The root-to-leaf path 4->9->1 represents the number 491.
The root-to-leaf path 4->0 represents the number 40.
Therefore, sum = 495 + 491 + 40 = 1026.
```

Solution

Approach 1: Recursive
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
    public int sumNumbers(TreeNode root) {
        int[] sum = new int[1];
        helper(root, 0, sum);
        return sum[0];
    }
    
    public void helper(TreeNode root, int curr, int[] total) {
        if(root == null) {
            return;
        }
        
        if(root.left == null && root.right == null) {
            total[0] += curr * 10 + root.val; 
            return;
        }
        
        helper(root.left, curr * 10 + root.val, total);
        helper(root.right, curr * 10 + root.val, total);
    }
}
```

Approach 2: Iterative Preorder Traversal
Here we implement standard iterative preorder traversal with the stack:


<ul>
<li>
<p>Push root into stack.</p>
</li>
<li>
<p>While stack is not empty:</p>
<ul>
<li>
<p>Pop out a node from stack and update the current number.</p>
</li>
<li>
<p>If the node is a leaf, update root-to-leaf sum.</p>
</li>
<li>
<p>Push right and left child nodes into stack.</p>
</li>
</ul>
</li>
<li>
<p>Return root-to-leaf sum.</p>
</li>
</ul>

```java
class Solution {
  public int sumNumbers(TreeNode root) {
    int rootToLeaf = 0, currNumber = 0;
    Deque<Pair<TreeNode, Integer>> stack = new ArrayDeque();
    stack.push(new Pair(root, 0));

    while (!stack.isEmpty()) {
      Pair<TreeNode, Integer> p = stack.pop();
      root = p.getKey();
      currNumber = p.getValue();

      if (root != null) {
        currNumber = currNumber * 10 + root.val;
        // if it's a leaf, update root-to-leaf sum
        if (root.left == null && root.right == null) {
          rootToLeaf += currNumber;
        } else {
          stack.push(new Pair(root.right, currNumber));
          stack.push(new Pair(root.left, currNumber));
        }
      }
    }
    return rootToLeaf;
  }
}
```

Approach 3: Morris Preorder Traversal

<p>We discussed already iterative and recursive preorder traversals,
which both have great time complexity though use up to
<span class="katex"><span class="katex-mathml"><math><semantics><mrow><mi mathvariant="script">O</mi><mo>(</mo><mi>H</mi><mo>)</mo></mrow><annotation encoding="application/x-tex">\mathcal{O}(H)</annotation></semantics></math></span><span class="katex-html" aria-hidden="true"><span class="base"><span class="strut" style="height:1em;vertical-align:-0.25em;"></span><span class="mord"><span class="mord mathcal" style="margin-right:0.02778em;">O</span></span><span class="mopen">(</span><span class="mord mathdefault" style="margin-right:0.08125em;">H</span><span class="mclose">)</span></span></span></span> to keep the stack. We could trade in performance to save space.</p>

<p>The idea of Morris preorder traversal is simple:
to use no space but to traverse the tree.</p>

<blockquote>
<p>How that could be even possible? At each node one has to decide where to go:
to the left or tj the right, traverse the left subtree or traverse the right subtree.
How one could know that the left subtree is already done if no
additional memory is allowed?</p>
</blockquote>

<p>The idea of <a href="https://www.sciencedirect.com/science/article/pii/0020019079900681">Morris</a>
algorithm is to set the <em>temporary link</em> between the node and its
<a href="https://leetcode.com/articles/delete-node-in-a-bst/">predecessor</a>:
<code>predecessor.right = root</code>.
So one starts from the node, computes its predecessor and
verifies if the link is present.</p>

<ul>
<li>
<p>There is no link? Set it and go to the left subtree.</p>
</li>
<li>
<p>There is a link? Break it and go to the right subtree.</p>
</li>
</ul>

<p>There is one small issue to deal with : what if there is no
left child, i.e. there is no left subtree?
Then go straightforward to the right subtree.</p>

Complexity Analysis
<ul>
<li>
<p>Time complexity: <span class="katex"><span class="katex-mathml"><math><semantics><mrow><mi mathvariant="script">O</mi><mo>(</mo><mi>N</mi><mo>)</mo></mrow><annotation encoding="application/x-tex">\mathcal{O}(N)</annotation></semantics></math></span><span class="katex-html" aria-hidden="true"><span class="base"><span class="strut" style="height:1em;vertical-align:-0.25em;"></span><span class="mord"><span class="mord mathcal" style="margin-right:0.02778em;">O</span></span><span class="mopen">(</span><span class="mord mathdefault" style="margin-right:0.10903em;">N</span><span class="mclose">)</span></span></span></span>.</p>
</li>
<li>
<p>Space complexity: <span class="katex"><span class="katex-mathml"><math><semantics><mrow><mi mathvariant="script">O</mi><mo>(</mo><mn>1</mn><mo>)</mo></mrow><annotation encoding="application/x-tex">\mathcal{O}(1)</annotation></semantics></math></span><span class="katex-html" aria-hidden="true"><span class="base"><span class="strut" style="height:1em;vertical-align:-0.25em;"></span><span class="mord"><span class="mord mathcal" style="margin-right:0.02778em;">O</span></span><span class="mopen">(</span><span class="mord">1</span><span class="mclose">)</span></span></span></span>.
<br>
<br></p>
</li>
</ul>

```java
class Solution {
  public int sumNumbers(TreeNode root) {
    int rootToLeaf = 0, currNumber = 0;
    int steps;
    TreeNode predecessor;

    while (root != null) {
      // If there is a left child,
      // then compute the predecessor.
      // If there is no link predecessor.right = root --> set it.
      // If there is a link predecessor.right = root --> break it.
      if (root.left != null) {
        // Predecessor node is one step to the left
        // and then to the right till you can.
        predecessor = root.left;
        steps = 1;
        while (predecessor.right != null && predecessor.right != root) {
          predecessor = predecessor.right;
          ++steps;
        }

        // Set link predecessor.right = root
        // and go to explore the left subtree
        if (predecessor.right == null) {
          currNumber = currNumber * 10 + root.val;
          predecessor.right = root;
          root = root.left;
        }
        // Break the link predecessor.right = root
        // Once the link is broken,
        // it's time to change subtree and go to the right
        else {
          // If you're on the leaf, update the sum
          if (predecessor.left == null) {
            rootToLeaf += currNumber;
          }
          // This part of tree is explored, backtrack
          for(int i = 0; i < steps; ++i) {
            currNumber /= 10;
          }
          predecessor.right = null;
          root = root.right;
        }
      }

      // If there is no left child
      // then just go right.
      else {
        currNumber = currNumber * 10 + root.val;
        // if you're on the leaf, update the sum
        if (root.right == null) {
          rootToLeaf += currNumber;
        }
        root = root.right;
      }
    }
    return rootToLeaf;
  }
}
```