package exam.review.data_structure.misc;

import java.util.Comparator;

/**
 * Created by shanwu on 17-2-2.
 */
public class DefaultComparator<T extends Comparable> implements Comparator<T> {
    @Override
    public int compare(T o1, T o2) {
        return o1.compareTo(o2);
    }
}
