package exam.review.leetcode.trees;

/**
 * Question description:
 * https://leetcode.com/problems/balanced-binary-tree/description/
 * PC: 2
 */
public class BalancedBinaryTree {
    public boolean isBalanced(TreeNode root) {
        if (root == null) {
            return true;
        }

        return Math.abs(getTreeHeight(root.left) - getTreeHeight(root.right)) <= 1 &&
                isBalanced(root.left) && isBalanced(root.right);
    }

    private int getTreeHeight(TreeNode root) {
        if (root == null) {
            return 0;
        }

        return Math.max(getTreeHeight(root.left), getTreeHeight(root.right)) + 1;
    }
}
