package exam.review.leetcode.trees;

import exam.review.leetcode.trees.BinaryTreePreorderTraversal;

/**
 * Question description:
 * https://leetcode.com/problems/sum-of-left-leaves/description/
 * PC: 2
 */
public class SumOfLeftLeaves {
    public int sumOfLeftLeaves(BinaryTreePreorderTraversal.TreeNode root) {
        if (root == null) {
            return 0;
        }

        int result = 0;
        if (root.left != null && isExternal(root.left)) {
            result = root.left.val;
        }

        return result + sumOfLeftLeaves(root.left) + sumOfLeftLeaves(root.right);
    }

    public boolean isExternal(BinaryTreePreorderTraversal.TreeNode node) {
        return node.left == null && node.right == null;
    }
}
