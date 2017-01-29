package exam.review.data_structure.heap;

import exam.review.data_structure.BTNodeADT;
import exam.review.data_structure.BTreeADT;

/**
 * This complete binary tree is going to help build the heap data structure
 * Created by shanwu on 17-1-29.
 */
public interface CompleteBTreeADT<T> extends BTreeADT<T> {
    BTNodeADT<T> add(T element);

    T remove();
}
