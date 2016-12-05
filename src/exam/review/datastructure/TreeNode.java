package exam.review.datastructure;


import java.util.Iterator;

/**
 * Created by shanwu on 16-12-5.
 */
public interface TreeNode<T> {
    T getElement();

    <T> void setElement(T element);

    TreeNode<T> getParent();

    void setParent(TreeNode<T> parent);

    Iterator<TreeNode<T>> getChildren();

    void setChild(TreeNode<T> child);
}
