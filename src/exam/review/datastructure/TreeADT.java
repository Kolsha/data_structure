package exam.review.datastructure;

import java.util.Iterator;

/**
 * Created by shanwu on 16-12-5.
 */
public interface TreeADT<T> {
    int size();

    boolean isEmpty();

    Iterator<T> iterator();

    void replace(TreeNode<T> oldOne, T newValue);

    void addNewNode(TreeNode<T> parent, TreeNode<T> child);

    TreeNode<T> root();

    TreeNode<T> findNode(T target);

    TreeNode<T> parent(TreeNode<T> v);

    Iterable<TreeNode<T>> children(TreeNode<T> v);

    boolean isInternal(TreeNode<T> v);

    boolean isExternal(TreeNode<T> v);

    boolean isRoot(TreeNode<T> v);
}
