package exam.review.leetcode.trees.iterater_path;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by shanwu on 17-1-14.
 */
public class PathSum {
    public boolean hasPathSum(TreeNode root, int sum) {
        ArrayList<Integer> pathSumList = new ArrayList();

        if(root!=null) hasPathSum(root, 0, pathSumList);

        for(Integer num:pathSumList) {
            if(num == sum) {
                return true;
            }
        }
        return false;
    }

    private void hasPathSum(TreeNode root, int sum, List record) {
        if(root.left == null && root.right == null) {
            record.add(sum+root.val);
        }

        if(root.left!=null) hasPathSum(root.left, sum+root.val, record);
        if(root.right!=null) hasPathSum(root.right, sum+root.val,record);
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
