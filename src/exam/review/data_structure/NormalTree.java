package exam.review.data_structure;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

/**
 * Created by shanwu on 16-12-5.
 */
public class NormalTree<T> implements TreeADT<T> {
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
    public void replace(TreeNode<T> oldOne, T newOne) {
        TreeNode target = findNode((T) oldOne.getElement());
        target.setElement(newOne);
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
    public TreeNode root() {
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

    @Override
    public TreeNode<T> parent(TreeNode<T> v) {
        return v.getParent();
    }

    @Override
    public Iterable<TreeNode<T>> children(TreeNode<T> v) {
        return v.getChildren();
    }

    @Override
    public boolean isInternal(TreeNode<T> v) {
        return v.isInternal();
    }

    @Override
    public boolean isExternal(TreeNode<T> v) {
        return v.isExternal();
    }

    @Override
    public boolean isRoot(TreeNode<T> v) {
        return v.getParent() == null;
    }

    public static <T> int getNodeDepth(TreeNode<T> node) {
        if (isNodeRoot(node)) {
            return 0;
        } else {
            return 1 + getNodeDepth(node.getParent());
        }
    }

    public static <T> int getTreeHeight(NormalTree<T> tree) {
        ArrayList<TreeNode<T>> treeNodes = tree.mAllNodes;
        int h = 0;
        for (TreeNode<T> node : treeNodes) {
            h = Math.max(getNodeDepth(node), h);
        }
        return h;
    }

    public static <T> boolean isNodeRoot(TreeNode<T> node) {
        return node.getParent() == null;
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
        NormalTree<String> tree = new NormalTree<>();
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
        preorderTraversal(tree.root());
        System.out.println();

        System.out.print("Tree postorder trasversal: ");
        postorderTraversal(tree.root());
        System.out.println();


    }


}
