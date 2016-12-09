package exam.review.datastructure;

/**
 * Created by shanwu on 16-12-7.
 */
public interface BTNodeADT<T> extends TreeNode<T> {
    BTNodeADT<T> getLeft(BTNodeADT<T> v);

    BTNodeADT<T> getRight(BTNodeADT<T> v);

    boolean hasLeft();

    boolean hasRight();

    void setLeft(BTNodeADT<T> v);

    void setRight(BTNodeADT<T> v);
}
