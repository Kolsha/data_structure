package exam.review.leetcode.recursive;

import exam.review.leetcode.trees.BinaryTreePreorderTraversal;

/**
 * Created by shanwu on 16-12-23.
 */
public class SameTree {
    public boolean isSameTree(BinaryTreePreorderTraversal.TreeNode p, BinaryTreePreorderTraversal.TreeNode q) {
        if(p ==null && q == null) {
            return true;
        }

        if(p == null || q == null) {
            return false;
        }

        if(p.val == q.val) {
            return isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
        } else {
            return false;
        }

    }
}
