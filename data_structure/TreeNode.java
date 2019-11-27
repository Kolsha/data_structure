package exam.review.data_structure;


/**
 * Created by shanwu on 16-12-5.
 */
public interface TreeNode<T> {
    T getElement();

    void setElement(T element);

    TreeNode<T> getParent();

    void setParent(TreeNode<T> parent);

    Iterable<TreeNode<T>> getChildren();

    void setChild(TreeNode<T> child);

    boolean isInternal();

    boolean isExternal();
}
