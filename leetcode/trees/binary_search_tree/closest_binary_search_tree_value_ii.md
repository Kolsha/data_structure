### 272. Closest Binary Search Tree Value II

https://leetcode.com/problems/closest-binary-search-tree-value-ii/

Given a non-empty binary search tree and a target value, find k values in the BST that are closest to the target.

Note:

- Given target value is a floating point.
- You may assume k is always valid, that is: k ≤ total nodes.
- You are guaranteed to have only one unique set of k values in the BST that are closest to the target.

Example:
```
Input: root = [4,2,5,1,3], target = 3.714286, and k = 2

    4
   / \
  2   5
 / \
1   3

Output: [4,3]
```
Follow up:
- Assume that the BST is balanced, could you solve it in less than O(n) runtime (where n = total nodes)?


Method 1: Recursive inorder traversal

Time complexity O(n)

Space complexity O(k)

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
class Solution {
    public List<Integer> closestKValues(TreeNode root, double target, int k) {
        ArrayList<Integer> res = new ArrayList<>();
        inorder(root, target, k, res);
        return res;
    }
    
    private void inorder(TreeNode root, double target, int k, List<Integer> res) {
        if(root == null) {
            return;
        }
        
        inorder(root.left, target, k, res);
        
        if(res.size() == k) {
            if(Math.abs(root.val - target) >= Math.abs(res.get(0) - target)) {
                return;
            } else {
                res.remove(0);
            }
        }
        res.add(root.val);
        
        inorder(root.right, target, k, res);
    } 
}
```