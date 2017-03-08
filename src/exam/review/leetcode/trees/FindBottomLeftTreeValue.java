package exam.review.leetcode.trees;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by shanwu on 17-3-8.
 */
// TODO: 17-3-8 recursive approach
public class FindBottomLeftTreeValue {

    public int findLeftMostNode(TreeNode root) {
        if (root == null) return 0;

        int result = 0;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);

        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                if (i == 0) result = node.val;
                if (node.left != null) queue.add(node.left);
                if (node.right != null) queue.add(node.right);
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
