package exam.review.leetcode.trees.iterater_path;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by shanwu on 17-1-14.
 * PC: 1
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

    /**
     *  my 2nd approach
     * @param root
     * @param sum
     * @return
     */
    public boolean hasPathSumII(TreeNode root, int sum) {
        return findPathSum(root,sum,0);
    }

    private boolean findPathSum(TreeNode root, int sum, int curr) {
        if(root == null) {
            return false;
        }

        if(isExternal(root)) {
            return (curr+root.val) == sum;
        }

        boolean findLeft = false, findRight = false;
        if(root.left!=null) {
            findLeft = findPathSum(root.left, sum, curr+root.val);
        }

        if(root.right!=null) {
            findRight = findPathSum(root.right, sum, curr+root.val);
        }

        return findLeft || findRight;
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
