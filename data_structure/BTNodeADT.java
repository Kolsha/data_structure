package exam.review.data_structure;

/**
 * Created by shanwu on 16-12-7.
 */
public interface BTNodeADT<T> extends TreeNode<T> {
    BTNodeADT<T> getLeft();

    BTNodeADT<T> getRight();

    boolean hasLeft();

    boolean hasRight();

    void setLeft(BTNodeADT<T> v);

    void setRight(BTNodeADT<T> v);
}
