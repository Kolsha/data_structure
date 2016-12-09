package exam.review.datastructure;

/**
 * Created by shanwu on 16-12-9.
 */
public interface BTreeADT<T> extends TreeADT<T> {
    BTNodeADT<T> getRoot();

    void addRoot(BTNodeADT<T> node);

    boolean insertLeft(BTNodeADT<T> target, BTNodeADT<T> node);

    boolean insertRight(BTNodeADT<T> target, BTNodeADT<T> node);

    boolean remove(BTNodeADT<T> target);

    boolean attach(BTNodeADT<T> target, BTreeADT<T> left, BTreeADT<T> right);
}
