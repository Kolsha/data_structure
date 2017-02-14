package exam.review.leetcode.trees;

import exam.review.leetcode.trees.BinaryTreePreorderTraversal;

/**
 * Created by shanwu on 16-12-24.
 * PC: 1
 */
public class InvertBinaryTree {
    public BinaryTreePreorderTraversal.TreeNode invertTree(BinaryTreePreorderTraversal.TreeNode root) {
        if(root == null) {
            return null;
        }

        final BinaryTreePreorderTraversal.TreeNode left = root.left, right = root.right;
        root.right = invertTree(left);
        root.left = invertTree(right);
        return root;
    }
}
