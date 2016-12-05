package exam.review.datastructure;

import java.util.Iterator;

/**
 * Created by shanwu on 16-12-5.
 */
public interface TreeADT<T> {
    int size();

    boolean isEmpty();

    Iterator<T> iterator();

    void replace(TreeNode<T> oldOne, TreeNode<T> newOne);

    void addNewNode(TreeNode<T> parent, TreeNode<T> child);

    TreeNode<T> getRoot();

    TreeNode<T> findNode(T target);
}
