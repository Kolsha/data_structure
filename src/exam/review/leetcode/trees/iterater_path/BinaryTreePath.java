package exam.review.leetcode.trees.iterater_path;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by shanwu on 17-1-14.
 * PC: 1
 */
public class BinaryTreePath {
    public List<String> binaryTreePaths(TreeNode root) {
        ArrayList<String> path = new ArrayList();
        if (root != null) binaryTreePaths(root, "", path);
        return path;
    }

    private void binaryTreePaths(TreeNode root, String path, List record) {
        if (root.left == null && root.right == null) {
            record.add(path + root.val);
            return;
        }

        if (root.left != null) binaryTreePaths(root.left, path + root.val + "->", record);

        if (root.right != null) binaryTreePaths(root.right, path + root.val + "->", record);

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
