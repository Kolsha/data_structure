package leetcode.trees;

/**
 * Created by shanwu on 16-12-18.
 */
public class MinimumDepthofBinaryTree {
    // FIXME: 16-12-18
    public int minDepth(BinaryTreePreorderTraversal.TreeNode root) {
        if(root == null) {
            return 0;
        } else {
            return 1+ Math.min(minDepth(root.left), minDepth(root.right));
        }

    }
}
