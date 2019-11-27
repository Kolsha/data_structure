package exam.review.leetcode.trees.level_order_traversal;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by shanwu on 17-3-11.
 */
public class BinaryTreeRightSideView {
    public List<Integer> rightSideView(TreeNode root) {
        ArrayList<Integer> result = new ArrayList<>();
        if (root == null) {
            return result;
        }

        LinkedList<TreeNode> list = new LinkedList<>();
        list.add(root);

        while (!list.isEmpty()) {
            final int size = list.size();
            for (int i = 0; i < size; i++) {
                TreeNode temp = list.poll();
                if (i == 0) result.add(temp.val);
                if (temp.right != null) list.add(temp.right);
                if (temp.left != null) list.add(temp.left);
            }
        }

        return result;
    }


    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }
}
