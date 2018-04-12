package exam.review.leetcode.trees;

import java.util.ArrayList;
import java.util.List;

/**
 * Question description:
 * https://leetcode.com/problems/binary-tree-preorder-traversal/description/
 * PC: 2
 */
public class BinaryTreePreorderTraversal {
    /**
     * Given a binary tree, return the preorder traversal of its nodes' values.
     *
     * @param root
     * @return
     */
    public List<Integer> preorderTraversal(TreeNode root) {
        ArrayList<Integer> result = new ArrayList<>();
        if (root == null) {
            return result;
        }

        result.add(root.val);

        if (root.left != null) {
            result.addAll(preorderTraversal(root.left));
        }

        if (root.right != null) {
            result.addAll(preorderTraversal(root.right));
        }

        return result;
    }

    // TODO: 16-12-18 Note: Recursive solution is trivial, could you do it iteratively?


    public static class TreeNode {
        public int val;
        public TreeNode left;
        public TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }
}
