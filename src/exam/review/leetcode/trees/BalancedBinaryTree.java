package exam.review.leetcode.trees;

/**
 * Created by shanwu on 16-12-18.
 */
public class BalancedBinaryTree {
    /**
     * the easiest solution
     * PC: 1
     * @param root
     * @return
     */
    public boolean isBalanced(BinaryTreePreorderTraversal.TreeNode root) {
        if (root == null) {
            return true;
        }
        return Math.abs(height(root.left) - height(root.right)) <= 1 &&
                isBalanced(root.left) &&
                isBalanced(root.right);
    }

    public int height(BinaryTreePreorderTraversal.TreeNode root) {
        if (root == null) {
            return 0;
        }
        return Math.max(height(root.left), height(root.right)) + 1;
    }
}
