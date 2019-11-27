package exam.review.leetcode.trees;

import exam.review.leetcode.trees.BinaryTreePreorderTraversal;

/**
 * Question description:
 * https://leetcode.com/problems/invert-binary-tree/description/
 * PC: 2
 */
public class InvertBinaryTree {
    public BinaryTreePreorderTraversal.TreeNode invertTree(BinaryTreePreorderTraversal.TreeNode root) {
        if (root == null) {
            return null;
        }

        final BinaryTreePreorderTraversal.TreeNode left = root.left, right = root.right;
        root.right = invertTree(left);
        root.left = invertTree(right);
        return root;
    }
}
