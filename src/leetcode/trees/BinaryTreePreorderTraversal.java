package leetcode.trees;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by shanwu on 16-12-18.
 */
public class BinaryTreePreorderTraversal {
    /**
     * Given a binary tree, return the preorder traversal of its nodes' values.
     * @param root
     * @return
     */
    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> list = new ArrayList();

        if (root == null) {
            return list;
        }


        list.add(root.val);
        if (root.left != null) {
            list.addAll(preorderTraversal(root.left));
        }

        if (root.right != null) {
            list.addAll(preorderTraversal(root.right));
        }
        return list;
    }

    // TODO: 16-12-18 Note: Recursive solution is trivial, could you do it iteratively?


    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }
}
