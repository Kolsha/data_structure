package exam.review.leetcode.trees.level_order_traversal;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by shanwu on 17-3-8.
 */
public class FindLargestValueInEachTreeRow {
    public List<Integer> largestValues(TreeNode root) {
        ArrayList<Integer> result = new ArrayList<>();

        if (root == null) {
            return result;
        }

        LinkedList<TreeNode> list = new LinkedList<>();
        list.add(root);
        while (!list.isEmpty()) {
            final int size = list.size();
            int max = list.get(0).val;
            for (int i = 0; i < size; i++) {
                TreeNode temp = list.poll();
                max = (temp.val > max) ? temp.val : max;
                if (temp.left != null) list.add(temp.left);
                if (temp.right != null) list.add(temp.right);
            }
            result.add(max);

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
