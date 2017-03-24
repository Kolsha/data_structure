package exam.review.leetcode.trees.level_order_traversal;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by shanwu on 17-3-24.
 */
public class BinaryTreeLevelOrderTraversalII {
    public List<List<Integer>> levelOrderBottom(TreeNode root) {
        ArrayList<List<Integer>> result = new ArrayList<>();
        if (root == null) {
            return result;
        }

        LinkedList<TreeNode> list = new LinkedList<>();
        list.add(root);
        while (!list.isEmpty()) {
            final int size = list.size();
            ArrayList<Integer> lvlList = new ArrayList<>();

            for (int i = 0; i < size; i++) {
                TreeNode temp = list.poll();
                lvlList.add(temp.val);
                if (temp.left != null) list.add(temp.left);
                if (temp.right != null) list.add(temp.right);
            }

            result.add(0, lvlList);
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
