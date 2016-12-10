package exam.review.datastructure;

import java.util.ArrayList;

/**
 * Created by shanwu on 16-12-9.
 */
public class LinkedBTreeNode<T> implements BTNodeADT<T> {
    private T mElement;
    private LinkedBTreeNode<T> mParentNode;
    private LinkedBTreeNode<T> mLeftNode;
    private LinkedBTreeNode<T> mRightNode;
    private ArrayList<TreeNode<T>> mChildNodes = new ArrayList<>();

    public LinkedBTreeNode(T element) {
        mElement = element;
    }

    @Override
    public T getElement() {
        return mElement;
    }

    @Override
    public void setElement(T element) {
        mElement = element;
    }


    @Override
    public TreeNode<T> getParent() {
        return mParentNode;
    }

    @Override
    public void setParent(TreeNode<T> parent) {
        mParentNode = (LinkedBTreeNode<T>) parent;
    }

    @Override
    public Iterable<TreeNode<T>> getChildren() {
        return mChildNodes;
    }

    @Override
    public void setChild(TreeNode<T> child) {
        if (mLeftNode == null) {
            child.setParent(this);
            mLeftNode = (LinkedBTreeNode<T>) child;
            mChildNodes.add(child);
        } else if (mRightNode == null) {
            child.setParent(this);
            mRightNode = (LinkedBTreeNode<T>) child;
            mChildNodes.add(child);
        } else {
            throw new RuntimeException("No more child node is allowed");
        }
    }

    @Override
    public boolean isInternal() {
        return !mChildNodes.isEmpty();
    }

    @Override
    public boolean isExternal() {
        return mChildNodes.isEmpty();
    }

    @Override
    public BTNodeADT<T> getLeft() {
        return mLeftNode;
    }

    @Override
    public BTNodeADT<T> getRight() {
        return mRightNode;
    }

    @Override
    public boolean hasLeft() {
        return mLeftNode == null;
    }

    @Override
    public boolean hasRight() {
        return mRightNode == null;
    }

    @Override
    public void setLeft(BTNodeADT<T> v) {
        mLeftNode = (LinkedBTreeNode<T>) v;
    }

    @Override
    public void setRight(BTNodeADT<T> v) {
        mRightNode = (LinkedBTreeNode<T>) v;
    }
}
