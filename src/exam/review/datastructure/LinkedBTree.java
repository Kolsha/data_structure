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

    /**
     * runs in O(1) time.
     *
     * @param node new node going to be the root of the tree
     */
    @Override
    public void addRoot(BTNodeADT<T> node) {
        if (mRoot != null) {
            throw new RuntimeException("Root's already existed!");
        }

        mRoot = node;
        mAllNodes.add(mRoot);
    }

    /**
     * runs in O(1) time, insert node as target's left child
     *
     * @param target
     * @param node
     * @return
     */
    @Override
    public void insertLeft(BTNodeADT<T> target, BTNodeADT<T> node) {
        if (!target.hasLeft()) {
            target.setLeft(node);
            node.setParent(target);
            mAllNodes.add(node);
        } else {
            throw new RuntimeException("Left node's already existed, try replace() or insertRight()");
        }
    }

    /**
     * runs in O(1) time, insert node as target's right child
     *
     * @param target
     * @param node
     */
    @Override
    public void insertRight(BTNodeADT<T> target, BTNodeADT<T> node) {
        if (!target.hasRight()) {
            target.setRight(node);
            node.setParent(target);
            mAllNodes.add(node);
        } else {
            throw new RuntimeException("Right node's already existed, try replace() or insertLeft()");
        }
    }

    /**
     * runs in O(n) time, remove external node or node with single child
     *
     * @param target
     */
    @Override
    public void remove(BTNodeADT<T> target) {
        if (target == null || !mAllNodes.contains(target)) {
            return;
        }


        if (target.equals(mRoot)) {
            if (mRoot.isExternal()) {
                mRoot = null;
            } else {
                BTNodeADT left = target.getLeft();
                BTNodeADT right = target.getRight();
                if (left != null && right != null) {
                    throw new RuntimeException("remove doesn't support nodes with 2 child nodes");
                }

                if (left != null) {
                    mRoot = left;
                }
                if (right != null) {
                    mRoot = right;
                }
                mRoot.setParent(null);
            }
        } else {
            if (target.isExternal()) {
                BTNodeADT parent = (BTNodeADT) target.getParent();
                parent.setLeft(null);
                parent.setRight(null);
                target.setParent(null);
            } else {
                BTNodeADT parent = (BTNodeADT) target.getParent();
                // remove connection from parent node
                boolean isNodeLeft = target.equals(parent.getLeft());
                if (isNodeLeft) {
                    parent.setLeft(null);
                } else {
                    parent.setRight(null);
                }
                target.setParent(null);


                // connection parent and child nodes
                BTNodeADT left = target.getLeft();
                BTNodeADT right = target.getRight();
                if (left != null && right != null) {
                    throw new RuntimeException("remove doesn't support nodes with 2 child nodes");
                }

                if (left != null) {
                    parent.setLeft(left);
                    left.setParent(parent);
                }

                if (right != null) {
                    parent.setRight(right);
                    right.setParent(parent);
                }

            }
        }
        mAllNodes.remove(target);
    }

    @Override
    public void attach(BTNodeADT<T> target, BTreeADT<T> left, BTreeADT<T> right) {
        BTNodeADT found = (BTNodeADT) findNode(target.getElement());
        if (found == null) return;

        boolean hasLeftTree = left != null && !left.isEmpty();
        boolean avaiLeft = !found.hasLeft();
        boolean hasRightTree = right != null && !right.isEmpty();
        boolean avaiRight = !found.hasRight();

        if (avaiLeft && hasLeftTree) {
            found.setLeft(left.getRoot());
            left.getRoot().setParent(found);
        }

        if (avaiRight && hasRightTree) {
            found.setRight(right.getRoot());
            right.getRoot().setParent(found);
        }
    }

    /**
     * runs in O(1) time
     *
     * @return the number of nodes for the linked-list implemented Binary Tree.
     */
    @Override
    public int size() {
        return mAllNodes.size();
    }

    /**
     * runs in O(1) time
     *
     * @return boolean value indicating whether the binary tree is empty or not.
     */
    @Override
    public boolean isEmpty() {
        return mAllNodes.isEmpty();
    }

    /**
     * runs in O(n)
     *
     * @return return iterator for all nodes
     */
    @Override
    public Iterator iterator() {
        return mAllNodes.iterator();
    }

    /**
     * runs in O(1) time.
     *
     * @param target
     * @param newOne
     */
    @Override
    public void replace(TreeNode<T> target, T newOne) {
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

    /**
     * runs in O(1) time. Get parent node of node v.
     *
     * @param v
     * @return parent node of node v
     */
    @Override
    public TreeNode<T> parent(TreeNode<T> v) {
        return v.getParent();
    }

    /**
     * runs in O(1) time. Get all child nodes of node v.
     *
     * @param v
     * @return child nodes Iterable
     */
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

        System.out.println("======================");
        tree.remove(e);
        NormalTree.prettyPrint(tree);

        System.out.println("======================");
        tree.remove(e);
        NormalTree.prettyPrint(tree);

        System.out.println("======================");
        tree.remove(c);
        NormalTree.prettyPrint(tree);
    }
}