package exam.review.leetcode.trees.iterater_path;

import java.util.ArrayList;
import java.util.List;

/**
 * Question description:
 * https://leetcode.com/problems/binary-tree-paths/description/
 * <p>
 * PC: 1
 */
public class BinaryTreePath {
    public List<String> binaryTreePaths(TreeNode root) {
        ArrayList<String> result = new ArrayList<>();
        if (root != null) {
            initTreePath(root, "", result);
        }
        return result;
    }

    public void initTreePath(TreeNode root, String path, List<String> result) {
        if (root.left == null && root.right == null) {
            result.add(path + root.val);
        }

        if (root.left != null) {
            initTreePath(root.left, path + root.val + "->", result);
        }

        if (root.right != null) {
            initTreePath(root.right, path + root.val + "->", result);
        }
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
