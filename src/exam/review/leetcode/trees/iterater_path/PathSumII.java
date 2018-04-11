package exam.review.leetcode.trees.iterater_path;

import java.util.ArrayList;
import java.util.List;

/**
 * Question description:
 * https://leetcode.com/problems/path-sum-ii/description/
 */
public class PathSumII {
    public List<List<Integer>> pathSum(TreeNode root, int sum) {
        List<List<Integer>> result = new ArrayList<>();
        matchPathSum(result, root, new ArrayList<Integer>(), 0, sum);
        return result;
    }

    private void matchPathSum(List<List<Integer>> result, TreeNode root, List<Integer> temp, int currSum, int target) {
        if (root == null) {
            return;
        }

        temp.add(root.val);

        if (isExternal(root)) {
            if ((root.val + currSum) == target) {
                result.add(new ArrayList(temp));
            }
            temp.remove(temp.size() - 1); // remove the last one in temp because it's depth first

            return;
        }

        currSum = currSum + root.val;
        if (root.left != null) {
            matchPathSum(result, root.left, temp, currSum, target);
        }

        if (root.right != null) {
            matchPathSum(result, root.right, temp, currSum, target);
        }

        temp.remove(temp.size() - 1);

    }

    private boolean isExternal(TreeNode root) {
        return root.left == null && root.right == null;
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
