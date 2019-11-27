package exam.review.leetcode.trees;

/**
 * Question description:
 * https://leetcode.com/problems/maximum-depth-of-binary-tree/description/
 * <p>
 * PC:2
 */
public class MaximumDepthofBinaryTree {
    public int maxDepth(BinaryTreePreorderTraversal.TreeNode root) {
        if (root == null) {
            return 0;
        }
        return 1 + Math.max(maxDepth(root.left), maxDepth(root.right));
    }
}