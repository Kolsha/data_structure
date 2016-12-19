package leetcode.trees;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by shanwu on 16-12-18.
 */
public class BinaryTreeInOrderTraversal {
    public List<Integer> inorderTraversal(BinaryTreePreorderTraversal.TreeNode root) {
        List<Integer> list = new ArrayList();
        if(root == null) {
            return list;
        }

        if(root.left!=null)
            list.addAll(inorderTraversal(root.left));

        list.add(root.val);

        if(root.right!=null)
            list.addAll(inorderTraversal(root.right));

        return list;
    }

    // TODO: 16-12-18 Note: Recursive solution is trivial, could you do it iteratively?

}
