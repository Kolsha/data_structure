package exam.review.datastructure;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by shanwu on 16-12-5.
 */
public class NormalTreeNode<T> implements TreeNode {
    private T element;
    private NormalTreeNode<T> mParentNode;
    private ArrayList<TreeNode<T>> mChildNodeList = new ArrayList<>();

    public NormalTreeNode(T element) {
        this.element = element;
    }

    @Override
    public T getElement() {
        return element;
    }

    @Override
    public void setElement(Object element) {
        this.element = (T) element;
    }

    @Override
    public TreeNode<T> getParent() {
        return mParentNode;
    }

    @Override
    public void setParent(TreeNode parent) {
        if (parent instanceof NormalTreeNode) {
            mParentNode = (NormalTreeNode) parent;
        }
    }

    @Override
    public Iterator<TreeNode<T>> getChildren() {
        return mChildNodeList.iterator();
    }

    @Override
    public void setChild(TreeNode child) {
        mChildNodeList.add(child);
    }

    public void setParentNode(NormalTreeNode parent) {
        mParentNode = parent;
    }

    public void setChildNode(NormalTreeNode childNode) {
        mChildNodeList.add(childNode);
    }

    public boolean isExternal() {
        return mChildNodeList.isEmpty();
    }

    public boolean isInternal() {
        return !mChildNodeList.isEmpty();
    }
}
