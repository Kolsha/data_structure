package exam.review.data_structure;

import java.util.Comparator;

/**
 * Created by shanwu on 16-11-23.
 */
public class QuickSort {
    public static <E> void sortInPlace(E[] array, Comparator<E> comp) {
        // TODO: 16-11-25
        if (array == null || array.length < 2) {
            return;
        }


    }

    public static <E> void sort(SinglyLinkedList<E> origin, Comparator<E> comp) {
        if (origin == null || origin.size < 2) {
            return;
        }

        // divide
        // pick pivot point (pick first element in this implementation)
        E pivot = origin.peek().value;

        SinglyLinkedList<E> L = new SinglyLinkedList<E>();
        SinglyLinkedList<E> E = new SinglyLinkedList<E>();
        SinglyLinkedList<E> G = new SinglyLinkedList<E>();

        while (!origin.isEmpty()) {
            E nodeValue = origin.peek().value;
            if (comp.compare(pivot, nodeValue) < 0) {
                G.addNodeToLast(origin.removeFirst().value);
            } else if (comp.compare(pivot, nodeValue) > 0) {
                L.addNodeToLast(origin.removeFirst().value);
            } else {
                E.addNodeToLast(origin.removeFirst().value);
            }
        }

        // recursive
        sort(L, comp);
        sort(E, comp);
        sort(G, comp);

        while (!L.isEmpty()) {
            origin.addNodeToLast(L.removeFirst().value);
        }

        while (!E.isEmpty()) {
            origin.addNodeToLast(E.removeFirst().value);
        }

        while (!G.isEmpty()) {
            origin.addNodeToLast(G.removeFirst().value);
        }
    }

    public static void main(String[] args) {
        SinglyLinkedList<Integer> list = new SinglyLinkedList<>();

        Integer[] test = new Integer[]{22, 7, 1, 2, 9, 3, 4, 5};
        list.addArrayToList(test);
        Comparator<Integer> comp = new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1 - o2;
            }
        };
        list.printNodes();
        sort(list, comp);
        System.out.print("Quick Sort: ");
        list.printNodes();
    }

}
