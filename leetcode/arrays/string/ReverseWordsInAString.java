package exam.review.leetcode.arrays.string;

import java.util.Arrays;
import java.util.Collections;

/**
 * Created by shanwu on 17-1-7.
 */
public class ReverseWordsInAString {
    public static String reverseWords(String s) {
        String[] words = s.trim().split(" +");
        Collections.reverse(Arrays.asList(words));
        return String.join(" ", words);
    }

    public static void main(String[] args) {
        String test = " a  b ";
        System.out.println(reverseWords(test));
    }
}
