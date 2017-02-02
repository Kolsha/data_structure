package exam.review.data_structure.tool;

import exam.review.data_structure.TreeADT;
import exam.review.data_structure.TreeNode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

/**
 * Created by shanwu on 17-2-2.
 */
public class MiscUtils {
    private MiscUtils() {
        // tool class, do not implement the constructor
    }

    public static <T> void prettyPrint(TreeADT<T> tree) {
        System.out.println("================================================");
        HashMap<Integer, ArrayList<TreeNode<T>>> levelMap = new HashMap<>();
        Iterator<TreeNode<T>> treeIterator = tree.iterator();
        while (treeIterator.hasNext()) {
            TreeNode treeNode = treeIterator.next();
            final int levelNum = getNodeDepth(treeNode);
            ArrayList<TreeNode<T>> nodesList = levelMap.get(levelNum);
            if (nodesList == null) {
                nodesList = new ArrayList<>();
            }
            nodesList.add(treeNode);
            levelMap.put(levelNum, nodesList);
        }

        final int size = tree.size();
        for (int i = 0; i < size; i++) {
            ArrayList<TreeNode<T>> levelContent = levelMap.get(i);
            if (levelContent == null) break;
            for (TreeNode<T> value : levelContent) {
                System.out.print(value.getElement() + " ");
            }
            System.out.println();
        }
        System.out.println("================================================");
    }

    public static <T> int getNodeDepth(TreeNode<T> node) {
        if (isNodeRoot(node)) {
            return 0;
        } else {
            return 1 + getNodeDepth(node.getParent());
        }
    }

    public static <T> void preorderTraversal(TreeNode<T> root) {
        System.out.print(root.getElement() + " ");

        for (TreeNode<T> childNodes : root.getChildren()) {
            preorderTraversal(childNodes);
        }
    }

    public static <T> void postorderTraversal(TreeNode<T> root) {
        for (TreeNode<T> childNodes : root.getChildren()) {
            postorderTraversal(childNodes);
        }

        System.out.print(root.getElement() + " ");
    }

    public static <T> boolean isNodeRoot(TreeNode<T> node) {
        return node.getParent() == null;
    }
}
