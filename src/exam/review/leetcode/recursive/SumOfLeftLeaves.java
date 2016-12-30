package exam.review.leetcode.recursive;

import exam.review.leetcode.trees.BinaryTreePreorderTraversal;

/**
 * Created by shanwu on 16-12-24.
 */
public class SumOfLeftLeaves {
    public int sumOfLeftLeaves(BinaryTreePreorderTraversal.TreeNode root) {
        if(root == null) {
            return 0;
        }

        int result =0;
        if(root.left!=null && isExternal(root.left)) {
            result = root.left.val;
        }

        return result + sumOfLeftLeaves(root.left) + sumOfLeftLeaves(root.right);
    }

    public boolean isExternal(BinaryTreePreorderTraversal.TreeNode node) {
        return node.left == null && node.right ==null;
    }
}
