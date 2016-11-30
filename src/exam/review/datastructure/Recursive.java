package exam.review.datastructure;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by shanwu on 16-11-29.
 */
public class Recursive {

    public static int getFactorial(int n) {
        if (n == 0) return 1;
        return n * getFactorial(n - 1);
    }

    public static int getNthFibnacci(int n) {
        if (n == 0) {
            return 0;
        } else if (n == 1) {
            return 1;
        } else {
            return getNthFibnacci(n - 1) + getNthFibnacci(n - 2);
        }
    }

    public static <E> void reverseArray(E[] array, final int left, final int right) {
        if (left < right) {
            // swap objects at index i and index j
            E temp = array[right];
            array[right] = array[left];
            array[left] = temp;

            // recursive
            reverseArray(array, left + 1, right - 1);
        }
    }

    public static <E> ArrayList<ArrayList<E>> getAllSubSet(ArrayList<E> set, int index) {
        ArrayList<ArrayList<E>> allSubSet;
        if (set.size() == index) {
            allSubSet = new ArrayList<>();
            allSubSet.add(new ArrayList<E>()); // 空集合为任何集合的子集合
        } else {
            allSubSet = getAllSubSet(set, index + 1);
            E item = set.get(index);
            ArrayList<ArrayList<E>> moreSubSet = new ArrayList<>(); // try to avoid concurrentMod exception
            for (ArrayList<E> subset : allSubSet) {
                ArrayList<E> newSubset = new ArrayList<E>();
                newSubset.addAll(subset);
                newSubset.add(item);
                moreSubSet.add(newSubset);
            }
            allSubSet.addAll(moreSubSet);
        }
        return allSubSet;
    }

    public static void main(String[] args) {
        int target = 5;
        System.out.println("factorial: " + target + "! = " + getFactorial(target));

        target = 10;
        System.out.println("Fibonacci: " + target + "th number = " + getNthFibnacci(target));

        Integer[] array = new Integer[]{1, 2, 3, 4, 5, 6, 7, 8, 9};
        reverseArray(array, 0, array.length - 1);
        System.out.println(Arrays.toString(array));

        ArrayList<Integer> intArrayList = new ArrayList<>();
        intArrayList.add(1);
        intArrayList.add(2);

        System.out.println("all subset: " + getAllSubSet(intArrayList, 0));


    }
}
