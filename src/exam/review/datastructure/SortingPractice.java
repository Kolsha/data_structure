package exam.review.datastructure;

import java.util.Arrays;
import java.util.Comparator;

/**
 * Created by shanwu on 16-11-26.
 */
public class SortingPractice {
    /**
     * My solution to question:
     * "You are given two sorted arrays, A and B, and A has a large enough buffer at the end
     * to hold B. Write a method to merge B into A in sorted order."
     *
     * @param bigArray
     * @param smallArray
     * @param aEndIndex
     * @param bEndIndex
     * @param comp
     * @param <E>
     */
    public static <E> void mergePracitce(E[] bigArray, E[] smallArray, int aEndIndex, int bEndIndex, Comparator<E> comp) {
        if (bigArray == null || smallArray == null || bigArray.length < aEndIndex + bEndIndex) {
            return;
        }

        int newEndIndex = aEndIndex + bEndIndex + 1;

        while (newEndIndex >= 0) {
            if (aEndIndex >= 0 && bEndIndex >= 0) {
                if (comp.compare(bigArray[aEndIndex], smallArray[bEndIndex]) <= 0) {
                    bigArray[newEndIndex] = bigArray[aEndIndex];
                    aEndIndex--;
                } else {
                    bigArray[newEndIndex] = smallArray[bEndIndex];
                    bEndIndex--;
                }
            } else if (aEndIndex < 0) {
                bigArray[newEndIndex] = smallArray[bEndIndex];
                bEndIndex--;
            } else if (bEndIndex < 0) {
                bigArray[newEndIndex] = bigArray[aEndIndex];
                aEndIndex--;
            }
            newEndIndex--;
        }
    }

    /**
     * How to sort an array of strings with anagrams next to each other
     * http://stackoverflow.com/questions/15515324/how-to-sort-an-array-of-strings-with-anagrams-next-to-each-other
     *
     * @param stringArray
     */
    public static void sortStringWithAnagrams(String[] stringArray) {
        Arrays.sort(stringArray, new AnagramComparator());
    }

    public static class AnagramComparator implements Comparator<String> {

        public String getSortedString(String s) {
            char[] content = s.toCharArray();
            Arrays.sort(content);
            return new String(content);
        }

        public int compare(String s1, String s2) {
            return getSortedString(s1).compareTo(getSortedString(s2));
        }

    }


    public static void main(String[] args) {
        // Question 1 test case
        Integer[] test1 = new Integer[6];
        test1[0] = 5;
        test1[1] = 6;
        test1[2] = 9;

        Integer[] test2 = new Integer[3];
        test2[0] = 1;
        test2[1] = 7;
        test2[2] = 8;

        mergePracitce(test1, test2, 2, 2, new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2 - o1;
            }
        });
        System.out.println(Arrays.toString(test1));

        // Question 2 test case
        String[] testStringArray = new String[]{"god", "dog", "abc", "cab", "man"};
        sortStringWithAnagrams(testStringArray);
        System.out.println(Arrays.toString(testStringArray));



    }
}
