package exam.review.leetcode.trees;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by shanwu on 16-12-18.
 */
public class BinaryTreePostOrderTraversal {
    public List<Integer> postorderTraversal(BinaryTreePreorderTraversal.TreeNode root) {
        List<Integer> list = new ArrayList();
        if(root == null) {
            return list;
        }

        if(root.left!=null)
            list.addAll(postorderTraversal(root.left));
        if(root.right!=null)
            list.addAll(postorderTraversal(root.right));

        list.add(root.val);
        return list;
    }

    // TODO: 16-12-18 Note: Recursive solution is trivial, could you do it iteratively?
}
