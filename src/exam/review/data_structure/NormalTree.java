package exam.review.data_structure;

import exam.review.data_structure.misc.MiscUtils;

import java.util.ArrayList;
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

    public static <T> int getTreeHeight(NormalTree<T> tree) {
        ArrayList<TreeNode<T>> treeNodes = tree.mAllNodes;
        int h = 0;
        for (TreeNode<T> node : treeNodes) {
            h = Math.max(MiscUtils.getNodeDepth(node), h);
        }
        return h;
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

        MiscUtils.prettyPrint(tree);

        System.out.println("Tree height: " + getTreeHeight(tree));

        System.out.print("Tree preorder trasversal: ");
        MiscUtils.preorderTraversal(tree.root());
        System.out.println();

        System.out.print("Tree postorder trasversal: ");
        MiscUtils.postorderTraversal(tree.root());
        System.out.println();


    }


}
