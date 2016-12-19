package leetcode.trees;

/**
 * Created by shanwu on 16-12-18.
 */
public class BalancedBinaryTree {
    /**
     * the easiest solution
     * @param root
     * @return
     */
    public boolean isBalanced(BinaryTreePreorderTraversal.TreeNode root) {
        if(root == null) {
            return true;
        }
        return Math.abs(height(root.left)-height(root.right))<=1 &&
                isBalanced(root.left)  &&
                isBalanced(root.right);
    }

    public int height(BinaryTreePreorderTraversal.TreeNode root) {
        if(root == null) {
            return 0;
        }
        return Math.max(height(root.left), height(root.right))+1;
    }

    // FIXME: 16-12-18 [1,2,2,3,3,3,3,4,4,4,4,4,4,null,null,5,5] output: false; expect: true
    public boolean isBalanced2(BinaryTreePreorderTraversal.TreeNode root) {
        return (getMaxDepth(root)-getMinDepth(root)) <= 1;
    }

    public int getMaxDepth(BinaryTreePreorderTraversal.TreeNode root) {
        if(root == null) {
            return 0;
        } else {
            return 1 + Math.max(getMaxDepth(root.left), getMaxDepth(root.right));
        }
    }

    public int getMinDepth(BinaryTreePreorderTraversal.TreeNode root) {
        if(root == null) {
            return 0;
        } else {
            return 1+ Math.min(getMinDepth(root.left), getMinDepth(root.right));
        }
    }
}
