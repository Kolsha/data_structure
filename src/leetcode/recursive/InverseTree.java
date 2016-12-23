package leetcode.recursive;

import leetcode.trees.BinaryTreePreorderTraversal;

/**
 * Created by shanwu on 16-12-24.
 */
public class InverseTree {
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
