package exam.review.leetcode.trees;

import java.util.ArrayList;
import java.util.List;

/**
 * Question description:
 * https://leetcode.com/problems/binary-tree-inorder-traversal/description/
 * PC: 2
 */
public class BinaryTreeInOrderTraversal {
    public List<Integer> inorderTraversal(BinaryTreePreorderTraversal.TreeNode root) {
        ArrayList<Integer> result = new ArrayList<>();
        if(root == null) {
            return result;
        }


        if(root.left!=null) {
            result.addAll(inorderTraversal(root.left));
        }

        result.add(root.val);

        if(root.right!=null) {
            result.addAll(inorderTraversal(root.right));
        }

        return result;
    }

    // TODO: 16-12-18 Note: Recursive solution is trivial, could you do it iteratively?

}
