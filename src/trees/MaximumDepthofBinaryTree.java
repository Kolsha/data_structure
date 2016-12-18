package trees;

/**
 * Created by shanwu on 16-12-18.
 */
public class MaximumDepthofBinaryTree {
    public int maxDepth(BinaryTreePreorderTraversal.TreeNode root) {
        if(root==null) {
            return 0;
        }
        return 1 + Math.max(maxDepth(root.left), maxDepth(root.right));
    }
}
