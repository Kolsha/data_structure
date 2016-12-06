package exam.review.datastructure;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by shanwu on 16-12-5.
 */
public class Tree<T> implements TreeADT<T> {
    private TreeNode<T> mRoot;
    private ArrayList<TreeNode<T>> mAllNodes = new ArrayList<>();

    @Override
    public int size() {
        return mAllNodes.size();
    }

    @Override
    public boolean isEmpty() {
        return mAllNodes.isEmpty();
    }

    @Override
    public Iterator iterator() {
        return mAllNodes.iterator();
    }

    @Override
    public void replace(TreeNode oldOne, TreeNode newOne) {
        TreeNode target = findNode((T) oldOne.getElement());
        target.setElement(newOne.getElement());
    }

    @Override
    public void addNewNode(TreeNode parent, TreeNode child) {
        if (mRoot == null || parent == null) {
            mRoot = child;
        } else {
            parent.setChild(child);
            child.setParent(parent);
        }
        mAllNodes.add(child);
    }

    @Override
    public TreeNode getRoot() {
        return mRoot;
    }

    @Override
    public TreeNode findNode(T target) {
        for (TreeNode node : mAllNodes) {
            if (node.equals(target)) {
                return node;
            }
        }
        return null;
    }

    public static <T> int getNodeDepth(TreeNode<T> node) {
        if (isRoot(node)) {
            return 0;
        } else {
            return 1 + getNodeDepth(node.getParent());
        }
    }

    public static <T> int getTreeHeight(Tree<T> tree) {
        ArrayList<TreeNode<T>> treeNodes = tree.mAllNodes;
        int h = 0;
        for (TreeNode<T> node : treeNodes) {
            h = Math.max(getNodeDepth(node), h);
        }
        return h;
    }

    public static <T> boolean isRoot(TreeNode<T> node) {
        return node.getParent() == null;
    }

    public static <T> void prettyPrint(Tree<T> tree) {
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
    }

    private static <T> void preorderTraversal(TreeNode<T> root) {
        System.out.print(root.getElement() + " ");

        for (TreeNode<T> childNodes : root.getChildren()) {
            preorderTraversal(childNodes);
        }
    }

    private static <T> void postorderTraversal(TreeNode<T> root) {
        for (TreeNode<T> childNodes : root.getChildren()) {
            postorderTraversal(childNodes);
        }

        System.out.print(root.getElement() + " ");
    }

    public static void main(String[] args) {
        Tree<String> tree = new Tree<>();
        NormalTreeNode<String> a = new NormalTreeNode<>("a");
        tree.addNewNode(null, a);
        NormalTreeNode<String> b = new NormalTreeNode<>("b");
        NormalTreeNode<String> c = new NormalTreeNode<>("c");
        NormalTreeNode<String> d = new NormalTreeNode<>("d");
        tree.addNewNode(a, b);
        tree.addNewNode(a, c);
        tree.addNewNode(a, d);
        NormalTreeNode<String> e = new NormalTreeNode<>("e");
        NormalTreeNode<String> f = new NormalTreeNode<>("f");
        tree.addNewNode(c, e);
        tree.addNewNode(c, f);

        prettyPrint(tree);

        System.out.println("Tree height: " + getTreeHeight(tree));

        System.out.print("Tree preorder trasversal: ");
        preorderTraversal(tree.getRoot());
        System.out.println();

        System.out.print("Tree postorder trasversal: ");
        postorderTraversal(tree.getRoot());
        System.out.println();

    }


}
