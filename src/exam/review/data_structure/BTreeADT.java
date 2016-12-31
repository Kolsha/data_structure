package exam.review.data_structure;

/**
 * Created by shanwu on 16-12-9.
 */
public interface BTreeADT<T> extends TreeADT<T> {
    BTNodeADT<T> getRoot();

    void addRoot(BTNodeADT<T> node);

    void insertLeft(BTNodeADT<T> target, BTNodeADT<T> node);

    void insertRight(BTNodeADT<T> target, BTNodeADT<T> node);

    void remove(BTNodeADT<T> target);

    void attach(BTNodeADT<T> target, BTreeADT<T> left, BTreeADT<T> right);
}
