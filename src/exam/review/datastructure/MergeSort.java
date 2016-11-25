package exam.review.datastructure;

import java.util.Arrays;
import java.util.Comparator;

public class MergeSort<E> {

    public static <E> void sort(SinglyLinkedList<E> origin, Comparator<E> comp) {
        if (origin == null || origin.size < 2) {
            return;
        }

        // divide
        SinglyLinkedList<E> list1 = new SinglyLinkedList<E>();
        SinglyLinkedList<E> list2 = new SinglyLinkedList<E>();

        final int size = origin.size;
        int i = 0;
        while (i++ < size / 2) {
            list1.addNodeToLast(origin.removeFirst().value);
        }

        while (!origin.isEmpty()) {
            list2.addNodeToLast(origin.removeFirst().value);
        }
        System.out.print("Divided1 :");
        list1.printNodes();
        System.out.print("Divided2 : ");
        list2.printNodes();

        // recursive
        sort(list1, comp);
        sort(list2, comp);

        // conquer
        merge(origin, list1, list2, comp);
    }

    // merge sorted lists
    public static <E> void merge(SinglyLinkedList<E> origin, SinglyLinkedList<E> list1,
                                 SinglyLinkedList<E> list2, Comparator<E> comp) {
        while (!list1.isEmpty() && !list2.isEmpty()) {
            if (comp.compare(list1.peek().value, list2.peek().value) <= 0) {
                origin.addNodeToLast(list1.removeFirst().value);
            } else {
                origin.addNodeToLast(list2.removeFirst().value);
            }
        }

        while (!list1.isEmpty()) {
            origin.addNodeToLast(list1.removeFirst().value);
        }

        while (!list2.isEmpty()) {
            origin.addNodeToLast(list2.removeFirst().value);
        }

        System.out.print("Conquer: ");
        origin.printNodes();
    }

    public static <E> void sort(E[] array, Comparator<E> comp) {
        if (array == null || array.length == 1)
            return;

        // divide
        E[] tempArray1 = (E[]) new Object[array.length / 2];
        E[] tempArray2 = (E[]) new Object[array.length - array.length / 2];

        for (int i = 0; i < array.length; i++) {
            if (i < array.length / 2) {
                tempArray1[i] = array[i];
            } else {
                tempArray2[i - array.length / 2] = array[i];
            }
        }
        System.out.println("Divided1 : " + Arrays.toString(tempArray1));
        System.out.println("Divided2 : " + Arrays.toString(tempArray2));

        // recursion
        sort(tempArray1, comp);
        sort(tempArray2, comp);

        // conquer
        merge(array, tempArray1, tempArray2, comp);
    }

    // merge sorted arrays
    public static <E> void merge(E[] origin, E[] array1, E[] array2, Comparator<E> comp) {
        int index1 = 0;
        int index2 = 0;
        int sortIndex = 0;
        E[] sortedArray = (E[]) new Object[origin.length];

        while (index1 < array1.length && index2 < array2.length) {
            if (comp.compare(array1[index1], array2[index2]) <= 0) {
                sortedArray[sortIndex] = array1[index1];
                index1++;
            } else {
                sortedArray[sortIndex] = array2[index2];
                index2++;
            }
            sortIndex++;
        }

        while (index1 < array1.length) {
            sortedArray[sortIndex] = array1[index1];
            index1++;
            sortIndex++;
        }

        while (index2 < array2.length) {
            sortedArray[sortIndex] = array2[index2];
            index2++;
            sortIndex++;
        }
        System.out.println("Conquer: " + Arrays.toString(sortedArray));

        System.arraycopy(sortedArray, 0, origin, 0, sortedArray.length);
    }

    public static void main(String[] args) {
        Integer[] test = new Integer[]{22, 7, 1, 2, 9, 3, 4, 5};
        Comparator<Integer> comp = new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1 - o2;
            }
        };

        sort(test, comp);

        System.out.println("Sorted: " + Arrays.toString(test));

        SinglyLinkedList<Integer> list = new SinglyLinkedList<>();

        test = new Integer[]{22, 7, 1, 2, 9, 3, 4, 5};
        list.addArrayToList(test);
        list.printNodes();
        sort(list, comp);
        list.printNodes();

    }
}
