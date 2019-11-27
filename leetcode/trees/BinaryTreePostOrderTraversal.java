package exam.review.leetcode.trees;

import java.util.ArrayList;
import java.util.List;

/**
 * Question description:
 * https://leetcode.com/problems/binary-tree-postorder-traversal/description/
 * PC: 2
 */
public class BinaryTreePostOrderTraversal {
    public List<Integer> postorderTraversal(BinaryTreePreorderTraversal.TreeNode root) {
        ArrayList<Integer> result = new ArrayList<>();
        if (root == null) {
            return result;
        }

        if (root.left != null) {
            result.addAll(postorderTraversal(root.left));
        }

        if (root.right != null) {
            result.addAll(postorderTraversal(root.right));
        }

        result.add(root.val);

        return result;
    }

    // TODO: 16-12-18 Note: Recursive solution is trivial, could you do it iteratively?
}
