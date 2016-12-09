package exam.review.datastructure;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by shanwu on 16-12-9.
 */
public class LinkedBTree<T> implements BTreeADT<T> {
    private BTNodeADT<T> mRoot;
    private ArrayList<BTNodeADT<T>> mAllNodes = new ArrayList<>();


    @Override
    public BTNodeADT<T> getRoot() {
        return mRoot;
    }

    @Override
    public void addRoot(BTNodeADT<T> node) {
        if (mRoot != null) {
            throw new RuntimeException("Root's already existed!");
        }

        mRoot = node;
        mAllNodes.add(mRoot);
    }

    @Override
    public boolean insertLeft(BTNodeADT<T> target, BTNodeADT<T> node) {
        BTNodeADT found = (BTNodeADT) findNode(target.getElement());
        if (found == null) {
            return false;
        }
        found.setLeft(node);
        node.setParent(found);
        mAllNodes.add(node);
        return true;
    }

    @Override
    public boolean insertRight(BTNodeADT<T> target, BTNodeADT<T> node) {
        BTNodeADT found = (BTNodeADT) findNode(target.getElement());
        if (found == null) {
            return false;
        }
        found.setRight(node);
        node.setParent(found);
        mAllNodes.add(node);
        return true;
    }

    @Override
    public boolean remove(BTNodeADT<T> target) {
        BTNodeADT found = (BTNodeADT) findNode(target.getElement());
        if (mRoot.equals(found)) {
            // todo we will go with BST approach here
        } else {
            if (found.isExternal()) {
                BTNodeADT parent = (BTNodeADT) found.getParent();
                parent.setLeft(null);
                parent.setRight(null);
                found.setParent(null);
            } else {
                BTNodeADT parent = (BTNodeADT) found.getParent();
                BTNodeADT left = target.getLeft(found);
                BTNodeADT right = target.getRight(found);
                parent.setLeft(left);
                parent.setRight(right);
                found.setParent(null);
            }
        }
        return true;
    }

    @Override
    public boolean attach(BTNodeADT<T> target, BTreeADT<T> left, BTreeADT<T> right) {
        // // TODO: 16-12-9
        BTNodeADT found = (BTNodeADT) findNode(target.getElement());
        if(found == null) return false;

        boolean hasLeftTree = left !=null && !left.isEmpty();
        boolean avaiLeft = !found.hasLeft();
        boolean hasRightTree = right!=null && !right.isEmpty();
        boolean avaiRight = !found.hasRight();

        if(avaiLeft && hasLeftTree) {
            found.setLeft(left.getRoot());
            left.getRoot().setParent(found);
        }

        if(avaiRight && hasRightTree) {
            found.setRight(right.getRoot());
            right.getRoot().setParent(found);
        }

        return true;
    }

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
        TreeNode target = findNode(oldOne.getElement());
        target.setElement(newOne);
    }

    @Override
    public void addNewNode(TreeNode<T> parent, TreeNode<T> child) {
        if (mRoot == null || parent == null) {
            mRoot = (BTNodeADT<T>) child;
        } else {
            parent.setChild(child);
            child.setParent(parent);
        }
        mAllNodes.add((BTNodeADT<T>) child);
    }

    @Override
    public TreeNode<T> root() {
        return mRoot;
    }

    @Override
    public TreeNode<T> findNode(T target) {
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

    public static void main(String[] args) {
        LinkedBTree<String> tree = new LinkedBTree<>();
        LinkedBTreeNode<String> a = new LinkedBTreeNode<>("a");
        tree.addNewNode(null, a);
        LinkedBTreeNode<String> b = new LinkedBTreeNode<>("b");
        LinkedBTreeNode<String> c = new LinkedBTreeNode<>("c");
        LinkedBTreeNode<String> d = new LinkedBTreeNode<>("d");
        tree.addNewNode(a, b);
        tree.addNewNode(a, c);
        LinkedBTreeNode<String> e = new LinkedBTreeNode<>("e");
        LinkedBTreeNode<String> f = new LinkedBTreeNode<>("f");
        tree.addNewNode(c, e);
        tree.addNewNode(c, f);

        NormalTree.prettyPrint(tree);


    }
}
